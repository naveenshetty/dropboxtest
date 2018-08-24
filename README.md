# DropBoxTest
Basic DropBox test cases using Selenium and Java.

## Requirements:

 1) Eclipse or anyother IDE with maven plugin installed.

 2) Mozilla Firefox Ver : 44. You can download it from https://ftp.mozilla.org/pub/firefox/releases/44.0/ depending on the    machine configuration and make sure to turn off automatic updates. If any higher version of firefox has been installed , we need to downgrade it to ver 44. Reference https://www.liberiangeek.net/2012/04/how-to-install-previous-versions-of-firefox-in-ubuntu-12-04-precise-pangolin/ .
 
 3) TestNG Plugin installed within eclipse. Refer http://toolsqa.com/selenium-webdriver/install-testng/ if not installed.
 
 
## Importing this project into Eclipse : 
 
 1) In eclipse go to File->Import->Maven-> Maven project with existing sources. In next section in root directory navigate to the project location , in project section below select the pom.xml file and click on finish.
 
 Note : After configuration , if you get any errors in pom.xml file update the maven project configuration. Right click on project maven->update project configurations. Select force update snapshots/releases and click on Ok.
 
 
## Configuration : 
 
 1) Make sure you have a dropbox account along with an app. If your account doesn't have an app you can login to you dropbox account and register for an app here https://www.dropbox.com/developers/apps. 
 
 2) After an app has been created , we need a unique token to access your dropbox account via selenium test cases. We can retrieve that by going in your created app and clicking on generate button under Generate access token section.
 
 3) The main use of this access token is to verify if all the performed operations functionally were actually working from the backend. Test case only verifies if any new files or folders added were actually added by verifying it using DBX API's. Apart from this , test case doesn't retrieve or manipulate the existing data in your dropbox account.
 
 4) TestNG.xml in root folder takes 3 parameters : email , password and accessToken generated in previous step. Kindly add it in the value section as follows 
  
      <parameter name="email" value="your email id goes here />
      <parameter name="password" value="your password goes here"/>
      <parameter name="dbxAccessToken" value="your access token goes here" />
 
 5) Without adding these parameters , the test case will not be able to run. Make sure to add correct details.
 
## Finally how to run testng.xml file:
 
 After all the configurations , make sure there are no errors in the project. After adding parameters to Testng.xml file as shown above , right click on testng.xml Run As -> TestNG Suite. 
 
 The execution report can be found in test-output folder in the root folder of the project.  
 

