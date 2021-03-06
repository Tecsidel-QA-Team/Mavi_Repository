package BackOffice;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Timestamp;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import BackOffice.senacFieldsConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BOPlazatransacciones_verTransaccionesBusqueda extends senacFieldsConfiguration{
		
@Before
		public void setUp() throws Exception{
    		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
    			/*DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
    			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true
    			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);*/
    				//ChromeOptions opts =  new ChromeOptions(); poner esta opci�n cuando se vaya a subir a Git
    				//opts.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"); poner esta opci�n cuando se vaya a subir a Git
    				driver = new ChromeDriver();//opts); poner esta opci�n cuando se vaya a subir al Git
    				driver.manage().window().maximize();	
    				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			}
		@Test
			public void senacGestionTurnosPage() throws Exception{
				Actions action = new Actions(driver);
				borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\Plaza_VerTranscciones\\attachments\\");
				try{
					driver.get("http://virtualbo-qa/BOQAPlazaSenac");
					takeScreenShot("E:\\Selenium\\","loginPlazaSenacPage"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\Plaza_VerTranscciones\\attachments\\","loginPlazaSenacPage.jpg");
					driver.findElement(By.id(loginField)).sendKeys("00001");
					driver.findElement(By.id(passField)).sendKeys("00001");
					driver.findElement(By.id(loginButton)).click();
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homePlazaSenacPage"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\Plaza_VerTranscciones\\attachments\\","homePlazaSenacPage.jpg");
					Thread.sleep(2000);					
					action.clickAndHold(driver.findElement(By.linkText("Gestion des transactions"))).build().perform();
					Thread.sleep(1000);
					driver.findElement(By.linkText("Voir transactions")).click();								
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","verTransaccionesPage"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\Plaza_VerTranscciones\\attachments\\","verTransaccionesPage.jpg");
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ContentZone_dateSelector_dt_from_box_date")).clear();
					driver.findElement(By.id("ctl00_ContentZone_dateSelector_dt_from_box_date")).sendKeys("15/05/2017");
					Thread.sleep(1000);
					selectDropDown("ctl00_ContentZone_cmb_vehicle_class_cmb_dropdown");
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ButtonsZone_BtnSearch")).click();
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","verTransaccionesResults"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\Plaza_VerTranscciones\\attachments\\","verTransaccionesResults.jpg");
					Thread.sleep(500);
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
