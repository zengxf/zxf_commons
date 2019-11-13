package cn.zxf.commons.data_mining.vo;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import cn.zxf.commons.data_mining.commons.Constant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdjustUser implements Constant {

    public Map<String, Double> adjustMap;
    private User               user;
    private double             min, max, interval;

    public AdjustUser adjust() {
        DoubleSummaryStatistics stats = user.scoreMap.values().stream() //
                .filter( this::neN ) //
                .mapToDouble( v -> v ).summaryStatistics();
        this.min = stats.getMin();
        this.max = stats.getMax();
        this.interval = this.max - this.min;
        adjustMap = user.scoreMap.entrySet().stream() //
                .collect( Collectors.toMap( e -> e.getKey(), e -> this.compute( e.getValue() ), ( v1, v2 ) -> null, LinkedHashMap::new ) );
        return this;
    }

    private double compute( double v ) {
        if ( this.isN( v ) )
            return v;
        double numerator = 2 * ( v - min ) - interval;
        return numerator / interval;
    }

    public double restore( double v ) {
        if ( this.isN( v ) )
            return v;
        return 0.5 * ( v + 1 ) * interval + min;
    }

}
