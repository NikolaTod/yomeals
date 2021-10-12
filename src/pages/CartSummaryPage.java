package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartSummaryPage extends BasicPage{

	public CartSummaryPage(WebDriver driver) {
		super(driver);
	}
	
	public WebElement getClearAll() {
		return this.driver.findElement(By.xpath("//div[@class='cart-head']/a[2]"));
	}
	
	public void clearAll() {
		this.getClearAll().click();
	}

}
