package com.predisw.test.http2.client;

import okhttp3.MediaType;

public class RequestConfig {
    private String url;
    private String body;

    private String userName;
    private String password;
    private MediaType mediaType = MediaType.parse("application/json; charset=utf-8");


    private RequestConfig(){};

    private RequestConfig(Builder builder){
        this.url= builder.requestConfig.getUrl();
        this.body = builder.requestConfig.getBody();
        this.userName = builder.requestConfig.getUserName();
        this.password = builder.requestConfig.getPassword();
        this.mediaType = builder.requestConfig.getMediaType();
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
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
        private RequestConfig requestConfig;


        public Builder(){
            requestConfig = new RequestConfig();
        }

        public Builder body(String body){
            requestConfig.body = body;
            return this;
        }
        public Builder url(String url){
            requestConfig.url = url;
            return this;
        }
        public Builder mediaType(String mediaType){
            requestConfig.mediaType = MediaType.parse(mediaType);
            return this;
        }

        public Builder username(String userName){
            requestConfig.userName = userName;
            return this;
        }
        public Builder password(String pw){
            requestConfig.password = pw;
            return this;
        }

        public RequestConfig build(){
            return new RequestConfig(this);
        }

    }


}
