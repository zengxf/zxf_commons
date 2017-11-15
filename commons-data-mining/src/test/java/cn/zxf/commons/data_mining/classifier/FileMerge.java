package cn.zxf.commons.data_mining.classifier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Deprecated
public class FileMerge {

    public static void main( String[] args ) throws URISyntaxException, IOException {
        String sign = "pima";

        Path path = Paths.get( ClassifyDataUtil.class.getResource( "/" ).toURI() );
        path = path.resolve( "../src/test/resources/ch5/" + sign ).normalize();
        System.out.println( path );
        System.out.println( "-----------------------" );
        Path target = path.resolve( "all.txt" );
        if ( !Files.exists( target ) )
            Files.createFile( target );
        System.out.println( target );
        System.out.println( "-----------------------" );
        Files.list( path ) //
                .filter( fp -> fp.toString().contains( sign + "-" ) ) //
                .peek( fp -> System.out.println( fp ) ) //
                .flatMap( fp -> {
                    try {
                        return Files.readAllLines( fp ).stream();
                    } catch ( IOException e ) {
                        throw new RuntimeException( "读取文件异常", e );
                    }
                } ) //
                .forEach( line -> {
                    try {
                        Files.write( target, ( line + "\n" ).getBytes(), StandardOpenOption.WRITE, StandardOpenOption.APPEND );
                    } catch ( IOException e ) {
                        throw new RuntimeException( "写入文件异常", e );
                    }
                } );
    }

}
