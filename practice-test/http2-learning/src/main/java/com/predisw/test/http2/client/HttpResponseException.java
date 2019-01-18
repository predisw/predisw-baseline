package com.predisw.test.http2.client;

public class HttpResponseException extends RuntimeException {

    private int statusCode;

    public HttpResponseException(int statusCode,Throwable cause){
        super(cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
