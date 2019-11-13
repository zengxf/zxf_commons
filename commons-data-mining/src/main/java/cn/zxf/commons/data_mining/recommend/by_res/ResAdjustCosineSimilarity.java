package cn.zxf.commons.data_mining.recommend.by_res;

import java.util.List;
import java.util.stream.Collectors;

import cn.zxf.commons.data_mining.commons.Constant;
import cn.zxf.commons.data_mining.vo.User;
import cn.zxf.commons.data_mining.vo.Users;
import lombok.Builder;
import lombok.Data;

/**
 * 计算物品的修正的余弦相似度<br>
 * 根据共同用户的打分来计算
 * 
 * <p>
 * Created by zengxf on 2017-11-10
 */
@Data
@Builder
public class ResAdjustCosineSimilarity implements Constant {

    private Users  users;
    private String res1;
    private String res2;

    public double calculate() {
        List<User> list = users.data.stream() //
                .filter( user -> this.contains( user.scoreMap, res1, res2 ) ) //
                .collect( Collectors.toList() );

        double difMultiplySum = 0;
        double dif1Pow2Sum = 0;
        double dif2Pow2Sum = 0;
        for ( User user : list ) {
            double avg = this.avg( user );
            double dif1 = user.scoreMap.get( res1 ) - avg;
            double dif2 = user.scoreMap.get( res2 ) - avg;
            difMultiplySum += dif1 * dif2;
            dif1Pow2Sum += Math.pow( dif1, 2 );
            dif2Pow2Sum += Math.pow( dif2, 2 );
        }

        double numerator = difMultiplySum;
        double denominator = Math.sqrt( dif1Pow2Sum ) * Math.sqrt( dif2Pow2Sum );

        return denominator == 0 ? 0 : numerator / denominator;
    }

    private double avg( User user ) {
        return user.scoreMap.values().stream().filter( this::neN ).mapToDouble( v -> v ).average().getAsDouble();
    }

}
