package codingRound.mastertest;
import java.awt.AWTException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ClearTripTest {

	
	WebDriver driver;
	SignInPage signinobj;
	HotelBookingPage hotelpageobj;
	FlightBookingPage flightpageobj;
	
	//Setting the system property, launching the browser, and getting the URL should be executed Before Every Test method
	@BeforeMethod
	public void initiateDriverSetup()
	{
		setDriverPath();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.get("https://www.cleartrip.com/");
        
	}
    
	//The part to be tested is to check if error is thrown if signed in without necessary details
    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

    	signinobj=new SignInPage(driver);
    	driver.manage().window().maximize();
        String errors1 = signinobj.ErrorwithoutSignInDetails();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        
    }
    
    //The part to be tested is to check if we are able to search for hotels. So verified that page navigated to searched page as title
    @Test
    public void shouldBeAbleToSearchForHotels() throws AWTException, InterruptedException
    {
    	Assert.assertEquals(driver.getTitle(), "#1 Site for Booking Flights, Hotels, Packages, Trains & Local activities.");
    	hotelpageobj= new HotelBookingPage(driver);
    	driver.manage().window().maximize();
    	hotelpageobj.SearchForHotels();
    	Assert.assertEquals(driver.getTitle(), "Cleartrip | Bangalore");
    	Assert.assertTrue(isElementPresent(By.className("searchSummary")));
    	
    }
    
    @Test
    public void testThatResultsAppearForAOneWayJourney()
    {
        flightpageobj= new FlightBookingPage(driver);
        driver.manage().window().maximize();
        flightpageobj.testOneWayJourney();
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));
    
    }
    
    @AfterMethod
    public void quitbrowser()
    {
    	driver.quit();
    }
    

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void setDriverPath() {
    	
    DesiredCapabilities cap=DesiredCapabilities.chrome();
    
        if (cap.getPlatform().is(Platform.MAC)) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (cap.getPlatform().is(Platform.WINDOWS)) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\625497\\Downloads\\chromedriver.exe");
        }
        if (cap.getPlatform().is(Platform.LINUX)) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
        if(cap.getPlatform().is(Platform.ANY))
        {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\625497\\Downloads\\chromedriver.exe");
        }
    }

}
