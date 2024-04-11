package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public static WebDriverWait wait;
    public InitializingTestBody initialize;
    public static Properties testData;
    public static final String TESTDATA = System.getProperty("user.dir") + "/src/test/resources/testData.properties";
    public static final Logger logger = LogManager.getLogger(BaseTest.class);
    public CommonUtility commonUtility;


    public WebDriver driver;



    @BeforeSuite
    public void setUpTestData(){
        try{
            testData = new Properties();
            FileInputStream testDataFile = new FileInputStream(TESTDATA);
            testData.load(testDataFile);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @BeforeClass
    public void launchBrowser()  {

        WebDriverManager.chromedriver().setup();

        driver=new ChromeDriver();
        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait=new WebDriverWait(driver,Duration.ofSeconds(60));

        initialize=new InitializingTestBody(driver,wait);
        commonUtility=new CommonUtility(driver,wait);

    }

    @AfterClass
    public void closeBrowser() {
        logger.info("Closing the browser");
        driver.quit();
    }


}
