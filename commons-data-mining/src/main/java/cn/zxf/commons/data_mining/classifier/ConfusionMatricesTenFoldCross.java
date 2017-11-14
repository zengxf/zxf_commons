package cn.zxf.commons.data_mining.classifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cn.zxf.commons.data_mining.numbers.distance.Euclidean;
import lombok.Builder;
import lombok.Data;

/**
 * 混淆矩阵与十折交叉验证 <br>
 * 十折交叉验证时间少些
 * 
 * <p>
 * Created by zengxf on 2017-11-14
 */
@Data
@Builder
public class ConfusionMatricesTenFoldCross {

    private List<ClassifyItem>              data;
    private final int                       n      = 10;
    // ----------------------
    private final Map<String, MatricesItem> result = new HashMap<>();

    public ConfusionMatricesTenFoldCross calculate() {
        for ( int ni = 0; ni < n; ni++ ) {
            List<ClassifyItem> training = new ArrayList<>();
            List<ClassifyItem> test = new ArrayList<>();
            for ( int i = 0; i < data.size(); i++ ) {
                if ( i % n == ni )
                    test.add( data.get( i ) );
                else
                    training.add( data.get( i ) );
            }
            Classifier classifier = Classifier.builder() //
                    .isNorm( true ) //
                    .data( training ) //
                    .disCalc( new Euclidean() ) //
                    .build();
            test.forEach( item -> {
                String forecast = classifier.target( item.getVector() ).classify();
                this.log( item.getClassify(), forecast );
            } );
        }
        return this;
    }

    private void log( String actual, String forecast ) {
        MatricesItem mi = result.computeIfAbsent( actual, k -> {
            MatricesItem temp = new MatricesItem();
            temp.setActual( actual );
            return temp;
        } );
        if ( Objects.equals( actual, forecast ) ) {
            mi.incCorrectCount();
        } else {
            mi.incErrorCount( forecast ); // 预测出错
        }
    }

    @Data
    public static class MatricesItem {
        String               actual;
        int                  correctCount;
        Map<String, Integer> errorMap = new HashMap<>();

        void incCorrectCount() {
            correctCount++;
        }

        void incErrorCount( String errorKey ) {
            int v = errorMap.getOrDefault( errorKey, 0 );
            v++;
            errorMap.put( errorKey, v );
        }
    }

}
