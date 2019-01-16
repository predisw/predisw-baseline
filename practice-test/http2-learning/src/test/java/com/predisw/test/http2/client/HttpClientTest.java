package com.predisw.test.http2.client;

import java.io.IOException;
import java.util.Arrays;
import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.Before;
import org.junit.Test;

public class HttpClientTest {

    HttpClient httpClient;
    CallBack callBack;

    private void setupCustomTruststore(){
        String truststore = "C:\\WORK\\work\\http2\\http2-client-trust.p12";
        System.setProperty("javax.net.ssl.trustStore",truststore);
        System.setProperty("javax.net.ssl.trustStorePassword","666666");
        System.setProperty("javax.net.ssl.trustStoreType","PKCS12");
    }

    @Before
    public void init(){
        httpClient = new HttpClient();
        callBack = new CallBack() {
            @Override
            public void onFailure(Request request, Response response, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onSuccess(Request request, Response response) {

                Headers reqHeaders = request.headers();
                for (int i = 0, size = reqHeaders.size(); i < size; i++) {
                    System.out.println(reqHeaders.name(i) + ": " + reqHeaders.value(i));
                }
                System.out.println("Protocol: " + response.protocol());

                System.out.println("----------------"+request.body().toString());
                System.out.println("----------------"+request.body().contentType());



                try(ResponseBody responseBody = response.body()){
                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    System.out.println("Protocol: " + response.protocol());

                    System.out.println(responseBody.string());

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };
    }


    @Test
    public void postAsyncHttp1() throws InterruptedException {
        httpClient.postAsync("http://localhost:12306/","abc",callBack);

        Thread.sleep(3000);
    }


    @Test
    public void postAsyncHttp2() throws InterruptedException {
        setupCustomTruststore();
        httpClient = new HttpClient();
        httpClient.postAsync("https://localhost:5000/","{\"a\":\"b\"}",callBack);

        Thread.sleep(3000);
    }


    @Test
    public void postAsyncHttp2Test2() throws InterruptedException {
        httpClient = new HttpClient();
        httpClient.postAsync("https://http2.akamai.com/demo","{\"a\":\"b\"}",callBack);

        Thread.sleep(10000);
    }

    @Test
    public void postAsyncHttp1_1WithNewClient() throws InterruptedException {
        setupCustomTruststore();
        httpClient = new HttpClient();
        httpClient.getClient().newBuilder().protocols(Arrays.asList(Protocol.HTTP_1_1));
        httpClient.postAsync("https://localhost:5000/","{\"a\":\"b\"}",callBack);

        Thread.sleep(3000);
    }


    @Test
    public void postAsyncHttp2WithNewClient() throws InterruptedException {
        //setupCustomTruststore();
        httpClient = new HttpClient();
        httpClient.getClient().newBuilder().protocols(Arrays.asList(Protocol.H2_PRIOR_KNOWLEDGE));

        httpClient.postAsync("http://http2.akamai.com/demo","{\"a\":\"b\"}",callBack);

        Thread.sleep(3000);
    }


}
