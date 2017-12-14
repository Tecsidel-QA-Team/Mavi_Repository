package HostPlazaBackOffice;

import static org.junit.Assert.*;
import R3R5CiralsaSettings.Settingsfields_File;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class BOHost_operatorCreation extends Settingsfields_File {
			 private static String lastcreated ;
			 private static WebElement tableResult;
			 private static List<WebElement> userResults;
			 private static String enviarViaVer;
			 private static Statement stmt;
			 private static ResultSet rs;
			 public static String Operator="";
			 private static String queryString;
			 private static ArrayList<String> transactions = new ArrayList<String>();
			 private static int i;
	
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

			@After
			public void tearDown() throws Exception{			  
				    driver.quit();
				    String verificationErrorString = verificationErrors.toString();
				    if (!"".equals(verificationErrorString)) {
				      fail(verificationErrorString);
				    }
			}

@Test
public void crearOperadores() throws Exception {
	Actions action = new Actions(driver);	
	borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_crearOperadores\\attachments\\");
	try{
		driver.get(BoHostUrl);
		takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_crearOperadores\\attachments\\","loginBOPage.jpg");
		loginPage("00001","00001");
		takeScreenShot("E:\\Selenium\\","homeBOPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_crearOperadores\\attachments\\","homeBOPage.jpg");
		BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();		
		Thread.sleep(2000);					
		action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
		Thread.sleep(1000);		
		action.clickAndHold(driver.findElement(By.linkText("Operadores"))).build().perform();
		Thread.sleep(500);
		driver.findElement(By.linkText("Gestión de operadores")).click();								
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","gestionOperadoresPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_crearOperadores\\attachments\\","gestionOperadoresPage.jpg");
		Thread.sleep(500);		
		elementClick("ctl00_ContentZone_BtnCreate");
		Thread.sleep(1000);
		takeScreenShot("E:\\Selenium\\","crearOperadoresPage"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_crearOperadores\\attachments\\","crearOperadoresPage.jpg");		
		int userSel = ranNumbr(0,nameOp.length-1);
		Thread.sleep(100);
		new Select (driver.findElement(By.id("ctl00_ContentZone_cmb_title_cmb_dropdown"))).selectByVisibleText(sexSelc[userSel]);
		Thread.sleep(100);
		new Select (driver.findElement(By.id("ctl00_ContentZone_cmb_gender_cmb_dropdown"))).selectByVisibleText(genderS[userSel]);
		Thread.sleep(100);		
		driver.findElement(By.id("ctl00_ContentZone_forename_box_data")).sendKeys(nameOp[userSel]);
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_surname_box_data")).sendKeys(lastNameOp[userSel]);
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_txt_address_box_data")).sendKeys(addressTec[userSel]);
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_txt_postcode_box_data")).sendKeys(cpAdress[userSel]);
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_txt_city_box_data")).sendKeys(townC[userSel]);
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_txt_country_box_data")).sendKeys("España");
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_email_box_data")).sendKeys(nameOp[userSel].toLowerCase()+lastNameOp[userSel].toLowerCase()+"@tecsidel.es");
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_txt_phone_box_data")).sendKeys(ranNumbr(600000000,699999999)+"");
		Thread.sleep(100);
		selectDropDown("ctl00_ContentZone_group_cmb_dropdown");
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_txt_numberDoc_box_data")).sendKeys(dniLetra("DNI",ranNumbr(10000000,50000000)));		
		Thread.sleep(100);		
		elementClick("ctl00_ContentZone_btnEditPan");
		driver.findElement(By.id("ctl00_ContentZone_txt_pan")).sendKeys(ranNumbr(100000000,999999999)+"");
		Thread.sleep(100);		
		elementClick("ctl00_ContentZone_btnEditPanISO");
		driver.findElement(By.id("ctl00_ContentZone_txt_paniso")).sendKeys(ranNumbr(100000000,999999999)+"");
		Thread.sleep(100);		
		driver.findElement(By.id("ctl00_ContentZone_dt_birth_box_date")).clear();
		driver.findElement(By.id("ctl00_ContentZone_dt_birth_box_date")).sendKeys(dateSel(1972,1979));
		Thread.sleep(1000);
		WebElement operatorGroup = new Select (driver.findElement(By.id("ctl00_ContentZone_group_cmb_dropdown"))).getFirstSelectedOption();
		String operatorG = operatorGroup.getText();				
		driver.findElement(By.id("ctl00_ContentZone_password_box_data")).sendKeys("00001");
		Thread.sleep(100);
		driver.findElement(By.id("ctl00_ContentZone_password2_box_data")).sendKeys("00001");
		Thread.sleep(5000);
		takeScreenShot("E:\\Selenium\\","allfilleddata"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_crearOperadores\\attachments\\","allfilleddata.jpg");
		elementClick("ctl00_ButtonsZone_lbl_btnSubmit");
		Thread.sleep(2000);
		takeScreenShot("E:\\Selenium\\","userCreated"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_crearOperadores\\attachments\\","userCreated.jpg");		
		Thread.sleep(1000);
		tableResult = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
		userResults = tableResult.findElements(By.tagName("tr"));
		if (userResults.size()<14){
			for (int i = 1; i <= userResults.size(); i++){
				if (i == userResults.size()){
					lastcreated = driver.findElement(By.xpath("//table[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[2]")).getText();
				}	
			}
		}else{
			elementClick("ctl00_ContentZone_tablePager_BtnLast");
			Thread.sleep(1500);
            tableResult = driver.findElement(By.id("ctl00_ContentZone_TblResults"));
            userResults = tableResult.findElements(By.tagName("tr"));
            lastcreated = driver.findElement(By.xpath("//table[@id='ctl00_ContentZone_TblResults']/tbody/tr[" + userResults.size() + "]/td[2]")).getText();		
            for (int i = 1; i <= userResults.size(); i++){
            		if (i == userResults.size()){
            			lastcreated = driver.findElement(By.xpath("//table[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td[2]")).getText();
				}	
			}
		}
		
		elementClick("ctl00_ButtonsZone_BtnDownload_IB_Label");
		Thread.sleep(1000);
		elementClick("popup_ok");
		Thread.sleep(5000);
		String enviarViaLbl = driver.findElement(By.xpath("//div[@class='toast-message']")).getText();				
		if (enviarViaLbl.contains("OK")){
			enviarViaVer = enviarViaLbl.substring(41,44);
			System.out.println("La telecarga de Operadores se ha enviado a Vía con la versión "+enviarViaVer);
		}else{
			fail("Hay un error en enviar telecargas a vía");
		}
		elementClick("ctl00_BtnLogOut");
		Thread.sleep(1000);
		if (isAlertPresent()){
			driver.switchTo().alert().accept();
		}
		Thread.sleep(1000);
		loginPage(lastcreated,"00001");		
		String HMver = BOVersion.substring(1);
		if (HMver.length()>18){
			HMver = BOVersion.substring(17);
			BOVersion=BOVersion.substring(0,16);
		}else{
			HMver = "<HM is not running>";
			BOVersion=BOVersion.substring(0,16);
		}	
		System.out.println("Se ha Creado el operador "+lastcreated+" con la contraseaña: 00001 en el grupo de "+operatorG.substring(04));
		System.out.println("Se ha probado en la versión del BO Host: " + BOVersion+" y Host Manager: "+HMver);
		takeScreenShot("E:\\Selenium\\","userCreatedscreenHome"+timet+".jpg");
		takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_crearOperadores\\attachments\\","userCreatedscreenHome.jpg");
		Thread.sleep(10000);
		String connectionUrlPlaza = "jdbc:sqlserver://172.18.130.188:1433;DataBaseName=R3R5CIRALSA_QA_TOLLPLAZA"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(connectionUrlPlaza, "sa", "lediscet");
		stmt = conn.createStatement();
		queryString = "select version, filename from dbo.atable where tabletype='operators' and version='"+enviarViaVer+"'";
		rs = stmt.executeQuery(queryString);
		String [] transaction = new String[2]; 			   
		while (rs.next()) {
			for (i = 0; i < transaction.length;i++){
				transaction[0]= rs.getString("version");
				transaction[1] = rs.getString("filename");
				transactions.add(transaction[i]);
			}
		}		
		if (transaction[0]==null){
			fail("La Telecarga de Operadores no ha bajado a Plaza");
		}else{
			System.out.println("La telecarga de operadores con la version: "+transactions.get(0)+" ha bajado a la plaza con el nombre de archivo: "+transactions.get(1));
		}
	}catch(Exception e){
		System.out.println(e.getMessage());
		fail(e.getMessage());
	}
		
}
		public static boolean isAlertPresent() throws Exception{
			try{
				driver.switchTo().alert();
				return true;
		}catch (Exception e){
			return false;
		}
	}
}





