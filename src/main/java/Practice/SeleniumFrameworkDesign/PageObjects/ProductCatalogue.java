package Practice.SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Practice.SeleniumFrameworkDesgin.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(css=".mb-3")
	private List<WebElement> products; 
	
	@FindBy(css=".ng-animating")
	private WebElement spinner;
	
	private By productsBy = By.cssSelector(".mb-3");
	private By addToCart = By.cssSelector(".card-body button:last-of-type");
	private By toastmessage = By.cssSelector("#toast-container");
	
	public List<WebElement> productList()
	{
		waitForElementToAppear(productsBy);
		return products;
		
	}
	
	public WebElement getProductByName(String productname) {
		
		WebElement product = productList().stream().filter(s->
		s.findElement(By.tagName("b")).getText().equals(productname)).findFirst().orElse(null);
		
		return product;
	}
	
	public void addProductToCart(String productname) throws InterruptedException {
		
		WebElement prod = getProductByName(productname);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastmessage);
		Thread.sleep(3000);
	}
	

}
