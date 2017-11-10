package com.predisw.test.guava;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * Created by eggnwwg on 11/6/2017.
 */
public class PreConditionsT {


    @Test
    public void checkArguments(){

        String s="abc";

        Preconditions.checkArgument(s.length()>4,"%s length is not enough",s); // throw IllegalArgumentException: abc length is not enough


    }




}
