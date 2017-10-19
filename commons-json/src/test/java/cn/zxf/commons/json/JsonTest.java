package cn.zxf.commons.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonTest {

    public static class TestFormat {
        @Test
        public void nullToJson() {
            String json = Json.toJson( null );
            log.info( "null-json: {}", json );
        }

        @Test
        public void mapToJson() {
            Map<String, Object> map = new HashMap<>();
            map.put( "age", 23 );
            map.put( "name", "zxf" );
            map.put( "activity", true );
            String json = Json.toJson( map );
            log.info( "map-json: {}", json );
        }

        @Test
        @SuppressWarnings( { "rawtypes", "unchecked" } )
        public void listToJson() {
            List list = new ArrayList();
            list.add( 23 );
            list.add( "zxf" );
            list.add( true );
            String json = Json.toJson( list );
            log.info( "list-json: {}", json );
        }

        @Test
        @SuppressWarnings( { "rawtypes", "unchecked" } )
        public void setToJson() {
            Set list = new HashSet();
            list.add( 23 );
            list.add( "zxf" );
            list.add( true );
            String json = Json.toJson( list );
            log.info( "set-json: {}", json );
        }

        @Test
        public void constomBeanToJson() {
            Group group = Group.builder().id( 10001 ).groupName( "zxf-test" ).build();
            User user = User.builder().name( "zxf" )//
                    .address( "湖南" ) //
                    .age( 23 ).status( 2 ).activity( true ).group( group ).build();
            user.sex = "男";
            String json = Json.toJson( user );
            log.info( "user-json: {}", json );

            user.address = null;
            json = Json.toJson( user );
            log.info( "user-json: {}", json );
        }
    }

    public static class TestParse {
        @Test
        public void parseConstomBean() {
            String json = "{\"name\": \"zxf\", \"age\": 23, \"status\": 2, \"activity\": true, " + //
                    " \"group\": {\"id\": 10001, \"groupName\": \"zxf-test\"}, \"sex\": \"男\"}";
            log.info( "user-json: {}", json );
            User user = Json.parseJson( json, User.class );
            log.info( "user: {}", user );
        }

    }

    public static class TestPerformance {
        @Test
        public void format() {
            Group group = Group.builder().id( 10001 ).groupName( "zxf-test" ).build();
            User user = User.builder().name( "zxf" )//
                    .address( "湖南" ) //
                    .age( 23 ).status( 2 ).activity( true ).group( group ).build();
            user.sex = "男";
            log.info( "user: {}", user );

            int count = 10_0000;
            long start = System.currentTimeMillis();
            for ( int i = 0; i < count; i++ ) {
                Json.toJson( user );
            }
            log.info( "[{}] 次格式化耗时：{}", count, System.currentTimeMillis() - start );
        }

        @Test
        public void parse() {
            String json = "{\"name\": \"zxf\", \"age\": 23, \"status\": 2, \"activity\": true, " + //
                    " \"group\": {\"id\": 10001, \"groupName\": \"zxf-test\"}, \"sex\": \"男\"}";

            int count = 10_0000;
            long start = System.currentTimeMillis();
            for ( int i = 0; i < count; i++ ) {
                Json.parseJson( json, User.class );
            }
            log.info( "[{}] 次解析耗时：{}", count, System.currentTimeMillis() - start );
        }
    }

    // -----------------------
    // -----------------------
    // -----------------------

    static class Person {
        String sex;
    }

    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User extends Person {
        String  name;
        String  address;
        int     age;
        int     status;
        boolean activity;
        Group   group;
    }

    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Group {
        int    id;
        String groupName;
    }

}
