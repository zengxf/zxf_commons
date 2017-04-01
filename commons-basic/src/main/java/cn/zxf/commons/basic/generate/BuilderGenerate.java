package cn.zxf.commons.basic.generate;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.zxf.commons.basic.reflect.ClassUtil;
import lombok.AllArgsConstructor;

/**
 * Builder 帮助类 java 代码生成器 <br>
 * 没有用 lombok 的，可以用此帮助类生成 Builder 的 java 代码
 * 
 * @author zxf
 */
@AllArgsConstructor
public class BuilderGenerate {
    private static final String	OBJ_SIGN = "value";
    private static final String	BR	 = "\n";

    private Class<?>		clazz;
    private final StringBuilder	sb	 = new StringBuilder( 1024 );

    /**
     * 生成 java 代码
     * 
     * @param clazz
     * @return
     */
    public static String generateJavaCode( Class<?> clazz ) {
	return new BuilderGenerate( clazz ).toJavaCode();
    }

    private String toJavaCode() {
	List<Method> methods = ClassUtil.getSetMethods( clazz );

	appendPackage();
	appendImport( methods );
	appendClassStart();
	appendHead();
	appendSetMethod( methods );
	appendClassEnd();

	return this.sb.toString();
    }

    private void appendClassEnd() {
	sb.append( BR );
	sb.append( "}" ).append( BR );
    }

    private void appendSetMethod( List<Method> methods ) {
	String fmt = "\n" + //
	        "    public #NAME#Builder #METHOD#(#pType# #pName#) {\n" + //
	        "        this.#obj#.#METHOD#(#pName#);\n" + //
	        "        return this;\n" + //
	        "    }";
	methods.forEach( m -> {
	    String mName = m.getName();
	    Class<?> type = m.getParameters()[0].getType();
	    String pType = getSimpleName( type );

	    // 泛型处理
	    Type genericType = m.getGenericParameterTypes()[0];
	    if ( ParameterizedType.class.isAssignableFrom( genericType.getClass() ) ) {
		Type[] genericTypes = ( (ParameterizedType) genericType ).getActualTypeArguments();
		String typeName = Arrays.stream( genericTypes ) //
		        .map( this::getSimpleName ) //
		        .collect( Collectors.joining( ", " ) );
		pType = String.format( "%s<%s>", pType, typeName );
	    }

	    char top1 = Character.toLowerCase( mName.charAt( 3 ) );
	    String pName = top1 + mName.substring( 4 );
	    String method = fmt.replace( "#NAME#", clazz.getSimpleName() ) //
	            .replace( "#METHOD#", mName ) //
	            .replace( "#pType#", pType ) //
	            .replace( "#pName#", pName ) //
	            .replace( "#obj#", OBJ_SIGN ) //
	    ;
	    sb.append( method ).append( BR );
	} );
    }

    private String getSimpleName( Type type ) {
	String name = type.getTypeName();
	int dotIndex = name.lastIndexOf( "." );
	if ( dotIndex > -1 ) {
	    name = name.substring( dotIndex + 1 );
	}
	return name;
    }

    private void appendHead() {
	String fmt = "" + //
	        "    private #NAME# #obj#;\n" + //
	        "\n" + //
	        "    public static #NAME#Builder get() {\n" + //
	        "        #NAME#Builder build = new #NAME#Builder();\n" + //
	        "        build.#obj# = new #NAME#();\n" + //
	        "        return build;\n" + //
	        "    }\n" + //
	        "\n" + //
	        "    public static #NAME#Builder get(#NAME# #obj#) {\n" + //
	        "        #NAME#Builder build = new #NAME#Builder();\n" + //
	        "        build.#obj# = #obj#;\n" + //
	        "        return build;\n" + //
	        "    }\n" + //
	        "\n" + //
	        "    public #NAME# build() {\n" + //
	        "        return this.#obj#;\n" + //
	        "    }";
	String head = fmt.replace( "#NAME#", clazz.getSimpleName() ) //
	        .replace( "#obj#", OBJ_SIGN );
	sb.append( head ).append( BR );
    }

    private void appendClassStart() {
	String name = String.format( "public class %sBuilder {%n", clazz.getSimpleName() );
	sb.append( name ).append( BR );
    }

    private void appendImport( List<Method> methods ) {
	List<String> names = new ArrayList<>();

	methods.stream() //
	        .forEach( m -> {
	            names.add( m.getParameters()[0].getType().getName() );
	            Type genericType = m.getGenericParameterTypes()[0];
	            if ( ParameterizedType.class.isAssignableFrom( genericType.getClass() ) ) {
		        Type[] genericTypes = ( (ParameterizedType) genericType ).getActualTypeArguments();
		        Arrays.asList( genericTypes ).stream() //
		                .forEach( t -> {
		                    names.add( t.getTypeName() );
		                } );
	            }
	        } );

	names.stream() //
	        .distinct() //
	        .filter( name -> name.contains( "." ) && !name.startsWith( "java.lang" ) ) //
	        .sorted() //
	        .forEach( name -> {
	            String im = String.format( "import %s;", name );
	            sb.append( im ).append( BR );
	        } );

	sb.append( BR );
    }

    private void appendPackage() {
	String pck = String.format( "package %s;%n", clazz.getPackage().getName() );
	sb.append( pck ).append( BR );
    }

}
