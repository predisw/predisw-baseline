package com.predisw.test.moco;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Moco;
import com.github.dreamhead.moco.MocoConfig;
import com.github.dreamhead.moco.Runner;
import com.github.dreamhead.moco.config.MocoFileRootConfig;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.github.dreamhead.moco.Moco.by;
import static com.github.dreamhead.moco.Moco.file;
import static com.github.dreamhead.moco.Moco.httpServer;
import static com.github.dreamhead.moco.Runner.runner;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MocoRunnerTest {

    private Runner runner;

    @Before
    public void setup() {

        MocoConfig<String> config = new MocoFileRootConfig("src/test/resources/mocoConfig.json");
        HttpServer server = Moco.httpServer(12306,config);
        //server.request(by(file("src/test/resources/com/predisw/test/moco/foo_request.json"))).response("bar");
        //server.request(by(file("src/test/resources/mocoConfig.json"))).response("bar");

        runner = runner(server);

        runner.start();

    }

    @After
    public void tearDown() {
        runner.stop();
    }

    @Test
    public void should_response_as_expected() throws IOException {

        Content content = Request.Get("http://localhost:12306").execute().returnContent();

//        Response response=Request.Post("http://localhost:12306").bodyFile(new File("src/test/resources/com/predisw/test/moco/foo_request.json"), ContentType.APPLICATION_JSON).execute();
//        Content content = response.returnContent();
        System.out.println(content.asString());
        //assertThat(content.asString(), is("foo"));
    }
}
