package testRunners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Hetty Gouw
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		strict=false,
		features="resources/features",
		glue= {"stepDefinitions"},
		plugin = { "html:target/report/html",
			    "json:target/report/json/test-output.json"}
		)
public class ValuesTestRunner {

}
