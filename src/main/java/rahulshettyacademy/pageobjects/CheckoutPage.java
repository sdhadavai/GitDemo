package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponenets.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement countryText;

	@FindBy(css = ".ta-item")
	List<WebElement> countyList;

	By result = By.cssSelector(".ta-item");
	By sortedCountryList = By.xpath("//a[text()='Place Order ']");

	@FindBy(css = ".action__submit")
	WebElement submitBtn;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	public void enterCountry(String countryName) {
		countryText.sendKeys(countryName);
		for (WebElement item : countyList) {
			String country = item.getText();
			System.out.println(country);
			if (country.equals("India")) {
				waitForElementToClickable(sortedCountryList);
				item.click();
				break;
			}
		}
	}

	public void selectCountry(String countryName) {
		Actions action = new Actions(driver);
		action.sendKeys(countryText, countryName).build().perform();
		waitForElementToAppear(result);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		submitBtn.click();
		return new ConfirmationPage(driver);
	
	}

}
