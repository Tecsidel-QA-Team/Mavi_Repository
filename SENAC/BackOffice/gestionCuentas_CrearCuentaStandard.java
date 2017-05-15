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



public class gestionCuentas_CrearCuentaStandard extends senacFieldsConfiguration{
		private static String opzero=""; //numero de operador
		private static String infoCuenta0 = "ctl00_ContentZone_ctrlAccountData_radio_company_0";
		private static String infoCuenta1 = "ctl00_ContentZone_ctrlAccountData_radio_company_1";
		private static String titulofield = "ctl00_ContentZone_ctrlAccountData_cmb_title_cmb_dropdown";
		private static String namef = "ctl00_ContentZone_ctrlAccountData_txt_forname_box_data";
		private static String surnamef ="ctl00_ContentZone_ctrlAccountData_txt_surname_box_data";
		private static String addressf = "ctl00_ContentZone_ctrlAccountData_txt_address_box_data";
		private static String cpf = "ctl00_ContentZone_ctrlAccountData_txt_postcode_box_data";
		private static String townf = "ctl00_ContentZone_ctrlAccountData_txt_town_box_data";
		private static String countryf = "ctl00_ContentZone_ctrlAccountData_txt_country_box_data";
		private static String emailf ="ctl00_ContentZone_ctrlAccountData_txt_email_box_data";
		private static String phoneCel = "ctl00_ContentZone_ctrlAccountData_txt_mobile_box_data";
		private static String workPhone = "ctl00_ContentZone_ctrlAccountData_txt_daytimephone_box_data";
		private static String perPhone = "ctl00_ContentZone_ctrlAccountData_txt_homephone_box_data";
		private static String faxPhone = "ctl00_ContentZone_ctrlAccountData_txt_fax_box_data";
		private static String companyf = "ctl00_ContentZone_ctrlAccountData_txt_company_box_data";
		private static String contactf = "ctl00_ContentZone_ctrlAccountData_txt_contact_box_data";
		private static int carModelSel;
		public static String confirmationMessage;
		private static boolean errorTagAssignment = false;
		private static String tagIdNmbr;
		private static String [] colorS = new String[]{"Blanco", "Negro", "Azul", "Rojo", "Verde", "Amarillo"};
		private static String matletT = "TRWAGMYFPDXBNJZSQVHLCKE";
		private static String accountNumbrT; 
		private static int carSel;
		private static int carModel;
		private static String matriNu;
		private static String vehtypeModel;
		private static String vehtypeKind;
		private static String [][] cocheModels = {{"Seat","Volkswagen","Ford","Fiat"},{"Ibiza","Polo","Fiesta","Punto"},{"León","Passat","Focus","Stilo"}};
		private static String [][] camionModels = {{"Mercedes-Benz","Scania"},{"Axor","R500"},{"Actros","P400"}};
		private static String [][] furgonetaModels = {{"Mercedes-Benz","Fiat"},{"Vito","Scudo"},{"Citan","Ducato"}};
		private static String [][] cicloModels = {{"Yamaha","Honda"},{"XT1200Z","Forza 300"},{"T-MAX SX","X-ADV"}};
		private static String [][] autoBusModels = {{"DAIMLER-BENZ","VOLVO"},{"512-CDI","FM-12380"},{"DB 605","FM 300"}};
		
		
		
		
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
			public void senacGestionCuentasPage() throws Exception{
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
					action.clickAndHold(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
					Thread.sleep(1000);
					action.clickAndHold(driver.findElement(By.linkText("Crear cuenta"))).build().perform();
					Thread.sleep(1000);
					driver.findElement(By.linkText("Standard")).click();
					//takeScreenShot("operatorCrateScr"+timet+".jpge");
					Thread.sleep(1000);
					String accountNmbr = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
					accountNumbrT = accountNmbr.substring(7, 16);
						Thread.sleep(2000);
						accountCreate();
						Thread.sleep(1000);
						elementClick("ctl00_ButtonsZone_BtnSave");
						Thread.sleep(3000);
						elementClick("ctl00_ButtonsZone_BtnExecute");
						Thread.sleep(1000);
						elementClick("ctl00_ButtonsZone_BtnValidate");
						Thread.sleep(1000);
						elementClick("ctl00_ContentZone_BtnVehicles");
						Thread.sleep(1000);
						elementClick("ctl00_ContentZone_BtnCreate");
						Thread.sleep(1000);
						crearVehiculo();
						Thread.sleep(2000);
						vehicleFieldsfill(matriNu,vehtypeModel,vehtypeKind,colorS[ranNumbr(0,colorS.length-1)]);
						Thread.sleep(3000);
						driver.findElement(By.id("ctl00_ButtonsZone_BtnSubmit")).click();
						Thread.sleep(1500);
						driver.findElement(By.id("ctl00_ButtonsZone_BtnBack")).click();
						Thread.sleep(1500);
						driver.findElement(By.id("ctl00_ButtonsZone_BtnValidate")).click();
						Thread.sleep(2500);
						tagAssignment();
						if (errorTagAssignment){
							System.out.println("ERROR AL ASIGNAR TAG: "+confirmationMessage);
							fail("Tag Invalido: No se puede asignar un Tag al Vehiculo");
							return;
						}
						System.out.println("Se ha creado la cuenta: "+accountNumbrT+" con un Vehiculo con la matricula "+matriNu+" y el tag asignado No.: "+ tagIdNmbr);
						Thread.sleep(3000);
				}catch(Exception e){
					e.printStackTrace();
					fail();
				}
		}		
		
		public static void accountCreate() throws Exception{
			Thread.sleep(1000);
			JavascriptExecutor js=(JavascriptExecutor) driver;
			int selOpt = ranNumbr(0,1);
			int selOp = ranNumbr(0,8);
			int selOp2 = ranNumbr(0,8);
			if (selOpt==0){
				driver.findElement(By.id(infoCuenta0)).click();
				Thread.sleep(1000);
				new Select (driver.findElement(By.id(titulofield))).selectByVisibleText(sexSelc[selOp]);
				driver.findElement(By.id(namef)).sendKeys(nameOp[selOp]);
				driver.findElement(By.id(surnamef)).sendKeys(lastNameOp[selOp]);
				driver.findElement(By.id(addressf)).sendKeys(addressTec[selOp]);
				driver.findElement(By.id(cpf)).sendKeys(cpAdress[selOp]);
				driver.findElement(By.id(townf)).sendKeys(townC[selOp]);
				driver.findElement(By.id(countryf)).sendKeys("España");
				driver.findElement(By.id(emailf)).sendKeys(nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.es");
				driver.findElement(By.id(phoneCel)).sendKeys(ranNumbr(600000000,699999999)+"");
				driver.findElement(By.id(workPhone)).sendKeys(workPhone1[selOp]);
				driver.findElement(By.id(perPhone)).sendKeys(ranNumbr(900000000,999999999)+"");
				driver.findElement(By.id(faxPhone)).sendKeys(workPhone1[selOp]);				
				Thread.sleep(4000);
				
			}else{
				driver.findElement(By.id(infoCuenta1)).click();				
				Thread.sleep(1000);
				driver.findElement(By.id(companyf)).sendKeys("Tecsidel, S.A");
				driver.findElement(By.id(contactf)).sendKeys(nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
				driver.findElement(By.id(addressf)).sendKeys(addressTec[2]);
				driver.findElement(By.id(cpf)).sendKeys(cpAdress[2]);
				driver.findElement(By.id(townf)).sendKeys(townC[2]);				
				driver.findElement(By.id(emailf)).sendKeys("info@tecsidel.es");
				driver.findElement(By.id(phoneCel)).sendKeys(ranNumbr(600000000,699999999)+"");
				driver.findElement(By.id(workPhone)).sendKeys(workPhone1[2]);
				driver.findElement(By.id(perPhone)).sendKeys(ranNumbr(900000000,999999999)+"");
				driver.findElement(By.id(faxPhone)).sendKeys(workPhone1[selOp]);
				Thread.sleep(000);								
			}
			selOpt = ranNumbr(0,1);
			if (selOpt==0){
				driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_rd_discount_0")).click();
			}else{
				driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_rd_discount_1")).click();
				selOpt = ranNumbr(0,1);
					if (selOpt==0){
						driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_rd_typeOfDiscount_0")).click();
						selOpt = ranNumbr(0,1);
							if (selOpt == 0){
								driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_rd_for_0")).click();
							}else{
								driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_rd_for_1")).click();
							}
					}else{
						driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_rd_typeOfDiscount_1")).click();
					}
			}
			Thread.sleep(2000);
			selectDropDown("ctl00_ContentZone_ctrlAccountStandard_cmb_paymentMode_cmb_dropdown");		
			Thread.sleep(3000);
			WebElement PayMode = new Select(driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_cmb_paymentMode_cmb_dropdown"))).getFirstSelectedOption();
			String PayModeT = PayMode.getText();		
				if (PayModeT.equals("Prepago")){
					selOpt = ranNumbr(0,1);
					if (selOpt > 0){
						Thread.sleep(1000);
						elementClick("ctl00_ContentZone_ctrlAccountStandard_chk_show_low_in_lane");
					}
					Thread.sleep(1000);
					selectDropDown("ctl00_ContentZone_ctrlAccountStandard_cmb_paymentMethod_cmb_dropdown");
						Thread.sleep(1500);
						WebElement PayMethod = new Select(driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_cmb_paymentMethod_cmb_dropdown"))).getFirstSelectedOption();
						String PayMetthodT = PayMethod.getText();
							if (PayMetthodT.equals("preautorizado")){
								Thread.sleep(1000);								
								 js.executeScript("document.getElementById('ctl00_ContentZone_ctrlAccountStandard_txt_topup_txt_formated').setAttribute('value',"+ ranNumbr(1000,200000)+")");
								 js.executeScript("document.getElementById('ctl00_ContentZone_ctrlAccountStandard_txt_minimum_box_data').click();");				
								Thread.sleep(1000);
								selOpt = ranNumbr(0,1);
								if (selOpt > 0){
									elementClick("ctl00_ContentZone_ctrlAccountStandard_chk_fixed");
								}else{
									selectDropDown("ctl00_ContentZone_ctrlAccountStandard_cmb_topupDay_cmb_dropdown");
								}
								Thread.sleep(1000);
								driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_txt_bankAccount_box_data")).sendKeys("ES0"+ranNumbr(10,200)+"-"+ranNumbr(1000,3000)+"-"+ranNumbr(100,200)+"-"+ranNumbr(1000,5000)+"-"+ranNumbr(50000,90000));
								Thread.sleep(1000);
								driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_txt_holderName_box_data")).sendKeys(nameOp[selOp]+" "+lastNameOp[selOp]);
							}
							Thread.sleep(5000);
							
					} if(PayModeT.equals("Postpago")){
						selectDropDown("ctl00_ContentZone_ctrlAccountStandard_cmb_paymentMethod_cmb_dropdown");
						Thread.sleep(1500);						
						WebElement PayMethod = new Select(driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_cmb_paymentMethod_cmb_dropdown"))).getFirstSelectedOption();
						String PayMetthodT = PayMethod.getText();						
						if (PayMetthodT.equals("preautorizado")){
							driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_txt_bankAccount_box_data")).sendKeys("ES0"+ranNumbr(10,200)+"-"+ranNumbr(1000,3000)+"-"+ranNumbr(100,200)+"-"+ranNumbr(1000,5000)+"-"+ranNumbr(50000,90000));
							Thread.sleep(1000);
							driver.findElement(By.id("ctl00_ContentZone_ctrlAccountStandard_txt_holderName_box_data")).sendKeys(nameOp[selOp]+" "+lastNameOp[selOp]);
							Thread.sleep(5000);
						}
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountNotes_check_itemised_billing"); //factura detallada click
					}
					Thread.sleep(1000);
					selectDropDown("ctl00_ContentZone_ctrlAccountNotes_combo_statement_date");
					Thread.sleep(1000);
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountNotes_check_statement_email_notice"); //enviar por email click
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountNotes_check_statement_post_notice");//enviar por correo click
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountNotes_radio_notification_1");//enviar notificación por
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountNotes_chk_receive_info");//Recibir info click
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountNotes_chk_receive_ads");//Rec. Publi. click
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountNotes_check_suspension_state");//Suspendida click
					}
					Thread.sleep(1000);
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountNotes_chk_internet_access");//Habilitada click
					}
					Thread.sleep(5000);
		}
		public static void crearVehiculo() throws Exception{
			Thread.sleep(2000);
			int matNum = ranNumbr(5000,9999);
			int matlet = ranNumbr(1,matletT.length());
			int matlet1 = ranNumbr(1,matletT.length());
			int matlet2 = ranNumbr(1,matletT.length());
			matriNu = String.valueOf(matNum+matletT.substring(matlet, matlet+1)+matletT.substring(matlet1, matlet1+1)+matletT.substring(matlet2, matlet2+1));
			selectDropDown("ctl00_ContentZone_cmb_vehicle_type");
			Thread.sleep(1000);
			WebElement vehtype = new Select(driver.findElement(By.id("ctl00_ContentZone_cmb_vehicle_type"))).getFirstSelectedOption();
			String  vehtypeT = vehtype.getText();
			if (vehtypeT.equals("Coche")){
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
			if (vehtypeT.equals("Ciclomotor")){
				carSel = ranNumbr(0,1);
				carModel = ranNumbr(1,2);
					if (cicloModels[0][carSel].equals("Yamaha")){
						carModelSel = 0;
					}
					if (cicloModels[0][carSel].equals("Honda")){
						carModelSel = 1;
					}			
					vehtypeModel = String.valueOf(cicloModels[0][carSel]);
					vehtypeKind = String.valueOf(cicloModels[carModel][carModelSel]);
			}
			if (vehtypeT.equals("Autobús")){
				carSel = ranNumbr(0,1);
				carModel = ranNumbr(1,2);
					if (autoBusModels[0][carSel].equals("DAIMLER-BENZ")){
						carModelSel = 0;
					}
					if (autoBusModels[0][carSel].equals("VOLVO")){
						carModelSel = 1;
					}
					vehtypeModel = String.valueOf(autoBusModels[0][carSel]);
					vehtypeKind = String.valueOf(autoBusModels[carModel][carModelSel]);
			}
			if (vehtypeT.equals("Camión")){
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
			if (vehtypeT.equals("Furgoneta")){
				carSel = ranNumbr(0,1);
				carModel = ranNumbr(1,2);
					if (furgonetaModels[0][carSel].equals("Mercedes-Benz")){
						carModelSel = 0;
					}
					if (furgonetaModels[0][carSel].equals("Fiat")){
						carModelSel = 1;
					}
					vehtypeModel = String.valueOf(furgonetaModels[0][carSel]);
					vehtypeKind = String.valueOf(furgonetaModels[carModel][carModelSel]);
			}
			
		}
		public static void vehicleFieldsfill(String Matricul, String vehtypeM, String vehtypeK, String ColorT) throws Exception{
			Thread.sleep(1000);
			driver.findElement(By.id("ctl00_ContentZone_text_plate_number")).sendKeys(Matricul);
			driver.findElement(By.id("ctl00_ContentZone_text_make")).sendKeys(vehtypeM);
			driver.findElement(By.id("ctl00_ContentZone_text_model")).sendKeys(vehtypeK);
			driver.findElement(By.id("ctl00_ContentZone_text_colour")).sendKeys(ColorT);			
		}
		public static void tagAssignment() throws Exception{
			Thread.sleep(1000);
			driver.findElement(By.id("ctl00_ContentZone_BtnTags")).click();
			Thread.sleep(500);
			driver.findElement(By.id("ctl00_ContentZone_chk_0")).click();
			Thread.sleep(500);
			driver.findElement(By.id("ctl00_ContentZone_btn_tag_assignment")).click();
			Thread.sleep(500);
			driver.findElement(By.id("ctl00_ContentZone_btn_tag_assignment")).click();
			if (ranNumbr(0,1)>0){
				driver.findElement(By.id("ctl00_ContentZone_radio_dist_how_0")).click();				
			}
			Thread.sleep(500);
			driver.findElement(By.id("ctl00_ContentZone_txt_pan_tag")).sendKeys(ranNumbr(1,99999)+"");
			Thread.sleep(500);
			driver.findElement(By.id("ctl00_ContentZone_btn_init_tag")).click();
			Thread.sleep(500);
			confirmationMessage = driver.findElement(By.id("ctl00_ContentZone_lbl_information")).getText();
				if (confirmationMessage.contains("ya tiene un tag asignado") || confirmationMessage.contains("Este tag no está operativo") || confirmationMessage.contains("Este tag ya está asignado al vehículo")){
					errorTagAssignment = true;
				}else{
					tagIdNmbr = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_m_table_vehicles']/tbody/tr[2]/td[6]")).getText();
				}
			Thread.sleep(1000);
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
