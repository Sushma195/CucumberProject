package runner;

import org.junit.runner.RunWith;

//import com.cucumber.listener.Reporter;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/", glue = {
		"stepDefinations" }, monochrome = true, dryRun = false, plugin = { "pretty", "html:target/cucumber",
				"json:target/cucumber.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
// "com.cucumber.listener.ExtentCucumberFormatter:output/report.html" })

public class MainRunner extends AbstractTestNGCucumberTests {

}
