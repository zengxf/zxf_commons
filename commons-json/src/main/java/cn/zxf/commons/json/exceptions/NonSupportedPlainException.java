package cn.zxf.commons.json.exceptions;

/**
 * 不支持的普通表达式的值异常
 * <p>
 * Created by zengxf on 2017-10-19
 */
@SuppressWarnings( "serial" )
public class NonSupportedPlainException extends Exception {

    public NonSupportedPlainException( String msg ) {
        super( msg );
    }

}
