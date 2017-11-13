package cn.zxf.commons.data_mining.numbers.distance;

import cn.zxf.commons.data_mining.numbers.utils.ArrayUtils;

/**
 * 皮尔逊相关系数<br>
 * 如果数据存在“分数膨胀”问题，就使用皮尔逊相关系数。
 * 
 * <p>
 * Created by zengxf on 2017-11-09
 */
public class Pearson extends AbstractDistanceCalculator {

    @Override
    protected double calculate( double[] xArr, double[] yArr ) {
        double avgX = ArrayUtils.avg( xArr );
        double avgY = ArrayUtils.avg( yArr );

        double numerator = 0, sumDiffXPom = 0, sumDiffYPom = 0;
        for ( int i = 0; i < xArr.length; i++ ) {
            double diffX = xArr[i] - avgX;
            double diffY = yArr[i] - avgY;
            numerator += diffX * diffY;
            sumDiffXPom += Math.pow( diffX, 2 );
            sumDiffYPom += Math.pow( diffY, 2 );
        }

        double denominator = Math.pow( sumDiffXPom, 1D / 2 ) * Math.pow( sumDiffYPom, 1D / 2 );

        return denominator == 0 ? 0 : numerator / denominator;
    }

}
