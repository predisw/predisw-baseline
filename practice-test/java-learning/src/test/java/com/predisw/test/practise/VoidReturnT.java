package com.predisw.test.practise;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by eggnwwg on 6/20/2017.
 */
public class VoidReturnT {

    public static void main(String[] args) throws Exception {
        String ret =new VoidReturnT().voidCastToStringInReflect();
        System.out.println(ret);

    }

    @Test
    public String voidCastToStringInReflect() throws Exception{

        HashMap<String,String> hashMap = new HashMap<>();

        Method method = Plugin.class.getMethod("init",HashMap.class);

        return (String)method.invoke(Plugin.class.newInstance(),hashMap);


    }

//    public String voidCastToString(){
//        HashMap<String,String> hashMap = new HashMap<>();
//
//        return (String) new Plugin().init(hashMap);
//
//    }
}
