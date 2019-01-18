package com.predisw.test.http2.client;

import okhttp3.Headers;

public class HttpResponse {
    private int statusCode;
    private String body;
    private Headers headers;


    private HttpResponse(){}
    private HttpResponse(Builder builder){
        this.statusCode = builder.response.statusCode;
        this.body = builder.response.body;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "statusCode=" + statusCode +
                ", body='" + body + '\'' +
                '}';
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }



    public static class Builder{
        private HttpResponse response;

        public Builder(){
            response = new HttpResponse();
        }


        public Builder statusCode(int statusCode){
            response.statusCode = statusCode;
            return this;
        }

        public Builder body(String body){
            response.body = body;
            return this;
        }

        public HttpResponse build(){
            return new HttpResponse(this);
        }

    }


}
