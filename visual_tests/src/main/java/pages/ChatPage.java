package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChatPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By eventMessage = By.className("event-message");
    private By messageField = By.id("message");
    private By sendButton     = By.cssSelector("#messageForm button");
    private By sentMessage  = By.cssSelector(".chat-message p");

    public ChatPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(eventMessage));
    }

    public String getEventMessage(){
        return driver.findElement(eventMessage).getText();
    }

    public void enterMessage(String message){
        driver.findElement(messageField).sendKeys(message + Keys.ENTER);
    }

    public void clickSend() {
        driver.findElement(sendButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(sentMessage));
    }

    public void sendMessage(String message) {
        enterMessage(message);
        clickSend();
    }
}