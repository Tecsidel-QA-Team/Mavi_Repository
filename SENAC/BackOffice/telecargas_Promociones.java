package BackOffice;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.net.UrlChecker.TimeoutException;

import BackOffice.senacFieldsConfiguration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class telecargas_Promociones extends senacFieldsConfiguration{
		private static String []  dateS = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre", "Octubre", "Noviembre", "Diciembre"};
		private static String [] telP = {"ctl00_ContentZone_Prepay", "ctl00_ContentZone_Postpay"};
		private static String [] promoSel = {"En función de recarga", "En función de tránsitos", "En función del horario"};
		private static Actions action;
		private static int dateMFrom;
		private static int dateMFromR;
		private static int linkSel;
		private static String errorText; 
		private static Calendar calT;
		private static Calendar calF;
		private static SimpleDateFormat sft;
		private static String [] weekDay = {"ctl00_ContentZone_chk_lundi","ctl00_ContentZone_chk_mardi","ctl00_ContentZone_chk_mercredi","ctl00_ContentZone_chk_jeudi","ctl00_ContentZone_chk_vendredi","ctl00_ContentZone_chk_samedi","ctl00_ContentZone_chk_dimanche"};
		
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
			public void senacPromocionesPage() throws Exception{
			 	action = new Actions(driver);
			 	borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\");
				try{
					driver.get(baseUrl);
					takeScreenShot("E:\\Selenium\\","loginpageSenac_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","loginpageSenac.jpg");				
					driver.findElement(By.id(loginField)).sendKeys("00001");
					driver.findElement(By.id(passField)).sendKeys("00001");
					driver.findElement(By.id(loginButton)).click();
					Thread.sleep(1000);
					dateMFrom = ranNumbr(0,12);
					if (dateMFrom>=12){
						dateMFromR = dateMFrom-1;
					}else{
						dateMFromR = dateMFrom;
					}
					takeScreenShot("E:\\Selenium\\","homepageSenac_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","homepageSenac.jpg");
					Thread.sleep(1000);
					action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
					Thread.sleep(1000);
					action.clickAndHold(driver.findElement(By.linkText("Promociones"))).build().perform();
					Thread.sleep(1000);
					linkSel = ranNumbr(0,2);
					driver.findElement(By.linkText(promoSel[linkSel])).click();
					Thread.sleep(2000);
					switch (linkSel){
					case 0:						Thread.sleep(1000);
												enfuncionRecarga();
												break;
					case 1:						enfunciontransitos();
												break;							
					case 2:						enfuncionhorario();
												break;												
					default:					System.out.println("Se ha clicqueado en ninguno");
					}
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("No se puede crear Telecarga Promociones "+ promoSel[linkSel]+ " debido a: "+errorText);
					fail(errorText);
				}			
		}			
			
			public static void enfuncionRecarga() throws Exception{		
					driver.findElement(By.id("ctl00_ContentZone_BtnCreate")).click(); // Botón crear operador
					takeScreenShot("E:\\Selenium\\","promoenFuncionRecarga_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionRecarga.jpg");
					Thread.sleep(1500);
					takeScreenShot("E:\\Selenium\\","promoenFuncionRecargaCreate_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionRecargaCreate.jpg");
					dateSel();
					driver.findElement(By.id("ctl00_ContentZone_txtNom_box_data")).sendKeys("PROMO_"+dateS[dateMFromR]);
					Thread.sleep(1000);
					driver.findElement(By.id("ctl00_ContentZone_dtmfrom_box_date")).clear(); 
					driver.findElement(By.id("ctl00_ContentZone_dtmfrom_box_date")).sendKeys(sft.format(calF.getTime()));
					Thread.sleep(1000);
					driver.findElement(By.id("ctl00_ContentZone_dtmTo_box_date")).clear();
					driver.findElement(By.id("ctl00_ContentZone_dtmTo_box_date")).sendKeys(sft.format(calT.getTime()));
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ContentZone_TXtMsgPromotion_box_data")).sendKeys("Nueva Promoción para "+dateS[dateMFromR]);
					Thread.sleep(500);
					selectDropDown("ctl00_ContentZone_CboType");
					Thread.sleep(1000);
					WebElement lanetype = new Select(driver.findElement(By.id("ctl00_ContentZone_CboType"))).getFirstSelectedOption();
					String laneS = lanetype.getText();
					if (laneS.equals("Vías")){
							selectDropDown("ctl00_ContentZone_Vias");
							Thread.sleep(100);
							selectDropDown("ctl00_ContentZone_Vias");
					}
					Thread.sleep(1500);
					action.click(driver.findElement(By.id("ctl00_ContentZone_BtnCreate"))).build().perform();
					Thread.sleep(1500);
					driver.findElement(By.id("ctl00_ContentZone_TxtBoxImporte_box_data")).clear();
					driver.findElement(By.id("ctl00_ContentZone_TxtBoxImporte_box_data")).sendKeys(ranNumbr(1000,10000)+"");
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ContentZone_TxtboxPorcentaje_box_data")).clear();
					driver.findElement(By.id("ctl00_ContentZone_TxtboxPorcentaje_box_data")).sendKeys(ranNumbr(1,100)+"");
					Thread.sleep(500);
					elementClick("ctl00_ContentZone_BtnApply");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","promoenFuncionRecargaCreateDataFill_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionRecargaCreateDataFill.jpg");
					elementClick("ctl00_ButtonsZone_BtnSubmit");
					Thread.sleep(3000);
					if (isAlertPresent()){
						errorText = driver.switchTo().alert().getText();
						takeScreenShot("E:\\Selenium\\","promoenFuncionRecargaErr_"+timet+".jpg");
						takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionRecargaCreateErr.jpg");
						return;
					}else{
						Thread.sleep(1000);
						elementClick("ctl00_ButtonsZone_BtnDownload");
						Thread.sleep(1000);
						driver.switchTo().alert().accept();
						Thread.sleep(2000);
						takeScreenShot("E:\\Selenium\\","promoenFuncionRecargaSuccess_"+timet+".jpg");
						takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionRecargaSuccess.jpg");
						String successMessage = driver.findElement(By.id("ctl00_LblError")).getText();
						Thread.sleep(3000);
						System.out.println("Telecarga de Promociones "+ promoSel[linkSel]+" ha sido creada y Envio de Telecarga: "+successMessage);
						return;
					}
						
				}
			
			public static void enfunciontransitos() throws Exception{	
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","promoenFuncionTransito_"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionTransito.jpg");
				elementClick("ctl00_ContentZone_BtnCreate");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","promoenFuncionTransitoCreate_"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionTransitoCreate.jpg");
				dateSel();
				driver.findElement(By.id("ctl00_ContentZone_txtNom_box_data")).sendKeys("PROMO_"+dateS[dateMFromR]);
				Thread.sleep(500);
				driver.findElement(By.id("ctl00_ContentZone_dtmfrom_box_date")).clear(); 
				driver.findElement(By.id("ctl00_ContentZone_dtmfrom_box_date")).sendKeys(sft.format(calF.getTime()));
				Thread.sleep(1000);
				driver.findElement(By.id("ctl00_ContentZone_dtmTo_box_date")).clear();
				driver.findElement(By.id("ctl00_ContentZone_dtmTo_box_date")).sendKeys(sft.format(calT.getTime()));
			
				Thread.sleep(1000);
					if (ranNumbr(1,2)<2){
						driver.findElement(By.id(telP[ranNumbr(0,1)])).click();
					}else{
						driver.findElement(By.id(telP[0])).click();
						Thread.sleep(500);
						driver.findElement(By.id(telP[1])).click();						
					}
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ContentZone_TXTNombrePassage_box_data")).sendKeys(ranNumbr(1,999)+"");
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ContentZone_TXTPassageGratuit_box_data")).sendKeys(ranNumbr(1,999)+"");
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ContentZone_TXtMsgPromotionTFI_box_data")).sendKeys("Promoción de "+dateS[dateMFromR]);
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ContentZone_TXtMsgPromotion_box_data")).sendKeys("La Nueva Promoción de "+dateS[dateMFromR]);
					Thread.sleep(500);
					selectDropDown("ctl00_ContentZone_Vias");
					Thread.sleep(500);
					selectDropDown("ctl00_ContentZone_Categoria");
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","promoenFuncionTransitoCreateFillData_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionTransitoCreateFillData.jpg");
					elementClick("ctl00_ButtonsZone_BtnSubmit");
					Thread.sleep(3000);
					if (isAlertPresent()){
						errorText = driver.switchTo().alert().getText();
						takeScreenShot("E:\\Selenium\\","promoenFuncionTransitoErr_"+timet+".jpg");
						takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionTransitoErr.jpg");
						return;
	
					}else{
						Thread.sleep(1000);
						elementClick("ctl00_ButtonsZone_BtnDownload");
						Thread.sleep(1000);
						driver.switchTo().alert().accept();
						Thread.sleep(2000);
						takeScreenShot("E:\\Selenium\\","promoenFuncionTransitoSuccess_"+timet+".jpg");
						takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionTransitoSuccess.jpg");
						String successMessage = driver.findElement(By.id("ctl00_LblError")).getText();
						Thread.sleep(3000);
						System.out.println("Telecarga de Promociones "+ promoSel[linkSel]+" ha sido creada y Envio de Telecarga: "+successMessage);
						Thread.sleep(1000);
						return;
					}
						
				}
			
			public static void enfuncionhorario() throws Exception{
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","promoenFuncionhorario_"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionhorario.jpg");
				elementClick("ctl00_ContentZone_BtnCreate");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","promoenFuncionhorarioCreate_"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionhorarioCreate.jpg");
				dateSel();
				driver.findElement(By.id("ctl00_ContentZone_txtNom_box_data")).sendKeys("PROMO_"+dateS[dateMFromR]);
				Thread.sleep(500);
				driver.findElement(By.id("ctl00_ContentZone_dtmfrom_box_date")).clear(); 
				driver.findElement(By.id("ctl00_ContentZone_dtmfrom_box_date")).sendKeys(sft.format(calF.getTime()));
				Thread.sleep(1000);
				driver.findElement(By.id("ctl00_ContentZone_dtmTo_box_date")).clear();
				driver.findElement(By.id("ctl00_ContentZone_dtmTo_box_date")).sendKeys(sft.format(calT.getTime()));
				Thread.sleep(500);
				driver.findElement(By.id("ctl00_ContentZone_TXtMsgPromotion_box_data")).sendKeys("La Nueva Promoción de "+dateS[dateMFromR]);
				Thread.sleep(500);
				selectDropDown("ctl00_ContentZone_Vias");
				Thread.sleep(500);
				selectDropDown("ctl00_ContentZone_Categoria");				
				Thread.sleep(500);
				int weekS = ranNumbr(1,7);				
				if (weekS < 7){
					for (int i = 0; i < weekS; i++){
						int selW = ranNumbr(0,weekDay.length-1);
						Thread.sleep(500);
						driver.findElement(By.id(weekDay[selW])).click();
						if(!driver.findElement(By.id(weekDay[selW])).isSelected()){
							driver.findElement(By.id(weekDay[selW])).click();
						}					
					}
				}else{
					for (int i = 0; i < weekDay.length; i++){
						driver.findElement(By.id(weekDay[i])).click();
					}
				}
				Thread.sleep(500);
				int HourS = ranNumbr(0,22);
				if (HourS < 10){
					driver.findElement(By.id("ctl00_ContentZone_horainio_box_data")).sendKeys("0"+HourS+"00");
					Thread.sleep(500);
					if (HourS+1<10 ){
						driver.findElement(By.id("ctl00_ContentZone_horafinal_box_data")).sendKeys("0"+HourS+1+"00");
					}else{
						driver.findElement(By.id("ctl00_ContentZone_horafinal_box_data")).sendKeys(HourS+1+"00");
					}
				}else{
					driver.findElement(By.id("ctl00_ContentZone_horainio_box_data")).sendKeys(HourS+"00");
					Thread.sleep(500);
					driver.findElement(By.id("ctl00_ContentZone_horafinal_box_data")).sendKeys(HourS+1+"00");
				}
				Thread.sleep(500);
				driver.findElement(By.id("ctl00_ContentZone_porce_promotion_box_data")).sendKeys(ranNumbr(1,100)+"");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","promoenFuncionhorarioCreateFillData_"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionhorarioCreateFillData.jpg");
				elementClick("ctl00_ButtonsZone_BtnSubmit");
				Thread.sleep(3000);
				if (isAlertPresent()){
					errorText = driver.switchTo().alert().getText();
					takeScreenShot("E:\\Selenium\\","promoenFuncionhorarioErr_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionhorarioErr.jpg");
					return;

				}else{
					Thread.sleep(1000);
					elementClick("ctl00_ButtonsZone_BtnDownload");
					Thread.sleep(1000);
					driver.switchTo().alert().accept();
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","promoenFuncionhorarioSuccess_"+timet+".jpg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\telecargas_Promociones\\attachments\\","promoenFuncionhorarioSuccess.jpg");
					String successMessage = driver.findElement(By.id("ctl00_LblError")).getText();
					Thread.sleep(3000);
					System.out.println("Telecarga de Promociones "+ promoSel[linkSel]+" ha sido creada y Envio de Telecarga: "+successMessage);
					Thread.sleep(1000);
					return;
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
		public static void dateSel() throws Exception{
				calF = Calendar.getInstance();
				calT = Calendar.getInstance();
			 sft = new SimpleDateFormat("dd/MM/yyyy");
			calF.set(ranNumbr(2017,2018), dateMFrom, ranNumbr(1,31));
			calT.set(ranNumbr(2017,2018), ranNumbr(1,12), ranNumbr(1,31));
	
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
