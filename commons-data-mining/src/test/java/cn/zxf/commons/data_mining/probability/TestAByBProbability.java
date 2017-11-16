package cn.zxf.commons.data_mining.probability;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestAByBProbability {

    @Test
    public void test() {
        double probability = AByBProbability.builder().aAndBProbability( 0.4 ).bProbability( 0.5 ).build().calculate();
        log.info( "probability: {}", probability );
        Assert.assertThat( probability, CoreMatchers.is( 0.8 ) );
    }

}
