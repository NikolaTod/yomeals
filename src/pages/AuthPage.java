package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getAccount() {
		return this.driver.findElement(By.xpath("//div[@class='accounts-link accounts-popup']"));
	}

	public WebElement getLogout() {
		return this.driver.findElement(By.xpath("//div[@class='my-account-dropdown']/ul/li[2]"));
	}

	public void logout() {
		this.getAccount().click();
		this.getLogout().click();
	}
}
