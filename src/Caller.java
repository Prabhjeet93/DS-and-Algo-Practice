/*package com.WLP.AutomationComponents.Common.CallerScript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.relevantcodes.extentreports.LogStatus;



//import org.eclipse.hyades.execution.runtime.datapool.IDatapoolIterator;

//import resources.com.WLP.AutomationComponents.Common.CallerScript.CallerHelper;




import com.WLP.AutomationComponents.Application.ApplicationProperties.EnvironmentVariables;
import com.WLP.AutomationComponents.Application.CustomFunctions.Application_CustomFunctions;
import com.WLP.AutomationComponents.Common.CustomFunctions.CustomFunctions;
import com.WLP.AutomationComponents.Common.DriverEngine.Driver;
import com.WLP.AutomationComponents.Common.JIRAUtility.JIRAUtility;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.io.FileOutputStream;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Caller {
                *//**
                * Script Name   : <b>Caller</b>
                * Description   : This is the caller for running the test scripts
                * Original Host : WinNT Version 5.1  Build 2600 (S)
                * @since  2010/04/29
                * Updated by Accenture to work with Selenium
                * @Version 1.00k
                *//*



                static String intEmailFlag = "", intervalFlag="";
                public static ArrayList<String[]>  messagetoPrint=new ArrayList<String[]>();
                public static String testCaseUnit[];
                public static String testCase_Id ="";// Value of the column TestCase_ID in Run Order
                
                public static int TotalRun=0;
                public static int TotalPassed=0;
                public static int TotalFailed=0;
                public static long TotalBatchTime=0;
                
                public static void main(String[] args) {
                                {

                                                ////logInfo("EAF Version 1.00k");
                                                
                                                * To check where the user has initiated the run from. Either from RFT or RQM. This flag is used
                                                * in Driver.java to write the XML report (Summary and Detailed) on initiation of execution from RQM
                                                * at the end of each test case.
                                                
                                                //extent.init("C:\\Eclipse\\workspace\\PAR\\ExtentReports\\ScreenshotReport.html", true,DisplayOrder.BY_LATEST_TO_OLDEST,GridType.STANDARD);
                                                //extent.config().documentTitle("Automation Report");
                                                //extent.config().reportHeadline("Automation Report");                                             
                                                
                                                Model model = null;
                                                FileReader reader = null;
                                                MavenXpp3Reader mavenreader = new MavenXpp3Reader();

                                                try {
                                                                File pom=new File(System.getProperty("user.dir")+File.separator+"pom.xml");
                                                     reader = new FileReader(pom); // <-- pomfile is your pom.xml
                                                     model = mavenreader.read(reader);
                                                     model.setPomFile(pom);
                                                }catch(Exception ex){
                                                     // do something better here
                                                     ex.printStackTrace();
                                                }

                                                MavenProject project = new MavenProject(model);
                                                Properties p = project.getProperties(); // <-- thats what you need

                                                
//                                            java.io.InputStream is = Caller.class.getResourceAsStream(System.getProperty("user.dir").replaceAll("\\", "/")+"\\pom.xml");
//                                            java.util.Properties p = new Properties();
//                                            p.load(is);
//                                            EnvironmentVariables.JIRA_URL= args[0];
//                                            EnvironmentVariables.JIRA_USERNAME= args[1];
//                                            EnvironmentVariables.JIRA_PASSWORD= args[2];
//                                            String RunOrderVariable=args[3];
//                                            EnvironmentVariables.SeleniumRemote= args[4];
//                                            EnvironmentVariables.SeleniumGridURL= args[5];
//                                            EnvironmentVariables.Framework= args[6];
//                                            
                                                EnvironmentVariables.JIRA_URL= "https://jira.anthem.com/rest/raven/1.0/import/execution";
                                                EnvironmentVariables.JIRA_USERNAME= "";
                                                EnvironmentVariables.JIRA_PASSWORD= "";
                                                String RunOrderVariable="runorder_DB";
                                                EnvironmentVariables.SeleniumRemote= "false";
                                                EnvironmentVariables.SeleniumGridURL= "";
                                
                                                if(System.getProperty("RunOrderVariable")==null)
                                                                RunOrderVariable=args[3];
                                                else
                                                                RunOrderVariable= System.getProperty("RunOrderVariable");
                                                
                                                if(System.getProperty("JIRA.URL")==null)
                                                                EnvironmentVariables.JIRA_URL= args[0];
                                                else
                                                                EnvironmentVariables.JIRA_URL= System.getProperty("JIRA.URL");
                                                
                                                if(System.getProperty("JIRA.USERNAME")==null)
                                                                EnvironmentVariables.JIRA_USERNAME= args[1];
                                                else
                                                                EnvironmentVariables.JIRA_USERNAME= System.getProperty("JIRA.USERNAME");
                                                
                                                if(System.getProperty("JIRA.PASSWORD")==null)
                                                                EnvironmentVariables.JIRA_PASSWORD= args[2];
                                                else
                                                                EnvironmentVariables.JIRA_PASSWORD= System.getProperty("JIRA.PASSWORD");
                                                
                                                if(System.getProperty("Selenium.Remote")==null)
                                                                EnvironmentVariables.SeleniumRemote= args[4];
                                                else
                                                                EnvironmentVariables.SeleniumRemote= System.getProperty("Selenium.Remote");
                                                
                                                if(System.getProperty("Selenium.GridURL")==null)
                                                                EnvironmentVariables.SeleniumGridURL= args[5];
                                                else
                                                                EnvironmentVariables.SeleniumGridURL= System.getProperty("Selenium.GridURL");
                                                
                                                if(System.getProperty("Selenium.FrameWork")==null)
                                                                EnvironmentVariables.Framework= args[6];
                                                else
                                                                EnvironmentVariables.Framework= System.getProperty("Selenium.FrameWork");
                                                
                                                //EnvironmentVariables.JIRA_URL = p.getProperty("JIRA.URL");
                                                //EnvironmentVariables.JIRA_USERNAME = p.getProperty("JIRA.USERNAME");
                                                //EnvironmentVariables.JIRA_PASSWORD = p.getProperty("JIRA.PASSWORD");                                               
                                                //EnvironmentVariables.SeleniumRemote= p.getProperty("Selenium.Remote");
                                                //EnvironmentVariables.SeleniumGridURL= p.getProperty("Selenium.GridURL");
                                                
                                                Driver.extent.loadConfig(new File(System.getProperty("user.dir")+File.separator+"extent-report.xml"));
                                                try {
                                                                Driver.driverMap.put("starting_script","caller");
                                                                String domain ="";// Value of the column Domain in Run Order
                                                                String domainUnique = ""; // Value of all the unique domains for the batch
                                                                String testplan = ""; //Value of the column TestPlan in Run Order sheet
                                                                String UniqueTestPlan = "";
                                                                String ReportToJIRA = "";
                                                                String JIRATestID = "";
                                                                boolean mark_tc_completion = true;
                                                                Driver.driverMap.put("MARK_TC_COMPLETION", "YES");
                                                                try{
                                                                                String runOrder = "";// Path to the Run order
                                                                                String dataSheet = ""; // Value of the column DS_ID in Run order
                                                                                String Iteration="";
                                                                                String app_type = "";// Value of the column App_Type in Run Order
                                                                                String subject = "";
                                                                                String dataDriveExcel="null";// Value of the column Test_Script_Path in Run Order
                                                                                
                                                                                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                                                                                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                                                                                Document doc = docBuilder.newDocument();
                                                                                Element testsuites = doc.createElement("testsuites");
                                                                                doc.appendChild(testsuites);
                                                                                Element testsuite = doc.createElement("testsuite");
                                                                                testsuite.setAttribute("name", "Regression");
                                                                                testsuites.appendChild(testsuite);
                                                                                testsuites.appendChild(testsuite);
                                                                                Element properties         = doc.createElement("properties");
                                                                                Element property            = doc.createElement("property");
                                                                                Element property2          = doc.createElement("property");
                                                                                Element property3          = doc.createElement("property");
                                                                                testsuite.appendChild(properties);
                                                                                property.setAttribute("name", "java.vendor");
                                                                                property.setAttribute("value", "Sun Microsystems Inc.");
                                                                                property2.setAttribute("name", "compiler.debug");
                                                                                property2.setAttribute("value", "on");
                                                                                property3.setAttribute("name", "project.jdk.classpath");
                                                                                property3.setAttribute("value", "jdk.classpath.1.8");
                                                                                properties.appendChild(property);
                                                                                properties.appendChild(property2);
                                                                                properties.appendChild(property3);
                                                                                testsuite.appendChild(properties);

                                                                                try{
                                                                                JunitFormatXML junit=new JunitFormatXML("Test");
                                                                                }
                                                                                catch(Exception e)
                                                                                {
                                                                                                
                                                                                }
                                                                                JunitFormatXML junit=new JunitFormatXML();
                                                                                junit.startTestSuite("Test");
                                                                                
                                                                                
                                                                                * This flag checks the presence of the Column in the Run order.This flag gets the column number
                                                                                * using findCol(). If it returns -1, then the column does not exist in the run order.
                                                                                
                                                                                int flag=-1;
                                                                                //            Driver.setForResumeActivities();


                                                                                // Get the run order excel path.
                                                                                try{
                                                                                                // Get from env var file.
                                                                                                runOrder = Driver.getValueFromEnvVariable(RunOrderVariable);

                                                                                                // This is to support Build Forge execution. The path is an argument to the testMain().
                                                                                                if(!(args == null)){
                                                                                                runOrder = args[0].toString();
                                                                                }
                                                                                }catch(Exception e){
                                                                                                e.printStackTrace();
                                                                                }

                                                                                Workbook tempWB;

                                                                                
                                                                                * Get the value from run order and pass the values to driver in the required format
                                                                                * for execution.
                                                                                * 
                                                                                try {

                                                                                                // Create the workbook based on the extension given.
                                                                                                if(runOrder.contains(".xlsx")){// for .xlsx workbook
                                                                                                                tempWB = new XSSFWorkbook(runOrder);
                                                                                                }
                                                                                                else{//for .xls workbook
                                                                                                                InputStream inp = new FileInputStream(runOrder);
                                                                                                                tempWB = (Workbook) new HSSFWorkbook(new POIFSFileSystem(inp));
                                                                                                }


                                                                                                // By default the sheet name of the Run order is - Sheet1
                                                                                                Sheet sheet = tempWB.getSheet("Sheet1");

                                                                                                
                                                                                                * Get the total number of rows Sheet1 in the excel and handle if there are no rows.
                                                                                                
                                                                                                int NumberOfRowsDataSheet = sheet.getLastRowNum(); // row count of runorder excel
                                                                                                if(NumberOfRowsDataSheet == 0){
                                                                                                                //            //logInfo("There are "+ NumberOfRowsDataSheet + " rows in the RunOrder");
                                                                                                }

                                                                                                Row row = null;

                                                                                                int testCaseCounter = 0;

                                                                                                int no_of_testcases_executed = 0;
                                                                                                int restart_rft_after = 0;
                                                                                                boolean should_restart = false;
                                                                                                mark_tc_completion = true;
                                                                                                if(!Driver.getValueFromEnvVariable("restart_rft_playback").trim().equalsIgnoreCase("")) {
                                                                                                                restart_rft_after = Integer.parseInt(Driver.getValueFromEnvVariable("restart_rft_playback"));
                                                                                                                //Driver.setLogMsg("info", "restart_rft_after = " + restart_rft_after);
                                                                                                                should_restart = true;
                                                                                                }

                                                                                                for (int i=1;i<(NumberOfRowsDataSheet+1);i++){

                                                                                                                
                                                                                                                * To stop the execution even if following cases in run order are yet to execute.
                                                                                                                * Break out of the loop and continue with reporting.
                                                                                                                

                                                                                                                Driver.driverMap.put("isExcelReportingEnabled", "FALSE");
                                                                                                                if(Driver.getValueFromEnvVariable("enable_excel_reporting").equalsIgnoreCase("y") ||
                                                                                                                                                Driver.getValueFromEnvVariable("enable_excel_reporting").equalsIgnoreCase("yes")){

                                                                                                                                Driver.driverMap.put("isExcelReportingEnabled", "TRUE");
                                                                                                                }

                                                                                                                if(Driver.driverMap.containsKey("stop_execution")){
                                                                                                                                if(Driver.driverMap.get("stop_execution").toString().equalsIgnoreCase("stop")){
                                                                                                                                                Driver.setLogMsg("info", "User stopped execution after "+i+" steps in Run order");
                                                                                                                                                break;
                                                                                                                                }
                                                                                                                }

                                                                                                                // Get the rows
                                                                                                                row=sheet.getRow(i);
                                                                                                                if(row== null){continue;}

                                                                                                                 Column :: TestCase_ID
                                                                                                                * Get the testcase name from the row. Clear the variable before assigning the new
                                                                                                                * value.
                                                                                                                
                                                                                                                testCase_Id ="";
                                                                                                                try{
                                                                                                                                testCase_Id = row.getCell(Driver.findCol(sheet, "TestCase_ID")).toString().trim();
                                                                                                                }
                                                                                                                catch(Exception e1){
                                                                                                                                e1.printStackTrace();
                                                                                                                                testCase_Id = "";
                                                                                                                                continue;
                                                                                                                }
                                                                                                                 Column :: Domain
                                                                                                                * Get the Domain from the row. Clear the variable before assigning the new
                                                                                                                * value.
                                                                                                                
                                                                                                                domain = "";
                                                                                                                if(Driver.findCol(sheet, "Domain")!= -1){
                                                                                                                                if(!(row.getCell(Driver.findCol(sheet, "Domain"))==null)){
                                                                                                                                                domain = row.getCell(Driver.findCol(sheet, "Domain")).toString().trim();
                                                                                                                                }
                                                                                                                }
                                                                                                                Driver.driverMap.put("Domain",domain );
                                                                                                                Driver.frameworkReport.setReportingVariable("Domain", domain);

                                                                                                                 Column :: TestPlan
                                                                                                                * Get the test plan from the row. Clear the variable before assigning the new
                                                                                                                * value.
                                                                                                                
                                                                                                                testplan = "";

                                                                                                                if(Driver.findCol(sheet, "TestPlan")!= -1){
                                                                                                                                if(!(row.getCell(Driver.findCol(sheet, "TestPlan"))==null)){
                                                                                                                                                testplan = row.getCell(Driver.findCol(sheet, "TestPlan")).toString().trim();
                                                                                                                                }
                                                                                                                }
                                                                                                                Driver.driverMap.put("TestPlan",testplan );
                                                                                                                Driver.frameworkReport.setReportingVariable("TestPlan", testplan);

                                                                                                                 Column :: Mstr_dp_Name
                                                                                                                * Get the master dp name from the row. Clear the variable before assigning the new
                                                                                                                * value.
                                                                                                                
                                                                                                                String masterDataPoolName ="";
                                                                                                                flag=Driver.findCol(sheet, "Mstr_dp_Name");
                                                                                                                if(! (flag==-1)){
                                                                                                                                if(!(row.getCell(flag)==null)){
                                                                                                                                                masterDataPoolName  = row.getCell(flag).toString().trim();
                                                                                                                                }
                                                                                                                }
                                                                                                                else{
                                                                                                                                masterDataPoolName = "";
                                                                                                                }

                                                                                                                 Column :: Object_Map_Name
                                                                                                                * Get the object map name from the row. Clear the variable before assigning the new
                                                                                                                * value.
                                                                                                                
                                                                                                                String objMapName ="";
                                                                                                                try{
                                                                                                                                objMapName = row.getCell(Driver.findCol(sheet, "Object_Map_Name")).toString().trim();
                                                                                                                }
                                                                                                                catch(Exception e3){
                                                                                                                                e3.printStackTrace();
                                                                                                                                objMapName = "";

                                                                                                                }

                                                                                                                 Column :: Execute_TestCase
                                                                                                                * Get the condition if the test case has to be executed. Clear the variable before
                                                                                                                * assigning the new value.
                                                                                                                
                                                                                                                String execute="";
                                                                                                                if(! (row.getCell(Driver.findCol(sheet, "Execute_TestCase"))==null)){
                                                                                                                                execute = row.getCell(Driver.findCol(sheet, "Execute_TestCase")).toString().trim();
                                                                                                                }
                                                                                                                if(execute.equalsIgnoreCase("") || execute == null){
                                                                                                                                execute = "";
                                                                                                                }




                                                                                                                //get the browser type and generate driver depending on the browser type

                                                                                                                if (execute.equalsIgnoreCase("Y") ||  execute.equalsIgnoreCase("yes")) {

                                                                                                                                flag=-1;
                                                                                                                                String browser="";
                                                                                                                                flag=Driver.findCol(sheet, "browser");
                                                                                                                                if(! (flag==-1)){
                                                                                                                                                if(!(row.getCell(flag)==null)){
                                                                                                                                                                browser = row.getCell(flag).toString().trim();

                                                                                                                                                                System.out.println("browser%%%%% "+browser);
                                                                                                                                                }
                                                                                                                                }
                                                                                                                                //p.waitFor();
                                                                                                                                if("IE".equalsIgnoreCase(browser)){
                                                                                                                                                EnvironmentVariables.Browser="IE";
                                                                                                                                                //System.setProperty("webdriver.ie.driver", "c://Drivers//IEDriverServer.exe");
                                                                                                                                                //WebDriver driver = new InternetExplorerDriver();
                                                                                                                                                //WebDriver driver =new RemoteWebDriver(new URL("http://30.128.83.94:4444/wd/hub"), DesiredCapabilities.internetExplorer());
//                                                                                                                                            try{
//                                                                                                                                                            Process p =Runtime.getRuntime().exec("cmd /c start /wait taskkill /F /IM iexplore.exe");
//                                                                                                                                                            Driver.setLogMsg("info", "Active Browsers closed");
//                                                                                                                                                            System.out.println("Waiting for IE to close ...");
//                                                                                                                                                            p.waitFor();
//                                                                                                                                                            System.out.println("IE closed.");
//                                                                                                                                            }
//                                                                                                                                            catch(Exception e){
//                                                                                                                                                            //do nothing - no browser is active
//                                                                                                                                                            Driver.setLogMsg("info","No Active Browsers present to close");
//                                                                                                                                            }                                              
                                                                                                                                                //Driver.driverMap.put("WEBDRIVER",driver);

                                                                                                                                }else if("Chrome".equalsIgnoreCase(browser)){
                                                                                                                                                EnvironmentVariables.Browser="CHROME";
                                                                                                                                                //System.setProperty("webdriver.chrome.driver", "c://Drivers//chromedriver.exe");
                                                                                                                                                //WebDriver driver =new RemoteWebDriver(new URL("http://30.128.83.94:4444/wd/hub"), DesiredCapabilities.chrome());
                                                                                                                                                //WebDriver driver = new ChromeDriver();



//                                                                                                                                            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//                                                                                                                                            ChromeOptions options = new ChromeOptions();
//                                                                                                                                            options.addArguments("test-type");
//                                                                                                                                            capabilities.setCapability("webdriver.chrome.driver","c://Drivers//chromedriver.exe");
//                                                                                                                                            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//
//                                                                                                                                            driver = new ChromeDriver(capabilities);

                                                                                                                                                //Driver.driverMap.put("WEBDRIVER",driver);

                                                                                                                                }else {
                                                                                                                                                EnvironmentVariables.Browser="CHROME";
                                                                                                                                                //WebDriver driver =new RemoteWebDriver(new URL("http://30.128.83.94:4444/wd/hub"), DesiredCapabilities.chrome());
//                                                                                                                                            System.setProperty("webdriver.chrome.driver", "c://Drivers//chromedriver.exe");
//                                                                                                                                            WebDriver driver = new ChromeDriver();

                                                                                                                                                //Driver.driverMap.put("WEBDRIVER",driver);
                                                                                                                                }
                                                                                                                                flag=Driver.findCol(sheet, "Iteration");
                                Iteration="";
                                if(! (flag==-1)){
                                       if(!(row.getCell(flag)==null)){
                                              Iteration  = row.getCell(flag).toString().trim();
                                       }
                                }
                                else{
                                       Iteration = "";
                                }
                                
                                Driver.driverMap.put("Data_Iteration", Iteration);   

                                                                                                                                flag=Driver.findCol(sheet, "DS_ID");
                                                                                                                                dataSheet="";
                                                                                                                                if(! (flag==-1)){
                                                                                                                                                if(!(row.getCell(flag)==null)){
                                                                                                                                                                dataSheet  = row.getCell(flag).toString().trim();
                                                                                                                                                }
                                                                                                                                }
                                                                                                                                else{
                                                                                                                                                dataSheet = "";
                                                                                                                                }

                                                                                                                                ArrayList ar=new ArrayList();
                                                                                                                                if(Driver.driverMap.containsKey("DataSheetList"))                                                                                                                                                
                                                                                                                                {
                                                                                                                                                ar=         (ArrayList) Driver.driverMap.get("DataSheetList");
                                                                                                                                }
                                                                                                                                
                                                                                                                                if(Driver.getValueFromEnvVariable("include_data_sheet_in_email").equalsIgnoreCase("yes") ||
                                                                                                                                                                Driver.getValueFromEnvVariable("include_data_sheet_in_email").equalsIgnoreCase("y")){
                                                                                                                                                String dataSheetName="";
                                                                                                                                                if(dataSheet.contains("path="))
                                                                                                                                                {
                                                                                                                                                                dataSheetName = dataSheet.split("path=")[1].split(";")[0].trim();
                                                                                                                                                                
                                                                                                                                                }
                                                                                                                                                else
                                                                                                                                                {
                                                                                                                                                                dataSheetName = "data_sheet_path";
                                                                                                                                                                
                                                                                                                                                }
                                                                                                                                                int fl=0;
                                                                                                                                                for(int i1=0;i1<ar.size();i1++)
                                                                                                                                                {
                                                                                                                                                                if(ar.get(i1).toString().equals(dataSheetName))
                                                                                                                                                                {
                                                                                                                                                                                fl=1;
                                                                                                                                                                                break;
                                                                                                                                                                }
                                                                                                                                                }
                                                                                                                                                //if((fl==0)&&(!dataSheetName.equals("data_sheet_path")))
                                                                                                                                                if(fl==0)
                                                                                                                                                {
                                                                                                                                                                ar.add(dataSheetName);
                                                                                                                                                                Driver.driverMap.put("DataSheetList",ar);
                                                                                                                                                }
                                                                                                                                                
                                                                                                                                }
                                                                                                                }



                                                                                                                 This is an Optional Column :: DS_ID
                                                                                                                * Get the data sheet id of the test case. Clear the variable before
                                                                                                                * assigning the new value.
                                                                                                                
                                                                                                                flag=Driver.findCol(sheet, "DS_ID");
                                                                                                                dataSheet="";
                                                                                                                if(! (flag==-1)){
                                                                                                                                if(!(row.getCell(flag)==null)){
                                                                                                                                                dataSheet  = row.getCell(flag).toString().trim();
                                                                                                                                }
                                                                                                                }
                                                                                                                else{
                                                                                                                                dataSheet = "";
                                                                                                                }

                                                                                                                 This is an Optional Column :: app_type
                                                                                                                * Get the app type. Clear the variable before assigning the new value.
                                                                                                                
                                                                                                                flag=-1;app_type="";
                                                                                                                flag=Driver.findCol(sheet, "app_type");
                                                                                                                if(! (flag==-1)){
                                                                                                                                if(!(row.getCell(flag)==null)){
                                                                                                                                                app_type = row.getCell(flag).toString().trim();
                                                                                                                                }
                                                                                                                }
                                                                                                                else{
                                                                                                                                app_type = "";
                                                                                                                }




                                                                                                                 This is an Optional Column :: Test_Script_Path
                                                                                                                * Get the datapool excel path to the testcase. Clear the variable before assigning the new value.
                                                                                                                
                                                                                                                flag=-1;
                                                                                                                dataDriveExcel="null";
                                                                                                                flag=Driver.findCol(sheet, "Test_Script_Path");
                                                                                                                if(! (flag==-1)){
                                                                                                                                if(!(row.getCell(flag)==null)){
                                                                                                                                                dataDriveExcel = row.getCell(flag).toString().trim();
                                                                                                                                }
                                                                                                                }
                                                                                                                else{
                                                                                                                                dataDriveExcel = "null";
                                                                                                                }
                                                                                                                if(dataDriveExcel.equals("")){
                                                                                                                                dataDriveExcel="null";
                                                                                                                }

                                                                                                                // To check if the execution failure handler is used
                                                                                                                if (execute.equalsIgnoreCase("Y") ||  execute.equalsIgnoreCase("yes")) {

                                                                                                                                System.out.println("in here!!!");
                                                                                                                                if(testCase_Id.equalsIgnoreCase("CloseApp_Browser"))
                                                                                                                                {
                                                                                                                                                //$$
                                                                                                                                                //call the script OpenApp_Browser
                                                                                                                                                           String scriptName = "com.WLP.AutomationComponents.Common.CloseApp.CloseApp_Browser";
                                                                                                                                //launchInfo :: url
                                                                                                                                String[] obj = {};
                                                                                                                                callScript(scriptName,obj);
                                                                                                                                //Form the message to be written in the excel report and e-mail
                                                                                                                                createMessageBody(testCase_Id, dataSheet);
                                                                                                                                Driver.updateExcelCellValue(runOrder, "Sheet1", i, "Execute_TestCase", "Executed");
                                                                                                                                mark_tc_completion = false;
                                                                                                                                //break;
                                                                                                                                continue;
                                                                                                                                                
                                                                                                                                } else if(testCase_Id.equalsIgnoreCase("CloseApp_Mainframe"))
                                                                                                                                {
                                                                                                                                                //$$
                                                                                                                                                
                                                                                                                                //call the script OpenApp_Browser
                                                                                                                                String scriptName = "com.WLP.AutomationComponents.Common.CloseApp.CloseApp_Mainframe";
                                                                                                                                //launchInfo :: url
                                                                                                                                String[] obj = {};
                                                                                                                                callScript(scriptName,obj);
                                                                                                                                //Form the message to be written in the excel report and e-mail
                                                                                                                                createMessageBody(testCase_Id, dataSheet);
                                                                                                                                Driver.updateExcelCellValue(runOrder, "Sheet1", i, "Execute_TestCase", "Executed");
                                                                                                                                mark_tc_completion = false;
                                                                                                                                //break;
                                                                                                                                continue;
                                                                                                                                                
                                                                                                                                } else {

                                                                                                                                                System.out.println("qweqwe%%%%%%%%%");
                                                                                                                                                flag=Driver.findColStartingWith(sheet, "Resume_Execution_Settings");
                                                                                                                                                if(flag != -1) {
                                                                                                                                                                String whatWhenFailure = "";
                                                                                                                                                                try {
                                                                                                                                                                                whatWhenFailure = row.getCell(flag).toString().trim();
                                                                                                                                                                }catch(Exception e){

                                                                                                                                                                                e.printStackTrace();
                                                                                                                                                                }
                                                                                                                                                                if(testCase_Id.equalsIgnoreCase("ResumeExecutionSettings")) {
                                                                                                                                                                                if(!whatWhenFailure.equalsIgnoreCase("")) {
                                                                                                                                                                                                Driver.driverMap.put("TCStartTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date())+"");
                                                                                                                                                                                                Driver.driverMap.put("TestCase_ID", "ResumeExecutionSettings");
                                                                                                                                                                                                Driver.driverMap.put("TCidentifier",UUID.randomUUID().toString().split("-")[0]);
                                                                                                                                                                                                if(whatWhenFailure.equalsIgnoreCase("restart")) {
                                                                                                                                                                                                                Driver.driverMap.put("RESUME_ACTION_WHEN_FAILURE", "RESTART");
                                                                                                                                                                                                                //Driver.setLogMsg("info", "RESUME_ACTION_WHEN_FAILURE = RESTART FAILED TESTCASE when failure");
                                                                                                                                                                                                }else if(whatWhenFailure.equalsIgnoreCase("continue")) {
                                                                                                                                                                                                                Driver.driverMap.put("RESUME_ACTION_WHEN_FAILURE", "CONTINUE");
                                                                                                                                                                                                                //Driver.setLogMsg("info", "RESUME_ACTION_WHEN_FAILURE = CONTINUE TO NEXT TESTCASE when failure");
                                                                                                                                                                                                }else {
                                                                                                                                                                                                                Driver.setLogMsg("info", "Invalid entry in \"Resume_Execution_Settings\" column of RunOrder");
                                                                                                                                                                                                                Driver.driverMap.put("RESUME_ACTION_WHEN_FAILURE", "CONTINUE");
                                                                                                                                                                                                                //Driver.setLogMsg("info", "RESUME_ACTION_WHEN_FAILURE = CONTINUE TO NEXT TESTCASE when failure");
                                                                                                                                                                                                }
                                                                                                                                                                                                //Driver.updateExcelCellValue(runOrder, "Sheet1", i, "Execute_TestCase", "Executed");
                                                                                                                                                                                }else {
                                                                                                                                                                                                System.out.println("in else");
                                                                                                                                                                                                Driver.driverMap.put("TCStartTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date())+"");
                                                                                                                                                                                                Driver.driverMap.put("TestCase_ID", "ResumeExecutionSettings");
                                                                                                                                                                                                Driver.driverMap.put("TCidentifier",UUID.randomUUID().toString().split("-")[0]);
                                                                                                                                                                                                Driver.setLogMsg("info", "Invalid entry in \"Resume_Execution_Settings\" column of RunOrder");
                                                                                                                                                                                                System.out.println("resume in hashmap");
                                                                                                                                                                                                Driver.driverMap.put("RESUME_ACTION_WHEN_FAILURE", "CONTINUE");
                                                                                                                                                                                                Driver.setLogMsg("info", "RESUME_ACTION_WHEN_FAILURE = CONTINUE TO NEXT TESTCASE when failure");
                                                                                                                                                                                }
                                                                                                                                                                                continue;
                                                                                                                                                                }
                                                                                                                                                }


                                                                                                                                                //$$

                                                                                                                                                Driver.driverMap.put("RESUME_ACTION_WHEN_FAILURE", "");
                                                                                                                                }
                                                                                                                }


                                                                                                                // If the step has to executed
                                                                                                                if (execute.equalsIgnoreCase("Y") ||  execute.equalsIgnoreCase("yes"))
                                                                                                                {
                                                                                                                                System.out.println("Test Case: "+ testCase_Id+" "+dataSheet +" Starts Execution..." );
                                                                                                                                 Column :: OpenApp_Specifications
                                                                                                                                * Get the value for executing any of the OpenApp script files.
                                                                                                                                
                                                                                                                                String launchInfo="";
                                                                                                                                flag=Driver.findCol(sheet, "OpenApp_Specifications");
                                                                                                                                if(! (row.getCell(flag)==null))
                                                                                                                                                launchInfo  = row.getCell(flag).toString().trim();

                                                                                                                                //launch the browser with the given url
                                                                                                                                if(testCase_Id.equalsIgnoreCase("openApp_browser"))
                                                                                                                                {
                                                                                                                                                //$$
                                                                                                                                                
                                                                                                                                //call the script OpenApp_Browser
                                                                                                                                String scriptName = "com.WLP.AutomationComponents.Common.OpenApp.OpenApp_Browser";
                                                                                                                                //launchInfo :: url
                                                                                                                                String[] obj = {launchInfo};
                                                                                                                                callScript(scriptName,obj);
                                                                                                                                //Form the message to be written in the excel report and e-mail
                                                                                                                                createMessageBody(testCase_Id, dataSheet);
                                                                                                                                Driver.updateExcelCellValue(runOrder, "Sheet1", i, "Execute_TestCase", "Executed");
                                                                                                                                continue;
                                                                                                                                                
                                                                                                                                }
                                                                                                                                //launch the mainframe terminal using OpenApp_Mainframe
                                                                                                                                else if(testCase_Id.equalsIgnoreCase("OpenApp_Mainframe"))
                                                                                                                                {
                                                                                                                                                //$$
                                                                                                                                                
                                                                                                                                String scriptName = "com.WLP.AutomationComponents.Common.OpenApp.OpenApp_Mainframe";
                                                                                                                                //launchInfo :: ip,port,screen resolution
                                                                                                                                String[] obj = {launchInfo};
                                                                                                                                callScript(scriptName,obj);
                                                                                                                                createMessageBody(testCase_Id, dataSheet);
                                                                                                                                Driver.updateExcelCellValue(runOrder, "Sheet1", i, "Execute_TestCase", "Executed");
                                                                                                                                continue;
                                                                                                                                                
                                                                                                                                }
                                                                                                                                //launch any file using OpenApp_File
                                                                                                                                if(testCase_Id.equalsIgnoreCase("openApp_file"))
                                                                                                                                {
                                                                                                                                                //$$
                                                                                                                                                
                                                                                                                                String scriptName = "com.WLP.AutomationComponents.Common.OpenApp.OpenApp_File";
                                                                                                                                //launchInfo :: complete path with the file name
                                                                                                                                String[] obj = {launchInfo};
                                                                                                                                callScript(scriptName,obj);
                                                                                                                                createMessageBody(testCase_Id, dataSheet);
                                                                                                                                Driver.updateExcelCellValue(runOrder, "Sheet1", i, "Execute_TestCase", "Executed");
                                                                                                                                continue;
                                                                                                                                }

                                                                                                                                 INTERMITTENT EMAIL - START
                                                                                                                                * Check if the env var flag is set and the recipient list is available.
                                                                                                                                
                                                                                                                                if(Driver.getValueFromEnvVariable("intermittent_email").equalsIgnoreCase("yes") ||
                                                                                                                                                                Driver.getValueFromEnvVariable("intermittent_email").equalsIgnoreCase("y")){
                                                                                                                                                if(Driver.getValueFromEnvVariable("intermittent_email_recipients").equals("")){
                                                                                                                                                                Driver.setLogMsg("info", "Intermittent email not sent. Recipients list is empty.");
                                                                                                                                                }
                                                                                                                                                else{
                                                                                                                                                                //email will be send after executing specified number of test cases
                                                                                                                                                                if(! Driver.getValueFromEnvVariable("after_every_testcase").equals("")){
                                                                                                                                                                                int after_every_testcase = Integer.parseInt(Driver.getValueFromEnvVariable("after_every_testcase"));

                                                                                                                                                                                testCaseCounter = testCaseCounter+1;
                                                                                                                                                                                if(testCaseCounter > after_every_testcase){
                                                                                                                                                                                                testCaseCounter = 1;
                                                                                                                                                                                                intEmailFlag = "send"; // send email if this flag is set as 'send'
                                                                                                                                                                                }
                                                                                                                                                                }

                                                                                                                                                                //email will be sent in specified time intervals
                                                                                                                                                                if(! Driver.getValueFromEnvVariable("after_time_interval").equals("")){
                                                                                                                                                                                int interval = Integer.parseInt(Driver.getValueFromEnvVariable("after_time_interval"));

                                                                                                                                                                                String a, b="";
                                                                                                                                                                                a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()); //current time
                                                                                                                                                                                //a = "2011-06-22T11:47:47";

                                                                                                                                                                                if(Driver.driverMap.containsKey("BatchStartTime")){
                                                                                                                                                                                                if(intervalFlag.equals("")){
                                                                                                                                                                                                                // batch st-time or last email send time
                                                                                                                                                                                                                intervalFlag = Driver.driverMap.get("BatchStartTime").toString();
                                                                                                                                                                                                }
                                                                                                                                                                                                b = intervalFlag; // batch st-time or last email send time

                                                                                                                                                                                                DateFormat df = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss");
                                                                                                                                                                                                int diff;
                                                                                                                                                                                                long s1s=0, s2s=0;
                                                                                                                                                                                                Date d1, d2;

                                                                                                                                                                                                d1=df.parse(a);
                                                                                                                                                                                                s1s=d1.getTime();

                                                                                                                                                                                                d2=df.parse(b);
                                                                                                                                                                                                s2s=d2.getTime();

                                                                                                                                                                                                // get time difference in minutes
                                                                                                                                                                                                diff=(int) ((s1s - s2s) / (60 * 1000));
                                                                                                                                                                                                if(diff<0)
                                                                                                                                                                                                                diff = diff * -1;

                                                                                                                                                                                                if(diff>=interval){
                                                                                                                                                                                                                intEmailFlag = "send"; //send mail
                                                                                                                                                                                                                intervalFlag = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()); //last email send time
                                                                                                                                                                                                }
                                                                                                                                                                                }
                                                                                                                                                                }
                                                                                                                                                                if(intEmailFlag.equalsIgnoreCase("send")){

                                                                                                                                                                                //count the pass and fail for intermediate result
                                                                                                                                                                                int intFailCnt = Driver.countOccurences(Driver.messageBody, "Failed");
                                                                                                                                                                                int intPassCnt = Driver.countOccurences(Driver.messageBody, "Passed");

                                                                                                                                                                                // create the subject
                                                                                                                                                                                String intSubject = "Intermittent status - Passed :"+intPassCnt+", Failed :"+intFailCnt;
                                                                                                                                                                                // create the message body
                                                                                                                                                                                String intMessageBody = "\n\n\t\t **INTERMITTENT EMAIL** \n\n" + Driver.messageBody + "\n\n\tTestCase: '" + testCase_Id+ " " + dataSheet +  "' in progress.\n\n";
                                                                                                                                                                                System.out.println("INTERMITTENT Subject: " + intSubject);
                                                                                                                                                                                System.out.println("INTERMITTENT Message Body: " + intMessageBody);

                                                                                                                                                                                String intRcpntsLst = Driver.getValueFromEnvVariable("intermittent_email_recipients");
                                                                                                                                                                                int noOfIntRecpnts = Driver.countOccurences(intRcpntsLst,",");
                                                                                                                                                                                String intRcpntsArr[]= new String[1];

                                                                                                                                                                                // From  ::
                                                                                                                                                                                String from = "Automation";
                                                                                                                                                                                if(Driver.driverMap.containsKey("application_name")){
                                                                                                                                                                                                // From  :: ApplicationName_Automation _Group
                                                                                                                                                                                                if(! Driver.driverMap.get("application_name").toString().equals("")){
                                                                                                                                                                                                                from = Driver.driverMap.get("application_name").toString();
                                                                                                                                                                                                }
                                                                                                                                                                                }
                                                                                                                                                                                for(int intRcpntsCnt=0;intRcpntsCnt<=noOfIntRecpnts;intRcpntsCnt++){
                                                                                                                                                                                                
                                                                                                                                                                                                * Send the mail to recipients in the list one by one. If it is sent in a group
                                                                                                                                                                                                * one invalid e-mail will result in non delivery of mail to other folks in the list.
                                                                                                                                                                                                
                                                                                                                                                                                                intRcpntsArr[0]=intRcpntsLst.split(",")[intRcpntsCnt].trim();
                                                                                                                                                                                                try{
                                                                                                                                                                                                                postMail(intRcpntsArr, intSubject ,intMessageBody, from+"_AUTOMATION_GROUP");
                                                                                                                                                                                                }
                                                                                                                                                                                                catch(Exception e){
                                                                                                                                                                                                                e.printStackTrace();
                                                                                                                                                                                                                Driver.setLogMsg("error","Unable to send intermittent email to recipient "+ intRcpntsArr[0]);
                                                                                                                                                                                                }
                                                                                                                                                                                }
                                                                                                                                                                                // reset the flag
                                                                                                                                                                                intEmailFlag = "";

                                                                                                                                                                }
                                                                                                                                                }
                                                                                                                                }
                                                                                                                                // INTERMITTENT EMAIL - END

                                                                                                                                try {
                                                                                                                                                System.out.println("@@@@@@@@@@");
                                                                                                                                                if("CONTINUE".equalsIgnoreCase(Driver.driverMap.get("RESUME_ACTION_WHEN_FAILURE").toString())) {
                                                                                                                                                                Driver.updateExcelCellValue(runOrder, "Sheet1", i, "Execute_TestCase", "Executed");
                                                                                                                                                                System.out.println("((((((((((");
                                                                                                                                                                Driver.setLogMsg("info", "\"Execute_TestCase\" column of RunOrder set to \"Executed\" for TestCaes " + testCase_Id + " [Before Executing Test Case]");
                                                                                                                                                }
                                                                                                                                }catch(Exception e) {
                                                                                                                                                System.out.println("@@@@@@@@@@ exceptio is here");
                                                                                                                                                e.printStackTrace();
                                                                                                                                }
                                                                                                                                // Template 2 - execute the testcases
                                                                                                                                if(Driver.getValueFromEnvVariable("use_template2").equalsIgnoreCase("y") ||
                                                                                                                                                                Driver.getValueFromEnvVariable("use_template2").equalsIgnoreCase("yes")){
                                                                                                                                                //$$
                                                                                                                                                
                                                                                                                                String    testCaseId[]={testCase_Id+ ","+objMapName+","+app_type+","+dataSheet+","+dataDriveExcel+","+domain+","+testplan};
                                                                                                                                callScript("com.WLP.AutomationComponents.Common.DriverEngine.Driver",testCaseId);
                                                                                                                                                
                                                                                                                                }
                                                                                                                                else{// Template 1
                                                                                                                                                System.out.println("########3here");
                                                                                                                                                String testCaseId[]={testCase_Id+ ","+masterDataPoolName+","+objMapName+","+app_type+","+dataSheet+","+dataDriveExcel+","+domain+","+testplan};
                                                                                                                                                //$$ replace with call to the Driver here

                                                                                                                                                System.out.println("testCase_Id "+testCase_Id+" masterDataPoolName "+masterDataPoolName+" objMapName "+objMapName+" app_type "+app_type+" dataSheet "+dataSheet+" dataDriveExcel "+dataDriveExcel+" domain  "+domain+" testplan "+testplan);
                                                                                                                                                try{
                                                                                                                                                                
                                                                                                                                                                Driver.extentTest=Driver.extent.startTest(testCase_Id);
                                                                                                                                                                //extentTest=extent.startTest(testCase_Id);
                                                                                                                                                                //testCaseUnit=testCaseId;
                                                                                                                                                                //Class<?> testClass=Caller.class.getClassLoader().loadClass(JUnitTest.class.getCanonicalName());
                                                                                                                                                                //Result result=(new JUnitCore()).run(Request.method(testClass, "test"));
                                                                                                                                                                //Result result=(new JUnitCore()).run(JUnitTest.class);
                                                                                                                                                                //Result result=JUnitCore.runClasses(JUnitTest.class);
                                                                                                                                                                //System.out.println(result.getRunCount());
                                                                                                                                                                //Result result=JUnitCore.runClasses(JUnitTest.class);
                                                                                                                                                                //junit.startTest(testCase_Id);
                                                                                                                            Driver.main(testCaseId);
                                                                                                                            
                                                                                                                                                }
                                                                                                                                                catch(Exception e)
                                                                                                                                                {
                                                                                                                                                                                e.printStackTrace();
                                                                                                                                                }
                                                                                                                                                finally{
                                                                                                                                                                
                                                                                                                                                                if(Driver.findCol(sheet, "ReportToJIRA")!= -1){
                                                                                                                                                                                if(!(row.getCell(Driver.findCol(sheet, "ReportToJIRA"))==null)){
                                                                                                                                                                                                ReportToJIRA = row.getCell(Driver.findCol(sheet, "ReportToJIRA")).toString().trim();
                                                                                                                                                                                                if(ReportToJIRA.equalsIgnoreCase("Yes")||ReportToJIRA.equalsIgnoreCase("y"))
                                                                                                                                                                                                {
                                                                                                                                                                                                                //Driver.driverMap.put("ReportToJIRA","YES");
                                                                                                                                                                                                                if(Driver.findCol(sheet, "JIRATestID")!= -1){
                                                                                                                                                                                                                                if(!(row.getCell(Driver.findCol(sheet, "JIRATestID"))==null)){
                                                                                                                                                                                                                                                JIRATestID = row.getCell(Driver.findCol(sheet, "JIRATestID")).toString().trim();
                                                                                                                                                                                                                                                if(Driver.emailBoolFlagPass==true)
                                                                                                                                                                                                                                                                JIRAUtility.setResultInJira(JIRATestID,true);
                                                                                                                                                                                                                                                else if(Driver.emailBoolFlagFail==true)
                                                                                                                                                                                                                                                                JIRAUtility.setResultInJira(JIRATestID,false);
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                }
                                                                                                                                                                                                                else
                                                                                                                                                                                                                {
                                                                                                                                                                                                                                Driver.setLogMsg("info", "JIRATestID column not found");
                                                                                                                                                                                                                }
                                                
                                                                                                                                                                                                                //Driver.driverMap.put("JIRATestID",JIRATestID);
                                                
                                                                                                                                                                                                }
                                                                                                                                                                                }
                                                                                                                                                                }
                                                                                                                                                                else
                                                                                                                                                                {
                                                                                                                                                                                Driver.setLogMsg("info", "ReportToJIRA column not found");
                                                                                                                                                                }
                                                                                                                                                                
                                                                                                                                                                if(Driver.emailBoolFlagPass==true)
                                                                                                                                                                {
                                                                                                                                                                                Driver.extentTest.log(LogStatus.PASS,testCase_Id, "PASSED");
                                                                                                                                                                }
                                                                                                                                                                else if(Driver.emailBoolFlagFail==true)
                                                                                                                                                                {
                                                                                                                                                                                Driver.extentTest.log(LogStatus.FAIL,testCase_Id, "FAILED");
                                                                                                                                                                }
                                                                                                                                                                Driver.extent.endTest(Driver.extentTest);
                                                                                                                                                }
                                                                                                                                                
                                                                                                                                                //callScript("com.WLP.AutomationComponents.Common.DriverEngine.Driver",testCaseId);

                                                                                                                                }


                                                                                                                                try {
                                                                                                                                                if(Driver.driverMap.get("RESUME_ACTION_WHEN_FAILURE").toString().equalsIgnoreCase("RESTART") && Driver.driverMap.get("MARK_TC_COMPLETION").toString().equalsIgnoreCase("YES")) {
                                                                                                                                                                Driver.updateExcelCellValue(runOrder, "Sheet1", i, "Execute_TestCase", "Executed");
                                                                                                                                                                Driver.setLogMsg("info", "\"Execute_TestCase\" column of RunOrder set to \"Executed\" for TestCaes " + testCase_Id + " [After Executing Test Case]");
                                                                                                                                                }
                                                                                                                                }catch(Exception e){
                                                                                                                                                e.printStackTrace();
                                                                                                                                }
                                                                                                                                no_of_testcases_executed++;
                                                                                                                                if(should_restart) {
                                                                                                                                                if(no_of_testcases_executed >= restart_rft_after){
                                                                                                                                                                i = NumberOfRowsDataSheet+2;
                                                                                                                                                                mark_tc_completion = false;

                                                                                                                                                }
                                                                                                                                }
                                                                                                                                System.out.println("before message "+testCase_Id);
                                                                                                                                System.out.println("dataSheet "+dataSheet);
                                                                                                                                System.out.println("Driver.d "+Driver.driverMap.get("Data_Sheet_Id").toString());
                                                                                                                                createMessageBody(testCase_Id, dataSheet);
                                                                                                                                PrintExecutionResult(testCase_Id, dataSheet);
                                                                                                                                System.out.println("after message");                    
                                                                                                                                
                                                                                                                                Calendar calStart = Calendar.getInstance();
                                                                                                                                //SimpleDateFormat dfDay = new SimpleDateFormat("HH:mm:ss");
                                                                                                                                Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("TCStartTime").toString());
                                                                                                                                calStart.setTime(startDate);
                                                                                                                                Calendar calEnd = Calendar.getInstance();
                                                                                                                                Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("TCEndTime").toString());
                                                                                                                                calEnd.setTime(endDate);

                                                                                                                                long totalTime = (calEnd.getTimeInMillis()-calStart.getTimeInMillis())/(1000);
                                                                                                                                
                                                                                                                                Element testcase             = doc.createElement("testcase");
                                                                                                                                testcase.setAttribute("classname", "regression");
                                                                                                                                testcase.setAttribute("name", testCase_Id);
                                                                                                                                testcase.setAttribute("time", String.valueOf(totalTime));
                                                                                                                                
                                                                                                                                if(Driver.emailBoolFlagFail==true)
                                                                                                                                {
                                                                                                                                                Element testcase1Failed               = doc.createElement("failure");
                                                                                                                                                testcase1Failed.setAttribute("message", "test failure");
                                                                                                                                                testcase1Failed.appendChild(doc.createTextNode("Assertion Failed"));
                                                                                                                                                testcase.appendChild(testcase1Failed);
                                                                                                                                }
                                                                                                                                testsuite.appendChild(testcase);

                                                                                                                }
                                                                                                                else{
                                                                                                                                if(testCase_Id.equals("")){
                                                                                                                                                continue;
                                                                                                                                }
                                                                                                                                System.out.println("Test Case: "+ testCase_Id+" "+dataSheet +" not executed" );
                                                                                                                }
                                                                                                }
                                                                                }
                                                                                catch (FileNotFoundException e) {
                                                                                                e.printStackTrace();
                                                                                                //                            //logError("There is no Runorder.xls file in the specified path: " + "'"+runOrder+"'");
                                                                                }
                                                                                catch (Exception e) {
                                                                                                //            //logError("Exception occured in Caller :"+e.getMessage());
                                                                                                e.printStackTrace();
                                                                                }

                                                                                //$$}
                                                                                //$$
                                                                                int cntFail = Driver.countOccurences(Driver.messageBody, "Failed");
                                                                                //count of the total number of passed test cases
                                                                                int cntPass = Driver.countOccurences(Driver.messageBody, "Passed");

                                                                                testsuite.setAttribute("tests", String.valueOf(cntPass+cntFail));
                                                                                testsuite.setAttribute("failures", String.valueOf(cntFail));
                                                                                SimpleDateFormat endTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

                                                                                System.out.println("endTime :"+endTime.format(new Date())+"::"+endTime.getCalendar().getTimeInMillis());
                                                                                System.out.println("startTime :"+Driver.driverMap.get("BatchStartTime").toString()+"::"+ new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("BatchStartTime").toString()).getTime());

                                                                                // totalTime denotes the time for execution to finish (in seconds)
                                                                                long totalTime = (endTime.getCalendar().getTimeInMillis()-(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("BatchStartTime").toString()).getTime()))/(1000);
                                                                                TotalBatchTime=totalTime;
                                                                                
                                                                                testsuite.setAttribute("time", String.valueOf(totalTime));
                                                                                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                                                                                Transformer transformer = transformerFactory.newTransformer();
                                                                                DOMSource source = new DOMSource(doc);
                                                                                StreamResult result = new StreamResult(new File("./target/junittest.xml"));
                                                                                transformer.transform(source, result);
                                                                                System.out.println("JUNIT Format XML File saved!");
                                                                }
                                                                catch(Exception e){
                                                                                e.printStackTrace();
                                                                }finally{

                                                                                Driver.extent.flush();
                                                                                SimpleDateFormat endTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                                                                int h =0,m=0,s=0;
                                                                                try {
                                                                                                System.out.println("endTime :"+endTime.format(new Date())+"::"+endTime.getCalendar().getTimeInMillis());
                                                                                                System.out.println("startTime :"+Driver.driverMap.get("BatchStartTime").toString()+"::"+ new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("BatchStartTime").toString()).getTime());

                                                                                                // totalTime denotes the time for execution to finish (in seconds)
                                                                                                long totalTime = (endTime.getCalendar().getTimeInMillis()-(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("BatchStartTime").toString()).getTime()))/(1000);
                                                                                                TotalBatchTime=totalTime;
                                                                                                h = (int)                totalTime/(60*60);//find the hours
                                                                                                m = (int) (totalTime-(h*60*60))/(60);//minutes
                                                                                                s = (int) (totalTime-(h*60*60)-(m*60));//seconds

                                                                                                System.out.println("Total Time for batch run :"+h+"h:"+m+"m:"+s+"s");
                                                                                                //            //logInfo("Total Time for batch run :"+h+"h:"+m+"m:"+s+"s");

                                                                                } catch (Exception e1) {
                                                                                                e1.printStackTrace();
                                                                                }

                                                                                try{
                                                                                                //count of the total number of failed test cases
                                                                                                int cntFail = Driver.countOccurences(Driver.messageBody, "Failed");
                                                                                                //count of the total number of passed test cases
                                                                                                int cntPass = Driver.countOccurences(Driver.messageBody, "Passed");
                                                                                                                                                                                
                                                                                                creating the subject
                                                                                                //default subject as total number of pass and fail count
                                                                                                String subject = "Passed :"+cntPass+", Failed :"+cntFail;
                                                                                                //if the env var is set, append it in the beginning of the default value
                                                                                                if(! Driver.getValueFromEnvVariable("subject").equals("")){
                                                                                                                subject = Driver.getValueFromEnvVariable("subject")+" :- "+subject;
                                                                                                }

                                                                                                 if the execution has not reached until the execution of testcase but failed even before
                                                                                                * the start there will not be any pass or fail of test case. In which case
                                                                                                * Message :- Execution Unsuccessful!
                                                                                                * Subject :- Execution Failed
                                                                                                if((Driver.messageBody.contains("Passed") || Driver.messageBody.contains("Failed"))){}
                                                                                                else{
                                                                                                                Driver.messageBody = "Execution Unsuccessful!";
                                                                                                                subject = "Execution Failed";
                                                                                                }

                                                                                                //console statement needed for build forge execution
                                                                                                if(Driver.messageBody.contains("Failed") || subject.equals("Execution Failed")){
                                                                                                                System.out.println("Execution Complete : Failures Found!");
                                                                                                }else{
                                                                                                                System.out.println("Execution Complete : Test Passed!");
                                                                                                }


                                                                                                //create the excel result after the execution is complete
                                                                                                if(Driver.getValueFromEnvVariable("create_result_excel").equalsIgnoreCase("y") ||
                                                                                                                                Driver.getValueFromEnvVariable("create_result_excel").equalsIgnoreCase("yes")){

                                                                                                                String totalExecTime = h+"h:"+m+"m:"+s+"s";
                                                                                                                int rowsTotal = cntPass + cntFail;
                                                                                                                boolean result = true;
                                                                                                                result = createExecutionResult(rowsTotal,subject,Driver.messageBodyExcel,totalExecTime);
                                                                                                }

                                                                                                //Check if mail has to be sent after the batch execution from env var send_email
                                                                                                if(Driver.getValueFromEnvVariable("send_email").equalsIgnoreCase("yes") ||
                                                                                                                                Driver.getValueFromEnvVariable("send_email").equalsIgnoreCase("y")){
                                                                                                                DecimalFormat decForm = new DecimalFormat("0.0");

                                                                                                                //get the list of reciepients in comma separated format from env_var email_recipients
                                                                                                                String recList = Driver.getValueFromEnvVariable("email_recipients");
                                                                                                                //get the count of recipients
                                                                                                                int noOfRecipients = Driver.countOccurences(recList,",");
                                                                                                                String recipientsArray[]= new String[1];
                                                                                                                //String totalExecTime = "Total Execution Time:- "+h+"h:"+m+"m:"+s+"s\n\n";
                                                                                                                float passPercent = 0f;
                                                                                                                try {
                                                                                                                                passPercent = ((float)((float)cntPass/((float)cntPass + (float)cntFail))*100);
                                                                                                                }catch(NumberFormatException e) {
                                                                                                                                e.printStackTrace();
                                                                                                                                passPercent = 0;
                                                                                                                }
                                                                                                                float failPercent = 0f;
                                                                                                                try {
                                                                                                                                failPercent = ((float)((float)cntFail/((float)cntPass + (float)cntFail))*100);
                                                                                                                }catch(NumberFormatException e) {
                                                                                                                                e.printStackTrace();
                                                                                                                                failPercent = 0f;
                                                                                                                }

                                                                                                                String totalExecTime = "<table col=\"3\" style=\"border:2px solid #000000;border-collapse:collapse\"> " +
                                                                                                                                                "<tr > " +
                                                                                                                                                "<td colspan=\"3\" style=\"border:1px solid #000000;color:blue;font-size:15px;font-family:verdana;text-align:center;\"><strong>Test Execution Summary</strong></td> " +

                                                                                                                                                                                                "</tr> " +
                                                                                                                                                                                                "<tr > " +
                                                                                                                                                                                                "<td style=\"width:300px;border:1px solid #000000;color:blue;font-size:12px;font-family:verdana;\"><strong>Total Test Cases Executed</strong></td> " +
                                                                                                                                                                                                "<td style=\"width:100px;border:1px solid #000000;color:blue;font-size:10px;font-family:verdana;\">" + (cntPass + cntFail) + " Test Case(s)</td> " +
                                                                                                                                                                                                "<td style=\"width:100px;border:1px solid #FFFFFF;color:blue;font:10px normal verdana;\"></td> " +
                                                                                                                                                                                                "</tr> " +
                                                                                                                                                                                                "<tr > " +
                                                                                                                                                                                                "<td style=\"width:300px;border:1px solid #000000;color:green;font-size:12px;font-family:verdana;\"><strong>Number of Test Cases Passed</strong></td> " +
                                                                                                                                                                                                "<td style=\"width:100px;border:1px solid #000000;color:green;font-size:10px;font-family:verdana;text-align:center;\">" + cntPass + "</td> " +
                                                                                                                                                                                                "<td style=\"width:100px;border:1px solid #000000;color:green;font-size:10px;font-family:verdana;\">Pass % = " + decForm.format( passPercent ) + "%</td> " +
                                                                                                                                                                                                "</tr> " +
                                                                                                                                                                                                "<tr > " +
                                                                                                                                                                                                "<td style=\"width:300px;border:1px solid #000000;color:red;font-size:12px;font-family:verdana;\"><strong>Number of Test Cases Failed</strong></td> " +
                                                                                                                                                                                                "<td style=\"width:100px;border:1px solid #000000;color:red;font-size:10px;font-family:verdana;text-align:center;\">" + cntFail + "</td> " +
                                                                                                                                                                                                "<td style=\"width:100px;border:1px solid #000000;color:red;font-size:10px;font-family:verdana;\">Fail % = " + decForm.format( failPercent ) + "%</td> " +
                                                                                                                                                                                                "</tr>" +
                                                                                                                                                                                                "<tr > " +
                                                                                                                                                                                                "<td colspan=\"3\" style=\"border:1px solid #000000;color:blue;font-size:12px;font-family:verdana;text-align:center;\"><strong>Total Execution Time: </strong>"+h+"h:"+m+"m:"+s+"s</td> " +
                                                                                                                                                                                                "</tr> " +
                                                                                                                                                                                                "</table></br><hr>";

                                                                                                                Driver.messageBody = "<table style=\"border:2px solid #000000;border-collapse:collapse\"> " + Driver.messageBody + "</table></br><hr>";

                                                                                                                //From by default is
                                                                                                                //if application name is specified it will be applicationName__AUTOMATION_GROUP
                                                                                                                String from = "RFT";
                                                                                                                if(Driver.driverMap.containsKey("application_name")){
                                                                                                                                if(! Driver.driverMap.get("application_name").toString().equals("")){
                                                                                                                                                from = Driver.driverMap.get("application_name").toString();
                                                                                                                                }
                                                                                                                }

                                                                                                                 Call the post mail method once for every recipient. This is to avoid the
                                                                                                                * exception that occurs in case of invalid email id 
                                                                                                                for(int recCount=0;recCount<=noOfRecipients;recCount++){
                                                                                                                                recipientsArray[0]=recList.split(",")[recCount].trim();
                                                                                                                                System.out.println("Value :"+recipientsArray[0]);
                                                                                                                                try{
                                                                                                                                                //Call the mailing method, even in case of exception catch it and continue
                                                                                                                                                postMail(recipientsArray, subject ,totalExecTime+Driver.messageBody, from+"_AUTOMATION_GROUP");
                                                                                                                                }
                                                                                                                                catch(Exception e){
                                                                                                                                                e.printStackTrace();
                                                                                                                                                //logError("Unable to Email the recipient "+ recipientsArray[0]);
                                                                                                                                }
                                                                                                                }//end of mailing to all recipients
                                                                                                }
                                                                                }
                                                                                catch(MessagingException e){
                                                                                                e.printStackTrace();
                                                                                }catch(Exception e){
                                                                                                e.printStackTrace();
                                                                                                //logError("Error in Emailing in Caller ");
                                                                                }
                                                                                finally
                                                                                {
                                                                                                // get the script end time

                                                                                                //call app exit
                                                                                                //new Application_CustomFunctions().app_exit();

                                                                                                //((WebDriver)Driver.driverMap.get("WEBDRIVER")).close();
                                                                                }
                                                                                //Driver.frameworkReport.closeTxtFileWriter();

                                                                                //if(mark_tc_completion && Driver.driverMap.get("MARK_TC_COMPLETION").toString().equalsIgnoreCase("YES"))
                                                                                //Driver.updateLastUpdatedTimeStampInResumeHandler(true);
                                                                }
                                                }
                                                catch(Exception e){
                                                                e.printStackTrace();
                                                }finally {
                                                                System.out.println("in here finally exiting");

                                                                SimpleDateFormat endTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                                                int h =0,m=0,s=0;
                                                                try {
                                                                                System.out.println("endTime :"+endTime.format(new Date())+"::"+endTime.getCalendar().getTimeInMillis());
                                                                                System.out.println("startTime :"+Driver.driverMap.get("BatchStartTime").toString()+"::"+ new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("BatchStartTime").toString()).getTime());

                                                                                // totalTime denotes the time for execution to finish (in seconds)
                                                                                long totalTime = (endTime.getCalendar().getTimeInMillis()-(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("BatchStartTime").toString()).getTime()))/(1000);
                                                                                                                                                                
                                                                                h = (int)                totalTime/(60*60);//find the hours
                                                                                m = (int) (totalTime-(h*60*60))/(60);//minutes
                                                                                s = (int) (totalTime-(h*60*60)-(m*60));//seconds

                                                                                System.out.println("Total Time for batch run :"+h+"h:"+m+"m:"+s+"s");
                                                                                //            //logInfo("Total Time for batch run :"+h+"h:"+m+"m:"+s+"s");



                                                                                DecimalFormat decForm = new DecimalFormat("0.0");
                                                                                int cntFail = Driver.countOccurences(Driver.messageBody, "Failed");
                                                                                //count of the total number of passed test cases
                                                                                int cntPass = Driver.countOccurences(Driver.messageBody, "Passed");
                                                                                TotalFailed=cntFail;
                                                                                
                                                                                float passPercent = 0f;
                                                                                try {
                                                                                                passPercent = ((float)((float)cntPass/((float)cntPass + (float)cntFail))*100);
                                                                                }catch(NumberFormatException e) {
                                                                                                e.printStackTrace();
                                                                                                passPercent = 0;
                                                                                }
                                                                                float failPercent = 0f;
                                                                                try {
                                                                                                failPercent = ((float)((float)cntFail/((float)cntPass + (float)cntFail))*100);
                                                                                }catch(NumberFormatException e) {
                                                                                                e.printStackTrace();
                                                                                                failPercent = 0f;
                                                                                }


                                                                                //TableBuilder tb= new TableBuilder();  
                                                                                String totalExecTime = "\n\nTest Execution Summary\n" +
                                                                                                                "Total Test Cases Executed:\t\t\t\t" + (cntPass + cntFail) + " Test Case(s)\n" +
                                                                                                                "Number of Test Cases Passed:\t\t\t\t" + cntPass + "\t\t\tPass % = " + decForm.format( passPercent ) + "%\n" +
                                                                                                                "Number of Test Cases Failed:\t\t\t\t" + cntFail + "\t\t\tFail % = " + decForm.format( failPercent ) + "%\n" +
                                                                                                                "Total Execution Time:\t\t\t\t"+h+"h:"+m+"m:"+s+"s\n";


                                                                                System.out.println(totalExecTime);

                                                                                String details[]=new String[3];
                                                                                details[0]="TestCaseId";
                                                                                details[1]="Result";
                                                                                details[2]="Execution Time";
                                                                                messagetoPrint.add(0,details);

                                                                                for (String[] arr : messagetoPrint) 
                                                                                {
                                                                                                //System.out.println(formatter.format("%-50s %-20s %-20s\n",arr[0], arr[1], arr[2]));
                                                                                                System.out.format("%-50s %-20s %-20s\n",arr[0], arr[1], arr[2]);
                                                                                }

                                                                } catch (Exception e1) {
                                                                                e1.printStackTrace();
                                                                }
                                                                new Application_CustomFunctions().app_exit();


                                                }}
                }
                *//**
                * @Description This method is used to send the execution result via e-mail. If the excel
                * reporting enabled it will send the result excel as an attachment.
                * @param
                * recipients[]: It has one email id per execution.
                * subject: Subject of the email. Value env var EMAIL SETTINGS -> subject is used if specified else
                *                                            the default value Passed:#, Failed:#.
                * message: The driver variable messageBody which has the complete execution result in the format
                *                                            TestcaseId_dataID_DataSheetName Passed/Failed separeted by (new line)'\n'.
                * from:                Either _AUTOMATION_GROUP or ApplicationName_AUTOMATION_GROUP.
                * *//*

                public static void postMail( String recipients[], String subject, String message , String from) throws MessagingException
                {
                                try{
                                                //message.replaceAll("\\^", "");
                                                boolean debug = false;

                                                //Set the host smtp address
                                                Properties props = new Properties();
                                                //Get Wellpoint SMTP server name: "mail.smtp.host", "ACPWVBE016.us.ad.wellpoint.com"
                                                String smtp = Driver.getValueFromEnvVariable("mail_smtp_server");
                                                props.put(smtp.split(",")[0],smtp.split(",")[1]);
                                                // create some properties and get the default Session
                                                Session session = Session.getDefaultInstance(props, null);
                                                session.setDebug(debug);

                                                // create a message
                                                Message msg = new MimeMessage(session);

                                                // set the from and to address
                                                InternetAddress addressFrom = new InternetAddress(from);
                                                msg.setFrom(addressFrom);

                                                InternetAddress[] addressTo = new InternetAddress[recipients.length];
                                                for (int i = 0; i < recipients.length; i++)
                                                {
                                                                addressTo[i] = new InternetAddress(recipients[i]);
                                                }
                                                msg.setRecipients(Message.RecipientType.TO, addressTo);

                                                // Setting the Subject and Content Type
                                                msg.setSubject(subject);
                                                MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();

                                                 Attach the result report excel if the e-mail is sent at then end of batch run.
                                                * The excel is attached iff there are failed testcases.
                                                * 
                                                if( (intEmailFlag.equalsIgnoreCase("send")==false) &&
                                                                                (Driver.getValueFromEnvVariable("create_result_excel").equalsIgnoreCase("y") ||
                                                                                                                Driver.getValueFromEnvVariable("create_result_excel").equalsIgnoreCase("yes"))){

                                                                // add handlers for main MIME types
                                                                mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
                                                                mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
                                                                mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
                                                                mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
                                                                mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
                                                                CommandMap.setDefaultCommandMap(mc);

                                                                // create and fill the first message part
                                                                MimeBodyPart mbp1 = new MimeBodyPart();
                                                                mbp1.setContent(message, "text/html");
                                                                mbp1.setContent(new String(message.toString().getBytes(), "iso-8859-1"), "text/html; charset=iso-8859-1");
                                                                mbp1.setHeader("MIME-Version" , "1.0" );
                                                                //mbp1.setHeader("Content-Type" , mbp1.getContentType() );
                                                                //mbp1.setText(message);

                                                                // create the second message part
                                                                MimeBodyPart mbp2 = new MimeBodyPart();

                                                                // attach the file to the message
                                                                FileDataSource fds = new FileDataSource(Driver.driverMap.get("result_excel").toString());
                                                                mbp2.setDataHandler(new DataHandler(fds));
                                                                mbp2.setFileName(fds.getName());

                                                                // create the Multipart and add its parts to it
                                                                Multipart mp = new MimeMultipart();
                                                                mp.addBodyPart(mbp1);
                                                                mp.addBodyPart(mbp2);

                                                                
                                                                ArrayList ar=new ArrayList();
                                                                if(Driver.driverMap.containsKey("DataSheetList"))                                                                                                                                          
                                                                {
                                                                                ar=         (ArrayList) Driver.driverMap.get("DataSheetList");
                                                                                
                                                                                for(int i=0;i<ar.size();i++)
                                                                                {
                                                                                                MimeBodyPart mbp3 = new MimeBodyPart();
                                                                                                fds = new FileDataSource(Driver.getValueFromEnvVariable(ar.get(i).toString()).toString());
                                                                                                mbp3.setDataHandler(new DataHandler(fds));
                                                                                                mbp3.setFileName(fds.getName());
                                                                                                mp.addBodyPart(mbp3);
                                                                                                //mbp2.attachFile(Driver.getValueFromEnvVariable(ar.get(i).toString()).toString());
                                                                                }
                                                                }
                                                                if(Driver.driverMap.containsKey("screen_shot_path"))                                                                                                                                
                                                                {
                                                                                ar=         (ArrayList) Driver.driverMap.get("screen_shot_path");
                                                                                for(int i=0;i<ar.size();i++)
                                                                                {
                                                                                                MimeBodyPart mbp3 = new MimeBodyPart();
                                                                                                fds = new FileDataSource(ar.get(i).toString());
                                                                                                mbp3.setDataHandler(new DataHandler(fds));
                                                                                                mbp3.setFileName(fds.getName());
                                                                                                mp.addBodyPart(mbp3);
                                                                                                //mbp2.attachFile(Driver.getValueFromEnvVariable(ar.get(i).toString()).toString());
                                                                                }
                                                                }
                                                                
                                                                
                                                                
                                                                
                                                                if(Driver.driverMap.containsKey("Log_To_Add"))                                                                                                                                            
                                                                {
                                                                                ar=         (ArrayList) Driver.driverMap.get("Log_To_Add");
                                                                                for(int i=0;i<ar.size();i++)
                                                                                {
                                                                                                MimeBodyPart mbp3 = new MimeBodyPart();
                                                                                                fds = new FileDataSource(ar.get(i).toString());
                                                                                                mbp3.setDataHandler(new DataHandler(fds));
                                                                                                mbp3.setFileName(fds.getName());
                                                                                                mp.addBodyPart(mbp3);
                                                                                                //mbp2.attachFile(Driver.getValueFromEnvVariable(ar.get(i).toString()).toString());
                                                                                }
                                                                }
                                                                // add the Multipart to the message
                                                                msg.setContent(mp);
                                                                
                                                                if(Driver.driverMap.containsKey("ExtentReportsLocationFileName"))                                                                                                                                    
                                                                {
                                                                                MimeBodyPart mbp3 = new MimeBodyPart();
                                                                                fds = new FileDataSource(Driver.driverMap.get("ExtentReportsLocationFileName").toString());
                                                                                mbp3.setDataHandler(new DataHandler(fds));
                                                                                mbp3.setFileName(fds.getName());
                                                                                mp.addBodyPart(mbp3);
                                                                                //mbp2.attachFile(Driver.getValueFromEnvVariable(ar.get(i).toString()).toString());
                                                                }
                                                                // add the Multipart to the message
                                                                msg.setContent(mp);
                                                                System.out.println("Sending E-mail with Excel Result");

                                                }//send the e-mail without the excel report as an attachment.
                                                else{
                                                                msg.setContent(new String(message.toString().getBytes(), "iso-8859-1"), "text/html; charset=iso-8859-1");
                                                                //msg.setContent( content );
                                                                msg.setHeader("MIME-Version" , "1.0" );
                                                }

                                                //Send the mail
                                                Transport.send(msg);
                                                if(intEmailFlag.equalsIgnoreCase("send"))
                                                                Driver.setLogMsg("info", "Intermittent email succesfully sent to recepients");
                                                else
                                                                Driver.setLogMsg("info", "Email succesfully sent to recepients");
                                }
                                catch(Exception e){
                                                e.printStackTrace();
                                                Driver.setLogMsg("error", "Cannot send email. Exception:"+e.getMessage() );
                                }
                }


                *//**
                * @description This method generates the messagebody string required for e-mail and excel updates
                * @param
                * testcase -name of the test case
                * datasheet - name of the data sheet
                * *//*
                private static String createMessageBody(String testCase, String dataSheet){

                                int n = 0, flag=0;

                                 this check is to ensure the test case specified in env var exclude_tc_frm_summ_report
                                * is excluded from the reporting
                                * 

                                try{
                                                n=Driver.countOccurences(Driver.getValueFromEnvVariable("exclude_tc_frm_summ_report"), ",");
                                }
                                catch(Exception e){
                                                e.printStackTrace();
                                                //do nothing
                                }
                                for (int i=0;i<=n;i++){
                                                if(Driver.getValueFromEnvVariable("exclude_tc_frm_summ_report").split(",")[i].trim().equals(testCase)){
                                                                flag=1;
                                                                //break if the test case has to be executed
                                                                break;
                                                }
                                }
                                // if the flag is 0 add the details to the messagebody
                                if(flag==0){

                                                // Get the test case in the format testcase name ds_id datasheet
                                                String sheetName = "Sheet1";

                                                if(dataSheet.equals("")){
                                                                sheetName = "";
                                                }else{
                                                                System.out.println("$$$$$$$$$4");
                                                                dataSheet=Driver.driverMap.get("Data_Sheet_Id").toString();//ds id
                                                                System.out.println("$$$$$$$$$5");
                                                                sheetName = Driver.driverMap.get("Data_Sheet_Name").toString();//data sheet name
                                                                System.out.println("$$$$$$$$$6");
                                                }
                                                String testCase_Id = testCase + " " + dataSheet + " " + sheetName;
                                                testCase_Id = testCase_Id.trim();
                                                String spaces = " ";

                                                 43 is to properly align the result in the email. We add as many spaces required to
                                                * make all the testcase to be of equal length 
                                                for(int i=1;i<(43-testCase_Id.length());i++){
                                                                spaces = spaces+" ";
                                                }
                                                System.out.println("TCEndTime:"+Driver.driverMap.get("TCEndTime").toString());
                                                System.out.println("TCStartTime:"+Driver.driverMap.get("TCStartTime").toString());
                                                //driverMap.put("TCEndTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date())+"");
                                                try{
                                                                Calendar calStart = Calendar.getInstance();
                                                                //SimpleDateFormat dfDay = new SimpleDateFormat("HH:mm:ss");
                                                                Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("TCStartTime").toString());
                                                                calStart.setTime(startDate);
                                                                Calendar calEnd = Calendar.getInstance();
                                                                Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("TCEndTime").toString());
                                                                calEnd.setTime(endDate);

                                                                long totalTime = (calEnd.getTimeInMillis()-calStart.getTimeInMillis())/(1000);
                                                                int h = (int)          totalTime/(60*60);//find the hours
                                                                int m = (int) (totalTime-(h*60*60))/(60);//minutes
                                                                int s = (int) (totalTime-(h*60*60)-(m*60));//seconds
                                                                System.out.println(h+":"+m+":"+s);
                                                                int commaCnt = 0;
                                                                String cellValue = "";
                                                                if(Driver.emailBoolFlagFail == true){
                                                                                //Driver.messageBody = Driver.messageBody+"\n"+testCase_Id+spaces+"Failed"+"\t\t\t\t"+h+":"+m+":"+s ;
                                                                                Driver.messageBody = Driver.messageBody + "<tr><td style=\"width:400px;text-align:left;border:1px solid #000000;color:red;font-size:10px;font-family:verdana;\">" + testCase_Id + "</td>" ;
                                                                                Driver.messageBody = Driver.messageBody + "<td style=\"width:100px;text-align:center;border:1px solid #000000;color:red;font-size:10px;font-family:verdana;\">Failed</td>" ;
                                                                                Driver.messageBody = Driver.messageBody + "<td style=\"width:200px;text-align:center;border:1px solid #000000;color:red;font-size:10px;font-family:verdana;\">" + h+"hrs:"+m+"mins:"+s + "secs</td></tr>" ;

                                                                                Driver.messageBodyExcel = Driver.messageBodyExcel+"\n"+testCase_Id+spaces+"Failed"+"\t\t\t\t"+h+":"+m+":"+s ;
                                                                }
                                                                else if(Driver.emailBoolFlagPass == true){
                                                                                //Driver.messageBody = Driver.messageBody+"\n"+testCase_Id+spaces+"Passed"+"\t\t\t\t"+h+":"+m+":"+s ;
                                                                                Driver.messageBody = Driver.messageBody + "<tr><td style=\"width:400px;text-align:left;border:1px solid #000000;color:green;font-size:10px;font-family:verdana;\">" + testCase_Id + "</td>" ;
                                                                                Driver.messageBody = Driver.messageBody + "<td style=\"width:100px;text-align:center;border:1px solid #000000;color:green;font-size:10px;font-family:verdana;\">Passed</td>" ;
                                                                                Driver.messageBody = Driver.messageBody + "<td style=\"width:200px;text-align:center;border:1px solid #000000;color:green;font-size:10px;font-family:verdana;\">" + h+"hrs:"+m+"mins:"+s + "secs</td></tr>" ;

                                                                                Driver.messageBodyExcel = Driver.messageBodyExcel+"\n"+testCase_Id+spaces+"Passed"+"\t\t\t\t"+h+":"+m+":"+s ;
                                                                }

                                                                try{
                                                                                if(Driver.getValueFromEnvVariable("additional_excel_col").equals("")==false){
                                                                                                String AddnlCol = Driver.getValueFromEnvVariable("additional_excel_col");
                                                                                                String hashMap = Driver.getValueFromEnvVariable("addnl_col_hashmap_value");
                                                                                                commaCnt = Driver.countOccurences(AddnlCol, ",");
                                                                                                if((commaCnt == Driver.countOccurences(hashMap, ",")) == false){
                                                                                                                //logError("Number of additional_excel_col \""+AddnlCol+"\" does not match addnl_col_hashmap_value in "+hashMap);
                                                                                                }
                                                                                                commaCnt = commaCnt+1;
                                                                                                for(int j=0;j<commaCnt;j++){
                                                                                                                String map = hashMap.split(",")[j].split(":")[0].trim();
                                                                                                                String mKey = hashMap.split(",")[j].split(":")[1].trim();
                                                                                                                //TempCell = row.createCell(3+j);

                                                                                                                String hMap = map;
                                                                                                                String mapKey = mKey+","+testCase.split(" ")[0];


                                                                                                                // Reading the key value from hashmap. do nothing if key is not present.
                                                                                                                try{
                                                                                                                                int flagMapExist = 0;
                                                                                                                                HashMap individualMap = null;
                                                                                                                                HashMap newMap = new HashMap();
                                                                                                                                Set set = CustomFunctions.masterMap.keySet();
                                                                                                                                //Set set = newMap.keySet();
                                                                                                                                Iterator iter = set.iterator();
                                                                                                                                while (iter.hasNext()) {
                                                                                                                                                String Itrval = (String) iter.next();

                                                                                                                                                if (hMap.equalsIgnoreCase(Itrval)) {
                                                                                                                                                                individualMap = (HashMap)CustomFunctions.masterMap.get(Itrval);
                                                                                                                                                                flagMapExist = 1;
                                                                                                                                                                break;
                                                                                                                                                }
                                                                                                                                }
                                                                                                                                if (flagMapExist == 0){
                                                                                                                                                cellValue = "";
                                                                                                                                                Driver.setLogMsg("info", "The specified hash map: "+hMap+" does not exist");
                                                                                                                                                continue;
                                                                                                                                                //return "";
                                                                                                                                }

                                                                                                                                //String value = "";
                                                                                                                                if( individualMap.containsKey(mapKey)){
                                                                                                                                                cellValue = individualMap.get(mapKey).toString();
                                                                                                                                }
                                                                                                                                //return value;
                                                                                                                                else {
                                                                                                                                                cellValue = "";
                                                                                                                                                Driver.setLogMsg("info", "the specified hashmap "+hMap+" does not contain the key "+ mapKey +".");
                                                                                                                                }
                                                                                                                                Driver.messageBodyExcel = Driver.messageBodyExcel + spaces +"^"+ cellValue ;
                                                                                                                }

                                                                                                                catch(Exception e){
                                                                                                                                e.printStackTrace();
                                                                                                                                Driver.setLogMsg("error", "Error in reading value from HashMap "+ map);
                                                                                                                                //return "";
                                                                                                                }


                                                                                                }
                                                                                }
                                                                }catch(Exception e){
                                                                                e.printStackTrace();
                                                                                String AddnlCol = Driver.getValueFromEnvVariable("additional_excel_col");
                                                                                String hashMap = Driver.getValueFromEnvVariable("addnl_col_hashmap_value");
                                                                                //logError("Error in writing additional columns "+AddnlCol+" in the hashmap "+hashMap);
                                                                }
                                                                
                                                                //check if the testcase had failed or passed and set testcaseId with spaces and the result
                                                                if(Driver.emailBoolFlagFail == true){
                                                                                Driver.messageBody = Driver.messageBody+"\n"+testCase_Id+spaces+"Failed"+spaces+h+":"+m+":"+s+spaces+cellValue ;
                                                                }
                                                                else if(Driver.emailBoolFlagPass == true){
                                                                                Driver.messageBody = Driver.messageBody+"\n"+testCase_Id+spaces+"Passed"+spaces+h+":"+m+":"+s+spaces+cellValue ;
                                                                }
                                                }catch(Exception e){
                                                                e.printStackTrace();
                                                }
                                }
                                //return the messagebody
                                return Driver.messageBody;
                }
                // END java code snippet createMessageBody


                *//**
                * @description This method creates the excel report at the end of the batch execution
                * @param
                * rowsTotal :total number of rows in excel
                * subject :pass fail count along with user specified value
                * messageBody :Complete execution result
                * @throws Exception 
                 * *//*
                public static boolean createExecutionResult
                (int rowsTotal, String subject,String messageBody, String totalExecTime) throws Exception{
                                //create a workbook and file IO stream to write to excel
                                Workbook workbook = null;
                                FileOutputStream fileOut = null;
                                //variable used to store failure statements corresponding to the test case
                                String failures= "";

                                //variable used to create adjacent cells in excel to store the consecutive failure values
                                int fails=3;
                                float passPercent = 0f;
                                float failPercent = 0f;
                                String fileName=null;
                                int cntPass=0;
                                int cntFail=0;
                                //generating the key which is the testcase name ds_id datasheetname_fail
                                String key = "";
                                try{
                                                //result of excel creating and updation
                                                boolean result = true;

                                                 messagebody will be put in row starting from 4th row
                                                * subject will be in the first 2 rows 
                                                int startRow=3;
                                                //making the messagebody string uniform
                                                messageBody = messageBody.replaceAll("\n\n", "\n")+"\n";

                                                if(Driver.getValueFromEnvVariable("create_result_excel").equalsIgnoreCase("y") ||
                                                                                Driver.getValueFromEnvVariable("create_result_excel").equalsIgnoreCase("yes")){
                                                                //get the path from the env var result_excel_path
                                                                fileName = Driver.getValueFromEnvVariable("result_excel_path");

                                                                //if the filename is log_file_name then replace the filename with the RFT log file name
                                                                           if(fileName.contains("log_file_name")){
                                                                                System.out.println("Excel file Name :" +ScriptPlayback.getLogName());
                                                                                fileName = fileName.replace("log_file_name", ScriptPlayback.getLogName());
                                                                }

                                                                //generate the excel workbook
                                                                String extension = ".xls";
                                                                if(fileName.contains(".xlsx")){
                                                                                workbook = new XSSFWorkbook();
                                                                                extension = ".xlsx";
                                                                }else{
                                                                                workbook = (Workbook) new HSSFWorkbook();
                                                                }

                                                                if the env var result_file_name_format is set to date-time append the batch start time with the name
                                                                * before the extension 
                                                                if(Driver.getValueFromEnvVariable("result_file_name_format").equalsIgnoreCase("date-time")){
                                                                                fileName = fileName.split(".xls")[0]+"_"+Driver.driverMap.get("BatchStartTime").toString().replace(":", ".").replace("-", ".")+extension;
                                                                }

                                                                //temp variable to store the messagebody values
                                                                String rowContent = "";
                                                                //temp variable to store the testcase from messagebody
                                                                String testCase= "";
                                                                //temp variable to store the pass / fail from messagebody
                                                                String status = "";
                                                                String time = "";

                                                                //store the value in driver hash map
                                                                Driver.driverMap.put("result_excel",fileName);

                                                                //create an output stream
                                                                fileOut = new FileOutputStream(fileName);
                                                                //create the result sheet with name - Result
                                                                Sheet sheet = workbook.createSheet("Result");
                                                                sheet.autoSizeColumn(1);
                                                                //styling
                                                                CellStyle style = workbook.createCellStyle();
                                                                Font font = workbook.createFont();
                                                                font.setFontHeightInPoints((short)10);
                                                                font.setFontName("Calibri");
                                                                style.setWrapText(true);
                                                                style.setFont(font);
                                                                style.setBorderLeft(CellStyle.BORDER_THIN);
                                                                style.setBorderRight(CellStyle.BORDER_THIN);
                                                                style.setBorderTop(CellStyle.BORDER_THIN);
                                                                style.setBorderBottom(CellStyle.BORDER_THIN);
                                                                style.setAlignment(CellStyle.ALIGN_CENTER);


                                                                //redFontHeadingStyle
                                                                CellStyle redFontHeadingStyle = workbook.createCellStyle();
                                                                redFontHeadingStyle.setFillBackgroundColor(new HSSFColor.BLUE().getIndex());
                                                                Font redFontHeading = workbook.createFont();
                                                                redFontHeading.setFontHeightInPoints((short)12);
                                                                redFontHeading.setFontName("Calibri");
                                                                redFontHeading.setBoldweight(Font.BOLDWEIGHT_BOLD);
                                                                redFontHeading.setColor(new HSSFColor.RED().getIndex());
                                                                redFontHeadingStyle.setWrapText(true);
                                                                redFontHeadingStyle.setFont(redFontHeading);
                                                                redFontHeadingStyle.setBorderLeft(CellStyle.BORDER_THIN);
                                                                redFontHeadingStyle.setBorderRight(CellStyle.BORDER_THIN);
                                                                redFontHeadingStyle.setBorderTop(CellStyle.BORDER_THIN);
                                                                redFontHeadingStyle.setBorderBottom(CellStyle.BORDER_THIN);
                                                                redFontHeadingStyle.setAlignment(CellStyle.ALIGN_CENTER);


                                                                //redFontNormalStyle
                                                                //styling
                                                                CellStyle redFontNormalStyle = workbook.createCellStyle();
                                                                Font redFontNormal = workbook.createFont();
                                                                redFontNormal.setFontHeightInPoints((short)10);
                                                                redFontNormal.setFontName("Calibri");
                                                                redFontNormal.setColor(new HSSFColor.RED().getIndex());
                                                                redFontNormalStyle.setFont(redFontNormal);
                                                                redFontNormalStyle.setBorderLeft(CellStyle.BORDER_THIN);
                                                                redFontNormalStyle.setBorderRight(CellStyle.BORDER_THIN);
                                                                redFontNormalStyle.setBorderTop(CellStyle.BORDER_THIN);
                                                                redFontNormalStyle.setBorderBottom(CellStyle.BORDER_THIN);
                                                                redFontNormalStyle.setAlignment(CellStyle.ALIGN_CENTER);


                                                                //headerStyle
                                                                CellStyle headerStyle = workbook.createCellStyle();
                                                                headerStyle.setFillBackgroundColor(new HSSFColor.BLUE().getIndex());
                                                                Font headerFont = workbook.createFont();
                                                                headerFont.setFontHeightInPoints((short)12);
                                                                headerFont.setFontName("Calibri");
                                                                headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
                                                                headerFont.setColor(new HSSFColor.BLUE().getIndex());

                                                                headerStyle.setFont(headerFont);
                                                                headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
                                                                headerStyle.setBorderRight(CellStyle.BORDER_THIN);
                                                                headerStyle.setBorderTop(CellStyle.BORDER_THIN);
                                                                headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
                                                                headerStyle.setAlignment(CellStyle.ALIGN_CENTER);

                                                                //ReportTitleStyle
                                                                CellStyle ReportTitleStyle = workbook.createCellStyle();
                                                                ReportTitleStyle.setFillBackgroundColor(new HSSFColor.BLUE().getIndex());
                                                                Font ReportTitleStyleFont = workbook.createFont();
                                                                ReportTitleStyleFont.setFontHeightInPoints((short)16);
                                                                ReportTitleStyleFont.setFontName("Calibri");
                                                                ReportTitleStyleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
                                                                ReportTitleStyleFont.setColor(new HSSFColor.BLUE().getIndex());

                                                                ReportTitleStyle.setFont(ReportTitleStyleFont);
                                                                ReportTitleStyle.setBorderLeft(CellStyle.BORDER_THIN);
                                                                ReportTitleStyle.setBorderRight(CellStyle.BORDER_THIN);
                                                                ReportTitleStyle.setBorderTop(CellStyle.BORDER_THIN);
                                                                ReportTitleStyle.setBorderBottom(CellStyle.BORDER_THIN);
                                                                ReportTitleStyle.setAlignment(CellStyle.ALIGN_CENTER);

                                                                //create row of 0
                                                                Row row = null;
                                                                int currentRowIndex = 0;
                                                                row = sheet.createRow(currentRowIndex);

                                                                Cell TempCell = row.createCell(0);

                                                                *//** Test Execution Summary **//*

                                                                TempCell.setCellValue("Test Execution Summary");
                                                                TempCell.setCellStyle(ReportTitleStyle);

                                                                TempCell = row.createCell(1);
                                                                TempCell.setCellStyle(ReportTitleStyle);

                                                                TempCell = row.createCell(2);
                                                                TempCell.setCellStyle(ReportTitleStyle);

                                                                sheet.addMergedRegion(new CellRangeAddress(
                                                                                                0, //first row (0-based)
                                                                                                0, //last row  (0-based)
                                                                                                0, //first column (0-based)
                                                                                                2  //last column  (0-based)
                                                                                                ));

                                                                currentRowIndex = currentRowIndex + 1;


                                                                *//** Summary **//*

                                                                if(subject.contains(":")){

                                                                                if(subject.contains(":-")){
                                                                                                //user defined subject is set at cell 2,1
                                                                                                //row.createCell(1).setCellValue(subject.split(":-")[0].trim());
                                                                                                //subject is set to pass fail count
                                                                                                subject = subject.split(":-")[1].trim();
                                                                                }

                                                                                // Pass Percentage
                                                                                cntPass = Integer.parseInt(subject.split(",")[0].split(":")[1]);
                                                                                cntFail = Integer.parseInt(subject.split(",")[1].split(":")[1]);
                                                                                passPercent = 0f;

                                                                                try {
                                                                                                passPercent = ((float)((float)cntPass/((float)cntPass + (float)cntFail))*100);
                                                                                }catch(NumberFormatException e) {
                                                                                                e.printStackTrace();
                                                                                                passPercent = 0;
                                                                                }

                                                                                failPercent = 0f;
                                                                                try {
                                                                                                failPercent = ((float)((float)cntFail/((float)cntPass + (float)cntFail))*100);
                                                                                }catch(NumberFormatException e) {
                                                                                                e.printStackTrace();
                                                                                                failPercent = 0f;
                                                                                }

                                                                                //currentRowIndex = currentRowIndex + 1;
                                                                                row = sheet.createRow(currentRowIndex);
                                                                                TempCell = row.createCell(0);
                                                                                TempCell.setCellValue("Total Test Cases Executed");
                                                                                TempCell.setCellStyle(headerStyle);

                                                                                TempCell = row.createCell(1);
                                                                                TempCell.setCellValue((cntPass + cntFail) + " Test Cases");
                                                                                TempCell.setCellStyle(style);

                                                                                //contents from messagebody has to start from 4th row

                                                                                //Set cell content 2,1 to Passed and pass count
                                                                                int passRow = currentRowIndex + 1;
                                                                                //Set cell content 3,1 to failed and fail count
                                                                                int failRow = passRow+1;
                                                                                startRow = failRow + 3;
                                                                                // if the subject contains :- for the user appended subject set the part before


                                                                                //create and add passed content
                                                                                row = sheet.createRow(passRow);
                                                                                TempCell = row.createCell(0);
                                                                                TempCell.setCellValue("Number of Test Cases Passed");
                                                                                TempCell.setCellStyle(headerStyle);

                                                                                TempCell = row.createCell(1);
                                                                                TempCell.setCellValue(subject.split(",")[0].split(":")[1]);
                                                                                TempCell.setCellStyle(style);


                                                                                TempCell = row.createCell(2);
                                                                                TempCell.setCellValue("Pass % = " + passPercent + "%");
                                                                                TempCell.setCellStyle(style);

                                                                                //create and add failed content
                                                                                row = sheet.createRow(failRow);
                                                                                TempCell = row.createCell(0);
                                                                                TempCell.setCellValue("Number of Test Cases Failed");
                                                                                TempCell.setCellStyle(redFontHeadingStyle);

                                                                                TempCell = row.createCell(1);
                                                                                TempCell.setCellValue(subject.split(",")[1].split(":")[1]);
                                                                                TempCell.setCellStyle(style);

                                                                                TempCell = row.createCell(2);
                                                                                TempCell.setCellValue("Fail % = " + failPercent + "%");
                                                                                TempCell.setCellStyle(redFontNormalStyle);

                                                                                currentRowIndex = failRow + 1;
                                                                                row = sheet.createRow(currentRowIndex);
                                                                                TempCell = row.createCell(0);
                                                                                TempCell.setCellValue("Total Execution Time");
                                                                                TempCell.setCellStyle(headerStyle);

                                                                                TempCell = row.createCell(1);
                                                                                TempCell.setCellValue(totalExecTime);
                                                                                TempCell.setCellStyle(style);

                                                                }
                                                                else{
                                                                                row = sheet.createRow(1);
                                                                                //subject will be execution Unsuccessful!
                                                                                TempCell = row.createCell(0);
                                                                                TempCell.setCellValue(subject);
                                                                                TempCell.setCellStyle(style);
                                                                }

                                                                 set the messagebody 
                                                                if(messageBody.contains("!")){
                                                                                //set row 2,1 to Execution failed.
                                                                                sheet.createRow(startRow).createCell(0).setCellValue(messageBody.trim());
                                                                }
                                                                else{
                                                                                //create the 4th row in excel and set the cell contents
                                                                                row = sheet.createRow(startRow);
                                                                                TempCell = row.createCell(0);
                                                                                TempCell.setCellValue("TestCaseID");
                                                                                TempCell.setCellStyle(headerStyle);
                                                                                TempCell = row.createCell(1);
                                                                                TempCell.setCellValue("Result");
                                                                                TempCell.setCellStyle(headerStyle);
                                                                                TempCell = row.createCell(2);
                                                                                TempCell.setCellValue("Execution Time");
                                                                                TempCell.setCellStyle(headerStyle);
                                                                                // filling the data for the test cases for the additional columns
                                                                                int commaCnt = 0;
                                                                                if(Driver.getValueFromEnvVariable("additional_excel_col").equals("")==false){
                                                                                                String AddnlCol = Driver.getValueFromEnvVariable("additional_excel_col");
                                                                                                commaCnt = Driver.countOccurences(AddnlCol, ",")+1;
                                                                                                for(int i=0; i<commaCnt; i++){
                                                                                                                TempCell = row.createCell(3+i);
                                                                                                                TempCell.setCellValue(AddnlCol.split(",")[i]);
                                                                                                                TempCell.setCellStyle(headerStyle);
                                                                                                }
                                                                                }
                                                                                TempCell = row.createCell(3+commaCnt);
                                                                                TempCell.setCellValue("Failures ->");
                                                                                TempCell.setCellStyle(headerStyle);

                                                                                //write the cell contents
                                                                                for(int i=1;i<=rowsTotal ;i++){
                                                                                                row = sheet.createRow(i+startRow);

                                                                                                //rowContent - testcaseId ds_id dataSheet along with the pass fail status
                                                                                                rowContent = messageBody.split("\n")[i];
                                                                                                //get the values and set in excel
                                                                                                int index = rowContent.indexOf("Passed");
                                                                                                status = "Passed";
                                                                                                CellStyle currentCellStyle = style;
                                                                                                if(index==-1){
                                                                                                                index = rowContent.indexOf("Failed");
                                                                                                                status = "Failed";
                                                                                                                currentCellStyle = redFontNormalStyle;
                                                                                                }

                                                                                                testCase = rowContent.substring(0,index).trim();
                                                                                                //status = rowContent.substring(rowContent.length()-7);
                                                                                                String temp = rowContent.substring(index+6).trim();
                                                                                                time = temp.split("\\^")[0].trim();
                                                                                                TempCell = row.createCell(0);
                                                                                                TempCell.setCellValue(testCase);
                                                                                                TempCell.setCellStyle(currentCellStyle);
                                                                                                TempCell = row.createCell(1);
                                                                                                TempCell.setCellValue(status);
                                                                                                TempCell.setCellStyle(currentCellStyle);
                                                                                                TempCell = row.createCell(2);
                                                                                                TempCell.setCellValue(time);
                                                                                                TempCell.setCellStyle(currentCellStyle);
                                                                                                String AddColVal = "";
                                                                                                for (int k =0;k<commaCnt ;k++) {
                                                                                                                try {
                                                                                                                                AddColVal = temp.split("\\^")[k+1].trim();
                                                                                                                }
                                                                                                                catch (Exception e){
                                                                                                                                e.printStackTrace();
                                                                                                                                AddColVal = "";
                                                                                                                }

                                                                                                                TempCell = row.createCell(3+k);
                                                                                                                TempCell.setCellValue(AddColVal);
                                                                                                                TempCell.setCellStyle(currentCellStyle);


                                                                                                }

                                                                                                //column number for fail messages starts from 2 for every test case
                                                                                                fails = 3+commaCnt;
                                                                                                //key for the testcase statement in driver map
                                                                                                key="";
                                                                                                //failure messages
                                                                                                failures="";
                                                                                                 testCase : TestcaseName DS_ID DataSheetName
                                                                                                * All the failures corresponding to this key will be stored in driverMap 
                                                                                                key=testCase+"_fail";

                                                                                                //writing to the row if a failure log is present
                                                                                                if(Driver.driverMap.containsKey(key)){
                                                                                                                // fail log message for that test case
                                                                                                                failures=Driver.driverMap.get(key).toString();
                                                                                                                //individual log msgs are separated by <*~>

                                                                                                                for (int j=0;j<=Driver.countOccurences(failures, "<*~>"); j ++ ){
                                                                                                                                //split the value from the driver map
                                                                                                                                String valueInCell = failures.split("<\\*~>")[j];
                                                                                                                                //check if the value starts with hyperlink - to a screen shot
                                                                                                                                //if so format the cell to store a hyper link
                                                                                                                                if(valueInCell.startsWith("hyperlink:")){
                                                                                                                                                CreationHelper createHelper = workbook.getCreationHelper();
                                                                                                                                                //cell style for hyper links
                                                                                                                                                //by default hyper links are blue and underlined
                                                                                                                                                CellStyle hlink_style = workbook.createCellStyle();
                                                                                                                                                Font hlink_font = workbook.createFont();
                                                                                                                                                hlink_font.setFontName("Calibri");
                                                                                                                                                hlink_font.setUnderline(Font.U_SINGLE);
                                                                                                                                                hlink_font.setColor(IndexedColors.BLUE.getIndex());
                                                                                                                                                hlink_style.setFont(hlink_font);
                                                                                                                                                hlink_style.setBorderLeft(CellStyle.BORDER_THIN);
                                                                                                                                                hlink_style.setBorderRight(CellStyle.BORDER_THIN);
                                                                                                                                                hlink_style.setBorderTop(CellStyle.BORDER_THIN);
                                                                                                                                                hlink_style.setBorderBottom(CellStyle.BORDER_THIN);

                                                                                                                                                Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
                                                                                                                                                link.setAddress(valueInCell.split("hyperlink:")[1]);
                                                                                                                                                Cell cell = row.createCell(fails);
                                                                                                                                                //Set the cell value to the path to the result excel
                                                                                                                                                cell.setCellValue(valueInCell.split("hyperlink:")[1]);
                                                                                                                                                cell.setHyperlink(link);
                                                                                                                                                cell.setCellStyle(hlink_style);

                                                                                                                                }
                                                                                                                                //if there are no screen shots, place the content alone
                                                                                                                                else{
                                                                                                                                                TempCell = row.createCell(fails);
                                                                                                                                                TempCell.setCellValue(valueInCell);
                                                                                                                                                TempCell.setCellStyle(currentCellStyle);
                                                                                                                                }
                                                                                                                                //increment the cell value to store another log
                                                                                                                                fails++;
                                                                                                                }
                                                                                                }
                                                                                }
                                                                }
                                                                for(int colInd=0; colInd<fails+1; colInd++) {
                                                                                sheet.autoSizeColumn(colInd, true);
                                                                }

                                                                Driver.setLogMsg("info", "Result Excel generated at "+Driver.getValueFromEnvVariable("result_excel_path"));
                                                }
                                                return result;
                                }
                                catch(Exception e){
                                                e.printStackTrace();
                                                return false;
                                }
                                finally{
                                                //close the workbook after use
                                                try {
                                                                workbook.write(fileOut);
                                                                fileOut.close();
                                                                //----------------------------------------------------------------------------------------------------
                                                                  // Code Changes done by Nagendra -- AF29042 on 26th April 2017 for the PIE CHART 
                                                                 //-------------------------------------------------------------------------------------------------------
                                                                                                createGraphOfExecutionResult(fileName,passPercent,failPercent,cntPass,cntFail);
                                                } catch (IOException e) {
                                                                e.printStackTrace();
                                                }
                                                
                                                

                                }
                }
                // END java code createExecutionResult
                
public static void createGraphOfExecutionResult(String fileName,float passPercent,float failPercent,int cntPass,int cntFail) throws Exception{
                                
                                //GraphDraw Graph=new GraphDraw();              
                                //Graph.Graphmain(applicationTitle, chartTitle);
                                //GraphDraw  chart = new GraphDraw("Execution Result");
                                CreatePieChart CreatePieChartExample_OBJ=new CreatePieChart();
                                CreatePieChartExample_OBJ.DrawChart(fileName,passPercent,failPercent,cntPass,cntFail);
                                //chart.Graph("Execution Result","Execution Status",passPercent,failPercent,fileName);
                }
                
                
                // END java code createExecutionResult
                *//**
                * @description This method generates the messagebody string required for e-mail and excel updates
                * @param
                * testcase -name of the test case
                * datasheet - name of the data sheet
                * *//*
                private static void PrintExecutionResult(String testCase, String dataSheet){

                                String testCase_Id = testCase;
                                testCase_Id = testCase_Id.trim();
                                try{
                                                Calendar calStart = Calendar.getInstance();
                                                //SimpleDateFormat dfDay = new SimpleDateFormat("HH:mm:ss");
                                                Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("TCStartTime").toString());
                                                calStart.setTime(startDate);
                                                Calendar calEnd = Calendar.getInstance();
                                                Date endDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(Driver.driverMap.get("TCEndTime").toString());
                                                calEnd.setTime(endDate);

                                                long totalTime = (calEnd.getTimeInMillis()-calStart.getTimeInMillis())/(1000);
                                                int h = (int)          totalTime/(60*60);//find the hours
                                                int m = (int) (totalTime-(h*60*60))/(60);//minutes
                                                int s = (int) (totalTime-(h*60*60)-(m*60));//seconds
                                                System.out.println(h+":"+m+":"+s);
                                                int commaCnt = 0;
                                                String cellValue = "";
                                                String details[]=new String[3];
                                                if(Driver.emailBoolFlagFail == true){

                                                                details[0]=testCase_Id;
                                                                details[1]="Failed";
                                                                details[2]=h+"hrs:"+m+"mins:"+s + "secs";
                                                                messagetoPrint.add(details);

                                                }
                                                else if(Driver.emailBoolFlagPass == true){
                                                                details[0]=testCase_Id;
                                                                details[1]="Passed";
                                                                details[2]=h+"hrs:"+m+"mins:"+s + "secs";
                                                                messagetoPrint.add(details);

                                                }


                                }catch(Exception e){
                                                e.printStackTrace();
                                }

                }

}

//END of Caller
*/