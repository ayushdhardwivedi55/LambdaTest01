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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.cdimascio.dotenv.Dotenv;

public class InputFormSubmitTest {
    private RemoteWebDriver driver;
    private WebDriverWait wait;  // Declare once
    private String Status = "passed"; // LambdaTest status tracking

    @BeforeMethod
    public void setup() throws MalformedURLException {
        // Load credentials from .env file
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("LT_USERNAME");
        String accessKey = dotenv.get("LT_ACCESS_KEY");

        if (username == null || accessKey == null) {
            throw new RuntimeException(" LambdaTest credentials are missing! Set them in .env file.");
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
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Declare once here
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to start LambdaTest session: " + e.getMessage());
        }
    }

    @Test
    public void TestScenario3() {
    	//1.opening the input form
    	driver.get("https://www.lambdatest.com/selenium-playground/");
		String title = driver.getTitle();
		System.out.println(title);
		
    			WebElement inputForm = driver.findElement(By.cssSelector("#__next > div > section.mb-50 > div > ul > li:nth-child(20) > a"));
    			inputForm.click();
    			String title2 = driver.getTitle();
    			System.out.println("Inout Form page opened with title: " + title2);
    			
    			//2.click on submit without entering any details.
    			WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"seleniumform\"]/div[6]/button"));
    			submitButton.click();
    			
    			//3.Assert the error message.
    			WebElement firstNameField = driver.findElement(By.id("name")); // Adjust locator if necessary
    	        String validationMessage = firstNameField.getAttribute("validationMessage");

    	        if (validationMessage.equals("Please fill out this field.")) {
    	            System.out.println("Test Passed: Validation message is displayed.");
    	        } else {
    	            System.out.println("Test Failed: Expected validation message not found.");
    	        }
    			
    	        //select the fields
    	        driver.findElement(By.id("name")).sendKeys("Ayush Dhar Dwivedi");
    	        driver.findElement(By.id("inputEmail4")).sendKeys("ayush@example.com");
    	        driver.findElement(By.cssSelector("#inputPassword4")).sendKeys("Ayush@123");
    	        driver.findElement(By.id("company")).sendKeys("Lovely Professional University");
    	        driver.findElement(By.id("websitename")).sendKeys("https://ayushdev.com");

    	        // Step 4: Select "United States" from the Country drop-down
    	        WebElement countryDropdown = driver.findElement(By.name("country"));
    	        Select selectCountry = new Select(countryDropdown);
    	        selectCountry.selectByVisibleText("United States");

    	        // Step 5: Fill in other required fields
    	        driver.findElement(By.id("inputCity")).sendKeys("New York");
    	        driver.findElement(By.id("inputAddress1")).sendKeys("123 Main Street");
    	        driver.findElement(By.id("inputAddress2")).sendKeys("Suite 500");
    	        driver.findElement(By.id("inputState")).sendKeys("NY");
    	        driver.findElement(By.id("inputZip")).sendKeys("10001");

    	        // Step 6: Click the Submit button
    	        //WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
    	        submitButton.click();

    	        // Step 7: Verify success message
    	        WebElement successMessage = driver.findElement(By.className("success-msg"));
    	        if (successMessage.getText().contains("Thanks for contacting us")) {
    	            System.out.println("Test Passed: Form submitted successfully!");
    	        } else {
    	            System.out.println("Test Failed: Success message not found.");
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
