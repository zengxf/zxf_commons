package cn.zxf.commons.data_mining.classifier;

import java.util.List;

import cn.zxf.commons.data_mining.numbers.VectorVo;
import cn.zxf.commons.data_mining.numbers.distance.DistanceCalculator;
import lombok.Builder;

/**
 * K 近邻分类器<br>
 * 从距离最短的几个中选数量多的种类获胜
 * 
 * <p>
 * Created by zengxf on 2017-11-15
 */
@Builder
public class KnnClassifier implements IClassifier {

    private DistanceCalculator disCalc;
    private List<ClassifyItem> data;
    private VectorVo           target;
    private boolean            isNorm;    // true 表示需要标准化
    // ------------
    private int                k;
    // -------------
    private Classifier         classifier;

    public String classify() {
        if ( classifier == null )
            classifier = Classifier.builder().disCalc( disCalc ).data( data ).isNorm( isNorm ).k( k ).build();
        return classifier.target( target ).classify();
    }

    public KnnClassifier target( VectorVo target ) {
        this.target = target;
        return this;
    }

}
