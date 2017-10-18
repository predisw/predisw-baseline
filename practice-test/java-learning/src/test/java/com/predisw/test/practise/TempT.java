package com.predisw.test.practise;

import org.junit.Test;

import java.lang.reflect.TypeVariable;

/**
 * Created by eggnwwg on 6/12/2017.
 */
public class TempT<T extends MockitoT> {


    public static void main(String [] args){


        System.out.println(Long.MAX_VALUE);
        System.out.println((long)(Integer.MAX_VALUE)*2);

        TypeVariable[] types =  TempT.class.getTypeParameters();


        System.out.println(types.length + " "+types[0].getName());
    }
}
