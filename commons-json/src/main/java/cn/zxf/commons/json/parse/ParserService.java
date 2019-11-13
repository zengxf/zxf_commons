package cn.zxf.commons.json.parse;

import cn.zxf.commons.json.parse.parser.ParserWrapper;

public class ParserService {

    public static Object parse( String json ) {
        return ParserWrapper.parse( json );
    }

    public static < T > T parseJson( String json, Class<T> clazz ) {
        Object obj = ParserWrapper.parse( json );
        if ( obj == null )
            return null;
        if ( clazz == null )
            throw new NullPointerException( "要转换的目标类不能为空" );
        return TransformUtil.trans( obj, clazz );
    }

}
