package BackOffice;

import java.io.File;
import java.sql.Timestamp;

import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class senacFieldsConfiguration {
	public static final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";		
	public static int delm;
	public static String caMe;
	public static String acam;
	public static int ad;
	public static int caMer;
	public static WebDriver driver;	
	public static String baseUrl="http://virtualbo-qa/BOQAHostSenac/web/forms/core/login.aspx";
	public boolean acceptNextAlert = true;
	public StringBuffer verificationErrors = new StringBuffer();
	public static int numbering;
	public static String linkPartes;
	public static String Types;
	public static String tipoSel;
	public static String loginField = "BoxLogin";
	public static String passField = "BoxPassword";
	public static String loginButton = "BtnLogin";
	
	  //Edit buttons icons configuration.	  
	  public static final String [] componentList = new String[]{"IB_comunication","IB_vehicle","IB_person","IB_patrol","IB_security","IB_ambulance","IB_crane","IB_weather","IB_trafic","IB_roadway","IB_insideInformation","IB_inconvenientShedule"};
	  public static Timestamp timest = new Timestamp (System.currentTimeMillis());
	  public static String timet = timest.toString().replace("-", "").replace(" ", "").replace(":", "").substring(0,14);
	  
	  public static void takeScreenShot(String fname) throws Exception {
		    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(scrFile, new File("E:\\Selenium\\", fname));
	  }
	  public static void selectDropDownClick2(String by)
      {
          Select vDropdown = new Select (driver.findElement(By.id(by)));
          List<WebElement> dd = vDropdown.getOptions();
          Random rand = new Random();
          int vdd = rand.nextInt(dd.size())+1;
          if (vdd <= 0) { vdd = vdd + 1; }
          	
          if (vdd >= dd.size()) { vdd = dd.size() - 1; }
          new Select(driver.findElement(By.id(by))).selectByIndex(vdd);

      }
	  
	  public static void ranSelection(String selId, int len1) throws Exception{
			List <WebElement >mcCameras = driver.findElements(By.xpath("//*[contains(@id, '"+selId+"')]"));        
	        caMe = mcCameras.get(0).getAttribute("id");
	        acam = mcCameras.get(mcCameras.size()-1).getAttribute("id");
	        ad = Integer.parseInt(caMe.substring(len1));
	        caMer = Integer.parseInt(acam.substring(len1));
		}
	  
	  public static void selectDropDownClick(String by)
      {
          Select vDropdown = new Select (driver.findElement(By.id(by)));
          List<WebElement> dd = vDropdown.getOptions();
          Random rand = new Random();
          int vdd = rand.nextInt(dd.size())+1;
          if (vdd < 0) { vdd = 0; }
          	
          if (vdd >= dd.size()) { vdd = dd.size() - 1; }
          new Select(driver.findElement(By.id(by))).selectByIndex(vdd);

      }
	  
	  public static void ranClick(String ranSel,String del, int min, int max) {// Elegir elemento al azar
          Random rand = new Random();
          
          delm = rand.nextInt((max - min) + 1) + min;
          if (delm < min) { delm = delm + 1; }
          if (delm > max) { delm = delm - 1; }
          if ((delm % 2) == 0) {
              delm = delm - 1;
          }
          if (delm < 10){
          driver.findElement(By.id(ranSel+del+delm)).click();
          }else{
        	  //No Comment
        	  driver.findElement(By.id(ranSel+delm)).click();
          }

      }
	  
	  public static void clickAll(String id, int camp1, int camp2) throws Exception{          
              for (int i = camp1; i <= camp2; i=i +2){
              Thread.sleep(200);
              driver.findElement(By.id(id + i)).click();
              }
	  	}

      	public static void elementClick(String byID) {
      			driver.findElement(By.id(byID)).click();
      		}
      	
      	public static void selectDropDown(String by) {
      		Select vDropdown = new Select (driver.findElement(By.id(by)));
      			List<WebElement> dd = vDropdown.getOptions();		
      			Random rand = new Random();
      		int vdd = rand.nextInt(dd.size());
      			if (vdd<0){vdd = vdd+1;}	
      			if (vdd>=dd.size()){vdd=vdd-1;}
      		new Select (driver.findElement(By.id(by))).selectByIndex(vdd);
      		
      	  }
      	
     	public static void ranclickOption(String[] id, int min, int max) throws Exception{
 			
     		Random rand = new Random();
     		int selOp = rand.nextInt(max-min)+1;
     		if (selOp >= id.length){
     			selOp = selOp -1;
     		} 		
     		for (int i = 1; i <= selOp;i++ ){
     			
     			if (selOp == id.length -1){
     				if (!driver.findElement(By.id(id[i])).isSelected()){
     					driver.findElement(By.id(id[i])).click();
     					Thread.sleep(300);
     				}	
     			}else{
     			int selc = rand.nextInt(max-min)+1;
     			if (selc == id.length){
     				selc = selc - 1;
     			}
     			if (!driver.findElement(By.id(id[i])).isSelected()){
     					driver.findElement(By.id(id[selc])).click();
     						Thread.sleep(1000);
     					}	
     	 			}	
     		}	
     		
     	}
     	
     	public static int ranNumbr(int min, int max) {
   		  Random rand = new Random();
   		  numbering = min+rand.nextInt((max-min)+1);
   		  return numbering;
   		  
   	  }
     	public static void notEmptyDropDown(String by) throws Exception{
            Select fDropDown = new Select (driver.findElement(By.id(by)));
            List<WebElement> fDsel = fDropDown.getOptions();
            fDsel.size();             
            if (fDsel.size() > 1){
            	selectDropDownClick(by);
            }
            Thread.sleep(1000);
     	}

     	public static int ranYearNumbr(int min, int max) {
     		  Random rand = new Random();
     		  numbering = min+rand.nextInt((max-min)+1);
     		  return numbering;    		  
       	}
     	public static String dniLetra (int dni){
  		  return String.valueOf(dni)+(NIF_STRING_ASOCIATION.charAt(dni % 23));
  	  }
      	
}
