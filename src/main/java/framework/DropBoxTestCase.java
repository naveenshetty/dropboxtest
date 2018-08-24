package framework;

import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import pageobjects.HomePage;
import pageobjects.LoginPage;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;

public class DropBoxTestCase {

	protected static WebDriver driver;

	private static DbxClient client;


	@Parameters({"email", "password" ,"dbxAccessToken"})
	@BeforeSuite
	public void setCredentials(@Optional("naveenshettyait@gmail.com") String email , @Optional("Amma@123321") String password , @Optional("")String dbxAccessToken){

		Config.LOGIN_USER = email;
		Config.LOGIN_PWD = password;
		Config.ACCESS_TOKEN = dbxAccessToken;

	}

	@BeforeMethod
	public void setUp() throws Exception{

		if(client == null){
			try {
				setClient(dropBoxAuth());
			} catch (DbxException e) {
				e.printStackTrace();
			}
		} else {
			getClient();
		}

		if(driver == null){
	   //System.setProperty("webdriver.gecko.driver","C:/Users/Naveen/Documents/geckodriver-v0.21.0-win32/geckodriver.exe");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver","C:/Users/Naveen/Documents/chromedriver_win32/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			userLogin();
		}
	}

	/**
	 * DROP AUTHENTICATION using DBX SDK.
	 * Useful for verifying test cases from backend.
	 * @return
	 * @throws DbxException
	 */
	public DbxClient dropBoxAuth() throws DbxException {

		DbxRequestConfig config = new DbxRequestConfig("SeleniumTest", Locale.getDefault().toString());
		String accessToken = Config.ACCESS_TOKEN;
		DbxClient client = new DbxClient(config, accessToken);
		System.out.println("Linked account: " + client.getAccountInfo().displayName);

		return client;
	}

	protected void log(String message) {
		Reporter.log(message, true);
	}

	protected void log(Object message) {
		if(message != null){
			Reporter.log(message.toString(), true);
		}else{
			Reporter.log("log method called with null object", true);
		}
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void userLogin() throws Exception {	

		log("Log in to user with "+Config.LOGIN_USER+" and "+Config.LOGIN_PWD);
		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		loginpage.open();
		loginpage.openSignInModal();
		loginpage.fillEmail(Config.LOGIN_USER);
		loginpage.fillPassword(Config.LOGIN_PWD);
		loginpage.submit();

		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		//Assert.assertTrue(homepage.userLoggedIn(true));
		log("User logged in successfully");
	}

	/*public void userLogout(){
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		homepage.logOut();
		tearDown();
	}*/

	/*@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			try{
				tearDown();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}*/

	@AfterSuite
	/*public void tearDown(){
		log("closing webdriver");
		driver.close();
		if(driver != null){
			driver = null;
		}
		log("webdriver closed.");
	}*/

	public static DbxClient getClient() {
		return client;
	}

	public void setClient(DbxClient client) {
		DropBoxTestCase.client = client;
	}
}
