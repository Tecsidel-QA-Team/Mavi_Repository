package BackOffice;

import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import BackOffice.senacFieldsConfiguration;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


public class transacciones_revisionTransaccionesBusqueda extends senacFieldsConfiguration{
		
@Before
		public void setUp() throws Exception{
    		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
    			/*DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
    			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true
    			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);*/
    				//ChromeOptions opts =  new ChromeOptions(); poner esta opción cuando se vaya a subir a Git
    				//opts.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"); poner esta opción cuando se vaya a subir a Git
    				driver = new ChromeDriver();//opts); poner esta opción cuando se vaya a subir al Git
    				driver.manage().window().maximize();	
    				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			}
		@Test
			public void senacGestionTurnosPage() throws Exception{
				Actions action = new Actions(driver);
				borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\transacciones_revisiónTransacciones\\attachments\\");
				try{
					driver.get(baseUrl);
					takeScreenShot("E:\\Selenium\\","loginpageSenac_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\transacciones_revisiónTransacciones\\attachments\\","loginpageSenac.jpg");					
					driver.findElement(By.id(loginField)).sendKeys("00001");
					driver.findElement(By.id(passField)).sendKeys("00001");
					driver.findElement(By.id(loginButton)).click();
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homepageSenac_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\transacciones_revisiónTransacciones\\attachments\\","homepageSenac.jpg");
					Thread.sleep(2000);					
					action.clickAndHold(driver.findElement(By.linkText("Transacciones"))).build().perform();
					Thread.sleep(1000);
					driver.findElement(By.linkText("Revisión de Transacciones")).click();								
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","revisionTransicionesPage_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\transacciones_revisiónTransacciones\\attachments\\","revisionTransicionesPage.jpg");
					driver.findElement(By.id("ctl00_ContentZone_dateSelector_dt_from_box_date")).clear();
					driver.findElement(By.id("ctl00_ContentZone_dateSelector_dt_from_box_date")).sendKeys("01/01/2017");
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ContentZone_Button_transactions")).click();
					Thread.sleep(3000);
					takeScreenShot("E:\\Selenium\\","revisionTransicionesResults_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\transacciones_revisiónTransacciones\\attachments\\","revisionTransicionesResults.jpg");
					String elementsFound = driver.findElement(By.id("ctl00_ContentZone_tablePager_LblCounter")).getText();				
					Thread.sleep(1500);
					System.out.println("Busqueda Completa: "+ elementsFound);
					Thread.sleep(1000);
				}catch(Exception e){
					e.printStackTrace();
					fail();
				}
		}				
		@After
			public void tearDown() throws Exception{			  
				    driver.quit();
				    String verificationErrorString = verificationErrors.toString();
				    if (!"".equals(verificationErrorString)) {
				      fail(verificationErrorString);
				    }
				  
		}
			
		
		
	  //Edit buttons icons configuration.	  
	      	
}
