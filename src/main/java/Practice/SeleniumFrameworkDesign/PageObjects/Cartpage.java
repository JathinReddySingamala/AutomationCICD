package Practice.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.SeleniumFrameworkDesgin.AbstractComponent.AbstractComponent;

public class Cartpage extends AbstractComponent{

	WebDriver driver;
	
	public Cartpage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartproducts;
	
	@FindBy(css = ".totalRow button")
	private WebElement checkout;
	
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartproducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", checkout);
		
		return new CheckoutPage(driver);
	}
	
	
	
	
}
