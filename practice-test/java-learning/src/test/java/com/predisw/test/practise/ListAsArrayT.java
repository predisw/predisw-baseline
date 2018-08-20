package com.predisw.test.practise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eggnwwg on 6/19/2017.
 */
public class ListAsArrayT {

    public static void main(String[] args) {

        List<String> strList = new ArrayList<>();

        strList.add("a");
        strList.add("b");

        System.out.println(strList.toString());

     //   addListWithArray(strList.toArray(new String[1]));


    }

    public static void addListWithArray(String[] strArr){

        System.out.println(strArr.getClass());
        System.out.println(strArr.length);

    }

}
