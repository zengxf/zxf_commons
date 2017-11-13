package cn.zxf.commons.data_mining.classifier;

import cn.zxf.commons.data_mining.numbers.VectorVo;
import lombok.Data;

@Data
public class ClassifyItem {

    private String   classify;
    private VectorVo vector;
    private String   commend;

}
