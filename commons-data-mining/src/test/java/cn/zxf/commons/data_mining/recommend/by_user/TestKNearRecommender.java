package cn.zxf.commons.data_mining.recommend.by_user;

import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.numbers.distance.CosineSimilarity;
import cn.zxf.commons.data_mining.numbers.distance.Pearson;
import cn.zxf.commons.data_mining.recommend.by_user.KNearRecommender;
import cn.zxf.commons.data_mining.vo.User;
import cn.zxf.commons.data_mining.vo.Users;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestKNearRecommender {

    @Test
    public void test() {
        User zxf = User.of( "zxf", "java-spring", 3.2, "java-junit", 4.2 );
        User other1 = User.of( "other1", "java-spring", 3.6, "java-junit", 4.6, "os-windows", 3.8, //
                "os-linux", 2.2, "java-dubbo", 4.2 );
        User other2 = User.of( "other2", "java-spring", 2.5, "java-junit", 3.1, "os-windows", 3.8, //
                "os-linux", 4.8, "java-dubbo", 4.5, "java-pattern", 4.9 );
        User other3 = User.of( "other3", "java-spring", 4.5, "java-junit", 4.1, "os-windows", 2.8, //
                "os-linux", 3.8, "java-dubbo", 4.8 );

        Users users = new Users();
        users.data.add( zxf );
        users.data.add( other1 );
        users.data.add( other2 );
        users.data.add( other3 );

        Map<String, Double> res = KNearRecommender.builder() //
                .calculator( new CosineSimilarity() ) //
                .calculator( new Pearson() ) //
                .users( users ) //
                .target( zxf ) //
                .kCount( 2 ) //
                .resultCount( 2 ) //
                .build() //
                .top();

        log.info( "res: {}", res );
        Assert.assertThat( res.get( "java-dubbo" ), CoreMatchers.is( 4.35 ) );
        Assert.assertThat( res.get( "os-windows" ), CoreMatchers.is( 3.8 ) );
    }

}
