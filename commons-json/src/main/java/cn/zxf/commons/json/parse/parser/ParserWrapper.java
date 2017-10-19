package cn.zxf.commons.json.parse.parser;

import java.util.Optional;

import cn.zxf.commons.json.exceptions.JsonFormatException;
import cn.zxf.commons.json.exceptions.NonSupportedPlainException;
import cn.zxf.commons.json.parse.parser.impl.ArrayParser;
import cn.zxf.commons.json.parse.parser.impl.ObjectParser;
import cn.zxf.commons.json.parse.parser.impl.StringParser;

public class ParserWrapper {
    final String json;
    final char[] arr;

    public ParserWrapper( String json ) {
        super();
        this.json = json;
        this.arr = Optional.ofNullable( json ).map( String::toCharArray ).orElse( new char[] {} );
    }

    public static Object parse( String json ) {
        if ( json == null || json.trim().isEmpty() )
            return null;
        return new ParserWrapper( json ).parse();
    }

    private Object parse() {
        int i = 0;
        char c;
        AbstractParser parser = null;
        foo:
        while ( i < arr.length ) {
            c = arr[i++];
            switch ( c ) {
                case '{':
                    parser = new ObjectParser( json, arr, i );
                    break foo;
                case '[':
                    parser = new ArrayParser( json, arr, i );
                    break foo;
                case '"':
                    parser = new StringParser( json, arr, i );
                    break foo;
            }
        }
        if ( parser == null ) {
            try {
                return ParsePlainUtils.valueOf( json );
            } catch ( NonSupportedPlainException e ) {
                throw new JsonFormatException( "json 格式不对！json: " + this.json, e );
            }
        }
        return parser.parse().obj;
    }
}
