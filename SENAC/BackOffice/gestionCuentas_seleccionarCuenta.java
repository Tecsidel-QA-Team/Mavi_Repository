package BackOffice;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Timestamp;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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



public class gestionCuentas_seleccionarCuenta extends senacFieldsConfiguration{

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
			public void senacGestionCuentasPage() throws Exception{
				Actions action = new Actions(driver);	
				borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\gestionCuentas_SeleccionarCuenta\\attachments\\");
				try{
					driver.get(baseUrl);
					takeScreenShot("E:\\Selenium\\","loginHostSenacPage_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\gestionCuentas_SeleccionarCuenta\\attachments\\","loginHostSenacPage.jpg");
					//takeScreenShot("loginpageSenac"+timet+".jpge");
					driver.findElement(By.id(loginField)).sendKeys("00001");
					driver.findElement(By.id(passField)).sendKeys("00001");
					driver.findElement(By.id(loginButton)).click();
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homeSenacPage_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\gestionCuentas_SeleccionarCuenta\\attachments\\","homeSenacPage.jpg");					
					Thread.sleep(1000);
					action.clickAndHold(driver.findElement(By.linkText("Gesti�n de cuentas"))).build().perform();
					Thread.sleep(1000);
					action.click(driver.findElement(By.linkText("Seleccionar cuenta"))).build().perform();
					Thread.sleep(1000);
					String tagNumbr = "68989";
					driver.findElement(By.id("ctl00_ContentZone_Textbox_byTag")).sendKeys(tagNumbr);
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ButtonsZone_BtnSearch")).click();
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","seleccionarCuentabyTag_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\gestionCuentas_SeleccionarCuenta\\attachments\\","seleccionarCuentabyTag.jpg");					
					Thread.sleep(1000);
					driver.findElement(By.id("ctl00_ContentZone_BtnVehicles")).click();
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","vehicleandTagAssigned_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\gestionCuentas_SeleccionarCuenta\\attachments\\","vehicleandTagAssigned.jpg");
					Thread.sleep(1000);	
					System.out.println("La cuenta se ha visualizado correctamente consultar los archivos de images de SeleccionarCuenta y TagAssigned para su verificaci�n");
					Thread.sleep(3000);
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
	      	
}
