package cn.zxf.commons.data_mining.normalization;

import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestRough {

    @Test
    public void test() {
        Map<String, Double> data = NormDataUtil.data();
        log.info( "data: {}", data );
        Map<String, Double> norm = Rough.builder().data( data ).build().normalization();
        log.info( "norm: {}", norm );
        Assert.assertThat( norm.get( "Brian A" ), CoreMatchers.is( 0.375 ) );
    }

}
