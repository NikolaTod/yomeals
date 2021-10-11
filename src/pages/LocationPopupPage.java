package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocationPopupPage extends BasicPage {

	private JavascriptExecutor js;
	
	public LocationPopupPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getSelectLocation() {
		return this.driver.findElement(By.className("location-selector"));
	}

	public WebElement getClose() {
		return this.driver.findElement(By.xpath("//*[contains(@class,'close-btn')]"));
	}

	public WebElement getKeyword() {
		return this.driver.findElement(By.id("locality_keyword"));
	}

	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return this.driver.findElement(By.id("location_id"));
	}

	public WebElement getSubmit() {
		return this.driver.findElement(By.name("btn_submit"));
	}

	public void openSelectLocation() {
		this.getSelectLocation().click();
	}

	public void setLocation(String locationName) {
		this.getKeyword().click();
		String value = this.getLocationItem(locationName).getAttribute("data-value");
		this.js.executeScript("arguments[0].value=arguments[1]", this.getLocationInput(), value);
		this.js.executeScript("arguments[0].click();", this.getSubmit());
	}

	public void closeLocationSelect() {
		this.getClose().click();
	}
}
