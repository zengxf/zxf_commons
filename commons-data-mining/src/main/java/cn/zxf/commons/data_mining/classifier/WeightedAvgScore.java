package cn.zxf.commons.data_mining.classifier;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * 加权平均分<br>
 * 结合 knn 进行推荐
 * 
 * <p>
 * Created by zengxf on 2017-11-15
 */
@Data
@Builder
public class WeightedAvgScore {

    private List<DistanceScore> refList;

    public double calculate() {
        double disInvSum = refList.stream().mapToDouble( DistanceScore::getDistanceInverse ).sum();
        double forecast = refList.stream().mapToDouble( ref -> ref.calculate( disInvSum ) ).sum();
        return forecast;
    }

    @Data
    public static class DistanceScore {
        private final double distance;
        private final double score;
        // ---------
        private final double distanceInverse;

        public DistanceScore( double distance, double score ) {
            super();
            this.distance = distance;
            this.score = score;
            this.distanceInverse = 1D / distance;
        }

        public double calculate( double disInvSum ) {
            return this.distanceInverse / disInvSum * score;
        }

        public static DistanceScore of( double distance, double score ) {
            DistanceScore ds = new DistanceScore( distance, score );
            return ds;
        }

    }

}
