package com.predisw.test.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","html:target/cucumber"})
public class CukeRunner {
    // need to run command like this : mvn clean test -Dtest=CukeRunner
}