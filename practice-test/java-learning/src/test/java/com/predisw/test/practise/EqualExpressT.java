package com.predisw.test.practise;

/**
 * Created by eggnwwg on 9/6/2017.
 */
public class EqualExpressT {


    public static void main(String[] args) {
        boolean result =true;

        result=result && (getTest1()==getTest2());
        System.out.println("output ..."+ result);

    }


    private static int getTest1(){

        return 1;
    }

    private static int getTest2(){

        return 2;
    }
}
