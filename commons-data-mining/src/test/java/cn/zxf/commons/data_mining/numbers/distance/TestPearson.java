package cn.zxf.commons.data_mining.numbers.distance;

import org.junit.Test;

import cn.zxf.commons.data_mining.numbers.NumberAsserts;
import cn.zxf.commons.data_mining.numbers.VectorVo;
import cn.zxf.commons.data_mining.numbers.distance.Pearson;
import cn.zxf.commons.data_mining.numbers.distance.PearsonSimilar;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestPearson {

    @Test
    public void test() {
        double d = new Pearson().calculate( VectorVo.of( 4.75, 4.5, 5, 4.25, 4 ), VectorVo.of( 4, 3, 5, 2, 1 ) );
        log.info( "pearson-d: {}", d );
        NumberAsserts.assertEq( 0.9999999999999998D, d );
    }

    @Test
    public void testSimilar() {
        double d = new PearsonSimilar().calculate( VectorVo.of( 4.75, 4.5, 5, 4.25, 4 ), VectorVo.of( 4, 3, 5, 2, 1 ) );
        log.info( "pearson-d: {}", d );
        NumberAsserts.assertEq( 0.9999999999999998D, d );
    }

}
