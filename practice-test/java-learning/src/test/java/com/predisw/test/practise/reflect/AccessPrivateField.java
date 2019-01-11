package com.predisw.test.practise.reflect;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

public class AccessPrivateField {

    ClassWithPrivateField classWithPrivateField = new ClassWithPrivateField();


    @Before
    public void setup(){
        classWithPrivateField.getSettings().add("The No.2");
    }


    @Test
    public void acessPrivateField() throws NoSuchFieldException, IllegalAccessException {


        Field f = ClassWithPrivateField.class.getDeclaredField("settings");
        f.setAccessible(true);

        List<String> settings = (List<String>) f.get(classWithPrivateField);

        System.out.println("get by reflect before change value: "+settings);

        settings.add("The No.3");

        System.out.println("get by reflect after change value: "+settings);

        System.out.println("getSettings: "+classWithPrivateField.getSettings());



    }


}
