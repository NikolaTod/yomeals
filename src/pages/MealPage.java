package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MealPage extends BasicPage {

	private JavascriptExecutor js;

	public MealPage(WebDriver driver, JavascriptExecutor js) {
		super(driver);
		this.js = js;
	}

	public WebElement getAddToCart() {
		return this.driver.findElement(By.xpath("//*[@class='btn btn--primary btn--large js-proceedtoAddInCart ']"));
	}

	public WebElement getQuanitiy() {
		return this.driver.findElement(By.name("product_qty"));
	}

	public WebElement getAddToFavorite() {
		return this.driver.findElement(By.id("item_119"));
	}

	public void addToCart(int quantity) {
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", this.getQuanitiy(), "value",
				quantity);
		this.getAddToCart().click();
	}

	public void addToFavorite() {
		this.getAddToFavorite().click();
	}
}
