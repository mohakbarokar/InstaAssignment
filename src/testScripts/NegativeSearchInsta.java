package testScripts;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.Constants;
import common.Reusable;
import setupConfig.BaseTestScript;
import uiComponentScreens.GoogleHome;
import uiComponentScreens.InstaworkHome;
import uiComponentScreens.SearchPage;

/**
 * @author Mohak
 *
 *         This Test Case Searches String "Instawor" in first 5 Pages of Google
 *         Search Results
 */

public class NegativeSearchInsta extends BaseTestScript {

	Reusable objReusable = new Reusable();
	GoogleHome objGhome = new GoogleHome();
	InstaworkHome objIhome = new InstaworkHome();
	SearchPage objSpage = new SearchPage();

	@Test
	public void negSearchInsta() throws InterruptedException {
		objReusable.Wait_for_Element(objGhome.inputBox);
		Reporter.log("Google Home Page Loaded");
		objReusable.clickOnElement(objGhome.inputBox);
		objReusable.sendKey(objGhome.inputBox, Constants.strNegSearchString);
		objReusable.clickOnElement(objGhome.searchBtn);
		Reporter.log("Search Initiated");

		for (int k = 1; k <= 4; k++) {
			objReusable.Wait_for_Element(objSpage.resultLinks);
			Reporter.log("Search Results Page loaded");
			List<WebElement> searchLinks = objReusable.GetElementsList(objSpage.resultLinks);
			System.out.println("=========================================================");
			System.out.println("Search Results :: Count:: " + searchLinks.size());
			System.out.println("=========================================================");

			for (int i = 0; i < searchLinks.size(); i++) {

				String getLink = searchLinks.get(i).getText();

				if (getLink.equalsIgnoreCase(Constants.strInstaLink)) {
					Reporter.log(Constants.strInstaLink + "Found in Search Results");
					System.out.println("InstaWork is positioned at No. " + (i + 1) + " in Google Search");
					boolean checkclick = objReusable.ClickOnIndex(objSpage.resulClicktTitle, i);
					if (checkclick = true) {
						System.out.println("Clicked on Index = " + checkclick);
						String pageTitle = driver.getTitle();
						Reporter.log(pageTitle + " Page Loaded");
						objReusable.Wait_for_Element(objIhome.InstaworkIcon);
						Assert.assertEquals(Constants.strInstaTitle, pageTitle);
						break;
					}

				} else {
					Reporter.log(Constants.strInstaLink + "Not Found in Search Results");
					System.out.println("Instawork Not Found in Search Results Page: " + k);
					System.out.println("Searching on Next Page...");
					objReusable.scrollBottomOfPage();
					Reporter.log("Scrolled to Bottom of Page");
					boolean checknextclick = objReusable.retryingFindClick(objSpage.nextPage);
					if (checknextclick = true) {
						System.out.println("Clicked on next page = " + checknextclick);
						Reporter.log("Navigated to Next Page");
						objReusable.Wait_for_Element(objSpage.resulClicktTitle);
						break;
					}

				}
			}
		}
	}
}