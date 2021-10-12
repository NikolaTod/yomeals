package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationPopupPage extends BasicPage {

	private JavascriptExecutor js;

	public LocationPopupPage(WebDriver driver) {
		super(driver);
		js = (JavascriptExecutor) driver;
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

	public void setLocation(String locationName) throws InterruptedException {
		this.openSelectLocation();
		Thread.sleep(1000);
		this.getKeyword().click();
		String location = this.getLocationItem(locationName).getAttribute("data-value");
		js.executeScript("arguments[0].value=arguments[1];", this.getLocationInput(), location);
		this.getSubmit().click();
	}

	public void closePopup() {
		this.getClose().click();
	}
}
