package cn.zxf.commons.json.exceptions;

@SuppressWarnings( "serial" )
public class TransformingException extends RuntimeException {

    public TransformingException( String message ) {
        super( message );
    }

    public TransformingException( String message, Throwable cause ) {
        super( message, cause );
    }

}
