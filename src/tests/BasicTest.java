package tests;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilPage;

public class BasicTest {

	protected WebDriver driver;
	protected String baseUrl = "http://demo.yo-meals.com/";
	protected String email = "customer@dummyid.com";
	protected String password = "12345678a";
	protected LocationPopupPage locationPopupPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilPage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage castSummaryPage;
	
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		locationPopupPage = new LocationPopupPage(driver);
		loginPage = new LoginPage(driver);
		notificationSystemPage = new NotificationSystemPage(driver);
		profilePage = new ProfilPage(driver);
		authPage = new AuthPage(driver);
		mealPage = new MealPage(driver);
		castSummaryPage = new CartSummaryPage(driver);		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
		this.driver.quit();
	}

}
