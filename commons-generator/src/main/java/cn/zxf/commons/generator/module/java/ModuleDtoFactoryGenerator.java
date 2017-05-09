package cn.zxf.commons.generator.module.java;

import cn.zxf.commons.generator.module.AbstractClassGenerator;
import cn.zxf.commons.generator.module.ClassTypeEnum;
import cn.zxf.commons.generator.module.ModuleDescriptor;

/**
 * Dto工厂帮助类 生成器
 * 
 * <p>
 * Created by zxf on 2017-05-09
 */
public class ModuleDtoFactoryGenerator extends AbstractClassGenerator {

    private static final String CONTENT_FORMAT = "package #package#.dto;\n" + //
            "\n" + //
            "/**\n" + //
            " * #comment# \n" + //
            " * <p>\n" + //
            " * Created by #author# on #date#.\n" + //
            " */\n" + //
            "public class #class_name# {\n" + //
            "\n" + //
            "    \n" + //
            "\n" + //
            "}\n";

    protected ModuleDtoFactoryGenerator( ModuleDescriptor desc ) {
	super( ClassTypeEnum.DTO_FACTORY, desc );
    }

    @Override
    protected String getClassFormat() {
	return CONTENT_FORMAT;
    }

    /**
     * 工厂方法
     * 
     * @param desc
     * @return
     */
    public static ModuleDtoFactoryGenerator of( ModuleDescriptor desc ) {
	return new ModuleDtoFactoryGenerator( desc );
    }

}
