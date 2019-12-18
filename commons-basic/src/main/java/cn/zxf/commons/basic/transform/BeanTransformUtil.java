package cn.zxf.commons.basic.transform;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * 两个对象相互转换的帮助类
 *
 * @author zengxf
 */
@Slf4j
public class BeanTransformUtil {

    /**
     * 转换并返回 <br>
     * 注：toClass 一定要有无参构造器
     */
    public static < T > T transformAndGet( Object from, Class<T> toClass ) throws Exception {
        T to = toClass.getConstructor()
                .newInstance();
        transform( from, to, false );
        return to;
    }

    /**
     * 转换下值(默认简单转换，即限制性的返回)
     */
    public static void transform( Object from, Object to ) {
        transform( from, to, true );
    }

    /**
     * 转换下值
     */
    public static void transform( Object from, Object to, boolean isSimple ) {

        Map<String, Field> fromFields = getFieldAtName( from.getClass() );
        Map<String, Field> toFields = getFieldAtName( to.getClass() );

        fromFields.forEach( ( fName, fromField ) -> {

            if ( !toFields.containsKey( fName ) ) {
                return;
            }

            Field toField = toFields.get( fName );

            if ( fromField.getGenericType() != toField.getGenericType() ) {
                return;
            }

            Exclude exclude = toField.getAnnotation( Exclude.class );
            if ( isSimple && exclude != null ) {
                return;
            }

            Limit limit = toField.getAnnotation( Limit.class );

            try {
                boolean fromAcc = fromField.canAccess( from );
                boolean toAcc = toField.canAccess( to );

                if ( !fromAcc ) {
                    fromField.setAccessible( true );
                }
                if ( !toAcc ) {
                    toField.setAccessible( true );
                }

                Object fromObj = fromField.get( from );
                if ( isSimple //
                        && fromField.getGenericType() == String.class //
                        && fromObj != null //
                        && limit != null ) {
                    int maxLength = limit.maxLength();
                    String fromStr = fromObj.toString();
                    fromObj = fromStr.length() > maxLength ? fromStr.substring( 0, maxLength ) : fromStr;
                }
                toField.set( to, fromObj );

                if ( !fromAcc ) {
                    fromField.setAccessible( false );
                }
                if ( !toAcc ) {
                    toField.setAccessible( false );
                }

            } catch ( Exception e ) {
                log.error( "对象转换时出错！", e );
            }

        } );

    }

    private static Map<String, Field> getFieldAtName( Class<?> clazz ) {
        Map<String, Field> map = new HashMap<>();

        do {

            Field[] fs = clazz.getDeclaredFields();

            for ( Field field : fs ) {
                map.put( field.getName(), field );
            }

            clazz = clazz.getSuperclass();

        } while ( clazz != Object.class );

        return map;
    }

}
