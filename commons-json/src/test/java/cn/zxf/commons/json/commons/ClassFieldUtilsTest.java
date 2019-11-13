package cn.zxf.commons.json.commons;

public class ClassFieldUtilsTest {

    public static void main( String[] args ) throws InterruptedException {
        Runnable r = () -> {
            ClassFieldUtils.getFields( A.class );
            ClassFieldUtils.getFields( B.class );
            ClassFieldUtils.getFields( C.class );
            ClassFieldUtils.getFields( D.class );
            ClassFieldUtils.getFields( D1.class );
            ClassFieldUtils.getFields( D2.class );
            ClassFieldUtils.getFields( D3.class );
        };
        Thread t1 = new Thread( r );
        Thread t2 = new Thread( r );
        Thread t3 = new Thread( r );
        t1.start();
        t2.start();
        t3.start();

        System.out.println( "size: " + ClassFieldUtils.CACHE.size() );
        t1.join();
        t2.join();
        t3.join();
        System.out.println( "size: " + ClassFieldUtils.CACHE.size() );

        System.out.println( "OK" );
    }

    static class A {
    }

    static class B {
    }

    static class C {
    }

    static class D {
    }

    static class D1 {
    }

    static class D2 {
    }

    static class D3 {
    }

}
