package cn.zxf.commons.basic.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 分析用时帮助类，精确度--毫秒
 * <p>
 * 
 * @author zxf
 */
// ./log.sh -t | grep AnalyzeUseTimeUtil
@Slf4j
public class AnalyzeUseTimeUtil {

    static ThreadLocal<UseTimeBO> local = new ThreadLocal<UseTimeBO>() {
	public UseTimeBO initialValue() {
	    return new UseTimeBO();
	}
    };

    /**
     * 重置并启用新的记时
     */
    public static void reset( String newOp ) {
	local.get().op = newOp;
	local.get().millis = System.currentTimeMillis();
	local.get().millisLv1 = System.currentTimeMillis();

	print( String.format( "[%s]计时开始！", newOp ) );
    }

    /**
     * 计时，在程序跑完之后打印用时
     */
    public static void timing( String childOp ) {
	long cur = System.currentTimeMillis();
	long millisLv1 = local.get().millisLv1 == 0L ? local.get().millis : local.get().millisLv1;
	long useTime = cur - millisLv1;
	local.get().millisLv1 = cur;

	print( String.format( "[%s]-[%s]用时：[%d]ms", local.get().op, childOp, useTime ) );
    }

    /**
     * 准备计时子节点操作
     */
    public static void preTimingLv2( String childOp ) {
	local.get().opLv1 = childOp;
	local.get().millisLv2 = System.currentTimeMillis();
	local.get().sumLv2 = 0L;

	print( String.format( "[%s]-[%s]计时开始！", local.get().op, childOp ) );
    }

    /**
     * 子节点操作计时
     */
    public static void timingLv2( String opLv2 ) {
	long cur = System.currentTimeMillis();
	long useTime = cur - local.get().millisLv2;

	print( String.format( "[%s]-[%s]-[%s]用时：[%d]ms", local.get().op, local.get().opLv1, opLv2, useTime ) );

	local.get().millisLv2 = System.currentTimeMillis();
	local.get().sumLv2 += useTime;
    }

    /**
     * 子节点操作总计时
     */
    public static void summaryLv1() {
	print( String.format( "[%s]-[%s]总用时：[%d]ms", local.get().op, local.get().opLv1, local.get().sumLv2 ) );
	local.get().millisLv1 = System.currentTimeMillis();
    }

    /**
     * 总计时
     */
    public static void summary() {
	long cur = System.currentTimeMillis();
	long useTime = cur - local.get().millis;
	print( String.format( "[%s]总用时：[%d]ms", local.get().op, useTime ) );
    }

    // 打印用户消息
    private static void print( String useTimeMessage ) {
	log.warn( useTimeMessage );
    }

    private static class UseTimeBO {
	private String op;	  // 当前主操作
	private String opLv1;	  // 当前子操作
	private long   millis;	  // 当前时间戳
	private long   millisLv1; // 当前时间戳
	private long   millisLv2; // 当前时间戳
	private long   sumLv2;	  // 总用时
    }

}
