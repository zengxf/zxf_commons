package cn.zxf.commons.data_mining.numbers.utils;

import java.util.stream.DoubleStream;

/**
 * 数组运算帮助类
 * 
 * <p>
 * Created by zengxf on 2017-11-06
 */
public class ArrayUtils {

    public static double sum( double... arr ) {
        return DoubleStream.of( arr ).sum();
    }

    public static double avg( double... arr ) {
        return DoubleStream.of( arr ).average().getAsDouble();
    }

}
