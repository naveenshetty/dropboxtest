package dropBox;

import org.testng.annotations.Test;
import framework.DropBoxTestCase;

class TestLoginLogout extends DropBoxTestCase {

	/**
	 * Login & Logout Functionality.
	 * Login Test case would be executed in before method.
	 */
	@Test
	public void userLoginandLogout() throws Exception {
		 log("Test Case to verify login and logout");
		 //userLogout();
		 userLogin();
		
	}
}
