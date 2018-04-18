package cn.zxf.commons.basic.utils;

import org.junit.Before;
import org.junit.Test;

import cn.zxf.commons.basic.utils.AnalyzeUseTimeUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestAnalyzeUseTimeUtil {

    @Before
    public void initLog() {
        log.warn( "-=-=-=-=-=-=-=-" );
    }

    @Test
    public void testSimple() throws InterruptedException {
        AnalyzeUseTimeUtil.reset( "测试简单" );
        Thread.sleep( 500L );
        AnalyzeUseTimeUtil.summary();
    }

    @Test
    public void testLv1() throws InterruptedException {
        AnalyzeUseTimeUtil.reset( "测试Lv1" );
        Thread.sleep( 500L );
        AnalyzeUseTimeUtil.timing( "LV1A" );
        Thread.sleep( 220L );
        AnalyzeUseTimeUtil.timing( "LV1B" );
        Thread.sleep( 330L );
        AnalyzeUseTimeUtil.summary();
    }

    @Test
    public void testLv2() throws InterruptedException {
        AnalyzeUseTimeUtil.reset( "测试Lv1" );

        Thread.sleep( 500L );
        AnalyzeUseTimeUtil.timing( "LV1A" );

        AnalyzeUseTimeUtil.preTimingLv2( "LV1A@2" );
        Thread.sleep( 110L );
        AnalyzeUseTimeUtil.timingLv2( "LV2A" );
        Thread.sleep( 440L );
        AnalyzeUseTimeUtil.timingLv2( "LV2B" );
        AnalyzeUseTimeUtil.summaryLv1();

        Thread.sleep( 220L );
        AnalyzeUseTimeUtil.timing( "LV1B" );

        Thread.sleep( 330L );

        AnalyzeUseTimeUtil.summary();
    }

    @Test
    public void testLv3() throws InterruptedException {
        AnalyzeUseTimeUtil.reset( "测试Lv1-A" );

        Thread.sleep( 500L );
        AnalyzeUseTimeUtil.timing( "LV1A" );

        AnalyzeUseTimeUtil.preTimingLv2( "LV1A@2" );
        Thread.sleep( 110L );
        AnalyzeUseTimeUtil.timingLv2( "LV2A" );
        Thread.sleep( 440L );
        AnalyzeUseTimeUtil.timingLv2( "LV2B" );
        AnalyzeUseTimeUtil.summaryLv1();

        AnalyzeUseTimeUtil.preTimingLv2( "LV1A@3" );
        Thread.sleep( 110L );
        AnalyzeUseTimeUtil.timingLv2( "LV2J" );
        Thread.sleep( 440L );
        AnalyzeUseTimeUtil.timingLv2( "LV2L" );
        AnalyzeUseTimeUtil.summaryLv1();

        Thread.sleep( 220L );
        AnalyzeUseTimeUtil.timing( "LV1B" );

        Thread.sleep( 330L );

        AnalyzeUseTimeUtil.summary();

        // ------------------

        Thread.sleep( 1500L );

        AnalyzeUseTimeUtil.reset( "测试Lv1-B" );

        Thread.sleep( 500L );
        AnalyzeUseTimeUtil.timing( "LV1A" );

        AnalyzeUseTimeUtil.preTimingLv2( "LV1A@2" );
        Thread.sleep( 110L );
        AnalyzeUseTimeUtil.timingLv2( "LV2A" );

        AnalyzeUseTimeUtil.summary();
    }

}
