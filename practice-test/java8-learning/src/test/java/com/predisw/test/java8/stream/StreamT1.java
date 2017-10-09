package com.predisw.test.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamT1 {



    @Test
    public void filterAndMap(){

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = numbers.stream();
        stream.filter((x) -> {
            return x % 2 == 0;
        }).map((x) -> {
            return x * x;
        }).forEach(System.out::println);

    }


    @Test
    public void IntStreamT(){

        IntStream.of(new int[]{1,3,5}).forEach(System.out::println);

        System.out.println("-----");

        IntStream.rangeClosed(1,10).limit(5).map(x -> {return x+1;}).forEach(System.out::println);

    }


}
