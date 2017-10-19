package cn.zxf.commons.json.parse.transformer.impl;

import cn.zxf.commons.json.parse.transformer.Transformer;

public class StringTf implements Transformer<String> {

    @Override
    public String trans( Object obj ) {
        return obj.toString();
    }

}
