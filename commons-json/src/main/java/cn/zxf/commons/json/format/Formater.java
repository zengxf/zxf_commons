package cn.zxf.commons.json.format;

import cn.zxf.commons.json.commons.JsonConstant;
import cn.zxf.commons.json.exceptions.FormattingException;
import lombok.NonNull;

public interface Formater extends JsonConstant {

    Formater NULL = obj -> {
        throw new FormattingException( "还未实现方法" );
    };

    /**
     * 将对象格式化成 JSON
     * 
     * @param obj
     *            不能为 null， 应该让外部处理为 null 的情况
     * @return
     */
    String format( @NonNull Object obj );

}
