package com.predisw.test.practise.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CollectionT {


    @Test
    public void subClassIfInstanceOfCollection(){

        List<String> stringList = new ArrayList<>();

        Assertions.assertThat(stringList instanceof Collection).isTrue();




    }


}
