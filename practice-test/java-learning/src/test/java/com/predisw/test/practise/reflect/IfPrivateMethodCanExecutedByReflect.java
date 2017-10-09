package com.predisw.test.practise.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by eggnwwg on 9/28/2017.
 */
public class IfPrivateMethodCanExecutedByReflect {


    public static void main(String[] args) {

        ClassAndMethodT instance = new ClassAndMethodT();

        Method[] allMethods = ClassAndMethodT.class.getMethods();
        Method[] declaredM = ClassAndMethodT.class.getDeclaredMethods();

        Arrays.asList(allMethods).forEach(method -> System.out.println(method.getName()));

        System.out.println("-------");

        // can get the private method ,but can not execute private method.
        // prove by code below
        Arrays.asList(declaredM).forEach(method -> {
            try {
                method.setAccessible(true );  // but when set access true then can invoke private method !!
                method.invoke(instance, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });


    }
}
