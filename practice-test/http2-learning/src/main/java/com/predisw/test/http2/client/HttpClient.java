package com.predisw.test.http2.client;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Credentials;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

public class HttpClient {

    //1. how to set MediaType to request ?
    //2. how to set custom header to request ?
    //3. set authentication info to request if not null on build chain ?
    //4. how to retry
    //5. gather configuration by ClientConfig class
    //6. adapter okHttp response to custom httpResponse.
    //7. custom thread pool for dispatch ??

    public static final MediaType MEDIA_TYPE_JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client;

    private String userName;
    private String password;


    public HttpClient(){
        client = new OkHttpClient.Builder()
                .authenticator(authenticator())
                .dispatcher(dispatcher())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
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

        if(Objects.nonNull(userName) && Objects.nonNull(password)){
            String credential = Credentials.basic(userName, password);
            reqBuilder.header("Authorization", credential);
        }

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


    private Authenticator authenticator(){
        Authenticator authenticator = new Authenticator() {
            @Nullable
            @Override
            public Request authenticate(@Nullable Route route, Response response) throws IOException {
                if (response.request().header("Authorization") != null) {
                    // Give up, we've already attempted to authenticate.
                    return null;
                }

                System.out.println("Authenticating for response: " + response);
                System.out.println("Challenges: " + response.challenges());


                if(Objects.nonNull(userName) && Objects.nonNull(password)){
                    String credential = Credentials.basic(userName, password);
                    return response.request().newBuilder()
                            .header("Authorization", credential)
                            .build();
                }
                return null;
            }
        };
        return authenticator;
    }

    private Dispatcher dispatcher(){
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(20);
        dispatcher.setMaxRequests(300);
        return dispatcher;
    }


    public OkHttpClient getClient() {
        return client;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }
}
