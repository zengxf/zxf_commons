package cn.zxf.commons.basic.utils;

import java.sql.Timestamp;

import javax.script.Bindings;

/**
 * 获取 JS 对象的值
 * 
 * <p>
 * Created by zxf on 2017-04-19
 */
public class GetJsValueUtil {

    public static int getInt( Bindings jsObj, String key ) {
	Object value = jsObj.get( key );
	if ( value instanceof String ) {
	    return Integer.valueOf( value.toString() );
	}
	return (int) value;
    }

    public static float getFloat( Bindings jsObj, String key ) {
	Object value = jsObj.get( key );
	if ( value instanceof String ) {
	    return Float.parseFloat( value.toString() );
	}
	return (float) value;
    }

    public static String getString( Bindings jsObj, String key ) {
	return (String) jsObj.get( key );
    }

    public static Timestamp getDate( Bindings jsObj, String key ) {
	return Timestamp.valueOf( (String) jsObj.get( key ) );
    }

    public static Bindings getJsObject( Bindings jsObj, String key ) {
	return (Bindings) jsObj.get( key );
    }

}
