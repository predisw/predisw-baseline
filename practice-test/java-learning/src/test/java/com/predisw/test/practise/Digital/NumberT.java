package com.predisw.test.practise.digital;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by eggnwwg on 3/28/2018.
 */
public class NumberT {

    @Test
    public void testInteger(){

        Assertions.assertThat(new Integer(10) == new Integer(10));

        Assertions.assertThat(new Integer(1000) != new Integer(1000));
        // so better to use the equals method to compare

        Assertions.assertThat(1000 == 1000);

        Assertions.assertThat(1000L == 1000L);
    }


    @Test
    public void testIntegerCompare() {

        Assertions.assertThat(new Integer(10) == new Integer(10));

        Assertions.assertThat(new Integer(1000) != new Integer(1000));
        // so better to use the equals method to compare

        Assertions.assertThat(new Integer(20000)).isGreaterThan(10000);

        Assertions.assertThat(new Integer(20000) > 10000).isTrue();

        Assertions.assertThat(new Integer(20000) < 10000).isFalse();


    }


}
