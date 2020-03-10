package base;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// containers
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

// begin test classes
public class BaseTests {

  protected static WebDriver driver;
  protected static Eyes eyes;
  protected static HomePage homePage;
  protected static ChromeOptions options = new ChromeOptions();

  @BeforeClass
  public static void setUp() throws MalformedURLException, InterruptedException{
    // Standard local visual test call
    // WebDriverManager.chromedriver().setup();
    // driver = new ChromeDriver();

    // CI/CD Container or Remote Selenium hub
    String Selenium = "http://selenium_hub:4444/wd/hub";
    ChromeOptions cap = new ChromeOptions();

    driver = new RemoteWebDriver(new URL(Selenium), cap);

    // For use with Applitools
    eyes = new Eyes();
    homePage = new HomePage(driver);
  }

  @AfterClass
  public static void tearDown() {
    driver.quit();
    eyes.abortIfNotClosed();
  }

  protected void validateWindow() {
    eyes.open(
        driver, 
        "ChattyBot", 
        Thread.currentThread().getStackTrace()[2].getMethodName(),
        new RectangleSize(1200, 774));
    eyes.checkWindow();
    eyes.close();
  }

}