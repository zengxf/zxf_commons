package cn.zxf.commons.generator.module.java;

import cn.zxf.commons.generator.module.AbstractClassGenerator;
import cn.zxf.commons.generator.module.ClassTypeEnum;
import cn.zxf.commons.generator.module.ModuleDescriptor;

/**
 * 服务类 生成器
 * 
 * <p>
 * Created by zxf on 2017-05-09
 */
public class ModuleServiceGenerator extends AbstractClassGenerator {

    private static final String CONTENT_FORMAT = "package #package#;\n" + //
            "\n" + //
            "import htp.midaner.commonweb.AbstractService;\n" + //
            "import org.springframework.beans.factory.annotation.Autowired;\n" + //
            "import org.springframework.stereotype.Component;\n" + //
            "import org.springframework.validation.annotation.Validated;\n" + //
            "\n" + //
            "/**\n" + //
            " * #comment# \n" + //
            " * <p>\n" + //
            " * Created by #author# on #date#.\n" + //
            " */\n" + //
            "@Validated\n" + //
            "@Component\n" + //
            "public class #class_name# extends AbstractService {\n" + //
            "\n" + //
            "    private static final String PREFIX = \"xx\";\n" + //
            "\n" + //
            "    @Autowired\n" + //
            "    private #module#Dao dao;\n" + //
            "\n" + //
            "}\n";

    protected ModuleServiceGenerator( ModuleDescriptor desc ) {
	super( ClassTypeEnum.SERVICE, desc );
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
    public static ModuleServiceGenerator of( ModuleDescriptor desc ) {
	return new ModuleServiceGenerator( desc );
    }

}
