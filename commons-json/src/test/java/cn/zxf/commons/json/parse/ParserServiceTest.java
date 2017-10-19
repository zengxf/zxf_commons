package cn.zxf.commons.json.parse;

import org.junit.Test;

import cn.zxf.commons.json.exceptions.JsonFormatException;
import cn.zxf.commons.json.parse.ParserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParserServiceTest {

    public static class TestObject {
        @Test( expected = JsonFormatException.class )
        public void testBad() {
            String json = "{\"name\": \"zxf\", \"age\": 23, \"}";
            log.info( "bad-json: {}", json );
            ParserService.parse( json );
        }

        @Test
        public void testCorrect() {
            String json = "{\"name\": \"zxf-{}\", \"age\": 23}";
            log.info( "correct-json: {}", json );
            Object res = ParserService.parse( json );
            log.info( "parse result: {}", res );
        }
        
        @Test
        public void testComplexCorrect() {
            String json = "{\"name\": \"zxf-{}\", \"address\":{\"code\": 10}, \"age\": 23, \"activate\": true}";
            log.info( "correct-json: {}", json );
            Object res = ParserService.parse( json );
            log.info( "parse result: {}", res );
        }
    }

    public static class TestArray {
        @Test( expected = JsonFormatException.class )
        public void testBad() {
            String json = "[32, 34, 45, \"]";
            log.info( "array-json: {}", json );
            ParserService.parse( json );
        }

        @Test
        public void testCorrect() {
            String json = "[32, 34, 45]";
            log.info( "array-json: {}", json );
            Object res = ParserService.parse( json );
            log.info( "parse result: {}", res );
        }
    }

}
