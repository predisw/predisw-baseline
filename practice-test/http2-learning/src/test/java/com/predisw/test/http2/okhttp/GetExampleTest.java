package com.predisw.test.http2.okhttp;

import java.io.IOException;
import org.junit.Test;

public class GetExampleTest {

    private void setupCustomTruststore(){
        String truststore = "C:\\WORK\\work\\http2\\http2-client-trust.p12";
        System.setProperty("javax.net.ssl.trustStore",truststore);
        System.setProperty("javax.net.ssl.trustStorePassword","666666");
        System.setProperty("javax.net.ssl.trustStoreType","PKCS12");
    }


    @Test
    public void getOkhttpGithubServer() throws IOException {
        GetExample example = new GetExample();
        String response = example.runSimpleGet("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }
    @Test
    public void getLocalhostHttp2Server() throws IOException {
        setupCustomTruststore();
        GetExample example = new GetExample();
        String response = example.runSimpleGet("https://localhost:50000/");
        System.out.println(response);
    }

    @Test
    public void getSyncGet1() throws IOException {
        GetExample example = new GetExample();
        example.runSyncGet("https://publicobject.com/helloworld.txt");
    }

    @Test
    public void getSyncGetToHttp2() throws IOException {
        setupCustomTruststore();
        GetExample example = new GetExample();
        example.runSyncGet("https://localhost:50000/");
    }


    @Test
    public void getSyncGetToHttp2TestDemo() throws IOException {
        // using default truststore
        GetExample example = new GetExample();
        example.runSyncGet("https://http2.akamai.com/demo");
    }


    @Test
    public void getAsyncGet1() throws IOException, InterruptedException {
        GetExample example = new GetExample();
        example.runAsyncGet("http://publicobject.com/helloworld.txt");
        Thread.sleep(3000);

    }


    @Test
    public void getAsyncGetToHttp2() throws IOException, InterruptedException {
        setupCustomTruststore();
        GetExample example = new GetExample();
        example.runAsyncGet("https://localhost:50000/");
        Thread.sleep(3000);
    }



    @Test
    public void getAsyncGetToHttp2TestDemo() throws IOException, InterruptedException {
        // using default truststore
        GetExample example = new GetExample();
        example.runAsyncGet("https://http2.akamai.com/demo");
        Thread.sleep(10000);
    }

}
