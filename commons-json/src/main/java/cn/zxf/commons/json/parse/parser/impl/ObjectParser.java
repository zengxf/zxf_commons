package cn.zxf.commons.json.parse.parser.impl;

import java.util.HashMap;
import java.util.Map;

import cn.zxf.commons.json.exceptions.JsonFormatException;
import cn.zxf.commons.json.parse.parser.AbstractParser;
import cn.zxf.commons.json.parse.parser.Result;

public class ObjectParser extends AbstractParser {

    public ObjectParser( String json, char[] arr, int index ) {
        super( json, arr, index );
    }

    Map<String, Object> map       = new HashMap<>();
    String              key       = null;
    boolean             signValue = false;
    Object              value     = null;

    public Result parse() {
        foo:
        while ( check() ) {
            char c = arr[index++];
            if ( c == ',' ) {
                this.handleEnd();
            } else if ( c == '"' ) {
                Result res = new StringParser( json, arr, index ).parse();
                super.resetIndexAndStart( res );
                key = !signValue ? res.obj.toString() : key;
                value = signValue ? res.obj : null;
            } else if ( c == '[' ) {
                Result res = new ArrayParser( json, arr, index ).parse();
                super.resetIndexAndStart( res );
                this.checkKey();
                value = res.obj;
            } else if ( c == '{' ) {
                Result res = new ObjectParser( json, arr, index ).parse();
                super.resetIndexAndStart( res );
                this.checkKey();
                value = res.obj;
            } else if ( c == ':' ) {
                super.resetStart();
                signValue = true;
            } else if ( c == '}' ) {
                this.handleEnd();
                break foo;
            }
        }
        return Result.builder().index( index ).obj( map ).build();
    }

    private void checkKey() {
        if ( key == null )
            throw new JsonFormatException( "json 格式不对！暂不支持其他类型数据作为 key！json: " + this.json );
    }

    private void handleEnd() {
        if ( signValue ) {
            if ( value == null ) {
                String mid = new String( arr, start, index - start - 1 );
                value = super.valueOf( mid );
            }
            map.put( key, value );
            signValue = false;
            key = null;
            value = null;
            super.resetStart();
        } else {
            throw new JsonFormatException( "json 格式不对！json: " + this.json );
        }
    }

}
