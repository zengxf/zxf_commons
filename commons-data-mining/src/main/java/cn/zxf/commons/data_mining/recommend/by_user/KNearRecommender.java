package cn.zxf.commons.data_mining.recommend.by_user;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.zxf.commons.data_mining.commons.MapUtils;
import cn.zxf.commons.data_mining.commons.MapUtilsForTest;
import cn.zxf.commons.data_mining.numbers.VectorVo;
import cn.zxf.commons.data_mining.numbers.distance.DistanceCalculator;
import cn.zxf.commons.data_mining.vo.User;
import cn.zxf.commons.data_mining.vo.Users;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * K 近邻推荐算法
 * 
 * <p>
 * Created by zengxf on 2017-11-09
 */
@Slf4j
@Data
@Builder
public class KNearRecommender {

    private User                      target;
    private Users                     users;
    private DistanceCalculator        calculator;
    private int                       kCount;
    private int                       resultCount;

    private List<User>                others;
    private final Map<String, Double> distanceMap = new HashMap<>();

    private void assertNonNull() {
        Objects.requireNonNull( target, "目标用户为空" );
        Objects.requireNonNull( users, "用户集合为空" );
        Objects.requireNonNull( calculator, "距离计算器为空" );
        if ( kCount < 1 )
            throw new RuntimeException( "K 参数不对" );
        if ( resultCount < 1 )
            throw new RuntimeException( "结果返回参数不对" );
    }

    private void clean() {
        this.others = users.data.stream() //
                .filter( user -> !Objects.equals( user.getName(), target.getName() ) ) //
                .collect( Collectors.toList() );
    }

    private double distance( User u1, User u2 ) {
        VectorVo v1 = new VectorVo();
        VectorVo v2 = new VectorVo();
        MapUtilsForTest.fillVector( v1, v2, u1.scoreMap, u2.scoreMap );
        return calculator.calculate( v1, v2 );
    }

    private void calculate() {
        this.assertNonNull();
        this.clean();
        others.forEach( user -> {
            double distance = this.distance( this.target, user );
            this.distanceMap.put( user.getName(), distance );
        } );
    }

    /**
     * 返回最相似的 K 个用户
     * 
     * @return
     */
    private Map<String, Double> similar() {
        this.calculate();
        Map<String, Double> res = new LinkedHashMap<>( kCount );
        Map<String, Double> desc = MapUtils.sortDesc( this.distanceMap );
        log.debug( "similar-candidate: {}", desc );
        desc.entrySet().stream().limit( kCount ).forEach( e -> res.put( e.getKey(), e.getValue() ) );
        return res; // 此 top 只是最相似的几个人，还非推荐的商品
    }

    /**
     * 返回最相似的 K 个用户的权值
     * 
     * @return
     */
    private Map<String, Double> weight() {
        Map<String, Double> similar = this.similar();
        log.debug( "similar: {}", similar );
        double sum = similar.values().stream().mapToDouble( v -> v ).sum();
        Map<String, Double> weight = similar.entrySet().stream() //
                .collect( Collectors.toMap( e -> e.getKey(), e -> e.getValue() / sum, ( v1, v2 ) -> null, LinkedHashMap::new ) );
        return weight;
    }

    /**
     * 返回推荐的商品
     * 
     * @return
     */
    public Map<String, Double> top() {
        Map<String, Double> weightMap = this.weight();
        log.debug( "weight-map: {}", weightMap );
        Map<String, Double> result = new HashMap<>();
        Map<String, Double> curScoreMap = target.scoreMap;
        others.stream() //
                .filter( user -> weightMap.containsKey( user.getName() ) ) // 拿 K 个用户的商品来推荐
                .forEach( user -> {
                    user.scoreMap.forEach( ( wareId, score ) -> {
                        if ( curScoreMap.containsKey( wareId ) ) // 当前用户有购买则忽略
                            return;
                        double computeScore = weightMap.get( user.getName() ) * score;
                        result.compute( wareId, ( k, v ) -> v == null ? computeScore : computeScore + v ); // 对分数进行累加
                    } );
                } );
        Map<String, Double> desc = MapUtils.sortDesc( result );
        log.debug( "top-candidate: {}", desc );
        Map<String, Double> res = desc.entrySet().stream() //
                .limit( resultCount ) //
                .collect( Collectors.toMap( e -> e.getKey(), e -> e.getValue(), ( v1, v2 ) -> null, LinkedHashMap::new ) );
        return res; // 最终推荐的商品
    }

}
