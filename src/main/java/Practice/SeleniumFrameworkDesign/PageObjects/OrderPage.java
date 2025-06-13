package Practice.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.SeleniumFrameworkDesgin.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	
	public OrderPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tr/td[2]")
	private List<WebElement> orderlist;
	
	
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = orderlist.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
}
