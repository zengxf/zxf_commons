package cn.zxf.commons.data_mining.commons;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.zxf.commons.data_mining.numbers.VectorVo;
import cn.zxf.commons.json.Json;

public class MapUtils {

    @SuppressWarnings( "rawtypes" )
    public static Map getMap( String jsonFile ) {
        try {
            Object json = Json.parseJson( FileUtils.path( "/ch2/test.json" ) );
            Map map = (Map) json;
            return map;
        } catch ( IOException e ) {
            throw new RuntimeException( "读取出错：" + jsonFile, e );
        }
    }

    @SuppressWarnings( "rawtypes" )
    public static Map getObj( Map map, String key ) {
        return (Map) map.get( key );
    }

    @SuppressWarnings( "rawtypes" )
    public static void fillVector( VectorVo v1, VectorVo v2, Map v1Map, Map v2Map ) {
        for ( Object key : v1Map.keySet() ) {
            if ( v2Map.containsKey( key ) ) {
                v1.add( (double) v1Map.get( key ) );
                v2.add( (double) v2Map.get( key ) );
            }
        }
    }

    public static Map<String, Double> sort( Map<String, Double> res ) {
        Map<String, Double> res1 = new LinkedHashMap<>();
        res.entrySet().stream() //
                .sorted( Comparator.comparing( Map.Entry::getValue ) ) //
                .forEach( e -> res1.put( e.getKey(), e.getValue() ) );
        return res1;
    }

    public static Map<String, Double> sortDesc( Map<String, Double> res ) {
        Map<String, Double> res1 = new LinkedHashMap<>();
        res.entrySet().stream() //
                .sorted( ( e1, e2 ) -> Double.compare( e2.getValue(), e1.getValue() ) ) //
                .forEach( e -> res1.put( e.getKey(), e.getValue() ) );
        return res1;
    }

}
