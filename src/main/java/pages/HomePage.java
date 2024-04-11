package pages;


import baseTest.CommonUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[contains(@title,'Search')]")
    private WebElement tbSearch;

    @FindBy(xpath = "//button[contains(@aria-label,'Search')]")
    private WebElement btnSearch;


    public ProductsPage searchProduct(String product){
        logger.info("Search Product");
        CommonUtility.sendKeys(tbSearch,product,"Search Text Field");
        CommonUtility.click(btnSearch,"Search Button");
        return new ProductsPage(driver,wait);
    }
}
