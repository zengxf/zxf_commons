package cn.zxf.commons.data_mining.probability;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.zxf.commons.data_mining.vo.Event.EventAttribute;
import cn.zxf.commons.data_mining.vo.Events;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestBayesClassifier {

    @Test
    public void test() {
        Events data = ProbabilityDataUtils.data();

        List<EventAttribute> and = new ArrayList<>();
        and.add( new EventAttribute( "运动目的", "健康" ) );
        and.add( new EventAttribute( "当前水平", "一般" ) );
        and.add( new EventAttribute( "热情", "一般" ) );
        and.add( new EventAttribute( "适应高科技", "yes" ) );

        String classify = BayesClassifier.builder() //
                .data( data ) //
                .conditions( and ) //
                .classifyName( "Result" ) //
                .build() //
                .classify();

        log.info( "classify: {}", classify );
    }

}
