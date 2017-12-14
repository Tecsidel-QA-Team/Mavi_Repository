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
import org.openqa.selenium.WebElement;
import R3R5CiralsaSettings.Settingsfields_File;


public class BOHost_gestionDescuento extends Settingsfields_File {		
		private static String [] gestionDescOption = {"Crear", "Modificar", "Eliminar"};
		private static String OptionSel;
		private static String descuentoNombre;
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
		public void gestionDescuento() throws Exception {
			borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\");
			driver.get(BoHostUrl);
			takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","loginBOPage.jpeg");
			loginPage("00001","00001");
			takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
			takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","homeBOHostPage.jpeg");
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
			opSel = ranNumbr(0,2);
			switch (gestionDescOption[opSel]){
				case "Crear":		OptionSel = "ctl00_ContentZone_BtnCreate";
									crear_modificarDescuento();
									System.out.println("Se ha Creado el Descuento: "+descuentoNombre+" correctamente");
									Thread.sleep(1000);
									break;
				case "Modificar":	OptionSel = "ctl00_ContentZone_BtnModify";
									crear_modificarDescuento();
									System.out.println("Se ha Modificado el Descuento: "+descuentoNombre+" correctamente");
									break;
				case "Eliminar": 	OptionSel = "ctl00_ContentZone_BtnDelete";
									eliminarDescuento();
									System.out.println("Se ha Eliminado el Descuento: "+descuentoNombre+" correctamente");
									break;								
			}
			
			System.out.println("Se ha probado en la versión del BO Host: " + BOVersion+" y Host Manager: "+HMver);
			
		}
		
		public static void crear_modificarDescuento() throws Exception {
			Actions action = new Actions(driver);			
			try{
				action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
				Thread.sleep(1000);
				driver.findElement(By.linkText("Gestión de descuentos")).click();
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","gestionDescuentopage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","gestionDescuentopage.jpeg");
				if (gestionDescOption[opSel]=="Modificar"){
					String recordDis = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span")).getText();					
					int selRecord = ranNumbr(1,Integer.parseInt(recordDis.substring(20)));					
					if (selRecord>11){
						for (int i = 0; i<selRecord;i++){
							elementClick("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[4]/input");
							for(int a = 2; a<=11; a ++){
								Thread.sleep(100);	
								descuentoNombre = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+a+"]/td[2]")).getText(); 
								recordFound = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+a+"]/td[1]/input")).getAttribute("id");																			
								if (recordFound.equals("discountTable_radio_"+(selRecord-1))){								
									driver.findElement(By.xpath("//*[@id='"+recordFound+"']")).click();
									Thread.sleep(5000);
									a=12;
									i = selRecord;
									break;
								}
							}
						}
					}else{
						if (selRecord == 1){
							selRecord=2;
						}
						descuentoNombre = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selRecord+"]/td[2]")).getText();
						recordFound = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selRecord+"]/td[1]/input")).getAttribute("id");
						driver.findElement(By.xpath("//*[@id='"+recordFound+"']")).click();								
						Thread.sleep(1000);
					}				
				}				
				elementClick(OptionSel);
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","crearDescuentoPagina"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","crearDescuentoPagina.jpeg");			
				if (gestionDescOption[opSel]=="Crear"){
					int opSel = ranNumbr(0,nameOp.length-1);
					descuentoNombre = "DESCUENTO_".concat(nameOp[opSel]);
					SendKeys("//*[@id='discountDialog']/table/tbody/tr[1]/td[1]/div/input",descuentoNombre);
					Thread.sleep(100);
				}
				SendKeys("//*[@id='discountDialog']/table/tbody/tr[1]/td[2]/div/input","Descuento hecho para: "+descuentoNombre);
				Thread.sleep(100);
				if (ranNumbr(0,1)>0){
					elementClick("//*[@id='discountDialog']/table/tbody/tr[2]/td[1]/div[1]/input");
				}
				Thread.sleep(100);
				if (ranNumbr(0,1)>0){
					elementClick("//*[@id='discountDialog']/table/tbody/tr[2]/td[1]/div[2]/input");
				}
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[2]/td[2]/div[1]/select");
				WebElement DependeOtro = new Select(driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[2]/td[2]/div[1]/select"))).getFirstSelectedOption();
				String DependeOtroText = DependeOtro.getText();
				if (!DependeOtroText.equals("Indiferente")){
					selectDropDown("selectedIdCombo");
				}
				selectDropDown("cmbDlgDiscountType");
				WebElement TypeDis = new Select(driver.findElement(By.id("cmbDlgDiscountType"))).getFirstSelectedOption();
				String TypeDisText = TypeDis.getText();
				Thread.sleep(1000);
				if (TypeDisText.equals("Por recurrencia")){
					SendKeys("txtDlgvehicleNumMin",ranNumbr(1,20)+"");
					Thread.sleep(100);
					SendKeys("txtDlgvehicleNumMax",ranNumbr(21,100)+"");
					Thread.sleep(100);
					if (ranNumbr(0,1)>0){
						driver.findElement(By.cssSelector("#discountDialog > table > tbody > tr:nth-child(1) > td:nth-child(3) > div:nth-child(2) > div:nth-child(3) > input")).click();

					}
					Thread.sleep(100);
					selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[1]/td[3]/div[2]/div[4]/select");				
				}
				Thread.sleep(100);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[3]/td[1]/div/select");
				Thread.sleep(100);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[3]/td[2]/div/select");
				Thread.sleep(100);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[4]/td[1]/div/select");
				Thread.sleep(100);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[4]/td[2]/div/select");
				Thread.sleep(100);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[4]/td[3]/div/select");
				Thread.sleep(100);
				for (int i = 1; i<= 7;i++){
					String daySel = driver.findElement(By.xpath("//*[@id='divWeekDaysInDiscount']/div/div["+i+"]/input")).getAttribute("id");					
					Thread.sleep(300);
					if (ranNumbr(0,1)>0){
						elementClick(daySel);
					}
					Thread.sleep(300);				
				}				
				Thread.sleep(300);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[6]/td[1]/div[1]/select");
				Thread.sleep(300);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[6]/td[1]/div[2]/select");
				Thread.sleep(300);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[6]/td[1]/div[3]/select");
				Thread.sleep(300);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[6]/td[2]/div[1]/select");
				Thread.sleep(300);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[6]/td[2]/div[2]/select");
				Thread.sleep(300);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[6]/td[2]/div[3]/select");
				Thread.sleep(300);
				if (ranNumbr(0,1)>0){
					elementClick("//*[@id='discountDialog']/table/tbody/tr[7]/td[1]/div[1]/input");
					Thread.sleep(300);					
					SendKeys("ctl00_ContentZone_dateSelector_dt_from_box_date",dateSel(2017,2017));
					Thread.sleep(300);					
					SendKeys("ctl00_ContentZone_dateSelector_dt_to_box_date",dateSel(2018,2019));				
				}
				Thread.sleep(300);
				if (ranNumbr(0,1)>0){
					elementClick("//*[@id='discountDialog']/table/tbody/tr[7]/td[2]/div[1]/input");
					Thread.sleep(300);
					SendKeys("//*[@id='discountDialog']/table/tbody/tr[7]/td[2]/div[2]/div[1]/input",hourFormat(0,23,0,59));
					Thread.sleep(300);
					SendKeys("//*[@id='discountDialog']/table/tbody/tr[7]/td[2]/div[2]/div[2]/input",hourFormat(0,23,0,59));
				}
				Thread.sleep(300);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[7]/td[3]/div/select");
				Thread.sleep(300);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[8]/td[1]/div/select");
				Thread.sleep(1000);
				WebElement valueType = new Select(driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[8]/td[1]/div/select"))).getFirstSelectedOption();
				String valueTypeText = valueType.getText();
				if (valueTypeText.equals("Porcentaje")){
					int value = ranNumbr(1,100);
					int valD = ranNumbr(0,99);
					if (value == 100){
						SendKeys("//*[@id='discountDialog']/table/tbody/tr[8]/td[2]/div/input",value+","+"0");
					}else{
						SendKeys("//*[@id='discountDialog']/table/tbody/tr[8]/td[2]/div/input",value+","+valD);
					}
				}else{
					int value = ranNumbr(1,999);
					int valD = ranNumbr(0,99);
					SendKeys("//*[@id='discountDialog']/table/tbody/tr[8]/td[2]/div/input",value+","+valD);
				}
				Thread.sleep(300);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[8]/td[3]/div/select");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","crearDescuentoFilled"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","crearDescuentoFilled.jpeg");
				driver.findElement(By.cssSelector("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)")).click();
				Thread.sleep(5000);
				
				
			}catch (Exception e){
				e.printStackTrace();
				fail(e.getMessage());
			}
		}
		
		public static void eliminarDescuento() throws Exception {
			Actions action = new Actions(driver);			
			try{
				action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
				Thread.sleep(1000);
				driver.findElement(By.linkText("Gestión de descuentos")).click();
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","gestionDescuentopage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","gestionDescuentopage.jpeg");				
					String recordDis = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[3]/span")).getText();					
					int selRecord = ranNumbr(1,Integer.parseInt(recordDis.substring(20)));					
					if (selRecord>11){
						for (int i = 0; i<selRecord;i++){
							elementClick("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[1]/td[2]/div/table/tbody/tr/td[4]/input");
							for(int a = 2; a<=11; a ++){
								Thread.sleep(100);	
								descuentoNombre = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+a+"]/td[2]")).getText(); 
								recordFound = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+a+"]/td[1]/input")).getAttribute("id");																			
								if (recordFound.equals("discountTable_radio_"+(selRecord-1))){								
									driver.findElement(By.xpath("//*[@id='"+recordFound+"']")).click();
									Thread.sleep(5000);
									a=12;
									i = selRecord;
									break;
								}
							}
						}
					}else{
						if (selRecord == 1){
							selRecord=2;
						}
						descuentoNombre = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selRecord+"]/td[2]")).getText();
						recordFound = driver.findElement(By.xpath("//*[@id='content']/div[2]/div/div/div/div[1]/div[1]/table/tbody/tr[2]/td[2]/div/table/tbody/tr["+selRecord+"]/td[1]/input")).getAttribute("id");
						driver.findElement(By.xpath("//*[@id='"+recordFound+"']")).click();								
						Thread.sleep(1000);
					}												
				elementClick(OptionSel);
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","eliminarDescuentoPagina"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","eliminarDescuentoPagina.jpeg");
				elementClick("popup_ok");
				Thread.sleep(4000);
				if (driver.getPageSource().contains("No se puede eliminar el descuento")){
					String errorText = driver.findElement(By.xpath("//*[@id='toast-container']/div/div")).getText();
					System.out.println("No se puede eliminar el Descuento debido a: "+errorText);
					fail("No se puede eliminar el Descuento debido a: "+errorText);
				}
				
				
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