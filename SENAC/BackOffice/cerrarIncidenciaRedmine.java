package BackOffice;

import static org.junit.Assert.*;



import org.openqa.selenium.chrome.ChromeDriver;


import BackOffice.senacFieldsConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;



public class cerrarIncidenciaRedmine extends senacFieldsConfiguration{
		public static int i = 0;
		public static String [] values = null;
		public static List<String> redL = new ArrayList<String>();
 
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
		
public static void main (String [] args) throws Exception{
		setUp();
		String inciQA = JOptionPane.showInputDialog(null,"Entre Incidencia",JOptionPane.OK_OPTION);
		ReqRedmine(inciQA);
		tearDown();
			}
 
public static void ReqRedmine(String inciQA) throws Exception{
			Thread.sleep(500);
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			Thread.sleep(1000);
			driver.get("http://redmine.tecsidel.es");
			Thread.sleep(500);
			driver.findElement(By.id("username")).sendKeys("fgn");
			driver.findElement(By.id("password")).sendKeys("Demo.1234");
			driver.findElement(By.name("login")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("q")).sendKeys(inciQA.toString());		
			driver.findElement(By.id("q")).submit();
			Thread.sleep(3000);
			String inciName = driver.findElement(By.xpath("//*[@id='content']/h2")).getText();
			if (inciName.equals("Búsqueda")){
				System.out.println("Esta Incidencia no existe o no hay permisos para verla");
				Thread.sleep(3000);
				return;
			}
			if (!inciName.contains("IncidenceQA")){
				System.out.println("Esto no es una IncidenciaQA, es un "+ inciName.substring(0, inciName.length()-7));
				Thread.sleep(3000);
				return;
			}
			
			if (inciName.contains("IncidenceQA")){
				String status = driver.findElement(By.xpath("//*[@id='content']/div[2]/div[2]/div[1]/div[1]/div[1]/div[2]")).getText();			
				String fechaFin = driver.findElement(By.xpath("//*[@id='content']/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]")).getText();
				Thread.sleep(1000);
				if (status.equals("Solved")){
				driver.findElement(By.linkText("Modificar")).click();
				Thread.sleep(2000);
				new Select(driver.findElement(By.id("issue_status_id"))).selectByVisibleText("Done");
				Thread.sleep(2000);
				if (fechaFin.equals("")){
					driver.findElement(By.id("issue_due_date")).sendKeys(dateformat.format(date));
				}
				Thread.sleep(1000);
				driver.findElement(By.id("issue_notes")).sendKeys("Puesta a Done automáticamente.");
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("#issue-form > input[name=\"commit\"]")).click();
				Thread.sleep(2000);
				System.out.println("El "+inciQA+" ha sido puesta a Done");
				Thread.sleep(1000);
			}else{
				System.out.println("El "+inciQA+" no está en estado Solved, está en estado "+status);
			}
		}	
			Thread.sleep(10000);
		
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
			
		
			public static void tearDown() throws Exception{			  
				    driver.quit();
				    String verificationErrorString = verificationErrors.toString();
				    if (!"".equals(verificationErrorString)) {
				      fail(verificationErrorString);
				    }
				  
		}
	      	
}