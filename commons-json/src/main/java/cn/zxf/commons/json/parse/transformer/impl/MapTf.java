package cn.zxf.commons.json.parse.transformer.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import cn.zxf.commons.json.parse.transformer.Transformer;

public class MapTf implements Transformer<Map<?, ?>> {

    @Override
    @SuppressWarnings( { "unchecked", "rawtypes" } )
    public Map<?, ?> trans( Object obj ) {
        if ( Collection.class.isInstance( obj ) ) {
            AtomicInteger i = new AtomicInteger();
            return (Map) ( (Collection) obj ).stream().collect( Collectors.toMap( o -> i.getAndIncrement(), o -> o ) );
        } else if ( Map.class.isInstance( obj ) ) {
            return (Map) obj;
        } else {
            Map set = new HashMap<>();
            set.put( 0, obj );
            return set;
        }
    }

}
