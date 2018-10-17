package com.predisw.test.java8.stream;

import com.predisw.test.java8.Person;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

public class StreamMap {

    @Test
    public void StringFlatMapTest(){
        Map<String,List<String>> mapWithList = new HashMap<>();
        List<String> words1 = Arrays.asList("a,b,c".split(","));
        List<String> words2 = Arrays.asList("d,e,f".split(","));

        mapWithList.put("words1",words1);
        mapWithList.put("words2",words2);

        List<String> chars = mapWithList.values().stream().flatMap(value -> value.stream()).collect(Collectors.toList());

        System.out.println(Arrays.toString(chars.toArray())); // [a, b, c, d, e, f]

        List<String> charsToo = mapWithList.entrySet().stream().map(entry -> entry.getValue()).flatMap(list ->list.stream()).collect(Collectors.toList());

        System.out.println(Arrays.toString(charsToo.toArray())); // [a, b, c, d, e, f]






    }


    @Test
    public void ObjectFlatMapTest(){
        Map<String,List<Person>> mapWithList = new HashMap<>();
        List<Person> persons1 = Arrays.asList(new Person[]{ new Person(1,"Mr A"),new Person(2,"Mr B")  });
        List<Person> persons2 =  Arrays.asList(new Person[]{ new Person(3,"Mr C"),new Person(4,"Mr D")  });

        mapWithList.put("persons1",persons1);
        mapWithList.put("persons2",persons2);

        List<Person> personList1 = mapWithList.values().stream().flatMap(value -> value.stream()).collect(Collectors.toList());

        System.out.println(Arrays.toString(personList1.toArray()));

        List<Person> personList2 = mapWithList.entrySet().stream().map(entry -> entry.getValue()).flatMap(list ->list.stream()).collect(Collectors.toList());

        System.out.println(Arrays.toString(personList2.toArray())); // [a, b, c, d, e, f]


    }



}
