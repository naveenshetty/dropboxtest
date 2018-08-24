package dropBox;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;

import framework.DropBoxTestCase;
import pageobjects.HomePage;

public class RenameFolderTest extends DropBoxTestCase{

	/**
	 * Rename folder in DBX
	 * @throws Exception
	 */
	@Test
	public void renameFolderTest() throws Exception{
		log("Test Case to verify rename folder functionality");
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		String foldrName = "TestFolder";
		homepage.renameFolder(foldrName, foldrName+"new");
		homepage.deleteFileOrFolder(foldrName+"new");
		log("Test Case verified successfully");
		
	}

}
