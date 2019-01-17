package com.predisw.test.http2.client;

public class HttpClientConfig {

    private long connectTimeout;
    private long writeTimeout;
    private long readTimeout;


    private HttpClientConfig(){}


    private HttpClientConfig(Builder builder){
        this.connectTimeout = builder.config.connectTimeout;
        this.writeTimeout = builder.config.writeTimeout;
        this.readTimeout = builder.config.readTimeout;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }


    public long getWriteTimeout() {
        return writeTimeout;
    }


    public long getReadTimeout() {
        return readTimeout;
    }

    public static class Builder{
        private HttpClientConfig config;

        public Builder(){
            config = new HttpClientConfig();
        }

        public Builder connectTimeout(long connectTimeout){
            config.connectTimeout = connectTimeout;
            return this;
        }
        public Builder writeTimeout(long writeTimeout){
            config.writeTimeout = writeTimeout;
            return this;
        }
        public Builder readTimeout(long readTimeout){
            config.readTimeout = readTimeout;
            return this;
        }

        public HttpClientConfig build(){
            return new HttpClientConfig(this);
        }
    }

}
