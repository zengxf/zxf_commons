package cn.zxf.commons.json.parse.parser.impl;

import java.util.ArrayList;
import java.util.List;

import cn.zxf.commons.json.parse.parser.AbstractParser;
import cn.zxf.commons.json.parse.parser.Result;

public class ArrayParser extends AbstractParser {

    public ArrayParser( String json, char[] arr, int index ) {
        super( json, arr, index );
    }

    List<Object> list = new ArrayList<>();
    Object       value;

    public Result parse() {
        foo:
        while ( check() ) {
            char c = arr[index++];
            if ( c == ',' ) {
                this.handleEnd();
            } else if ( c == '"' ) {
                Result res = new StringParser( json, arr, index ).parse();
                super.resetIndexAndStart( res );
                value = res.obj;
            } else if ( c == '[' ) {
                Result res = new ArrayParser( json, arr, index ).parse();
                super.resetIndexAndStart( res );
                value = res.obj;
            } else if ( c == '{' ) {
                Result res = new ObjectParser( json, arr, index ).parse();
                super.resetIndexAndStart( res );
                value = res.obj;
            } else if ( c == ']' ) {
                this.handleEnd();
                break foo;
            }
        }
        return Result.builder().index( index ).obj( list ).build();
    }

    private void handleEnd() {
        if ( value == null ) {
            String mid = new String( arr, start, index - start - 1 );
            value = super.valueOf( mid );
        }
        list.add( value );
        value = null;
        super.resetStart();
    }

}
