package cn.zxf.commons.json.commons;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Builder;

public class ClassFieldUtils {

    // static final int CAPACITY = 2; // used to test
    static final int                               CAPACITY = 100;
    static final AtomicInteger                     SIGN     = new AtomicInteger();
    static final ConcurrentMap<String, ClassField> CACHE    = new ConcurrentHashMap<>( CAPACITY );

    public static Set<Field> getFields( Class<?> clazz ) {
        String name = clazz.getName();
        ClassField cf = CACHE.computeIfAbsent( name, k -> {
            return ClassField.builder() //
                    .className( name )//
                    .fields( ReflectUtils.getFields( clazz ) )//
                    .index( SIGN.incrementAndGet() )//
                    .build();
        } );
        checkAndRemove();
        return cf.fields;
    }

    static void checkAndRemove() {
        if ( CACHE.size() > CAPACITY ) {
            int bound = SIGN.get() - CAPACITY;
            Iterator<ClassField> iter = CACHE.values()
                    .iterator();
            while ( iter.hasNext() ) {
                if ( iter.next().index <= bound )
                    iter.remove();
            }
        }
    }

    @Builder
    static class ClassField {
        String     className;
        Set<Field> fields;
        int        index;
    }

}
