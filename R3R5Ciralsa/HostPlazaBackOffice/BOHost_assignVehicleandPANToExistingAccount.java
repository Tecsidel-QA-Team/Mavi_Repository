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

public class BOHost_assignVehicleandPANToExistingAccount extends Settingsfields_File {		
		
		
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
			borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_accounAssignVehicleandPAN\\attachments\\");
			try{
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_accounAssignVehicleandPAN\\attachments\\","loginBOPage.jpg");
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_accounAssignVehicleandPAN\\attachments\\","homeBOHostPage.jpg");
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
					fail("No se puede asignar un Vehículo y Pan debido a que la Cuenta: "+accountNumbr.substring(7,16)+" porque está cerrada");
				}
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Label");
				Thread.sleep(1000);
				BOHost_accountCreationWithVehicleandPAN.vehicleCreation();
				Thread.sleep(1000);
				System.out.println("Se ha asignado un vehículo a la cuenta: "+accountNumbr.substring(7,16)+" correctamente con el vehículo creado con la matricula: "+matriNu+ " y el PAN: "+PAN);
				String HMver = BOVersion.substring(1);				
				if (HMver.length()>18){
					HMver = BOVersion.substring(17);
					BOVersion=BOVersion.substring(1,16);
				}else{
					HMver = "<HM is not running>";
					BOVersion=BOVersion.substring(1,7);
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
