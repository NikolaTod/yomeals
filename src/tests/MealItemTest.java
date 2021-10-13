package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.collections4.functors.CatchAndRethrowClosure;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartSummaryPage;
import pages.LocationPopupPage;

public class MealItemTest extends BasicTest {

	
//	@Test
//	public void addMealToCartTest() throws InterruptedException {
//		driver.get(baseUrl+"meal/lobster-shrimp-chicken-quesadilla-combo");
//		locationPopupPage.closePopup();
//		int quantity = 2;
//		mealPage.addToCart(quantity);
//		
//		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Errors Occurred"), "Error: setup");
//		
//		notificationSystemPage.waitForMsgToDissapear();
//		
//		String location = "City Center - Albany";
//		locationPopupPage.openSelectLocation();
//		locationPopupPage.setLocation(location);
//		Thread.sleep(500);
//		
//		mealPage.addToCart(quantity);
//		
//	
//		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Meal Added To Cart"), "Error: add");		
//	}
	
//	@Test
//	public void addToFavoriteTest() throws InterruptedException {
//		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
//		locationPopupPage.closePopup();
//		mealPage.addToFavorite();
//		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Please login first"), "Error: add");
//
//		driver.get(baseUrl + "guest-user/login-form");
//		loginPage.login(email, password);
//		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
//		mealPage.addToFavorite();
//		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Product has been added to your favorites"),"Error: add")
//
//	}
	@Test
	public void clearCartTest() throws IOException {
		driver.get(baseUrl+"/meals");
		locationPopupPage.closePopup();
		locationPopupPage.openSelectLocation();
		String location = "City Center - Albany";
		locationPopupPage.setLocation(location);
		
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet meals = wb.getSheet("Meals");
		SoftAssert sa = new SoftAssert();

		int quantity = 1;
		for (int i = 1; i <= meals.getLastRowNum(); i++) {
			String url = meals.getRow(i).getCell(0).getStringCellValue();
			driver.get(url);
			mealPage.addToCart(quantity);
			sa.assertTrue(notificationSystemPage.getMessageText().contains("Meal Added To Cart"), i + "is not added");
			notificationSystemPage.waitForMsgToDissapear();
		}

		sa.assertAll();
		cartSummaryPage.clearAll();
		
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("All meals removed from Cart successfully"));
	}
}
