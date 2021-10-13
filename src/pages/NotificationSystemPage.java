package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	private WebDriverWait waiter;

	public NotificationSystemPage(WebDriver driver, WebDriverWait waiter) {
		super(driver);
		this.waiter = waiter;
	}

	public WebElement getMessage() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public String getMessageText() {
		return this.getMessage().getText();
	}

	public void waitForMsgToDissapear() {
		this.waiter.until(ExpectedConditions.attributeContains(By.xpath("//*[contains(@class, 'system_message')]"),
				"style", "display: none"));
	}

}
