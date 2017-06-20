package cn.zxf.commons.basic.utils;

/**
 * 枚举帮助类
 * <p>
 * Created by zengxf on 2017/6/16.
 */
public class EnumUtil {

    /**
     * 枚举接口
     */
    public interface IEnum {
	int getCode();
    }

    /**
     * 获取枚举
     *
     * @param es
     * @param status
     * @param def
     * @param <T>
     * @return
     */
    public static < T extends IEnum > T getEnum( T[] es, Integer status, T def ) {
	if ( status == null )
	    return def;
	for ( T e : es ) {
	    if ( e.getCode() == status )
		return e;
	}
	return def;
    }

}
