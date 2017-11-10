package com.predisw.test.practise.Exception;

import org.junit.Test;

/**
 * Created by eggnwwg on 11/6/2017.
 */
public class NullExceptionT {

    @Test
    public void throwEmptyCommentException(){

        try {
            throw new IllegalArgumentException();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
