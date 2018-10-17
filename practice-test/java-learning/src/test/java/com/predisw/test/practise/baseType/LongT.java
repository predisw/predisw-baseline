package com.predisw.test.practise.baseType;

import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class LongT {

    @Test
    public void equalWithIntegerTest(){

        Long zero = Long.valueOf("0");

        boolean isEqual = Objects.equals(zero,Long.valueOf(0));
        Assertions.assertThat(isEqual).isTrue();

        Assertions.assertThat(zero).isEqualTo(0);
    }

}
