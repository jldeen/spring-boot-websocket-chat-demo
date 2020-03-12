package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.stream.Collectors;

public class WindowUtils {

    public static void openNewTab(WebDriver driver, String url){
        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", url);
        driver.getWindowHandles().forEach(driver.switchTo()::window);
    }

    /**
     * Switches to an open tab
     * @param index which tab to switch to (1-based
     */
    public static void switchToTab(WebDriver driver, int index){
        String window = driver.getWindowHandles().stream().collect(Collectors.toList()).get(index - 1);
        driver.switchTo().window(window);
    }
}
