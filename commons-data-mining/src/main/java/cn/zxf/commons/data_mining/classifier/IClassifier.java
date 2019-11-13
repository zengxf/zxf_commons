package cn.zxf.commons.data_mining.classifier;

import cn.zxf.commons.data_mining.numbers.VectorVo;

public interface IClassifier {

    String classify();

    IClassifier target( VectorVo target );

}
