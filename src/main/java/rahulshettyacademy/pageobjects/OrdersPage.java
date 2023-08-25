package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponenets.AbstractComponents;

public class OrdersPage extends AbstractComponents{
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> prodcustNames;
	
	public boolean verifyOrderDisplayed(String productName) {
		boolean match = prodcustNames.stream().anyMatch(prodcut-> prodcut.getText().equalsIgnoreCase(productName));
		return match;
	}
	

}
