package cn.zxf.commons.data_mining.numbers;

/**
 * 皮尔逊相关系数
 * 
 * <p>
 * Created by zengxf on 2017-11-07
 */
public class PearsonUtils {

    public static double calculate( VectorVo v1, VectorVo v2 ) {
        NumberAsserts.assertVectorLen( v1, v2 );
        double[] xArr = v1.getValues();
        double[] yArr = v2.getValues();

        double avgX = ArrayUtils.avg( xArr );
        double avgY = ArrayUtils.avg( yArr );

        double numerator = 0, sumDiffXPom = 0, sumDiffYPom = 0;
        for ( int i = 0; i < v1.getLen(); i++ ) {
            double diffX = xArr[i] - avgX;
            double diffY = yArr[i] - avgY;
            numerator += diffX * diffY;
            sumDiffXPom += Math.pow( diffX, 2 );
            sumDiffYPom += Math.pow( diffY, 2 );
        }

        double denominator = Math.pow( sumDiffXPom, 1D / 2 ) * Math.pow( sumDiffYPom, 1D / 2 );

        return denominator == 0 ? 0 : numerator / denominator;
    }

    /**
     * 相似皮尔逊相关系数计算
     * 
     * @param v1
     * @param v2
     * @return
     */
    public static double calculateSimilar( VectorVo v1, VectorVo v2 ) {
        NumberAsserts.assertVectorLen( v1, v2 );
        int n = v1.getLen();
        double[] xArr = v1.getValues();
        double[] yArr = v2.getValues();

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
