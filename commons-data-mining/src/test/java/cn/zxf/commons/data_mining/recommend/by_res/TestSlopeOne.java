package cn.zxf.commons.data_mining.recommend.by_res;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.commons.Constant;
import cn.zxf.commons.data_mining.vo.Users;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestSlopeOne implements Constant {

    @Test
    public void test() {
        Users users = DataUtils.slopeOne();
        double dev1 = calculate( users, "Taylor Swift", "PSY" );
        double dev2 = calculate( users, "PSY", "Taylor Swift" );
        Assert.assertThat( dev1, CoreMatchers.is( 2.0 ) );
        Assert.assertThat( dev2, CoreMatchers.is( -2.0 ) );

        calculate( users, "Taylor Swift", "Whitney Houston" );
    }

    double calculate( Users users, String res1, String res2 ) {
        double dev = SlopeOne.builder() //
                .users( users ) //
                .res1( res1 ) //
                .res2( res2 ) //
                .build() //
                .calculate();
        log.info( "SlopeOne -> {} - {}: {}", res1, res2, dev );
        return dev;
    }

}
