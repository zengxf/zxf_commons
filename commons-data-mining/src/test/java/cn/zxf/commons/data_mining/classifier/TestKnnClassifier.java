package cn.zxf.commons.data_mining.classifier;

import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.classifier.ConfusionMatricesTenFoldCross.MatricesItem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestKnnClassifier {

    @Test
    public void test() {
        List<ClassifyItem> data = ClassifyDataUtil.pima_data();
        Map<String, MatricesItem> result = ConfusionMatricesTenFoldCross.builder() //
                .data( data ) //
                .isKnn( true ) //
                .k( 3 ) //
                .build() //
                .calculate() //
                .getResult();
        int correct = ClassifyDataUtil.printAndReturn( result );
        System.out.println( String.format( "正确：%s，总数：%s，正确率：%s", correct, data.size(), correct * 100D / data.size() ) );
        Assert.assertThat( correct, CoreMatchers.is( 56 ) );

        int kappa = KappaStatistic.builder().data( result ).build().calculate();
        log.info( "kappa: {}", kappa );
    }

    @Test
    public void testBig() {
        List<ClassifyItem> data = ClassifyDataUtil.pimaBig_data();
        Map<String, MatricesItem> result = ConfusionMatricesTenFoldCross.builder() //
                .data( data ) //
                .isKnn( true ) //
                .k( 3 ) //
                .build() //
                .calculate() //
                .getResult();
        int correct = ClassifyDataUtil.printAndReturn( result );
        System.out.println( String.format( "正确：%s，总数：%s，正确率：%s", correct, data.size(), correct * 100D / data.size() ) );
        Assert.assertThat( correct, CoreMatchers.is( 297 ) );

        int kappa = KappaStatistic.builder().data( result ).build().calculate();
        log.info( "kappa: {}", kappa );
    }

}
