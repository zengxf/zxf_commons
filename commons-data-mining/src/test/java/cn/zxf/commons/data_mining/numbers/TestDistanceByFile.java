package cn.zxf.commons.data_mining.numbers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.zxf.commons.data_mining.commons.FileUtils;
import cn.zxf.commons.data_mining.commons.MapUtils;
import cn.zxf.commons.data_mining.commons.MapUtilsForTest;
import cn.zxf.commons.data_mining.numbers.distance.Manhattan;
import cn.zxf.commons.data_mining.numbers.distance.Pearson;
import cn.zxf.commons.data_mining.numbers.distance.PearsonSimilar;
import cn.zxf.commons.json.Json;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDistanceByFile {

    @SuppressWarnings( "rawtypes" )
    @Test
    public void test_manhattan() throws IOException {
        Map map = MapUtilsForTest.getMap( "/ch2/test.json" );
        Map Hailey = (Map) map.get( "Hailey" );
        Map Veronica = (Map) map.get( "Veronica" );
        Map Jordyn = (Map) map.get( "Jordyn" );
        log.info( "Hailey: {}", Hailey );
        log.info( "Veronica: {}", Veronica );
        log.info( "Jordyn: {}", Jordyn );
        this.manhattan1( Hailey, Veronica );
        this.manhattan1( Hailey, Jordyn );

        log.info( "computeNearest - Hailey: {}", computeNearest( "Hailey", map ) );
        log.info( "computeNearest - Angelica: {}", computeNearest( "Angelica", map ) );
        log.info( "recommendNearest - Hailey: {}", recommendNearest( "Hailey", map ) );
        log.info( "recommendNearest - Chan: {}", recommendNearest( "Chan", map ) );
        log.info( "recommendNearest - Sam: {}", recommendNearest( "Sam", map ) );
        log.info( "recommendNearest - Angelica: {}", recommendNearest( "Angelica", map ) );
    }

    @SuppressWarnings( "rawtypes" )
    @Test
    public void test_pearson() throws IOException {
        Object json = Json.parseJson( FileUtils.path( "/ch2/test.json" ) );
        Map map = (Map) json;
        test_pearson_1( map, "Angelica", "Bill" );
        test_pearson_1( map, "Angelica", "Hailey" );
        test_pearson_1( map, "Angelica", "Jordyn" );
    }

    @SuppressWarnings( "rawtypes" )
    void test_pearson_1( Map map, String name1, String name2 ) {
        Map user1 = (Map) map.get( name1 );
        Map user2 = (Map) map.get( name2 );
        log.info( "{}: {}", name1, user1 );
        log.info( "{}: {}", name2, user2 );
        log.info( "{}-{}: {}", name1, name2, computePearson( user1, user2 ) );
        log.info( "{}-{}: {}", name1, name2, computePearsonSimilar( user1, user2 ) );
    }

    @SuppressWarnings( "rawtypes" )
    double computePearson( Map v1Map, Map v2Map ) {
        VectorVo v1 = new VectorVo();
        VectorVo v2 = new VectorVo();
        MapUtilsForTest.fillVector( v1, v2, v1Map, v2Map );
        return new Pearson().calculate( v1, v2 );
    }

    @SuppressWarnings( "rawtypes" )
    double computePearsonSimilar( Map v1Map, Map v2Map ) {
        VectorVo v1 = new VectorVo();
        VectorVo v2 = new VectorVo();
        MapUtilsForTest.fillVector( v1, v2, v1Map, v2Map );
        return new PearsonSimilar().calculate( v1, v2 );
    }

    @SuppressWarnings( "rawtypes" )
    void manhattan1( Map v1Map, Map v2Map ) {
        log.info( "V1-V2: {}", computeManhattan( v1Map, v2Map ) );
    }

    @SuppressWarnings( "rawtypes" )
    double computeManhattan( Map v1Map, Map v2Map ) {
        VectorVo v1 = new VectorVo();
        VectorVo v2 = new VectorVo();
        MapUtilsForTest.fillVector( v1, v2, v1Map, v2Map );
        return new Manhattan().calculate( v1, v2 );
    }

    @SuppressWarnings( "rawtypes" )
    Map<String, Double> computeNearest( String name, Map allMap ) {
        Map<String, Double> res = new HashMap<>();
        Map target = (Map) allMap.get( name );
        for ( Object key : allMap.keySet() ) {
            if ( !name.equals( key ) ) {
                Map v2Map = (Map) allMap.get( key );
                res.put( (String) key, computeManhattan( target, v2Map ) );
            }
        }
        return MapUtils.sort( res );
    }

    @SuppressWarnings( "rawtypes" )
    Map<String, Double> recommendNearest( String name, Map allMap ) {
        Map<String, Double> res = new HashMap<>();
        Map<String, Double> nearest = computeNearest( name, allMap );
        String key = nearest.entrySet().iterator().next().getKey();
        Map target = (Map) allMap.get( name );
        Map value = (Map) allMap.get( key );
        for ( Object k : value.keySet() ) {
            if ( !target.containsKey( k ) ) {
                res.put( (String) k, (Double) value.get( k ) );
            }
        }
        return MapUtils.sortDesc( res );
    }

}
