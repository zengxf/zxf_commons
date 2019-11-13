package cn.zxf.commons.data_mining.normalization;

import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestModifiedStandardScore {

    @Test
    public void test() {
        Map<String, Double> data = NormDataUtil.data();
        log.info( "data: {}", data );
        Map<String, Double> norm = StandardScore.builder().data( data ).build().normalization();
        log.info( "norm: {}", norm );
        Assert.assertThat( norm.get( "Yun L" ), CoreMatchers.is( 0.11714129261815952 ) );
    }

}
