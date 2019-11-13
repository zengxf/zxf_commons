package cn.zxf.commons.basic.compare_field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 比较的具体结果片段
 * 
 * @author zengxf
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompareDifferentPiece {

    private String field;     // 字段名
    private Object newValue;  // 新值
    /**
     * 操作类型
     * 
     * @see ActionDetailType
     */
    private String actionType;

}