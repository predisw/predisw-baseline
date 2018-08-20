package com.predisw.test.practise.collection;

import java.util.Collection;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ArraysT {


    @Test
    public void asListIfCanChanged(){

        List<String> names = Arrays.asList("predisw","viw");

 //       names.add("john"); // throw UnsupportedOperationException

       // names.remove(1);  // throw UnsupportedOperationException

        System.out.println(names);



        System.out.println(names instanceof Collection);

    }


}
