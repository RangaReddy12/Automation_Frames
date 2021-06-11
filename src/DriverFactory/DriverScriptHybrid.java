package DriverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibraray.PBFunctions;
import Constant.PBConstant;
import Utilities.ExcelFileUtil;

public class DriverScriptHybrid extends PBConstant{
String inputpath="E:\\930AMBatch\\Automation_Frameworks\\TestInput\\HybridTest.xlsx";
String outputpath="E:\\930AMBatch\\Automation_Frameworks\\TestoutPut\\HybridResults.xlsx";
ExtentReports report;
ExtentTest test;
String TCSheet="TestCases";
String TSSheet="TestSteps";
@Test
public void startTest()throws Throwable
{
	report= new ExtentReports("./ExtentReports/"+PBFunctions.generateDate()+"-"+"Hybrid.html");
	boolean res=false;
	String tcres="";
	ExcelFileUtil xl= new ExcelFileUtil(inputpath);
	int TCCount=xl.rowCount(TCSheet);
	int TSCount=xl.rowCount(TSSheet);
	Reporter.log("No of rows in TCSheet::"+TCCount+"  "+"No of rows in TSSheet::"+TSCount,true);
for(int i=1;i<=TCCount;i++)
{
	test=report.startTest("Hybrid Test");
	String execute=xl.getCellData(TCSheet, i, 2);
	if(execute.equalsIgnoreCase("Y"))
	{
	String tcid=xl.getCellData(TCSheet, i, 0);
	for(int j=1;j<=TSCount;j++)
	{
		String Description=xl.getCellData(TSSheet, j, 2);
		String tsid=xl.getCellData(TSSheet, j, 0);
		if(tcid.equalsIgnoreCase(tsid))
		{
		String keyword=xl.getCellData(TSSheet, j, 4);
		if(keyword.equalsIgnoreCase("AdminLogin"))
		{
			String username=xl.getCellData(TSSheet, j, 5);
			String password=xl.getCellData(TSSheet, j, 6);
			res=PBFunctions.verifyLogin(username, password);
			test.log(LogStatus.INFO, Description);
		}
		else if(keyword.equalsIgnoreCase("NewBranchCreation"))
		{
			String branchname=xl.getCellData(TSSheet, j, 5);
			String Address1=xl.getCellData(TSSheet, j, 6);
			String Address2=xl.getCellData(TSSheet, j, 7);
			String Address3=xl.getCellData(TSSheet, j, 8);
			String Area=xl.getCellData(TSSheet, j, 9);
			String zipcode=xl.getCellData(TSSheet, j, 10);
			String country=xl.getCellData(TSSheet, j, 11);
			String state=xl.getCellData(TSSheet, j, 12);
			String city=xl.getCellData(TSSheet, j, 13);
			PBFunctions.clickBranches();
			res=PBFunctions.verifyNewBranch(branchname, Address1, Address2, Address3, Area, zipcode, country, state, city);
			test.log(LogStatus.INFO, Description);
		}
		else if(keyword.equalsIgnoreCase("UpdateBranch"))
		{
			String branchname=xl.getCellData(TSSheet, j, 5);
			String Address1=xl.getCellData(TSSheet, j, 6);
			String zipcode=xl.getCellData(TSSheet, j, 10);
			PBFunctions.clickBranches();
			res=PBFunctions.verifyUpdateBranch(branchname, Address1, zipcode);
			test.log(LogStatus.INFO, Description);
		}
		else if(keyword.equalsIgnoreCase("AdminLogout"))
		{
			res=PBFunctions.verifylogout();
			test.log(LogStatus.INFO, Description);
		}
		
		String tsres="";
		if(res)
		{
			tsres="Pass";
			xl.setCelldata(TSSheet, j, 3, tsres, outputpath);
			test.log(LogStatus.PASS, Description);
		}
		else
		{
			tsres="Fail";
			xl.setCelldata(TSSheet, j, 3, tsres, outputpath);
			test.log(LogStatus.FAIL, Description);
		}
		tcres=tsres;
		
		}
		report.endTest(test);
		report.flush();
	}
	xl.setCelldata(TCSheet, i, 3, tcres, outputpath);
	}
	else
	{
		xl.setCelldata(TCSheet, i, 3, "Blocked", outputpath);
	}
}
}
}












