package cn.zxf.commons.json;

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

    public static Object parseJson( String json ) {
        return ParserService.parse( json );
    }

    public static < T > T parseJson( String json, Class<T> clazz ) {
        return ParserService.parseJson( json, clazz );
    }

}
