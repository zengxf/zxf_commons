package cn.zxf.commons.generator.module.java;

import cn.zxf.commons.generator.module.AbstractClassGenerator;
import cn.zxf.commons.generator.module.ClassTypeEnum;
import cn.zxf.commons.generator.module.ModuleDescriptor;

/**
 * Dao 生成器
 * 
 * <p>
 * Created by zxf on 2017-05-09
 */
public class ModuleDaoGenerator extends AbstractClassGenerator {

    private static final String CONTENT_FORMAT = "package #package#;\n" + //
            "\n" + //
            "import hunterplus.mongo.CommonMongoDao;\n" + //
            "import org.springframework.stereotype.Component;\n" + //
            "\n" + //
            "/**\n" + //
            " * #comment# \n" + //
            " * <p>\n" + //
            " * Created by #author# on #date#.\n" + //
            " */\n" + //
            "@Component\n" + //
            "public class #class_name# extends CommonMongoDao<#module#> implements #module#Constant {\n" + //
            "\n" + //
            "    private static final int PAGE_SIZE = 20;\n" + //
            "\n" + //
            "    public #class_name#() {\n" + //
            "        super(#module#.class);\n" + //
            "    }\n" + //
            "\n" + //
            "}\n";

    protected ModuleDaoGenerator( ModuleDescriptor desc ) {
        super( ClassTypeEnum.MONGO_DAO, desc );
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
    public static ModuleDaoGenerator of( ModuleDescriptor desc ) {
        return new ModuleDaoGenerator( desc );
    }

}
