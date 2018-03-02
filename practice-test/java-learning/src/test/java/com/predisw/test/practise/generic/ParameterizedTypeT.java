package com.predisw.test.practise.generic;

import com.predisw.test.practise.ListAsArrayT;
import org.junit.Assert;

import java.lang.reflect.ParameterizedType;

/**
 * Created by eggnwwg on 2/8/2018.
 */
public class ParameterizedTypeT<T>{


    public T printTName(Class<T> clazz) throws IllegalAccessException, InstantiationException {

        Class<? super T> rawType;
        rawType = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(rawType);
        Assert.assertEquals(rawType,clazz);


        return clazz.newInstance();
    }


}
