package BackOffice;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import javax.sql.rowset.CachedRowSet;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;
import com.sun.rowset.CachedRowSetImpl;


import org.openqa.selenium.chrome.ChromeDriver;


import BackOffice.senacFieldsConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;



public class conexión_BBDDSenac extends senacFieldsConfiguration{
		private static Statement stmt;
		private static ResultSet rs;
		public static int i;
		private static String queryString;
		public static ArrayList<String> transactionsHIds = new ArrayList<String>();
		public static ArrayList<String> transactionsPIds = new ArrayList<String>();		


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
			public void verConfirmacion_Transitos() throws Exception{
			
			//borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\conexion_BBDDSenac\\attachments\\");
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Date date = new Date();			
				String transSearch = dateFormat.format(date);
				String connectionUrlPlaza = "jdbc:sqlserver://172.18.130.188:1433;DataBaseName=COVIHONDURAS_QA_TOLLPLAZA"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
				String connectionUrlHost = "jdbc:sqlserver://172.18.130.188:1433;DataBaseName=COVIHONDURAS_QA_TOLLHOST"; //+ "user=sa; password=lediscet";//" + "user=SENEGAL_QA_TOLLHOST; password=USRTOLLHOST";
			    stmt = null;
			    rs = null;
			    try {
			    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			    		Connection conn = DriverManager.getConnection(connectionUrlPlaza, "sa", "lediscet");
			    		stmt = conn.createStatement();
			    		queryString = "select passagetime, transactionid from dbo.atransaction where passagetime like '"+"20170926"+"%' ORDER BY passagetime DESC";
			    		rs = stmt.executeQuery(queryString);
			    		String [] transactions = new String[2]; 			   
			    		while (rs.next()) {
			    			for (i = 0; i < transactions.length;i++){
			    				transactions[0]= rs.getString("passagetime");
			    				transactions[1] = rs.getString("transactionid");
			    				transactionsPIds.add(transactions[i]);
			    			}
			    		}
			    		if (transactions[0]==null&&transactions[1]==null){
			    			System.out.println("No han subido tránsitos a Plaza");
			    			fail("No han subido tránsitos a Plaza");
			    		}else{	
			    			Connection conn2 = DriverManager.getConnection(connectionUrlHost, "sa", "lediscet");
			    			stmt = conn2.createStatement();
				    		queryString = "select passagetime, transactionid from dbo.atransaction where passagetime like '"+"20170926"+"%' ORDER BY passagetime DESC";
				    		rs = stmt.executeQuery(queryString);
				    		while (rs.next()) {
				    			for (i = 0; i < transactions.length;i++){
				    				transactions[0]= rs.getString("passagetime");
				    				transactions[1] = rs.getString("transactionid");
				    				transactionsHIds.add(transactions[i]);
				    			}
				    		}
				    		if (transactions[0]==null&&transactions[1]==null){
				    			System.out.println("No han subido tránsitos a Host");
				    			fail("No han subido tránsitos a Host");
				    		}else{
				    			HostPlazaBackOffice.BOHost_VerTransacciones.
				    			Thread.sleep(1000);
				    			
				    		}
							System.out.println(transactionsHIds.get(1));
			    		}
					//fis.close();
					//fis2.close();
					//System.setOut(old);
				}catch(Exception e){
					e.printStackTrace();
					fail();
				}
		}		
			
		@After
			public void tearDown() throws Exception{			  
				    //driver.quit();
				    String verificationErrorString = verificationErrors.toString();
				    if (!"".equals(verificationErrorString)) {
				      fail(verificationErrorString);
				    }
				  
		}
	      	
}