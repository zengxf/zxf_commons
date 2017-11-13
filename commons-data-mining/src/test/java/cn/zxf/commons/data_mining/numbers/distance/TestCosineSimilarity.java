package cn.zxf.commons.data_mining.numbers.distance;

import org.junit.Test;

import cn.zxf.commons.data_mining.numbers.NumberAsserts;
import cn.zxf.commons.data_mining.numbers.VectorVo;
import cn.zxf.commons.data_mining.numbers.distance.CosineSimilarity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestCosineSimilarity {

    @Test
    public void test() {
        VectorVo v1 = VectorVo.of( 4.75, 4.5, 5, 4.25, 4 );
        VectorVo v2 = VectorVo.of( 4, 3, 5, 2, 1 );
        double cs = new CosineSimilarity().calculate( v1, v2 );
        log.info( "cs: {}", cs );
        NumberAsserts.assertEq( 0.9351534585705217D, cs );
    }

}
