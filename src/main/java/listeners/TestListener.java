package listeners;

import com.aventstack.extentreports.Status;
import helpers.CaptureHelper;
import helpers.PropertiesHelper;
import reports.AllureManager;
import reports.ExtentReportManager;
import reports.ExtentTestManager;
import utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext arg0) {
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext result) {
        ExtentReportManager.getExtentReports().flush();

    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info(" ");
        LogUtils.info("️⭐️⭐️⭐️TestStart: " + result.getName() + "⭐⭐️⭐️⭐️");
        CaptureHelper.startRecord(result.getName());
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("❤️☘️☘️☘️️===>TestSuccess: " + result.getName() + " is SUCCESSFULLY.❤️❤️❤️❤️❤️");
        CaptureHelper.stopRecord();
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is Passed.");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("❌☘️☘️☘️===>TestFailure: " + result.getName() + " is FAILED. ❌❌❌❌❌");
        CaptureHelper.captureScreenshot(result.getName());
        CaptureHelper.stopRecord();
        ExtentTestManager.addScreenShot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());//Lấy ra lý do lỗi
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is Failed.");
        AllureManager.saveScreenshotPNG();
    }
}
