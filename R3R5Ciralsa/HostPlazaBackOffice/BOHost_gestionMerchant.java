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

public class BOHost_gestionMerchant extends Settingsfields_File {		
		private static String merchantName;
		private static int merchantCode;
		private static String merchantCodeS;
		private static String merchantName2;
		private static boolean merchantChanged = false;
		private static boolean merchantCodedChanged = false;
		private static boolean bothfields = false;
		private static int opSel; 		
		private static String [] optionSel = {"Crear","Modificar","Eliminar"};
		private static String [] merchantRange = {"Crear", "Modificar", "Eliminar"};
		private static int opSel1; 
		
		
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
		public void gestionMarchantInit() throws Exception {
			Actions action = new Actions(driver);
			borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\");
			try{
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","loginBOPage.jpeg");
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","homeBOHostPage.jpeg");
				BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
				Thread.sleep(2000);					
				String HMver = BOVersion.substring(1);
				if (HMver.length()>18){
					HMver = BOVersion.substring(17);
					BOVersion=BOVersion.substring(0,16);
				}else{
					HMver = "<HM is not running>";
					BOVersion=BOVersion.substring(0,16);
				}
				action.clickAndHold(driver.findElement(By.linkText("Gestión de pagos"))).build().perform();
				Thread.sleep(1000);
				driver.findElement(By.linkText("Gestión de merchants")).click();
				Thread.sleep(500);
				takeScreenShot("E:\\Selenium\\","gestioinmerchantspage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","gestioinmerchantspage.jpeg");
				opSel = ranNumbr(0,2);
				switch(optionSel[opSel]){
					case "Crear":			crear_Merchant();
											driver.findElement(By.cssSelector("html body div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix div.ui-dialog-buttonset button.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only")).click();									
											Thread.sleep(2000);
											System.out.println("Se ha creado el Merchant: " +merchantName+" con el Código: "+merchantCode+" correctamente");
											break;
					case "Modificar":		edit_deleteMerchant();											
											driver.findElement(By.cssSelector("html body div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix div.ui-dialog-buttonset button.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only")).click();									
											Thread.sleep(2000);
											if (bothfields){
												System.out.println("Se modificado el Nombre del Merchant: "+merchantName2+" con otro nombre: "+merchantName+" y también su código "+merchantCodeS+" por el código "+merchantCode+" y todos los campos relevantes");
											}else{
												if (merchantChanged){
													System.out.println("Se modificado el Nombre del Merchant: "+merchantName2+" con otro nombre: "+merchantName+" pero tiene el mismo código "+merchantCodeS+" y todos los campos relevantes");
												}
												if (merchantCodedChanged){
													System.out.println("No se ha modificado el Nombre del Merchant: "+merchantName2+" con otro nombre, pero si se ha cambiado su código "+merchantCodeS+ " por otro código; "+merchantCodeS+" y todos los campos relevantes");
												}
												if (!merchantChanged && !merchantCodedChanged){
													System.out.println("No se ha modificado ni el Nombre del Merchant: "+merchantName2+" ni tampoco su código: "+merchantCodeS+ ", pero si todos los campos relevantes");
												}
											}
											
											break;
					case "Eliminar":		edit_deleteMerchant();
											System.out.println("Se ha borrado el Merchant: "+merchantName2+ " con el Código:" +merchantCodeS+" correctamente");
											break;
				}				
				System.out.println("Se ha probado en la versión del BO Host: " + BOVersion +" y Host Manager: "+HMver);
				
			}catch (Exception e){
				e.getStackTrace();
				fail(e.getMessage());
			}
		}
		
		public static void crear_Merchant() throws Exception{
				Thread.sleep(1000);
				try{
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(500);
					takeScreenShot("E:\\Selenium\\","createMerchantspage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","createMerchantspage.jpeg");
					merchantName = "MERCH_"+nameOp[ranNumbr(0,nameOp.length-1)];
					merchantCode = ranNumbr(1,99999999);
					driver.findElement(By.id("merchantNameDlg")).sendKeys(merchantName);
					Thread.sleep(100);
					driver.findElement(By.id("merchantCodeDlg")).sendKeys(merchantCode+"");
					Thread.sleep(100);
					new Select (driver.findElement(By.xpath("//*[@id='merchantDialog']/table/tbody/tr[1]/td[2]/div/select"))).selectByIndex(ranNumbr(1,2));
					Thread.sleep(100);
					if (ranNumbr(0,1)>0){
						elementClick("chkmerchantDlgAdmitCards");
					}
					if (ranNumbr(0,1)>0){
						elementClick("chkmerchantDlgOBE");
					}
					takeScreenShot("E:\\Selenium\\","createMerchantspagedataFilled"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","createMerchantspagedataFilled.jpeg");
					Thread.sleep(1000);
					driver.findElement(By.xpath("//div[1]/div[3]/div/button[1]")).click();
					Thread.sleep(2000);
					String titlePage = driver.findElement(By.id("ctl00_SectionZone_LblTitle")).getText();
					if (titlePage.equals("Merchants")){
						if (driver.getPageSource().contains("Guardado terminado correctamente")){
						driver.findElement(By.xpath("//*[@id='toast-container']/div/button")).click();
						Thread.sleep(2000);
						}					
					}
					modifyMerchant();
					Thread.sleep(2000);
					
								
				
			}catch (Exception e){
				e.getStackTrace();
				fail(e.getMessage());
			}
		}
		
		public static void edit_deleteMerchant() throws Exception{
			Thread.sleep(1000);
			String recordsNumber = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span")).getText();
			if (recordsNumber.length()>20){
				recordsNumber = recordsNumber.substring(20);
			}else{
				recordsNumber = recordsNumber.substring(19);
			}			
			int opSelRecord = ranNumbr(0,Integer.parseInt(recordsNumber)-1);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			
			for (int i = 0;i<Integer.parseInt(recordsNumber);i++){					
				if (driver.findElements(By.id("merchantTable_radio_"+opSelRecord)).size()>0){				
					elementClick("merchantTable_radio_"+opSelRecord);
					merchantName2 = driver.findElement(By.xpath("//*[@id='merchantTable_row_"+opSelRecord+"']/td[2]")).getText();
					merchantCodeS = driver.findElement(By.xpath("//*[@id='merchantTable_row_"+opSelRecord+"']/td[3]")).getText();
					Thread.sleep(2000);
					break;				
				}
				if (!driver.findElement(By.cssSelector("#content > div.ng-scope > div > div > div > div.pageMainDiv > div:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > table > tbody > tr > td:nth-child(4) > input")).isEnabled()){
					System.out.println("No se ha encontrado ningún Merchant disponible");
					fail("No se ha encontrado ningún Merchant disponible");
					return;
				}else{
					driver.findElement(By.cssSelector("#content > div.ng-scope > div > div > div > div.pageMainDiv > div:nth-child(2) > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > table > tbody > tr > td:nth-child(4) > input")).click();											
					Thread.sleep(1000);
					
				}
			}
			if (optionSel[opSel].equals("Eliminar")){
				Thread.sleep(1000);
				elementClick("ctl00_ContentZone_BtnDelete");
				Thread.sleep(1000);
				elementClick("popup_ok");
				Thread.sleep(3000);
				return;
			}
			if (optionSel[opSel].equals("Modificar")){
				Thread.sleep(1000);
				elementClick("ctl00_ContentZone_BtnModify");
				Thread.sleep(1000);
				modifyMerchant();
				Thread.sleep(1000);				
			}
		}
		
		public static void modifyMerchant() throws Exception{
		try{
			Thread.sleep(1000);
			//Ya en la pantalla de modificar Merchant
			if (optionSel[opSel].equals("Modificar")){
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","modifymerchantPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","modifymerchantPage.jpeg");
				merchantName = "MERCH_"+nameOp[ranNumbr(0,nameOp.length-1)];
				merchantCode = ranNumbr(1,99999999);
				if (ranNumbr(0,1)>0){
					SendKeys("merchantNameDlg",merchantName);
					merchantChanged = true;
					Thread.sleep(1000);
				}
				if (ranNumbr(0,1)>0){
					SendKeys("merchantCodeDlg",merchantCode+"");
					merchantCodedChanged = true;
					Thread.sleep(1000);
				}
				if (merchantCodedChanged && merchantChanged){
					bothfields = true;
					Thread.sleep(1000);
				}
				selectDropDownXPath("//*[@id='merchantDialog']/table/tbody/tr[1]/td[2]/div[1]/select");
				Thread.sleep(200);
				selectDropDownXPath("//*[@id='merchantDialog']/table/tbody/tr[1]/td[2]/div[2]/select");
				Thread.sleep(200);
				if (ranNumbr(0,1)>0){
					elementClick("chkmerchantDlgAdmitCards");
					Thread.sleep(200);
				}
				if (ranNumbr(0,1)>0){
					elementClick("chkmerchantDlgOBE");
					Thread.sleep(200);
				}
			}else{
				//En la pantalla de Buscar Merchant
				driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/table/tbody/tr/td[1]/div[1]/input")).sendKeys(merchantName+"");
				Thread.sleep(100);
				driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/table/tbody/tr/td[1]/div[2]/input")).sendKeys(merchantCode+"");
				Thread.sleep(100);
				elementClick("ctl00_ButtonsZone_BtnSearch");
				Thread.sleep(1000);
				elementClick("merchantTable_radio_0");			
				Thread.sleep(100);
				elementClick("ctl00_ContentZone_BtnModify");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","modifymerchantPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","modifymerchantPage.jpeg");
			}						
			driver.findElement(By.xpath("//*[@class='ng-binding ui-accordion-header ui-state-default ui-corner-all ui-accordion-icons']")).click();
			Thread.sleep(3000);
			String RecordsNumbr = driver.findElement(By.xpath("//*[@id='rangesZoneTable']/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span")).getText();
				if (RecordsNumbr.length()>20){
					RecordsNumbr = RecordsNumbr.substring(20);
				}else{
					RecordsNumbr = RecordsNumbr.substring(19);
				}
				if (Integer.parseInt(RecordsNumbr)==0){
					opSel1 =0;
				}else{
					opSel1= ranNumbr(0,2);
				}							
			if (optionSel[opSel].equals("Crear") || merchantRange[opSel1].equals("Crear") || Integer.parseInt(RecordsNumbr)==0){
				driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_td1']/div/input[1]")).click();			
				Thread.sleep(8000);
			}
			if (Integer.parseInt(RecordsNumbr)>0){
				if (merchantRange[opSel1].equals("Modificar") || merchantRange[opSel1].equals("Eliminar")){
					int opSelRecord = ranNumbr(0,Integer.parseInt(RecordsNumbr)-1);
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);				
					for (int i = 0;i<Integer.parseInt(RecordsNumbr);i++){					
						if (driver.findElements(By.id("rangeTable_row_"+opSelRecord)).size()>0){				
						elementClick("rangeTable_row_"+opSelRecord);						
						Thread.sleep(2000);
						break;				
					}					
					if (!driver.findElement(By.cssSelector("#rangesZoneTable > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > table > tbody > tr > td:nth-child(4) > input")).isEnabled()){
						System.out.println("No se ha encontrado ningún Rango disponible");
						fail("No se ha encontrado ningún Rango disponible");
						return;
					}else{
						driver.findElement(By.cssSelector("#rangesZoneTable > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > table > tbody > tr > td:nth-child(4) > input")).click();											
						Thread.sleep(1000);
						
					}
				}
					if (merchantRange[opSel1].equals("Eliminar")){
						driver.findElement(By.name("ctl00$ContentZone$ctl04")).click();
						Thread.sleep(1000);
						elementClick("popup_ok");
						if (driver.getPageSource().contains("Eliminación terminada con éxito")){
							driver.findElement(By.xpath("//*[@id='toast-container']/div/button")).click();
						}
					}else{
						driver.findElement(By.name("ctl00$ContentZone$ctl03")).click();
					}
				}
			}
			if (optionSel[opSel].equals("Crear") || merchantRange[opSel1].equals("Crear") || merchantRange[opSel1].equals("Modificar")){		  							 
				int rangNumber = ranNumbr(1000,999999);
				String rangName = "Rango_"+rangNumber;
				SendKeys("txtRangeName",rangName+"");
				Thread.sleep(100);
				SendKeys("txtRanges",rangNumber+"");
				Thread.sleep(100);
				if (ranNumbr(0,2)>1){
					elementClick("chkRangeActive");
				}
				Thread.sleep(1000);
				if (ranNumbr(0,2)>1){
					elementClick("chkRangeOnline");
				}
				SendKeys("txtRangeMinLength",ranNumbr(0,20)+"");
				Thread.sleep(100);
				SendKeys("txtRangeMaxLength",ranNumbr(21,99)+"");
				Thread.sleep(100);
				List <WebElement> AccionGen = new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[1]/div[4]/select"))).getOptions();
				new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[1]/div[4]/select"))).selectByIndex(ranNumbr(0,AccionGen.size()-1));
				Thread.sleep(100);
				selectDropDown ("selectedDiscountIdCombo");
				Thread.sleep(100);
				List <WebElement> AccionListaNegra = new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[2]/div[4]/select"))).getOptions();
				new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[2]/div[4]/select"))).selectByIndex(ranNumbr(0,AccionListaNegra.size()-1));
				Thread.sleep(100);
				List <WebElement> AccionCaduca = new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[3]/div[4]/select"))).getOptions();
				new Select(driver.findElement(By.xpath("//*[@id='rangesEditZone']/table[2]/tbody/tr[2]/td[3]/div[4]/select"))).selectByIndex(ranNumbr(0,AccionCaduca.size()-1));
				Thread.sleep(100);
				SendKeys("txtServiceCode",ranNumbr(0,3)+""+ranNumbr(0,3)+""+ranNumbr(0,3));
				Thread.sleep(100);
				SendKeys("txtAmountMin",ranNumbr(1,200)+"");
				Thread.sleep(100);
				SendKeys("txtAmountMax",ranNumbr(201,999)+"");
				Thread.sleep(100);
				driver.findElement(By.name("ctl00$ContentZone$ctl01")).click();
				Thread.sleep(1000);
				if (driver.getPageSource().contains("Guardado terminado correctamente")){
					driver.findElement(By.xpath("//*[@id='toast-container']/div/button")).click();
				}
				
				
			}
			Thread.sleep(3000);
			takeScreenShot("E:\\Selenium\\","modifymerchantRangefilled"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","modifymerchantRangeFilled.jpeg");
			if (driver.findElement(By.xpath("//*[@id='merchantDialog']/table/tbody/tr[3]/td/div/h3[2]")).isDisplayed()){
				listaNegraConfiguration();
			}
						
		}catch (Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
		
	public static void listaNegraConfiguration() throws Exception{
			elementClick("h3AccordionBlacklist");
		try{
			takeScreenShot("E:\\Selenium\\","blackListsectionpage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","blackListsectionpage.jpeg");
			Thread.sleep(3000);
			boolean activeChecked = driver.findElement(By.id("chkBLactive")).isSelected();
			if (optionSel[opSel].equals("Modificar")){				
				if (activeChecked){
					if (ranNumbr(0,1)>0){
						elementClick("chkBLactive");
						activeChecked=false;
					}
				}else{
					elementClick("chkBLactive");
					activeChecked=true;					
				}
			}
			
			if (optionSel[opSel].equals("Crear")){
				if (ranNumbr(0,1)>0){
					elementClick("chkBLactive");
					activeChecked=false;
					Thread.sleep(1000);
				}
			}
			
			boolean activeSharedChecked = driver.findElement(By.id("chkBLSharedBlacklist")).isSelected();
				if (activeChecked){
					if (optionSel[opSel].equals("Modificar")){				
						if (activeSharedChecked){
							elementClick("chkBLSharedBlacklist");
							activeSharedChecked=true;					
						}else{
							if (ranNumbr(0,1)==0){
								elementClick("chkBLSharedBlacklist");
								Thread.sleep(1000);
								selectDropDown("cmbSBLSharedBlacklist");
								activeSharedChecked=false;
							}else{
								activeSharedChecked=true;
							}
						}
					}
					
					if (optionSel[opSel].equals("Crear")){
						if (ranNumbr(0,1)>0){
							elementClick("chkBLSharedBlacklist");
							activeSharedChecked=false;
							Thread.sleep(1000);
							selectDropDown("cmbSBLSharedBlacklist");
						}else{
							activeSharedChecked=true;	
						}
				}
			}			
			if (activeChecked && activeSharedChecked){								
				selectDropDown("cmbBLformatType");
				Thread.sleep(100);
				selectDropDown("cmbBLaccessType");
				Thread.sleep(100);				
				SendKeys("txtBLtime",hourFormat(0,23,0,59));
				Thread.sleep(100);				
				SendKeys("txtBLretryTime",hourFormat(0,23,0,59));
				Thread.sleep(100);
				selectDropDown("cmbBLCadence");
				WebElement weekSelected = new Select (driver.findElement(By.id("cmbBLCadence"))).getFirstSelectedOption();
				String weeSel = weekSelected.getText();
				if (weeSel.equals("Seleccionar días")){
				char [] weekDay = {'L','M','X','J','V','S','D'};
					for (int i=0; i<weekDay.length;i++){
						if (ranNumbr(0,1)>0){
							elementClick("divWeekDaysInBL_chkWeekDayComponent"+weekDay[i]);
						}
					}
				}	
				
				Thread.sleep(100);
				SendKeys("txtBLserver",ranNumbr(170,195)+"."+ranNumbr(15,20)+"."+ranNumbr(130,230)+"."+ranNumbr(2,240));
				Thread.sleep(100);
				SendKeys("txtBLport","22");
				Thread.sleep(100);
				SendKeys("txtBLdirectory","/mechantsData");
				Thread.sleep(100);
				SendKeys("txtBLfileName","mechanDataV001.dat");
				Thread.sleep(100);
				SendKeys("txtBLftpUserName","root");
				Thread.sleep(100);
				SendKeys("txtBLftpPassword","00001");
				Thread.sleep(100);
				int racksel = ranNumbr(0,1);
				if (optionSel[opSel].equals("Modificar")){
					if (driver.findElement(By.id("chkBLSendEmail")).isSelected()){
						racksel =0;		
					}else{
						racksel = ranNumbr(0,1);
					}
				}
								
				if (racksel>0 ){
					elementClick("chkBLSendEmail");
				}else{
					List <WebElement> optionsSelected = new Select(driver.findElement(By.id("cmbAlertNotifListOperators"))).getOptions();									
					if (optionSel[opSel].equals("Modificar")){
						if (optionsSelected.size()>0){
							if (ranNumbr(0,1)>0){
								new Select(driver.findElement(By.id("cmbAlertNotifListOperators"))).selectByIndex(ranNumbr(0,optionsSelected.size()-1));
								Thread.sleep(100);
								elementClick("btnAlertNotifDel");
								Thread.sleep(100);
							}else{
								selectDropDownV("cmbAlertNotifOperators");
								Thread.sleep(100);
								elementClick("btnAlertNotifAdd");
								Thread.sleep(100);
							}
						}
					}
					if (optionsSelected.size()==0){					
						selectDropDownV("cmbAlertNotifOperators");
						Thread.sleep(100);
						elementClick("btnAlertNotifAdd");
						Thread.sleep(100);
					}
					WebElement tableR = driver.findElement(By.xpath("//*[@id='divAlertNotificationInBL']/div/div[2]/table/tbody/tr/td[2]/div/div/table[@class='generalTable']"));
					List <WebElement> tableResult = tableR.findElements(By.tagName("tr"));
					if (optionSel[opSel].equals("Crear") || tableResult.size()==1){
						elementClick("btnAlertNotifCreateExt");
						Thread.sleep(1000);					
						int selOp = ranNumbr(0,nameOp.length-1);
						SendKeys("(//input[@id='txtnameAlertNotification'])[2]",nameOp[selOp]+" "+lastNameOp[selOp]);;
						Thread.sleep(100);
						SendKeys("(//input[@id='txtemailAlertNotification'])[2]",nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com");
						Thread.sleep(100);
						driver.findElement(By.xpath("//div[3]/div[3]/div/button[1]")).click();			
					}
					if (optionSel[opSel].equals("Modificar")|| tableResult.size()>1){
						int recSel = ranNumbr(0,tableResult.size()-1);
							if (recSel == 1){
								recSel= 0;
							}
						int optionS = ranNumbr (0,1);
						switch (optionS){
							case 0:				int crearEditarOption = ranNumbr(0,1);
													if (crearEditarOption == 0){
														elementClick("btnAlertNotifCreateExt");
													}else{
														elementClick("extMailTable_radio_"+recSel);
														Thread.sleep(300);
														elementClick("btnAlertNotifModifyExt");
													}
												Thread.sleep(100);
												int selOp = ranNumbr(0,nameOp.length-1);
												SendKeys("(//input[@id='txtnameAlertNotification'])[2]",nameOp[selOp]+" "+lastNameOp[selOp]);;
												Thread.sleep(100);
												SendKeys("(//input[@id='txtemailAlertNotification'])[2]",nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com");
												Thread.sleep(100);
												driver.findElement(By.xpath("//div[3]/div[3]/div/button[1]")).click();
												break;							
							case 1:				elementClick("extMailTable_radio_"+recSel);
												Thread.sleep(300);
												elementClick("btnAlertNotifDelExt");
												Thread.sleep(300);
												elementClick("popup_ok");
												break;
												
						}
					}
				}
			}

			takeScreenShot("E:\\Selenium\\","blackListsectionfilled"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","blackListsectionfilled.jpeg");
				Thread.sleep(5000);
				if (driver.findElement(By.xpath("//*[@id='merchantDialog']/table/tbody/tr[3]/td/div/h3[3]")).isDisplayed()){
					configuracionFicheroLotes();
				}
				
		}catch (Exception e){
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
						
	
	public static void configuracionFicheroLotes() throws Exception{
		Thread.sleep(2000);
		elementClick("h3AccordionBatchFiles");
		try{			
			takeScreenShot("E:\\Selenium\\","batchFilesectionPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","batchFilesectionPage.jpeg");
			Thread.sleep(3000);
			boolean activeChecked = driver.findElement(By.id("chkBatchfilesactive")).isSelected();
			if (optionSel[opSel].equals("Modificar")){				
				if (activeChecked){
					if (ranNumbr(0,1)>0){
						elementClick("chkBatchfilesactive");
						activeChecked=false;
					}
				}else{
					elementClick("chkBatchfilesactive");
					activeChecked=true;					
				}
			}		
			if (optionSel[opSel].equals("Crear")){
				if (ranNumbr(0,1)>0){
					elementClick("chkBatchfilesactive");
					activeChecked=false;
					Thread.sleep(1000);
				}
			}
			
			boolean activeSharedChecked = driver.findElement(By.id("chkBatchfilesSharedBatchfiles")).isSelected();
				if (activeChecked){
					if (optionSel[opSel].equals("Modificar")){				
						if (activeSharedChecked){
							elementClick("chkBatchfilesSharedBatchfiles");
							activeSharedChecked=true;					
						}else{
							if (ranNumbr(0,1)==0){
								elementClick("chkBatchfilesSharedBatchfiles");
								Thread.sleep(1000);
								selectDropDown("cmbSBatchfilesSharedBatchfiles");
								activeSharedChecked=false;
							}else{
								activeSharedChecked=true;
							}
						}
					}
					
					if (optionSel[opSel].equals("Crear")){
						if (ranNumbr(0,1)>0){
							elementClick("chkBatchfilesSharedBatchfiles");
							activeSharedChecked=false;
							Thread.sleep(1000);
							selectDropDown("cmbSBatchfilesSharedBatchfiles");
						}else{
							activeSharedChecked=true;	
						}
				}
			}			
			
			if (activeChecked && activeSharedChecked){											
					selectDropDown("cmbBatchfilesformatType");
					Thread.sleep(100);
					selectDropDown("cmbBatchfilesaccessType");
					Thread.sleep(1000);
					WebElement bachtFileT = new Select (driver.findElement(By.id("cmbBatchfilesaccessType"))).getFirstSelectedOption();
					String batchFileText = bachtFileT.getText();
					driver.findElement(By.id("txtBatchfilestime")).clear();
					driver.findElement(By.id("txtBatchfilestime")).sendKeys(hourFormat(0,23,0,59));
					Thread.sleep(100);
					driver.findElement(By.id("txtBatchfilesretryTime")).clear();
					driver.findElement(By.id("txtBatchfilesretryTime")).sendKeys(hourFormat(0,23,0,59));
					Thread.sleep(100);
					selectDropDown("cmbBatchfilesCadence");
					WebElement weekSelected = new Select (driver.findElement(By.id("cmbBLCadence"))).getFirstSelectedOption();
					String weeSel = weekSelected.getText();
					if (weeSel.equals("Seleccionar días")){
						char dayWeek[] = {'L', 'M','X','J','V','S','D'};
						for (int i = 0; i< dayWeek.length;i++){
							if (ranNumbr(0,1)>0){
								elementClick("divWeekDaysInDiscount_chkWeekDayComponent"+dayWeek[i]);								
							}
							Thread.sleep(300);
						}
					}
					if (batchFileText.equals("EMAIL")){
					Thread.sleep(100);
					SendKeys("txtBatchfilesserver","mavi.garrido@tecsidel.es");
					Thread.sleep(100);
					SendKeys("txtBatchfilesfileName","batchFileDataV001.dat");
					Thread.sleep(2000);
				}else{
					Thread.sleep(100);
					SendKeys("txtBatchfilesserver",ranNumbr(170,195)+"."+ranNumbr(15,20)+"."+ranNumbr(130,230)+"."+ranNumbr(2,240));
					Thread.sleep(100);
					SendKeys("txtBatchfilesport","22");
					Thread.sleep(100);
					SendKeys("txtBatchfilesdirectory","/BachFileData");
					Thread.sleep(100);
					SendKeys("txtBatchfilesfileName","batchFileDataV001.dat");
					Thread.sleep(100);
					SendKeys("txtBatchfilesftpUserName","mavi_test");
					Thread.sleep(100);
					SendKeys("txtBatchfilesftpPassword","00001");
					Thread.sleep(2000);
				}		
				Thread.sleep(2000);
				int racksel = ranNumbr(0,1);
				if (optionSel[opSel].equals("Modificar")){
					if (driver.findElement(By.id("chkBatchfilesSendEmail")).isSelected()){
						racksel =0;		
					}else{
						racksel = ranNumbr(0,1);
					}
				}								
				if (racksel>0 ){
					elementClick("chkBatchfilesSendEmail");
				}else{
					List <WebElement> alertNo1 = new Select(driver.findElement(By.cssSelector("#divAlertNotificationInBatchFiles_component > div.ng-scope > div > table > tbody > tr > td > div.formRow > #cmbAlertNotifOperators"))).getOptions();
					List <WebElement> alertNo = new Select(driver.findElement(By.xpath("(//select[@id='cmbAlertNotifListOperators'])[2]"))).getOptions();					
					if (optionSel[opSel].equals("Modificar")){
						if (alertNo.size()>0){
							if (ranNumbr(0,1)>0){
								new Select(driver.findElement(By.xpath("(//select[@id='cmbAlertNotifListOperators'])[2]"))).selectByIndex(ranNumbr(0,alertNo.size()-1));
								Thread.sleep(2000);
								elementClick("(//input[@id='btnAlertNotifDel'])[2]");
								Thread.sleep(1000);
							}else{
								new Select(driver.findElement(By.cssSelector("#divAlertNotificationInBatchFiles_component > div.ng-scope > div > table > tbody > tr > td > div.formRow > #cmbAlertNotifOperators"))).selectByIndex(ranNumbr(1,alertNo1.size()-1));								
								Thread.sleep(1000);
								elementClick("(//input[@id='btnAlertNotifAdd'])[2]");
								Thread.sleep(1000);
							}
						}
					}
					if (alertNo.size()==0){
						new Select(driver.findElement(By.cssSelector("#divAlertNotificationInBatchFiles_component > div.ng-scope > div > table > tbody > tr > td > div.formRow > #cmbAlertNotifOperators"))).selectByIndex(ranNumbr(1,alertNo1.size()-1));
						Thread.sleep(1000);
						elementClick("(//input[@id='btnAlertNotifAdd'])[2]");
						Thread.sleep(1000);
					}
					WebElement tableR = driver.findElement(By.xpath("//*[@id='divAlertNotificationInBatchFiles_component']/div/div[2]/table/tbody/tr/td[2]/div/div/table[@class='generalTable']"));
					List <WebElement> tableResult = tableR.findElements(By.tagName("tr"));
					int recSel = ranNumbr(0,tableResult.size()-1);
					if (optionSel[opSel].equals("Crear") || tableResult.size()==1){
						elementClick("(//input[@id='btnAlertNotifCreateExt'])[2]");
						Thread.sleep(1000);					
						int selOp = ranNumbr(0,nameOp.length-1);
						SendKeys("(//input[@id='txtnameAlertNotification'])[2]",nameOp[selOp]+" "+lastNameOp[selOp]);;						
						Thread.sleep(100);
						SendKeys("(//input[@id='txtemailAlertNotification'])[2]",nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com");
						Thread.sleep(100);
						driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();			
					}
					if (optionSel[opSel].equals("Modificar")|| tableResult.size()>1){						
							if (recSel == 1){
								recSel= 0;
							}						
						int optionS = ranNumbr (0,1);
						switch (optionS){
							case 0:				int crearEditarOption = ranNumbr(0,1);
													if (crearEditarOption == 0){
														elementClick("(//input[@id='btnAlertNotifCreateExt'])[2]");
													}else{
														elementClick("(//input[@id='extMailTable_radio_"+recSel+"'])[2]");
														Thread.sleep(300);
														elementClick("(//input[@id='btnAlertNotifModifyExt'])[2]");
													}
												Thread.sleep(500);
												int selOp = ranNumbr(0,nameOp.length-1);
												SendKeys("(//input[@id='txtnameAlertNotification'])[2]",nameOp[selOp]+" "+lastNameOp[selOp]);;
												Thread.sleep(500);
												SendKeys("(//input[@id='txtemailAlertNotification'])[2]",nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com");
												Thread.sleep(500);
												driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
												break;							
							case 1:				elementClick("(//input[@id='extMailTable_radio_"+recSel+"'])[2]");
												Thread.sleep(3000);
												elementClick("(//input[@id='btnAlertNotifDelExt'])[2]");
												Thread.sleep(300);
												elementClick("popup_ok");
												break;
												
						}
					}
				
				}
			}
				takeScreenShot("E:\\Selenium\\","batchFilesectionfilled"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","batchFilesectionfilled.jpeg");
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
