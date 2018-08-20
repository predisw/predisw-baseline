package com.predisw.test.practise.digital;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class LongT {


    @Test
    public void TestCompareOperator(){

        Assertions.assertThat(Long.valueOf(20000) > 10000).isTrue();
        Assertions.assertThat(new Long(20000) < 10000).isFalse();
        Assertions.assertThat(new Long(20000) == 20000).isTrue();

        Assertions.assertThat(new Long(20000) > new Long(10000)).isTrue();
        Assertions.assertThat(new Long(20000) < new Long(10000)).isFalse();

        Assertions.assertThat(new Long(20000) >= new Long(20000)).isTrue();
        Assertions.assertThat(new Long(20000) == new Long(20000)).isFalse();


    }

    @Test
    public void setNullTolong(){
        long a =  new Test1().getA();
    }


    class Test1{
        Long a;

        public Long getA() {
            return a;
        }

        public void setA(Long a) {
            this.a = a;
        }
    }

}
