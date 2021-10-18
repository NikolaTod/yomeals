package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class SearchTest extends BasicTest {

	@Test
	public void searchTest() throws IOException, InterruptedException {
		// Open the meals page
		driver.get(baseUrl + "/meals");
		// Set location
		locationPopupPage.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet meals = wb.getSheet("Meal Search Results");

		for (int i = 1; i <= 6; i++) {
			Thread.sleep(1000);
			String location = meals.getRow(i).getCell(0).getStringCellValue();
			String url = meals.getRow(i).getCell(1).getStringCellValue();
			int results = (int) meals.getRow(i).getCell(2).getNumericCellValue();
			driver.get(url);
			locationPopupPage.openSelectLocation();
			locationPopupPage.setLocation(location);
			Thread.sleep(1000);
			// Verify that the number of search results is correct
			sa.assertEquals(searchResultPage.getResultsNo(), results, i + ". num row error");

		}
		sa.assertAll();
		wb.close();
	}

}
