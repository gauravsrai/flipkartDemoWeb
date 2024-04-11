package scripts;

import baseTest.BaseTest;
import baseTest.CommonUtility;
import org.testng.annotations.Test;
import pages.ProductsPage;

public class FlipcartTest extends BaseTest {

    @Test
    public void test_001(){

        String[]color={"Red","Blue","Green"};
        String[]brands={"PUMA","AMICO"};

        String originalWindowHandle = driver.getWindowHandle();

        // Search for product
        ProductsPage productPage = initialize.homePage.searchProduct(BaseTest.testData.getProperty("productName"))
                                                     .validateSearchResult(BaseTest.testData.getProperty("productName"));
        // applying filters and selecting the first product
        String productDetails = productPage.applyFilterColor(color)
                .applyFilterBrand(brands)
                .selectFirstProduct();

        // Switching to the product details tab
        CommonUtility.switchToDifferentTab(originalWindowHandle);

        // Buying the product for the first size
        initialize.productDetailsPage
                .validateProductDetails(productDetails)
                .selectSize()
                .clickOnBuy();

        //Validating login page
        initialize.loginPage.validateLoginPage();
    }
}
