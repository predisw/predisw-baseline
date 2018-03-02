package com.predisw.test.practise;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eggnwwg on 2/6/2018.
 */
public class HashMapT {

    @Test
    public void ChangeMapFieldRefByGetMethod(){

        Map<String,String> inerMap = new HashMap<>();

        Map<String,Map<String,String>> map = new HashMap<>();

        inerMap.put("name","superMan");
        map.put("person",inerMap);

        Map<String,String> person =  map.get("person");

        person = null;

        System.out.println(map.get("person").get("name"));

        Assert.assertEquals(map.get("person").get("name"),"superMan");



    }

}
