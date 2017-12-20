package BackOffice;

import static org.junit.Assert.*;



import org.openqa.selenium.chrome.ChromeDriver;


import BackOffice.senacFieldsConfiguration;
import java.awt.datatransfer.Clipboard;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import java.awt.event.KeyEvent;




public class crearIncidenciaRedMine extends senacFieldsConfiguration{
		public static int i = 0;
		public static String [] values = null;
		public static List<String> redL = new ArrayList<String>();
		public static final String [] testers = new String[]{"Pilar Bonilla", "Mavi Garrido", "Francisco Castro"}; 

		public static void createIncidence() throws Exception{
			setUp();
			ReqRedmine();
			tearDown();
	
		}
 

		public static void setUp() throws Exception{
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
 		
 
			public static void ReqRedmine() throws Exception{			
			Thread.sleep(1000);
			driver.get("http://redmine.tecsidel.es");
			Thread.sleep(500);
			driver.findElement(By.id("username")).sendKeys("fgn");
			driver.findElement(By.id("password")).sendKeys("Demo.1234");
			driver.findElement(By.name("login")).click();
			Thread.sleep(2000);
			new Select(driver.findElement(By.id("project_quick_jump_box"))).selectByVisibleText(projectSel);
			Thread.sleep(1000);
			driver.findElement(By.linkText("Nueva petición")).click();
			Thread.sleep(1000);
			new Select(driver.findElement(By.id("issue_tracker_id"))).selectByVisibleText("IncidenceQA");
			Thread.sleep(1500);
			driver.findElement(By.id("issue_subject")).sendKeys(additionalTitle+errorLev);
			Thread.sleep(500);
			driver.findElement(By.id("issue_description")).sendKeys(descriptionSubject);
			Thread.sleep(500);
			new Select(driver.findElement(By.id("issue_assigned_to_id"))).selectByVisibleText(testers[ranNumbr(0,2)]);
			Thread.sleep(500);
			new Select(driver.findElement(By.id("issue_custom_field_values_59"))).selectByVisibleText("Major");
			Thread.sleep(1000);
			driver.findElement(By.name("attachments[dummy][file]")).click();
			Thread.sleep(1500);
			selectoneFile();
			Thread.sleep(1500);
			driver.findElement(By.name("commit")).click();
			Thread.sleep(3000);
			String inciName = driver.findElement(By.xpath("//*[@id='content']/h2")).getText();
			Thread.sleep(2000);
			System.out.println("Se ha creado la "+inciName+" correctamente");
			
				}
				
		public static BufferedReader getBuffered(String link){

		    FileReader lector  = null;
		    BufferedReader br = null;
		    try {
		         File Arch=new File(link);
		        if(!Arch.exists()){
		           System.out.println("No existe el archivo");
		        }else{
		           lector = new FileReader(link);
		           br = new BufferedReader(lector);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return br;
		}
		
		public static void readTxt() throws Exception{
		    try {
		        String ruta = "C:\\redmines.txt";
		        BufferedReader br = getBuffered(ruta);
		        String linea =  br.readLine();
		        
		        while(linea != null){
		            values = linea.split(" ");
		            for (int i = 0; i<values.length; i++) {
		            	redL.add(values[i]);
		            }
		            linea = br.readLine();
		        }
		    } catch (IOException | NumberFormatException e) {
		        e.printStackTrace();
		    }
		}
		public static void setClipboardContents(String aString){
		    StringSelection stringSelection = new StringSelection(aString);
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		    clipboard.setContents(stringSelection, stringSelection);
		  }
		
		
			public  static void tearDown() throws Exception{			  
				    driver.quit();
				    String verificationErrorString = verificationErrors.toString();
				    if (!"".equals(verificationErrorString)) {
				      fail(verificationErrorString);
				    }
				  
		}
		public static void selectoneFile() throws Exception{
			Robot robot = new Robot();
			Thread.sleep(1000);
			setClipboardContents(folderSel);
			robot.keyPress(KeyEvent.VK_COLON);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			setClipboardContents("\""+fileDatafilled+"\""+" "+"\""+fileError+"\"");
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);	
		}
		
		public static void selectAllFiles() throws Exception{			
			Robot robot = new Robot();
			String url = "E:\\workspace\\Mavi_Repository\\gestionCuentas_CrearCuenta\\attachments";
			setClipboardContents(url);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_E);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_E);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);	
		}
	      	
}