package com.predisw.test.practise.generic;

import com.predisw.test.practise.ListT;

/**
 * Created by eggnwwg on 2/8/2018.
 */
public class ParameterizedTypeTChild extends ParameterizedTypeT<ListT> {


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        new ParameterizedTypeTChild().printTName(ListT.class);

    }

}
