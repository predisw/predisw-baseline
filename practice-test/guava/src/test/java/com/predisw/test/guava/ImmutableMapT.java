package com.predisw.test.guava;

import com.google.common.collect.ImmutableMap;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eggnwwg on 2/6/2018.
 */
public class ImmutableMapT {


    @Test
    public void testIfMutable(){

        ImmutableMap<String,String> imMap = ImmutableMap.of("key","value");

        System.out.println(imMap.get("key"));

        imMap.clear();
        imMap.remove("key");

        System.out.println(imMap.get("key"));

    }
}
