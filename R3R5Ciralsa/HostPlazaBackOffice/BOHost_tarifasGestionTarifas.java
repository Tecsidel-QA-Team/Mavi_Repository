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

public class BOHost_tarifasGestionTarifas extends Settingsfields_File {		
		private static String [] tarifasOption = {"Crear Copia", "Editar Tarifas","Editar Agrupación tarifas","Eliminar Tarifas"};
		private static String [] tarifasOptionOperation = {"Crear", "Modificar", "Eliminar", "Edición Múltiple"};
		private static int opSel;
		private static String optionSelected;
		public static String fechaAct;
		
		
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
		public void gestionTarifas() throws Exception {			
			Actions action = new Actions(driver);		
			borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\");
			try{
				driver.get(BoHostUrl);
				takeScreenShot("E:\\Selenium\\","loginBOPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","loginBOPage.jpeg");
				loginPage("00001","00001");
				takeScreenShot("E:\\Selenium\\","homeBOHostPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","homeBOHostPage.jpeg");
				BOVersion = driver.findElement(By.id("ctl00_statusRight")).getText();
				Thread.sleep(2000);					
				action.clickAndHold(driver.findElement(By.linkText("Configuración sistema"))).build().perform();
				Thread.sleep(200);
				action.clickAndHold(driver.findElement(By.linkText("Tarifas & moneda"))).build().perform();
				Thread.sleep(1000);
				driver.findElement(By.linkText("Gestión de tarifas")).click();				
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","gestiónTarifasPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","gestiónTarifasPage.jpeg");
				String HMver = BOVersion.substring(1);
				if (HMver.length()>18){
					HMver = BOVersion.substring(18);
					BOVersion = BOVersion.substring(0,16);
				}else{
					HMver = "<HM is not running>";
					BOVersion = BOVersion.substring(0,16);
				}			
				opSel = 0; ranNumbr(0,3);
				switch (tarifasOption[opSel]){				
					case "Crear Copia" :				SendKeys("ctl00_ContentZone_dt_newTime_box_date", dateSel(2017,2018));
														Thread.sleep(100);
														SendKeys("ctl00_ContentZone_dt_newTime_box_hour", hourFormat(0,23,0,59));
														Thread.sleep(100);
														elementClick("ctl00_ContentZone_ButtonClone");
														Thread.sleep(2000);
														if (driver.getPageSource().contains("Por favor, introduzca una fecha futura") || driver.getPageSource().contains("Ya existe un grupo de tarifa para la fecha dada")){
															String errorText = driver.findElement(By.xpath("//*[@id='toast-container']/div/div")).getText();
															System.out.println("No se puede crear la Tarifa debido a: "+errorText);
															fail("No se puede crear la Tarifa debido a: "+errorText);
														}										
														Thread.sleep(1000);														
														fechaAct = driver.findElement(By.id("ctl00_ContentZone_txt_ActivationTime_box_data")).getAttribute("value");
														crear_EditarTarifas();														
														switch (optionSelected){
															case "Crear":						System.out.println("Se ha creado una Tarifa correctamente con fecha de activación: "+fechaAct);
																								break;
															case "Modificar":					System.out.println("Se ha modificado una Tarifa correctamente con fecha de activación: "+fechaAct);
																								break;
															case "Eliminar":					System.out.println("Se ha eliminado una Tarifa correctamente con fecha de: "+fechaAct);
																								break;
															case "Edición Múltiple":			System.out.println("Se ha hecho una edición múltiple a todas las tarifas con fecha de activación: "+fechaAct);
																								break;
														}
														Thread.sleep(2000);
														break;
					case "Editar Tarifas":				selectDropDown("ctl00_ContentZone_CmbDates");
														Thread.sleep(500);
														elementClick("ctl00_ContentZone_BtnEdit");
														Thread.sleep(1000);
														if (driver.getPageSource().contains("Solo se pueden editar tarifas futuras")){
															String errorText = driver.findElement(By.xpath("//*[@id='toast-container']/div/div")).getText();
															System.out.println("No se puede editar la Tarifa debido a: "+errorText);
															fail("No se puede editar la Tarifa debido a: "+errorText);
														}
														fechaAct = driver.findElement(By.id("ctl00_ContentZone_txt_ActivationTime_box_data")).getAttribute("value");														
														crear_EditarTarifas();
														switch (optionSelected){
														case "Crear":						System.out.println("Se ha editado un rango de Tarifa con la creación de una Tarifa correctamente de fecha de activación: "+fechaAct);
																							break;
														case "Modificar":					System.out.println("Se ha editado una rango de Tarifa con la modificación de una Tarifa correctamente de fecha de activación: "+fechaAct);
																							break;
														case "Eliminar":					System.out.println("Se ha editado un rango de  Tarifa eliminando una Tarifa correctamente de fecha de: "+fechaAct);
																							break;
														case "Edición Múltiple":			System.out.println("Se ha editado un rango de Tarifa con una edición múltiple a todas las tarifas de fecha de activación: "+fechaAct);
																							break;
														}			
														Thread.sleep(2000);
														break;
					case "Editar Agrupación tarifas": 	selectDropDown("ctl00_ContentZone_CmbDates");
														Thread.sleep(500);
														elementClick("ctl00_ContentZone_BtnEditFareGroup");
														Thread.sleep(1000);
														if (driver.getPageSource().contains("Solo se pueden editar tarifas futuras")){
															String errorText = driver.findElement(By.xpath("//*[@id='toast-container']/div/div")).getText();
															System.out.println("No se puede editar la Tarifa debido a: "+errorText);
															fail("No se puede editar la Tarifa debido a: "+errorText);
														}
														fechaAct = driver.findElement(By.id("ctl00_ContentZone_txt_ActivationTime_box_data")).getAttribute("value");														
														editar_AgrupacionTarifas();
														switch (optionSelected){
															case "Crear":						System.out.println("Se ha editado un rango de Tarifa con la creación de una Tarifa correctamente de fecha de activación: "+fechaAct);
																								break;
															case "Modificar":					System.out.println("Se ha editado una rango de Tarifa con la modificación de una Tarifa correctamente de fecha de activación: "+fechaAct);
																								break;
															case "Eliminar":					System.out.println("Se ha editado un rango de  Tarifa eliminando una Tarifa correctamente de fecha de: "+fechaAct);
																								break;
														}
														Thread.sleep(2000);
														break;																
															
					case "Eliminar Tarifas":			selectDropDown("ctl00_ContentZone_CmbDates");
														Thread.sleep(1000);
														WebElement itemSelected = new Select(driver.findElement(By.id("ctl00_ContentZone_CmbDates"))).getFirstSelectedOption();
														fechaAct = itemSelected.getText();
														elementClick("ctl00_ContentZone_BtnDelete");
														Thread.sleep(1000);
														if (driver.getPageSource().contains("Solo se pueden eliminar tarifas futuras")){
															String errorText = driver.findElement(By.xpath("//*[@id='toast-container']/div/div")).getText();
															System.out.println("No se puede eliminar la Tarifa debido a: "+errorText);
															fail("No se puede eliminar la Tarifa debido a: "+errorText);
														}														
														elementClick("popup_ok");
														Thread.sleep(5000);
														System.out.println("Se ha eliminado una tarifa con fecha de activación: "+fechaAct);
														break;
				}
				
				System.out.println("Se ha probado en la versión del BO Host: " + BOVersion+" y Host Manager: "+HMver);
				
			}catch (Exception e){
				e.printStackTrace();
				fail(e.getMessage());
			}
		}	
		
		public static void crear_EditarTarifas() throws Exception{					 
			try{
				takeScreenShot("E:\\Selenium\\","creacionTarifasPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","creacionTarifasPage.jpeg");
				opSel = ranNumbr(0,3);
				optionSelected = tarifasOptionOperation[opSel];
				WebElement tableRes = driver.findElement(By.id("ctl00_ContentZone_TblResults"));					
				List <WebElement> tableResult = tableRes.findElements(By.tagName("tr"));
				
				if (optionSelected.equals("Edición Múltiple")){
					elementClick("ctl00_ButtonsZone_BtnMultipleEdition_IB_Label");
					Thread.sleep(1000);
					opSel = ranNumbr(2,tableResult.size());
					for (int i=2; i<=tableResult.size();i++){
						WebElement tableResu = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]"));
						List <WebElement> columNumber = tableResu.findElements(By.tagName("td"));
						int value1 = ranNumbr(1000,10000);
						int value2 = ranNumbr(1000,2500)/100;
						int value3 = ranNumbr(1000,10000);
						int value4 = ranNumbr(1000,2500)/100;
						for (int a=9; a<columNumber.size();a++){
							String fieldName = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td["+a+"]/input[1]")).getAttribute("id");
							String fieldName1 = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+i+"]/td["+a+"]/input[2]")).getAttribute("id");							
							if (a==9){
								elementClick(fieldName);
								SendKeys(fieldName1,value1+"");								
							}
							Thread.sleep(200);
							if (a==10){
								elementClick(fieldName);
								SendKeys(fieldName1, value1*value2/100+"");
							}
							Thread.sleep(200);
							if (a==11){
								elementClick(fieldName);
								SendKeys(fieldName1,value2+"00");
							}
							Thread.sleep(200);
							if (a==12){
								elementClick(fieldName);
								SendKeys(fieldName1,value3+"");
							}
							Thread.sleep(200);
							if (a==13){
								elementClick(fieldName);
								SendKeys(fieldName1, value3*value4/100+"");
							}
							Thread.sleep(200);
							if (a==14){
								elementClick(fieldName);
								SendKeys(fieldName1,value4+"00");
							}
							
						}
											
					}
				
						Thread.sleep(5000);
						elementClick("ctl00_ButtonsZone_BtnMultipleEdition_IB_Label");
						Thread.sleep(2000);
						return;
				}	
						
				if (optionSelected.equals("Modificar") || optionSelected.equals("Eliminar")){ 					
					opSel = ranNumbr(2,tableResult.size());
					String selId = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+opSel+"]/td[1]/input")).getAttribute("id");					
					elementClick(selId);
					Thread.sleep(2000);;
				}	
				if (optionSelected.equals("Eliminar")){
					Thread.sleep(1000);
					elementClick("ctl00_ContentZone_BtnDelete");
					Thread.sleep(1000);
					elementClick("popup_ok");
					Thread.sleep(1000);
					return;
				}
				if (optionSelected.equals("Modificar")){
					elementClick("ctl00_ContentZone_BtnModify");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","creacionTarifas3Page"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","creacionTarifas3Page.jpeg");
					if (driver.findElement(By.id("ctl00_ContentZone_ChkOrigTollCompany")).isSelected()){
						elementClick("ctl00_ContentZone_ChkOrigTollCompany");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkOrigCompany");
						Thread.sleep(500);
						selectDropDown("ctl00_ContentZone_CmbOrigTollCompany");
						Thread.sleep(500);
						if (driver.findElement(By.id("ctl00_ContentZone_ChkOrigPlaza")).isSelected()){
							elementClick("ctl00_ContentZone_ChkOrigPlaza");
							Thread.sleep(500);
						}else{
							elementClick("ctl00_ContentZone_ChkOrigPlaza");
							Thread.sleep(500);
							selectDropDown("ctl00_ContentZone_CmbOrigPlaza");
							Thread.sleep(500);
						}
					}
					
					if (driver.findElement(By.id("ctl00_ContentZone_ChkCompany")).isSelected()){
						elementClick("ctl00_ContentZone_ChkCompany");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkCompany");
						Thread.sleep(500);
						selectDropDown("ctl00_ContentZone_CmbCompany");
						if (driver.findElement(By.id("ctl00_ContentZone_ChkPlaza")).isSelected()){
							elementClick("ctl00_ContentZone_ChkPlaza");
							Thread.sleep(500);
						}else{
							elementClick("ctl00_ContentZone_ChkPlaza");
							Thread.sleep(500);
							selectDropDown("ctl00_ContentZone_CmbPlaza");
							Thread.sleep(500);
						}
					}
					if (driver.findElement(By.id("ctl00_ContentZone_ChkLaneDir")).isSelected()){
						elementClick("ctl00_ContentZone_ChkLaneDir");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkLaneDir");
						Thread.sleep(500);
						selectDropDown("ctl00_ContentZone_CmbLaneDir");
						Thread.sleep(500);
					}
					if (driver.findElement(By.id("ctl00_ContentZone_ChkClass")).isSelected()){
						elementClick("ctl00_ContentZone_ChkClass");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkClass");
						Thread.sleep(500);
						selectDropDown("ctl00_ContentZone_CmbClass");
						Thread.sleep(500);
					}
					if (driver.findElement(By.id("ctl00_ContentZone_ChkFareAggrupation")).isSelected()){
						elementClick("ctl00_ContentZone_ChkFareAggrupation");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkFareAggrupation");
						Thread.sleep(500);
						selectDropDown("ctl00_ContentZone_CmbFareAggrupation");
						Thread.sleep(500);
					}
					
				}
				
				if (optionSelected.equals("Crear")){					
					elementClick("ctl00_ContentZone_BtnCreate");
					Thread.sleep(2000);				
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkOrigCompany");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbOrigTollCompany");
						Thread.sleep(300);
						elementClick("ctl00_ContentZone_ChkOrigPlaza");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbOrigPlaza");
						Thread.sleep(300);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkCompany");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbCompany");
						Thread.sleep(300);
						elementClick("ctl00_ContentZone_ChkPlaza");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbPlaza");
						Thread.sleep(300);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkLaneDir");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbLaneDir");
						Thread.sleep(300);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkClass");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbClass");
						Thread.sleep(300);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkFareAggrupation");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbFareAggrupation");
						Thread.sleep(1000);
					}
				}
				int value1 = ranNumbr(1000,10000);
				int value2 = ranNumbr(1000,2500)/100;
				int value3 = ranNumbr(1000,10000);
				int value4 = ranNumbr(1000,2500)/100;		
				elementClick("ctl00_ContentZone_BoxAmount_txt_formated");
				SendKeys("ctl00_ContentZone_BoxAmount_txt_plain",value1+"");
				Thread.sleep(100);
				elementClick("ctl00_ContentZone_BoxVat_txt_formated");
				SendKeys("ctl00_ContentZone_BoxVat_txt_plain",value1*value2/100+"");
				Thread.sleep(100);
				elementClick("ctl00_ContentZone_BoxVatPct_txt_formated");
				SendKeys("ctl00_ContentZone_BoxVatPct_txt_plain",value2+"00");
				Thread.sleep(1000);
				elementClick("ctl00_ContentZone_BoxExternalAmount_txt_formated");
				SendKeys("ctl00_ContentZone_BoxExternalAmount_txt_plain",value3+"");
				Thread.sleep(100);
				elementClick("ctl00_ContentZone_BoxExternalVat_txt_formated");
				SendKeys("ctl00_ContentZone_BoxExternalVat_txt_plain",value3*value4/100+"");
				Thread.sleep(100);
				elementClick("ctl00_ContentZone_BoxExternalVatPct_txt_formated");
				SendKeys("ctl00_ContentZone_BoxExternalVatPct_txt_plain",value4+"00");
				Thread.sleep(100);
				SendKeys("ctl00_ContentZone_BoxMaxRouteTime",ranNumbr(1000,10000)+"");
				Thread.sleep(2000);
				takeScreenShot("E:\\Selenium\\","creacionTarifasFilledPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","creacionTarifasFilledPage.jpeg");
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");
				Thread.sleep(3000);			
				
			}catch (Exception e){
				e.printStackTrace();
				fail(e.getMessage());
			}
			
		}
		
		public static void editar_AgrupacionTarifas() throws Exception{					 
			try{
				takeScreenShot("E:\\Selenium\\","editar_AgrupacionTarifas"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","editar_AgrupacionTarifas.jpeg");
				String recordsList = driver.findElement(By.id("ctl00_ContentZone_tablePager_LblCounter")).getText();				
				if (recordsList.length()>25){
					recordsList = recordsList.substring(25);
				}else{
					recordsList = recordsList.substring(24);
				}
				int firstRecordList = Integer.parseInt(recordsList);
				opSel = ranNumbr(0,2);
				optionSelected = tarifasOptionOperation[opSel];
				WebElement tableRes = driver.findElement(By.id("ctl00_ContentZone_TblResults"));					
				List <WebElement> tableResult = tableRes.findElements(By.tagName("tr"));								
						
				if (optionSelected.equals("Modificar") || optionSelected.equals("Eliminar")){ 					
					opSel = ranNumbr(2,tableResult.size());
					String selId = driver.findElement(By.xpath("//*[@id='ctl00_ContentZone_TblResults']/tbody/tr["+opSel+"]/td[1]/input")).getAttribute("id");					
					elementClick(selId);
					Thread.sleep(2000);;
				}	
				if (optionSelected.equals("Eliminar")){
					Thread.sleep(1000);
					elementClick("ctl00_ContentZone_btnPrevDelete");
					Thread.sleep(1000);
					elementClick("popup_ok");					
					Thread.sleep(1000);
					takeScreenShot("E:\\Selenium\\","ediciónAgrupaTarifasPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","ediciónAgrupaTarifasPage.jpeg");
					return;
				}
				if (optionSelected.equals("Modificar")){
					elementClick("ctl00_ContentZone_btnPrevModify");
					Thread.sleep(2000);
					takeScreenShot("E:\\Selenium\\","ediciónAgrupaTarifasPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","ediciónAgrupaTarifasPage.jpeg");
					if (driver.findElement(By.id("ctl00_ContentZone_ChkOriginTollCompany")).isSelected()){
						elementClick("ctl00_ContentZone_ChkOriginTollCompany");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkOriginTollCompany");
						Thread.sleep(500);
						selectDropDown("ctl00_ContentZone_CmbOriginTollCompany");
						Thread.sleep(500);
						if (driver.findElement(By.id("ctl00_ContentZone_ChkOriginPlaza")).isSelected()){
							elementClick("ctl00_ContentZone_ChkOriginPlaza");
							Thread.sleep(500);
						}else{
							elementClick("ctl00_ContentZone_ChkOriginPlaza");
							Thread.sleep(500);
							selectDropDown("ctl00_ContentZone_CmbOriginPlaza");
							Thread.sleep(500);
						}
					}
					
					if (driver.findElement(By.id("ctl00_ContentZone_ChkDestinationTollCompany")).isSelected()){
						elementClick("ctl00_ContentZone_ChkDestinationTollCompany");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkDestinationTollCompany");
						Thread.sleep(500);
						selectDropDown("ctl00_ContentZone_CmbDestinationTollCompany");
						if (driver.findElement(By.id("ctl00_ContentZone_ChkDestinationPlaza")).isSelected()){
							elementClick("ctl00_ContentZone_ChkDestinationPlaza");
							Thread.sleep(500);
						}else{
							elementClick("ctl00_ContentZone_ChkDestinationPlaza");
							Thread.sleep(500);
							selectDropDown("ctl00_ContentZone_CmbDestinationPlaza");
							Thread.sleep(500);
						}
					}
					if (driver.findElement(By.id("ctl00_ContentZone_ChkLaneDir")).isSelected()){
						elementClick("ctl00_ContentZone_ChkLaneDir");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkLaneDir");
						Thread.sleep(500);
						selectDropDown("ctl00_ContentZone_CmbLaneDir");
						Thread.sleep(500);
					}
					if (driver.findElement(By.id("ctl00_ContentZone_ChkWeekDay")).isSelected()){
						elementClick("ctl00_ContentZone_ChkWeekDay");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkWeekDay");
						Thread.sleep(500);
						selectDropDown("ctl00_ContentZone_CmbWeekDay");
						Thread.sleep(500);
					}
					if (driver.findElement(By.id("ctl00_ContentZone_ChkDaytype")).isSelected()){
						elementClick("ctl00_ContentZone_ChkDaytype");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkDaytype");
						Thread.sleep(500);						
					}
					if (driver.findElement(By.id("ctl00_ContentZone_ChkStartTime")).isSelected()){
						elementClick("ctl00_ContentZone_ChkStartTime");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkStartTime");
						Thread.sleep(500);						
						SendKeys("ctl00_ContentZone_BoxStartTime",hourFormat(0,23,0,59)+"");
						Thread.sleep(500);						
					}
					if (driver.findElement(By.id("ctl00_ContentZone_ChkEndTime")).isSelected()){
						elementClick("ctl00_ContentZone_ChkEndTime");
						Thread.sleep(500);
					}else{
						elementClick("ctl00_ContentZone_ChkEndTime");
						Thread.sleep(500);						
						SendKeys("ctl00_ContentZone_BoxEndTime",hourFormat(0,23,0,59)+"");
						Thread.sleep(500);						
					}
					
				}
				
				if (optionSelected.equals("Crear")){					
					elementClick("ctl00_ContentZone_btnPrevCreate");
					Thread.sleep(2000);				
					takeScreenShot("E:\\Selenium\\","ediciónAgrupaTarifasPage"+timet+".jpeg");
					takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","ediciónAgrupaTarifasPage.jpeg");
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkOriginTollCompany");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbOriginTollCompany");
						Thread.sleep(300);
						elementClick("ctl00_ContentZone_ChkOriginPlaza");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbOriginPlaza");
						Thread.sleep(300);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkDestinationTollCompany");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbDestinationTollCompany");
						Thread.sleep(300);
						elementClick("ctl00_ContentZone_ChkDestinationPlaza");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbDestinationPlaza");
						Thread.sleep(300);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkLaneDir");
						Thread.sleep(100);
						selectDropDown("ctl00_ContentZone_CmbLaneDir");
						Thread.sleep(300);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkWeekDay");
						Thread.sleep(500);
						selectDropDown("ctl00_ContentZone_CmbWeekDay");
						Thread.sleep(500);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkDaytype");
						Thread.sleep(500);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkStartTime");
						Thread.sleep(500);						
						SendKeys("ctl00_ContentZone_BoxStartTime",hourFormat(0,23,0,59)+"");
						Thread.sleep(500);
					}
					if (ranNumbr(0,1)>0){
						elementClick("ctl00_ContentZone_ChkEndTime");
						Thread.sleep(500);						
						SendKeys("ctl00_ContentZone_BoxEndTime",hourFormat(0,23,0,59)+"");
						Thread.sleep(500);
					}
				}
				Thread.sleep(100);
				selectDropDown("ctl00_ContentZone_CmbFareAggrupation");
				Thread.sleep(2000);				
				takeScreenShot("E:\\Selenium\\","ediciónAgrupaTarifasFilledPage"+timet+".jpeg");
				takeScreenShot("E:\\workspace\\Mavi_Repository\\BOHost_tarifasgestionTarifas\\attachments\\","ediciónAgrupaTarifasFilledPage.jpeg");
				elementClick("ctl00_ButtonsZone_BtnSubmit_IB_Label");				
				Thread.sleep(2000);
				if (optionSelected.equals("Crear")){
					recordsList = driver.findElement(By.id("ctl00_ContentZone_tablePager_LblCounter")).getText();
					if (recordsList.length()>25){
						recordsList = recordsList.substring(25);
					}else{
						recordsList = recordsList.substring(24);
					}
					int secondRecordList = Integer.parseInt(recordsList);
					if (secondRecordList == firstRecordList){
						System.out.println("No se ha podido crear una tarifa en Agrupación de Tarifa, favor hacerlo manualmente y averiguar el error");
						fail("No se ha podido crear una tarifa en Agrupación de Tarifa, favor hacerlo manualmente y averiguar el error");
					}
				}
				Thread.sleep(2000);
				
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
