package setupConfig;
import java.io.File;

import org.testng.Reporter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;





public class BaseTestScript {
	
		
		public static WebDriver driver; 
		public static WebDriverWait wait;
		
		
		public static String project_Path = new File("").getAbsolutePath();
		
	    
		@Parameters("browser")
	    @BeforeClass
		public void initiateBrowser(String browser) throws InterruptedException
		{	

		    File file = new File(project_Path+"\\TestData.properties");
			
		    FileInputStream fileInput = null;
		
		    try{
				fileInput = new FileInputStream(file);
				
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Properties prop = new Properties();
			
			//load properties file
			try {
				prop.load(fileInput);
				Reporter.log("Properties File Loaded");
			} catch (IOException e) {
				e.printStackTrace();
				
			}
			
			//Initiating Browser drivers
			
			if(browser.equalsIgnoreCase("firefox")) {
				 
				 System.setProperty("webdriver.gecko.driver", project_Path+prop.getProperty("geckoPath"));
				
				 driver = new FirefoxDriver();
			  
			 
			  }
			  else if (browser.equalsIgnoreCase("ie")) { 
			 
				  System.setProperty("webdriver.ie.driver", project_Path+prop.getProperty("iePath"));
			 
				  driver = new InternetExplorerDriver();
				  DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				  capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			 
			  }
			  else if (browser.equalsIgnoreCase("chrome")) {
				  System.setProperty("webdriver.chrome.driver", project_Path+prop.getProperty("chromePath"));
				  driver = new ChromeDriver(); 
			  }
			  else if (browser.equalsIgnoreCase("edge")) {
				  System.setProperty("webdriver.edge.driver", project_Path+prop.getProperty("edgePath"));
					driver = new EdgeDriver();
				  
			  }
			Reporter.log("Browser Loaded");
		    driver.manage().window().maximize();
		    Reporter.log("Browser Window Maximized");
		    driver.get(prop.getProperty("baseUrl"));
		    Reporter.log("Loaded Base URL");
		    driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			driver.manage().timeouts().pageLoadTimeout(50000,TimeUnit.MILLISECONDS);
			wait = new WebDriverWait(driver, 60000);
		     
		}
	 
	 
	  
	@AfterClass
	   public void terminateBrowser(){
		
		try 
		  {
		   TakesScreenshot ts=(TakesScreenshot)driver;
		 
		   File source=ts.getScreenshotAs(OutputType.FILE);
		 
		   // result.getName() will return name of test case so that screenshot name will be same
		   FileUtils.copyFile(source, new File(project_Path+"\\Screenshots\\"+System.currentTimeMillis()+".png"));
		 
		   System.out.println("Screenshot taken");
		  } 
		catch (Exception e)
		 {
		 
		System.out.println("Exception while taking screenshot "+e.getMessage());
		 } 
		
		driver.quit();
		}
}
		 
