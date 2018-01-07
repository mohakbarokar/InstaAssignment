package testScripts;

import setupConfig.*;
import common.*;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import uiComponentScreens.*;



/**
 * @author Mohak
 *
 *This Test Case Searches for "Instawork" string in search results in Page 1
 */

public class SearchInsta extends BaseTestScript{

		@Test
		public void searchInsta() throws InterruptedException {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(GoogleHome.inputBox)));
			
			driver.findElement(By.id(GoogleHome.inputBox)).clear();
			driver.findElement(By.id(GoogleHome.inputBox)).sendKeys(Constants.searchString);
			
			if(ExpectedConditions.visibilityOfElementLocated(By.xpath(GoogleHome.searchTermBox)) != null)
			{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(GoogleHome.firstSearchTerm)));
			driver.findElement(By.id(GoogleHome.firstSearchTerm)).click();
			}
			else {
			  driver.findElement(By.xpath(GoogleHome.SearchBtn)).click();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SearchPage.resultLinks)));
			List<WebElement> searchClick = driver.findElements(By.xpath(SearchPage.resulClicktTitle));
			List<WebElement> searchLinks = driver.findElements(By.xpath(SearchPage.resultLinks));			
			
			System.out.println("=========================================================");
			System.out.println("Search Results :: Count:: "+searchLinks.size());
			System.out.println("=========================================================");
			
			for(int i=0;i<searchLinks.size();i++) {
				 int j=i+1;
			    Constants.getLink = searchLinks.get(i).getText();
			  
			    if(Constants.getLink.equalsIgnoreCase(Constants.instaLink)) {
					 System.out.println("InstaWork is positioned at No. "+j+" in Google Search");
					  try{
						 searchClick.get(i).click();
					     break;
					    }
					  catch(Exception e)
					    {
					     System.out.println(e.getMessage());
					    }
					 
					 wait.until(ExpectedConditions.titleContains("InstaWork"));
					 Constants.pageTitle = driver.getTitle();
					 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(InstaworkHome.InstaworkIcon)));
					 Assert.assertEquals(Constants.instaTitle, Constants.pageTitle);
				 }
			    else {
			    	System.out.println("Instawork Not Found in First Page of Search Results");
			    }
			}
			
	 }
}
