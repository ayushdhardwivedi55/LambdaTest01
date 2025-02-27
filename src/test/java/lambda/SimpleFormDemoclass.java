package lambda;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.cdimascio.dotenv.Dotenv;

public class SimpleFormDemoclass {
    private RemoteWebDriver driver;
    private String Status = "passed"; // LambdaTest status tracking

    @BeforeMethod
    public void setup() throws MalformedURLException {
        // Load credentials from .env file
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("LT_USERNAME");
        String accessKey = dotenv.get("LT_ACCESS_KEY");

        if (username == null || accessKey == null) {
            throw new RuntimeException("LambdaTest credentials are missing! Set them in .env file.");
        }
        //git clone https://github.com/ayushdhardwivedi55/Lambdatest
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
            throw new RuntimeException("Failed to start LambdaTest session: " + e.getMessage());
        }
    }

    @Test
    public void testSimpleFormDemo() {
        try {
            driver.get("https://www.lambdatest.com/selenium-playground/");
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement simpleFormDemoLink = wait.until(
                    ExpectedConditions.elementToBeClickable(By.linkText("Simple Form Demo")));
            simpleFormDemoLink.click();

            Assert.assertTrue(driver.getCurrentUrl().contains("simple-form-demo"), "URL validation failed!");
            System.out.println("URL Matched successfully!");

            String message = "Welcome to LambdaTest";

            WebElement messageBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("user-message")));
            messageBox.sendKeys(message);
            
            WebElement button = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("showInput")));
            button.click();

            WebElement outputMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("message")));
            Assert.assertEquals(outputMessage.getText(), message, "Message validation failed!");

            System.out.println("Test Passed: Message displayed correctly that is: @" + message);

        } catch (Exception e) {
            Status = "failed"; // Update LambdaTest status
            System.out.println("Test Failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
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
