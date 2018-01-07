package common;

import org.openqa.selenium.JavascriptExecutor;

import setupConfig.BaseTestScript;

public class Reusable {
	
public static void scrollBottomOfPage(){
		
		((JavascriptExecutor) BaseTestScript.driver).executeScript("javascript:window.scrollBy(0,800)");
	}

}
