package com.predisw.test.practise.Exception;

import org.junit.Test;

/**
 * Created by eggnwwg on 3/19/2018.
 */
public class InnerException {


    @Test
    public void innerTest(){

        try{
            System.out.println("try 1");
            try{
                System.out.println("try 2");
                throw new NullPointerException();
            }finally {
                System.out.println("final 2");
            }
        }catch (Exception e){
            System.out.println("catch 1");
        }finally {
            System.out.println("final 1");
        }

        ExceptionTImpl parent = new ExceptionTImpl();

        NullExceptionT child = (NullExceptionT) parent;


    }
}
