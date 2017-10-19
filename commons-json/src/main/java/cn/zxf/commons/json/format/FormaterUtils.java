package cn.zxf.commons.json.format;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

import cn.zxf.commons.json.exceptions.GetFormaterException;

class FormaterUtils {

    static ConcurrentMap<Class<?>, Formater> map = new ConcurrentHashMap<>();

    static {
        map.put( String.class, obj -> "\"" + obj + "\"" );
        map.put( Integer.class, toStringFormater() );
        map.put( Double.class, toStringFormater() );
        map.put( Boolean.class, toStringFormater() );
    }

    static Formater defFormater( Class<?> clazz ) {
        return map.getOrDefault( clazz, Formater.NULL );
    }

    static Formater javaLangFormater( Class<?> clazz ) {
        Formater fmt = map.get( clazz );
        if ( fmt != null )
            return fmt;

        FormatTypeEnum fmtType = Stream.of( FormatTypeEnum.values() ) //
                .filter( type -> type.srcClass.isAssignableFrom( clazz ) ) //
                .findFirst() //
                .orElse( FormatTypeEnum.NULL );

        if ( fmtType.isNull() )
            return Formater.NULL;

        Class<? extends Formater> fmtClass = fmtType.formaterClass;
        try {
            fmt = fmtClass.newInstance();
            map.put( clazz, fmt );
            return fmt;
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
