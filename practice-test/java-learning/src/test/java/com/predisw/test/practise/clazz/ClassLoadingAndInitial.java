package com.predisw.test.practise.clazz;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;

public class ClassLoadingAndInitial {



    @Test
    public void testInitialOrder(){

        NotUsed notUsed = null; // won't be loaded if only declare

        Child child = new Child();

        System.out.println((Object) child == (Object) notUsed);

    }


    @Test
    public void testIfStaticFieldInitialWhenLoading(){

        NotUsedWithEagerInstance notUsed_non = null; //print nothing

        NotUsedWithEagerInstance notUsed = NotUsedWithEagerInstance.getNotUsed();

    }

    @Test
    public void onlyStaticWillBeInitial(){

        System.out.println(Parent.static_str); // only print non static block

    }


}
