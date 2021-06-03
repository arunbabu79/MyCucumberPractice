package parallel;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class StepDef {
	WebDriver driver;

	//Utility for taking screenshot	- just like that
		public void takeScreenshot() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
			LocalDateTime now = LocalDateTime.now();
			String dateF = dtf.format(now);
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			System.out.println(currentDir);
			try {
				FileUtils.copyFile(srcFile, new File(currentDir + "\\screenshots\\" + dateF + ".png"));
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		@Given("The user is on facebook.com")
		public void the_user_is_on_facebook_com() throws InterruptedException {
			//System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\ver88\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://www.facebook.com/");
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		}

		@SuppressWarnings("deprecation")
		@When("title of the login page is facebook")
		public void title_of_the_login_page_is_facbook() {
			String expectedTitle = "Facebook - Log In or Sign Up";
			String actualTitle = driver.getTitle();
			Assert.assertEquals(expectedTitle, actualTitle);
		}

		@SuppressWarnings("deprecation")
		@Then("validate that username password and login button is displayed")
		public void validate_that_username_password_and_login_button_is_displayed() {
			Assert.assertTrue(driver.findElement(By.cssSelector("input#email1")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("input#pass1")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("button[name='login']")).isDisplayed());

		}

		@Then("close the browser")
		public void close_the_browser() {
			driver.quit();
		}

		@When("^user enter username (.*)$")
		public void user_enter_username(String username) {
			driver.findElement(By.cssSelector("input#email")).sendKeys(username);
		}

		@When("^user enters password (.*)$")
		public void user_enters_password(String password) {
			driver.findElement(By.cssSelector("input#pass")).sendKeys(password);
		}

		@When("user selects login button")
		public void user_seleects_login_button() throws InterruptedException {
			driver.findElement(By.cssSelector("button[name='login']")).click();
			Thread.sleep(5000);
		}

		@Then("user homepage is displayed")
		public void user_homepage_is_displayed() throws InterruptedException {
			//takeScreenshot();
			Thread.sleep(5000);
			System.out.println("Customer Home page is displayed");
		}

		@After
		public void takeScreenshot(Scenario scenario) {
			if (scenario.isFailed()) {
				byte[] src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.log(scenario.getName() + "is failed");
				scenario.attach(src, "image/png", scenario.getName() + ".png");
				driver.quit();
			}

		}

}
