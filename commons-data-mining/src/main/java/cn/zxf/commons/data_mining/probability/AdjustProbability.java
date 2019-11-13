package cn.zxf.commons.data_mining.probability;

import cn.zxf.commons.data_mining.vo.Event.EventAttribute;

import java.util.Objects;

import cn.zxf.commons.data_mining.vo.Events;
import lombok.Builder;
import lombok.Data;

/**
 * 调整概率，防止出现 0 的情况
 * 
 * <pre>
 * P( a ) = Na + 1 / N + Nt // Na: 发生事件 a 的总数, Nt: 事件属性A类型种数
 * </pre>
 * 
 * <p>
 * Created by zengxf on 2017-11-17
 */
@Data
@Builder
public class AdjustProbability {

    private Events         data;
    private EventAttribute attr;

    public double probability() {
        int total = data.events.size();
        int count = (int) data.events.stream().filter( event -> event.attributes.contains( attr ) ).count();
        int typeCount = (int) data.events.stream() //
                .map( event -> event.attributes.stream() //
                        .filter( ea -> Objects.equals( ea.name, attr.name ) ) //
                        .map( EventAttribute::getValue ) //
                        .findFirst() //
                        .orElse( null ) //
                ) //
                .filter( Objects::nonNull ) //
                .distinct() //
                .count();
        return 1D * ( 1 + count ) / ( total + typeCount );
    }

}
