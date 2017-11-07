package com.predisw.test.practise.cocurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadT {

    @Test
    public void joinT() throws InterruptedException {

        long startM = System.currentTimeMillis();

        Thread t1 = new Thread(()->{
            try {
                Thread.currentThread().sleep(3000);
                System.out.println("wake up...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();

        t1.join();  // the main thread will wait t1 terminate to run next step

        System.out.println("if thread t1 terminate");

        System.out.println(System.currentTimeMillis() - startM);


    }


    @Test
    public void countDownLatch() throws InterruptedException {

        long startM = System.currentTimeMillis();



        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executors = Executors.newFixedThreadPool(3);

        for(int i=0;i<3;i++){
            executors.submit(getThread(latch));
        }


        // 等子线程都执行完毕后就会wake 然后继续执行
        latch.await(7, TimeUnit.SECONDS);


        System.out.println(System.currentTimeMillis() - startM); // output 2088


    }


    private Thread getThread(CountDownLatch latch){

        return new Thread(()->{

            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            latch.countDown();

        });


    }




}
