package cn.zxf.commons.data_mining.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class Users {

    public final List<User> data = new ArrayList<>();

    public User get( String userName ) {
        return data.stream().filter( user -> Objects.equals( userName, user.getName() ) ).findFirst().orElse( null );
    }

}
