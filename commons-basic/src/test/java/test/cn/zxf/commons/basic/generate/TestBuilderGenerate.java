package test.cn.zxf.commons.basic.generate;

import cn.zxf.commons.basic.generate.BuilderGenerate;

public class TestBuilderGenerate {

    public static void main( String[] args ) {
	String javacode = BuilderGenerate.generateJavaCode( UserBo.class );
	System.out.println( javacode );
    }

}
