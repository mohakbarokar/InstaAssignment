package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import setupConfig.BaseTestScript;

public class TestListener implements ITestListener {
	WebDriver driver = null;

	@Override
	public void onFinish(ITestContext context) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error " + result.getName() + " test has failed *****");
		String methodName = result.getName().toString().trim();
		takeScreenShot(methodName, result);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println("***** Success " + result.getName() + " test has passed *****");
		String methodName = result.getName().toString().trim();
		takeScreenShot(methodName, result);
	}

	public void takeScreenShot(String methodName, ITestResult result) {

		File file = new File(Constants.strProject_Path + "\\TestData.properties");

		FileInputStream fileInput = null;

		try {
			fileInput = new FileInputStream(file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// load properties file
		try {
			prop.load(fileInput);
			Reporter.log("Properties File Loaded");
		} catch (IOException e) {
			e.printStackTrace();

		}

		driver = BaseTestScript.driver;
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		if (prop.getProperty("ScreenshotFail").equalsIgnoreCase("true")) {
			if (result.getStatus() == 2) {
				try {
					FileUtils.copyFile(scrFile, new File(Constants.strProject_Path + "//Screenshots//Failed//"
							+ methodName + System.currentTimeMillis() + ".png"));
					System.out.println(
							"***Placed screen shot in " + Constants.strProject_Path + "\\Screenshots\\Failed" + " ***");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (prop.getProperty("ScreenshotPass").equalsIgnoreCase("true")) {
			if (result.getStatus() == 1) {
				try {
					FileUtils.copyFile(scrFile, new File(Constants.strProject_Path + "//Screenshots//Passed//"
							+ methodName + System.currentTimeMillis() + ".png"));
					System.out.println(
							"***Placed screen shot in " + Constants.strProject_Path + "\\Screenshots\\Passed" + " ***");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}