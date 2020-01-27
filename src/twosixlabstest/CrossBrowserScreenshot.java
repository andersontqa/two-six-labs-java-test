package twosixlabstest;

import org.openqa.selenium.*;
import java.io.*;
import org.apache.commons.io.FileUtils;

public class CrossBrowserScreenshot{
	
	private String browser;
	
	public CrossBrowserScreenshot (String browser_){
		browser = browser_;
	}
	
	public void getCrossBrowserScreenshot(String filename, WebDriver _driver) {
		if(browser.equalsIgnoreCase("firefox")) {
			File src = ((TakesScreenshot) _driver).getScreenshotAs(OutputType.FILE);
			String appended_filename = "firefox_" + filename;
			try {
			     FileUtils.copyFile(src, new File(appended_filename));
			  } catch(IOException e) {
			         System.out.println(e.getMessage());
				}
		}
		else if(browser.equalsIgnoreCase("chrome")) {
			File src = ((TakesScreenshot) _driver).getScreenshotAs(OutputType.FILE);
			String appended_filename = "chrome_" + filename;
			try {
			     FileUtils.copyFile(src, new File(appended_filename));
			  } catch(IOException e) {
			         System.out.println(e.getMessage());
				}
		}
	}
	

}
