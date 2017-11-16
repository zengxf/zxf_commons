package cn.zxf.commons.data_mining.probability;

import lombok.Builder;
import lombok.Data;

/**
 * A 在 B 发生下的概率<br>
 * 即 B 事件发生的条件下 A 事件发生的概率
 * 
 * <pre>
 * P(A | B) = P(A ∩ B) / P(B)
 * </pre>
 * 
 * <p>
 * Created by zengxf on 2017-11-15
 */
@Data
@Builder
public class AByBProbability {

    private double bProbability;     // 发生 B事件 的概率
    private double aAndBProbability; // 同时发生 A事件 和 B事件 的概率

    /**
     * 返回的值需要再乘 100，才能算百分比
     * 
     * @return
     */
    public double calculate() {
        return aAndBProbability / bProbability;
    }

}
