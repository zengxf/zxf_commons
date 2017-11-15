package cn.zxf.commons.data_mining.classifier;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.classifier.WeightedAvgScore.DistanceScore;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestWeightedAvgScore {

    @Test
    public void test1() {
        List<DistanceScore> refList = new ArrayList<>();
        refList.add( DistanceScore.of( 5, 4 ) );
        refList.add( DistanceScore.of( 10, 5 ) );
        refList.add( DistanceScore.of( 15, 5 ) );
        double forecast = WeightedAvgScore.builder().refList( refList ).build().calculate();
        refList.forEach( ref -> log.info( "ref: {}", ref ) );
        log.info( "forecast: {}", forecast );
        Assert.assertThat( forecast, CoreMatchers.is( 4.454545454545454 ) );
    }

    @Test
    public void test2() {
        List<DistanceScore> refList = new ArrayList<>();
        refList.add( DistanceScore.of( 4, 3 ) );
        refList.add( DistanceScore.of( 8, 3 ) );
        refList.add( DistanceScore.of( 10, 5 ) );
        double forecast = WeightedAvgScore.builder().refList( refList ).build().calculate();
        refList.forEach( ref -> log.info( "ref: {}", ref ) );
        log.info( "forecast: {}", forecast );
        Assert.assertThat( forecast, CoreMatchers.is( 3.421052631578948 ) );
    }

}
