package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponenets.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);// This will initialize all the elements in this class and construct driver.findElements....
	}
	
	
	//WebElement userEmail =driver.findElement(By.id("userEmail"));
	
	//Simplifying with PageFactory method
	//WebElements
	@FindBy(id="userEmail")
	WebElement txtUserEmail;
	
	@FindBy(id="userPassword")
	WebElement txtPassword;
	
	@FindBy(id="login")
	WebElement BtnLogin;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//Action methods
	public ProductCatalogue loginApplication(String email, String password) {
		txtUserEmail.sendKeys(email);
		txtPassword.sendKeys(password);
		BtnLogin.click();
		return new ProductCatalogue(driver);
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	

}
