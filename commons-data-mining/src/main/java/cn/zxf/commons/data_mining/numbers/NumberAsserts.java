package cn.zxf.commons.data_mining.numbers;

/**
 * 断言帮助类
 * 
 * <p>
 * Created by zengxf on 2017-11-06
 */
public class NumberAsserts {

    public static void assertArrayNotNull( double[] arr ) {
        if ( arr == null || arr.length == 0 )
            fail( "数组不能为空！" );
    }

    public static void assertVectorNotNull( VectorVo vo ) {
        if ( vo == null || vo.getLen() == 0 )
            fail( "向量不能为空！" );
    }

    public static void assertArrayLen( double[] arr1, double[] arr2 ) {
        assertArrayNotNull( arr1 );
        assertArrayNotNull( arr2 );
        if ( arr1.length != arr2.length )
            fail( "两数组长度不一致！" );
    }

    public static void assertVectorLen( VectorVo v1, VectorVo v2 ) {
        assertVectorNotNull( v1 );
        assertVectorNotNull( v2 );
        if ( v1.getLen() != v2.getLen() )
            fail( "两向量长度不一致！" );
    }

    public static void assertEq( double expect, double fact ) {
        if ( fact != expect )
            fail( String.format( "实际值不对！expect: %f, fact: %f", expect, fact ) );
    }

    // -------------

    static void fail( String msg ) {
        throw exception( msg );
    }

    static NumberAssertsException exception( String msg ) {
        return new NumberAssertsException( msg );
    }

    public static class NumberAssertsException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public NumberAssertsException( String message ) {
            super( message );
        }
    }

}
