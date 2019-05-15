package com.predisw.test.java8.lambda;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by eggnwwg on 9/8/2017.
 */
public class LambdaT1 {

    public static void main(String[] args) {

        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

        features.forEach((n)-> System.out.println(n));

        Collections.sort(features, comparingInt(String::length));

    }





    @Test
    public void comparatorTest(){

        int result = Objects.compare(new Integer(1000),new Long("1000"),(a,b)->{
            if(a.intValue() > b.longValue()){
                return 1;
            }
            if (a.intValue() == b.longValue()) {
                return 0;
            }
            return -1;
        });

        Assertions.assertThat(result).isEqualTo(0);
    }




}
