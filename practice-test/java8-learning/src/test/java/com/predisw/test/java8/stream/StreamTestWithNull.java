package com.predisw.test.java8.stream;

import com.predisw.test.java8.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class StreamTestWithNull {


    @Test
    public void emptyListToStreamTest(){
        List<Person> personsEmpty = new ArrayList<>();
        List<String> nameEmptyList  = personsEmpty.stream().map(p -> p.getName()).collect(Collectors.toList());
        Assertions.assertThat(nameEmptyList).isNotNull().isEmpty();
    }

    @Test(expected = NullPointerException.class)
    public void streamMapWithNullElementTest(){
        // stream map can not filter null, the forEach will thrown NullPointException
        List<Person> personsAddNull = new ArrayList<>();
        personsAddNull.add(null);
        personsAddNull.stream().map(p -> p.getName()).forEach(System.out::println);
    }

    @Test(expected = NullPointerException.class)
    public void nullToStreamTest(){
        // will thrown NullPointException
        List<Person> personsNull = null;
        personsNull.stream().map(p -> p.getName()).collect(Collectors.toList());
    }


}
