package cn.zxf.commons.data_mining.numbers.distance;

/**
 * 欧几里得距离<br>
 * 如果数据比较“密集”，变量之间基本都存在公有值，且这些距离数据是非常重要的，那就使用欧几里得或曼哈顿距离。
 * 
 * <p>
 * Created by zengxf on 2017-11-09
 */
public class Euclidean extends AbstractDistanceCalculator {

    @Override
    protected double calculate( double[] xArr, double[] yArr ) {
        double sum = 0;
        for ( int i = 0; i < xArr.length; i++ ) {
            sum += Math.pow( xArr[i] - yArr[i], 2 ); // pow(x, 2): x的2次方
        }
        return Math.sqrt( sum ); // sqrt 平方根
    }

}
