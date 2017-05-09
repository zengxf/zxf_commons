package test.cn.zxf.commons.generator.module;

import cn.zxf.commons.generator.module.ModuleDescriptor;
import cn.zxf.commons.generator.module.ModuleGenerator;

public class TestGenerator {
    static String BASIC_PATH = "D:/test/jdk17/core";

    public static void main( String[] args ) {
	ModuleDescriptor desc = ModuleDescriptor.builder()//
	        .basicPath( BASIC_PATH )//
	        .author( "zengxf" )//
	        .basicPackage( "com.hunterplus.server" )//
	        .modulePackage( "subsystems.morning_news.news" )//
	        .moduleEntityName( "MorningNews" )//
	        .moduleComment( "早报" )//
	        .apiPath( "/api/morning_news" )//
	        .moduleEntityCollection( "morning_news" )//
	        .createDtoPackage( true )//
	        .createEnumsPackage( true )//
	        .build();

	String descStr = desc.toString().replaceFirst( "\\(", "\n\t " ).replaceAll( ",|\\)", "\n\t" ).replace( "=", " = " );
	System.out.println( descStr );

	ModuleGenerator.generate( desc );
    }

}
