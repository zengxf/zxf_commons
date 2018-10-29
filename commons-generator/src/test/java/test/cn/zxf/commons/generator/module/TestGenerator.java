package test.cn.zxf.commons.generator.module;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import cn.zxf.commons.generator.module.ModuleDescriptor;
import cn.zxf.commons.generator.module.ModuleGenerator;

public class TestGenerator {
    static String BASIC_PATH = "D:/test/jdk17/core";

    public static void main( String[] args ) {
        delAll();

        System.out.println( "BASIC_PATH: " + BASIC_PATH );

        ModuleDescriptor desc = ModuleDescriptor.builder()
                .moduleComment( "组归属时间" ) // 所有注释-主要标识
                .modulePackage( "objectives.team_belong" ) // 模块-子包名
                .moduleEntityName( "TeamBelong" ) // 实体类名
                .apiPath( "/api/team-belong" ) // API 接口 URL
                .moduleEntityCollection( "team_belong" ) // MongoDB 集合名
                .createDtoPackage( true ) // 是否创建 DTO 包

                .basicPath( BASIC_PATH )
                .author( "zengxf" )
                .basicPackage( "com.hunterplus.server.module" )
                .createEnumsPackage( false )
                .build();

        String descStr = desc.toString()
                .replaceFirst( "\\(", "\n\t " )
                .replaceAll( ",|\\)", "\n\t" )
                .replace( "=", " = " );
        System.out.println( descStr );

        ModuleGenerator.generate( desc );
    }

    static void delAll() {
        try {
            Path root = Paths.get( BASIC_PATH );

            if ( !Files.exists( root ) )
                return;

            Files.find( root, 2, //
                    ( path, fileAtt ) -> path.getFileName()
                            .toString()
                            .endsWith( ".java" ) )
                    .forEach( path -> {
                        try {
                            Files.delete( path );
                        } catch ( IOException e ) {
                            e.printStackTrace();
                        }
                    } );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

}
