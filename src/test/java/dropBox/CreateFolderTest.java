package dropBox;

import org.testng.annotations.Test;
import java.util.Random;
import org.openqa.selenium.support.PageFactory;
import framework.DropBoxTestCase;
import pageobjects.HomePage;

public class CreateFolderTest extends DropBoxTestCase {

	/**
	 * CREATE FOLDER IN DROPBOX
	 * @throws Exception
	 */
	@Test
	public void createFolderInDBX() throws Exception{
		log("Test Case to verify create folder functionality");
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		
		Random r = new Random();
		String foldrName = "Folder"+r.nextInt(1000);
		homepage.createFolder(foldrName);
		log("Permanent delete folder");
		homepage.deleteFileOrFolder(foldrName);
		log("Test Case verified successfully");
	}
}
