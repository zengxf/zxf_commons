package cn.zxf.commons.data_mining.classifier;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import cn.zxf.commons.data_mining.numbers.VectorVo;
import cn.zxf.commons.data_mining.numbers.distance.Euclidean;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestClassifier {

    @Test
    public void test() {
        VectorVo vo = VectorVo.of( 67, 123 );
        List<ClassifyItem> data = ClassifyDataUtil.training();
        data.forEach( item -> {
            log.info( "training-data-item: {}", item );
        } );
        String classify = Classifier.builder()//
                .data( data )//
                .target( vo ) //
                .disCalc( new Euclidean() ) //
                .build() //
                .classify();
        log.info( "vo: {}, classify: {}", vo, classify );
        Assert.assertThat( classify, CoreMatchers.is( "Track" ) );
    }

    @Test
    public void testCorrect() {
        List<ClassifyItem> testData = ClassifyDataUtil.test();
        List<ClassifyItem> trainingData = ClassifyDataUtil.training();
        AtomicInteger correct = this.test( testData, trainingData, true );
        Assert.assertThat( correct.get(), CoreMatchers.is( 16 ) );
    }

    @Test
    public void testIrisCorrect() {
        List<ClassifyItem> testData = ClassifyDataUtil.iris_test();
        List<ClassifyItem> trainingData = ClassifyDataUtil.iris_training();
        AtomicInteger correct = this.test( testData, trainingData, true );
        Assert.assertThat( correct.get(), CoreMatchers.is( 29 ) );
    }

    @Test
    public void testMpgCorrect() {
        List<ClassifyItem> testData = ClassifyDataUtil.mpg_test();
        List<ClassifyItem> trainingData = ClassifyDataUtil.mpg_training();
        AtomicInteger correct = this.test( testData, trainingData, true );
        Assert.assertThat( correct.get(), CoreMatchers.is( 28 ) );
    }

    AtomicInteger test( List<ClassifyItem> testData, List<ClassifyItem> trainingData, boolean isNorm ) {
        trainingData.forEach( item -> {
            log.info( "training-data-item: {}", item );
        } );
        log.info( "-------------------------" );
        testData.forEach( item -> {
            log.info( "test-data-item: {}", item );
        } );
        log.info( "-------------------------" );

        Classifier classifier = Classifier.builder()//
                .data( trainingData )//
                .disCalc( new Euclidean() ) //
                .isNorm( isNorm ) //
                .build();

        AtomicInteger correct = new AtomicInteger();
        testData.forEach( item -> {
            String classify = classifier //
                    .target( item.getVector() ) //
                    .classify();
            boolean isCorrect = Objects.equals( classify, item.getClassify() );
            log.info( "is-correct: {}, act: {}, pre: {}", isCorrect, item.getClassify(), classify );
            if ( isCorrect ) {
                correct.incrementAndGet();
            }
        } );

        log.info( "总数：{}, 正确数：{}, 正确率: {}", testData.size(), correct, correct.get() * 100 / testData.size() );
        return correct;
    }

}
