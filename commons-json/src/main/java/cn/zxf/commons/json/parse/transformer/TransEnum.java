package cn.zxf.commons.json.parse.transformer;

import java.util.Map;
import java.util.Set;

import cn.zxf.commons.json.parse.transformer.impl.BooleanTf;
import cn.zxf.commons.json.parse.transformer.impl.DoubleTf;
import cn.zxf.commons.json.parse.transformer.impl.IntegerTf;
import cn.zxf.commons.json.parse.transformer.impl.MapTf;
import cn.zxf.commons.json.parse.transformer.impl.SetTf;
import cn.zxf.commons.json.parse.transformer.impl.StringTf;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransEnum {

    SET( Set.class, SetTf.class), //
    MAP( Map.class, MapTf.class), //
    BOOLEAN( Boolean.class, BooleanTf.class), //
    INTEGER( Integer.class, IntegerTf.class), //
    DOUBLE( Double.class, DoubleTf.class), //
    STRING( String.class, StringTf.class), //
    //
    ;

    public final Class<?>                        target;
    public final Class<? extends Transformer<?>> transformer;
}
