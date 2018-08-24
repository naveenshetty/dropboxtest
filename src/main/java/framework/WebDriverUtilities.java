package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WebDriverUtilities {
	
	/**
	 * Wait for a element to be displayed.
	 * @param by : Element
	 * @param maxWaitTime : Maximum waiting time in seconds.
	 * @throws Exception 
	 */
	public static void waitForElement(WebDriver driver, WebElement element) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
		Thread.sleep(1000);
	}
	
	protected void log(String message) {
		Reporter.log(message, true);
	}

	protected void log(Object message) {
		if(message != null){
			Reporter.log(message.toString(), true);
		}else{
			Reporter.log("log method called with null object", true);
		}
	}
	
}
