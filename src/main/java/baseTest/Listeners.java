package baseTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    public static final Logger logger = LogManager.getLogger(Listeners.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test PASSED: " + result.getName());
        CommonUtility.takeScreeShot();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("Test Failed: " + result.getName());
        CommonUtility.takeScreeShot();
    }

    }


