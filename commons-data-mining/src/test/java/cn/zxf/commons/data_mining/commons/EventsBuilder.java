package cn.zxf.commons.data_mining.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import cn.zxf.commons.data_mining.vo.Event;
import cn.zxf.commons.data_mining.vo.Event.EventAttribute;
import cn.zxf.commons.data_mining.vo.Events;

public class EventsBuilder {

    private final List<String>   attributeNames  = new ArrayList<>();
    private final List<String[]> attributeValues = new ArrayList<>();

    public static EventsBuilder of() {
        EventsBuilder self = new EventsBuilder();
        return self;
    }

    public EventsBuilder names( String... names ) {
        attributeNames.addAll( Arrays.asList( names ) );
        return this;
    }

    public EventsBuilder addValues( String... values ) {
        attributeValues.add( values );
        return this;
    }

    private void assertLength() {
        int len = attributeNames.size();
        attributeValues.forEach( values -> {
            if ( values.length != len ) {
                throw new RuntimeException( "长度不一致！" );
            }
        } );
    }

    public EventsBuilder print() {
        System.out.println();
        attributeNames.forEach( name -> System.out.print( "\t" + name + "\t|" ) );
        System.out.println();
        attributeValues.forEach( values -> {
            Stream.of( values ).forEach( value -> System.out.print( "\t" + value + "\t|" ) );
            System.out.println();
        } );
        System.out.println();
        return this;
    }

    public Events build() {
        this.assertLength();
        Events events = new Events();
        attributeValues.stream().map( values -> {
            Event event = new Event();
            for ( int i = 0; i < values.length; i++ ) {
                EventAttribute attr = new EventAttribute( attributeNames.get( i ), values[i] );
                event.attributes.add( attr );
            }
            return event;
        } ).forEach( events.events::add );
        return events;
    }

}
