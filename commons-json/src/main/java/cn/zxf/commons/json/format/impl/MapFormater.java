package cn.zxf.commons.json.format.impl;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import cn.zxf.commons.json.format.FormatService;
import cn.zxf.commons.json.format.Formater;

public class MapFormater implements Formater {

    @Override
    @SuppressWarnings( { "rawtypes", "unchecked" } )
    public String format( Object obj ) {
        Map map = (Map) obj;
        Set<Map.Entry> entrySet = map.entrySet();
        String content = entrySet.stream()//
                .map( e -> FormatService.format( e.getKey() ) + ": " + FormatService.format( e.getValue() ) ) //
                .collect( Collectors.joining( ", " ) );
        return "{" + content + "}";
    }

}
