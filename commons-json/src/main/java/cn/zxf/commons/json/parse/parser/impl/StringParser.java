package cn.zxf.commons.json.parse.parser.impl;

import cn.zxf.commons.json.parse.parser.AbstractParser;
import cn.zxf.commons.json.parse.parser.Result;

public class StringParser extends AbstractParser {

    public StringParser( String json, char[] arr, int index ) {
        super( json, arr, index );
    }

    public Result parse() {
        while ( check() ) {
            char c = arr[index++];
            if ( c == '"' )
                break;
        }
        return Result.builder().index( index ).obj( new String( arr, start, index - start - 1 ) ).build();
    }

}
