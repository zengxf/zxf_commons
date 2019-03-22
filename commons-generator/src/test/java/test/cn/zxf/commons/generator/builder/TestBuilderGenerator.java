package test.cn.zxf.commons.generator.builder;

import cn.zxf.commons.generator.builder.BuilderGenerator;

public class TestBuilderGenerator {

    public static void main( String[] args ) {
        String javacode = BuilderGenerator.generateJavaCode( UserBo.class );
        System.out.println( javacode );
    }

}
