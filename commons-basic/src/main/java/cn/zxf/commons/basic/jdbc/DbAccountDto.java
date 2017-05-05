package cn.zxf.commons.basic.jdbc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DbAccountDto {

    /**
     * JDBC-驱动类名
     */
    private String driver;
    /**
     * 数据库-账号
     */
    private String user;
    /**
     * 数据库-密码
     */
    private String password;
    /**
     * 数据库-连接URL
     */
    private String url;

}
