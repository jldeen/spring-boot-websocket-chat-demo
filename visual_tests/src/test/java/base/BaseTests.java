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

public class BaseTests {

  protected static WebDriver driver;
  protected static Eyes eyes;
  protected static HomePage homePage;
  protected static ChromeOptions options = new ChromeOptions();

  @BeforeClass
  public static void setUp() {
    // WebDriverManager.chromedriver().setup();
    // ChromeOptions options = new ChromeOptions();
    // options.addArguments("--headless");
    // driver = new ChromeDriver();

    WebDriverManager.chromedriver().setup();
    ChromeOptions ChromeOptions = new ChromeOptions();
    ChromeOptions.addArguments("--headless", "--no-sandbox");
    driver = new ChromeDriver(ChromeOptions);

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