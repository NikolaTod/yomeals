package tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test
	public void editProfileTest() throws InterruptedException {
		// Open the login page
		driver.get(baseUrl + "guest-user/login-form");
		// Close the popup
		locationPopupPage.closePopup();
		// Login
		loginPage.login(email, password);
		// Verify that the user is logged in
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Login Successfull"), "Login error");
		// Go to the profile page
		driver.get(baseUrl + "member/profile");
		// Change all the info
		String firstName = "John";
		String lastName = "Doe";
		String address = "address123";
		String phone = "123456";
		String zipCode = "12";
		String country = "United Kingdom";
		String state = "Derby";
		String city = "Buxton";
		profilePage.changeInfo(firstName, lastName, address, phone, zipCode, country, state, city);
		// Verify that the info setup is successful
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Setup Successful"), "Setup info error");
		// Logout
		authPage.logout();
		// Verify that the user is logged out
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Logout Successfull!"), "Logout error");
	}

	@Test
	public void changeProfileImage() throws InterruptedException, IOException {
		// Open the login page
		driver.get(baseUrl + "guest-user/login-form");
		// Close the popup
		locationPopupPage.closePopup();
		// Login
		loginPage.login(email, password);
		// Verify that the user is logged in
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Login Successfull"), "Login error");
		// Go to the profile page
		driver.get(baseUrl + "member/profile");
		// Upload the image
		String imgPath = new File("img/profile_pic.jpg").getCanonicalPath();
		profilePage.uploadPic(imgPath);
		// Verfy that the profile image is changed
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Profile Image Uploaded Successfully"),
				"Picture upload error");
		// Wait for the message to disappear
		notificationSystemPage.waitForMsgToDisappear();
		// Remove the image
		profilePage.removePic();
		// Verify that the image is removed
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Profile Image Deleted Successfully"),
				"Picture remove error");
		notificationSystemPage.waitForMsgToDisappear();
		// Loggout
		authPage.logout();
		// Verify that the user is logged out
		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Logout Successfull!"), "Loggout error");

	}

}
