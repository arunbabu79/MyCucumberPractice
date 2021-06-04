package parallel;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "/Users/Arun/git/MyCucumberPractice/fb.cucumber/src/test/resources/parallel", //path of feature file
		glue = {"parallel"}, //path of step definition,
		plugin = {"html:Report/cucumber-html-report.html", 
				   "json:Report/cucumber-json-report.json","junit:Report/cucumber-junit-report.xml",
				   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, //to generate different report types
		dryRun=false,
		monochrome = true
					)
public class TestRunner {

}
