package pages;

import baseTest.CommonUtility;
import exceptions.SoldOutException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(ProductDetailsPage.class);

    public ProductDetailsPage(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[text()='Buy Now']")
    private WebElement btnBuyNow;

    @FindBy(xpath = "//button[text()='Add to cart']")
    private WebElement btnAddToCart;

    @FindBy(xpath = "//h1/span")
    private WebElement txtBrandName;

    @FindBy(xpath = "//h1/span/following-sibling::span")
    private WebElement txtProductName;

    @FindBy(xpath = "//div[text()='Sold Out']")
    private WebElement txtSoldOut;

    @FindBy(xpath = "//span[contains(@id,'Size')]/following-sibling::div//li/a")
    private WebElement btnFirstSize;


    /**
     * Description: Validating the Product details page
     * @Author Gaurav
     * @return ProductDetailsPage
     */
    public ProductDetailsPage validateProductDetails(String productDetails){
        logger.info("Validating the Product details page");
        String productName = txtProductName.getText().split("\\s+\\(")[0];
        if(productDetails.contains(txtBrandName.getText().split("\\s+")[0]) && productDetails.contains(productName)){
            logger.info("User is redirected to product details page");
        }else{
            Assert.fail("Failed to redirect to product details page");
        }

        return new ProductDetailsPage(driver,wait);
    }

    /**
     * Description: Select size
     * @Author Gaurav
     * @return ProductDetailsPage
     */
    public ProductDetailsPage selectSize(){
        logger.info("Select size");
        CommonUtility.click(btnFirstSize,"First Size Button");
        CommonUtility.poll(3);

        try{
            if(txtSoldOut.isDisplayed()){
                logger.info("The product is sold out");
                try {
                    throw new SoldOutException("The product is sold out and try for different size");
                }catch (SoldOutException e){
                    e.printStackTrace();
                    Assert.fail("The product is sold out and try for different size");
                }
            }else{
                logger.info("The product with smaller size is selected");
            }
        }catch (Exception ef){
            logger.info("The product with smaller size is selected");
        }

        return new ProductDetailsPage(driver,wait);
    }


    /**
     * Description: Click on Buyu Now button
     * @Author Gaurav
     */
    public void clickOnBuy() {
        logger.info("Click on Buy now button");
        CommonUtility.click(btnBuyNow,"Buy Now Button");
    }
}
