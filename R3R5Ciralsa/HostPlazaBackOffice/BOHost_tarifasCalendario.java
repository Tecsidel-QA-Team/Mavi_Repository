package HostPlazaBackOffice;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


import R3R5CiralsaSettings.Settingsfields_File;

public class BOHost_tarifasCalendario extends Settingsfields_File {		
		private static String [] calenSel = {"Crear","Modificar", "Eliminar", "Copiar"};
		private static Statement stmt;		
		private static ResultSet rs;
		private static int yearSel;
		private static String dateDesde;
		private static String dateHasta=" ";
		private static String calReSelected;
		private static String queryString;
		private static String errorText;
		private static boolean notAction = false;
		private static int opSel;
		private static ArrayList<String> calendarRecords = new ArrayList<String>();	
		
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
		public void calendarioInit() throws Exception {
			Actions action = new Actions(driver);
			
			borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_tarifasCalendario\\attachments\\");
			try{
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasCalendario\\attachments\\","loginBOPage.jpeg");
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasCalendario\\attachments\\","homeBOHostPage.jpeg");
				BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
				String HMver = BOVersion.substring(1);				
				if (HMver.length()>18){
					HMver = BOVersion.substring(17);
					BOVersion=BOVersion.substring(0,16);
				}else{
					HMver = "<HM is not running>";
					BOVersion=BOVersion.substring(0,16);
				}	
				Thread.sleep(2000);					
				action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
				Thread.sleep(200);
				action.clickAndHold(driver.findElement(By.linkText("Tarifas & moneda"))).build().perform();
				Thread.sleep(1000);
				driver.findElement(By.linkText("Calendario")).click();
				Thread.sleep(1000);
				takeScreenShot("E:\\Selenium\\","CalendarioPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasCalendario\\attachments\\","CalendarioPage.jpeg");
				opSel = ranNumbr(0,3);
				switch (calenSel[opSel]){
					case "Crear":				gestionTarifa_Calendario();														
												if (notAction){
													System.out.println("No se puede crear Calendario para Tarifas debido a: "+errorText);
													fail("No se puede crear Calendario para Tarifas debido a: "+errorText);
												}
												if (dateHasta.equals(" ")){
													System.out.println("Se ha Creado el Calendario Festivo de Tarifas correctamente con fecha: "+dateDesde+" hasta "+dateDesde);
													}else{
														System.out.println("Se ha Creado el Calendario Festivo de Tarifas correctamente con fecha desde: "+dateDesde+" hasta: "+dateHasta);
													}																								
												break;
					case "Modificar":			gestionTarifa_Calendario();
												if (notAction){
													System.out.println("No se puede Modificar la fecha del Calendario: "+calReSelected+" debido a: "+errorText);
													fail("No se puede Modificar la fecha del Calendario: "+calReSelected+" debido a: "+errorText);
												}
												System.out.println("Se ha modificado la fecha del Calendario: "+calReSelected+" a esta nueva fecha:"+dateDesde+ "y con nuevos datos correctamente");
												break;												
					case "Eliminar":			gestionTarifa_Calendario();			
												if (notAction){
													System.out.println("No se puede eliminar la fecha del Calendario: "+calReSelected+"debido a: "+errorText);
													fail("No se puede eliminar la fecha del Calendario: "+calReSelected+" debido a: "+errorText);
												
													}
												System.out.println("Se ha eliminado la fecha del Calendario: "+calReSelected+" correctamente");
												break;
					case "Copiar":				gestionTarifa_Calendario();
												if (notAction){
													System.out.println("No se ha podido copiar el Calendario del año anterior: "+(yearSel-1) +" a este año "+yearSel+" debido a: "+errorText.substring(2));
													fail("No se ha podido copiar el Calendario del año anterior: "+(yearSel-1) +" a este año "+yearSel+" debido a: "+errorText.substring(2));													
												}
												System.out.println("Se han copiado las fechas del Calendario anterior: "+(yearSel-1)+" a este año: "+yearSel+" correctamente");
												break;
				}
				System.out.println("Se ha probado en la versión del BO Host: " + BOVersion +" y Host Manager: "+HMver);
				
			}catch (Exception e){
				e.printStackTrace();
				fail(e.getMessage());
			}
		}
		
		public static void gestionTarifa_Calendario() throws Exception{
				Thread.sleep(1000);
			try{	
				if (calenSel[opSel].equals("Modificar") || calenSel[opSel].equals("Eliminar")){ 
					String connectionUrlHost = "jdbc:sqlserver://172.18.130.188:1433;DataBaseName=R3R5CIRALSA_QA_TOLLHOST"; 
					stmt = null;
					rs = null;			    
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection conn = DriverManager.getConnection(connectionUrlHost, "sa", "lediscet");
					stmt = conn.createStatement();
					queryString = "select * from dbo.acalendar ORDER BY DAYFROM ASC";
					rs = stmt.executeQuery(queryString);
					String [] calenQuery = new String[1];
					while  (rs.next()){
						for (int i = 0; i<calenQuery.length;i++){
							calenQuery[0] = rs.getString("DAYFROM");
							calendarRecords.add(calenQuery[i]);
						}
					}
					int selcRan = ranNumbr(0,calendarRecords.size()-1);
					String calenSelected = calendarRecords.get(selcRan);
					int calSelN = Integer.parseInt(calenSelected.substring(0,4));
					calReSelected = calenSelected.substring(6,8).concat("/").concat(calenSelected.substring(4,6)).concat("/").concat(calenSelected.substring(0,4));					
					String currentCalWindow = driver.findElement(By.id("ctl00_ContentZone_LblYear")).getText();			    					
					do {
						currentCalWindow = driver.findElement(By.id("ctl00_ContentZone_LblYear")).getText();
						if (calSelN<Integer.parseInt(currentCalWindow)){
							elementClick("ctl00_ContentZone_BtnPrev");					
						}
						if (calSelN>Integer.parseInt(currentCalWindow)){
							elementClick("ctl00_ContentZone_BtnNext");						
						}
						if (Integer.parseInt(currentCalWindow)==calSelN){
							Thread.sleep(200);						
							elementClick("ctl00_ContentZone_radio"+calenSelected);												
							Thread.sleep(1000);			    		
							break;			    		
						}
						Thread.sleep(300);
					} while (Integer.parseInt(currentCalWindow)!=calSelN);
				}
				
				Thread.sleep(1000);				
				if (calenSel[opSel].equals("Eliminar")){
					Thread.sleep(1000);
					elementClick("ctl00_ContentZone_BtnDelete");
					Thread.sleep(1000);
					if (driver.getPageSource().contains("Solo se pueden eliminar días futuros")){
						errorText = driver.findElement(By.id("toast-message")).getText();
						notAction = true;
						return;
					}else{
						elementClick("popup_ok");
						Thread.sleep(2000);
					}
					return;
				}
				
				if (calenSel[opSel].equals("Copiar")){
					yearSel = ranNumbr(2018,2030);
					String currentCalWindow = driver.findElement(By.id("ctl00_ContentZone_LblYear")).getText();								    					
					do {
						currentCalWindow = driver.findElement(By.id("ctl00_ContentZone_LblYear")).getText();
						if (Integer.parseInt(currentCalWindow)==yearSel){
							Thread.sleep(200);						
							elementClick("ctl00_ContentZone_BtnCopyDays");												
							Thread.sleep(1000);			    		
							elementClick("popup_ok");
							Thread.sleep(2000);
							if (driver.getPageSource().contains("No se pueden copiar los días porque el año") || driver.getPageSource().contains("No existen días a copiar para el año")){
								errorText = driver.findElement(By.id("toast-container")).getText();
								notAction = true;
								return;
							}
							break;
						}else{
							elementClick("ctl00_ContentZone_BtnNext");
						}
						Thread.sleep(400);
					}while(Integer.parseInt(currentCalWindow)!=yearSel || yearSel==2030);					
					return;
				}
				
				if (calenSel[opSel].equals("Crear")|| calenSel[opSel].equals("Modificar")){
					if (calenSel[opSel].equals("Crear")){
						elementClick("ctl00_ContentZone_BtnCreate");
						Thread.sleep(1000);
					}
					if (calenSel[opSel].equals("Modificar")){
						elementClick("ctl00_ContentZone_BtnModify");
						Thread.sleep(1000);
						if (driver.getPageSource().contains("Solo se pueden modificar días futuros") || driver.getPageSource().contains("El rango de fechas introducido se solapa con")){
							errorText = driver.findElement(By.id("toast-message")).getText();
							notAction = true;
							return;
						}						
					}
				
					SendKeys("ctl00_ContentZone_dt_from_box_date", dateSel(2017,2020));
					Thread.sleep(500);
					dateDesde = driver.findElement(By.id("ctl00_ContentZone_dt_from_box_date")).getAttribute("value"); 				
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkTo");
						Thread.sleep(500);
						SendKeys("ctl00_ContentZone_dt_to_box_date", dateSel(2018,2025));
						Thread.sleep(500);
						dateHasta = driver.findElement(By.id("ctl00_ContentZone_dt_to_box_date")).getAttribute("value");
					}
					takeScreenShot("E:\\Selenium\\","CalendarioFilledPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasCalendario\\attachments\\","CalendarioFilledPage.jpeg");
					Thread.sleep(500);
					elementClick("ctl00_ContentZone_BtnSubmit");
					Thread.sleep(1000);				
					if (driver.getPageSource().contains("Por favor, introduzca una fecha futura") || driver.getPageSource().contains("Fecha 'hasta' no puede ser anterior a 'desde'") || driver.getPageSource().contains("El rango de fecha introducido se solapa") || driver.getPageSource().contains("El rango de fechas introducido se solapa con")){
						notAction = true;
						errorText = driver.findElement(By.xpath("//*[@id='toast-container']/div/div")).getText();
						return;
					}
				}
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
		