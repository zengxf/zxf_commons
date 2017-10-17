package test.cn.zxf.commons.generator.module;

import cn.zxf.commons.generator.module.ModuleDescriptor;
import cn.zxf.commons.generator.module.ModuleGenerator;

public class TestGenerator {
    static String BASIC_PATH = "D:/test/jdk17/core";

    public static void main( String[] args ) {
        ModuleDescriptor desc = ModuleDescriptor.builder()//
                .basicPath( BASIC_PATH )//
                .author( "zengxf" )//
                .basicPackage( "com.hunterplus.data" )//
                .modulePackage( "domain.funnel.data" )//
                .moduleEntityName( "Funnel" )//
                .moduleComment( "漏斗分析" )//
                .apiPath( "/api/v2/funnel" )//
                .moduleEntityCollection( "funnel" )//
                .createDtoPackage( false )//
                .createEnumsPackage( false )//
                .build();

        String descStr = desc.toString().replaceFirst( "\\(", "\n\t " ).replaceAll( ",|\\)", "\n\t" ).replace( "=", " = " );
        System.out.println( descStr );

        ModuleGenerator.generate( desc );
    }

}
