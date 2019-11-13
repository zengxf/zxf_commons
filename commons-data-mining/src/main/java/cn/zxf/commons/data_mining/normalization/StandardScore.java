package cn.zxf.commons.data_mining.normalization;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;

/**
 * 标准分-分值偏离均值的程度-但会受均值影响 <br>
 * 标准化（均值为 0，偏离均值的程度（即标准偏差或绝对偏差））
 * <p>
 * Created by zengxf on 2017-11-13
 */
@Data
@Builder
public class StandardScore {

    private Map<String, Double> data;

    public Map<String, Double> normalization() {
        DoubleSummaryStatistics stats = data.values().stream().mapToDouble( v -> v ).summaryStatistics();
        double avg = stats.getAverage();
        double pow2Sum = data.values().stream().mapToDouble( v -> Math.pow( v - avg, 2 ) ).sum();
        double sd = Math.pow( pow2Sum / data.size(), 1D / 2 ); // 标准差 standard deviation
        return data.entrySet().stream().collect( Collectors.toMap( //
                e -> e.getKey(), e -> ( e.getValue() - avg ) / sd, //
                ( v1, v2 ) -> null, //
                LinkedHashMap::new ) );
    }

}
