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
 *         This Test Case Searches for "Instawork" string in search results in
 *         Page 1
 */

public class SearchInsta extends BaseTestScript {

	Reusable objReusable = new Reusable();
	GoogleHome objGhome = new GoogleHome();
	InstaworkHome objIhome = new InstaworkHome();
	SearchPage objSpage = new SearchPage();

	@Test
	public void searchInsta() throws InterruptedException {
		objReusable.Wait_for_Element(objGhome.inputBox);
		Reporter.log("Google Home Page Loaded");
		objReusable.clickOnElement(objGhome.inputBox);
		objReusable.sendKey(objGhome.inputBox, Constants.strSearchString);

		if (objReusable.isElementPresent(objGhome.searchTermBox)) {
			objReusable.Wait_for_Element(objGhome.firstSearchTerm);
			objReusable.clickOnElement(objGhome.firstSearchTerm);
		} else {
			objReusable.clickOnElement(objGhome.searchBtn);
		}
		Reporter.log("Search Initiated");
		objReusable.Wait_for_Element(objSpage.resultLinks);
		Reporter.log("Search Results Page loaded");
		List<WebElement> searchLinks = objReusable.GetElementsList(objSpage.resultLinks);
		System.out.println("=========================================================");
		System.out.println("Search Results :: Count:: " + searchLinks.size());
		System.out.println("=========================================================");

		for (int i = 0; i < searchLinks.size(); i++) {
			String getLink = searchLinks.get(i).getText();

			if (getLink.equalsIgnoreCase(Constants.strInstaLink)) {
				Reporter.log(Constants.strInstaLink + " Found in search results");
				System.out.println("InstaWork is positioned at No. " + (i + 1) + " in Google Search");
				boolean checkclick = objReusable.ClickOnIndex(objSpage.resulClicktTitle, i);
				if (checkclick = true) {
					System.out.println("Clicked on Index = " + checkclick);
					objReusable.Wait_for_Element(objIhome.InstaworkIcon);
					String pageTitle = driver.getTitle();
					Reporter.log(pageTitle + " Page Loaded");
					Assert.assertEquals(Constants.strInstaTitle, pageTitle);
					break;
				}

			} else {

				System.out.println("Instawork Not Found in First Page of Search Results");
			}
		}

	}
}
