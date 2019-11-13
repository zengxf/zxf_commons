package cn.zxf.commons.data_mining.numbers.distance;

/**
 * 曼哈顿距离<br>
 * 如果数据比较“密集”，变量之间基本都存在公有值，且这些距离数据是非常重要的，那就使用欧几里得或曼哈顿距离。
 * 
 * <pre>
 * A=(x1,y1)
 * B=(x2,y2)
 * A-B=|x1-x2|+|y1-y2|
 * </pre>
 * 
 * <p>
 * Created by zengxf on 2017-11-09
 */
public class Manhattan extends AbstractDistanceCalculator {

    @Override
    protected double calculate( double[] xArr, double[] yArr ) {
        double sum = 0;
        for ( int i = 0; i < xArr.length; i++ ) {
            sum += Math.abs( xArr[i] - yArr[i] );
        }
        return sum;
    }

}
