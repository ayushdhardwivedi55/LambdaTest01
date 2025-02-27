package lambda;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.cdimascio.dotenv.Dotenv;

public class DragDropSliderTest {
    private RemoteWebDriver driver;
    private String Status = "passed"; // LambdaTest status tracking

    @BeforeMethod
    public void setup() throws MalformedURLException {
        // Load credentials from .env file
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("LT_USERNAME");
        String accessKey = dotenv.get("LT_ACCESS_KEY");

        if (username == null || accessKey == null) {
            throw new RuntimeException("❌ LambdaTest credentials are missing! Set them in .env file.");
        }

        // LambdaTest Hub URL
        String lambdaHub = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

        // Desired Capabilities for LambdaTest
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "latest");
        capabilities.setCapability("platformName", "Windows 10");

        // Additional LambdaTest options
        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("project", "Selenium Certification");
        ltOptions.put("build", "TestNG With Java");
        ltOptions.put("w3c", true);
        capabilities.setCapability("LT:Options", ltOptions);

        // Initialize RemoteWebDriver
        try {
            driver = new RemoteWebDriver(new URL(lambdaHub), capabilities);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to start LambdaTest session: " + e.getMessage());
        }
    }

    @Test

	public void TestScenario2() throws InterruptedException {

		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().maximize();
		Thread.sleep(2000);

		WebElement DrageAndDroplink = driver.findElement(
				By.xpath("//a[@href='https://www.lambdatest.com/selenium-playground/drag-drop-range-sliders-demo']"));
		DrageAndDroplink.click();

		Thread.sleep(1000);
		WebElement slider3 = driver.findElement(By.xpath(".//*[@id='slider3']/div/input"));
		//js.executeScript("arguments[0].scrollIntoView(true);", slider3);
		Thread.sleep(1000);
		Actions move = new Actions(driver);
		Actions action = (Actions) move.dragAndDropBy(slider3, 99, 0);
		action.perform();

		WebElement Expected_Range = driver.findElement(By.xpath(".//*[@id='slider3']/div/output"));
		String Expe_range = Expected_Range.getText();
		String Actual_Range = "95";

		if (Expe_range.contains(Actual_Range)) {
			System.out.println("Range is matched");
		} else {
			System.out.println("Range is not matched!");
		}

	}

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.executeScript("lambda-status=" + Status);
            driver.quit();
        }
    }
}

