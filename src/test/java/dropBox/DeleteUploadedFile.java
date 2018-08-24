package dropBox;

import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;
import pageobjects.HomePage;
import framework.Config;
import framework.DropBoxTestCase;

public class DeleteUploadedFile extends DropBoxTestCase {

	/**
	 * DELETE UPLOADED FILE 
	 */
	@Test
	public void deleteUploadedFileTest(){
		log("Delete uploaded file");
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		try{
			homepage.deleteFileOrFolder(Config.FILENAME);
		}catch(Exception e){
			log(Config.FILENAME+" was not found");
		}
		log("Uploaded file deleted successfully");
	}
}

