package cn.zxf.commons.data_mining.vo;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String                   name;
    public final Map<String, Double> scoreMap = new LinkedHashMap<>();

    public static User of( String name, Object... objects ) {
        User user = new User();
        user.name = name;
        for ( int i = 0; i < objects.length; ) {
            user.scoreMap.put( objects[i++].toString(), valueOf( objects[i++] ) );
        }
        return user;
    }

    static double valueOf( Object dob ) {
        if ( dob instanceof Integer )
            return ( (Integer) dob ).doubleValue();
        return (double) dob;
    }

}
