package cn.zxf.commons.generator.module;

public enum ClassTypeEnum {

    ENTITY( "", "实体类"), //
    MONGO_DAO( "MongoDao", "Mongo DAO"), //
    SERVICE( "Service", "服务类"), //
    CONTROLLER( "Controller", "控制器"), //
    CONSTANT( "Constant", "常量接口"), //
    DTO_FACTORY( "DtoFactory", "Dto 工厂帮助类", "/dto"), //
    ENUM_TYPE( "TypeEnum", "类型枚举类", "/enums"), //
    ENUM_STATUS( "StatusEnum", "状态枚举类", "/enums"), //

    ;

    /** 类名后缀 */
    public final String classSuffix;
    /** 类注释后缀 */
    public final String commentSuffix;
    /** 应该保存的相对位置的父路径 */
    public final String parentPath;

    private ClassTypeEnum( String classSuffix, String commentSuffix ) {
        this( classSuffix, commentSuffix, "" );
    }

    private ClassTypeEnum( String classSuffix, String commentSuffix, String parentPath ) {
        this.classSuffix = classSuffix;
        this.commentSuffix = commentSuffix;
        this.parentPath = parentPath;
    }

}
