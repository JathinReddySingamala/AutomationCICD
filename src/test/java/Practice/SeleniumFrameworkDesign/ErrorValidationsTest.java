package Practice.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Practice.SeleniumFrameworkDesign.PageObjects.Cartpage;
import Practice.SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import Practice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import Practice.SeleniumFrameworkDesign.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{

	
	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException{
	
		landingpage.loginApplication("singamaaaalaj@gmail.com", "Jathin@10");
		
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	
		

	
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
		
		String productName = "ZARA COAT 3";
	
		ProductCatalogue product_catalogue = landingpage.loginApplication("singamalaj@gmail.com", "Jathin@10");
		List<WebElement> products = product_catalogue.productList();
		
		product_catalogue.addProductToCart(productName);		
		
		Cartpage cartpage = product_catalogue.goToCartPage(); 
		boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		
		Assert.assertFalse(match);
	
		

	
	}

}
