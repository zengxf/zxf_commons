package cn.zxf.commons.data_mining.recommend.by_res;

import java.util.List;

import cn.zxf.commons.data_mining.vo.User;
import cn.zxf.commons.data_mining.vo.Users;
import lombok.Builder;

/**
 * 简单的 slope one 算法
 * 
 * <p>
 * Created by zengxf on 2017-11-13
 */
@Builder
public class SlopeOne extends AbstractSlopeOne {
    private Users  users;
    private String res1;
    private String res2;

    public double calculate() {
        List<User> cardList = super.cardList( users, res1, res2 );
        int card = cardList.size();
        if ( card == 0 )
            return 0;

        double devSum = cardList.stream() //
                .mapToDouble( user -> user.scoreMap.get( res1 ) - user.scoreMap.get( res2 ) ) // 用户对1的评分减去对2的评分
                .sum();

        double dev = devSum / card;
        return dev;
    }

}
