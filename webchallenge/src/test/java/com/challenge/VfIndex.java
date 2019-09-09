package com.challenge;


//import org.apache.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;
//import java.util.logging.Level;


public class VfIndex extends WebdriverIndex {

    @Test(priority=1)
    public void Trial1() {
        JavascriptExecutor js = (JavascriptExecutor)  driver;

        driver.navigate().to(URL);
            driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(), 'Log In')]")).getText(), "Log In");
            System.out.println("******** Login link is enabled ****************");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            SaveScreenShot("homepage");
        }
        catch (Exception e){ System.out.println("Was unable to take screenshot");}

        js.executeScript("window.scrollBy(700, 1000)");

        driver.manage().timeouts().implicitlyWait(15,  TimeUnit.SECONDS);


        driver.manage().timeouts().implicitlyWait(15,  TimeUnit.SECONDS);
            driver.navigate().to("https://www.binance.com/en/trade/ETH_BTC");
                driver.manage().timeouts().implicitlyWait(15,  TimeUnit.SECONDS);
        try {
            SaveScreenShot("PairTrading");
        }
        catch (Exception e){ System.out.println("Was unable to take screenshot of Pair Trading");}

        //Verify Limit Link
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'Limit')]")).isDisplayed());
            System.out.println("********* Limit Link properly displays ********" );
        }
        catch (Exception e){System.out.println("Limit Link does not display: " + e.getMessage());}

        //Verify Market Link
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(), 'Market')]")).isDisplayed());
            System.out.println("********* Market Link properly displays ********" );
        }
        catch (Exception e){System.out.println("Market Link does not display:" + e.getMessage());}

        js.executeScript("window.scrollBy(500, 700)");

        //Display  StopPrice and stopLimitPrice inbox
        WebElement stopLimitItemList = driver.findElements(By.xpath("//li[@data-name='oco']")).get(0).findElement(By.tagName("span"));
        stopLimitItemList.click();

        WebElement stopLimitItemList1 = driver.findElements(By.xpath("//li[@data-name='Stop-limit']")).get(0).findElement(By.tagName("span"));
        stopLimitItemList1.click();

        //Verify stopPrice input box display
        try {
            Assert.assertTrue(driver.findElement(By.id("FormRow-BUY-stopPrice")).isDisplayed());
            System.out.println("********* Top-price properly displays ********" );
        }
        catch (Exception e){System.out.println("Stop-price does not display: " + e.getMessage());}

        //Verify stoplimitPrice input box
        try {
            Assert.assertTrue(driver.findElement(By.id("FormRow-BUY-stopLimitPrice")).isDisplayed());
            System.out.println("********* stopLimitPrice properly displays ********" );
        }
        catch (Exception e){System.out.println("StopLimitPrice does not display: " + e.getMessage());}

        //Verify Amount input box display
        try {
            Assert.assertTrue(driver.findElement(By.id("FormRow-BUY-quantity")).isDisplayed());
            System.out.println("********* Amount Input box properly displays ********" );
        }
        catch (Exception e){System.out.println("Amount input box does not display: " + e.getMessage());}




    }//Trial1


    @Test(priority=2)
    public void Trial2() throws Exception {
        int price = 0;
        int total = 0;

            driver.navigate().to("https://www.binance.com/en/trade/ETH_BTC");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);


            driver.findElement(By.id("FormRow-BUY-quantity")).isDisplayed();
                    driver.findElement(By.id("FormRow-BUY-price")).clear();
                        driver.findElement(By.id("FormRow-BUY-price")).click();
                            driver.findElement(By.id("FormRow-BUY-price")).sendKeys("10");

                Thread.sleep(1000);
                driver.findElement(By.id("FormRow-BUY-quantity")).sendKeys("500" + Keys.TAB);


        try {
            Assert.assertEquals(driver.findElement(By.id("FormRow-BUY-total")).getAttribute("value"), "8.70650000");
            System.out.println(" Total Amount is correct");
        }
        catch (Exception e){ System.out.println(e.getMessage() + "Total ammount is: " + driver.findElement(By.id("FormRow-BUY-total")).getAttribute("value"));}

        try {
            SaveScreenShot("TotalAmount");
        }
        catch (Exception e){ System.out.println("Was unable to take screenshot of Total amout");}


    }//Trial2




}
