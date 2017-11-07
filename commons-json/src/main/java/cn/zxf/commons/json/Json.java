package cn.zxf.commons.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import cn.zxf.commons.json.format.FormatService;
import cn.zxf.commons.json.parse.ParserService;

/**
 * commons-json 的封装类
 * 
 * <p>
 * Created by zengxf on 2017-10-13
 */
public class Json {

    public static String toJson( Object obj ) {
        return FormatService.format( obj );
    }

    public static Object parseJson( Path path ) throws IOException {
        String json = Files.readAllLines( path ).stream().collect( Collectors.joining( "\n" ) );
        return parseJson( json );
    }

    public static Object parseJson( String json ) {
        return ParserService.parse( json );
    }

    public static < T > T parseJson( String json, Class<T> clazz ) {
        return ParserService.parseJson( json, clazz );
    }

}
