package com.predisw.test.practise.baseType;

import org.junit.Test;


public class Byte {

    @Test
    public void test(){


        System.out.println((byte)128);  //output -128

        System.out.println((byte)129);  //output -127

        System.out.println((byte)129 > 120);  //output -127

    }

}
