package cn.zxf.commons.data_mining.recommend.by_res;

import java.util.Objects;

import cn.zxf.commons.data_mining.vo.Users;
import lombok.Builder;

/**
 * 加权 slope one 算法
 * 
 * <p>
 * Created by zengxf on 2017-11-13
 */
@Builder
public class WeightedSlopeOne extends AbstractSlopeOne {

    private Users  users;
    private String userId;
    private String res;

    public double calculate() {
        Forecast fore = new Forecast();
        users.get( userId ).scoreMap.forEach( ( k, v ) -> {
            if ( Objects.equals( res, k ) && super.neN( v ) ) // 不能是评价过的
                throw new RuntimeException( String.format( "用户[%s]已评价过[%s]商品！", userId, res ) );
            if ( super.isN( v ) ) // 未评分过
                return;
            double dev = SlopeOne.builder().users( users ).res1( res ).res2( k ).build().calculate();
            double score = v;
            int card = super.cardList( users, res, k ).size();
            fore.addNumerator( ( dev + score ) * card ) //
                    .addDenominator( card );
        } );
        return fore.forecast();
    }

    static class Forecast {
        double numeratorSum   = 0;
        double denominatorSum = 0;

        Forecast addNumerator( double value ) {
            numeratorSum += value;
            return this;
        }

        Forecast addDenominator( double value ) {
            denominatorSum += value;
            return this;
        }

        double forecast() {
            return denominatorSum == 0 ? 0 : numeratorSum / denominatorSum;
        }
    }

}
