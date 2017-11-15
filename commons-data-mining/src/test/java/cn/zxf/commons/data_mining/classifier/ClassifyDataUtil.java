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
        return data( file, "\t+", 2, Integer.MAX_VALUE, 1, 0 );
    }

    public static List<ClassifyItem> iris_training() {
        return iris_data( "/ch4/irisTrainingSet.txt" );
    }

    public static List<ClassifyItem> iris_test() {
        return iris_data( "/ch4/irisTestSet.txt" );
    }

    static List<ClassifyItem> iris_data( String file ) {
        return data( file, "\\s+", 0, 4, 4, -1 );
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
        return data( file, "\t+", 1, 5, 0, 6 );
    }

    public static List<ClassifyItem> pima_data() {
        return pima_data( "/ch5/pimaSmall/all.txt" );
    }

    public static List<ClassifyItem> pimaBig_data() {
        return pima_data( "/ch5/pima/all.txt" );
    }

    static List<ClassifyItem> pima_data( String file ) {
        return data( file, "\t+", 0, 8, 8, -1 );
    }

    static List<ClassifyItem> data( String file, String regex, int skip, int limit, int classifyIndex, int commendIndex ) {
        try {
            Path path = Paths.get( ClassifyDataUtil.class.getResource( file ).toURI() );
            List<String> lines = Files.readAllLines( path );
            return lines.stream().map( line -> {
                ClassifyItem item = new ClassifyItem();
                String[] arr = line.split( regex );
                item.setClassify( arr[classifyIndex] );
                if ( commendIndex > -1 )
                    item.setCommend( arr[commendIndex] );
                double[] values = Stream.of( arr ).skip( skip ).limit( limit ).mapToDouble( Double::parseDouble ).toArray();
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
