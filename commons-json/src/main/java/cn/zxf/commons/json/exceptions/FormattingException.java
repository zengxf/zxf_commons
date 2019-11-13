package cn.zxf.commons.json.exceptions;

@SuppressWarnings( "serial" )
public class FormattingException extends RuntimeException {

    public FormattingException( String message ) {
        super( message );
    }

    public FormattingException( String message, Throwable cause ) {
        super( message, cause );
    }

}
