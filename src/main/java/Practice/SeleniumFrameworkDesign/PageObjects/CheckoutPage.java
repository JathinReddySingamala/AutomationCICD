package Practice.SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Practice.SeleniumFrameworkDesgin.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement selectCountry;
	
	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	private WebElement submit;
	
	private By dropdown = By.cssSelector(".ta-results");
	

	public  void SelectCountry(String countryname) {
		
		 country.sendKeys(countryname);
		 waitForElementToAppear(dropdown);
		 selectCountry.click();
		
	}
	public ConfirmationPage submitOrder() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		
		return new ConfirmationPage(driver);
	}
}
