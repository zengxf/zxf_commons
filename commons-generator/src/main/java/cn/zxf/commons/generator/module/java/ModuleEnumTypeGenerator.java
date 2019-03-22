package cn.zxf.commons.generator.module.java;

import cn.zxf.commons.generator.module.AbstractClassGenerator;
import cn.zxf.commons.generator.module.ClassTypeEnum;
import cn.zxf.commons.generator.module.ModuleDescriptor;

/**
 * 类型枚举类 生成器
 * 
 * <p>
 * Created by zxf on 2017-05-09
 */
public class ModuleEnumTypeGenerator extends AbstractClassGenerator {

    private static final String CONTENT_FORMAT = "package #package#.enums;\n" + //
            "\n" + //
            "import lombok.AllArgsConstructor;\n" + //
            "import lombok.Getter;\n" + //
            "\n" + //
            "/**\n" + //
            " * #comment# \n" + //
            " * <p>\n" + //
            " * Created by #author# on #date#.\n" + //
            " */\n" + //
            "@AllArgsConstructor\n" + //
            "public enum #class_name# {\n" + //
            "\n" + //
            "    ERROR(-1, \"类型错误\"), //\n" + //
            "    XXX(1, \"XXX\"), //\n" + //
            "    ;\n" + //
            "\n" + //
            "    @Getter\n" + //
            "    public final int code;\n" + //
            "    public final String desc;\n" + //
            "\n" + //
            "}\n";

    protected ModuleEnumTypeGenerator( ModuleDescriptor desc ) {
        super( ClassTypeEnum.ENUM_TYPE, desc );
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
    public static ModuleEnumTypeGenerator of( ModuleDescriptor desc ) {
        return new ModuleEnumTypeGenerator( desc );
    }

}
