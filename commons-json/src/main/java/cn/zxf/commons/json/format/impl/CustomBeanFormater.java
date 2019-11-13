package cn.zxf.commons.json.format.impl;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import cn.zxf.commons.json.commons.ClassFieldUtils;
import cn.zxf.commons.json.exceptions.FormattingException;
import cn.zxf.commons.json.format.FormatService;
import cn.zxf.commons.json.format.Formater;

/**
 * 用户自定义的 Bean 格式器
 * 
 * <p>
 * Created by zengxf on 2017-10-17
 */
public class CustomBeanFormater implements Formater {

    @Override
    public String format( Object obj ) {
        Class<?> clazz = obj.getClass();
        Set<Field> fields = ClassFieldUtils.getFields( clazz );
        String content = fields.stream() //
                .map( f -> {
                    f.setAccessible( true );
                    try {
                        Object v = f.get( obj );
                        if ( v == null )
                            return null;
                        String k = f.getName();
                        return FormatService.format( k ) + ": " + FormatService.format( v );
                    } catch ( IllegalArgumentException | IllegalAccessException e ) {
                        throw new FormattingException( "提取自定义 Bean 的字段值时出错", e );
                    }
                } ) //
                .filter( Objects::nonNull ) //
                .collect( Collectors.joining( ", " ) );
        return "{" + content + "}";
    }

}
