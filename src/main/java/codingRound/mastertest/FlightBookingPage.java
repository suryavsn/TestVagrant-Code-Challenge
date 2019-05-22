package codingRound.mastertest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class FlightBookingPage extends Library {


    @FindBy(xpath="//input[@id='OneWay']")
    private WebElement oneway;
    
    @FindBy(xpath="//input[@id='FromTag']")
    private WebElement fromTextBox;
    
    @FindBy(xpath="//input[@id='ToTag']")
    private WebElement toTextBox;
    
    @FindBy(xpath="(//td[@data-handler='selectDay'])[1]")
    private WebElement datePicker;
    
    @FindBy(xpath="//input[@id='SearchBtn']")
    private WebElement searchButton;
    
    public FlightBookingPage(WebDriver driver)
    {
    	super(driver);
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    }
    public void testOneWayJourney() {


        click(driver,oneway);

        clearText(driver,fromTextBox);
        sendKeys(driver,fromTextBox,"Bangalore");

        //wait for the auto complete options to appear for the origin

        waitFor(4000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        waitFor(2000);
        originOptions.get(0).click();

        clearText(driver,toTextBox);
        sendKeys(driver,toTextBox,"Delhi");

        //wait for the auto complete options to appear for the destination

        waitFor(4000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        waitFor(2000);
        destinationOptions.get(0).click();

        click(driver,datePicker);

        //all fields filled in. Now click on search
        click(driver,searchButton);

        waitFor(5000);
        //verify that result appears for the provided journey search

    }

   
}
