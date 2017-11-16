package cn.zxf.commons.data_mining.probability;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.commons.EventsBuilder;
import cn.zxf.commons.data_mining.vo.Event.EventAttribute;
import cn.zxf.commons.data_mining.vo.Events;

public class TestNaiveBayes {

    @Test
    public void test() {
        Events data = EventsBuilder.of() //
                .names( "运动目的", "当前水平", "热情", "适应高科技", "Result" ) //
                .addValues( "两者皆是", "很少运动", "一般", "yes", "i100" ) //
                .addValues( "两者皆是", "很少运动", "一般", "no", "i100" ) //
                .addValues( "健康", "很少运动", "一般", "yes", "i500" ) //
                .addValues( "外表", "经常运动", "一般", "yes", "i500" ) //
                .addValues( "外表", "一般", "高", "yes", "i500" ) //
                .addValues( "外表", "一般", "高", "no", "i100" ) //
                .addValues( "健康", "一般", "高", "no", "i500" ) //
                .addValues( "两者皆是", "经常运动", "一般", "yes", "i100" ) //
                .addValues( "两者皆是", "一般", "高", "yes", "i500" ) //
                .addValues( "外表", "经常运动", "高", "yes", "i500" ) //
                .addValues( "两者皆是", "经常运动", "高", "no", "i500" ) //
                .addValues( "健康", "经常运动", "一般", "no", "i500" ) //
                .addValues( "健康", "很少运动", "高", "yes", "i500" ) //
                .addValues( "外表", "经常运动", "一般", "no", "i100" ) //
                .addValues( "健康", "很少运动", "一般", "no", "i100" ) //
                .print() //
                .build();

        List<EventAttribute> and = new ArrayList<>();
        and.add( new EventAttribute( "运动目的", "健康" ) );
        and.add( new EventAttribute( "当前水平", "一般" ) );
        and.add( new EventAttribute( "热情", "一般" ) );
        and.add( new EventAttribute( "适应高科技", "yes" ) );

        NaiveBayes bayes = NaiveBayes.builder() //
                .data( data ) //
                .byAndList( and ) //
                .build();

        double prI100 = bayes.expect( new EventAttribute( "Result", "i100" ) ).calculate();
        System.out.println( "PR(i100): " + prI100 );
        Assert.assertThat( prI100, CoreMatchers.is( 0.003086419753086419 ) );

        double prI500 = bayes.expect( new EventAttribute( "Result", "i500" ) ).calculate();
        System.out.println( "PR(i500): " + prI500 );
        Assert.assertThat( prI500, CoreMatchers.is( 0.019753086419753093 ) );

    }

}
