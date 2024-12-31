package testPackage;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import basePackage.Baseclasstheghar;
import pagePackage.ThegharHomepage;

public class Theghartestclass extends Baseclasstheghar {
	ThegharHomepage homePage;
	@BeforeMethod 
	public void setUpMethod() { 
	super.setUp(); 
	homePage = new ThegharHomepage(driver); 
	}
	@AfterMethod
	public void tearDownMethod() {
		super.tearDown();
	}
	
	@Test 
	public void testTitleVerification() {
		test = extent.createTest("Title Verification Test", "Verifying the title of the homepage");
		homePage.Title();
		test.pass("Title verification completed.");
	}
	@Test(priority=1)
	public void testBrokenLinks() throws IOException, InterruptedException {
		test = extent.createTest("Broken Links Test", "Checking for broken links on the homepage");
		homePage.checkBrokenLinks(); 
		test.pass("Broken links check completed.");
	}
	@Test (priority=2)
	public void windowHandle() throws InterruptedException {
		test = extent.createTest("Window Handle Test", "Handling multiple windows and performing actions");
		Thread.sleep(4000);
		homePage.windowHandlers();
		test.pass("Window handling test completed.");
		Thread.sleep(4000);
		
	}
	@Test (priority=3)
	public void testScreenshot() throws IOException {
		 test = extent.createTest("Screenshot Test", "Taking a screenshot of the homepage");
		homePage.takeScreenshot("./screenshots/homepageimage.png");
	}
	@Test (priority=4)
	public void testBookNow() throws InterruptedException, IOException {
		test = extent.createTest("Book Now Test", "Testing the booking functionality");
		homePage.bookNow();
		Thread.sleep(8000);
		homePage.selectDate("December 2025","20");
		Thread.sleep(2000);
		homePage.selectDatecheckOut("January 2026", "2");
		Thread.sleep(4000);
		
		homePage.persons();
		homePage.editdetails();
		homePage.noofPeoples();
		Thread.sleep(4000);
	}
	
	
}
