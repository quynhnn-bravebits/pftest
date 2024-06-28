package org.pftest.listeners;

import org.pftest.constants.FrameworkConstants;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private final int limit = Integer.parseInt(FrameworkConstants.RETRY_TEST_FAIL);

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (count < limit) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}
