package cn.zxf.commons.data_mining.probability;

import java.util.List;

import cn.zxf.commons.data_mining.vo.Event.EventAttribute;
import cn.zxf.commons.data_mining.vo.Events;
import lombok.Builder;
import lombok.Data;

/**
 * 朴素贝叶斯<br>
 * P(A | B)参考：{@link AByBProbability}
 * 
 * <pre>
 * P( A | B & C & D ) = P( B | A ) * P( C | A ) * P( D | A ) * P( A )
 * </pre>
 * 
 * <p>
 * Created by zengxf on 2017-11-16
 */
@Data
@Builder
public class NaiveBayes {

    private Events               data;      // 原始数据
    private List<EventAttribute> byAndList; // 所有必须发生的事件
    private EventAttribute       expect;    // 期望发生的事件
    // ------------------
    private double               prBase;

    public NaiveBayes expect( EventAttribute expect ) {
        this.expect = expect;
        return this;
    }

    public double calculate() {
        this.prBase = this.eventProbability( expect );
        double prMultiply = byAndList.stream() //
                .mapToDouble( attr -> this.eventAndProbability( attr ) ) //
                .reduce( 1D, ( v1, v2 ) -> v1 * v2 );
        return prMultiply * this.prBase;
    }

    private double eventAndProbability( EventAttribute accur ) {
        long accurAndCount = data.events.stream().filter( event -> {
            return event.attributes.contains( expect ) && event.attributes.contains( accur );
        } ).count();
        double prAnd = this.probability( accurAndCount );
        return AByBProbability.builder().aAndBProbability( prAnd ).bProbability( this.prBase ).build().calculate();
    }

    private double eventProbability( EventAttribute attr ) {
        long count = data.events.stream().filter( event -> {
            return event.attributes.contains( attr );
        } ).count();
        return this.probability( count );
    }

    private double probability( long count ) {
        return 1D * count / data.events.size();
    }

}
