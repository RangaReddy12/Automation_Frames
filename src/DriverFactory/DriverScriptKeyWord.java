package DriverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibraray.PBFunctions;
import Constant.PBConstant;
import Utilities.ExcelFileUtil;

public class DriverScriptKeyWord extends PBConstant{
String inputpath="E:\\930AMBatch\\Automation_Frameworks\\TestInput\\Controller.xlsx";
String outputpath="E:\\930AMBatch\\Automation_Frameworks\\TestoutPut\\KeywordResults.xlsx";
ExtentReports report;
ExtentTest test;
String TCSheet="TestCases";
String TSSheet="TestSteps";
@Test
public void startTest()throws Throwable
{
	report= new ExtentReports("./Reports/"+PBFunctions.generateDate()+"Keyword.html");
boolean res=false;
String tcres="";
//Access Excel Methods
ExcelFileUtil xl = new ExcelFileUtil(inputpath);
//count no of rows in TCSheet and TSSheet
int TCCount=xl.rowCount(TCSheet);
int TSCount=xl.rowCount(TSSheet);
Reporter.log("No of rows in TCSheet::"+TCCount+"  "+"No of rows in TSSheet::"+TSCount,true);
//iterate all rows in TCSheet
for(int i=1;i<=TCCount; i++)
{
	test=report.startTest("Primus");
	//Read Execute Cell
	String Execute=xl.getCellData(TCSheet, i, 2);
	if(Execute.equalsIgnoreCase("Y"))
	{
		//read tcid cell from TCsheet
		String tcid=xl.getCellData(TCSheet, i, 0);
		//iterate all rows in TSSheet
		for(int j=1;j<=TSCount;j++)
		{
			//read Description cell
			String Description=xl.getCellData(TSSheet, j, 2);
			//read tsid cell from TSSheet
			String tsid=xl.getCellData(TSSheet, j, 0);
			if(tcid.equalsIgnoreCase(tsid))
			{
				//Read Keyword Cell From TSSheet
				String Keyword=xl.getCellData(TSSheet, j, 3);
				//call methods
			if(Keyword.equalsIgnoreCase("AdminLogin"))
			{
				res=PBFunctions.verifyLogin("Admin", "Admin");
				test.log(LogStatus.INFO, Description);
			}
			else if(Keyword.equalsIgnoreCase("NewBranchCreation"))
			{
				PBFunctions.clickBranches();
				res=PBFunctions.verifyNewBranch("Kadiri", "Anantapur", "madanapalli", "tanakal", "Kadiri", "12345", "INDIA", "Andhra Pradesh", "Hyderabad");
				test.log(LogStatus.INFO, Description);
			}
			else if(Keyword.equalsIgnoreCase("UpdateBranch"))
			{
				PBFunctions.clickBranches();
				res=PBFunctions.verifyUpdateBranch("Madanapalli", "Tanakal", "26547");
				test.log(LogStatus.INFO, Description);
			}
			else if(Keyword.equalsIgnoreCase("AdminLogout"))
			{
				res=PBFunctions.verifylogout();
				test.log(LogStatus.INFO, Description);
			}
			String tsres="";
			if(res)
			{
				//if res is true write as pass into results cell in TSsheet
				tsres="Pass";
				xl.setCelldata(TSSheet, j, 4, tsres, outputpath);
				test.log(LogStatus.PASS, Description);
			}
			else
			{
				//if res is false write as fail into results cell in TSsheet
				tsres="Fail";
				xl.setCelldata(TSSheet, j, 4, tsres, outputpath);
				test.log(LogStatus.FAIL, Description);
			}
			tcres=tsres;
			}
			report.endTest(test);
			report.flush();
		}
		//write tcres in TCSheet
		xl.setCelldata(TCSheet, i, 3, tcres, outputpath);
	}
	else
	{
		//write as Blocked Into Results Cell
		xl.setCelldata(TCSheet, i, 3, "Blocked", outputpath);
	}
}
}
}











