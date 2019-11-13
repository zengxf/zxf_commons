package cn.zxf.commons.json.parse.parser;

import cn.zxf.commons.json.exceptions.JsonFormatException;
import cn.zxf.commons.json.exceptions.NonSupportedPlainException;

public abstract class AbstractParser {

    protected final String json;
    protected final char[] arr;

    protected int          start;
    protected int          index;

    public AbstractParser( String json, char[] arr, int index ) {
        super();
        this.json = json;
        this.arr = arr;
        this.start = index;
        this.index = index;
    }

    /** 使用此方法前，须将游标往前移一位 */
    public abstract Result parse();

    protected boolean check() {
        if ( index == arr.length )
            throw new JsonFormatException( "json 格式不对！json: " + this.json );
        return true;
    }

    protected Object valueOf( String mid ) {
        try {
            return ParsePlainUtils.valueOf( mid );
        } catch ( NonSupportedPlainException e ) {
            throw new JsonFormatException( "json 格式不对！json: " + this.json, e );
        }
    }

    protected void resetIndexAndStart( Result res ) {
        this.index = res.index;
        this.start = this.index;
    }

    protected void resetStart() {
        this.start = this.index;
    }

}
