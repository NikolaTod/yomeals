package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProfilPage extends BasicPage {

	private JavascriptExecutor js;

	public ProfilPage(WebDriver driver) {
		super(driver);
		js = (JavascriptExecutor) driver;
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.name("user_email"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhoneNo() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		return new Select(this.driver.findElement(By.name("user_country_id")));
	}

	public Select getState() {
		return new Select(this.driver.findElement(By.name("user_state_id")));
	}

	public Select getCity() {
		return new Select(this.driver.findElement(By.name("user_city")));
	}

	public WebElement getSave() {
		return this.driver.findElement(By.name("btn_submit"));
	}

	public WebElement getUploadPic() {
		return this.driver.findElement(By.xpath("//*[@class='upload uploadFile-Js']"));
	}

	public WebElement getRemovePic() {
		return this.driver.findElement(By.xpath("//*[@class='remove']"));
	}

	public void removePic() {
		js.executeScript("arguments[0].click();", this.getRemovePic());
	}

	public void changeInfo(String firstName, String lastName, String address, String phone,
			String zipCode, String country, String state, String city) {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
		this.getAddress().clear();
		this.getAddress().sendKeys(address);
		this.getPhoneNo().clear();
		this.getPhoneNo().sendKeys(phone);
		this.getZipCode().clear();
		this.getZipCode().sendKeys(zipCode);
		this.getCountry().selectByVisibleText(country);
		this.getState().selectByVisibleText(state);
		this.getCity().selectByVisibleText(city);
		this.getSave().click();
	}

	public void uploadPic(String imgPath) {
		js.executeScript("arguments[0].click();", this.getUploadPic());
		this.getUploadPic().sendKeys(imgPath);
	}

}
