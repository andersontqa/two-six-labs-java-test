package twosixlabstest;

import java.io.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ChromeTestScript {
	public WebDriver driver = new ChromeDriver();
	
	@Test
	public void testAuthorName() {
	  // Navigate to google
	  driver.get("https://www.google.com");
	  // Locate search text box
	  WebElement search = driver.findElement(By.cssSelector("input[title='Search']"));
	  // Enter desired text into search box
	  search.sendKeys("cyber-warriors-lack-planning-tools-that-could-change");
	  // Submit the google search query
	  search.submit();
	  // Locate and click on the first search result
	  WebElement first_item = driver.findElement(By.cssSelector("#rso > div:nth-child(1) > div > div > div > div > div > div:nth-child(3) > div > div > div > a"));
	  first_item.click();
	  //Locate two locations where author name is displayed
	  String author_name_top = driver.findElement(By.cssSelector("#f0mcELYnsjf5vr > div > div.m-byline__meta.a-body1 > div.m-byline__author > span > span > a")).getText();
	  String author_name_bottom = driver.findElement(By.cssSelector("#f0XdmP5nsjf5vr > div > h6 > a")).getText();
	  
	  //Assert top author name and take screenshots if assertion is valid or not
	  try {
		  Assert.assertEquals(author_name_top, "Mark Pomerleau");
		  File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  try {
		  	FileUtils.copyFile(src, new File("correct_author_name_top.png"));
		  } catch(IOException e) {
			  System.out.println(e.getMessage());
		  }
	  } catch(AssertionError e) {
		  File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  try {
			  	FileUtils.copyFile(src, new File("wrong_author_name_top.png"));
			  } catch(IOException ee) {
				  System.out.println(e.getMessage());
			  }
	  }
	  
	  //Scroll to bottom author name so it is viewport for screenshot
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("window.scrollTo(0, 5093)");
	  
	  //Assert bottom author name and take screenshots if assertion is valid or not
	  try {
		  Assert.assertEquals(author_name_bottom, "Mark Pomerleau");
		  File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  try {
		  	FileUtils.copyFile(src, new File("correct_author_name_bottom.png"));
		  } catch(IOException e) {
			  System.out.println(e.getMessage());
		  }
	  } catch(AssertionError e) {
		  File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  try {
			  	FileUtils.copyFile(src, new File("wrong_author_name_bottom.png"));
			  } catch(IOException ee) {
				  System.out.println(e.getMessage());
			  }
	  
	  	}	
	}
	
	@AfterTest
	public void doAfterTest() {
		driver.close();
	}
}
