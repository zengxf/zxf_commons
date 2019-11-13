package cn.zxf.commons.data_mining.numbers.utils;

import cn.zxf.commons.data_mining.numbers.NumberAsserts;
import cn.zxf.commons.data_mining.numbers.VectorVo;

/**
 * 向量运算帮助类
 * 
 * <p>
 * Created by zengxf on 2017-11-09
 */
public class VectorUtils {

    /**
     * 向量的模，也叫长度
     * 
     * @param vo
     * @return
     */
    public static double length( VectorVo vo ) {
        NumberAsserts.assertVectorNotNull( vo );
        double[] arr = vo.getValues();
        return length( arr );
    }

    /**
     * 向量的模，也叫长度
     * 
     * @param vo
     * @return
     */
    public static double length( double[] arr ) {
        NumberAsserts.assertArrayNotNull( arr );
        double pom2Sum = 0;
        for ( int i = 0; i < arr.length; i++ ) {
            pom2Sum += arr[i] * arr[i];
        }
        double vectorLength = Math.pow( pom2Sum, 1D / 2 );
        return vectorLength;
    }

    /**
     * 向量点积
     * 
     * @param v1
     * @param v2
     * @return
     */
    public static double dotProduct( VectorVo v1, VectorVo v2 ) {
        NumberAsserts.assertVectorLen( v1, v2 );
        double[] xArr = v1.getValues();
        double[] yArr = v2.getValues();
        return dotProduct( xArr, yArr );
    }

    /**
     * 向量点积
     * 
     * @param v1
     * @param v2
     * @return
     */
    public static double dotProduct( double[] xArr, double[] yArr ) {
        NumberAsserts.assertArrayLen( xArr, yArr );
        double productSum = 0;
        for ( int i = 0; i < xArr.length; i++ ) {
            productSum += xArr[i] * yArr[i];
        }
        return productSum;
    }

}
