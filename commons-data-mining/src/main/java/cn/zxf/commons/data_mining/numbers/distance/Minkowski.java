package cn.zxf.commons.data_mining.numbers.distance;

/**
 * 闵可夫斯基
 * 
 * <p>
 * Created by zengxf on 2017-11-09
 */
public class Minkowski extends AbstractDistanceCalculator {

    @Override
    protected double calculate( double[] xArr, double[] yArr ) {
        double sum = 0;
        int len = xArr.length;
        for ( int i = 0; i < len; i++ ) {
            double abs = Math.abs( xArr[i] - yArr[i] );
            sum += Math.pow( abs, len ); // pow(x, n): x的n次方
        }
        return Math.pow( sum, 1D / len ); // pow(x, 1D / n): x的n次方根
    }

}
