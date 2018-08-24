package dropBox;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import pageobjects.HomePage;
import framework.Config;
import framework.DropBoxTestCase;

public class UploadFileTest extends DropBoxTestCase {
	
	/**
	 *  UPLOAD FILE IN DROPBOX.
	 * @throws Exception
	 */
	@Test
	public void uploadFileInDBX()  throws Exception {
		log("Test Case to verify upload file functionality");
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		homepage.uploadFile(Config.FILENAME);
		log("Test Case verified successfully");

	}
}
