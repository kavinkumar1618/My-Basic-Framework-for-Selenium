package org.kavin.driver;

import org.kavin.constant.uttils.FrameWorkConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    public static WebDriver driver;

    public static void initDriver() {
        driver = new ChromeDriver();
        driver.get(FrameWorkConstants.URL);
        driver.manage().window().maximize();
    }

    public static void quitDriver() {
        driver.quit();
    }
}
