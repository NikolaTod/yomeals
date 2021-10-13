package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

	private JavascriptExecutor js;
	private WebDriverWait waiter;

	public LocationPopupPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver);
		this.js = js;
		this.waiter = waiter;
	}

	public WebElement getSelectLocation() {
		return this.driver.findElement(By.className("location-selector"));
	}

	public WebElement getClose() {
		return this.driver.findElement(By.xpath("//*[@class='close-btn close-btn-white']"));
	}

	public WebElement getKeyword() {
		return this.driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return this.driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	public void openSelectLocation() {
		this.getSelectLocation().click();
	}

	public void closePopup() {
		this.getClose().click();
	}

	public void setLocation(String locationName) {
		this.openSelectLocation();
		this.getKeyword().click();
		String location = this.getLocationItem(locationName).getAttribute("data-value");
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", this.getLocationInput(), "value",
				location);
		js.executeScript("arguments[0].click();", this.getSubmit());
	}

}
