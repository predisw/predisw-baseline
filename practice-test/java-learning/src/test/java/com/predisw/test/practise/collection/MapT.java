package com.predisw.test.practise.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapT {

    @Test
    public void keySetIfCanChange(){

        Map<String,String> names = new HashMap<>();
        names.put("pre","boy");
        names.put("viw","isBoy?");

        names.keySet().remove("pre"); // remove successfully!! strange

        names.keySet().add("error?"); // throw UnsupportedOperationException

        System.out.println(names);


    }
}
