package Practice.SeleniumFrameworkDesign.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Practice.SeleniumFrameworkDesign.PageObjects.Cartpage;
import Practice.SeleniumFrameworkDesign.PageObjects.CheckoutPage;
import Practice.SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import Practice.SeleniumFrameworkDesign.PageObjects.LandingPage;
import Practice.SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import Practice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue product_catalogue;
	public ConfirmationPage confirmationPage;
	public CheckoutPage checkoutpage;
	
	@Given("I landed on the Ecommerce Page")
	public void I_landed_on_the_Ecommerce_Page() throws IOException
	{
		landingpage =  launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password)
	{
		product_catalogue = landingpage.loginApplication(username,password);
	}
	
	 @When("^I add product (.+) to Cart$")
	 public void  I_add_product_to_Cart(String productName) throws InterruptedException
	 {
		 List<WebElement> products = product_catalogue.productList();
			
		product_catalogue.addProductToCart(productName);
	 }
	 
	 @When("^Checkout (.+) and submit the order$")
	 public void checkout_submit_the_order(String productName)
	 {
		 Cartpage cartpage = product_catalogue.goToCartPage(); 
			boolean match = cartpage.VerifyProductDisplay(productName);
			
			Assert.assertTrue(match);
		
			checkoutpage = cartpage.goToCheckout();
			checkoutpage.SelectCountry("india");
			confirmationPage = checkoutpage.submitOrder();
			
	 }
	 
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_on_ConfirmationPage(String message)
	 {
		 String confirmMessage = confirmationPage.getConfirmationMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
		 driver.close();

	 }
	 
	 @Then("{string} message is displayed") 
	 public void message_is_displayed(String message)
	 {
		 Assert.assertEquals(message, landingpage.getErrorMessage());
		 driver.close();
	 }
}
