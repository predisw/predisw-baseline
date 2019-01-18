package com.predisw.test.http2.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class HttpClientTest {

    HttpClient httpClient;
    CallBack callBack;

    HttpClientConfig clientConfig;
    HttpRequest httpRequestLocalV1_1;
    HttpRequest httpRequestLocalV2;
    HttpRequest httpRequestLocalV2c;



    private void setupCustomTruststore(){
        String truststore = "C:\\WORK\\work\\http2\\http2-client-trust.p12";
        System.setProperty("javax.net.ssl.trustStore",truststore);
        System.setProperty("javax.net.ssl.trustStorePassword","666666");
        System.setProperty("javax.net.ssl.trustStoreType","PKCS12");
    }

    @Before
    public void init(){

        httpRequestLocalV1_1 = new HttpRequest.Builder()
                .url("http://localhost:12306/")
                .body("{\"a\":\"b\"}")
                .build();

        httpRequestLocalV2 = new HttpRequest.Builder()
                .url("https://localhost:50000/")
                .body("{\"a\":\"b\"}")
                .mediaType("ddddd")
                .build();


        httpRequestLocalV2c = new HttpRequest.Builder()
                .url("http://localhost:50000/")
                .body("{\"a\":\"b\"}")
                .build();

        clientConfig = new HttpClientConfig.Builder()
                .connectTimeout(10000)
                .readTimeout(30000)
                .writeTimeout(10000)
                .build();
        httpClient = new HttpClient(clientConfig);
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
    public void postWithResponseAsyncHttp1() throws InterruptedException {
        httpClient.postAsync("http://localhost:12306/","abc",callBack);

        Thread.sleep(3000);
    }


    @Test
    public void postWithResponseAsyncHttp2() throws InterruptedException {
        setupCustomTruststore();
        httpClient = new HttpClient(clientConfig);
        httpClient.postAsync("https://localhost:50000/","{\"a\":\"b\"}",callBack);

        Thread.sleep(3000);
    }


    @Test
    public void postWithResponseAsyncHttp2Test2() throws InterruptedException {
        httpClient = new HttpClient(clientConfig);
        httpClient.postAsync("https://http2.akamai.com/demo","{\"a\":\"b\"}",callBack);

        Thread.sleep(10000);
    }

    @Test
    public void postWithResponseAsyncHttp1_1WithNewClient() throws InterruptedException {
        setupCustomTruststore();
        httpClient = new HttpClient(clientConfig);
        httpClient.getClient().newBuilder().protocols(Arrays.asList(Protocol.HTTP_1_1));
        httpClient.postAsync("https://localhost:50000/","{\"a\":\"b\"}",callBack);

        Thread.sleep(3000);
    }


    @Test
    public void postWithResponseAsyncHttp2WithNewClient() throws InterruptedException {
        //setupCustomTruststore();
        httpClient = new HttpClient(clientConfig);
        httpClient.getClient().newBuilder().protocols(Arrays.asList(Protocol.H2_PRIOR_KNOWLEDGE));

        httpClient.postAsync("http://http2.akamai.com/demo","{\"a\":\"b\"}",callBack);

        Thread.sleep(3000);
    }


    @Test
    public void postWithResponseAsync1_1WithRequestConfigTest(){

        HttpRequest httpRequest = new HttpRequest.Builder()
                .url("http://localhost:12306/")
                .body("{\"a\":\"b\"}")
                .build();

        CompletableFuture<Response> future = httpClient.postWithResponseAsync(httpRequest);
        String resBody = future.thenApply(response -> {
            String body="";
            try(ResponseBody responseBody = response.body()){
                if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                try {
                    body = responseBody.string();
                    System.out.println(body);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return body;
        }).join();

        System.out.println(resBody);

    }



    @Test
    public void postWithAsyncHttp1_1(){

       HttpResponse httpResponse =  httpClient.postAsync(httpRequestLocalV1_1).join();

        System.out.println(httpResponse);

        Assertions.assertThat(httpResponse.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void postWithResponseAsyncHttp1_1Test(){

        Response response = httpClient.postWithResponseAsync(httpRequestLocalV1_1).join();

        System.out.println(response);

        Assertions.assertThat(response.code()).isEqualTo(200);
        Assertions.assertThat(response.protocol()).isEqualTo(Protocol.HTTP_1_1);
    }


    @Test
    public void postWithResponseAsyncHttp2_Test(){
        setupCustomTruststore();
        httpClient = new HttpClient(clientConfig);
        Response response = httpClient.postWithResponseAsync(httpRequestLocalV2).join();

        System.out.println(response);

        Assertions.assertThat(response.code()).isEqualTo(200);
        Assertions.assertThat(response.protocol()).isEqualTo(Protocol.HTTP_2);
    }


    @Test
    public void postWithResponseAsyncHttp2_ClearTextTest(){

        httpClient.setClient(
                httpClient.getClient()
                .newBuilder()
                .protocols(Arrays.asList(Protocol.H2_PRIOR_KNOWLEDGE))
                .build());
        Response response = httpClient.postWithResponseAsync(httpRequestLocalV2c).join();

        System.out.println(response);

        Assertions.assertThat(response.code()).isEqualTo(200);
        Assertions.assertThat(response.protocol()).isEqualTo(Protocol.HTTP_2);
    }

}
