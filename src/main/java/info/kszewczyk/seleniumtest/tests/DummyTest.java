package info.kszewczyk.seleniumtest.tests;

import info.kszewczyk.seleniumtest.SeleniumWebDriverFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class DummyTest {

    private static WebDriver browser;

    @BeforeClass
    public static void setupWebDriver(){
        try{
            browser = new SeleniumWebDriverFactory().create();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void browserTest(){
        System.out.println("Hi site "+browser.getTitle());
        browser.close();
        Assert.assertTrue(true);
    }
    @AfterClass
    public static void quitWebDriver(){
        browser.quit();
    }
}
