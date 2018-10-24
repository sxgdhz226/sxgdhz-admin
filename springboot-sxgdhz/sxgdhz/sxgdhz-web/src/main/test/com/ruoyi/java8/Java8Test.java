package com.ruoyi.java8;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Java8Test {


    /**
     * 数值创建流
     */
    @Test
    public void test1(){

        Stream<String> streams = Stream.of("java","python");
        streams.map(String::toUpperCase).forEach(System.out::println);
    }

    /**
     * 数组创建流
     */
    @Test
    public void test2(){
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
    }

}
