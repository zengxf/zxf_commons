package cn.zxf.commons.json.parse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import cn.zxf.commons.json.commons.ClassFieldUtils;
import cn.zxf.commons.json.exceptions.TransformingException;
import cn.zxf.commons.json.parse.transformer.TransformerFactor;
import cn.zxf.commons.json.parse.transformer.Transformer;

class TransformUtil {

    @SuppressWarnings( "unchecked" )
    static < T > T trans( Object obj, Class<T> clazz ) {
        if ( obj == null )
            return null;
        if ( obj.getClass() == clazz )
            return (T) obj;

        Transformer<?> tf = TransformerFactor.get( clazz );
        if ( tf != null )
            return (T) tf.trans( obj );

        if ( clazz.getName().startsWith( "java." ) )
            throw new TransformingException( "还不支持 java 类转换，class: " + clazz.getName() );

        return toTarget( obj, clazz );
    }

    @SuppressWarnings( { "rawtypes", "unchecked" } )
    static < T > T toTarget( Object obj, Class<T> clazz ) {
        if ( !Map.class.isInstance( obj ) )
            throw new TransformingException( "解析出来的对象无法转换到目标类，class: " + clazz.getName() + "，Object: " + obj );
        Map map = (Map) obj;
        Constructor c = Stream.of( clazz.getConstructors() ) //
                .sorted( Comparator.comparing( Constructor::getParameterCount ) ) //
                .findFirst() //
                .orElseThrow( () -> new TransformingException( "目标类找不到合适的构造器，class: " + clazz.getName() ) );
        try {
            c.setAccessible( true );
            Object[] params = new Object[c.getParameterCount()];
            Object target = c.newInstance( params );
            Set<Field> fields = ClassFieldUtils.getFields( clazz );
            fields.forEach( field -> {
                field.setAccessible( true );
                Object v = map.get( field.getName() );
                fill( target, field, v );
            } );
            return (T) target;
        } catch ( InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e ) {
            throw new TransformingException( "无法创建目标类的实例，class: " + clazz.getName() + "，构造器：" + c );
        }
    }

    static void fill( Object target, Field field, Object value ) {
        if ( value == null )
            return;
        Object obj = trans( value, field.getType() );
        try {
            field.set( target, obj );
        } catch ( IllegalArgumentException | IllegalAccessException e ) {
            throw new TransformingException( "转换时无法赋值，field: " + field + "，obj：" + obj );
        }
    }

}
