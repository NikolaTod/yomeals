package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MealItemTest extends BasicTest {

	
	@Test
	public void addMealToCartTest() throws InterruptedException {
		driver.get(baseUrl+"meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopup();
		int quantity = 2;
		mealPage.addToCart(quantity);
		
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Errors Occurred"), "Error: setup");
		
		notificationSystemPage.waitForMsgToDissapear();
		
		String location = "City Center - Albany";
		locationPopupPage.setLocation(location);
		locationPopupPage.waitPopupToDissapear();
		
		mealPage.addToCart(quantity);
		
	
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Meal Added To Cart"), "Error: add");
	}
}
