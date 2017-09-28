package com.predisw.test.java8.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by eggnwwg on 9/8/2017.
 */
public class LambdaT1 {

    public static void main(String[] args) {

        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

        features.forEach((n)-> System.out.println(n));
    }


}
