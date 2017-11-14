package cn.zxf.commons.data_mining.classifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import cn.zxf.commons.data_mining.classifier.ConfusionMatricesTenFoldCross.MatricesItem;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * kappa 指标
 * 
 * <pre>
 *   <= 0   差
 *  1 - 20  稍好
 * 21 - 40  一般
 * 41 - 60  符合期望
 * 61 - 80  本质上超越
 * 81 - 100 几近完美
 * </pre>
 * 
 * <p>
 * Created by zengxf on 2017-11-14
 */
@Data
@Builder
@Slf4j
public class KappaStatistic {

    private Map<String, MatricesItem> data;

    public int calculate() {
        Set<String> set = new TreeSet<>();
        data.values().forEach( item -> {
            set.add( item.getActual() );
            set.addAll( item.getErrorMap().keySet() );
        } );

        Map<String, List<Integer>> rowMap = new TreeMap<>(); // 横行
        Map<String, List<Integer>> colMap = new TreeMap<>(); // 纵列

        AtomicInteger correct = new AtomicInteger();
        set.forEach( k -> { // 行
            List<Integer> rows = rowMap.computeIfAbsent( k, rk -> new ArrayList<>() );
            MatricesItem mi = data.getOrDefault( k, new MatricesItem() );
            System.out.print( k );
            set.forEach( ki -> { // 列
                List<Integer> cols = colMap.computeIfAbsent( ki, rk -> new ArrayList<>() );
                int count;
                if ( ki.equals( mi.getActual() ) ) { // 相当于 k
                    count = mi.getCorrectCount();
                    correct.addAndGet( count );
                } else {
                    count = mi.getErrorMap().getOrDefault( ki, 0 );
                }
                rows.add( count );
                cols.add( count );
            } );
        } );

        log.debug( "row-map: {}", rowMap );
        log.debug( "col-map: {}", colMap );

        int sum = rowMap.values().stream().flatMapToInt( list -> list.stream().mapToInt( v -> v.intValue() ) ).sum();
        Map<String, Integer> rateMap = colMap.entrySet().stream().collect( Collectors.toMap( e -> e.getKey(), e -> this.rate( e.getValue(), sum ) ) );

        AtomicInteger correct2 = new AtomicInteger();
        set.forEach( k -> {
            int rowSum = rowMap.getOrDefault( k, Collections.emptyList() ).stream().mapToInt( v -> v.intValue() ).sum();
            correct2.addAndGet( rowSum * rateMap.getOrDefault( k, 0 ) / 1000 );
        } );

        int pc = correct.get() * 1000 / sum;
        int pr = correct2.get() * 1000 / sum;
        int k = ( pc - pr ) * 100 / ( 1000 - pr );

        log.debug( "pc: {}, pr: {}", pc, pr );

        return k;
    }

    Integer rate( List<Integer> list, int total ) {
        int sum = list.stream().mapToInt( v -> v ).sum();
        return sum * 1000 / total;
    }

}
