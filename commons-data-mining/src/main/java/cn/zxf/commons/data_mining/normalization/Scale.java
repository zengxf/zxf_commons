package cn.zxf.commons.data_mining.normalization;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;

/**
 * 比例标准化(正规化)<br>
 * 正规化（0~1 之间）
 * 
 * <p>
 * Created by zengxf on 2017-11-15
 */
@Data
@Builder
public class Scale {

    private Map<String, Double> data;

    public Map<String, Double> normalization() {
        double sum = data.values().stream().mapToDouble( v -> v ).sum();
        return data.entrySet().stream().collect( Collectors.toMap( //
                e -> e.getKey(), e -> e.getValue() / sum, //
                ( v1, v2 ) -> null, //
                LinkedHashMap::new ) );
    }

}
