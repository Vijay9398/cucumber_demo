package com.bdd.test;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com.bdd.step",
        features = "src/test/resources",
        plugin = {"pretty","html:target/html-report/index.html","json:target/json-report/result.json"},
        tags = "@examples_table"
)
public class AnnotationRunner {

}
