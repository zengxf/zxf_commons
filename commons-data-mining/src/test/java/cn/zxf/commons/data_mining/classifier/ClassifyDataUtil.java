package cn.zxf.commons.data_mining.classifier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.zxf.commons.data_mining.numbers.VectorVo;

class ClassifyDataUtil {

    public static List<ClassifyItem> training() {
        return data( "/ch4/athletesTrainingSet.txt" );
    }

    public static List<ClassifyItem> test() {
        return data( "/ch4/athletesTestSet.txt" );
    }

    static List<ClassifyItem> data( String file ) {
        try {
            Path path = Paths.get( ClassifyDataUtil.class.getResource( file ).toURI() );
            List<String> lines = Files.readAllLines( path );
            return lines.stream().map( line -> {
                ClassifyItem item = new ClassifyItem();
                String[] arr = line.split( "\t+" );
                item.setCommend( arr[0] );
                item.setClassify( arr[1] );
                double[] values = Stream.of( arr ).skip( 2 ).mapToDouble( Double::parseDouble ).toArray();
                item.setVector( VectorVo.of( values ) );
                return item;
            } ).collect( Collectors.toList() );
        } catch ( URISyntaxException | IOException e ) {
            throw new RuntimeException( "读取数据时出错！", e );
        }
    }

}
