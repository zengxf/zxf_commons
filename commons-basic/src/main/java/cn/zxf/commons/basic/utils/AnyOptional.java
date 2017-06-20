package cn.zxf.commons.basic.utils;

import java.util.function.Consumer;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;

/**
 * 防空指针的任一对象帮助类
 * 
 * <p>
 * Created by zxf on 2017-06-12
 */
@AllArgsConstructor
public class AnyOptional< T > {

    public final T   v;
    public final T[] nonOpVs; // 不能操作的值

    public static < T > AnyOptional<T> of( T t ) {
	return new AnyOptional<T>( t, null );
    }

    @SafeVarargs
    public static < T > AnyOptional<T> of( T t, T... nonOpVs ) {
	return new AnyOptional<T>( t, nonOpVs );
    }

    /**
     * 提供流式操作<br>
     * 注：如果值是 String 类型，会用 isNotEmpty 判断<br>
     * 不要 <code>criteria.and(FIELD_COMPANY_ID)::is</code> 这样操作，会加条件进去的
     *
     * @param consumer
     * @return
     */
    public AnyOptional<T> op( Consumer<T> consumer ) {
	if ( v != null ) {
	    if ( nonOpVs != null && nonOpVs.length > 0 ) {
		boolean nonOp = Stream.of( nonOpVs ).anyMatch( t -> v.equals( t ) );
		if ( nonOp ) {
		    return this;
		}
	    }
	    if ( v instanceof String ) {
		String str = (String) v;
		if ( str == null || str.length() == 0 ) {
		    consumer.accept( v );
		}
	    } else {
		consumer.accept( v );
	    }
	}
	return this;
    }

}
