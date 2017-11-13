package cn.zxf.commons.data_mining.recommend.by_res;

import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.commons.Constant;
import cn.zxf.commons.data_mining.recommend.by_res.ResAdjustCosineSimilarity;
import cn.zxf.commons.data_mining.vo.Users;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestResAdjustCosineSimilarity implements Constant {

    @Test
    public void test() {
        Users users = DataUtils.cosineSimilarity();

        double res = ResAdjustCosineSimilarity.builder() //
                .users( users ) //
                .res1( "a" ) //
                .res2( "b" ) //
                .build() //
                .calculate();

        log.info( "res: {}", res );
        Assert.assertThat( res, CoreMatchers.is( 0.5259968577797907 ) );
    }

    @Test
    public void compute() {
        Users users = DataUtils.cosineSimilarity();
        String[] keys = users.data.get( 0 ).scoreMap.keySet().toArray( new String[] {} );
        log.info( "keys: {}", Arrays.toString( keys ) );
        for ( int i = 0; i < keys.length - 1; i++ ) {
            String k1 = keys[i];
            for ( int j = i + 1; j < keys.length; j++ ) {
                String k2 = keys[j];
                double res = ResAdjustCosineSimilarity.builder() //
                        .users( users ) //
                        .res1( k1 ) //
                        .res2( k2 ) //
                        .build() //
                        .calculate();
                System.out.printf( "%s-%s: %.4f\t", k1, k2, res );
            }
            System.out.println();
        }
    }

}
