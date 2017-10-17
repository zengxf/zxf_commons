package cn.zxf.commons.generator.module;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import cn.zxf.commons.generator.module.java.ModuleConstantGenerator;
import cn.zxf.commons.generator.module.java.ModuleControllerGenerator;
import cn.zxf.commons.generator.module.java.ModuleDaoGenerator;
import cn.zxf.commons.generator.module.java.ModuleDtoFactoryGenerator;
import cn.zxf.commons.generator.module.java.ModuleEntityGenerator;
import cn.zxf.commons.generator.module.java.ModuleEnumStatusGenerator;
import cn.zxf.commons.generator.module.java.ModuleEnumTypeGenerator;
import cn.zxf.commons.generator.module.java.ModuleServiceGenerator;
import lombok.extern.slf4j.Slf4j;

/**
 * 模块生成器
 * 
 * <p>
 * Created by zxf on 2017-05-09
 */
@Slf4j
public class ModuleGenerator {

    public static void generate( ModuleDescriptor desc ) {
        createDtoPackage( desc );
        createEnumsPackage( desc );

        ModuleDaoGenerator.of( desc ).generateFile();
        ModuleServiceGenerator.of( desc ).generateFile();
        ModuleControllerGenerator.of( desc ).generateFile();
        ModuleConstantGenerator.of( desc ).generateFile();
        ModuleEntityGenerator.of( desc ).generateFile();
    }

    static void createDtoPackage( ModuleDescriptor desc ) {
        if ( desc.createDtoPackage ) {
            Path path = Paths.get( desc.basicPath, ClassTypeEnum.DTO_FACTORY.parentPath );
            try {
                Files.createDirectories( path );
                ModuleDtoFactoryGenerator.of( desc ).generateFile();
            } catch ( IOException e ) {
                log.error( "创建 dto 包时出错！", e );
            }
        }
    }

    static void createEnumsPackage( ModuleDescriptor desc ) {
        if ( desc.createEnumsPackage ) {
            Path path = Paths.get( desc.basicPath, ClassTypeEnum.ENUM_TYPE.parentPath );
            try {
                Files.createDirectories( path );
                ModuleEnumTypeGenerator.of( desc ).generateFile();
                ModuleEnumStatusGenerator.of( desc ).generateFile();
            } catch ( IOException e ) {
                log.error( "创建 enums 包时出错！", e );
            }
        }
    }

}
