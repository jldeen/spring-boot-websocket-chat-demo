package tests;

import org.junit.Before;
import org.junit.Test;
import base.BaseTests;
import pages.ChatPage;
import utils.WindowUtils;

public class ChattyBotTests extends BaseTests {

    private ChatPage chatPage;

    @Before 
    public void startSession(){
        driver.get(appUrl);
        homePage.enterUsername("angie");
        chatPage = homePage.clickStartChatting();
    }

    @Test
    public void newSession(){
        validateWindow();
    }

    @Test
    public void enterMessage() {
        chatPage.sendMessage("hello world");
        validateWindow();
    }

    @Test
    public void multiPersonChat() {

        //Angie sends message
        chatPage.sendMessage("hello world");

        //Jessica opens app and sends message
        WindowUtils.openNewTab(driver, appUrl);

        homePage.enterUsername("jessica");
        chatPage = homePage.clickStartChatting();
        chatPage.sendMessage("goodbye world");

        WindowUtils.switchToTab(driver,1);
        validateWindow();
    }
}