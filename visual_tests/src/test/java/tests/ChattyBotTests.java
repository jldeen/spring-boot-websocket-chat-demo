package tests;

import org.junit.Before;
import org.junit.Test;
import base.BaseTests;
import pages.ChatPage;

public class ChattyBotTests extends BaseTests {

    private ChatPage chatPage;

    @Before
    public void startSession(){
        driver.get(testStartPage);
        homePage.enterUsername("angie");
        chatPage = homePage.clickStartChatting();
    }

    @Test
    public void newSession(){
        validateWindow();
    }

    @Test
    public void enterMessage(){
        chatPage.sendMessage("hello world");
        validateWindow();
    }

    @Test
    public void multiUser(){
        chatPage.sendMessage("hello world");
        chatPage.sendMessage("goodbye world");
        validateWindow();
    }
}