package HostPlazaBackOffice;

import static org.junit.Assert.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
//import org.openqa.selenium.support.ui.Select;
import R3R5CiralsaSettings.Settingsfields_File;

public class BOHost_accountCreationOnly extends Settingsfields_File {
	
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
    				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}

			@Test
			public void accountCreationInit() throws Exception {		
				accountCreation();
				Thread.sleep(1000);
				System.out.println("Se ha creado la cuenta: "+accountNumbr.substring(7,16)+" correctamente");
				System.out.println("Se ha probado en la versión del Host BO: " + getVersion("BO")+" y Host Manager: "+getVersion("HM"));				
			}

		public static void accountCreation() throws Exception {
			Actions action = new Actions(driver);
			borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_accountCreationAlone\\attachments\\");
			try{
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_accountCreationAlone\\attachments\\","loginBOPage.jpg");
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_accountCreationAlone\\attachments\\","homeBOHostPage.jpg");
				BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
				Thread.sleep(2000);					
				action.clickAndHold(driver.findElement(By.linkText("Gestión de cuentas"))).build().perform();
				Thread.sleep(1000);
				action.clickAndHold(driver.findElement(By.linkText("Crear cuenta"))).build().perform();
				Thread.sleep(500);
				int accountCreatelink = ranNumbr(0,1);
				if (accountCreatelink==0){
					driver.findElement(By.partialLinkText(accountLink[0])).click();;
				}else{
					driver.findElement(By.partialLinkText(accountLink[1])).click();
				}
				accountNumbr = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();				
				switch (accountCreatelink){
				case 0:						postPagoAccount();
											break;
				case 1:						excentaAccount();
											break;
				}
				
			}catch (Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
				fail(e.getMessage());
			}
		}
		
		public static void excentaAccount() throws Exception{
			try{
				int selOp = ranNumbr(0,8);
				int selOp2 = ranNumbr(0,8);
				int accountSel = ranNumbr(0,1);				
				if (accountSel == 0){
					SendKeys("ctl00_ContentZone_ctrlAccountData_txt_ID_box_data",dniLetra("DNI",ranNumbr(10000000,50000000)));
					Thread.sleep(100);
					new Select (driver.findElement(By.id(titulofield))).selectByVisibleText(sexSelc[selOp]);
					Thread.sleep(100);
					SendKeys(namef,nameOp[selOp]);
					Thread.sleep(100);
					SendKeys(surnamef,lastNameOp[selOp]);
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_ctrlAccountData_txt_surname2_box_data",lastNameOp2[selOp]);
					Thread.sleep(100);
					SendKeys(addressf,addressTec[selOp]);
					Thread.sleep(100);										
					SendKeys(emailf,nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.es");
					Thread.sleep(100);
					SendKeys(phoneCel,ranNumbr(600000000,699999999)+"");
					Thread.sleep(100);
					SendKeys(workPhone,workPhone1[selOp]);
					Thread.sleep(100);
					SendKeys(perPhone,ranNumbr(900000000,999999999)+"");
					Thread.sleep(100);
					SendKeys(faxPhone,workPhone1[selOp]);	
					Thread.sleep(100);
					selectDropDown("ctl00_ContentZone_ctrlAccountExempt_combo_exempts_cmb_dropdown");
					Thread.sleep(100);
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_expiry_1");						
						SendKeys("ctl00_ContentZone_ctrlAccountExempt_cal_date_expiry_box_date",dateSel(2018,2019));
						Thread.sleep(100);
						SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_days_warning", ranNumbr(10,15)+"");
						Thread.sleep(100);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_trips_1");
						SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_max_trips",ranNumbr(1,10)+"");
						Thread.sleep(100);
						SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_trips_warning",ranNumbr(1,6)+"");
						Thread.sleep(100);
					}
					
					
				}else{
					elementClick("ctl00_ContentZone_ctrlAccountData_radio_company_1");
					driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_txt_ID_box_data")).sendKeys(dniLetra("CIF",ranNumbr(10000000,19999999)));
					selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_companyType_cmb_dropdown");
					Thread.sleep(100);
					SendKeys(companyf,"Tecsidel, S.A");
					Thread.sleep(100);
					SendKeys(contactf,nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
					Thread.sleep(100);
					SendKeys(addressf,addressTec[2]);
					Thread.sleep(100);						
					SendKeys(emailf,"info@tecsidel.es");
					Thread.sleep(100);
					SendKeys(phoneCel,ranNumbr(600000000,699999999)+"");
					Thread.sleep(100);
					SendKeys(workPhone,workPhone1[2]);
					Thread.sleep(100);
					SendKeys(perPhone,ranNumbr(900000000,999999999)+"");
					Thread.sleep(100);
					SendKeys(faxPhone,workPhone1[selOp]);					
					Thread.sleep(100);
					selectDropDown("ctl00_ContentZone_ctrlAccountExempt_combo_exempts_cmb_dropdown");
					Thread.sleep(100);
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_expiry_1");						
						SendKeys("ctl00_ContentZone_ctrlAccountExempt_cal_date_expiry_box_date",dateSel(2018,2019));
						Thread.sleep(100);
						SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_days_warning",ranNumbr(10,15)+"");
						Thread.sleep(100);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountExempt_radio_trips_1");
						driver.findElement(By.id("ctl00_ContentZone_ctrlAccountExempt_txt_max_trips")).sendKeys(ranNumbr(1,10)+"");
						Thread.sleep(100);
						SendKeys("ctl00_ContentZone_ctrlAccountExempt_txt_trips_warning",ranNumbr(1,6)+"");
						Thread.sleep(100);
					}
					
				}
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnSave_IB_Label");
				Thread.sleep(2000);
				

			}catch (Exception e){
				fail(e.getMessage());
			}
		}

		public static void postPagoAccount() throws Exception{
			try{
				int selOp = ranNumbr(0,8);
				int selOp2 = ranNumbr(0,8);
				int accountSel = ranNumbr(0,1);					
				if (accountSel == 0){
					SendKeys("ctl00_ContentZone_ctrlAccountData_txt_ID_box_data",dniLetra("DNI",ranNumbr(10000000,50000000)));
					Thread.sleep(100);
					new Select (driver.findElement(By.id(titulofield))).selectByVisibleText(sexSelc[selOp]);
					Thread.sleep(100);
					SendKeys(namef,nameOp[selOp]);
					Thread.sleep(100);
					SendKeys(surnamef,lastNameOp[selOp]);
					Thread.sleep(100);
					SendKeys("ctl00_ContentZone_ctrlAccountData_txt_surname2_box_data",lastNameOp2[selOp]);
					Thread.sleep(100);
					SendKeys(addressf,addressTec[selOp]);
					Thread.sleep(100);										
					SendKeys(emailf,nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.es");
					Thread.sleep(100);
					SendKeys(phoneCel,ranNumbr(600000000,699999999)+"");
					Thread.sleep(100);
					SendKeys(workPhone,workPhone1[selOp]);
					Thread.sleep(100);
					SendKeys(perPhone,ranNumbr(900000000,999999999)+"");
					Thread.sleep(100);
					SendKeys(faxPhone,workPhone1[selOp]);	
					Thread.sleep(100);					
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountData_chk_dtEnd");						
						SendKeys("ctl00_ContentZone_ctrlAccountData_dt_end_box_date",dateSel(2018,2019));
						Thread.sleep(100);
						SendKeys("ctl00_ContentZone_ctrlAccountData_txt_warningThreshold_box_data", ranNumbr(10,15)+"");
						Thread.sleep(100);
					}
					selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_discounts_cmb_dropdown");
				}else{
					elementClick("ctl00_ContentZone_ctrlAccountData_radio_company_1");
					driver.findElement(By.id("ctl00_ContentZone_ctrlAccountData_txt_ID_box_data")).sendKeys(dniLetra("CIF",ranNumbr(10000000,19999999)));
					selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_companyType_cmb_dropdown");
					Thread.sleep(100);
					SendKeys(companyf,"Tecsidel, S.A");
					Thread.sleep(100);
					SendKeys(contactf,nameOp[selOp]+" "+lastNameOp[selOp]+", "+nameOp[selOp2]+" "+lastNameOp[selOp2]);
					Thread.sleep(100);
					SendKeys(addressf,addressTec[2]);
					Thread.sleep(100);						
					SendKeys(emailf,"info@tecsidel.es");
					Thread.sleep(100);
					SendKeys(phoneCel,ranNumbr(600000000,699999999)+"");
					Thread.sleep(100);
					SendKeys(workPhone,workPhone1[2]);
					Thread.sleep(100);
					SendKeys(perPhone,ranNumbr(900000000,999999999)+"");
					Thread.sleep(100);
					SendKeys(faxPhone,workPhone1[selOp]);					
					Thread.sleep(100);										
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ctrlAccountData_chk_dtEnd");						
						SendKeys("ctl00_ContentZone_ctrlAccountData_dt_end_box_date",dateSel(2018,2019));
						Thread.sleep(100);						
						SendKeys("ctl00_ContentZone_ctrlAccountData_txt_warningThreshold_box_data",ranNumbr(10,15)+"");
						Thread.sleep(100);
					}
					selectDropDown("ctl00_ContentZone_ctrlAccountData_cmb_discounts_cmb_dropdown");
					
				}
				Thread.sleep(1000);
				elementClick("ctl00_ButtonsZone_BtnSave_IB_Label");
				Thread.sleep(2000);
				

			}catch (Exception e){
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
