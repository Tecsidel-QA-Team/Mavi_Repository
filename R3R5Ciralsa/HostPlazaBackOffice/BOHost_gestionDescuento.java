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

public class BOHost_gestionDescuento extends Settingsfields_File {		
		private static String merchantName;
		private static int merchantCode;
		
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
		public void gestionDescuento() throws Exception {
			Actions action = new Actions(driver);
			borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\");
			try{
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","loginBOPage.jpeg");
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","homeBOHostPage.jpeg");
				BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
				Thread.sleep(2000);					
				action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
				Thread.sleep(1000);
				driver.findElement(By.linkText("Gestión de descuentos")).click();
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","gestionDescuentopage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","gestionDescuentopage.jpeg");				
				elementClick("ctl00_ContentZone_BtnCreate");
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","crearDescuentoPagina"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","crearDescuentoPagina.jpeg");
				int opSel = ranNumbr(0,nameOp.length-1);
				String descuentoNombre = "DESCUENTO_".concat(nameOp[opSel]);
				driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[1]/td[1]/div/input")).sendKeys(descuentoNombre);
				Thread.sleep(100);
				driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[1]/td[2]/div/input")).sendKeys("Descuento hecho para: "+descuentoNombre);
				Thread.sleep(100);
				if (ranNumbr(0,1)>0){
					driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[2]/td[1]/div[1]/input")).click();
				}
				Thread.sleep(100);
				if (ranNumbr(0,1)>0){
					driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[2]/td[1]/div[2]/input")).click();
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
				if (TypeDisText.equals("Por recurrencia")){
					driver.findElement(By.id("txtDlgvehicleNumMin")).sendKeys(ranNumbr(1,20)+"");
					Thread.sleep(100);
					driver.findElement(By.id("txtDlgvehicleNumMax")).sendKeys(ranNumbr(21,100)+"");
					Thread.sleep(100);
					if (ranNumbr(0,1)>0){
						driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[1]/td[3]/div[2]/div[3]/input")).click();
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
				char dayWeek[] = {'L', 'M','X','J','V','S','D'};
				for (int i = 0; i< dayWeek.length;i++){
					if (ranNumbr(0,1)>0){
						elementClick("divWeekDaysInDiscount_chkWeekDayComponent"+dayWeek[i]);						
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
					driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[7]/td[1]/div[1]/input")).click();
					Thread.sleep(300);					
					driver.findElement(By.id("ctl00_ContentZone_dateSelector_dt_from_box_date")).sendKeys(dateSel(2017,2017));
					Thread.sleep(300);					
					driver.findElement(By.id("ctl00_ContentZone_dateSelector_dt_to_box_date")).sendKeys(dateSel(2018,2019));				
				}
				Thread.sleep(300);
				if (ranNumbr(0,1)>0){
					driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[7]/td[2]/div[1]/input")).click();
					Thread.sleep(300);
					driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[7]/td[2]/div[2]/div[1]/input")).clear();
					driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[7]/td[2]/div[2]/div[1]/input")).sendKeys(hourFormat(0,23,0,59));
					Thread.sleep(300);
					driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[7]/td[2]/div[2]/div[2]/input")).clear();
					driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[7]/td[2]/div[2]/div[2]/input")).sendKeys(hourFormat(0,23,0,59));
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
						driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[8]/td[2]/div/input")).sendKeys(value+","+"0");
					}else{
						driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[8]/td[2]/div/input")).sendKeys(value+","+valD);
					}
				}else{
					int value = ranNumbr(1,999);
					int valD = ranNumbr(0,99);
					driver.findElement(By.xpath("//*[@id='discountDialog']/table/tbody/tr[8]/td[2]/div/input")).sendKeys(value+","+valD);
				}
				Thread.sleep(300);
				selectDropDownXPath("//*[@id='discountDialog']/table/tbody/tr[8]/td[3]/div/select");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","crearDescuentoFilled"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_gestionDescuento\\attachments\\","crearDescuentoFilled.jpeg");
				driver.findElement(By.cssSelector("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-dialog-buttons.ui-draggable.ui-resizable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(1)")).click();
				Thread.sleep(5000);
				String HMver = BOVersion.substring(1);
				if (HMver.length()>18){
					HMver = BOVersion.substring(18);			
				}else{
					HMver = "<HM is not running>";
				}
				System.out.println("Se ha Creado el Descuento: "+descuentoNombre+" correctamente");
				System.out.println("Se ha probado en la versión del BO Host: " + BOVersion.substring(1,16)+" y Host Manager: "+HMver);
				
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