package cn.zxf.commons.data_mining.commons;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtils {

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
