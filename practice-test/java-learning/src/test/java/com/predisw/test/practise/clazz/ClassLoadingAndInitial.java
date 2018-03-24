package com.predisw.test.practise.clazz;

import org.junit.Test;

public class ClassLoadingAndInitial {



    @Test
    public void test1(){

        NotUsed notUsed = null; // won't be loaded if only declare

        Child child = new Child();

        System.out.println((Object) child == (Object) notUsed);

    }


    @Test
    public void testIfStaticFieldInitialWhenLoading(){

        NotUsedWithEagerInstance notUsed_non = null; //print nothing

        NotUsedWithEagerInstance notUsed = NotUsedWithEagerInstance.getNotUsed();

    }

}
