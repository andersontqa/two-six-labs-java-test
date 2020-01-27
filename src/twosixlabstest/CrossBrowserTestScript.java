package twosixlabstest;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

public class CrossBrowserTestScript {
	WebDriver driver;
	
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception {
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else {
			throw new Exception("Browser is not correct.");
		}
	}
	
	@Test
	@Parameters("browser")
	public void testAuthorName(String browser) {
	  // Navigate to google
	  driver.get("https://www.google.com");
	  // Locate search text box
	  WebElement search = driver.findElement(By.cssSelector("input[title='Search']"));
	  // Enter desired text into search box
	  search.sendKeys("cyber-warriors-lack-planning-tools-that-could-change");
	  // Submit the google search query
	  search.submit();
	  // Locate and click on the first search result
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  WebElement first_item = null;
	  // Noticed a Div element would disappear and then reappear at random times
	  try {
		  first_item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rso > div:nth-child(1) > div > div > div > div > div > div:nth-child(3) > div > div > div > a")));
	  } catch(NoSuchElementException e) {
		  first_item = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rso > div:nth-child(1) > div > div > div > div > div > div:nth-child(2) > div > div > div > a")));
	  }
	  first_item.click();
	  //Locate two locations where author name is displayed
	  String author_name_top = driver.findElement(By.cssSelector("#f0mcELYnsjf5vr > div > div.m-byline__meta.a-body1 > div.m-byline__author > span > span > a")).getText();
	  String author_name_bottom = driver.findElement(By.cssSelector("#f0XdmP5nsjf5vr > div > h6 > a")).getText();
	  
	  //Assert top author name and take screenshots if assertion is valid or not
	  CrossBrowserScreenshot cbs = new CrossBrowserScreenshot(browser);
	  try {
		  Assert.assertEquals(author_name_top, "Mark Pomerleau");
		  cbs.getCrossBrowserScreenshot("correct_author_name_top.png", driver);
	  } catch(AssertionError e) {
		  cbs.getCrossBrowserScreenshot("wrong_author_name_top.png", driver);
		  throw e;
	  	}
	  
	  // Move to element wasn't actually putting the element in the viewport. Best solution for now
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("window.scrollTo(0, 5300)");
	  
	  //Assert bottom author name and take screenshots if assertion is valid or not
	  try {
		  Assert.assertEquals(author_name_bottom, "Mark Pomerleau");
		  cbs.getCrossBrowserScreenshot("correct_author_name_bottom.png", driver);
	  } catch(AssertionError e) {
		  cbs.getCrossBrowserScreenshot("wrong_author_name_bottom.png", driver);
		  throw e;
	  	}
	}
	
	@AfterTest
	public void doAfterTest() {
	     driver.close();
	}
}
