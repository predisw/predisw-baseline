package com.predisw.test.http2.client;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private Logger logger = LoggerFactory.getLogger(this.getClass());


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






    public CompletableFuture<Response> postAsync(String url, String body){
        return postAsync(url,body,MEDIA_TYPE_JSON);
    }


    public CompletableFuture<Response> postAsync(String url, String body, MediaType mediaType){

        HttpUrl httpUrl = HttpUrl.parse(url);

        Request.Builder reqBuilder = new Request.Builder()
                .url(httpUrl)
                .post(RequestBody.create(mediaType, body));

        Request request = reqBuilder.build();

        return okHttp3Async(client.newCall(request));
    }


    public CompletableFuture<Response> postWithResponseAsync(HttpRequest reqConfig){

        HttpUrl httpUrl = HttpUrl.parse(reqConfig.getUrl());

        Request.Builder reqBuilder = new Request.Builder()
                .url(httpUrl)
                .post(RequestBody.create(reqConfig.getMediaType(), reqConfig.getBody()));

        Request request = reqBuilder.build();

        return okHttp3Async(client.newCall(request));
    }

    public void postAsync(HttpRequest httpRequest,CallBack callBack){

    }


    public CompletableFuture<HttpResponse> postAsync(HttpRequest httpRequest){

        CompletableFuture<Response> response = postWithResponseAsync(httpRequest);

        CompletableFuture<HttpResponse> httpRes = response.handle((res,ex) -> {
            if(ex != null){
                return handleException(ex);
            }
            int statusCode = res.code();
            try (ResponseBody responseBody = res.body()) {

                HttpResponse.Builder builder = new HttpResponse.Builder();
                builder.statusCode(statusCode);

                String bodyContent = responseBody.string();
                builder.body(bodyContent);

                return builder.build();
            }catch (Exception e){

                return handleException(statusCode,e);
            }
        });

        return httpRes;
    }

    private HttpResponse handleException(Throwable th){
        return handleException(500,th);
    }

    private HttpResponse handleException(int statusCode, Throwable th){
        logger.error("",th);
        HttpResponse httpResponse =  new HttpResponse.Builder()
                .statusCode(statusCode)
                .body(th.getMessage())
                .build();
        return httpResponse;
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


    public CompletableFuture<Response> okHttp3Async(Call call){

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
