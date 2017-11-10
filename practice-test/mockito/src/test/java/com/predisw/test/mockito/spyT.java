package com.predisw.test.mockito;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by eggnwwg on 11/10/2017.
 */
public class spyT {

    @Test
    public void spy_Simple_demo(){
        List<String> list = new LinkedList<String>();
        List<String> spy = Mockito.spy(list);  // 是对list 对象的监视，这个对象是真实的，没有被替换。

        when(spy.size()).thenReturn(100);  // spy 后的对象也可以 对其对象进行模拟和预设

        spy.add("one");  // 所以是执行了真正的方法，确实是加了"one" 到list 中去
        spy.add("two");

/*      spy的原理是，如果不"打桩"就默认都会执行真实的方法，如果"打桩"则返回桩实现。"打桩"就是指模拟预设之类，英文stub
        可以看出spy.size()通过桩实现返回了值100，而spy.get(0)则返回了实际值*/
        assertEquals(spy.get(0), "one");
        assertEquals(100, spy.size());
    }

}
