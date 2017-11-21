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

public class BOHost_tarifasGestionTarifas extends Settingsfields_File {		
		
		
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
		public void gestionTarifas() throws Exception {
			Actions action = new Actions(driver);
			borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\");
			try{
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","loginBOPage.jpeg");
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","homeBOHostPage.jpeg");
				BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
				Thread.sleep(2000);					
				action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
				Thread.sleep(200);
				action.clickAndHold(driver.findElement(By.linkText("Tarifas & moneda"))).build().perform();
				Thread.sleep(1000);
				driver.findElement(By.linkText("Gestión de tarifas")).click();
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","creacionTarifasPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","creacionTarifasPage.jpeg");
				SendKeys("ctl00_ContentZone_dt_newTime_box_date", dateSel(2017,2018));
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_dt_newTime_box_hour", hourFormat(0,23,0,59));
				Thread.sleep(100);
				elementClick("ctl00_ContentZone_ButtonClone");
				Thread.sleep(2000);
				if (driver.getPageSource().contains("Por favor, introduzca una fecha futura") || driver.getPageSource().contains("Ya existe un grupo de tarifa para la fecha dada")){
					String errorText = driver.findElement(By.xpath("//*[@id='toast-container']/div/div")).getText();
					System.out.println("No se puede crear la Tarifa debido a: "+errorText);
					fail("No se puede crear la Tarifa debido a: "+errorText);
				}
				takeScreenShot("E:\\Selenium\\","creacionTarifas2Page"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","creacionTarifas2Page.jpeg");
				elementClick("ctl00_ContentZone_BtnCreate");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","creacionTarifas3Page"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","creacionTarifas3Page.jpeg");
				elementClick("ctl00_ContentZone_ChkOrigTollCompany");
				Thread.sleep(100);
				selectDropDown("ctl00_ContentZone_CmbOrigTollCompany");
				Thread.sleep(300);
				elementClick("ctl00_ContentZone_ChkOrigPlaza");
				Thread.sleep(100);
				selectDropDown("ctl00_ContentZone_CmbOrigPlaza");
				Thread.sleep(300);
				elementClick("ctl00_ContentZone_ChkCompany");
				Thread.sleep(100);
				selectDropDown("ctl00_ContentZone_CmbCompany");
				Thread.sleep(300);
				elementClick("ctl00_ContentZone_ChkPlaza");
				Thread.sleep(100);
				selectDropDown("ctl00_ContentZone_CmbPlaza");
				Thread.sleep(300);
				elementClick("ctl00_ContentZone_ChkLaneDir");
				Thread.sleep(100);
				selectDropDown("ctl00_ContentZone_CmbLaneDir");
				Thread.sleep(300);
				elementClick("ctl00_ContentZone_ChkClass");
				Thread.sleep(100);
				selectDropDown("ctl00_ContentZone_CmbClass");
				Thread.sleep(300);
				elementClick("ctl00_ContentZone_ChkFareAggrupation");
				Thread.sleep(100);
				selectDropDown("ctl00_ContentZone_CmbFareAggrupation");
				Thread.sleep(1000);
				SendKeys("ctl00_ContentZone_BoxAmount_txt_formated",ranNumbr(10000,500000)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxVat_txt_formated",ranNumbr(1000,10000)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxVatPct_txt_formated",ranNumbr(100,3000)+"");
				Thread.sleep(1000);
				SendKeys("ctl00_ContentZone_BoxExternalAmount_txt_formated",ranNumbr(10000,500000)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxExternalVat_txt_formated",ranNumbr(1000,10000)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxExternalVatPct_txt_formated",ranNumbr(100,3000)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMaxRouteTime",ranNumbr(1000,10000)+"");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","creacionTarifasFilledPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","creacionTarifasFilledPage.jpeg");
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
				Thread.sleep(3000);
				String fechaAct = driver.findElement(By.id("ctl00_ContentZone_txt_ActivationTime_box_data")).getAttribute("value");
				System.out.println("Se ha creado una Tarifa correctamente con fecha de activación: "+fechaAct);
				
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
