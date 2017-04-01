package cn.zxf.commons.basic.compare_field;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 比较对象各字段更改情况
 * 
 * @author zengxf
 */
@Slf4j
public class CompareFieldChangeUtil {

    private static final String ORIGIN_TYPES = ",byte,short,int,long,float,double,boolean,char,";

    /**
     * 比较两对象的字段更改情况 <br>
     * 注：暂没实现梯归父类字段的比较
     * 
     * @param oldObj
     * @param newObj
     * @return
     */
    public static List<CompareDifferentPiece> compare( Object oldObj, Object newObj ) {
	if ( oldObj == null || newObj == null )
	    return Collections.emptyList();

	if ( oldObj.getClass() != newObj.getClass() )
	    throw new RuntimeException( "两对象的类型必须一致" );

	return new Compare( oldObj, newObj ).compare();
    }

    @Data
    @AllArgsConstructor
    private static class Compare {

	private final List<CompareDifferentPiece> list = new ArrayList<>();

	private Object				  oldObj;
	private Object				  newObj;

	private List<CompareDifferentPiece> compare() {
	    Field[] fields = oldObj.getClass().getDeclaredFields();
	    Arrays.stream( fields ) //
	            .forEach( field -> {
		        compareField( oldObj, newObj, field );
	            } );
	    return this.list;
	}

	private void compareField( Object oldObj, Object newObj, Field field ) {
	    compareField( oldObj, newObj, field, "" );
	}

	private void compareField( Object oldObj, Object newObj, Field field, String superFieldName ) {
	    boolean sign = field.isAccessible();
	    field.setAccessible( true );

	    Class<?> fieldType = field.getType();
	    String fieldName = field.getName();
	    boolean simple = isSimpleType( fieldType );

	    try {
		Object oldV = field.get( oldObj );
		Object newV = field.get( newObj );
		if ( ( oldV == null && newV != null ) || ( oldV != null && !oldV.equals( newV ) ) ) {
		    ActionDetailType type = ActionDetailType.ACTION_UP;
		    if ( oldV == null )
			type = ActionDetailType.ACTION_SET;
		    if ( newV == null )
			type = ActionDetailType.ACTION_CLEAR;
		    if ( oldV == null || newV == null || simple ) {
			String recordField = "".equals( superFieldName ) ? fieldName : superFieldName + "." + fieldName;
			CompareDifferentPiece piece = new CompareDifferentPiece( recordField, newV, type.code );
			this.list.add( piece );
		    } else {
			Field[] fields = fieldType.getDeclaredFields();
			Arrays.stream( fields ) //
			        .forEach( childField -> {
			            compareField( oldV, newV, childField, fieldName );
			        } );
		    }
		}
	    } catch ( Exception e ) {
		log.error( "比较字段时出错！", e );
	    }

	    field.setAccessible( sign );
	}

	private static boolean isSimpleType( Class<?> fieldType ) {
	    String typeName = fieldType.getName();
	    if ( typeName.startsWith( "java." ) // JDK 里面的 java 包
	            || typeName.startsWith( "[" ) // 或者是数组
	    ) {
		return true;
	    }
	    return ORIGIN_TYPES.contains( typeName );
	}

    }

}
