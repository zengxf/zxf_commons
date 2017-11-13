package cn.zxf.commons.data_mining.numbers.distance;

import org.junit.Test;

import cn.zxf.commons.data_mining.numbers.NumberAsserts;
import cn.zxf.commons.data_mining.numbers.VectorVo;
import cn.zxf.commons.data_mining.numbers.distance.Euclidean;
import cn.zxf.commons.data_mining.numbers.distance.Manhattan;
import cn.zxf.commons.data_mining.numbers.distance.Minkowski;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestDistance {

    @Test
    public void test_manhattan() {
        double d = new Manhattan().calculate( VectorVo.of( 2, 3, 4 ), VectorVo.of( 4, 5, 6 ) );
        log.info( "manhattan-d: {}", d );
        NumberAsserts.assertEq( 6, d );
    }

    @Test
    public void test_euclid() {
        double d = new Euclidean().calculate( VectorVo.of( 2, 3, 4 ), VectorVo.of( 5, 7, 4 ) );
        log.info( "euclid-d: {}", d );
        NumberAsserts.assertEq( 5, d );
    }

    @Test
    public void test_minkowski() {
        double d = new Minkowski().calculate( VectorVo.of( 2, 3, 4 ), VectorVo.of( 2, 3, 4 ) );
        log.info( "minkowski-d: {}", d );
        NumberAsserts.assertEq( 0, d );
    }

}
