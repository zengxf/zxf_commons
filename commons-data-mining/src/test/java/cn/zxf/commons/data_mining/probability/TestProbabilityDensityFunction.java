package cn.zxf.commons.data_mining.probability;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.vo.Event.EventAttribute;
import cn.zxf.commons.data_mining.vo.Events;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestProbabilityDensityFunction {

    @Test
    public void test() {
        Events data = ProbabilityDataUtils.data();
        EventAttribute base = EventAttribute.of( "Result", "i500" );
        EventAttribute target = EventAttribute.of( "收入", "100" );

        double pr = ProbabilityDensityFunction.builder() //
                .data( data ) //
                .base( base ) //
                .target( target ) //
                .build() //
                .calculate();

        log.info( "PR({}|{}): {}", target.value, base.value, pr );
        Assert.assertThat( pr, CoreMatchers.is( 0.0179532103045308 ) );
    }

}
