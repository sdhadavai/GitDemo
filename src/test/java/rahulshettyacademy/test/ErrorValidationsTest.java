package rahulshettyacademy.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {
	

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {
		landingPage.loginApplication("sdhadavai.qa1@gmil.com", "test");
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void productErrorValidation() {
		String productName = "IPHONE 13 PRO";
		ProductCatalogue productCatalogue = landingPage.loginApplication("sdhadavai.qa@gmail.com", "JavaSelenium4$");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyCartDetails("IIPHONE 13 PRO");
		Assert.assertFalse(match);
	}
}