package com.predisw.test.java8.concurrent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;

public class CompletableFutureTest {

    @Test
    public void thenAcceptInMain() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            // output ForkJoinPool.commonPool-worker-1
            Assert.assertNotEquals("main",Thread.currentThread().getName());
            return "thenAccept message";
        })
        .thenAccept(s -> {
            System.out.println(Thread.currentThread().getName());
            Assert.assertEquals("main",Thread.currentThread().getName());
            result.append(s);
        });
        cf.join();

        System.out.println(result.toString());

        assertTrue("Result was empty", result.length() > 0);
    }



    @Test
    public void thenAcceptInThreadPool() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName());
            // output ForkJoinPool.commonPool-worker-1
            Assert.assertNotEquals("main",Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "thenAccept message";
        })
        .thenAccept(s -> {
            System.out.println(Thread.currentThread().getName());
            // also output ForkJoinPool.commonPool-worker-1
            Assert.assertNotEquals("main",Thread.currentThread().getName());
            result.append(s);
        });
        cf.join();

        System.out.println(result.toString());

        assertTrue("Result was empty", result.length() > 0);
    }


    @Test
    public void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s -> {
                    System.out.println(Thread.currentThread().getName());
                    // output ForkJoinPool.commonPool-worker-1
                    Assert.assertNotEquals("main",Thread.currentThread().getName());
                    result.append(s);
                });
        cf.join();

        System.out.println(result.toString());

        assertTrue("Result was empty", result.length() > 0);
    }



    @Test
    public void completeExceptionallyExample() {

        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase);
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));

//        CompletableFuture exceptionHandler = cf.handle((result, throwable) -> { return (throwable != null) ? "exception occur" : "this is the result"; });

        CompletableFuture exceptionHandler2 = cf.whenComplete((result, throwable) -> {});
        CompletableFuture exceptionHandler = cf.exceptionally(ex -> {
            System.out.println(ex.getClass());
            return "exception cc";
        });

        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        try {
            cf.join();
            fail("Should have thrown an exception");
        } catch(CompletionException ex) { // just for testing
            ex.printStackTrace();
            assertEquals("completed exceptionally", ex.getCause().getMessage());
        }


       //assertEquals("exception cc", exceptionHandler.join());
    }

    @Test
    public void cancelExample() {
        ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase);
        CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");
        assertTrue("Was not canceled", cf.cancel(true));
        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        assertEquals("canceled message", cf2.join());


    }


    @Test
    public void allOf(){
        List<Integer> list = Arrays.asList(10,20,30,40);

        List<CompletableFuture<Integer>> futures = list.stream()
                .map(data-> CompletableFuture.supplyAsync(()->{System.out.println("async "+Thread.currentThread().getName());return getNumber(data);}))
                .map(compFuture-> compFuture.thenApply(n-> {System.out.println(Thread.currentThread().getName());return n*n;}))
                .collect(Collectors.toList());


        List<Integer> list2 = CompletableFuture.allOf(futures.toArray(new CompletableFuture[1]))
                .thenApply(v -> {
                    return futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());
                }).join();

        System.out.println(Arrays.toString(list2.toArray()));
    }

    private int getNumber(int a){
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a*a;
    }




}
