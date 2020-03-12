package base;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import io.github.bonigarcia.wdm.WebDriverManager;

import pages.ChatPage;
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
  private static String runWhere;
  private static ChromeOptions chromeOptions;
  protected static String appUrl;

  @BeforeClass
  public static void setSuite(){
    appUrl = System.getenv().get("TEST_START_PAGE");

    // obtain the batch name and ID from the environment variables
    String batchName = System.getenv("APPLITOOLS_BATCH_NAME");
    String batchId   = System.getenv("APPLITOOLS_BATCH_ID");

    // set the batch
    BatchInfo batchInfo = new BatchInfo(batchName);
    batchInfo.setId(batchId);
    eyes.setBatch(batchInfo);
  }

  @BeforeClass
  public static void setUp() throws MalformedURLException{

    chromeOptions = new ChromeOptions();
    WebDriverManager.chromedriver().setup();

    getEnvironment();
      
    // For use with Applitools
    eyes = new Eyes();
    homePage = new HomePage(driver);
  }

  private static void getEnvironment() throws MalformedURLException {
    runWhere = System.getenv("RUNWHERE");

    if (runWhere.equals("local")) {
      // Standard local visual test call
      driver = new ChromeDriver();
    }
    else if (runWhere.equals("pipeline")) {
      // build server headless chrome CI/CD example
      chromeOptions.addArguments("--headless", "--no-sandbox");
      driver = new ChromeDriver(chromeOptions);
    }
    else if (runWhere.equals("container")) {
      // selenium hub remote settings (container based CI/CD)
      String Selenium = "http://selenium_hub:4444/wd/hub";
      driver = new RemoteWebDriver(new URL(Selenium), chromeOptions);
    };
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
    eyes.check(Target.window().ignore(ChatPage.leftMessage));
    eyes.close();
  }
}