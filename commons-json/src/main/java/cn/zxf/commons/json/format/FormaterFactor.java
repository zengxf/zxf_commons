package cn.zxf.commons.json.format;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.zxf.commons.json.format.impl.CollectionFormater;
import cn.zxf.commons.json.format.impl.MapFormater;

class FormaterFactor {

    static Map<Class<?>, Formater> primitiveMap = new HashMap<>();
    static Map<Class<?>, Formater> objMap       = new HashMap<>();

    static {
        primitiveMap.put( String.class, obj -> "\"" + obj + "\"" );
        primitiveMap.put( Integer.class, toStringFormater() );
        primitiveMap.put( Double.class, toStringFormater() );
        primitiveMap.put( Boolean.class, toStringFormater() );

        objMap.put( Map.class, new MapFormater() );
        objMap.put( Collection.class, new CollectionFormater() );
    }

    static Formater defFormater( Class<?> clazz ) {
        Formater formater = primitiveMap.getOrDefault( clazz, Formater.NULL );
        if ( isNonNull( formater ) )
            return formater;
        formater = objMap.entrySet().stream() //
                .filter( e -> e.getKey().isAssignableFrom( clazz ) ) //
                .findFirst() //
                .map( Entry::getValue ) //
                .orElse( Formater.NULL );
        return formater;
    }

    static boolean isNonNull( Formater formater ) {
        return formater != null && formater != Formater.NULL;
    }

    private static Formater toStringFormater() {
        return obj -> obj + "";
    }

}
