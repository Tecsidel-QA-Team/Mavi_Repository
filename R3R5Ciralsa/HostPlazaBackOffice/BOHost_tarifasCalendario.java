package HostPlazaBackOffice;

import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


import R3R5CiralsaSettings.Settingsfields_File;

public class BOHost_tarifasCalendario extends Settingsfields_File {		
		
		
		@Before
		public void setUp() throws Exception{
    		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
    			/*DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
    			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true
    			cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);*/
    				//ChromeOptions opts =  new ChromeOptions(); poner esta opción cuando se vaya a subir a Git
    				//opts.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"); poner esta opción cuando se vaya a subir a Git
    				driver = new ChromeDriver();//opts); poner esta opción cuando se vaya a subir al Git
    				//driver.manage().window().maximize();	
    				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		}

		@Test
		public void Calendario() throws Exception {
			Actions action = new Actions(driver);
			borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_tarifasCalendario\\attachments\\");
			try{
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasCalendario\\attachments\\","loginBOPage.jpeg");
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasCalendario\\attachments\\","homeBOHostPage.jpeg");
				BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
				Thread.sleep(2000);					
				action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
				Thread.sleep(200);
				action.clickAndHold(driver.findElement(By.linkText("Tarifas & moneda"))).build().perform();
				Thread.sleep(1000);
				driver.findElement(By.linkText("Calendario")).click();
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","CalendarioPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasCalendario\\attachments\\","CalendarioPage.jpeg");
				elementClick("ctl00_ContentZone_BtnCreate");
				Thread.sleep(1000);
				
				SendKeys("ctl00_ContentZone_dt_from_box_date", dateSel(2017,2018));
				Thread.sleep(500);
				String dateDesde = driver.findElement(By.id("ctl00_ContentZone_dt_from_box_date")).getAttribute("value"); 
				String dateHasta = " ";
				if (ranNumbr(0,1)>0){
					elementClick("ctl00_ContentZone_ChkTo");
					Thread.sleep(500);
					SendKeys("ctl00_ContentZone_dt_to_box_date", dateSel(2018,2019));
					Thread.sleep(500);
					dateHasta = driver.findElement(By.id("ctl00_ContentZone_dt_to_box_date")).getAttribute("value");
				}
				takeScreenShot("E:\\Selenium\\","CalendarioFilledPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasCalendario\\attachments\\","CalendarioFilledPage.jpeg");
				Thread.sleep(500);
				elementClick("ctl00_ContentZone_BtnSubmit");
				Thread.sleep(1000);
				if (driver.getPageSource().contains("Por favor, introduzca una fecha futura") || driver.getPageSource().contains("Fecha 'hasta' no puede ser anterior a 'desde'") || driver.getPageSource().contains("El rango de fecha introducido se solapa")){
					String errorText = driver.findElement(By.xpath("//*[@id='toast-container']/div/div")).getText();
					System.out.println("No se puede crear Calendario para Tarifas debido a: "+errorText);
					fail("No se puede crear Calendario para Tarifas debido a: "+errorText);
				}else{
					if (dateHasta.equals(" ")){
					System.out.println("Se ha Creado el Calendario Festivo de Tarifas correctamente con fecha: "+dateDesde+" hasta "+dateDesde);
					}else{
						System.out.println("Se ha Creado el Calendario Festivo de Tarifas correctamente con fecha desde: "+dateDesde+" hasta: "+dateHasta);
					}
				}
				Thread.sleep(3000);
			}catch (Exception e){
				e.printStackTrace();
				fail(e.getMessage());
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
		