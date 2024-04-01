package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


// To run multiple feature file we need to use {".//Features/Login.feature", ".//Features/Customers.feature"}
// To run multiple step file same above format we need to follow

@RunWith(Cucumber.class)
@CucumberOptions
      (
	    features = ".//Features/",
	    glue = "stepDefinitions",
	    dryRun = false,
	    monochrome=true,
	    plugin = {"pretty","html:test-output"},
	    tags = "@regression or @sanity"
      ) 

public class Test_Run {


}
