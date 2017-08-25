package com.predisw.test.practise;

import java.util.HashMap;

public class Plugin {

    public void init(HashMap<String, String> parameters){
        System.out.println("INIT "+parameters);
    }

    public void start(HashMap<String, String> parameters){
        System.out.println(parameters);
    }
}
