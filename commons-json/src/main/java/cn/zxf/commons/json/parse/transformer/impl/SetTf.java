package cn.zxf.commons.json.parse.transformer.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.zxf.commons.json.parse.transformer.Transformer;

public class SetTf implements Transformer<Set<?>> {

    @Override
    @SuppressWarnings( { "unchecked", "rawtypes" } )
    public Set trans( Object obj ) {
        if ( Collection.class.isInstance( obj ) ) {
            return new HashSet<>( (Collection) obj );
        } else if ( Map.class.isInstance( obj ) ) {
            return new HashSet<>( ( (Map) obj ).values() );
        } else {
            Set set = new HashSet<>();
            set.add( obj );
            return set;
        }
    }

}
