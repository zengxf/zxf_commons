package cn.zxf.commons.json.format;

import cn.zxf.commons.json.format.impl.CollectionFormater;
import cn.zxf.commons.json.format.impl.MapFormater;
import cn.zxf.commons.json.format.impl.NullFormater;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FormatTypeEnum {

    NULL( Null.class, NullFormater.class), //

    MAP( java.util.Map.class, MapFormater.class), //
    COLLECTION( java.util.Collection.class, CollectionFormater.class), //
    //
    ;

    public final Class<?>                  srcClass;
    public final Class<? extends Formater> formaterClass;

    public boolean isNull() {
        return this == NULL;
    }

    static class Null {
    }

}
