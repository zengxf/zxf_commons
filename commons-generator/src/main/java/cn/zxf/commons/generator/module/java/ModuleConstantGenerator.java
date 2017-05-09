package cn.zxf.commons.generator.module.java;

import cn.zxf.commons.generator.module.AbstractClassGenerator;
import cn.zxf.commons.generator.module.ClassTypeEnum;
import cn.zxf.commons.generator.module.ModuleDescriptor;

/**
 * 接口常量 生成器
 * 
 * <p>
 * Created by zxf on 2017-05-09
 */
public class ModuleConstantGenerator extends AbstractClassGenerator {

    private static final String CONTENT_FORMAT = "package #package#;\n" + //
            "\n" + //
            "/**\n" + //
            " * #comment# \n" + //
            " * <p>\n" + //
            " * Created by #author# on #date#.\n" + //
            " */\n" + //
            "interface #class_name# {\n" + //
            "\n" + //
            "    \n" + //
            "\n" + //
            "}\n";

    protected ModuleConstantGenerator( ModuleDescriptor desc ) {
	super( ClassTypeEnum.CONSTANT, desc );
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
    public static ModuleConstantGenerator of( ModuleDescriptor desc ) {
	return new ModuleConstantGenerator( desc );
    }

}
