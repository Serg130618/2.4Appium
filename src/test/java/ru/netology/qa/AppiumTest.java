package ru.netology.qa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import ru.netology.qa.screens.PageAppium;


public class AppiumTest {
    PageAppium pageAppium;
    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "phone");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        pageAppium = new PageAppium(driver);
    }
    @Test
    public void EmptyText() {
        pageAppium = new PageAppium(driver);
        pageAppium.userInput.isDisplayed();
        pageAppium.userInput.click();
        String textBefore = pageAppium.textChanged.getText();
        pageAppium.userInput.sendKeys(" ");
        pageAppium.buttonChange.isDisplayed();
        pageAppium.buttonChange.click();
        String textAfter = pageAppium.textChanged.getText();
        pageAppium.textChanged.isDisplayed();
        Assertions.assertEquals(textBefore, textAfter);
    }
    @Test
    public void activityTest() throws InterruptedException {
        pageAppium = new PageAppium(driver);
        pageAppium.userInput.isDisplayed();
        pageAppium.userInput.click();
        pageAppium.userInput.sendKeys("qwerty");
        pageAppium.openTextInActivity.isDisplayed();
        pageAppium.openTextInActivity.click();
        Thread.sleep(3000);
        pageAppium.expectedText.isDisplayed();
        Assertions.assertEquals("qwerty", pageAppium.expectedText.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}

