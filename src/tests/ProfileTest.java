package tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test
	public void editProfileTest() throws InterruptedException {
		driver.get(baseUrl + "guest-user/login-form");
		locationPopupPage.closePopup();
		loginPage.login(email, password);

		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Login Successfull"), "Error: login");

		driver.get(baseUrl + "member/profile");
		String firstName = "Marko";
		String lastName = "Markovic";
		String address = "address123";
		String phone = "123456";
		String zipCode = "12";
		String country = "India";
		String state = "Goa";
		String city = "Raia";
		profilePage.changeInfo(firstName, lastName, address, phone, zipCode, country, state, city);

		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Setup Successfu"), "Error: setup");

		authPage.logout();

		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Logout Successfull!"), "Error: logout");
	}

	@Test
	public void changeProfileImage() throws InterruptedException, IOException {
		driver.get(baseUrl + "guest-user/login-form");
		locationPopupPage.closePopup();
		loginPage.login(email, password);

		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Login Successfull"), "Error: login");

		driver.get(baseUrl + "member/profile");
		String imgPath = new File("img/profile_pic.jpg").getCanonicalPath();
		profilePage.uploadPic(imgPath);

		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Profile Image Uploaded Successfully"),
				"Error: login");

		notificationSystemPage.waitForMsgToDisappear();
		profilePage.removePic();

		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Profile Image Deleted Successfully"),
				"Error: login");
		notificationSystemPage.waitForMsgToDisappear();
		authPage.logout();

		Assert.assertTrue(notificationSystemPage.getMessageText().contains("Logout Successfull!"), "Error: login");

	}

}
