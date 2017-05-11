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
					/*Thread.sleep(2000);
					driver.findElement(By.id("ctl00_ContentZone_BtnCreate")).click(); // Botón crear operador
					Thread.sleep(1500);*/
					//takeScreenShot("operatorCrateScr"+timet+".jpge");
					Thread.sleep(500);
					/*int opId = ranNumbr(1,99999);					
					String opIdnumbr = String.valueOf(opId);
						if (opIdnumbr.length() < 5){
							int opI = 5-opIdnumbr.length();
							char [] opc = new char [opI];
							for (int i = 0; i < opI; i++){
								opc[i] = '0';
								opzero = opzero.concat(String.valueOf(opc[i]));								
							}
							opzero = opzero.concat(String.valueOf(opId));														
						}else{
							opzero = String.valueOf(opId) ;
						}
						Thread.sleep(2000);
						
						
						Thread.sleep(4000);
						System.out.println("El Operador "+opzero+" ha sido creado y entra correctamente a BackOffice");*/
						Thread.sleep(2000);
						accountCreate();
				}catch(Exception e){
					e.printStackTrace();
					fail();
				}
		}		
		public static void accountCreate() throws Exception{
			Thread.sleep(1000);
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
			/*driver.findElement(By.id(opIdField)).sendKeys(opzero);			
			
			
			driver.findElement(By.id(emailField)).sendKeys(nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.es");
			selectDropDownClick(groupOperator);
			driver.findElement(By.id(pwdField)).sendKeys(opzero);
			driver.findElement(By.id(repeatpwdField)).sendKeys(opzero);	
			driver.findElement(By.id("ctl00_ContentZone_ChkSalde")).click();
			driver.findElement(By.id("ctl00_ContentZone_ChkHistorique")).click();
			driver.findElement(By.id(hourNumber)).sendKeys(ranNumbr(1,999)+"");	
			Thread.sleep(500);
			driver.findElement(By.id(submitBtn)).click();
			Thread.sleep(3000);*/
	
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
