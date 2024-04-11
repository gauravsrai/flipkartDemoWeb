package pages;

import baseTest.CommonUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h3/span[text()='Login or Signup']")
    private WebElement headerLogin;

    /**
     * Description: Validating login page
     * @Author Gaurav
     */
    public LoginPage validateLoginPage() {
        logger.info("Validating login page");
        if(headerLogin.isDisplayed()){
            logger.info("User is redirected to login page");
        }else{
            logger.info("Failed to redirect to login page");
        }
        return new LoginPage(driver,wait);
    }

}
