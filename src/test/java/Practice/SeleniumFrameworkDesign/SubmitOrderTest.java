package Practice.SeleniumFrameworkDesign;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Practice.SeleniumFrameworkDesgin.AbstractComponent.AbstractComponent;
import Practice.SeleniumFrameworkDesign.PageObjects.Cartpage;
import Practice.SeleniumFrameworkDesign.PageObjects.CheckoutPage;
import Practice.SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import Practice.SeleniumFrameworkDesign.PageObjects.LandingPage;
import Practice.SeleniumFrameworkDesign.PageObjects.OrderPage;
import Practice.SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import Practice.SeleniumFrameworkDesign.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice.Argument;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException{
		
		
		ProductCatalogue product_catalogue = landingpage.loginApplication(input.get("email"),input.get("password"));
		
		List<WebElement> products = product_catalogue.productList();
		product_catalogue.addProductToCart(input.get("productName"));		
		
		Cartpage cartpage = product_catalogue.goToCartPage(); 
		boolean match = cartpage.VerifyProductDisplay(input.get("productName"));
		
		Assert.assertTrue(match);
	
		CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.SelectCountry("india");
		ConfirmationPage confirmationPage = checkoutpage.submitOrder();

		

		String confirmMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	
	}

	@Test(dependsOnMethods = {"submitOrder"} )
	public void OrderHistoryTest() {
		
		ProductCatalogue product_catalogue = landingpage.loginApplication("singamalaj@gmail.com", "Jathin@10");
		OrderPage orderPage =  product_catalogue.goToOrdertPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	public String getScreenshot(String testCaseName) throws Exception
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(Source, file);
		
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	@DataProvider
	public Object[][] getData() throws Exception {
		
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//data//PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	} 
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "singamalaj@gmail.com");
//	map.put("password", "Jathin@10");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "xyz123abc@gmail.com");
//	map1.put("password", "Abcd@1234");
//	map1.put("productName", "ADIDAS ORIGINAL");

}
