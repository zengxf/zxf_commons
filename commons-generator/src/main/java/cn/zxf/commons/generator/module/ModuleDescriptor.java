package cn.zxf.commons.generator.module;

import lombok.Builder;
import lombok.ToString;

/**
 * 模块描述符
 * 
 * <p>
 * Created by zxf on 2017-05-09
 */
@Builder
@ToString
public class ModuleDescriptor {

    /** 基础包名 */
    public String  basicPackage;
    /** 模块包名 */
    public String  modulePackage;
    /** 模块实体类名 */
    public String  moduleEntityName;
    /** 模块实体类对应的数据库集合名 */
    public String  moduleEntityCollection;
    /** 基础路径 */
    public String  basicPath;
    /** 是否创建 enums 包 */
    public boolean createEnumsPackage;
    /** 是否创建 dto 包 */
    public boolean createDtoPackage;
    /** 模块注释 */
    public String  moduleComment;
    /** 模块作者 */
    public String  author;
    /** API 路径 */
    public String  apiPath;

}
