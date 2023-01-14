package com.lms.api.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Main Runner class to run all cucumber feature files
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/", 
                  glue = { "com/lms/api/stepDefinition" },
                  monochrome = true, 
                  dryRun = false, 
                  plugin = { "pretty", "html:target/lms_api_logs.html",
				              "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" })

public class TestRunner {

}