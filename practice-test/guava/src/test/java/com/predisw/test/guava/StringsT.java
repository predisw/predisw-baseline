package com.predisw.test.guava;

import com.google.common.base.Strings;
import org.junit.Test;
/**
 * Created by eggnwwg on 11/6/2017.
 */
public class StringsT {


    @Test
    public void testStrings(){

        System.out.println(Strings.emptyToNull(""));

        System.out.println(Strings.padEnd("hh",4,'o')); // hhoo

        System.out.println(Strings.repeat("-",2));

        System.out.println(Strings.commonPrefix("dd","dddabcd")); // dd
    }

}
