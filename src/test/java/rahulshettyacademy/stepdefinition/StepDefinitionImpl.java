package rahulshettyacademy.stepdefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.testcomponents.BaseTest;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	ProductCatalogue productCatalogue;
	ConfirmationPage confirmationPage;
	
	@Given ("I landed on Ecamerce page")
	public void I_landed_on_Ecamerce_page() throws IOException {
		
		landingPage = launchApplication();
		
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String userName, String password) {
		productCatalogue = landingPage.loginApplication(userName, password);
	
	}
	
	@When ("^I add the prodcut (.+) to cart$")
	public void I_add_prodcut_to_cart(String productName) {
		productCatalogue.addProductToCart(productName);
		
	}
	//you can use @And or @When for below
	@And ("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyCartDetails(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("Ind");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then ("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string) {
		String confirmMessag = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessag.equalsIgnoreCase(string));
	}
	
	@Then ("{string} message is displayed")
	public void message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
		
	}
}
