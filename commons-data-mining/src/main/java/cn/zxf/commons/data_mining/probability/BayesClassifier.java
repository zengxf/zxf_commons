package cn.zxf.commons.data_mining.probability;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.zxf.commons.data_mining.commons.MapUtils;
import cn.zxf.commons.data_mining.vo.Event.EventAttribute;
import cn.zxf.commons.data_mining.vo.Events;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class BayesClassifier {

    private Events               data;
    private List<EventAttribute> conditions;
    private String               classifyName;
    // --------------------

    public String classify() {
        List<String> classifyList = data.events.stream() //
                .map( event -> event.attributes.stream() //
                        .filter( attr -> Objects.equals( attr.name, this.classifyName ) ) //
                        .findFirst() //
                        .get().value ) //
                .distinct() //
                .collect( Collectors.toList() );

        Map<String, Double> res = classifyList.stream() //
                .collect( Collectors.toMap( //
                        classify -> classify, //
                        classify -> NaiveBayes.builder() //
                                .data( data ) //
                                .byAndList( conditions ) //
                                .expect( EventAttribute.of( classifyName, classify ) ) //
                                .build() //
                                .calculate() ) );

        Map<String, Double> desc = MapUtils.sortDesc( res );
        desc.forEach( ( classify, pr ) -> log.debug( "Pr({}): {}", classify, pr ) );
        return desc.keySet().iterator().next();
    }

}
