package BackOffice;

import static org.junit.Assert.*;



import org.openqa.selenium.chrome.ChromeDriver;


import BackOffice.senacFieldsConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;




import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;



public class cerrarReqRedmine extends senacFieldsConfiguration{
		public static int i = 0;
		public static String [] values = null;
		public static List<String> redL = new ArrayList<String>();
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
		
			public static void main (String [] args) throws Exception{
				ReqRedmine();
				
		
			}
 
			public static void ReqRedmine() throws Exception{
			readTxt();
			Thread.sleep(1000);
			driver.get("http://redmine.tecsidel.es");
			Thread.sleep(500);
			driver.findElement(By.id("username")).sendKeys("fgn");
			driver.findElement(By.id("password")).sendKeys("Demo.1234");
			driver.findElement(By.name("login")).click();
			Thread.sleep(2000);
		for (i = 0; i < redL.size(); i++){
			driver.findElement(By.linkText("Búsqueda")).click();
			Thread.sleep(500);
			driver.findElement(By.id("search-input")).sendKeys(redL.get(i));
			driver.findElement(By.name("commit")).click();
			String status = driver.findElement(By.xpath("//*[@id='content']/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]")).getText();
			
			switch (status){
			case "Dev Completed":  				driver.findElement(By.linkText("Modificar")).click();
												Thread.sleep(2000);
												new Select(driver.findElement(By.id("issue_status_id"))).selectByVisibleText("Testing");
												Thread.sleep(2000);
												new Select(driver.findElement(By.id("time_entry_activity_id"))).selectByVisibleText("Test Execution");
												Thread.sleep(3000);
												driver.findElement(By.cssSelector("#issue-form > input[name=\"commit\"]")).click();
												Thread.sleep(2000);
			
			case "Testing": 					driver.findElement(By.linkText("Modificar")).click();
												Thread.sleep(2000);
												new Select(driver.findElement(By.id("issue_status_id"))).selectByVisibleText("Test Completed");
												Thread.sleep(2000);
												new Select(driver.findElement(By.id("time_entry_activity_id"))).selectByVisibleText("Test Execution");
												Thread.sleep(2000);
												new Select(driver.findElement(By.id("issue_custom_field_values_59"))).selectByVisibleText("Won't be tested");
												Thread.sleep(2000);
												driver.findElement(By.id("issue_notes")).sendKeys("Se dan como pruebas unitarias validas en producción.");
												Thread.sleep(3000);
												driver.findElement(By.cssSelector("#issue-form > input[name=\"commit\"]")).click();
												Thread.sleep(2000);
			case "Test Completed": 				System.out.println("Requirement #"+redL.get(i)+" is in Test Completed");
												Thread.sleep(1000);
												break;
			}
			Thread.sleep(4000);
		}
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
			
		@After
			public void tearDown() throws Exception{			  
				    driver.quit();
				    String verificationErrorString = verificationErrors.toString();
				    if (!"".equals(verificationErrorString)) {
				      fail(verificationErrorString);
				    }
				  
		}
	      	
}