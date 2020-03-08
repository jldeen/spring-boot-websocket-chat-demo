package tests;

import com.applitools.eyes.BatchInfo;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import base.BaseTests;
import pages.ChatPage;

public class ChattyBotTests extends BaseTests {

    private ChatPage chatPage;

    private static String _testStartPage;

    @BeforeClass
    public static void setSuite(){
        _testStartPage = System.getenv().get("TEST_START_PAGE");
        // eyes.setBatch(new BatchInfo("ChattyBot"));

        // obtain the batch name and ID from the environment variables
        String batchName = System.getenv("APPLITOOLS_BATCH_NAME");
        String batchId   = System.getenv("APPLITOOLS_BATCH_ID");

        // set the batch
        BatchInfo batchInfo = new BatchInfo(batchName); 
        batchInfo.setId(batchId);
        eyes.setBatch(batchInfo);
    }

    @Before 
    public void startSession(){
        driver.get(_testStartPage);
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