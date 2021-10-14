package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MealItemTest extends BasicTest {

	@Test
	public void addMealToCartTest() throws InterruptedException {
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopup();
		int quantity = 2;
		mealPage.addToCart(quantity);

		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Errors Occurred"), "Error: setup");

		notificationSystemPage.waitForMsgToDisappear();

		String location = "City Center - Albany";
		locationPopupPage.openSelectLocation();
		locationPopupPage.setLocation(location);
		Thread.sleep(500);

		mealPage.addToCart(quantity);

		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Meal Added To Cart"), "Error: add");
	}

	@Test
	public void addToFavoriteTest() throws InterruptedException {
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopup();
		mealPage.addToFavorite();
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Please login first"), "Error: login first");
		notificationSystemPage.waitForMsgToDisappear();

		driver.get(baseUrl + "guest-user/login-form");
		loginPage.login(email, password);
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.addToFavorite();
		Thread.sleep(500);
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Product has been added to your favorites"),
				"Error: add favorite");
		mealPage.addToFavorite();
	}

	@Test
	public void clearCartTest() throws IOException {
		driver.get(baseUrl + "/meals");
		locationPopupPage.closePopup();
		locationPopupPage.openSelectLocation();
		String location = "City Center - Albany";
		locationPopupPage.setLocation(location);

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet meals = wb.getSheet("Meals");

		int quantity = 1;
		for (int i = 1; i <= meals.getLastRowNum(); i++) {
			String url = meals.getRow(i).getCell(0).getStringCellValue();
			driver.get(url);
			mealPage.addToCart(quantity);
			sa.assertTrue(notificationSystemPage.getMessageText().contains("Meal Added To Cart"), i + "is not added");
		}

		sa.assertAll();
		cartSummaryPage.clearAll();

		Assert.assertTrue(notificationSystemPage.getMessageText().contains("All meals removed from Cart successfully"));
		wb.close();
	}
}
