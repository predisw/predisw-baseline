package com.predisw.test.practise.cocurrent;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

/**
 * Created by eggnwwg on 10/13/2017.
 */
public class QueueT {


    @Test
    public void queuePeek(){

        Queue<String> q = new ArrayBlockingQueue(4);

        for(int i=0;i<4;i++){
            q.add(String.valueOf(i));
        }

        for(int i=0;i<4;i++){
            System.out.println(q.peek());  // if retrieve the same value,The answer is yes
            // all output 0
        }


    }


}
