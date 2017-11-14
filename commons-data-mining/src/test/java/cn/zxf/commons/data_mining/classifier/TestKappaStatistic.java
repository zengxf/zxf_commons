package cn.zxf.commons.data_mining.classifier;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.classifier.ConfusionMatricesTenFoldCross.MatricesItem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestKappaStatistic {

    @Test
    public void test() {
        Map<String, MatricesItem> data = getData();
        ClassifyDataUtil.printAndReturn( data );

        int kappa = KappaStatistic.builder().data( data ).build().calculate();
        log.info( "kappa: {}", kappa );
        Assert.assertThat( kappa, CoreMatchers.is( 61 ) );
    }

    Map<String, MatricesItem> getData() {
        Map<String, MatricesItem> data = new HashMap<>();
        data.computeIfAbsent( "体操", k -> {
            MatricesItem item = new MatricesItem();
            item.setActual( k );
            item.setCorrectCount( 35 );
            item.getErrorMap().put( "篮球", 5 );
            item.getErrorMap().put( "马拉松", 20 );
            return item;
        } );
        data.computeIfAbsent( "篮球", k -> {
            MatricesItem item = new MatricesItem();
            item.setActual( k );
            item.setCorrectCount( 88 );
            item.getErrorMap().put( "体操", 0 );
            item.getErrorMap().put( "马拉松", 12 );
            return item;
        } );
        data.computeIfAbsent( "马拉松", k -> {
            MatricesItem item = new MatricesItem();
            item.setActual( k );
            item.setCorrectCount( 28 );
            item.getErrorMap().put( "体操", 5 );
            item.getErrorMap().put( "篮球", 7 );
            return item;
        } );
        return data;
    }

}
