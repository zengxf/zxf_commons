package cn.zxf.commons.json.parse.transformer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.zxf.commons.json.parse.transformer.impl.BooleanTf;
import cn.zxf.commons.json.parse.transformer.impl.DoubleTf;
import cn.zxf.commons.json.parse.transformer.impl.IntegerTf;
import cn.zxf.commons.json.parse.transformer.impl.MapTf;
import cn.zxf.commons.json.parse.transformer.impl.SetTf;
import cn.zxf.commons.json.parse.transformer.impl.StringTf;

public class TransformerFactor {

    @SuppressWarnings( "rawtypes" )
    static Map<Class, Transformer> primitiveMap = new HashMap<>();
    @SuppressWarnings( "rawtypes" )
    static Map<Class, Transformer> objMap       = new HashMap<>();

    static {
        primitiveMap.put( boolean.class, new BooleanTf() );
        primitiveMap.put( int.class, new IntegerTf() );
        primitiveMap.put( double.class, new DoubleTf() );
        primitiveMap.put( Boolean.class, new BooleanTf() );
        primitiveMap.put( Integer.class, new IntegerTf() );
        primitiveMap.put( Double.class, new DoubleTf() );
        primitiveMap.put( String.class, new StringTf() );

        objMap.put( Set.class, new SetTf() );
        objMap.put( Map.class, new MapTf() );
    }

    @SuppressWarnings( "rawtypes" )
    public static Transformer<?> get( Class<?> clazz ) {
        Transformer tf = primitiveMap.get( clazz );
        if ( tf != null )
            return tf;

        tf = objMap.get( clazz );
        if ( tf != null )
            return tf;

        return null;
    }

}
