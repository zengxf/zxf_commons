package cn.zxf.commons.json.format.impl;

import cn.zxf.commons.json.format.Formater;

public class NullFormater implements Formater {

    @Override
    public String format( Object obj ) {
        return NULL_STRING;
    }

}
