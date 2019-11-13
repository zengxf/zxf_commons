package cn.zxf.commons.basic.transform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限制长度的，暂用于字符串
 * 
 * @author zengxf
 */
@Target( ElementType.FIELD )
@Retention( RetentionPolicy.RUNTIME )
public @interface Limit {

    int maxLength();

}
