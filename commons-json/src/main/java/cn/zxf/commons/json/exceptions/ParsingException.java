package cn.zxf.commons.json.exceptions;

@SuppressWarnings( "serial" )
public class ParsingException extends RuntimeException {

    public ParsingException( String message ) {
        super( message );
    }

    public ParsingException( String message, Throwable cause ) {
        super( message, cause );
    }

}
