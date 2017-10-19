package cn.zxf.commons.json.parse.transformer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

import cn.zxf.commons.json.exceptions.GetTransformerException;
import cn.zxf.commons.json.parse.transformer.impl.BooleanTf;
import cn.zxf.commons.json.parse.transformer.impl.DoubleTf;
import cn.zxf.commons.json.parse.transformer.impl.IntegerTf;

public class GetTransformerUtils {

    @SuppressWarnings( "rawtypes" )
    static ConcurrentMap<Class, Transformer> map = new ConcurrentHashMap<>();

    static {
        map.put( boolean.class, new BooleanTf() );
        map.put( int.class, new IntegerTf() );
        map.put( double.class, new DoubleTf() );
    }

    @SuppressWarnings( "rawtypes" )
    public static Transformer<?> get( Class<?> clazz ) {
        Transformer tf = map.get( clazz );
        if ( tf != null )
            return tf;
        Class<? extends Transformer<?>> c = Stream.of( TransEnum.values() ) //
                .filter( e -> e.target == clazz ) //
                .findFirst().map( e -> e.transformer ) //
                .orElse( null );
        if ( c == null )
            return null;
        try {
            tf = c.newInstance();
            map.put( clazz, tf );
            return tf;
        } catch ( InstantiationException | IllegalAccessException e1 ) {
            throw new GetTransformerException( "获取[" + clazz + "]转换器实例失败", e1 );
        }
    }

}
