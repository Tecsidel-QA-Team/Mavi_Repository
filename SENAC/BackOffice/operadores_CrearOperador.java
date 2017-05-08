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

public class operadores_CrearOperador extends senacFieldsConfiguration{
		public static String opzero=""; //numero de operador
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
			public void senacOperadoresPage() throws Exception{
				Actions action = new Actions(driver);
				try{
					driver.get(baseUrl);
					//takeScreenShot("loginpageSenac"+timet+".jpge");
					driver.findElement(By.id(loginField)).sendKeys("00001");
					driver.findElement(By.id(passField)).sendKeys("00001");
					driver.findElement(By.id(loginButton)).click();
					Thread.sleep(1000);
					//takeScreenShot("homePageSenac"+timet+".jpge");
					Thread.sleep(1000);
					action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
					Thread.sleep(1000);
					action.clickAndHold(driver.findElement(By.linkText("Operadores"))).build().perform();
					Thread.sleep(1000);
					driver.findElement(By.linkText("Gestión de operadores")).click();
					Thread.sleep(2000);
					driver.findElement(By.id("ctl00_ContentZone_BtnCreate")).click(); // Botón crear operador
					Thread.sleep(1500);
					//takeScreenShot("operatorCrateScr"+timet+".jpge");
					Thread.sleep(500);
					int opId = ranNumbr(1,99999);					
					String opIdnumbr = String.valueOf(opId);
						if (opIdnumbr.length() < 5){
							int opI = 5-opIdnumbr.length();
							char [] opc = new char [opI];
							for (int i = 0; i < opI; i++){
								opc[i] = '0';
								opzero = opzero.concat(String.valueOf(opc[i]));								
							}
							opzero = opzero.concat(String.valueOf(opId));														
						}else{
							opzero = String.valueOf(opId) ;
						}
						Thread.sleep(2000);
						operatorCreate();
						driver.findElement(By.id("ctl00_BtnLogOut")).click();
						Thread.sleep(2000);
						driver.switchTo().alert().accept();
						Thread.sleep(3000);
						driver.findElement(By.id(loginField)).sendKeys(opzero);
						driver.findElement(By.id(passField)).sendKeys(opzero);
						driver.findElement(By.id(loginButton)).click();
						Thread.sleep(4000);
						System.out.println("El Operador "+opzero+" ha sido creado y entra correctamente a BackOffice");
				}catch(Exception e){
					e.printStackTrace();
					fail();
				}
		}		
		public static void operatorCreate() throws Exception{
			Thread.sleep(1000);
			driver.findElement(By.id(opIdField)).sendKeys(opzero);
			int selOp = ranNumbr(0,8);
			driver.findElement(By.id(nameField)).sendKeys(nameOp[selOp]);
			driver.findElement(By.id(lastNameField)).sendKeys(lastNameOp[selOp]);
			driver.findElement(By.id(emailField)).sendKeys(nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.es");
			selectDropDownClick(groupOperator);
			driver.findElement(By.id(pwdField)).sendKeys(opzero);
			driver.findElement(By.id(repeatpwdField)).sendKeys(opzero);	
			driver.findElement(By.id("ctl00_ContentZone_ChkSalde")).click();
			driver.findElement(By.id("ctl00_ContentZone_ChkHistorique")).click();
			driver.findElement(By.id(hourNumber)).sendKeys(ranNumbr(1,1000)+"");	
			Thread.sleep(500);
			driver.findElement(By.id(submitBtn)).click();
			Thread.sleep(3000);
	
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
