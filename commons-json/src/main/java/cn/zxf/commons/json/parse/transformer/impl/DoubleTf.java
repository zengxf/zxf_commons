package cn.zxf.commons.json.parse.transformer.impl;

import cn.zxf.commons.json.exceptions.NonSupportTransformException;
import cn.zxf.commons.json.parse.transformer.Transformer;

public class DoubleTf implements Transformer<Double> {

    @Override
    public Double trans( Object obj ) {
        if ( Integer.class.isInstance( obj ) )
            return ( (Integer) obj ).doubleValue();
        if ( Double.class.isInstance( obj ) )
            return (Double) obj;
        throw new NonSupportTransformException( "不支持转换成 Double！obj: " + obj );
    }

}
