package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getSearchResults() {
		return this.driver.findElement(By.xpath("//*[@class='product-name']/a"));
	}

	public int getResultsNo() {
		List<WebElement> meals = driver.findElements(By.xpath("//*[@class='product-name']"));
		return meals.size();
	}
}
