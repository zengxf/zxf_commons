package cn.zxf.commons.data_mining.probability;

import lombok.Builder;
import lombok.Data;

/**
 * 贝叶斯法则
 * 
 * <pre>
 * P( h | D ) = P( D | h ) * P( h ) / P( D )    // 最终 h 的概率
 * P( A | B ) = P( B | A ) * P( A ) / P( B )    // 最终 A 的概率
 * </pre>
 * 
 * <p>
 * Created by zengxf on 2017-11-15
 */
@Data
@Builder
public class BayesTheorem {

    private double aProbability;
    private double bProbability;
    private double aAndBProbability;

    /**
     * 返回的值需要再乘 100，才能算百分比
     * 
     * @return
     */
    public double calculate() {
        double bByAProbability = AByBProbability.builder() //
                .aAndBProbability( aAndBProbability ) //
                .bProbability( bProbability ) //
                .build() //
                .calculate();
        return bByAProbability * aProbability / bProbability;
    }

}
