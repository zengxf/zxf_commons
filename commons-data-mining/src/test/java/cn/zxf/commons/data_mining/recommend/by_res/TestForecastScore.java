package cn.zxf.commons.data_mining.recommend.by_res;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.recommend.by_res.ForecastScore;
import cn.zxf.commons.data_mining.vo.User;
import cn.zxf.commons.data_mining.vo.Users;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestForecastScore {

    @Test
    public void test() {
        Users users = DataUtils.cosineSimilarity();
        User curUser = users.data.get( 0 );
        String resId = "a";
        double res = ForecastScore.builder() //
                .users( users ) //
                .curUser( curUser ) //
                .resId( resId ) //
                .build() //
                .calculate();
        log.info( "{} 对物品< {} >的评分预计为: {}", curUser.getName(), resId, res );
        Assert.assertThat( res, CoreMatchers.is( 4.509984014468131 ) );
    }

}
