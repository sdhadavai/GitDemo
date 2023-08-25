package rahulshettyacademy.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\SD\\SelfLearning\\AutomationByRS\\drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		String productName = "IPHONE 13 PRO";
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("sdhadavai.qa@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("JavaSelenium4$");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		System.out.println(prod.getText());
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//Thread.sleep(5000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		//Verifying added products in the cart
		List<WebElement> cartDetails = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartDetails.stream().anyMatch(cartProd->
		cartProd.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		//Actions action = new Actions(driver);
		//action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-item"))));
		
		List<WebElement> items = driver.findElements(By.cssSelector(".ta-item"));
		for(WebElement item: items) {
			String country = item.getText();
			System.out.println(country);
			if(country.equals("India")) {
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Place Order ']")));
				item.click();
				break;
			}
		}
		
		//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Place Order ']")));
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
		
	
		
	}

}
