package cn.zxf.commons.data_mining.numbers.distance;

import cn.zxf.commons.data_mining.numbers.utils.VectorUtils;

/**
 * 余弦相似度<br>
 * 如果数据是稀疏的，则使用余弦相似度
 * 
 * <p>
 * Created by zengxf on 2017-11-09
 */
public class CosineSimilarity extends AbstractDistanceCalculator {

    @Override
    protected double calculate( double[] xArr, double[] yArr ) {
        double xLength = VectorUtils.length( xArr );
        double yLength = VectorUtils.length( yArr );
        double numerator = VectorUtils.dotProduct( xArr, yArr );
        double denominator = xLength * yLength;
        return denominator == 0 ? 0 : numerator / denominator;
    }

}
