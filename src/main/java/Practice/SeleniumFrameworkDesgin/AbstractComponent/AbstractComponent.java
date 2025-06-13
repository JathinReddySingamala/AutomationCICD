package Practice.SeleniumFrameworkDesgin.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Practice.SeleniumFrameworkDesign.PageObjects.Cartpage;
import Practice.SeleniumFrameworkDesign.PageObjects.OrderPage;

public class AbstractComponent {	
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement OrderHeader;
	
	public Cartpage goToCartPage() 
	{
		cartHeader.click();
		Cartpage cartpage = new Cartpage(driver);
		return cartpage;
	}
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement Ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(Ele));
	}

	public OrderPage goToOrdertPage() 
	{
		OrderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
	
}
