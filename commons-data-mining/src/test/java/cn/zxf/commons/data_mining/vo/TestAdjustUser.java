package cn.zxf.commons.data_mining.vo;

import java.util.Map;

import org.junit.Test;

import cn.zxf.commons.data_mining.commons.Constant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestAdjustUser implements Constant {

    @Test
    public void test() {
        User user = User.of( "otherA", "a", N, "b", 3, "c", 5, "d", 4, "e", 1 );
        AdjustUser adjustUser = AdjustUser.builder().user( user ).build().adjust();
        Map<String, Double> adjust = adjustUser.getAdjustMap();
        log.info( "adjust: {}", adjust );
        log.info( "adjust-restore: {}", adjustUser.restore( 0.5 ) );
    }

}
