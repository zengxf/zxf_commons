package cn.zxf.commons.data_mining.numbers.distance;

import cn.zxf.commons.data_mining.numbers.VectorVo;

/**
 * 向量距离计算器
 * 
 * <p>
 * Created by zengxf on 2017-11-09
 */
public interface DistanceCalculator {

    double calculate( VectorVo v1, VectorVo v2 );

}
