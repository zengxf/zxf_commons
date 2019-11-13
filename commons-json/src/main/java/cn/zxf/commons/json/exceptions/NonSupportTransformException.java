package cn.zxf.commons.json.exceptions;

@SuppressWarnings( "serial" )
public class NonSupportTransformException extends RuntimeException {

    public NonSupportTransformException( String message ) {
        super( message );
    }

    public NonSupportTransformException( String message, Throwable cause ) {
        super( message, cause );
    }

}
