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
		// Open a meal page
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		// Close the popup
		locationPopupPage.closePopup();
		// Add the meal to cart
		int quantity = 2;
		mealPage.addToCart(quantity);
		// Verify that the user cant add the meal before selecting a location
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Errors Occurred"),
				"Add to cart - error is not occured");
		// Wait for message to disapper
		notificationSystemPage.waitForMsgToDisappear();
		// Set location
		String location = "City Center - Albany";
		locationPopupPage.openSelectLocation();
		locationPopupPage.setLocation(location);
		Thread.sleep(500);
		// Add the meal to cart
		mealPage.addToCart(quantity);
		// Verify that the user can add the meal to cart
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Meal Added To Cart"), "Add to cart error");
	}

	@Test
	public void addToFavoriteTest() throws InterruptedException {
		// Open a meal page
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		// Close the popup
		locationPopupPage.closePopup();
		// Add the meal to favorite
		mealPage.addToFavorite();
		// Verify that the user has to be logged in
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Please login first"),
				"User is not logged in error");
		// Wait for message to disapper
		notificationSystemPage.waitForMsgToDisappear();
		// Go to the login page
		driver.get(baseUrl + "guest-user/login-form");
		// Login
		loginPage.login(email, password);
		// Go back to the meal page
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		// Add the meal to favorite
		mealPage.addToFavorite();
		Thread.sleep(500);
		// Verify that the meal is added to favorite
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Product has been added to your favorites"),
				"Add favorite error");
		// Remove the meal from favorite
		mealPage.addToFavorite();
		Thread.sleep(500);
		// Verify that the meal is removed from favorite
		Assert.assertTrue(
				notificationSystemPage.getMessageText().contains("Product has been removed from your favorites"),
				"Remove from favorite error");
	}

	@Test
	public void clearCartTest() throws IOException {
		// Open the meals page
		driver.get(baseUrl + "/meals");
		// Set location
		String location = "City Center - Albany";
		locationPopupPage.setLocation(location);
		// Add meals to cart
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet meals = wb.getSheet("Meals");
		int quantity = 1;
		for (int i = 1; i <= meals.getLastRowNum(); i++) {
			String url = meals.getRow(i).getCell(0).getStringCellValue();
			driver.get(url);
			mealPage.addToCart(quantity);
			// Verify that a meal is added to cart
			sa.assertTrue(notificationSystemPage.getMessageText().contains("Meal Added To Cart"), i + "is not added");
		}
		// Verify that all meals are added
		sa.assertAll();
		// Clear all
		cartSummaryPage.clearAll();
		// Verify that cart is clear
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("All meals removed from Cart successfully"),
				"Clear all error");
		wb.close();
	}
}
