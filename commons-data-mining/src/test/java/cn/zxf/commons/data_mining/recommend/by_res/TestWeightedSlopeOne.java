package cn.zxf.commons.data_mining.recommend.by_res;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.vo.Users;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestWeightedSlopeOne {

    @Test
    public void test() {
        Users users = DataUtils.slopeOne();
        double value = calculate( users, "Ben", "Whitney Houston" );
        Assert.assertThat( value, CoreMatchers.is( 3.375 ) );
    }

    double calculate( Users users, String user, String res ) {
        double value = WeightedSlopeOne.builder() //
                .users( users ) //
                .userId( user ) //
                .res( res ) //
                .build() //
                .calculate();
        log.info( "SlopeOne -> {} - {}: {}", user, res, value );
        return value;
    }

}
