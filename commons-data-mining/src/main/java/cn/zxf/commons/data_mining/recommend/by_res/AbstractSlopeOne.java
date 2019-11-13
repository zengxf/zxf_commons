package cn.zxf.commons.data_mining.recommend.by_res;

import java.util.List;
import java.util.stream.Collectors;

import cn.zxf.commons.data_mining.commons.Constant;
import cn.zxf.commons.data_mining.vo.User;
import cn.zxf.commons.data_mining.vo.Users;

public class AbstractSlopeOne implements Constant {

    protected List<User> cardList( Users users, String res1, String res2 ) {
        List<User> cardList = users.data.stream() //
                .filter( user -> this.contains( user.scoreMap, res1, res2 ) ) //
                .collect( Collectors.toList() ); // 同时评价过物品1和物品2的用户
        return cardList;
    }

}
