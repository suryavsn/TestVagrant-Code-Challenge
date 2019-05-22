package codingRound.mastertest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends Library{

    
    @FindBy(xpath = "//a[@id='userAccountLink']")
    private WebElement userAccountLink;

    @FindBy(xpath = "//input[@id='SignIn']")
    private WebElement signInButtonInDropdown;

    @FindBy(xpath = "(//div/iframe[1])[3]")
    private WebElement iframeElement;
    
    @FindBy(xpath = "//button[@id='signInButton']")
    private WebElement signInButtonInPopup;
    
    public SignInPage(WebDriver driver)
    {
    	super(driver);
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    	
    }
    
    public String ErrorwithoutSignInDetails()
    {
    	String error="";
    	click(driver, userAccountLink);  
    	click(driver, signInButtonInDropdown);
    	
    	driver.switchTo().frame(iframeElement);
    	click(driver, signInButtonInPopup);
    	error = driver.findElement(By.id("errors1")).getText();
    	driver.switchTo().defaultContent();
    	return error;
    }
    
}
