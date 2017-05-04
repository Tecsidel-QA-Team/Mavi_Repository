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

public class ausaFieldsConfiguration {
	public static String comLabel= "ctl00_ContentZone_BtnAddComunication_IB_Label";
	public static String vehLabel = "ctl00_ContentZone_BtnAddVehicle_IB_Label"; 
	public static String perLabel = "ctl00_ContentZone_BtnAddPerson_IB_Label";
	public static String ambLabel = "ctl00_ContentZone_BtnAddAmbulance_IB_Label";
	public static String patroLabel = "ctl00_ContentZone_BtnAddPatrol_IB_Label";
	public static String securLabel = "ctl00_ContentZone_BtnAddSecurity_IB_Label";
	public static String cranLabel = "ctl00_ContentZone_BtnAddCrane_IB_Label";
	public static String weaLabel = "ctl00_ContentZone_BtnAddWeather_IB_Label";
	public static String traffLabel = "ctl00_ContentZone_BtnAddTraffic_IB_Label";
	public static String roadLabel = "ctl00_ContentZone_BtnAddRoadway_IB_Label";
	public static String otherLabel = "ctl00_ContentZone_BtnAddOther_IB_Label";
	public static String jobOrLabel = "ctl00_ContentZone_BtnAddJobOrder_IB_Label";
	public static String infoLabel = "ctl00_ContentZone_BtnAddInsideInformation_IB_Label";
	public static String inconLabel = "ctl00_ContentZone_BtnAddInconvenientSchedule_IB_Label";
	public static String []  personsT = new String[]{"Pilar Bonilla", "Mavi Garrido", "Francisco Castro", "Miguel Angel Sanchez", "Franklyn Garcia", "Maria Blanco", "Oscar Bailon", "Fatima Romano", "Marc Navarro"};
	public static final String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
	public static int []  genderT = new int[]{1, 1, 2, 2, 2, 1, 2, 1, 2};
	public static String nameLast = "_txt_name_box_data";
	public static String typeT = "_cmb_occupant_type_cmb_dropdown";
	public static String phone = "_txt_phone_box_data";
	public static String home = "_txt_address_box_data";
	public static String DNI = "_txt_dni_box_data";	
	public static String other = "_txt_others_box_data";
	public static int delm;
	public static String caMe;
	public static String acam;
	public static int ad;
	public static int caMer;
	public static WebDriver driver;
	public static String partText;
	  public static String baseUrl="http://172.18.133.105/12263/web/forms/core/site.aspx";
	  public boolean acceptNextAlert = true;
	  public StringBuffer verificationErrors = new StringBuffer();
	  public static int numbering;
	  public static String linkPartes;
	  public static String Types;
	  public static String tipoSel;
	  public static String issueCreateBtn = "ctl00_ButtonsZone_BtnSave_IB_Label";
	  public static String communicationField = "ctl00_ContentZone_ctrl_newComunication_txt_Title_box_data";	  
	  public static String medioField = "ctl00_ContentZone_ctrl_newComunication_cmb_mean_cmb_dropdown";
	  public static String motiveField = "ctl00_ContentZone_ctrl_newComunication_cmb_motive_cmb_dropdown";
	  public static String originDestination = "ctl00_ContentZone_ctrl_newComunication_cmb_type_ori_des_cmb_dropdown";
	  public static String originDest = "ctl00_ContentZone_ctrl_newComunication_cmb_ori_des_cmb_dropdown";
	  public static String importanceField = "ctl00_ContentZone_ctrl_newComunication_cmb_importance_cmb_dropdown";
	  public static String subjectField = "ctl00_ContentZone_ctrl_newComunication_txt_subject_box_data";
	  public static String commentField = "ctl00_ContentZone_ctrl_newComunication_txt_comment_box_data"; 
	  public static String cameraSel = "ctl00_ContentZone_mcCameras_txt_selected";
	  public static String typeAccidentes = "ctl00_ContentZone_mc_typeOfAccident_img_expand";
	  public static String newCommunication = "ctl00_ContentZone_ctrl_newComunication_cmb_type_cmb_dropdown";
	  public static String typeImpacto = "ctl00_ContentZone_mc_causal_img_expand";
	  public static String templateBtn = "ctl00_ContentZone_BtnConfirmTemplate";
	  public static String templateSel = "ctl00_ContentZone_cmb_template_cmb_dropdown";
	  public static String createBtn = "ctl00_ContentZone_BtnCreate";
	  public static String prioritySel = "ctl00_ContentZone_cmb_priority_cmb_dropdown";
	  public static String cPartesBtn = "ctl00_ButtonsZone_BtnSave_IB_Label";
	  public static String[] dOption = new String[]{"","ctl00_ContentZone_check_has_torched", "ctl00_ContentZone_check_has_disp_hard_shouleder", "ctl00_ContentZone_check_has_damages_ins", "ctl00_ContentZone_check_has_overturned"};
	  public static String[] TipoS = new String[] { "ctl00_ContentZone_mc_type_ctl43","ctl00_ContentZone_mc_type_ctl45", "ctl00_ContentZone_mc_type_ctl47", "ctl00_ContentZone_mc_type_ctl49", "ctl00_ContentZone_mc_type_ctl51" };
	  public static String[] gravedadS = new String[] {"ctl00_ContentZone_mc_severity_ctl31", "ctl00_ContentZone_mc_severity_ctl33",  };
	  public static String datoBtn = "ctl00_ContentZone_showCausesInfoObservations";
	  public static String[] vOption = new String[]{"","ctl00_ContentZone_check_has_motorcycle", "ctl00_ContentZone_check_has_car", "ctl00_ContentZone_check_has_truck", "ctl00_ContentZone_check_has_bus", "ctl00_ContentZone_check_has_charter"};
	  public static String vVolcado = "ctl00_ContentZone_txt_number_overturned_box_data";
	  public static String[] vOptionT = new String[]{"","ctl00_ContentZone_txt_number_motorcycle_box_data", "ctl00_ContentZone_txt_number_car_box_data", "ctl00_ContentZone_txt_number_truck_box_data", "ctl00_ContentZone_txt_number_bus_box_data", "ctl00_ContentZone_txt_number_charter_box_data"};
	  public static String gravedadT = "ctl00_ContentZone_cmb_severity_cmb_dropdown";
	  public static String asignadoT = "ctl00_ContentZone_cmb_assigned_cmb_dropdown";
	  public static String supervisorT = "ctl00_ContentZone_cmb_supervisor_cmb_dropdown"; 
	  public static String tValoresT = "ctl00_ContentZone_cmb_highway_branch_cmb_dropdown";
	  public static String direcT = "ctl00_ContentZone_cmb_list_direction_cmb_dropdown";
	  public static String locationT = "ctl00_ContentZone_txt_location_box_data";
	  public static String observaT = "ctl00_ContentZone_txt_comments_box_data";
	  public static String ramalsT = "ctl00_ContentZone_cmb_branch_cmb_dropdown";
	  //Edit buttons icons configuration.
	  public static String BtnAddCommunication = "ctl00_ContentZone_BtnAddComunication_IB_Label";
	  public static String BtnAddVehicle = "ctl00_ContentZone_BtnAddVehicle_IB_Label";
	  public static String BtnAddPerson = "ctl00_ContentZone_BtnAddPerson_IB_Label";
	  public static String BtnAddAmbulance = "ctl00_ContentZone_BtnAddAmbulance_IB_Label";
	  public static String BtnAddPatrol = "ctl00_ContentZone_BtnAddPatrol_div_label";
	  public static String BtnAddSecurity = "ctl00_ContentZone_BtnAddSecurity_div_label";
	  public static String BtnAddCrane = "ctl00_ContentZone_BtnAddCrane_div_label";
	  public static String BtnAddWeather = "ctl00_ContentZone_BtnAddWeather_div_label";
	  public static String BtnAddTraffic = "ctl00_ContentZone_BtnAddTraffic_div_label";
	  public static String BtnAddRoadway = "ctl00_ContentZone_BtnAddRoadway_div_label";
	  public static String BtnAddOther = "ctl00_ContentZone_BtnAddOther_div_label";
	  public static String BtnAddJobOrder = "ctl00_ContentZone_BtnAddJobOrder_IB_Label";
	  public static String BtnAddInformation = "ctl00_ContentZone_BtnAddInsideInformation_IB_Label";
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
