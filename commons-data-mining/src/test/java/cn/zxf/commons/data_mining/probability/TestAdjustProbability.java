package cn.zxf.commons.data_mining.probability;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.vo.Event.EventAttribute;
import cn.zxf.commons.data_mining.vo.Events;

public class TestAdjustProbability {

    @Test
    public void test() {
        Events data = ProbabilityDataUtils.data();
        // EventAttribute attr = new EventAttribute( "运动目的", "健康" );
        EventAttribute attr = new EventAttribute( "当前水平", "一般" );
        double pr = AdjustProbability.builder().data( data ).attr( attr ).build().probability();
        System.out.println( "attr: " + attr + ", pr: " + pr );
        Assert.assertThat( pr, CoreMatchers.is( 0.2777777777777778 ) );
    }

}
