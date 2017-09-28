package com.predisw.test.practise;

import org.junit.Test;

import java.util.List;

/**
 * Created by eggnwwg on 9/11/2017.
 */
public class ListT {


    @Test
    public void listNullWithForEach(){
// check forEach if will throw exception if the list is null
        // consequence: it will
        List<String> list =null;

        for(String str:list){

            System.out.println(str);

        }

    }
}
