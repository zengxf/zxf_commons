package cn.zxf.commons.data_mining.normalization;

import java.util.LinkedHashMap;
import java.util.Map;

class NormDataUtil {

    public static Map<String, Double> data() {
        Map<String, Double> data = new LinkedHashMap<>();
        data.put( "Yun L", 75000D );
        data.put( "Allie C", 55000D );
        data.put( "Daniela C", 45000D );
        data.put( "Rita A", 115000D );
        data.put( "Brian A", 70000D );
        data.put( "Abdullah K", 105000D );
        data.put( "David A", 69000D );
        data.put( "Michael W", 43000D );
        return data;
    }

}
