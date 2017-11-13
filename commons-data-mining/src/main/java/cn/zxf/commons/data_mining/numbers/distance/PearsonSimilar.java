package cn.zxf.commons.data_mining.numbers.distance;

import cn.zxf.commons.data_mining.numbers.utils.ArrayUtils;

/**
 * 相似皮尔逊相关系数计算<br>
 * 如果数据存在“分数膨胀”问题，就使用皮尔逊相关系数。
 * 
 * <p>
 * Created by zengxf on 2017-11-09
 */
public class PearsonSimilar extends AbstractDistanceCalculator {

    @Override
    protected double calculate( double[] xArr, double[] yArr ) {
        int n = xArr.length;
        double sumX = ArrayUtils.sum( xArr );
        double sumY = ArrayUtils.sum( yArr );

        double sumXYMultiply = 0;
        double sumXPom = 0, sumYPom = 0;
        for ( int i = 0; i < n; i++ ) {
            double x = xArr[i];
            double y = yArr[i];
            sumXYMultiply += x * y;
            sumXPom += Math.pow( x, 2 );
            sumYPom += Math.pow( y, 2 );
        }

        double numerator = sumXYMultiply - sumX * sumY / n;
        double denX = Math.pow( sumXPom - Math.pow( sumX, 2 ) / n, 1D / 2 );
        double denY = Math.pow( sumYPom - Math.pow( sumY, 2 ) / n, 1D / 2 );
        double denominator = denX * denY;

        return denominator == 0 ? 0 : numerator / denominator;
    }

}
