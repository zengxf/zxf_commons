package cn.zxf.commons.json.component;

public interface Component {
    String toJson();

    < T > T parse( String json );
}
