package com.predisw.test;

import java.io.IOException;
import java.net.BindException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.nio.reactor.IOReactorExceptionHandler;
import org.junit.Before;
import org.junit.Test;

public class HttpAsyncClientT {

    private CloseableHttpAsyncClient httpclient;


    @Before
    public void init() throws IOReactorException {

        IOReactorConfig config = IOReactorConfig.DEFAULT;
        DefaultConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(config);

        ioReactor.setExceptionHandler(new IOReactorExceptionHandler() {

            public boolean handle(IOException ex) {
                ex.printStackTrace();
                if (ex instanceof BindException) {
                    // bind failures considered OK to ignore
                    return true;
                }
                return false;
            }

            public boolean handle(RuntimeException ex) {
                ex.printStackTrace();
                if (ex instanceof UnsupportedOperationException) {
                    // Unsupported operations considered OK to ignore
                    return true;
                }
                return false;
            }

            public boolean handle(Exception ex) {
                ex.printStackTrace();
                return false;
            }
        });

        PoolingNHttpClientConnectionManager nConnManager = new PoolingNHttpClientConnectionManager(ioReactor);

        httpclient = HttpAsyncClients.custom().setConnectionManager(nConnManager).build();

       // httpclient = HttpAsyncClients.createDefault();

    }


    @Test
    public void ifCallBackInOtherThread() throws InterruptedException, ExecutionException {
        // Start the client
        httpclient.start();
        // Execute request

        System.out.println("the main thread is "+Thread.currentThread().getId());



        final HttpGet request1 = new HttpGet("http://www.apache.org/");
        Future<HttpResponse> future = httpclient.execute(request1, null);
        // and wait until a response is received
        HttpResponse response1 = future.get();
        System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());

        // One most likely would want to use a callback for operation result
        final CountDownLatch latch1 = new CountDownLatch(1);
        final HttpGet request2 = new HttpGet("http://www.apache.org/");
        httpclient.execute(request2, new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse response2) {
                latch1.countDown();
                System.out.println("the callBack thread is "+Thread.currentThread().getId());
                System.out.println(request2.getRequestLine() + "->" + response2.getStatusLine());
            }

            public void failed(final Exception ex) {
                latch1.countDown();
                System.out.println(request2.getRequestLine() + "->" + ex);
            }

            public void cancelled() {
                latch1.countDown();
                System.out.println(request2.getRequestLine() + " cancelled");
            }

        });
        latch1.await();

    }

    @Test
    public void ifCallBackInOtherThreadWhenException() throws InterruptedException, ExecutionException {
        // Start the client
        httpclient.start();
        // Execute request

        System.out.println("the main thread is "+Thread.currentThread().getId());



        final HttpGet request1 = new HttpGet("http://www.apache.org/");
        Future<HttpResponse> future = httpclient.execute(request1, null);
        // and wait until a response is received
        HttpResponse response1 = future.get();
        System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());

        // One most likely would want to use a callback for operation result
        final CountDownLatch latch1 = new CountDownLatch(1);
        final HttpGet request2 = new HttpGet("http://9999.99.99.99/");
        httpclient.execute(request2, new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse response2) {  // invoked at other thread
                latch1.countDown();
                System.out.println("the completed callBack thread is "+Thread.currentThread().getId());
                System.out.println(request2.getRequestLine() + "->" + response2.getStatusLine());
            }

            /**
             * invoked at the main thread either if haven't start to do request.like if new HttpGet("http://999.99.99.99/")
             * which will case unknown host exception, that is the parse exception
             *
             * will also invoked in other thread if already start to do request. like if set new HttpGet("http://99.99.99.99/")
             * which will case java.net.ConnectException: Connection timed out: no further information
             *
             * @param ex
             */
            public void failed(final Exception ex) {
                latch1.countDown();
                System.out.println("the failed callBack thread is "+Thread.currentThread().getId());
                System.out.println(request2.getRequestLine() + "->" + ex);
            }

            public void cancelled() {
                latch1.countDown();
                System.out.println("the cancelled callBack thread is "+Thread.currentThread().getId());
                System.out.println(request2.getRequestLine() + " cancelled");
            }

        });
        latch1.await();

    }


}
