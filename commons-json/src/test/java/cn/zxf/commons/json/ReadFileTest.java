package cn.zxf.commons.json;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadFileTest {

    @Test
    public void test() throws URISyntaxException, IOException {
        URI uri = ReadFileTest.class.getResource( "/test.json" ).toURI();
        Path path = Paths.get( uri );
        Object obj = Json.parseJson( path );
        log.info( "json: {}", obj );
    }

}
