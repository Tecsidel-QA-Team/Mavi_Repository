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
		private static String clickItem;
		
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
				action.clickAndHold(driver.findElement(By.linkText("Gestión de pagos"))).build().perform();
				Thread.sleep(1000);
				driver.findElement(By.linkText("Gestión de merchants")).click();
				Thread.sleep(500);
				takeScreenShot("E:\\Selenium\\","gestioinmerchantspage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","gestioinmerchantspage.jpeg");				
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
				driver.findElement(By.cssSelector("html body div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix div.ui-dialog-buttonset button.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only")).click();				
				Thread.sleep(2000);
				String HMver = BOVersion.substring(1);
				if (HMver.length()>18){
					HMver = BOVersion.substring(18);			
				}else{
					HMver = "<HM is not running>";
				}				
				System.out.println("Se ha creado el Merchant: " +merchantName+" con el Código: "+merchantCode+" correctamente");
				System.out.println("Se ha probado en la versión del BO Host: " + BOVersion.substring(1,16)+" y Host Manager: "+HMver);
			}catch (Exception e){
				e.getStackTrace();
				fail(e.getMessage());
			}
		}
		
		public static void modifyMerchant() throws Exception{
		try{
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/table/tbody/tr/td[1]/div[1]/input")).sendKeys(merchantName);
			Thread.sleep(100);
			driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/table/tbody/tr/td[1]/div[2]/input")).sendKeys(merchantCode+"");
			Thread.sleep(100);
			elementClick("ctl00_ButtonsZone_BtnSearch");
			Thread.sleep(1000);
			elementClick("merchantTable_radio_0");			
			Thread.sleep(100);
			elementClick("ctl00_ContentZone_BtnModify");
			Thread.sleep(3000);
			takeScreenShot("E:\\Selenium\\","modifymerchantPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","modifymerchantPage.jpeg");			
			driver.findElement(By.xpath("//*[@class='ng-binding ui-accordion-header ui-state-default ui-corner-all ui-accordion-icons']")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_td1']/div/input[1]")).click();
			Thread.sleep(8000);
			int rangNumber = ranNumbr(1000,999999);
			String rangName = "Rango_"+rangNumber;
			driver.findElement(By.id("txtRangeName")).sendKeys(rangName);
			Thread.sleep(100);
			driver.findElement(By.id("txtRanges")).sendKeys(rangNumber+"");
			Thread.sleep(100);
			if (ranNumbr(0,2)>1){
				elementClick("chkRangeActive");
			}
			Thread.sleep(1000);
			if (ranNumbr(0,2)>1){
				elementClick("chkRangeOnline");
			}
			driver.findElement(By.id("txtRangeMinLength")).sendKeys(ranNumbr(0,20)+"");
			Thread.sleep(100);
			driver.findElement(By.id("txtRangeMaxLength")).sendKeys(ranNumbr(21,99)+"");
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
			driver.findElement(By.id("txtServiceCode")).clear();
			driver.findElement(By.id("txtServiceCode")).sendKeys(ranNumbr(0,3)+""+ranNumbr(0,3)+""+ranNumbr(0,3));
			Thread.sleep(100);
			driver.findElement(By.id("txtAmountMin")).sendKeys(ranNumbr(1,200)+"");
			Thread.sleep(100);
			driver.findElement(By.id("txtAmountMax")).sendKeys(ranNumbr(201,999)+"");
			Thread.sleep(100);
			driver.findElement(By.name("ctl00$ContentZone$ctl01")).click();
			Thread.sleep(1000);
			if (driver.getPageSource().contains("Guardado terminado correctamente")){
				driver.findElement(By.xpath("//*[@id='toast-container']/div/button")).click();
			}
			Thread.sleep(3000);
			takeScreenShot("E:\\Selenium\\","modifymerchantRangefilled"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","modifymerchantRangeFilled.jpeg");
			if (driver.findElement(By.xpath("//*[@id='merchantDialog']/table/tbody/tr[3]/td/div/h3[2]")).isDisplayed()){
				listaNegraConfiguration();
			}
			
		}catch (Exception e){
			fail(e.getMessage());
		}
	}
		
		public static void listaNegraConfiguration() throws Exception{
			elementClick("h3AccordionBlacklist");
		try{
			takeScreenShot("E:\\Selenium\\","blackListsectionpage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionMarchant\\attachments\\","blackListsectionpage.jpeg");
			Thread.sleep(1000);
			if (ranNumbr(0,2)>1){
				elementClick("chkBLSharedBlacklist");
				selectDropDownV("cmbSBLSharedBlacklist");
				Thread.sleep(1000);
			}else{				
				selectDropDown("cmbBLformatType");
				Thread.sleep(100);
				selectDropDown("cmbBLaccessType");
				Thread.sleep(100);
				driver.findElement(By.id("txtBLtime")).clear();
				driver.findElement(By.id("txtBLtime")).sendKeys(hourFormat(0,23,0,59));
				Thread.sleep(100);
				driver.findElement(By.id("txtBLretryTime")).clear();
				driver.findElement(By.id("txtBLretryTime")).sendKeys(hourFormat(0,23,0,59));
				Thread.sleep(100);
				selectDropDown("cmbBLCadence");
				WebElement weekSelected = new Select (driver.findElement(By.id("cmbBLCadence"))).getFirstSelectedOption();
				String weeSel = weekSelected.getText();
				if (weeSel.equals("Seleccionar días")){
					if (ranNumbr(0,1)>0){
						elementClick("divWeekDaysInBL_chkWeekDayComponentL");
					}
					if (ranNumbr(0,1)>0){
						elementClick("divWeekDaysInBL_chkWeekDayComponentM");
					}
					if (ranNumbr(0,1)>0){
						elementClick("divWeekDaysInBL_chkWeekDayComponentX");
					}
					if (ranNumbr(0,1)>0){
						elementClick("divWeekDaysInBL_chkWeekDayComponentJ");
					}
					if (ranNumbr(0,1)>0){
						elementClick("divWeekDaysInBL_chkWeekDayComponentV");
					}
					if (ranNumbr(0,1)>0){
						elementClick("divWeekDaysInBL_chkWeekDayComponentS");
					}
					if (ranNumbr(0,1)>0){
						elementClick("divWeekDaysInBL_chkWeekDayComponentD");
					}
				}
				Thread.sleep(100);
				driver.findElement(By.id("txtBLserver")).sendKeys(ranNumbr(170,195)+"."+ranNumbr(15,20)+"."+ranNumbr(130,230)+"."+ranNumbr(2,240));
				Thread.sleep(100);
				driver.findElement(By.id("txtBLport")).sendKeys("22");
				Thread.sleep(100);
				driver.findElement(By.id("txtBLdirectory")).sendKeys("/mechantsData");
				Thread.sleep(100);
				driver.findElement(By.id("txtBLfileName")).sendKeys("mechanDataV001.dat");
				Thread.sleep(100);
				driver.findElement(By.id("txtBLftpUserName")).sendKeys("mavi_test");
				Thread.sleep(100);
				driver.findElement(By.id("txtBLftpPassword")).sendKeys("00001");
				Thread.sleep(100);
				int racksel = 0;
				if (racksel>0){
					elementClick("chkBatchfilesSendEmail");
				}else{
					selectDropDownV("cmbAlertNotifOperators");
					Thread.sleep(100);
					elementClick("btnAlertNotifAdd");
					Thread.sleep(100);					
					elementClick("btnAlertNotifCreateExt");
					Thread.sleep(1000);					
					int selOp = ranNumbr(0,nameOp.length-1);
					driver.findElement(By.xpath("(//input[@id='txtnameAlertNotification'])[2]")).sendKeys(nameOp[selOp]+" "+lastNameOp[selOp]);;
					Thread.sleep(100);
					driver.findElement(By.xpath("(//input[@id='txtemailAlertNotification'])[2]")).sendKeys(nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com");
					Thread.sleep(100);
					driver.findElement(By.xpath("//div[3]/div[3]/div/button[1]")).click();	
					
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
			Thread.sleep(1000);
			if (ranNumbr(0,2)>1){
				elementClick("chkBatchfilesSharedBatchfiles");
				selectDropDownV("cmbSBatchfilesSharedBatchfiles");
			}else{				
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
					driver.findElement(By.id("txtBatchfilesserver")).sendKeys("mavi.garrido@tecsidel.es");
					Thread.sleep(100);
					driver.findElement(By.id("txtBatchfilesfileName")).sendKeys("batchFileDataV001.dat");
					Thread.sleep(2000);
				}else{
					Thread.sleep(100);
					driver.findElement(By.id("txtBatchfilesserver")).sendKeys(ranNumbr(170,195)+"."+ranNumbr(15,20)+"."+ranNumbr(130,230)+"."+ranNumbr(2,240));
					Thread.sleep(100);
					driver.findElement(By.id("txtBatchfilesport")).sendKeys("22");
					Thread.sleep(100);
					driver.findElement(By.id("txtBatchfilesdirectory")).sendKeys("/BachFileData");
					Thread.sleep(100);
					driver.findElement(By.id("txtBatchfilesfileName")).sendKeys("batchFileDataV001.dat");
					Thread.sleep(100);
					driver.findElement(By.id("txtBatchfilesftpUserName")).sendKeys("mavi_test");
					Thread.sleep(100);
					driver.findElement(By.id("txtBatchfilesftpPassword")).sendKeys("00001");
					Thread.sleep(2000);
				}
				int racksel = ranNumbr(0,1);
				if (racksel==0){
					elementClick("chkBatchfilesSendEmail");
				}else{
					List <WebElement> alertNo = new Select(driver.findElement(By.cssSelector("#divAlertNotificationInBatchFiles_component > div.ng-scope > div > table > tbody > tr > td > div.formRow > #cmbAlertNotifOperators"))).getOptions();
					new Select(driver.findElement(By.cssSelector("#divAlertNotificationInBatchFiles_component > div.ng-scope > div > table > tbody > tr > td > div.formRow > #cmbAlertNotifOperators"))).selectByIndex(ranNumbr(1,alertNo.size()-1));
					Thread.sleep(1000);
					elementClick("btnAlertNotifAdd");
					Thread.sleep(1000);					
					elementClick("btnAlertNotifCreateExt");
					Thread.sleep(1000);					
					int selOp = ranNumbr(0,nameOp.length-1);
					driver.findElement(By.xpath("(//input[@id='txtnameAlertNotification'])[2]")).sendKeys(nameOp[selOp]+" "+lastNameOp[selOp]);;
					Thread.sleep(100);
					driver.findElement(By.xpath("(//input[@id='txtemailAlertNotification'])[2]")).sendKeys(nameOp[selOp].toLowerCase()+"."+lastNameOp[selOp].toLowerCase()+"@tecsidel.com");
					Thread.sleep(100);
					driver.findElement(By.xpath("//div[3]/div[3]/div/button[1]")).click();	
					
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
