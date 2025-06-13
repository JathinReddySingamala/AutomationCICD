package Practice.SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.SeleniumFrameworkDesgin.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goTo(String url)
	{
		driver.get(url);
	}
	
	@FindBy(id="userEmail")
	private WebElement userEmail; 
	
	@FindBy(id="userPassword")
	private WebElement userPassword; 
	
	@FindBy(id="login")
	private WebElement submit; 
	
	@FindBy(css = "[class*='flyInOut']")
	private WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue product_catalogue = new ProductCatalogue(driver);
		return product_catalogue;
	}
	
	public String getErrorMessage() 
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	

}
