package cn.zxf.commons.data_mining.commons;

import java.util.Map;

import cn.zxf.commons.data_mining.vo.User;
import cn.zxf.commons.data_mining.vo.Users;

public class UsersUtils {

    @SuppressWarnings( { "rawtypes", "unchecked" } )
    public static Users getUsers( String jsonFile ) {
        Users users = new Users();
        Map map = MapUtilsForTest.getMap( jsonFile );
        map.forEach( ( k, v ) -> {
            User user = getUser( k.toString(), v );
            users.data.add( user );
        } );
        return users;
    }

    @SuppressWarnings( { "rawtypes", "unchecked" } )
    public static User getUser( String name, Object mapObj ) {
        User user = new User();
        user.setName( name );
        Map map = (Map) mapObj;
        map.forEach( ( k, v ) -> user.scoreMap.put( k.toString(), (Double) v ) );
        return user;
    }

}
