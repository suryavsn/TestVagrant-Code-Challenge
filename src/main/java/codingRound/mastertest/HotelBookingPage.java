package codingRound.mastertest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HotelBookingPage extends Library {

    
    @FindBy(xpath = "//a[contains(@href,'hotel') and contains(@title,'Find hotels')]")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;
    
    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    public HotelBookingPage(WebDriver driver)
    {
    	super(driver);
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    }

    public void SearchForHotels() throws AWTException {
    	
        click(driver, hotelLink);
        sendKeys(driver, localityTextBox, "Indiranagar, Bangalore");
        waitFor(7000);
        List<WebElement> locationOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        locationOptions.get(0).click();
        Robot robotobj= new Robot();
        robotobj.keyPress(KeyEvent.VK_TAB);
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        click(driver,searchButton);
        waitFor(10000);

    }

	

    

}
