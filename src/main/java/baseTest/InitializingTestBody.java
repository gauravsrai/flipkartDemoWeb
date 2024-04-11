package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ProductDetailsPage;
import pages.ProductsPage;
import pages.HomePage;
import pages.LoginPage;


public class InitializingTestBody {
    public LoginPage loginPage;
    public HomePage homePage;
    public ProductsPage productsPage;
    public ProductDetailsPage productDetailsPage;

    public InitializingTestBody(WebDriver driver, WebDriverWait wait){
        loginPage=new LoginPage(driver,wait);
        homePage=new HomePage(driver,wait);
        productsPage =new ProductsPage(driver,wait);
        productDetailsPage =new ProductDetailsPage(driver,wait);
    }
}
