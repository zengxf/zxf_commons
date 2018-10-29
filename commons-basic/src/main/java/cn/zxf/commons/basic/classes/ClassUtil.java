package cn.zxf.commons.basic.classes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

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
            clazz = clazz.getSuperclass(); // 获取父类的字段
        }

        return temp;
    }

}
