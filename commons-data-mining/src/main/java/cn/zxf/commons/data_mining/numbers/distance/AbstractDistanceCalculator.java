package cn.zxf.commons.data_mining.numbers.distance;

import cn.zxf.commons.data_mining.numbers.NumberAsserts;
import cn.zxf.commons.data_mining.numbers.VectorVo;

public abstract class AbstractDistanceCalculator implements DistanceCalculator {

    public double calculate( VectorVo v1, VectorVo v2 ) {
        NumberAsserts.assertVectorLen( v1, v2 );
        double[] xArr = v1.getValues();
        double[] yArr = v2.getValues();
        return this.calculate( xArr, yArr );
    }

    protected abstract double calculate( double[] xArr, double[] yArr );

}
