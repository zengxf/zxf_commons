package cn.zxf.commons.data_mining.probability;

import cn.zxf.commons.data_mining.commons.EventsBuilder;
import cn.zxf.commons.data_mining.vo.Events;

public class ProbabilityDataUtils {

    public static Events data() {
        Events data = EventsBuilder.of() //
                .names( "收入", "运动目的", "当前水平", "热情", "适应科技", "Result" ) //
                .addValues( "60", "两者皆是", "很少运动", "一般", "yes", "i100" ) //
                .addValues( "75", "两者皆是", "很少运动", "一般", "no", "i100" ) //
                .addValues( "90", "健康", "很少运动", "一般", "yes", "i500" ) //
                .addValues( "125", "外表", "经常运动", "一般", "yes", "i500" ) //
                .addValues( "100", "外表", "一般", "高", "yes", "i500" ) //
                .addValues( "90", "外表", "一般", "高", "no", "i100" ) //
                .addValues( "150", "健康", "一般", "高", "no", "i500" ) //
                .addValues( "85", "两者皆是", "经常运动", "一般", "yes", "i100" ) //
                .addValues( "100", "两者皆是", "一般", "高", "yes", "i500" ) //
                .addValues( "120", "外表", "经常运动", "高", "yes", "i500" ) //
                .addValues( "95", "两者皆是", "经常运动", "高", "no", "i500" ) //
                .addValues( "90", "健康", "经常运动", "一般", "no", "i500" ) //
                .addValues( "85", "健康", "很少运动", "高", "yes", "i500" ) //
                .addValues( "70", "外表", "经常运动", "一般", "no", "i100" ) //
                .addValues( "45", "健康", "很少运动", "一般", "no", "i100" ) //
                .print() //
                .build();
        return data;
    }

}
