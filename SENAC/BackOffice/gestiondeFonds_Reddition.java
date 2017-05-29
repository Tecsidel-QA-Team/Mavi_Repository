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
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class gestiondeFonds_Reddition extends senacFieldsConfiguration{
		
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
				borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\gestionFonds_Reddition\\attachments\\");
				try{
					driver.get("http://virtualbo-qa/BOQAPlazaSenac");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","loginPlazatSenacPage_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\gestionFonds_Reddition\\attachments\\","loginPlazaSenacPage.jpg");				
					driver.findElement(By.id(loginField)).sendKeys("00001");
					driver.findElement(By.id(passField)).sendKeys("00001");
					driver.findElement(By.id(loginButton)).click();
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","homePlazatSenacPage_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\gestionFonds_Reddition\\attachments\\","homePlazaSenacPage.jpg");					
					Thread.sleep(2000);					
					action.clickAndHold(driver.findElement(By.linkText("Gestion des fonds"))).build().perform();
					Thread.sleep(1000);
					driver.findElement(By.linkText("Reddition")).click();								
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","RedditionPage_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\gestionFonds_Reddition\\attachments\\","RedditionPage.jpg");
					Thread.sleep(1000);
					selectDropDown("ctl00_ContentZone_cmb_numBags_cmb_dropdown");
					Thread.sleep(1000);
					WebElement bagsnumbr = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_numBags_cmb_dropdown"))).getFirstSelectedOption();
					String bagNum = bagsnumbr.getText();
					Thread.sleep(500);
					for (int i=1; i <=Integer.parseInt(bagNum);i++){
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C5_"+i)).sendKeys(ranNumbr(1,4)+"");
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C10_"+i)).sendKeys(ranNumbr(1,4)+"");
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C25_"+i)).sendKeys(ranNumbr(1,4)+"");
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C50_"+i)).sendKeys(ranNumbr(1,4)+"");
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C100_"+i)).sendKeys(ranNumbr(1,4)+"");
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C200_"+i)).sendKeys(ranNumbr(1,4)+"");
						Thread.sleep(200);						
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C250_"+i)).sendKeys(ranNumbr(1,4)+"");
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01C500_"+i)).sendKeys(ranNumbr(1,4)+"");
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N500_"+i)).sendKeys(ranNumbr(1,5)+"");
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N1000_"+i)).sendKeys(ranNumbr(1,5)+"");
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N2000_"+i)).sendKeys(ranNumbr(1,5)+"");
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N5000_"+i)).sendKeys(ranNumbr(1,5)+"");
						Thread.sleep(200);
						driver.findElement(By.id("ctl00_ContentZone_NumberCASH01N10000_"+i)).sendKeys(ranNumbr(1,5)+"");
					}
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ContentZone_NumberCH201")).sendKeys(ranNumbr(1,5)+"");
					Thread.sleep(200);
					driver.findElement(By.id("ctl00_ContentZone_NumberCH202")).sendKeys(ranNumbr(1000,10000)+"");
					Thread.sleep(200);
					driver.findElement(By.id("ctl00_ContentZone_NumberVO01201")).sendKeys(ranNumbr(1,5)+"");
					Thread.sleep(200);
					driver.findElement(By.id("ctl00_ContentZone_NumberVO01202")).sendKeys(ranNumbr(1000,10000)+"");
					Thread.sleep(200);
					driver.findElement(By.id("ctl00_ContentZone_NumberOM201")).sendKeys(ranNumbr(1,5)+"");
					Thread.sleep(200);
					driver.findElement(By.id("ctl00_ContentZone_NumberOM202")).sendKeys(ranNumbr(1000,10000)+"");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","RedditionPageDataFilled_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\gestionFonds_Reddition\\attachments\\","RedditionPageDataFilled.jpg");
					Thread.sleep(1000);
					driver.findElement(By.id("ctl00_ButtonsZone_BtnSubmit")).click();
					Thread.sleep(400);
					if (isAlertPresent()){
						driver.switchTo().alert().accept();
					}
					Thread.sleep(500);
					if (isAlertPresent()){
						driver.switchTo().alert().accept();		
						Thread.sleep(4000);
						takeScreenShot("E:\\Selenium\\","RedditionCreated_"+timet+".jpg");
						takeScreenShot("E:\\workspace\\Mavi_Repository\\gestionFonds_Reddition\\attachments\\","RedditionCreated.jpg");
						Thread.sleep(1000);
						System.out.println("Se ha creado correctamente Reddition");
					}else{
						String nextPTitle = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
						Thread.sleep(500);
							if (nextPTitle.contains("Une erreur a été detectée")){
								Thread.sleep(1500);
								takeScreenShot("E:\\Selenium\\","RedditionErr_"+timet+".jpg");
								takeScreenShot("E:\\workspace\\Mavi_Repository\\gestionFonds_Reddition\\attachments\\","RedditionErr.jpg");
								Thread.sleep(1000);
								String errorCreation = driver.findElement(By.id("ctl00_ContentZone_lblMsg")).getText();
								Thread.sleep(100);
								System.out.println("ERROR AL CREAR REDDITION :"+errorCreation);
								fail(errorCreation);
								return;
							}
						
					}
					
					Thread.sleep(5000);

				}catch(Exception e){
					e.printStackTrace();
					fail();
				}
		}	
		public static boolean isAlertPresent(){
		    try{
		        driver.switchTo().alert();
		        return true;
		    }catch (NoAlertPresentException e){
		        return false;
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
