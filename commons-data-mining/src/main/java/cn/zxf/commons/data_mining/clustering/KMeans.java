package cn.zxf.commons.data_mining.clustering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

import cn.zxf.commons.data_mining.numbers.VectorVo;
import lombok.Builder;


/**
 * k-means 聚类算法
 * 
 * <p>
 * Created by zengxf on 2017-11-28
 */
@Builder
public class KMeans {

    int                           k;
    List<VectorVo>                data;
    // --------
    List<VectorVo>                centerList;
    Map<VectorVo, List<VectorVo>> map;

    public void calculate() {
        init();
        while ( true ) {
            // 计算距离
            // 重新计算中心点
            break;
        }
    }

    void init() {
        centerList = new ArrayList<>( k );
        map = new HashMap<>( k );

        int size = data.size();
        int[] indexs = new int[k];
        Arrays.fill( indexs, -1 );
        IntStream.range( 0, k ).forEach( i -> {
            int r = new Random().nextInt( size );
            while ( true ) {
                if ( IntStream.of( indexs ).allMatch( j -> j != r ) ) {
                    break;
                }
            }
            indexs[i] = r;
        } );

        IntStream.of( indexs ).forEach( i -> centerList.add( data.get( i ) ) );
    }

}
