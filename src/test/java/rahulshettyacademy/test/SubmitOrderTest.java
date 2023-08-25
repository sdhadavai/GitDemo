package rahulshettyacademy.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "IPHONE 13 PRO";

	@Test(dataProvider = "getData", groups = { "Purchage" })
	//Below one is without HashMap
	//public void submitOrder(String email, String pwd, String productName) throws IOException {
	//Below one with HashMap
	public void submitOrder(HashMap<String, String> input) throws IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("pwd") );
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyCartDetails(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		// checkoutPage.enterCountry("Ind");
		// or
		checkoutPage.selectCountry("Ind");

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessag = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessag.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	// To verify order in displayed in Orders page
	@Test(dependsOnMethods = "submitOrder")
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("sdhadavai.qa@gmail.com", "JavaSelenium4$");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplayed(productName));

	}

	/*
	 * @DataProvider public Object[][] getData(){
	 * 
	 * return new Object[][] {{"sdhadavai.qa@gmail.com", "JavaSelenium4$",
	 * "IPHONE 13 PRO" },{"sdhadavai.qa1@gmail.com","JavaSelenium4$",
	 * "ZARA COAT 3"}}; }
	 */

	/*@DataProvider
	public Object[][] getData() {

		
		 * HashMap<String, String> hMap = new HashMap<String, String>();
		 * hMap.put("email", "sdhadavai.qa@gmail.com"); hMap.put("pwd",
		 * "JavaSelenium4$"); hMap.put("productName", "IPHONE 13 PRO");
		 * 
		 * HashMap<String, String> hMap1 = new HashMap<String, String>();
		 * hMap1.put("email", "sdhadavai.qa1@gmail.com"); hMap1.put("pwd",
		 * "JavaSelenium4$"); hMap1.put("productName", "ZARA COAT 3");
		 * 
		 * return new Object[][] {{hMap},{hMap1}};
		 
		
	}*/
	
	//Using JSON file
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchageOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	


}