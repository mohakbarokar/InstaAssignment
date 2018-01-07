package testScripts;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
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
 *This Test Case Searches String "Instawor" in first 5 Pages of Google Search Results
 */

public class NegativeSearchInsta extends BaseTestScript{

	@Test
	public void negSearchInsta() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(GoogleHome.inputBox)));
		
		driver.findElement(By.id(GoogleHome.inputBox)).clear();
		driver.findElement(By.id(GoogleHome.inputBox)).sendKeys(Constants.negSearchString);
		driver.findElement(By.xpath(GoogleHome.SearchBtn)).click();
		
		for(int k=1; k<=4;k++){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SearchPage.resultLinks)));
		List<WebElement> searchClick = driver.findElements(By.xpath(SearchPage.resulClicktTitle));
		List<WebElement> searchLinks = driver.findElements(By.xpath(SearchPage.resultLinks));			
		
		System.out.println("=========================================================");
		System.out.println("Search Results :: Count:: "+searchLinks.size());
		System.out.println("=========================================================");
		
		    try{
		         for(int i=0;i<searchLinks.size();i++) {
			     int j=i+1;
		         Constants.getLink = searchLinks.get(i).getText();
		  
		             if(Constants.getLink.equalsIgnoreCase(Constants.instaLink)) {
				      System.out.println("InstaWork is positioned at No. "+j+" in Google Search");
				      try{
					      searchClick.get(i).click();
					      wait.until(ExpectedConditions.titleContains("InstaWork"));
					      Constants.pageTitle = driver.getTitle();
					      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(InstaworkHome.InstaworkIcon)));
					      Assert.assertEquals(Constants.instaTitle, Constants.pageTitle);
				          break;
				         }
		              catch(Exception e)
				         {
				           System.out.println(e.getMessage());
				         }
				        
		                }
			         else {
				       System.out.println("Instawork Not Found in Search Results Page: "+k);
				       System.out.println("Searching on Next Page...");
				       Reusable.scrollBottomOfPage();
				       driver.findElement(By.id(SearchPage.nextPage)).click();
				       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SearchPage.resulClicktTitle)));
			              }
		          }
		     break;
		    }
		 catch(Exception e)
		   {
		      System.out.println(e.getMessage());
		   }
				
		  }
	   }
	}