package com.predisw.test.java8.concurrent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class CompletableFutureTest {



    @Test
    public void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(s -> result.append(s));
        cf.join();

        System.out.println(result.toString());

        assertTrue("Result was empty", result.length() > 0);
    }



    @Test
    public void completeExceptionallyExample() {

        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase);
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));

//        CompletableFuture exceptionHandler = cf.handle((result, throwable) -> { return (throwable != null) ? "exception occur" : "this is the result"; });

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




}
