package BackOffice;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl;


import org.openqa.selenium.chrome.ChromeDriver;


import BackOffice.senacFieldsConfiguration;


import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class conexión_BBDDSenac extends senacFieldsConfiguration{
		private static Statement stmt;
		private static ResultSet rs;
		private static String queryString;
		
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
		@Test
			public void senacGestionCuentasPage() throws Exception{
			 String connectionUrl = "jdbc:sqlserver://172.18.130.188;"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
			    stmt = null;
			    rs = null;
		      try {
		         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		         Connection conn = DriverManager.getConnection(connectionUrl, "sa", "lediscet");
		         stmt = conn.createStatement();
		         queryString = "select msgtype,min(msgtime) from amessage where msgstatus=0 and msgtype not  in ('StaticFileActivation','Exception') group by msgtype";
		         rs = stmt.executeQuery(queryString);
		         String PCD;
				while (rs.next()) {
		        	PCD = rs.getString("msgtype");
		        	System.out.println(PCD);
		         }
		         
				}catch(Exception e){
					e.printStackTrace();
					fail();
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
