package cn.zxf.commons.json.exceptions;

@SuppressWarnings( "serial" )
public class GetTransformerException extends RuntimeException {

    public GetTransformerException( String message ) {
        super( message );
    }

    public GetTransformerException( String message, Throwable cause ) {
        super( message, cause );
    }

}
