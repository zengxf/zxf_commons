package cn.zxf.commons.data_mining.numbers;

import lombok.Data;

/**
 * 矩阵实体
 * 
 * <p>
 * Created by zengxf on 2017-11-06
 */
@Data
public class MatrixVo {

    private int        row;
    private int        column;
    /**
     * 行，列
     */
    private double[][] values;

}
