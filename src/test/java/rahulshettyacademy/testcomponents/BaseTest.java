package rahulshettyacademy.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		// Properties class can read the properties

		Properties property = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\resources\\GobalData.properties");
		property.load(fis);
		//Get the property from maven(while running script from cmd using -Dbrowser command) then use it otherwise use property from property file
		String browserName =  System.getProperty("browser")!=null ? System.getProperty("browser") : property.getProperty("browser");
		

		if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\SD\\SelfLearning\\AutomationByRS\\drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//Run in full screen
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\SD\\SelfLearning\\AutomationByRS\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;

	}

	@AfterMethod(alwaysRun = true)
	public void teaDwon() {
		driver.close();
	}
	
	public List<HashMap<String, String>>  getJsonDataToMap(String filePath) throws IOException {
		//Read JOSN to String
		String jsonConnect = FileUtils.readFileToString(new File (filePath), 
				StandardCharsets.UTF_8);
		 //String to HashMap we need dependency Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonConnect, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return data;
		//{{hmap1},{hmap2}}
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File fis = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+ "\\Reports\\" + testCaseName + ".png");
		FileUtils.copyFile(fis, file);
		return System.getProperty("user.dir")+ "\\Reports\\" + testCaseName + ".png";
	}

}
