package cn.zxf.commons.data_mining.classifier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cn.zxf.commons.data_mining.classifier.ConfusionMatricesTenFoldCross.MatricesItem;
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

    public static List<ClassifyItem> iris_training() {
        return iris_data( "/ch4/irisTrainingSet.txt" );
    }

    public static List<ClassifyItem> iris_test() {
        return iris_data( "/ch4/irisTestSet.txt" );
    }

    static List<ClassifyItem> iris_data( String file ) {
        try {
            Path path = Paths.get( ClassifyDataUtil.class.getResource( file ).toURI() );
            List<String> lines = Files.readAllLines( path );
            return lines.stream().map( line -> {
                ClassifyItem item = new ClassifyItem();
                String[] arr = line.split( "\\s+" );
                item.setCommend( "" );
                item.setClassify( arr[4] );
                double[] values = Stream.of( arr ).limit( 4 ).mapToDouble( Double::parseDouble ).toArray();
                item.setVector( VectorVo.of( values ) );
                return item;
            } ).collect( Collectors.toList() );
        } catch ( URISyntaxException | IOException e ) {
            throw new RuntimeException( "读取数据时出错！", e );
        }
    }

    public static List<ClassifyItem> mpg_training() {
        return mpg_data( "/ch4/mpgTrainingSet.txt" );
    }

    public static List<ClassifyItem> mpg_test() {
        return mpg_data( "/ch4/mpgTestSet.txt" );
    }

    public static List<ClassifyItem> mpg_ten_test() {
        return mpg_data( "/ch5/mpgData/mpgAll.txt" );
    }

    static List<ClassifyItem> mpg_data( String file ) {
        try {
            Path path = Paths.get( ClassifyDataUtil.class.getResource( file ).toURI() );
            List<String> lines = Files.readAllLines( path );
            return lines.stream().map( line -> {
                ClassifyItem item = new ClassifyItem();
                String[] arr = line.split( "\t+" );
                item.setCommend( arr[6] );
                item.setClassify( arr[0] );
                double[] values = Stream.of( arr ).skip( 1 ).limit( 5 ).mapToDouble( Double::parseDouble ).toArray();
                item.setVector( VectorVo.of( values ) );
                return item;
            } ).collect( Collectors.toList() );
        } catch ( URISyntaxException | IOException e ) {
            throw new RuntimeException( "读取数据时出错！", e );
        }
    }

    static int printAndReturn( Map<String, MatricesItem> result ) {
        Set<String> set = new TreeSet<>();
        result.values().forEach( item -> {
            set.add( item.getActual() );
            set.addAll( item.getErrorMap().keySet() );
        } );
        System.out.println();
        set.forEach( k -> System.out.print( "\t" + k ) );
        System.out.println();
        AtomicInteger correct = new AtomicInteger();
        set.forEach( k -> {
            MatricesItem mi = result.getOrDefault( k, new MatricesItem() );
            System.out.print( k );
            set.forEach( ki -> {
                System.out.print( "\t" );
                if ( ki.equals( mi.getActual() ) ) { // 相当于 k
                    int correctCount = mi.getCorrectCount();
                    correct.addAndGet( correctCount );
                    System.out.print( correctCount );
                } else
                    System.out.print( mi.getErrorMap().getOrDefault( ki, 0 ) );
            } );
            System.out.println();
        } );
        System.out.println();
        return correct.get();
    }
    
}
