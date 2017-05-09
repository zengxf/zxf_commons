package cn.zxf.commons.generator.module.java;

import cn.zxf.commons.generator.module.AbstractClassGenerator;
import cn.zxf.commons.generator.module.ClassTypeEnum;
import cn.zxf.commons.generator.module.ModuleDescriptor;

/**
 * 控制器 生成器
 * 
 * <p>
 * Created by zxf on 2017-05-09
 */
public class ModuleControllerGenerator extends AbstractClassGenerator {

    private static final String CONTENT_FORMAT = "package #package#;\n" + //
            "\n" + //
            "import com.hunterplus.server.basic.controller.BasicController;\n" + //
            "import org.springframework.beans.factory.annotation.Autowired;\n" + //
            "import org.springframework.web.bind.annotation.RequestMapping;\n" + //
            "import org.springframework.web.bind.annotation.RestController;\n" + //
            "\n" + //
            "/**\n" + //
            " * #comment# \n" + //
            " * <p>\n" + //
            " * Created by #author# on #date#.\n" + //
            " */\n" + //
            "@RestController\n" + //
            "@RequestMapping(\"#api_path#\")\n" + //
            "public class #class_name# {\n" + //
            "\n" + //
            "    @Autowired\n" + //
            "    private #module#Service service;\n" + //
            "\n" + //
            "}\n";

    protected ModuleControllerGenerator( ModuleDescriptor desc ) {
	super( ClassTypeEnum.CONTROLLER, desc );
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
    public static ModuleControllerGenerator of( ModuleDescriptor desc ) {
	return new ModuleControllerGenerator( desc );
    }

}
