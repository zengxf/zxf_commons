package cn.zxf.commons.data_mining.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public class Event {

    public final List<EventAttribute> attributes = new ArrayList<>();

    @Data
    public static class EventAttribute {
        public final String name;
        public final String value;
    }

}
