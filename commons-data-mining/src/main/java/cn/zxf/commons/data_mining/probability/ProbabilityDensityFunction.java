package cn.zxf.commons.data_mining.probability;

import java.util.Objects;
import java.util.stream.DoubleStream;

import cn.zxf.commons.data_mining.vo.Events;
import cn.zxf.commons.data_mining.vo.Event.EventAttribute;
import lombok.Builder;
import lombok.Data;

/**
 * 概率密度函数计算，返回的结果是概率<br>
 * 类似正态分布
 * 
 * <p>
 * Created by zengxf on 2017-11-17
 */
@Data
@Builder
public class ProbabilityDensityFunction {

    private Events         data;
    private EventAttribute base;   // 基础事件
    private EventAttribute target; // 计算目标
    // -----------------

    public double normal() {
        return 0;
    }

    public double calculate() {
        double[] arr = data.events.stream() //
                .filter( event -> event.attributes.contains( base ) ) //
                .map( event -> event.attributes.stream() //
                        .filter( attr -> Objects.equals( attr.name, this.target.name ) ) //
                        .findFirst() //
                        .get().value ) //
                .mapToDouble( Double::parseDouble ) //
                .toArray();

        double sum = DoubleStream.of( arr ).sum();
        int card = arr.length;
        double mean = sum / card; // 平均值(μ 读“谬”)

        double pow2Sum = DoubleStream.of( arr ).map( xi -> Math.pow( xi - mean, 2 ) ).sum();
        double sd = Math.sqrt( pow2Sum / ( card - 1 ) ); // 标准差(σ 读“西格玛”)。样本~要减1

        double mu = mean, sigma = sd;

        double x = Double.parseDouble( target.value );
        double sigmaPow2Multiply2 = 2 * Math.pow( sigma, 2 );
        double negDifPow2 = 0 - Math.pow( x - mu, 2 );
        double ePowX = Math.pow( Math.E, negDifPow2 / sigmaPow2Multiply2 );

        double sqrt2Pi = Math.sqrt( 2 * Math.PI );
        double reciprocal = 1D / ( sqrt2Pi * sigma );

        double pr = ePowX * reciprocal;
        return pr;
    }

}
