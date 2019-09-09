package com.challenge;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.FindAll;
//import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Test;
import src.main.Configuration;
import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class WebdriverIndex {

    protected static WebDriver driver;
    public String appUrl;
    public ChromeDriverService service;
    public EdgeDriverService edgeservice;
    public InternetExplorerDriverService ieservice;
    public String BROWSER = "chrome";
    public String URL = "https://www.binance.com";
    public String PROJECT_DIR = System.getProperty("user.dir");


    @BeforeSuite
    public void Setup()throws Exception {
        DesiredCapabilities capabilities;


        if(BROWSER == null || BROWSER.contains("chrome"))	{
            System.setProperty("webdriver.chrome.driver", PROJECT_DIR + "/GridSettings/chromedriver");
            service = ChromeDriverService.createDefaultService();
            service.start();
            ChromeOptions options= new ChromeOptions();
            options.addArguments("disable-infobars");
            options.addArguments("start-maximized");
            //options.addExtensions(new File("C:\\SELENIUM_WD\\CHROME_DRIVER\\JSONView_v0.0.32.3.crx"));
            driver = new ChromeDriver(options);
            Thread.sleep(3000);
        }

        else if(BROWSER.equalsIgnoreCase("msedge"))	{
            System.setProperty("webdriver.edge.driver", "C:\\SELENIUM_WD\\MS_EDGE_DRIVER\\64bit\\msedgedriver.exe");
            capabilities = DesiredCapabilities.edge();
            capabilities.setCapability("nativeEvents", true);
            //edgeservice = EdgeDriverService.createDefaultService();
            //edgeservice.start();
            driver = new EdgeDriver(capabilities);
            driver.get(appUrl);
            Thread.sleep(160);
        }
    }//BeforeSuite


    @AfterSuite
    public void TearDown() {

        if (BROWSER.equalsIgnoreCase("chrome")) {
            service.stop();
        } //Chrome Only

        else if (BROWSER.equalsIgnoreCase("iexplorer")) {
            ieservice.stop();
        } //ie Only

        else if (BROWSER.equalsIgnoreCase("msedge")) {
            edgeservice.stop();
        } //edge Only

        driver.quit();

    }//AfterSuite

    public void SaveScreenShot(String ssName) throws IOException {
    String screenPath = PROJECT_DIR + "/test-output/screenshots/";
     String imageName = GetUniqueString() + ssName + "screenshot.png";
     String imagePath = screenPath + imageName;
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileUtils.copyFile(scrFile, new File(imagePath));
                 }
     catch (Exception e){ System.out.println(e.fillInStackTrace());}

    }


        public String GetUniqueString () {
            Date dt = new Date();
             SimpleDateFormat sdf= new SimpleDateFormat("yyHHmmss");
                        sdf.setTimeZone(TimeZone.getDefault());
                String str = sdf.format(dt);
            return(str);
        }

}