package com.predisw.test.java8;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by eggnwwg on 11/8/2017.
 */
public class optional {


    @Test
    public void OptionalNulltoMap(){

        String isNull = null;
        Optional result =  Optional.ofNullable(isNull).map(noNull -> noNull.length());
        System.out.println(" what is result ?"+ result);  //Optional.empty
        System.out.println(result.isPresent());  //false

    }



    @Test
    public void ifExistThenExeOrElseThrowEx(){

        // if ifNull != null then return ifNull's length or else throw Exception

        String ifNull = null;

        // the implement one
        try{
            int length = Optional.ofNullable(ifNull).map(noNull-> noNull.length()).orElseThrow(IllegalArgumentException::new);
            System.out.println(length);
        }catch (Exception e){
            e.printStackTrace();
        }

        // the other implement
        int length2 = Optional.of(ifNull).map(noNull-> noNull.length()).get();

        System.out.println(length2);

    }


    @Test
    public void OptionalGetValue(){

        Optional<String> testOp = Optional.ofNullable("test");

        String testStr = testOp.orElseThrow(NullPointerException::new);

        System.out.println(testStr);

    }

    @Test
    public void ifNotExistThrowException(){

        List<String> list1 = new ArrayList<>();
        list1.add("1");

        list1.stream().filter(e->e.equals("1"))
                .findFirst().map( e ->{
                    System.out.println(e);
                    return "";
        }).orElseThrow(()->new NullPointerException("element equal 1 doesn't exists"));

    }
}
