package cn.zxf.commons.json.parse.parser;

import cn.zxf.commons.json.commons.JsonConstant;
import cn.zxf.commons.json.exceptions.NonSupportedPlainException;

class ParsePlainUtils implements JsonConstant {

    public static Object valueOf( String plain ) throws NonSupportedPlainException {
        plain = plain.trim();
        Object res = TRUE.equalsIgnoreCase( plain ) ? true : null;
        if ( res == null )
            res = FALSE.equalsIgnoreCase( plain ) ? false : null;
        if ( STR_NULL.equalsIgnoreCase( plain ) ) {
            res = null;
        } else if ( res == null ) {
            try {
                res = Double.valueOf( plain );
            } catch ( NumberFormatException e1 ) {
                throw new NonSupportedPlainException( "不支持此类普通解析！plain: " + plain );
            }
        }
        return res;
    }

}
