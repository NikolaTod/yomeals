package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class BasicPage {

	protected WebDriver driver;
	protected JavascriptExecutor js;
	
	public BasicPage(WebDriver driver, JavascriptExecutor js) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}

}
