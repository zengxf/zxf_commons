package cn.zxf.commons.generator.module.java;

import cn.zxf.commons.generator.module.AbstractClassGenerator;
import cn.zxf.commons.generator.module.ClassTypeEnum;
import cn.zxf.commons.generator.module.ModuleDescriptor;

/**
 * 实体类 生成器
 * 
 * <p>
 * Created by zxf on 2017-05-09
 */
public class ModuleEntityGenerator extends AbstractClassGenerator {

    private static final String CONTENT_FORMAT = "package #package#;\n" + //
            "\n" + //
            "import hunterplus.mongo.MongoObject;\n" + //
            "import lombok.Data;\n" + //
            "import org.springframework.data.annotation.Id;\n" + //
            "import org.springframework.data.mongodb.core.mapping.Document;\n" + //
            "\n" + //
            "/**\n" + //
            " * #comment# \n" + //
            " * <p>\n" + //
            " * Created by #author# on #date#.\n" + //
            " */\n" + //
            "@Data\n" + //
            "@Document(collection = \"#collection#\")\n" + //
            "public class #class_name# implements MongoObject, #module#Constant {\n" + //
            "\n" + //
            "    @Id\n" + //
            "    private String id;\n" + //
            "\n" + //
            "}\n";

    protected ModuleEntityGenerator( ModuleDescriptor desc ) {
        super( ClassTypeEnum.ENTITY, desc );
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
    public static ModuleEntityGenerator of( ModuleDescriptor desc ) {
        return new ModuleEntityGenerator( desc );
    }

}
