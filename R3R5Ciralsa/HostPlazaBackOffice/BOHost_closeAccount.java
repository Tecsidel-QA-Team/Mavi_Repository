package HostPlazaBackOffice;

import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import R3R5CiralsaSettings.Settingsfields_File;

public class BOHost_closeAccount extends Settingsfields_File {		
		
		
		@Before
		public void setUp() throws Exception{
			System.setProperty("webdriver.chrome.driver", "E:\\workspace\\Mavi_Repository\\lib\\chromedriver.exe");
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
		public void assignVehicleandPANToExistingAccount() throws Exception {
			Actions action = new Actions(driver);
			borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_closeAccount\\attachments\\");
			try{
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_closeAccount\\attachments\\","loginBOPage.jpg");
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_closeAccount\\attachments\\","homeBOHostPage.jpg");
				BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
				Thread.sleep(2000);					
				action.clickAndHold(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(1000);
				driver.findElement(By.linkText("Seleccionar cuenta")).click();
				Thread.sleep(500);
				elementClick("ctl00_ButtonsZone_BtnSearch_IB_Label");
				Thread.sleep(500);
				WebElement tableResult = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
				List <WebElement> tableRe = tableResult.findElements(By.tagName("tr"));
				if (tableRe.size() > 1){
					int tableList = ranNumbr(2,tableRe.size());
					String account = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+tableList+"]/td[1]")).getText();
					driver.findElement(By.linkText(account)).click();
				}
				Thread.sleep(1000);
				accountNumbr = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
				if (driver.getPageSource().contains("CUENTA CERRADA")){
					System.out.println("No se puede cerrar la Cuenta: "+accountNumbr.substring(7,16)+" porque está cerrada");
					fail("No se puede cerrar la Cuenta: "+accountNumbr.substring(7,16)+" porque está cerrada");
				}
				String vehicleNum = driver.findElement(By.id("ctl00_ContentZone_lbl_vehicles")).getText();
				int vehicleNumbr = Integer.parseInt(vehicleNum);
				if (vehicleNumbr>0){
					System.out.println("No se puede cerrar la Cuenta: "+accountNumbr.substring(7,16)+" porque tiene "+vehicleNumbr+" vehículos asignado/s");
					fail("No se puede cerrar la Cuenta: "+accountNumbr.substring(7,16)+" porque tiene "+vehicleNumbr+" vehículos asignado/s");
				}
				elementClick("ctl00_ContentZone_BtnCloseAccount");
				Thread.sleep(1000);
				driver.findElement(By.id("ctl00_ContentZone_txtComment")).sendKeys("Se ha cerrado la cuenta");
				takeScreenShot("E:\\Selenium\\","accountClosePage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_closeAccount\\attachments\\","accountClosePage.jpg");
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
				Thread.sleep(3000);
				elementClick("ctl00_ButtonsZone_BtnBack_IB_Label");
				Thread.sleep(4000);
				takeScreenShot("E:\\Selenium\\","accountClosed"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_closeAccount\\attachments\\","accountClosed.jpg");
				System.out.println("Se cerrado la cuenta: "+accountNumbr.substring(7,16)+" correctamente ");
				String HMver = BOVersion.substring(1);
				if (HMver.length()>18){
					HMver = BOVersion.substring(17);			
				}else{
					HMver = "<HM is not running>";
				}				
				System.out.println("Se ha probado en la versión del BO Host: " + BOVersion.substring(1,16)+" y Host Manager: "+HMver);
			}catch (Exception e){
				fail (e.getMessage());
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
