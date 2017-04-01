package cn.zxf.commons.basic.compare_field;

import lombok.AllArgsConstructor;

/**
 * 运作详情类型
 * 
 * @author zengxf
 */
@AllArgsConstructor
public enum ActionDetailType {

    ACTION_UP( "up"), // 'a' -> 'b'
    ACTION_SET( "set"), // null -> 'a'
    ACTION_CLEAR( "clear"), // 'a' -> null
    ;

    public final String code;

}
