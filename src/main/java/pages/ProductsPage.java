package pages;

import baseTest.BaseTest;
import baseTest.CommonUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(ProductsPage.class);

    public ProductsPage(WebDriver driver, WebDriverWait wait){
        this.driver=driver;
        this.wait=wait;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[contains(text(),'results for')]")
    private WebElement txtResults;

    @FindBy(xpath = "//div[contains(@data-id,'SHOG')]/div/a")
    private WebElement lnkFirstProduct;

    @FindBy(xpath = "//div[contains(@data-id,'SHOG')]/div/div")
    private WebElement txtFirstProductDetails;

    public WebElement filter(String filter){
        return driver.findElement(By.xpath("//div[text()='"+filter+"']"));
    }

    public WebElement searchToFilter(String filter){
        return driver.findElement(By.xpath("//div[text()='"+filter+"']/parent::div/following-sibling::div//input"));
    }

    public WebElement addToFilter(String value){
        return driver.findElement(By.xpath("//div[text()='"+value+"']/ancestor::div/label"));
    }

    public WebElement addedFilter(String value){
        return driver.findElement(By.xpath("//span[text()='Filters']/parent::div/parent::div/following-sibling::div//div[text()='"+value+"']"));
    }

    /**
     * Description: Validating the search results
     * @Author Gaurav
     * @param product
     * @return ProductPage
     */
    public ProductsPage validateSearchResult(String product){
        logger.info("Validating Search Results");
        wait.until(ExpectedConditions.visibilityOf(txtResults));
        String actualResultText= txtResults.getText();
        Assert.assertEquals(true,actualResultText.contains(product),"Failed to Search products for "+product);
        logger.info("User Successfully navigated to product "+product+" page");
        return new ProductsPage(driver,wait);
    }

    /**
     * Description: Applying filter brand
     * @Author Gaurav
     * @param brands
     * @return ProductPage
     */
    public ProductsPage applyFilterBrand(String[] brands){
        logger.info("Applying filters for Brands");
        CommonUtility.scrollToElement(filter(BaseTest.testData.getProperty("filterBrand")),"Brand Filter");
        for (int i=0;i<brands.length;i++){
            logger.info("Searching for Brands "+brands[i]);
            CommonUtility.sendKeys(searchToFilter(BaseTest.testData.getProperty("filterBrand")),brands[i],"Search Brand Text Field");
            CommonUtility.click(addToFilter(brands[i]),"Brand "+brands[i]);

            if(CommonUtility.isElementDisplayed(addedFilter(brands[i]))){
                logger.info("The Brand "+brands[i]+" successfully added to the filter");
            }else{
                Assert.fail("Failed to apply Brand "+brands[i]+" to the filter");
            }
            if(!(i==(brands.length)-1)){
                CommonUtility.scrollToElement(filter(BaseTest.testData.getProperty("filterBrand")), "Brand Filter");
            }
        }

        return new ProductsPage(driver,wait);
    }

    /**
     * Description: Applying filter Customer Ratings
     * @Author Gaurav
     * @param customerRatings
     * @return ProductPage
     */
    public ProductsPage applyFilterCustomerRatings(List<String> customerRatings){
        logger.info("Applying filters for Customer Ratings");
        //Not yet implemented
        return new ProductsPage(driver,wait);
    }

    /**
     * Description: Applying filter Color
     * @Author Gaurav
     * @param color
     * @return ProductPage
     */
    public ProductsPage applyFilterColor(String[] color){
        logger.info("Applying filters for Color");
        CommonUtility.scrollToElement(filter(BaseTest.testData.getProperty("filterColor")),"Color Filter");
        for (int i=0;i<color.length;i++){
            logger.info("Searching for color "+color[i]);
            CommonUtility.sendKeys(searchToFilter(BaseTest.testData.getProperty("filterColor")),color[i],"Search Color Text Field");
            CommonUtility.click(addToFilter(color[i]),"Color "+color[i]);

            if(CommonUtility.isElementDisplayed(addedFilter(color[i]))){
                logger.info("The Color "+color[i]+" successfully added to the filter");
            }else{
                Assert.fail("Failed to apply color "+color[i]+" to the filter");
            }
            if(!(i==(color.length)-1)) {
                CommonUtility.scrollToElement(filter(BaseTest.testData.getProperty("filterColor")), "Color Filter");
            }
        }
        return new ProductsPage(driver,wait);
    }

    /**
     * Description: Applying filter Discounts
     * @Author Gaurav
     * @param discounts
     * @return ProductPage
     */
    public ProductsPage applyFilterDiscounts(String[] discounts){
        logger.info("Applying filters for Discounts");
        // Not Yet Implemented
        return new ProductsPage(driver,wait);
    }

    /**
     * Description: Selecting the first product
     * @Author Gaurav
     * @return String (product details)
     */
    public String  selectFirstProduct(){
        logger.info("Selecting First product in the screen");
        CommonUtility.poll(5);
        String productDetails=txtFirstProductDetails.getText();
        CommonUtility.click(lnkFirstProduct,"First Product");
        return productDetails;
    }

}
