package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

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
//		locationPopupPage.setLocation(location);
//		Thread.sleep(500);
//		
//		mealPage.addToCart(quantity);
//		
//	
//		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Meal Added To Cart"), "Error: add");		
//	}
	
	@Test
	public void addToFavoriteTest() throws InterruptedException {
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopup();
		mealPage.addToFavorite();
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Please login first"), "Error: add");

		driver.get(baseUrl + "guest-user/login-form");
		loginPage.login(email, password);
		driver.get(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.addToFavorite();
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Product has been added to your favorites"),
				"Error: add");

	}
}
