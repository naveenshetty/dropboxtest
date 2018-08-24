package pageobjects;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.dropbox.core.DbxEntry;
import framework.Config;
import framework.DropBoxTestCase;
import framework.WebDriverUtilities;

public class HomePage {

	// ------------------------------ HOME PAGE FIELDS ------------------------------

	public static String PATH_TO_FILE_FROM_ROOTFOLDER = File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator;

	
	//@FindBy(xpath = "//*[@id='header-account-menu']")
	
	//*/div/div[2]/div/div/nav/div/h2
	//@FindBy(xpath = "//*/div/div[2]/div/div/nav/div")
	//WebElement account_header_menu;

	@FindBy(xpath = "//*[@href='/logout']")
	WebElement logout;

	//*@FindBy(xpath="//*/div/div/div[1]/div/ul/li[2]/div/span/a")
	//@FindBy(class="action-upload-files mc-popover-content-item")
	//WebElement file;*/
	
	@FindBy(xpath="//li[@data-reactid='19']/div/button")
	WebElement createFolderButton;

	@FindBy(xpath="//input[@aria-label='Folder name']")
	WebElement newFolderName;

	@FindBy(xpath="//input[contains(@aria-label,'Rename directory…')]")
	WebElement renameFolderName;

	//@FindBy(xpath="//button[@data-reactid='17']")
	@FindBy(xpath="//span[contains(text(),'Upload')]")
	WebElement uploadFileButton;

	@FindBy(xpath="//a[@class='basic-uploader-link']")
	WebElement basicUploaderButton;

	@FindBy(xpath="//button[text()='Choose a file']")
	WebElement basicUploadLink;

	@FindBy(xpath="//input[@class='search-bar__text-input']")
	WebElement search;

	@FindBy(id="notify-msg")
	WebElement notificationMsg;

	@FindBy(xpath="//li[@data-action='Delete...']/button")
	WebElement deleteButton;

	@FindBy(xpath="//img[@alt='Show deleted files']/..")
	WebElement showDeletedFilesButton;

	@FindBy(xpath="//img[@alt='Hide deleted files']/..")
	WebElement hideDeletedFilesButton;

	@FindBy(xpath="//li[@data-action='Permanently delete…']/button")
	WebElement permanentDeleteButton;

	@FindBy(xpath="//button[@class='button-primary dbmodal-button']")
	WebElement confirmDelete;

	@FindBy(xpath="//li[@data-action='Rename']/button")
	WebElement renameButton;

	WebDriver driver;


	// --------------------------- CONSTRUCTORS ---------------------------

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// -------------------------- OTHER METHODS --------------------------

	public void open() {
		driver.get(Config.LOGIN_URL);
	}


	/*private void openAccountMenu() {
		account_header_menu.click();
	}

	public void waitForHomePage() throws Exception{
		WebDriverUtilities.waitForElement(driver, account_header_menu);
	}


	public String getUserName() throws Exception{
		WebDriverUtilities.waitForElement(driver, account_header_menu);
		return account_header_menu.getText();
	}*/

	/*public boolean userLoggedIn(boolean expectedCondition) throws Exception {

		waitForHomePage();
		if(expectedCondition){
			Assert.assertEquals(getUserName(), DropBoxTestCase.getClient().getAccountInfo().displayName);
			expectedCondition = true;
		}else {
			expectedCondition = false;
		}
		return expectedCondition;
	}

	public void logOut() {
		openAccountMenu();
		logout.click();
	}*/

	// -------------------------- FOLDER  --------------------------

	public void createFolder(String folderName) throws Exception {
		//file.click();
		createFolderButton.click();	
		WebDriverUtilities.waitForElement(driver, newFolderName);
		newFolderName.clear();
		newFolderName.sendKeys(folderName);
		newFolderName.sendKeys("\n");
		WebDriverUtilities.waitForElement(driver, notificationMsg);
		Assert.assertTrue(fileIsPresent(folderName, true));
	}


	// -------------------------- FILE  --------------------------

	public void uploadFile(String fileName) throws Exception {
		File file = new File("");
		PATH_TO_FILE_FROM_ROOTFOLDER = file.getCanonicalPath()+PATH_TO_FILE_FROM_ROOTFOLDER+fileName;
		PATH_TO_FILE_FROM_ROOTFOLDER = new File(PATH_TO_FILE_FROM_ROOTFOLDER).getCanonicalPath();
		if(!fileIsPresent(fileName, false)){
			uploadFileButton.click();
			WebDriverUtilities.waitForElement(driver, basicUploaderButton);
			basicUploaderButton.click();
			setClipboardData(PATH_TO_FILE_FROM_ROOTFOLDER);
			basicUploadLink.click();
			pasteFileLocationInUploadWindow();
		}
		Assert.assertTrue(fileIsPresent(fileName, true));
	}

	public void pasteFileLocationInUploadWindow() throws Exception {

		Robot robot = new Robot();
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyRelease(KeyEvent.VK_ENTER);

		WebDriverUtilities.waitForElement(driver, notificationMsg);

	}

	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public boolean fileIsPresent(String fileName, boolean expectedCondition) throws Exception {

		Thread.sleep(2500);
		expectedCondition = false;
		DbxEntry.WithChildren listing = DropBoxTestCase.getClient().getMetadataWithChildren("/");
		for (DbxEntry child : listing.children) {

			if(child.name.equals(fileName)){
				Assert.assertTrue(driver.findElements(By.xpath("//li[@data-filename='"+fileName+"']")).size()>0);
				expectedCondition = true;
				break;
			}
		}
		return expectedCondition;
	}

	public void deleteFileOrFolder(String fileOrFolderName) throws Exception{

		driver.findElement(By.xpath("//li[@data-filename='"+fileOrFolderName+"']")).click();
		deleteButton.click();
		confirmDelete.click();
		WebDriverUtilities.waitForElement(driver, notificationMsg);
		Assert.assertFalse(fileIsPresent(fileOrFolderName, false));
		showDeletedFilesButton.click();
		Thread.sleep(2000);
		WebDriverUtilities.waitForElement(driver, driver.findElement(By.xpath("//li[@data-filename='"+fileOrFolderName+"']")));
		driver.findElement(By.xpath("//li[@data-filename='"+fileOrFolderName+"']")).click();
		permanentDeleteButton.click();
		confirmDelete.click();
		WebDriverUtilities.waitForElement(driver, notificationMsg);
		Assert.assertFalse(fileIsPresent(fileOrFolderName, false));
		hideDeletedFilesButton.click();
		Thread.sleep(2000);
	}


	public void renameFolder(String folderName,String renamedFolderName) throws Exception{

		if(!fileIsPresent(folderName, true)){
			createFolder(folderName);
		}
		driver.findElement(By.xpath("//li[@data-filename='"+folderName+"']")).click();
		renameButton.click();
		WebDriverUtilities.waitForElement(driver, renameFolderName);
		renameFolderName.clear();
		renameFolderName.sendKeys(renamedFolderName);
		renameFolderName.sendKeys("\n");
		WebDriverUtilities.waitForElement(driver, notificationMsg);
		Assert.assertTrue(fileIsPresent(renamedFolderName, true));


	}




	// -------------------------- SEARCH  --------------------------

	public void searchFor(String fileName) throws Exception {

		search.sendKeys(fileName);
		search.sendKeys("\n");
		Thread.sleep(2000);
		fileIsPresent(fileName, true);

	}


}
