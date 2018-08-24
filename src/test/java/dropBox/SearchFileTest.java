package dropBox;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import pageobjects.HomePage;
import framework.Config;
import framework.DropBoxTestCase;

public class SearchFileTest extends DropBoxTestCase {
 
	/**
	 * SEARCH FOR A FILE IN DBX
	 * @throws Exception
	 */
	@Test
	public void testSearchFile() throws Exception{
		
		log("Test case to verify search functionality");
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		homepage.searchFor(Config.FILENAME);
		log("Test case verified successfully");
		
	}
}
