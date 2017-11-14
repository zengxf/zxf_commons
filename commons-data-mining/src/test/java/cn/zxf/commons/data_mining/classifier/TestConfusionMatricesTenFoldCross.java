package cn.zxf.commons.data_mining.classifier;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.zxf.commons.data_mining.classifier.ConfusionMatricesTenFoldCross.MatricesItem;

public class TestConfusionMatricesTenFoldCross {

    @Test
    public void test() {
        List<ClassifyItem> data = ClassifyDataUtil.mpg_ten_test();
        Map<String, MatricesItem> result = ConfusionMatricesTenFoldCross.builder() //
                .data( data ) //
                .build() //
                .calculate() //
                .getResult();
        int correct = ClassifyDataUtil.printAndReturn( result );
        System.out.println( "正确：" + correct + "，正确率：" + ( correct * 100D / data.size() ) );
    }

}
