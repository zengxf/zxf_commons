package cn.zxf.commons.data_mining.normalization;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;

/**
 * 修正的标准分-分值偏离均值的程度<br>
 * 标准化（均值为 0，偏离均值的程度（即标准偏差或绝对偏差）
 * 
 * <p>
 * Created by zengxf on 2017-11-13
 */
@Data
@Builder
public class ModifiedStandardScore {

    private Map<String, Double> data;

    public Map<String, Double> normalization() {
        double[] arr = data.values().stream().mapToDouble( v -> v ).sorted().toArray();
        int midIndex = arr.length / 2;
        double median = arr.length % 2 == 0 ? ( arr[midIndex - 1] + arr[midIndex] ) / 2 : arr[midIndex]; // 中位数
        double difSum = data.values().stream().mapToDouble( v -> Math.abs( v - median ) ).sum();
        double asd = difSum / arr.length; // 绝对偏差 absolute standard deviation
        return data.entrySet().stream().collect( Collectors.toMap( //
                e -> e.getKey(), e -> ( e.getValue() - median ) / asd, //
                ( v1, v2 ) -> null, //
                LinkedHashMap::new ) );
    }

}
