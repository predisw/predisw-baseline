package com.predisw.test.java8;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by eggnwwg on 11/8/2017.
 */
public class optional {

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
}
