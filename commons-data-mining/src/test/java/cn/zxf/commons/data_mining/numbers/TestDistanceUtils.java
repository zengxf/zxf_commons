package cn.zxf.commons.data_mining.numbers;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDistanceUtils {

    @Test
    public void test_manhattan() {
        double d = DistanceUtils.manhattan( VectorVo.of( 2, 3, 4 ), VectorVo.of( 4, 5, 6 ) );
        log.info( "manhattan-d: {}", d );
        NumberAsserts.assertEq( 6, d );
    }

    @Test
    public void test_euclid() {
        double d = DistanceUtils.euclid( VectorVo.of( 2, 3, 4 ), VectorVo.of( 5, 7, 4 ) );
        log.info( "euclid-d: {}", d );
        NumberAsserts.assertEq( 5, d );
    }

    @Test
    public void test_minkowski() {
        double d = DistanceUtils.minkowski( VectorVo.of( 2, 3, 4 ), VectorVo.of( 2, 3, 4 ) );
        log.info( "minkowski-d: {}", d );
        NumberAsserts.assertEq( 0, d );
    }

}
