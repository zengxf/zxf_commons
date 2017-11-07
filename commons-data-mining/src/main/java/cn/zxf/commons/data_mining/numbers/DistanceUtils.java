package cn.zxf.commons.data_mining.numbers;

import lombok.RequiredArgsConstructor;

/**
 * 距离计算帮助类
 * 
 * <p>
 * Created by zengxf on 2017-11-06
 */
public class DistanceUtils {

    /**
     * 曼哈顿距离
     */
    public static double manhattan( VectorVo v1, VectorVo v2 ) {
        return new Manhattan( v1, v2 ).calculate();
    }

    /**
     * 欧几里得距离
     */
    public static double euclid( VectorVo v1, VectorVo v2 ) {
        return new Euclid( v1, v2 ).calculate();
    }

    /**
     * 闵可夫斯基
     */
    public static double minkowski( VectorVo v1, VectorVo v2 ) {
        return new Minkowski( v1, v2 ).calculate();
    }

    /**
     * 曼哈顿距离
     * 
     * <pre>
     * A=(x1,y1)
     * B=(x2,y2)
     * A-B=|x1-x2|+|y1-y2|
     * </pre>
     */
    @RequiredArgsConstructor
    static class Manhattan {
        final VectorVo v1;
        final VectorVo v2;

        double calculate() {
            NumberAsserts.assertVectorLen( v1, v2 );
            double sum = 0;
            for ( int i = 0; i < v1.getLen(); i++ ) {
                sum += Math.abs( v1.getValues()[i] - v2.getValues()[i] );
            }
            return sum;
        }

    }

    /**
     * 欧几里得距离
     */
    @RequiredArgsConstructor
    static class Euclid {
        final VectorVo v1;
        final VectorVo v2;

        double calculate() {
            NumberAsserts.assertVectorLen( v1, v2 );
            double sum = 0;
            for ( int i = 0; i < v1.getLen(); i++ ) {
                sum += Math.pow( v1.getValues()[i] - v2.getValues()[i], 2 ); // pow(x, 2): x的2次方
            }
            return Math.sqrt( sum ); // sqrt 平方根
        }

    }

    /**
     * 闵可夫斯基
     */
    @RequiredArgsConstructor
    static class Minkowski {
        final VectorVo v1;
        final VectorVo v2;

        double calculate() {
            NumberAsserts.assertVectorLen( v1, v2 );
            double sum = 0;
            int len = v1.getLen();
            for ( int i = 0; i < len; i++ ) {
                double abs = Math.abs( v1.getValues()[i] - v2.getValues()[i] );
                sum += Math.pow( abs, len ); // pow(x, n): x的n次方
            }
            return Math.pow( sum, 1D / len ); // pow(x, 1D / n): x的n次方根
        }

    }

}
