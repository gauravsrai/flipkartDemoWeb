package baseTest;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;


public class CommonUtility {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions actions;
    public static final String screenShotPath = System.getProperty("user.dir") + "/src/test/resources/";
    public static final Logger logger = LogManager.getLogger(CommonUtility.class);

    public CommonUtility(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
        actions=new Actions(driver);
    }

    public static void click(WebElement ele, String elementName)  {
        wait.until(ExpectedConditions.visibilityOf(ele));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();
        logger.info("clicked on "+elementName);

    }

    public static void sendKeys(WebElement ele,String value, String elementName)  {
        wait.until(ExpectedConditions.visibilityOf(ele));
        ele.clear();
        ele.sendKeys(value);
        logger.info("Entered "+value+" into "+elementName);

    }

    public static void switchToDifferentTab(String originalWindowHandle)  {
        poll(5);
        Set<String> windowHandles = driver.getWindowHandles();
        for(String window:windowHandles){
            if(window!=originalWindowHandle){
                driver.switchTo().window(window);
            }
        }
        logger.info("Switched to different tab");

    }

    public static void scrollToElement(WebElement ele, String elementName) {
        logger.info("Scrolling to "+elementName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    public static boolean isElementDisplayed(WebElement ele) {
        wait.until(ExpectedConditions.visibilityOf(ele));
        return ele.isDisplayed();
    }

    public static void poll(int sec) {
        try {
            Thread.sleep(sec*1000);
        }catch (Exception e){

        }
    }

    public static void takeScreeShot()  {
        logger.info("Taking screenshot");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
        File screenShotFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
        File screenShotFileToReplace=new File(screenShotPath+"img"+formattedDateTime+".jpg");

        try {
            FileUtils.copyFile(screenShotFile,screenShotFileToReplace);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
