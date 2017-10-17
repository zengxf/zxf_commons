package cn.zxf.commons.json.format;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import cn.zxf.commons.json.exceptions.GetFormaterException;

class FormaterUtils {

    static Map<Class<?>, Formater> map = new HashMap<>();

    static {
        map.put( String.class, obj -> "\"" + obj + "\"" );
        map.put( int.class, toStringFormater() );
        map.put( Integer.class, toStringFormater() );
        map.put( boolean.class, toStringFormater() );
        map.put( Boolean.class, toStringFormater() );
    }

    static Formater defFormater( Class<?> clazz ) {
        return map.getOrDefault( clazz, Formater.NULL );
    }

    static Formater javaLangFormater( Class<?> clazz ) {
        FormatTypeEnum fmtType = Stream.of( FormatTypeEnum.values() ) //
                .filter( type -> type.srcClass.isAssignableFrom( clazz ) ) //
                .findFirst() //
                .orElse( FormatTypeEnum.NULL );

        if ( fmtType.isNull() )
            return Formater.NULL;

        Class<? extends Formater> fmtClass = fmtType.formaterClass;
        try {
            return fmtClass.newInstance();
        } catch ( InstantiationException | IllegalAccessException e ) {
            throw new GetFormaterException( clazz + " 的格式器[" + fmtClass + "]实例化出错", e );
        }
    }

    static boolean isNonNull( Formater formater ) {
        return formater != null && formater != Formater.NULL;
    }

    private static Formater toStringFormater() {
        return obj -> obj + "";
    }

}
