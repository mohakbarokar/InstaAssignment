package setupConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
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
		
		
		public static String driver_Path = new File("").getAbsolutePath();

	    
		@Parameters("browser")
	    @BeforeClass
		public void initiateBrowser(String browser) throws InterruptedException
		{	

		    File file = new File("C:\\Users\\User\\eclipse-workspace\\Insta_Assignment\\TestData.properties");
			
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
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Initiating Browser drivers
			
			if(browser.equalsIgnoreCase("firefox")) {
				 
				 System.setProperty("webdriver.gecko.driver", driver_Path+prop.getProperty("geckoPath"));
				
				 driver = new FirefoxDriver();
			  
			 
			  }
			  else if (browser.equalsIgnoreCase("ie")) { 
			 
				  System.setProperty("webdriver.ie.driver", driver_Path+prop.getProperty("iePath"));
			 
				  driver = new InternetExplorerDriver();
				  DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				  capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			 
			  }
			  else if (browser.equalsIgnoreCase("chrome")) {
				  System.setProperty("webdriver.chrome.driver", driver_Path+prop.getProperty("chromePath"));
				  driver = new ChromeDriver(); 
			  }
			  else if (browser.equalsIgnoreCase("edge")) {
				  System.setProperty("webdriver.edge.driver", driver_Path+prop.getProperty("edgePath"));
					driver = new EdgeDriver();
				  
			  }
			
		    driver.manage().window().maximize();
		    driver.get(prop.getProperty("baseUrl"));
		    driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			driver.manage().timeouts().pageLoadTimeout(50000,TimeUnit.MILLISECONDS);
			wait = new WebDriverWait(driver, 60000);
		     
		}
	 
	 
	  
	@AfterClass
	      public void terminateBrowser(){
	          driver.quit();
	      }

	    
}