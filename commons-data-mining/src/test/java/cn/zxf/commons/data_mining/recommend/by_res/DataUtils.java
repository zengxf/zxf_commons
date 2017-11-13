package cn.zxf.commons.data_mining.recommend.by_res;

import cn.zxf.commons.data_mining.commons.Constant;
import cn.zxf.commons.data_mining.vo.User;
import cn.zxf.commons.data_mining.vo.Users;

class DataUtils implements Constant {

    public static Users cosineSimilarity() {
        User otherA = User.of( "otherA", "a", N, "b", 3, "c", 5, "d", 4, "e", 1 );
        User otherB = User.of( "otherB", "a", N, "b", 3, "c", 4, "d", 4, "e", 1 );
        User other1 = User.of( "other1", "a", 4, "b", 3, "c", N, "d", 3, "e", 1 );
        User other2 = User.of( "other2", "a", 4, "b", 4, "c", 4, "d", 3, "e", 1 );
        User other3 = User.of( "other3", "a", 5, "b", 4, "c", 5, "d", N, "e", 3 );

        Users users = new Users();
        users.data.add( otherA );
        users.data.add( otherB );
        users.data.add( other1 );
        users.data.add( other2 );
        users.data.add( other3 );
        return users;
    }

    public static Users slopeOne() {
        User Amy = User.of( "Amy", "Taylor Swift", 4, "PSY", 3, "Whitney Houston", 4 );
        User Ben = User.of( "Ben", "Taylor Swift", 5, "PSY", 2, "Whitney Houston", N );
        User Clara = User.of( "Clara", "Taylor Swift", N, "PSY", 3.5, "Whitney Houston", 4 );
        User Daisy = User.of( "Daisy", "Taylor Swift", 5, "PSY", N, "Whitney Houston", 3 );

        Users users = new Users();
        users.data.add( Amy );
        users.data.add( Ben );
        users.data.add( Clara );
        users.data.add( Daisy );

        return users;
    }

}
