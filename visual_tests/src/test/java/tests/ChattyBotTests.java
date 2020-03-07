package tests;

import com.applitools.eyes.BatchInfo;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import base.BaseTests;
import pages.ChatPage;

public class ChattyBotTests extends BaseTests {

    private ChatPage chatPage;
    private String _testStartPage;

    @BeforeClass
    public static void setSuite(){
        System.out.println("In setSuite()");
        Map<String, String> env = System.getenv();
        _testStartPage = env.get("TEST_START_PAGE");
        System.out.println("test start page: " + _testStartPage);
        eyes.setBatch(new BatchInfo("ChattyBot"));
        System.out.println("Done with setSuite()");
    }

    @Before 
    public void startSession(){
        System.out.println("In startSession()");
        System.out.println("test start page: " + _testStartPage);
        driver.get("https://cf-chattybot.k8s.az.jessicadeen.com/");
        homePage.enterUsername("angie");
        chatPage = homePage.clickStartChatting();
        System.out.println("Done with startSession()");
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