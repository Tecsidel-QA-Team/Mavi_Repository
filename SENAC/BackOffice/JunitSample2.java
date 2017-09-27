package BackOffice;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class JunitSample2 {
	public  static WebDriver driver ;
	public static ChromeDriver drv;
	public static Timestamp timest = new Timestamp (System.currentTimeMillis());
	  public static String timet = timest.toString().replace("-", "").replace(" ", "").replace(":", "").substring(0,14);
	  public static String baseUrl="http://virtualbo-qa/BOQAHostSenac/web/forms/core/login.aspx";
		public static String loginField = "BoxLogin";
		public static String passField = "BoxPassword";
		public static String loginButton = "BtnLogin";
		public static StringBuffer verificationErrors = new StringBuffer();
		
		

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void newTesttoConfigure() throws Exception{
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		
		/*DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);*/
			//ChromeOptions opts =  new ChromeOptions(); //poner esta opción cuando se vaya a subir a Git
			//opts.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"); //poner esta opción cuando se vaya a subir a Git
			drv = new ChromeDriver();//opts); poner esta opción cuando se vaya a subir al Git
			drv.manage().window().maximize();	

		
		
		//borrararch();
		//borrarArchivosTemp("E:\\workspace\\Mavi_Repository\\Host_VerTranscciones\\attachments\\");
	try{
		Actions action = new Actions(drv);
		drv.get(baseUrl);
		//takeScreenShot("E:\\Selenium\\","loginHostSenacPage"+timet+".jpg");
		//takeScreenShot("E:\\workspace\\Mavi_Repository\\Host_VerTranscciones\\attachments\\","loginHostSenacPage.jpg");
		drv.findElement(By.id(loginField)).sendKeys("00001");
		drv.findElement(By.id(passField)).sendKeys("00001");
		drv.findElement(By.id(loginButton)).click();
		Thread.sleep(1000);
		//takeScreenShot("E:\\Selenium\\","homeHostSenacPage"+timet+".jpg");
		//takeScreenShot("E:\\workspace\\Mavi_Repository\\Host_VerTranscciones\\attachments\\","homeHostSenacPage.jpg");	
		Thread.sleep(2000);					
		//drv.findElement(By.linkText("Transacciones")).click();
		action.clickAndHold(drv.findElement(By.linkText("Transacciones"))).build().perform();
		Thread.sleep(1000);
		drv.findElement(By.linkText("Ver transaciones")).click();								
		Thread.sleep(1000);
		//takeScreenShot("E:\\Selenium\\","verTransaccionesPage"+timet+".jpg");
		//takeScreenShot("E:\\workspace\\Mavi_Repository\\Host_VerTranscciones\\attachments\\","verTransaccionesPage.jpg");
		Thread.sleep(500);
		drv.findElement(By.id("ctl00_ContentZone_dateSelector_dt_from_box_date")).clear();
		drv.findElement(By.id("ctl00_ContentZone_dateSelector_dt_from_box_date")).sendKeys("15/05/2017");
		Thread.sleep(1000);
		//selectDropDown("ctl00_ContentZone_cmb_vehicle_class_cmb_dropdown");
		Thread.sleep(500);
		drv.findElement(By.id("ctl00_ButtonsZone_BtnSearch")).click();
		Thread.sleep(2000);
		//takeScreenShot("E:\\Selenium\\","verTransaccionesResults"+timet+".jpg");
		//takeScreenShot("E:\\workspace\\Mavi_Repository\\Host_VerTranscciones\\attachments\\","verTransaccionesRetults.jpg");
		Thread.sleep(1000);
		String elementsFound = drv.findElement(By.id("ctl00_ContentZone_tablePager_LblCounter")).getText();				
		Thread.sleep(1500);
		System.out.println("Busqueda Completa: "+ elementsFound);
		Thread.sleep(1000);					
	}catch(Exception e){
		e.printStackTrace();
		fail();
	}
}		

public static void takeScreenShot(String pathS, String fname) throws Exception {
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile, new File(pathS, fname));
}


public static void selectDropDown(String by) {
		Select vDropdown = new Select (driver.findElement(By.id(by)));
			List<WebElement> dd = vDropdown.getOptions();		
			Random rand = new Random();
		int vdd = rand.nextInt(dd.size());
			if (vdd<0){vdd = 0;}	
			if (vdd>=dd.size()){vdd=vdd-1;}
		new Select (driver.findElement(By.id(by))).selectByIndex(vdd);
		
	  }




}
	/*public void test() {
		Random ran = new Random();
		
		Calendar calend;
		
		calend = Calendar.getInstance();
		calend.set(Calendar.MONTH, ran.nextInt(12)+1);
		//calend.set(ran.nextInt(3)+2014, ran.nextInt(12)+1, ran.nextInt(30)+1);
		SimpleDateFormat sft = new SimpleDateFormat("MM");
		System.out.println("La fecah aleatoria es: "+sft.format(calend.getTime()));
		
        MyClass2 tester = new MyClass2(); // MyClass is tested

        // assert statements
        Assert.assertEquals("Multiplication", 0, tester.multiply(10, 0));
        System.out.println("value is: "+tester.multiply(10, 0));
        assertEquals("0 x 10 must be 0", 0, tester.multiply(0, 10));
        assertEquals("0 x 0 must be 0", 0, tester.multiply(0, 0));
        tester.log("Assertion Correct");
    }
    
}
class MyClass2{
	public int multiply(int x, int y){
		int a = x * y;
		return a;
		
	}
	public void log(String Amessage){
		System.out.println(Amessage);
	}
}*/