package cn.zxf.commons.json.exceptions;

@SuppressWarnings( "serial" )
public class GetFormaterException extends RuntimeException {

    public GetFormaterException( String message ) {
        super( message );
    }

    public GetFormaterException( String message, Throwable cause ) {
        super( message, cause );
    }

}
