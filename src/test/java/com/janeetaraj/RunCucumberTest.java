package com.janeetaraj;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"}, strict = true)
public class RunCucumberTest {
}
