package org.kavin.test;

import org.kavin.constant.uttils.FrameWorkConstants;
import org.kavin.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class StandAloneTest extends BaseTest {

    @Test
    public void test() throws InterruptedException {

        Driver.driver.findElement(By.id("userEmail")).sendKeys(FrameWorkConstants.EMAIL);
        Driver.driver.findElement(By.id("userPassword")).sendKeys(FrameWorkConstants.PASSWORD);
        Driver.driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = Driver.driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(Product -> Product.findElement(By.cssSelector("b")).getText().equals(FrameWorkConstants.PRODUCT_NAME)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(Driver.driver.findElement(By.cssSelector(".ng-animating"))));
        Driver.driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
        Thread.sleep(3000);
        List<WebElement> cartProducts = Driver.driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(FrameWorkConstants.PRODUCT_NAME));
        Assert.assertTrue(match);
        Driver.driver.findElement(By.cssSelector(".totalRow button")).click();
        Actions a = new Actions(Driver.driver);
        a.sendKeys(Driver.driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        Driver.driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        Driver.driver.findElement(By.cssSelector(".actions a")).click();
        Thread.sleep(2000);
        String answer = Driver.driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(answer.equalsIgnoreCase(FrameWorkConstants.THANKING_MESSAGE_NAME));
        Thread.sleep(3000);
    }
}
