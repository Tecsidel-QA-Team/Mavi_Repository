package HostPlazaBackOffice;

import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import R3R5CiralsaSettings.Settingsfields_File;
import javafx.scene.chart.Chart;

public class BOHost_gestion_MapeoDAC_CAT extends Settingsfields_File {		
		private static String [] gestionDACOption = {"Crear", "Modificar", "Eliminar"};
		private static String OptionSel;
		private static String DACIndice; 
		private static String DACNumber;
		private static int opSel;
		private static String recordFound;
		
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
		public void gestion_MapeoDAC_CAT() throws Exception {
			borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_gestionMapeoDAC\\attachments\\");
			driver.get(BoHostUrl);
			takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMapeoDAC\\attachments\\","loginBOPage.jpeg");
			loginPage("00001","00001");
			takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMapeoDAC\\attachments\\","homeBOHostPage.jpeg");
			BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
			Thread.sleep(2000);
			String HMver = BOVersion.substring(1);
			if (HMver.length()>18){
				HMver = BOVersion.substring(18);			
			}else{
				HMver = "<HM is not running>";
			}			
			opSel = ranNumbr(0,2);
			switch (gestionDACOption [opSel]){
				case "Crear":		OptionSel = "ctl00_ContentZone_BtnCreate";
									crear_modificarDAC();
									System.out.println("Se ha Creado el DAC: " +DACNumber+" con el número de índice: "+DACIndice+" correctamente");
									Thread.sleep(1000);
									break;
				case "Modificar":	OptionSel = "ctl00_ContentZone_BtnModify";
									crear_modificarDAC();
									System.out.println("Se ha Modificado el DAC: "+ DACNumber+" del índice: "+DACIndice+" correctamente");
									break;
				case "Eliminar": 	OptionSel = "ctl00_ContentZone_BtnDelete";
									eliminar_DAC();
									System.out.println("Se ha Eliminado el DAC: " + DACNumber+" del índice: "+DACIndice+" correctamente");
									break;								
			}
			
			System.out.println("Se ha probado en la versión del BO Host: " + BOVersion.substring(1,16)+" y Host Manager: "+HMver);
			
		}
		
		public static void crear_modificarDAC() throws Exception{
			Actions action = new Actions(driver);			
			try{
				action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
				Thread.sleep(1000);
				action.clickAndHold(driver.findElement(By.linkText("Gestión de clases"))).build().perform();
				driver.findElement(By.linkText("Mapeo DAC vs categorías")).click();
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","gestionMapeoDACpage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMapeoDAC\\attachments\\","gestionMapeoDACpage.jpeg");
				if (gestionDACOption[opSel]=="Modificar"){
					int selRecord;
					String recordDis = driver.findElement(By.id("ctl00_ContentZone_tablePager_LblCounter")).getText();
					if (recordDis.length()>25){
						selRecord = ranNumbr(1,Integer.parseInt(recordDis.substring(25)));						
											
					}else{
						selRecord = ranNumbr(1,Integer.parseInt(recordDis.substring(24)));
					}					
					if (selRecord>21){
						elementClick("ctl00_ContentZone_tablePager_BtnNext");
						if (selRecord>40 && selRecord<61){
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);								
						}
						if (selRecord>60 && selRecord<81){
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);
						}
						if (selRecord >80){
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);
						}
							String recSel = String.valueOf(selRecord);  
							int a = Integer.parseInt(recSel.substring(1));
							if (a==1){
								a=2;
							}
							DACNumber = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[3]")).getText(); 
							DACIndice = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[2]")).getText();
							recordFound = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[1]/input")).getAttribute("id");																																	
							elementClick(recordFound);
							Thread.sleep(1000);								
													
					}else{
						if (selRecord == 1){
							selRecord=2;
						}
						DACNumber = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRecord+"]/td[3]")).getText(); 
						DACIndice = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRecord+"]/td[2]")).getText();
						recordFound = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRecord+"]/td[1]/input")).getAttribute("id");																																	
						elementClick(recordFound);							
						Thread.sleep(1000);						
						
					}			
				}				
				elementClick(OptionSel);
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","gestionMapeoDACCreatepage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMapeoDAC\\attachments\\","gestionMapeoDACCreatepage.jpeg");
				selectDropDown("ctl00_ContentZone_CmbCompany_cmb_dropdown");
				if (gestionDACOption[opSel]=="Crear"){
					int indic = ranNumbr(1,99);					
					if (indic < 10){
						DACIndice = "0".concat(String.valueOf(indic));
					}else{
						DACIndice = String.valueOf(indic);
					}
					SendKeys("ctl00_ContentZone_BoxMapIndex_box_data",DACIndice);
					WebElement Concesionaria = new Select(driver.findElement(By.id("ctl00_ContentZone_CmbCompany_cmb_dropdown"))).getFirstSelectedOption();
					DACNumber = Concesionaria.getText();
				}				
				Thread.sleep(100);
				selectDropDown("ctl00_ContentZone_CmbClassId_cmb_dropdown");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMinDoubleWheelLeft_box_data", ranNumbr(1,3)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMaxDoubleWheelLeft_box_data", ranNumbr(3,6)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMinDoubleWheelRight_box_data", ranNumbr(1,3)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMaxDoubleWheelRight_box_data", ranNumbr(3,6)+"");
				Thread.sleep(100);
				driver.findElement(By.id("ctl00_ContentZone_RdBus_"+ranNumbr(0,2))).click();
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMinAxlesleft_box_data", ranNumbr(1,3)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMaxAxlesleft_box_data", ranNumbr(3,6)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMinAxlesright_box_data", ranNumbr(1,3)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMaxAxlesright_box_data", ranNumbr(3,6)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMinAxle12dist_box_data", ranNumbr(10,150)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMaxAxle12dist_box_data", ranNumbr(200,500)+"");
				Thread.sleep(100);
				driver.findElement(By.id("ctl00_ContentZone_RdTrailer_"+ranNumbr(0,2))).click();
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMinHeight_box_data", ranNumbr(10,150)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMaxHeight_box_data", ranNumbr(200,500)+"");
				Thread.sleep(100);
				driver.findElement(By.id("ctl00_ContentZone_RdAlturaCortina_"+ranNumbr(0,2))).click();
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMinWidth_box_data", ranNumbr(10,150)+"");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMaxWidth_box_data", ranNumbr(200,500)+"");
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
				Thread.sleep(5000);
				if (driver.getPageSource().contains("Un mapeo con el índice especificado ya existe")){
					String errorText = driver.findElement(By.id("toast-container")).getText();
					System.out.println("No se pudo crear el Mapeo de DAC debido a: "+errorText);
					fail("No se pudo crear el Mapeo de DAC debido a: "+errorText);
				}

			}catch (Exception e){
				e.printStackTrace();
				fail(e.getMessage());
			}
		
	}
		private static void eliminar_DAC() throws Exception{
			Actions action = new Actions(driver);			
			try{
				action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
				Thread.sleep(1000);
				action.clickAndHold(driver.findElement(By.linkText("Gestión de clases"))).build().perform();
				driver.findElement(By.linkText("Mapeo DAC vs categorías")).click();
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","gestionMapeoDACpage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMapeoDAC\\attachments\\","gestionMapeoDACpage.jpeg");				
					int selRecord;
					String recordDis = driver.findElement(By.id("ctl00_ContentZone_tablePager_LblCounter")).getText();
					if (recordDis.length()>25){
						selRecord = ranNumbr(1,Integer.parseInt(recordDis.substring(25)));						
											
					}else{
						selRecord = ranNumbr(1,Integer.parseInt(recordDis.substring(24)));
					}					
					if (selRecord>21){
						elementClick("ctl00_ContentZone_tablePager_BtnNext");
						if (selRecord>40 && selRecord<61){
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);								
						}
						if (selRecord>60 && selRecord<81){
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);
						}
						if (selRecord >80){
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);
							elementClick("ctl00_ContentZone_tablePager_BtnNext");
							Thread.sleep(300);
						}
							String recSel = String.valueOf(selRecord);  
							int a = Integer.parseInt(recSel.substring(1));
							if (a==1){
								a=2;
							}
							DACNumber = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[3]")).getText(); 
							DACIndice = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[2]")).getText();
							recordFound = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+a+"]/td[1]/input")).getAttribute("id");																																	
							elementClick(recordFound);
							Thread.sleep(1000);								
													
					}else{
						if (selRecord == 1){
							selRecord=2;
						}
						DACNumber = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRecord+"]/td[3]")).getText(); 
						DACIndice = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRecord+"]/td[2]")).getText();
						recordFound = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+selRecord+"]/td[1]/input")).getAttribute("id");																																	
						elementClick(recordFound);							
						Thread.sleep(1000);						
						
					}											
				elementClick(OptionSel);
				Thread.sleep(1000);
				elementClick("popup_ok");
				Thread.sleep(5000);
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