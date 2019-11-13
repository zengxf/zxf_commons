package cn.zxf.commons.basic.transform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 排除不用转换的(即不用转给前端的)
 * 
 * @author zengxf
 */
@Target( ElementType.FIELD )
@Retention( RetentionPolicy.RUNTIME )
public @interface Exclude {
}
