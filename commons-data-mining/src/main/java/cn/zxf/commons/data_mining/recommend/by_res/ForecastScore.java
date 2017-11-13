package cn.zxf.commons.data_mining.recommend.by_res;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.zxf.commons.data_mining.commons.Constant;
import cn.zxf.commons.data_mining.vo.AdjustUser;
import cn.zxf.commons.data_mining.vo.User;
import cn.zxf.commons.data_mining.vo.Users;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * 预测用户对商品打分——根据物品之间的修正余弦相似度
 * 
 * <p>
 * Created by zengxf on 2017-11-10
 */
@Slf4j
@Builder
public class ForecastScore implements Constant {

    private String resId;
    private User   curUser;
    private Users  users;

    public double calculate() {
        Map<String, Double> cosineSimilar = curUser.scoreMap.entrySet().stream() //
                .filter( e -> !Objects.equals( e.getKey(), resId ) || this.neN( e.getValue() ) ) // 过滤掉当前物品的或未评价的
                .collect( Collectors.toMap( e -> e.getKey(), e -> this.adjust( e.getKey() ) //
                        , ( v1, v2 ) -> null, LinkedHashMap::new ) );
        log.debug( "cosine-similar: {}", cosineSimilar );
        AdjustUser adjustUser = AdjustUser.builder().user( curUser ).build().adjust();
        Map<String, Double> adjustUserScore = adjustUser.getAdjustMap();
        log.debug( "adjust-user-score: {}", adjustUserScore );
        double absSum = cosineSimilar.values().stream().mapToDouble( v -> Math.abs( v ) ).sum();
        double multiplySum = 0;
        for ( String res : cosineSimilar.keySet() ) {
            double adjust = adjustUserScore.get( res );
            if ( this.isN( adjust ) )
                continue;
            multiplySum += adjust * cosineSimilar.get( res );
        }
        double numerator = multiplySum;
        double denominator = absSum;
        double score = denominator == 0 ? 0 : numerator / denominator;
        log.debug( "forecast-score: {}", score );
        return adjustUser.restore( score );
    }

    /**
     * 只是计算物品与物品之前的相似度
     */
    private double adjust( String userResId ) {
        return ResAdjustCosineSimilarity.builder() //
                .res1( this.resId ) // 当前用户是绝没有评价过此物品
                .res2( userResId ) //
                .users( this.users ) //
                .build() //
                .calculate();
    }

}
