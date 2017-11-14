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
        Path path = Paths.get( ClassifyDataUtil.class.getResource( "/" ).toURI() );
        path = path.resolve( "../src/test/resources/ch5/mpgData" ).normalize();
        System.out.println( path );
        System.out.println( "-----------------------" );
        Path target = path.resolve( "mpgAll.txt" );
        System.out.println( target );
        System.out.println( "-----------------------" );
        Files.list( path ) //
                .filter( fp -> fp.toString().contains( "mpgData-" ) ) //
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
