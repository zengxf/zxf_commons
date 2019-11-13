package cn.zxf.commons.json.commons;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;

public class ReflectUtils {

    public static Set<Field> getFields( Class<?> clazz ) {
        Set<Field> temp = new LinkedHashSet<>();

        while ( clazz != Object.class ) {
            Field[] fields = clazz.getDeclaredFields();
            for ( Field field : fields ) {
                int modifier = field.getModifiers();
                boolean isStatic = Modifier.isStatic( modifier );
                boolean isFinal = Modifier.isFinal( modifier );
                if ( !isStatic && !isFinal ) {
                    temp.add( field );
                }
            }
            clazz = clazz.getSuperclass(); // 递归
        }

        return temp;
    }

}
