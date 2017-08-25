package com.predisw.test.cucumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Before;



public class HelloWorld {

    Logger logger =Logger.getLogger(HelloWorld.class.getName());

    private String something;


    @Given("^the java version 1.8$")
    public void printJdkVersion() throws IOException, InterruptedException {
        Process proc=Runtime.getRuntime().exec("java -version");
        proc.waitFor();
        final BufferedReader outputReader = new BufferedReader(new InputStreamReader(proc
                .getInputStream()));
        final BufferedReader errorReader = new BufferedReader(new InputStreamReader(proc
                .getErrorStream()));

        String line;

        while ((line = outputReader.readLine()) != null) {
            System.out.println(line);
        }

        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }
    }


    @When("^i want to say \"([^\"]*)\"$")
    public void iWantSaySomething(String someWords){

        something=someWords;

    }

    @Then("^the program output hello world$")
    public void outputTheWords(){

        System.out.println("i want to say "+something);
        Assert.assertEquals(something,"hello world");
    }
}
