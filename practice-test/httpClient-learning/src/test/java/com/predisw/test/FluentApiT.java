package com.predisw.test;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.github.dreamhead.moco.Moco.httpServer;


/*
* Fluent facade API exposes only the most fundamental functions of HttpClient and is intended for simple use cases
* that do not require the full flexibility of HttpClient.
*
* For instance, fluent facade API relieves the users from having to deal with connection management and resource deallocation.
*
* */
public class FluentApiT {


    @Before
    public void startHttpMocoServer(){

        HttpServer server = httpServer(8080);
        server.response("foo");

        Runner runner = Runner.runner(server);

        runner.start();

    }


    @Test
    public void httpConnectionTimeOut() throws IOException {

        Request.Get("http://localhost:8080/")
                .connectTimeout(1000) // connecting server timeout
                .socketTimeout(1000)  // read response timeout
                .execute().returnContent().asString();

    }


    @Test
    public void postWithForm() throws IOException {

        Request.Post("http://localhost:8080/")
                .bodyForm(Form.form().add("username",  "vip").add("password",  "secret").build())
                .execute().returnContent();

    }


/*
    One can also use Executor directly in order to execute requests in a specific security context
     whereby authentication details are cached and re-used for subsequent requests.
*/

    public void reuseSettingForRequest() throws IOException {

        Executor executor = Executor.newInstance()
                .auth(new HttpHost("somehost"), "username", "password")
                .auth(new HttpHost("myproxy", 8080), "username", "password")
                .authPreemptive(new HttpHost("myproxy", 8080));

        executor.execute(Request.Get("http://somehost/"))
                .returnContent().asString();

        executor.execute(Request.Post("http://somehost/do-stuff")
                .useExpectContinue()
                .bodyString("Important stuff", ContentType.DEFAULT_TEXT))
                .returnContent().asString();

    }



    // add handler to deal response in Async request



}
