package com.predisw.test.http2.client;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Credentials;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;

public class HttpClient {

    //1. how to set MediaType/username/pw to request ?
    //2. how to set custom header to request ?
    //3. set authentication info to request if not null on build chain ?
    //4. how to retry !!
    //5. gather configuration by HttpClientConfig class
    //6. adapter okHttp response to custom httpResponse.
    //7. relation between http request number and thread count  !!

    public static final MediaType MEDIA_TYPE_JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client;

    public HttpClient(HttpClientConfig httpClientConfig, ThreadPoolExecutor threadPoolExecutor){
        this(httpClientConfig);
        client = client.newBuilder()
                .dispatcher(dispatcher(threadPoolExecutor))
                .build();
    }

    public HttpClient(HttpClientConfig httpClientConfig){
        client = new OkHttpClient.Builder()
                .dispatcher(dispatcher())
                .connectTimeout(httpClientConfig.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(httpClientConfig.getWriteTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(httpClientConfig.getReadTimeout(), TimeUnit.MILLISECONDS)
                .build();
    }


    public void postAsync(String url, String body, CallBack callBack){
        postAsync(url,body,MEDIA_TYPE_JSON,callBack);
    }


    public void postAsync(String url, String body, MediaType mediaType,CallBack callBack){

        HttpUrl httpUrl = HttpUrl.parse(url);

        Request.Builder reqBuilder = new Request.Builder()
                .url(httpUrl)
                .post(RequestBody.create(mediaType, body));

        Request request = reqBuilder.build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(call.request(),null,e);
            }

            @Override
            public void onResponse(Call call, Response response)  {
                if (!response.isSuccessful()) {
                    callBack.onFailure(call.request(),response,new IOException("Unexpected code " + response));
                }
                callBack.onSuccess(call.request(),response);
            }

        });
    }


    public CompletableFuture<Response> postAsync(RequestConfig reqConfig){

        HttpUrl httpUrl = HttpUrl.parse(reqConfig.getUrl());

        Request.Builder reqBuilder = new Request.Builder()
                .url(httpUrl)
                .post(RequestBody.create(reqConfig.getMediaType(), reqConfig.getBody()));

        if(Objects.nonNull(reqConfig.getUserName()) && Objects.nonNull(reqConfig.getPassword())){
            String credential = Credentials.basic(reqConfig.getUserName(), reqConfig.getPassword());
            reqBuilder.header("Authorization", credential);
        }

        Request request = reqBuilder.build();

        return async(client.newCall(request));
    }



    public CompletableFuture<Response> postAsync(String url, String body){
        return postAsync(url,body,MEDIA_TYPE_JSON);
    }


    public CompletableFuture<Response> postAsync(String url, String body, MediaType mediaType){

        HttpUrl httpUrl = HttpUrl.parse(url);

        Request.Builder reqBuilder = new Request.Builder()
                .url(httpUrl)
                .post(RequestBody.create(mediaType, body));

        Request request = reqBuilder.build();

        return async(client.newCall(request));
    }



    private Dispatcher dispatcher(){
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, 100, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util
                .threadFactory("OkHttp Dispatcher", false));
        return dispatcher(executorService);
    }

    private Dispatcher dispatcher(ThreadPoolExecutor threadPoolExecutor){
        int maxThread = threadPoolExecutor.getMaximumPoolSize();
        Dispatcher dispatcher = new Dispatcher(threadPoolExecutor);
        dispatcher.setMaxRequestsPerHost(maxThread);
        dispatcher.setMaxRequests(maxThread);
        return dispatcher;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }


    public CompletableFuture<Response> async(Call call){

        CompletableFuture<Response> completedFuture = new CompletableFuture<Response>(){
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                call.cancel();
                return super.cancel(mayInterruptIfRunning);
            }
        };

        okhttp3.Callback callback =  new okhttp3.Callback(){
            @Override
            public void onFailure(Call request, IOException e) {
                completedFuture.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call request, Response response) {
                completedFuture.complete(response);
            }
        };

        call.enqueue(callback);

        return completedFuture;
    }
}
