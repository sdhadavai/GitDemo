package rahulshettyacademy.abstractcomponenets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrdersPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement ordersButton;
	

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));

	}
	
	public void waitForWebElementToAppear(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForElementToDisappear(WebElement webelement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(webelement));

	}

	public void waitForElementToClickable(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));

	}

	public CartPage goToCartPage() {
		cartButton.click();
		return new CartPage(driver);
	}
	
	public OrdersPage goToOrdersPage() {
		ordersButton.click();
		return new OrdersPage(driver);
	}
}
