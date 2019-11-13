package cn.zxf.commons.json.parse.transformer.impl;

import cn.zxf.commons.json.exceptions.NonSupportTransformException;
import cn.zxf.commons.json.parse.transformer.Transformer;

public class BooleanTf implements Transformer<Boolean> {

    @Override
    public Boolean trans( Object obj ) {
        if ( Integer.class.isInstance( obj ) )
            return ( (Integer) obj ).equals( 1 );
        if ( Boolean.class.isInstance( obj ) )
            return (Boolean) obj;
        throw new NonSupportTransformException( "不支持转换成 Boolean！obj: " + obj );
    }

}
