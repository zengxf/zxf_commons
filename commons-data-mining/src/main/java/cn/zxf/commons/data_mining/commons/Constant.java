package cn.zxf.commons.data_mining.commons;

import java.util.Map;
import java.util.Objects;

public interface Constant {

    double N = Double.NaN;

    default boolean neN( double v ) {
        return !Objects.equals( v, N );
    }

    default boolean isN( double v ) {
        return Objects.equals( v, N );
    }

    default boolean contains( Map<String, Double> map, String k1, String k2 ) {
        return map.containsKey( k1 ) //
                && this.neN( map.get( k1 ) ) //
                && map.containsKey( k2 ) //
                && this.neN( map.get( k2 ) );
    }

}
