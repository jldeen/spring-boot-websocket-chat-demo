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

// container cicd packages
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

// begin test classes
public class BaseTests {

  protected static WebDriver driver;
  protected static Eyes eyes;
  protected static HomePage homePage;
  protected static ChromeOptions options = new ChromeOptions();
  private static String _runWhere;

  @BeforeClass
  public static void setUp() throws MalformedURLException, InterruptedException{
  
    _runWhere = System.getenv("RUNWHERE");
    ChromeOptions ChromeOptions = new ChromeOptions();
    WebDriverManager.chromedriver().setup();

    if (_runWhere.equals("local")) {
      // Standard local visual test call
      driver = new ChromeDriver();
    } 
    else if (_runWhere.equals("pipeline")) {
      // build server headless chrome CI/CD example
      ChromeOptions.addArguments("--headless", "--no-sandbox");
      driver = new ChromeDriver(ChromeOptions);
    }
    else if (_runWhere.equals("container")) {
      // selenium hub remote settings (container based CI/CD)
      String Selenium = "http://selenium_hub:4444/wd/hub";
      driver = new RemoteWebDriver(new URL(Selenium), ChromeOptions);
    };
      
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