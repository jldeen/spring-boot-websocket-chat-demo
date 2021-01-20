package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By usernameField       = By.id("name");
    private By startChattingButton = By.className("username-submit");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void enterUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }

    public ChatPage clickStartChatting(){
        driver.findElement(startChattingButton).click();
        return new ChatPage(driver);
    }
}