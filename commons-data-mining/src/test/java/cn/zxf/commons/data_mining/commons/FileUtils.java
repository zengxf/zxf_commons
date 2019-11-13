package cn.zxf.commons.data_mining.commons;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static Path path( String file ) {
        try {
            return Paths.get( FileUtils.class.getResource( file ).toURI() );
        } catch ( URISyntaxException e ) {
            throw new RuntimeException( "获取路径出错! file: " + file, e );
        }
    }

}
