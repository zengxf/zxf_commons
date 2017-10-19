package cn.zxf.commons.json.parse.transformer.impl;

import cn.zxf.commons.json.exceptions.NonSupportTransformException;
import cn.zxf.commons.json.parse.transformer.Transformer;

public class IntegerTf implements Transformer<Integer> {

    @Override
    public Integer trans( Object obj ) {
        if ( Integer.class.isInstance( obj ) )
            return (Integer) obj;
        if ( Double.class.isInstance( obj ) )
            return ( (Double) obj ).intValue();
        throw new NonSupportTransformException( "不支持转换成 Integer！obj: " + obj );
    }

}
