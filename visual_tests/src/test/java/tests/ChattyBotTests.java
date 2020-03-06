package tests;

import com.applitools.eyes.BatchInfo;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import base.BaseTests;
import pages.ChatPage;

public class ChattyBotTests extends BaseTests {

    private ChatPage chatPage;

    @BeforeClass
    public static void setSuite(){
        eyes.setBatch(new BatchInfo("ChattyBot"));
    }

    @Before 
    public void startSession(){
        driver.get("https://cf-chattybot.k8s.az.jessicadeen.com/");
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
    public void enterMessages() {
        chatPage.sendMessage("hello world");
        chatPage.sendMessage("goodbye world");
        validateWindow();
    }
}