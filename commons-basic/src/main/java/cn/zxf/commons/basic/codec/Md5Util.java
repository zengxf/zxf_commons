package cn.zxf.commons.basic.codec;

import java.security.MessageDigest;

import lombok.extern.slf4j.Slf4j;

/**
 * MD5 加密帮助类
 * 
 * @author zxf
 */
@Slf4j
public class Md5Util {

    // 用于 MD5 加密的字符
    private static final char MD5_DIGITS_LOWER[] = //
            { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 返回 32 位长度小写字符
     * 
     * @param v
     * @return
     */
    public static String crypt( String v ) {
        try {
            // 使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = v.getBytes();

            // 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance( "MD5" );

            // MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update( btInput );

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for ( int i = 0; i < j; i++ ) { // i = 0
                byte byte0 = md[i]; // 95
                str[k++] = MD5_DIGITS_LOWER[byte0 >>> 4 & 0xf]; // 5
                str[k++] = MD5_DIGITS_LOWER[byte0 & 0xf]; // F
            }

            // 返回经过加密后的字符串
            return new String( str );
        } catch ( Exception e ) {
            log.error( "加密时出错！value：" + v, e );
            return null;
        }
    }

    /**
     * 返回 16 位长度小写字符
     * 
     * @param v
     * @return
     */
    public static String crypt16( String v ) {
        String encode = crypt( v );
        return encode == null ? null : encode.substring( 8, 24 );
    }

}
