package com.predisw.test.practise;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by eggnwwg on 6/9/2017.
 */
public class MockitoT {

    @Test
    public void mockitoLearnig(){

        List list = Mockito.mock(List.class);

        list.add("qq");

        Mockito.verify(list).add("qq");


        when(list.get(0)).thenReturn("1");


        Assert.assertEquals(list.get(0),"1");

    }

}
