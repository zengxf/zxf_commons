package cn.zxf.commons.basic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

/**
 * JDBC 连接构造器
 * 
 * <p>
 * Created by zxf on 2017-04-14
 */
@Slf4j
public class ConnectionBudler {
    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    /**
     * 获取连接(同一个线程共用)
     * 
     * @return
     */
    public static Connection get() {
	return local.get();
    }

    /**
     * 获取连接(同一个线程共用)
     * 
     * @return
     */
    public static Connection getAndInit( DbAccountDto dto ) {
	Connection conn = local.get();
	if ( conn == null ) {
	    initLocalConnection( dto );
	}
	return local.get();
    }

    /**
     * 获取本地连接
     * 
     * @return
     */
    private static void initLocalConnection( DbAccountDto dto ) {
	try {
	    Class.forName( dto.getDriver() );
	    Connection conn = DriverManager.getConnection( dto.getUrl(), dto.getUser(), dto.getPassword() );
	    local.set( conn );
	} catch ( SQLException e ) {
	    log.error( "获取连接失败！", e );
	} catch ( ClassNotFoundException e ) {
	    log.error( "没有找到相应的驱动类，获取连接失败！", e );
	}
    }

}
