package com.predisw.test.practise.cocurrent;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eggnwwg on 3/2/2018.
 */
public class AtomicIntegerT {


    @Test
    public void ifAtomicIntThreadSafe() throws InterruptedException { // yes, it is

        AtomicInteger number = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        int max = 200;

        for(int i =0 ;i<max;i++){
            executor.execute(new Task(number));
        }

        Thread.sleep(2000);

        Assertions.assertThat(number.get() == max);
    }


    class Task implements Runnable{
        private AtomicInteger num;

        public Task(AtomicInteger num){
            this.num =num;
        }

        @Override
        public void run() {
            num.getAndIncrement();
            System.out.println(Thread.currentThread().getName());

        }
    }

}
