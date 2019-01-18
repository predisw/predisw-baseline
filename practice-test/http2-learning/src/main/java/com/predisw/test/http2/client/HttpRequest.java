package com.predisw.test.http2.client;

import java.util.Map;
import okhttp3.MediaType;

public class HttpRequest {
    private String url;
    private String body;
    private Map<String,String> headers;
    private MediaType mediaType = MediaType.parse("application/json; charset=utf-8");


    private HttpRequest(){};

    private HttpRequest(Builder builder){
        this.url= builder.httpRequest.getUrl();
        this.body = builder.httpRequest.getBody();
        this.mediaType = builder.httpRequest.getMediaType();
    }



    public MediaType getMediaType() {
        return mediaType;
    }


    public String getUrl() {
        return url;
    }


    public String getBody() {
        return body;
    }


    public static class Builder{
        private HttpRequest httpRequest;


        public Builder(){
            httpRequest = new HttpRequest();
        }

        public Builder body(String body){
            httpRequest.body = body;
            return this;
        }
        public Builder url(String url){
            httpRequest.url = url;
            return this;
        }
        public Builder mediaType(String mediaType){
            httpRequest.mediaType = MediaType.parse(mediaType);
            return this;
        }

        public HttpRequest build(){
            return new HttpRequest(this);
        }

    }


}
