package com.predisw.test.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import java.util.stream.IntStream;

import java.util.stream.Stream;

public class StreamT1 {



    @Test
    public void filterAndMap(){

        // can not change the base type value
        List<Integer> numbers = Arrays.asList(new Integer(1), new Integer(2), 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = numbers.stream();
        stream.filter(x -> x %2 ==0).map((x) -> {
            x= x * x;
            return x;
        }).forEach(System.out::println);

        System.out.println(numbers); //output [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


        numbers.stream().filter((x) -> {
            return x % 2 == 0;
        }).findFirst()
                .ifPresent(x-> {x = x*x;});

        System.out.println(numbers); //output [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    }


    @Test
    public void filterAndMapObject(){

        IntegerT a,b;

        a = new IntegerT(1);
        b = new IntegerT(2);

        List<IntegerT> numbers = Arrays.asList(a,b);
        Stream<IntegerT> stream = numbers.stream();
        stream.filter((x) -> {
            return x.value % 2 == 0;
        }).map((x) -> {
            x.value = x.value*2;
            return x;
        }); // output [1,2]
        //.forEach(System.out::println); after append this then output [1,4]

        System.out.println(numbers);

        IntegerT c,d;
        c = new IntegerT(3);
        d = new IntegerT(4);
        List<IntegerT> listCD = Arrays.asList(c,d);
        listCD.stream().filter((x) -> {
            return x.value % 2 == 0;
        }).map((x) -> {
            x = new IntegerT(x.value*3);
            return x;

        }).forEach(System.out::println);  // output x*3 = 12

        System.out.println(listCD); // output [3,4] the input x will not be changed


    }


    @Test
    public void filterAndMapToInt(){

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = numbers.stream();
        stream.filter((x) -> {
            return x % 2 == 0;
        }).map(x -> {
            return x * x;
        }).mapToInt(x->x+1).sum();

    }


    @Test
    public void forEachBreak(){

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.forEach(num->{
            if(num>3){
                System.out.println(num);
               // break; // can't break in foreach !!
            }
        });

    }

    public static int getLength(String text) {
        // Java 8
        return Optional.ofNullable(text).map(String::length).orElse(-1);
        // Pre-Java 8
        // return if (text != null) ? text.length() : -1;
    }

    @Test
    public void testLimitAndSkip() {
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 10; i++) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        List<String> personList2 = persons.stream().
                map(Person::getName).sorted().limit(6).collect(Collectors.toList());
        System.out.println(personList2);
    }
    private class Person {
        public int no;
        private String name;
        public Person (int no, String name) {
            this.no = no;
            this.name = name;
        }
        public String getName() {
            System.out.println(name);
            return name;
        }
    }

    public void IntStreamT(){

        IntStream.of(new int[]{1,3,5}).forEach(System.out::println);

        System.out.println("-----");

        IntStream.rangeClosed(1,10).limit(5).map(x -> {return x+1;}).forEach(System.out::println);

    }


    class IntegerT{

        public int value;

        public IntegerT(int value){
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

}
