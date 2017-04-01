package cn.zxf.commons.basic.utils;

/**
 * 扩展 OptionalInt 实现自己业务需求
 * 
 * @author zxf
 */
public class MyOptionalInt {

    private Integer value;

    public MyOptionalInt( Integer value ) {
	this.value = value;
    }

    public boolean is( Integer specify ) {
	if ( value == null )
	    return specify == null;
	if ( specify == null )
	    return false;
	return value.intValue() == specify.intValue();
    }

    public boolean ne( Integer specify ) {
	return !this.is( specify );
    }

    public boolean isNullOrIt( Integer specify ) {
	if ( value == null )
	    return true;
	if ( specify == null )
	    return false;
	return value.intValue() == specify.intValue();
    }

    public boolean in( Integer... arr ) {
	for ( Integer v : arr ) {
	    if ( value == v )
		return true;
	}
	return false;
    }

    public boolean nin( Integer... arr ) {
	return !in( arr );
    }

    public int get( int def ) {
	return isNull() ? def : value.intValue();
    }

    public boolean isNull() {
	return value == null;
    }

    public static MyOptionalInt of( Integer value ) {
	return new MyOptionalInt( value );
    }

}
