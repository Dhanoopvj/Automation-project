package basePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Baseclasstheghar {
 protected WebDriver driver;
 protected static ExtentReports extent;
 protected static ExtentTest test;

 @BeforeSuite
 public void setupExtent() {
	 ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./testReports/myTestReports.html"); 
	 extent = new ExtentReports(); 
	 extent.attachReporter(sparkReporter); 
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setDocumentTitle("Automation Report");
 }
@BeforeClass
public void setUp() {
	driver=new ChromeDriver();
	driver.get("https://theghar.in/");
	driver.manage().window().maximize();
}
@AfterMethod 
public void tearDown() {
	if (driver != null) { 
		try {
		driver.quit();
	}catch(Exception e){
		System.out.println("Error while quitting the driver: "+ e.getMessage());
	}
		
	}
	extent.flush();
  }
}
