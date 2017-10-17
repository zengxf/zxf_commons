package cn.zxf.commons.basic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassUtil {

    public static Set<Field> getFields( Class<?> clazz ) {
        Set<Field> temp = new HashSet<>();

        while ( clazz != Object.class ) {
            Field[] fields = clazz.getDeclaredFields();
            for ( Field field : fields ) {
                String name = field.getName();
                int modifier = field.getModifiers();
                boolean isField = name.matches( "[a-z][a-zA-Z0-9]*" );
                if ( isField ) {
                    boolean isStatic = Modifier.isStatic( modifier );
                    boolean isFinal = Modifier.isFinal( modifier );
                    if ( !isStatic && !isFinal ) {
                        temp.add( field );
                    }
                }
            }
            clazz = clazz.getSuperclass(); // 递归
        }

        return temp;
    }

    public static List<Method> getSetMethods( Class<?> clazz ) {
        List<Method> list = new ArrayList<>();
        while ( clazz != Object.class ) {
            Method[] ms = clazz.getDeclaredMethods();
            List<Method> temp = Arrays.stream( ms ) //
                    .filter( method -> method.getName().startsWith( "set" ) ) //
                    .collect( Collectors.toList() );
            list.addAll( temp );
            clazz = clazz.getSuperclass();
        }
        return list;
    }

}
