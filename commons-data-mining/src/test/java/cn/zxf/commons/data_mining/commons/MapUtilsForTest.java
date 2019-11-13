package cn.zxf.commons.data_mining.commons;

import java.io.IOException;
import java.util.Map;

import cn.zxf.commons.data_mining.numbers.VectorVo;
import cn.zxf.commons.json.Json;

public class MapUtilsForTest {

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

}
