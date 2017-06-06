package cn.zxf.commons.basic.utils;

/**
 * 异常帮助类
 * 
 * <p>
 * Created by zxf on 2017-06-05
 */
public class ExceptionUtil {

    /**
     * 将异常转换成字符串
     * 
     * @param ex
     * @return
     */
    public static String toString( Exception ex ) {
	if ( ex == null )
	    return null;

	StringBuilder sb = new StringBuilder();
	sb.append( ex ).append( "\n" );
	StackTraceElement[] arr = ex.getStackTrace();
	for ( StackTraceElement e : arr ) {
	    sb.append( "\tat " + e ).append( "\n" );
	}
	append( sb, ex.getCause(), arr );
	return sb.toString();
    }

    static void append( StringBuilder sb, Throwable ourCause, StackTraceElement[] enclosingTrace ) {
	if ( ourCause == null ) {
	    return;
	}

	Throwable ex = ourCause;
	StackTraceElement[] trace = ex.getStackTrace();

	int m = trace.length - 1;
	int n = enclosingTrace.length - 1;
	while ( m >= 0 && n >= 0 && trace[m].equals( enclosingTrace[n] ) ) {
	    m--;
	    n--;
	}
	int framesInCommon = trace.length - 1 - m;

	sb.append( "Caused by: " ).append( ex ).append( "\n" );
	for ( int i = 0; i <= m; i++ )
	    sb.append( "\tat " + trace[i] ).append( "\n" );
	if ( framesInCommon != 0 )
	    sb.append( "\t... " + framesInCommon + " more" ).append( "\n" );

	Throwable our = ex.getCause();
	append( sb, our, trace );
    }

}
