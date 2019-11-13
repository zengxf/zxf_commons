package cn.zxf.commons.json.exceptions;

@SuppressWarnings( "serial" )
public class JsonFormatException extends RuntimeException {

    public JsonFormatException( String message ) {
        super( message );
    }

    public JsonFormatException( String message, Throwable cause ) {
        super( message, cause );
    }

}
