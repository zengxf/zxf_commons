package cn.zxf.commons.data_mining.numbers;

import java.util.Arrays;

import lombok.Data;

/**
 * 向量实体
 * 
 * <p>
 * Created by zengxf on 2017-11-06
 */
@Data
public class VectorVo {

    private int      len;
    private double[] values = {};

    public static VectorVo of( double... values ) {
        VectorVo vo = new VectorVo();
        vo.len = values.length;
        vo.values = values;
        return vo;
    }

    public void add( double value ) {
        len++;
        values = Arrays.copyOf( this.values, len );
        values[len - 1] = value;
    }

}
