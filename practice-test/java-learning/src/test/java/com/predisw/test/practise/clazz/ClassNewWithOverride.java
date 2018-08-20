package com.predisw.test.practise.clazz;

public class ClassNewWithOverride {

    int[] ports;
    String test;


    public ClassNewWithOverride(String test, int... ports){

        this.test = test;
        this.ports = ports;

        System.out.println(">>>>>> "+ports);

    }


    public void setTest(){
        this.test="hh";

    }

    public String getTest(){
        return this.test;
    }


}
