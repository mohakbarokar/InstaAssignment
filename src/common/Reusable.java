package common;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import setupConfig.BaseTestScript;

public class Reusable {

	public void clickOnElement(UIElement element) throws InterruptedException {
		Thread.sleep(2000);
		try {
			System.out.println("Enter in click method:" + element.getCaption());
			BaseTestScript.driver.findElement(element.getByElement()).click();

		} catch (Exception e) {
			System.out.println("Element " + element.getCaption() + " not found.\n Original Error: " + e);
			Assert.assertTrue(false, "Element " + element.getCaption() + " not found.\n Original Error: " + e);
		}
	}

	public boolean ClickOnIndex(UIElement element, int index) throws InterruptedException {
		List<WebElement> ElementList = BaseTestScript.driver.findElements(element.getByElement());
		try {

			ElementList.get(index).click();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;

	}

	/* Method used for getting the Element Count */
	public int GetElementCount(UIElement element) {

		List<WebElement> ElementList = BaseTestScript.driver.findElements(element.getByElement());
		return ElementList.size();
	}

	/* Method used for getting the Element List */
	public List<WebElement> GetElementsList(UIElement element) {

		List<WebElement> ElementsList = BaseTestScript.driver.findElements(element.getByElement());
		return ElementsList;
	}

	public boolean isElementPresent(UIElement element) {

		try {

			WebDriverWait wait = new WebDriverWait(BaseTestScript.driver, 2);
			wait.until(ExpectedConditions.visibilityOfElementLocated(element.getByElement()));
			return true;

		} catch (Exception e) {
			System.out.println("Failed in check visibility method.");
		}
		return false;
	}

	public boolean retryingFindClick(UIElement element) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				System.out.println("Enter in click method:" + element.getCaption());
				BaseTestScript.driver.findElement(element.getByElement()).click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	public void scrollBottomOfPage() {

		((JavascriptExecutor) BaseTestScript.driver).executeScript("javascript:window.scrollBy(0,800)");
	}

	/* This method is used to enter given text on edit text box */
	public void sendKey(UIElement element, String textToSend) {

		try {
			BaseTestScript.driver.findElement(element.getByElement()).clear();
			BaseTestScript.driver.findElement(element.getByElement()).sendKeys(textToSend);

		} catch (Exception e) {
			System.out.println("Element " + element.getCaption() + " not found.\n Original Error: " + e);
			Assert.assertTrue(false, "Element " + element.getCaption() + " not found.\n Original Error: " + e);
		}
	}

	public void Wait_for_Element(UIElement element) throws InterruptedException {
		System.out.println("Enter in wait method:" + element.getCaption());

		try {
			WebDriverWait wait = new WebDriverWait(BaseTestScript.driver, Constants.lngTIMEOUT);
			wait.until(ExpectedConditions.presenceOfElementLocated(element.getByElement()));
		}

		catch (Exception e) {
			System.out.println("Wait timeout for element " + element.getCaption());
		}

	}

	public void Wait_for_Element(UIElement element, long timeout) {
		System.out.println("Enter in wait method:" + element.getCaption());

		try {
			WebDriverWait wait = new WebDriverWait(BaseTestScript.driver, timeout);
			wait.until(ExpectedConditions.presenceOfElementLocated(element.getByElement()));
		} catch (Exception e) {

		}

	}

}
