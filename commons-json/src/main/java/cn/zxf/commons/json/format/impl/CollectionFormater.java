package cn.zxf.commons.json.format.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import cn.zxf.commons.json.format.FormatService;
import cn.zxf.commons.json.format.Formater;

public class CollectionFormater implements Formater {

    @Override
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    public String format( Object obj ) {
        Collection<Object> coll = (Collection) obj;
        String content = coll.stream().map( FormatService::format ).collect( Collectors.joining( ", " ) );
        return "[" + content + "]";
    }

}
