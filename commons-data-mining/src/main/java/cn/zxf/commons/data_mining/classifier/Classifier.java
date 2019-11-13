package cn.zxf.commons.data_mining.classifier;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import cn.zxf.commons.data_mining.commons.MapUtils;
import cn.zxf.commons.data_mining.numbers.NumberAsserts;
import cn.zxf.commons.data_mining.numbers.VectorVo;
import cn.zxf.commons.data_mining.numbers.distance.DistanceCalculator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 分类器<br>
 * 距离越短才越近
 * 
 * <p>
 * Created by zengxf on 2017-11-13
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Classifier implements IClassifier {

    private DistanceCalculator disCalc;
    private List<ClassifyItem> data;
    private VectorVo           target;
    private boolean            isNorm;    // true 表示需要标准化
    // ------------
    private int                k;         // def 1
    // ------------
    private double[][]         normArr;   // median, asd
    private List<ClassifyItem> normData;
    private VectorVo           normTarget;

    public String classify() {
        if ( !isNorm ) {
            this.normData = data;
            this.normTarget = target;
        } else if ( normArr == null )
            this.normalizeVector();
        List<ClassifyItem> nnList = this.nearestNeighbor();
        if ( k <= 1 )
            return nnList.get( 0 ).getClassify();
        Map<String, Double> nnMap = nnList.stream() //
                .limit( k ) //
                .collect( Collectors.toMap( ci -> ci.getClassify(), ci -> 1D, ( v1, v2 ) -> v1 + v2, HashMap::new ) );
        return MapUtils.sortDesc( nnMap ).keySet().iterator().next();
    }

    public Classifier target( VectorVo target ) {
        this.target = target;
        if ( !isNorm )
            this.normTarget = target;
        else if ( normArr != null )
            this.normalizeTarget();
        return this;
    }

    /**
     * 距离越短才越近
     */
    private List<ClassifyItem> nearestNeighbor() {
        return normData.stream().sorted( ( i1, i2 ) -> {
            double d1 = disCalc.calculate( this.normTarget, i1.getVector() );
            double d2 = disCalc.calculate( this.normTarget, i2.getVector() );
            return Double.compare( d1, d2 ); // 距离越短才越近
        } ).collect( Collectors.toList() );
    }

    private void normalizeVector() {
        VectorVo first = data.get( 0 ).getVector();
        int len = first.getLen();
        data.stream().skip( 1 ).forEach( item -> NumberAsserts.assertVectorLen( first, item.getVector() ) );

        this.normArr = new double[len][2];
        for ( int i = 0; i < len; i++ ) {
            int j = i;
            double[] arr = data.stream().mapToDouble( item -> item.getVector().getValues()[j] ).sorted().toArray();
            int midIndex = arr.length / 2;
            double median = arr.length % 2 == 0 ? ( arr[midIndex - 1] + arr[midIndex] ) / 2 : arr[midIndex]; // 中位数
            double difSum = DoubleStream.of( arr ).map( v -> Math.abs( v - median ) ).sum();
            double asd = difSum / arr.length; // 绝对偏差 absolute standard deviation
            this.normArr[i] = new double[] { median, asd };
        }
        log.debug( "norm-arr: {}", this.normArrString() );

        this.normData = data.stream().map( item -> {
            ClassifyItem normItem = new ClassifyItem();
            VectorVo normVo = new VectorVo();
            normItem.setClassify( item.getClassify() );
            normItem.setCommend( item.getCommend() );
            normItem.setVector( normVo );
            double[] arr = item.getVector().getValues();
            for ( int i = 0; i < arr.length; i++ ) {
                double v = ( arr[i] - normArr[i][0] ) / normArr[i][1];
                normVo.add( v );
            }
            return normItem;
        } ).collect( Collectors.toList() );

        this.normalizeTarget();
    }

    void normalizeTarget() {
        if ( this.target == null )
            return;
        this.normTarget = new VectorVo();
        double[] arr = target.getValues();
        for ( int i = 0; i < arr.length; i++ ) {
            double v = ( arr[i] - normArr[i][0] ) / normArr[i][1];
            this.normTarget.add( v );
        }
        log.debug( "norm-target: {}", this.normTarget );
    }

    private String normArrString() {
        if ( normArr == null )
            return "null";
        return Stream.of( normArr ).map( Arrays::toString ).collect( Collectors.joining( ", ", "[", "]" ) );
    }

}
