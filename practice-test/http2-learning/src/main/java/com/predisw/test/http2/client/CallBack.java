package com.predisw.test.http2.client;

import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;

public interface CallBack {

    void onFailure(Request request, Response response, IOException e);

    void onSuccess(Request request,Response response);
}
