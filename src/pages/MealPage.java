package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MealPage extends BasicPage{

	public MealPage(WebDriver driver) {
		super(driver);
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
	public void addToCart(String quantity) {
		this.getQuanitiy().clear();
		this.getQuanitiy().sendKeys(quantity);
		this.getAddToCart().click();
	}
	public void addToFavorite() {
		this.getAddToFavorite().click();
	}
}
