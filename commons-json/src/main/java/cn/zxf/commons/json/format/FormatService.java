package cn.zxf.commons.json.format;

import cn.zxf.commons.json.commons.JsonConstant;
import cn.zxf.commons.json.exceptions.GetFormaterException;
import cn.zxf.commons.json.format.impl.CustomBeanFormater;

public class FormatService extends FormaterUtils implements JsonConstant {

    public static String format( Object obj ) {
        if ( obj == null )
            return NULL_STRING;
        Class<?> clazz = obj.getClass();
        return getFormater( clazz ).format( obj );
    }

    static Formater getFormater( Class<?> clazz ) {
        Formater fmt = defFormater( clazz );
        if ( isNonNull( fmt ) )
            return fmt;

        fmt = javaLangFormater( clazz );
        if ( isNonNull( fmt ) )
            return fmt;

        if ( clazz.getName().startsWith( "java." ) )
            throw new GetFormaterException( clazz + " 没有找到格式器" );

        return new CustomBeanFormater();
    }

}
