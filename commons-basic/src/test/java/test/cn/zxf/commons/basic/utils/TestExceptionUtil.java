package test.cn.zxf.commons.basic.utils;

import java.io.IOException;

import cn.zxf.commons.basic.utils.ExceptionUtil;

public class TestExceptionUtil {

    static void aa() throws IOException {
	throw new IOException( "file exists" );
    }

    static void bb() throws Exception {
	try {
	    aa();
	} catch ( IOException e ) {
	    throw new Exception( e );
	}
    }

    static void test() {
	Exception ex = null;
	try {
	    bb();
	} catch ( Exception e1 ) {
	    ex = e1;
	}

	ex.printStackTrace();

	System.out.println( "--------------" );
	System.out.println( "--------------" );

	String sb = ExceptionUtil.toString( ex );
	System.out.println( sb );
    }

    public static void main( String[] args ) throws IOException {
	test();
    }

}
