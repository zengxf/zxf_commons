package cn.zxf.commons.data_mining.normalization;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;

/**
 * 粗略的标准化——简单
 * 
 * <p>
 * Created by zengxf on 2017-11-13
 */
@Data
@Builder
public class Rough {

    private Map<String, Double> data;

    public Map<String, Double> normalization() {
        DoubleSummaryStatistics stats = data.values().stream().mapToDouble( v -> v ).summaryStatistics();
        double min = stats.getMin();
        double max = stats.getMax();
        double interval = max - min;
        return data.entrySet().stream().collect( Collectors.toMap( //
                e -> e.getKey(), e -> ( e.getValue() - min ) / interval, //
                ( v1, v2 ) -> null, //
                LinkedHashMap::new ) );
    }

}
