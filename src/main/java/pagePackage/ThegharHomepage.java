package pagePackage;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThegharHomepage {
	WebDriver driver;
	
	public ThegharHomepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);  
		}
	
	public void Title() { 
		String expectedTitle ="The Ghar &#8211; Perfect boutique home stay";
		if (expectedTitle.equals(driver.getTitle()) ){
			System.out.println("Title verification failed.");
		}else {
			System.out.println("Title Verification is passed.");
		}
		
	}
	
	public void checkBrokenLinks() throws IOException, InterruptedException {
		Thread.sleep(5000);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for (WebElement link : links) { 
			String href = link.getAttribute("href"); 
			if (href != null && !href.isEmpty()) { 
		checkLink(href); } } }

	private void checkLink(String urlString) throws IOException {
		URL url;
		try {
		 url = new URL(urlString);}
		catch(Exception e) {
			System.out.println("Other Urls: "+ urlString);
			return;
		}
		if (!url.getProtocol().equals("https") && !url.getProtocol().equals("http")) {
			System.out.println("Skipping non-http/https link: "+ urlString);
			return;
		}
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("GET"); 
		httpURLConnection.connect(); 
		int responseCode = httpURLConnection.getResponseCode(); 
		if (responseCode >= 400) {
			System.out.println("Broken link: " + urlString + " - Response code: " + responseCode); 
		} else {
			System.out.println("Valid link: " + urlString + " - Response code: " + responseCode);
			} }
	
	@FindBy(xpath="//*[@id=\"mapDiv\"]/div/div[3]/div[3]/div/div/div/div/div[2]/a")
	WebElement mapLink;
	@FindBy(xpath="//*[@id=\"colophon\"]/div/div/div/div/div[4]/div/div/iframe")
	WebElement map;
	
	public void windowHandlers() throws InterruptedException {
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,90000)");
		Thread.sleep(7000);
		map.click();
		
		driver.switchTo().frame(map);
		mapLink.click();
		
		String ParentWindow=driver.getWindowHandle();
		Set<String> allWindows=driver.getWindowHandles();
		for(String handle:allWindows) {
			if(!handle.equals(ParentWindow)) {
				driver.switchTo().window(handle);
				driver.close();
				}
			Thread.sleep(5000);
				driver.switchTo().window(ParentWindow);}
		
		driver.switchTo().defaultContent();
		Thread.sleep(6000);
		JavascriptExecutor scrollUp=(JavascriptExecutor) driver;
		scrollUp.executeScript("window.scrollBy(0,-90000)");
	}

	public void takeScreenshot(String filePath) throws IOException {
		
		File homepageImage=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(homepageImage,new File("./screenshots/homepageimage.png"));
		
	}
	
	@FindBy(xpath="//*[@id=\"masthead\"]/div/div[1]/div[4]/div[2]/div/h2/a")
	WebElement booknow;
		
	public void bookNow() {
		booknow.click();
	}
	
   	@FindBy(xpath="//input[@id='startdatepicker']")
	WebElement datePicker;
	
    public void selectDate(String monthAndYear , String date) {
    	datePicker.click();
    
    while (true) {
   		String currentmonthAndYear= driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[2]/table/thead/tr[1]/th[2]")).getText();
   		if(currentmonthAndYear.equals(monthAndYear)) {
   			 List<WebElement> allDates = driver.findElements(By.xpath("/html/body/div[5]/div[1]/div[2]/table/tbody/tr/td"));		
   				for(WebElement dt : allDates) {
   					if(dt.getText().equals(date)) {
   						dt.click();
   						break;
   	}
   				} break;
		}else {
			driver.findElement(By.xpath("//body[1]/div[5]/div[2]/div[2]/table[1]/thead[1]/tr[1]/th[3]")).click();
			}
	}
    }
    
    @FindBy(xpath="//input[@id='enddatepicker']")
    WebElement datePickercheckOut;
    
    
    public void selectDatecheckOut(String monthAndYear , String date) throws InterruptedException {
    	datePickercheckOut.click();
    while (true) {
    	String currentMonthAndYearCheckOut=driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/table/thead/tr[1]/th[2]")).getText();
    	Thread.sleep(2000);
    	if(currentMonthAndYearCheckOut.equals(monthAndYear)) {
    		 List<WebElement> allDates = driver.findElements(By.xpath("/html/body/div[6]/div[1]/div[2]/table/tbody/tr/td"));	
    		 for(WebElement dt : allDates) {
					if(dt.getText().equals(date)) {
						dt.click();
						break;
					}
    		 }break;}else {
    			 driver.findElement(By.xpath("/html/body/div[6]/div[2]/div[2]/table/thead/tr[1]/th[3]")).click();}
    }
    }
    
    @FindBy(xpath="//input[@id='input_103']")
    WebElement details;
    public void persons() {
    	details.click();
    }
    @FindBy(xpath="//*[@id=\"wrapperDiv\"]/section/div[2]/div/div[4]/div[1]/div[2]/div/div[2]/div/div/div[1]/div/div[1]/div[1]/div[2]/span/i[1]")
    WebElement edit;
    public void editdetails() {
    	edit.click();
    }
    @FindBy(xpath="//*[@id=\"select_value_label_135\"]/span[1]")
    WebElement adult;
    @FindBy(xpath="//*[@id=\"select_option_149\"]/div")
    WebElement option;
    @FindBy(xpath="//*[@id=\"select_value_label_136\"]/span[1]/div")
    WebElement childrens;
    @FindBy(xpath="//*[@id=\"select_option_152\"]/div")
    WebElement childOption;
    
    @FindBy(xpath="//*[@id=\"wrapperDiv\"]/section/div[2]/div/div[4]/div[1]/div[2]/div/div[2]/div/div/div[2]/div[2]/button")
    WebElement update;
   
    @FindBy(xpath="//*[@id=\"wrapperDiv\"]/section/div[3]/span/div/div[2]/div[1]/div/div/div[3]/div/div/div[2]/div[1]/div/div[4]/button")
    WebElement add;
    
    @FindBy(xpath="/html/body/div[1]/section/div[3]/span/div/div[2]/div[1]/div/div/div/div/div[3]/div/div/form/div[2]/md-input-container[1]/input")
    WebElement fname;
    
    @FindBy(xpath="/html/body/div[1]/section/div[3]/span/div/div[2]/div[1]/div/div/div/div/div[3]/div/div/form/div[2]/md-input-container[2]/input")
    WebElement lname;
    
    @FindBy(xpath="/html/body/div[1]/section/div[2]/div/div[4]/div[1]/div[2]/div/div[2]/md-input-container/input")
    WebElement makealert;
    
   @FindBy(xpath="//button[normalize-space()='cancel']")
    WebElement closeAlert ;
    
    public void noofPeoples() throws InterruptedException, IOException {
    adult.click();
    Thread.sleep(3000);
    option.click();
    Thread.sleep(3000);

    childrens.click();
    Thread.sleep(3000);

    childOption.click();
    Thread.sleep(3000);
    
    update.click();
    Thread.sleep(6000);
    
    JavascriptExecutor js=(JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,-60000)");
	Thread.sleep(8000);
   
	
    try {
        add.click();
    } catch (ElementClickInterceptedException e) {
        System.out.println("Element click intercepted: " + e.getMessage());
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File("./screenshots/img.png"));
   
        js.executeScript("arguments[0].click();", add);
        Thread.sleep(4000);
    }
Thread.sleep(4000);

JavascriptExecutor js1=(JavascriptExecutor) driver;
	js1.executeScript("window.scrollBy(0,-60000)");
	Thread.sleep(8000);
	
    fname.click();
	fname.sendKeys("Testname");
	Thread.sleep(1000);
	lname.sendKeys("LastName");
	Thread.sleep(1000);
    makealert.click();
    update.click();
    Thread.sleep(6000);
    
    handlePopupAlert();
    Thread.sleep(6000);
    closeAlert.click();
    
    }
private void handlePopupAlert() {
	try {
    Alert updateAlert =driver.switchTo().alert();
    String text=updateAlert.getText();
    System.out.println(text);
    updateAlert.dismiss();
    }catch(Exception e) {
    	System.out.println("No alert found: "+e.getMessage());
    }
}

   
    }
    
    
    	
    	
    
    
   
    
  
