package HostPlazaBackOffice;

import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import R3R5CiralsaSettings.Settingsfields_File;

public class BOHost_accountCreationWithVehicleandPAN extends Settingsfields_File {		
		
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
		public void accountCreationwithVehicleandPANInit() throws Exception {				
				BOHost_accountCreationOnly.accountCreation();
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnValidate_IB_Label");// Editar Cuenta con el botón
				Thread.sleep(1000);
				vehicleCreation();
				Thread.sleep(2000);
				System.out.println("Se ha creado la cuenta: "+accountNumbr.substring(7,16)+" correctamente y con el vehículo creado con la matricula: "+matriNu+ " y el PAN: "+PAN);
				String HMver = BOVersion.substring(1);
				if (HMver.length()>18){
					HMver = BOVersion.substring(17);			
				}else{
					HMver = "<HM is not running>";
				}				
				System.out.println("Se ha probado en la versión del: "+ getVersion("BO","HM"));				
		}

		public static void vehicleCreation() throws Exception {
				elementClick("ctl00_ContentZone_BtnVehicles");
				Thread.sleep(1000);		
				takeScreenShot("E:\\Selenium\\","vehiclePage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\accountCreationVehicle\\attachments\\","vehiclePage.jpg");
				elementClick("ctl00_ContentZone_BtnCreate");
				Thread.sleep(1000);
				int matNum = ranNumbr(5000,9999);
				int matlet = ranNumbr(1,matletT.length()-1);
				int matlet1 = ranNumbr(1,matletT.length()-1);
				int matlet2 = ranNumbr(1,matletT.length())-1;
				matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
				selectDropDownV("ctl00_ContentZone_cmb_vehicle_type");
				Thread.sleep(1000);
				WebElement vehtype = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_vehicle_type"))).getFirstSelectedOption();
				String  vehtypeT = vehtype.getText();
				if (vehtypeT.equals("Ligero")){
					carSel = ranNumbr(0,3);
					carModel = ranNumbr(1,2);
					if (cocheModels[0][carSel].equals("Seat")){
						carModelSel = 0;
					}
					if (cocheModels[0][carSel].equals("Volkswagen")){
						carModelSel = 1;
					}
					if (cocheModels[0][carSel].equals("Ford")){
						carModelSel = 2;
					}
					if (cocheModels[0][carSel].equals("Fiat")){
						carModelSel = 3;
					}
					vehtypeModel = String.valueOf(cocheModels[0][carSel]);
					vehtypeKind = String.valueOf(cocheModels[carModel][carModelSel]);	
				}
				else{
					carSel = ranNumbr(0,1);
					carModel = ranNumbr(1,2);
					if (camionModels[0][carSel].equals("Mercedes-Benz")){
						carModelSel = 0;
					}
					if (camionModels[0][carSel].equals("Scania")){
						carModelSel = 1;
					}
					vehtypeModel = String.valueOf(camionModels[0][carSel]);
					vehtypeKind = String.valueOf(camionModels[carModel][carModelSel]);
				}
				vehicleFieldsfill(matriNu,vehtypeModel,vehtypeKind,colorS[ranNumbr(0,colorS.length-1)]);
				Thread.sleep(1000);
				driver.findElement(By.id("ctl00_ContentZone_txt_comment")).sendKeys("Vehículo creado para la cuenta: "+accountNumbr.substring(7,16));
				Thread.sleep(100);
				selectDropDownV("ctl00_ContentZone_cmb_tipo_titulo");
				Thread.sleep(100);
				if (ranNumbr(0,1)>0){
					elementClick("ctl00_ContentZone_CtrlExemptData_RadioExpDate_1");
					Thread.sleep(100);					
					driver.findElement(By.id("ctl00_ContentZone_CtrlExemptData_BoxExpDate_box_date")).sendKeys(dateSel(2018,2019));
					Thread.sleep(100);
					driver.findElement(By.id("ctl00_ContentZone_CtrlExemptData_BoxExpDateWarn")).clear();
					driver.findElement(By.id("ctl00_ContentZone_CtrlExemptData_BoxExpDateWarn")).sendKeys(ranNumbr(1,15)+"");
				}
				Thread.sleep(100);
				elementClick("ctl00_ContentZone_btnEditPan");
				Thread.sleep(100);				
				driver.findElement(By.id("ctl00_ContentZone_txt_pan")).sendKeys(ranNumbr(1,9000000)+"");
				Thread.sleep(100);			
				takeScreenShot("E:\\Selenium\\","vehicleDataFilledPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\accountCreationVehicle\\attachments\\","vehicleDataFilledPage.jpg");																
				driver.findElement(By.id("ctl00_ButtonsZone_lbl_btnSubmit")).click();
				Thread.sleep(1500);
				String validateTitle = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
				if (validateTitle.equals("Vehículo en cuenta")){
					String validateerror = driver.findElement(By.xpath("//*[@id='toast-container']/div/div")).getText();
					if (validateerror.equals("El Pan ya está asignado")){
						fail("No se puede crear un vehículo con PAN debido a un error: "+validateerror);						
					}
				}	
				WebElement tablResult = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
				List <WebElement> tableRe = tablResult.findElements(By.tagName("tr"));
				for (int i = 0; i <=tableRe.size();i++){
					if (i == tableRe.size()){
						PAN = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[10]")).getText();
					}
				}
				Thread.sleep(100);
				driver.findElement(By.id("ctl00_ButtonsZone_BtnBack_IB_Label")).click();
				Thread.sleep(1500);
				driver.findElement(By.id("ctl00_ButtonsZone_BtnValidate_IB_Label")).click();
				Thread.sleep(2500);
			}
		
		public static void vehicleFieldsfill(String Matricul, String vehtypeM, String vehtypeK, String ColorT) throws Exception{
				Thread.sleep(1000);
				driver.findElement(By.id("ctl00_ContentZone_text_plate_number")).sendKeys(Matricul);
				driver.findElement(By.id("ctl00_ContentZone_text_make")).sendKeys(vehtypeM);
				driver.findElement(By.id("ctl00_ContentZone_text_model")).sendKeys(vehtypeK);
				driver.findElement(By.id("ctl00_ContentZone_text_colour")).sendKeys(ColorT);			
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

