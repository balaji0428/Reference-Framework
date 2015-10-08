package com.fp.firstpickadmin;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.Properties;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.fp.firstpickadmin.CommonFunctions;
import com.fp.mail.Mail;

public class AdminLogin {

	private static WebDriver driver;
	private static String baseurl;
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		AdminLogin.driver = driver;
	}
	
	public AdminLogin() {
		driver = new FirefoxDriver();
	}

	/**
	 * @param args
	 */
	@Test
	public void LoginFirstPick()
	{
	try
	{
		//************  invoke firefox with url  **************//
		driver.manage().window().maximize();
		baseurl="http://172.16.36.39:8080/FirstPickAdmin";
		driver.get(baseurl);
		Thread.sleep(2000);	
		
		//***** get data from properties file *****//
		Properties props = null;
		props = new Properties();
		
		try {	 
			   InputStream in = getClass().getResourceAsStream("GetAdminXpaths.properties");	 
			   props.load(in);
			} catch (Exception e) {
			   System.out.println("Could not read GetXpaths.properties files :");
			   e.printStackTrace();
			}
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		String headerFormat = "<tr><th>%8s</th><th>%8s</th><th>%8s</th><th>%8s%n</th></tr>";
		String rowFormat = "<tr><td>%8s</td><td>%8s</td><td>%8s</td><td>%8s%n</td></tr>";

		pw.printf(headerFormat, "TestCaseId", "TestCaseTitle", "Status", "Comments");
		
		String starttime = CommonFunctions.dateandTime();
		System.out.println(starttime);
		
		//-----------*********Login with empty credentials*********--------------//
		String LoginEmptyValues[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","FPadminlogincredentialsempty");
		String loginEmptyValuesStatus = "";
		String loginEmptyValuesComments= "";
		
		for (int loginEmptyVals=1 ;loginEmptyVals<2;loginEmptyVals++)
		{	
			CommonFunctions.genericLogin (props.getProperty("txtAdminLoginId"),props.getProperty("txtAdminLoginPwd"),props.getProperty("adminLoginBtn"),LoginEmptyValues[2][loginEmptyVals],LoginEmptyValues[3][loginEmptyVals]);
			
			boolean errmsgloginEmptyVals = CommonFunctions.isTextPresent("Please enter Login Id");
//			boolean errmsgLogin = CommonFuncitons.verifyElementAbsent(props.getProperty("loginRegUserErrMsg"));
			System.out.println(errmsgloginEmptyVals);
		
			if(errmsgloginEmptyVals == true)
			{
				loginEmptyValuesStatus = "PASS";
				CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "FPadminlogincredentialsempty", loginEmptyValuesStatus, loginEmptyVals, 4);
			}
			else
			{
				loginEmptyValuesStatus = "FAIL";
				loginEmptyValuesComments = loginEmptyValuesComments+LoginEmptyValues[3][loginEmptyVals]+"<br/>";
				CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "FPadminlogincredentialsempty", loginEmptyValuesStatus, loginEmptyVals, 4);
				CommonFunctions.googleScreenshot();
			}
		}
		
		if (loginEmptyValuesComments != "") {
				loginEmptyValuesComments = "Failed for following data values<br/>" + loginEmptyValuesComments;    
		}
    
		pw.printf(rowFormat, LoginEmptyValues[0][1], LoginEmptyValues[1][1], loginEmptyValuesStatus, loginEmptyValuesComments);
		
		Thread.sleep(5000);
		
		//-----------*********Login with wrong credentials***********----------------//
		String LoginWrongCredentails[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","FpadminLoginwrongCredentials");
		String loginWrongCredentailsStatus = "";
		String loginWrongCredentailsComments= "";

		for (int loginWrongvals=1 ;loginWrongvals<3;loginWrongvals++)
		{		
			CommonFunctions.genericLogin (props.getProperty("txtAdminLoginId"),props.getProperty("txtAdminLoginPwd"),props.getProperty("adminLoginBtn"),LoginWrongCredentails[2][loginWrongvals],LoginWrongCredentails[3][loginWrongvals]);
				
			boolean errmsgloginWrongvals = CommonFunctions.isTextPresent("Invalid Login Id or Password");
//			boolean errmsgLogin = CommonFuncitons.verifyElementAbsent(props.getProperty("loginRegUserErrMsg"));
			System.out.println(errmsgloginWrongvals);
			
			if(errmsgloginWrongvals == true)
			{
				loginWrongCredentailsStatus = "PASS";
				CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "FpadminLoginwrongCredentials", loginWrongCredentailsStatus, loginWrongvals, 4);
			}
			else
			{
				loginWrongCredentailsStatus = "FAIL";
				loginWrongCredentailsComments = loginWrongCredentailsComments+LoginWrongCredentails[3][loginWrongvals]+"<br/>";
				CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "FpadminLoginwrongCredentials", loginWrongCredentailsStatus, loginWrongvals, 4);
				CommonFunctions.googleScreenshot();
			}
		}
			
		if (loginWrongCredentailsComments != "") {
			loginWrongCredentailsComments = "Failed for following data values<br/>" + loginWrongCredentailsComments;    
		}
		
		pw.printf(rowFormat, LoginWrongCredentails[0][1], LoginWrongCredentails[1][1], loginWrongCredentailsStatus, loginWrongCredentailsComments);
			
		Thread.sleep(5000);
		
		//----------*********Login with Valid credentials***********------------//
		String LoginValidCredentails[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","FPValidLoginCredntials");
		String loginValidCredentailsStatus = "";
			
		for (int loginValidvals=1 ;loginValidvals<2;loginValidvals++)
		{		
			CommonFunctions.genericLogin (props.getProperty("txtAdminLoginId"),props.getProperty("txtAdminLoginPwd"),props.getProperty("adminLoginBtn"),LoginValidCredentails[2][loginValidvals],LoginValidCredentails[3][loginValidvals]);
				
			loginValidCredentailsStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "FPValidLoginCredntials", loginValidCredentailsStatus, loginValidvals, 4);
		
		}
			
		pw.printf(rowFormat, LoginValidCredentails[0][1], LoginValidCredentails[1][1], loginValidCredentailsStatus, "");
			
		Thread.sleep(5000);
		
		//*******   Select Users in Manage select box   *******//
		Select selctUsers = new Select(driver.findElement(By.xpath(props.getProperty("selctUsers"))));
		selctUsers.selectByVisibleText("Users");
		Thread.sleep(2000);
		
		//*******   Activation Button Clicking with Empty Users Selection   *******//
		String activeBtnEmptyUsers[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","activeBtnEmptyUsers");
		String activeBtnEmptyUsersStatus = "";
		String activeBtnEmptyComments = "";
		
		driver.findElement(By.xpath(props.getProperty("activeBtn"))).click();
		System.out.println("Active Button Clicked.");
		Thread.sleep(20000);

		boolean errmsgaciveBtnEmptyvals = CommonFunctions.isTextPresent("Please select User(s)");
		System.out.println(errmsgaciveBtnEmptyvals);
		
		if(errmsgaciveBtnEmptyvals == true)
		{
			activeBtnEmptyUsersStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "activeBtnEmptyUsers", activeBtnEmptyUsersStatus, 1, 2);
		}
		else
		{
			activeBtnEmptyUsersStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "activeBtnEmptyUsers", activeBtnEmptyUsersStatus, 1, 2);
			activeBtnEmptyComments = activeBtnEmptyComments+activeBtnEmptyUsers[3][2]+"<br/>";
			CommonFunctions.googleScreenshot();
		}
		
		if (activeBtnEmptyComments != "") {
			activeBtnEmptyComments = "Failed for following data values<br/>" + activeBtnEmptyComments;    
		}
		
		pw.printf(rowFormat, activeBtnEmptyUsers[0][1], activeBtnEmptyUsers[1][1], activeBtnEmptyUsersStatus, activeBtnEmptyComments);
		
		Thread.sleep(5000);
		
		//*******   Activation of multiple Users   *******//
		String activeBtnValidUsers[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","activeBtnValidUsers");
		String activeBtnValidUsersStatus = "";
		
		driver.findElement(By.xpath(props.getProperty("activeChkbox1"))).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath(props.getProperty("activeBtn"))).click();
		System.out.println("Active Button Clicked.");
		Thread.sleep(20000);
		
		if((driver.findElement(By.xpath(props.getProperty("activeVal"))).getText()) == "Yes")
		{
			activeBtnValidUsersStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "activeBtnValidUsers", activeBtnValidUsersStatus, 1, 2);
		}
		else
		{
			activeBtnValidUsersStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "activeBtnValidUsers", activeBtnValidUsersStatus, 1, 2);
			CommonFunctions.googleScreenshot();
		}
			
		pw.printf(rowFormat, activeBtnValidUsers[0][1], activeBtnValidUsers[1][1], activeBtnValidUsersStatus, "");
		
		Thread.sleep(5000);
		
		//*******   DeActivation Button Clicking with Empty Users and Reason   *******//
		String deactiveBtnEmptyUsers[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","deactiveBtnEmptyUsers");
		String deactiveBtnEmptyUsersStatus = "";
		String deactiveBtnEmptyComments = "";
		

		driver.findElement(By.xpath(props.getProperty("reasonTxtArea"))).sendKeys(deactiveBtnEmptyUsers[2][1]);
		System.out.println("De-activate Reason entered.");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath(props.getProperty("deactiveBtn"))).click();
		System.out.println("Deactive Button Clicked.");
		Thread.sleep(5000);
		
		boolean errmsgdeaciveBtnEmptyvals = CommonFunctions.isTextPresent("Please enter De-activate Reason");
		System.out.println(errmsgdeaciveBtnEmptyvals);
		
		if(errmsgdeaciveBtnEmptyvals == true)
		{
			deactiveBtnEmptyUsersStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "deactiveBtnEmptyUsers", deactiveBtnEmptyUsersStatus, 1, 3);
		}
		else
		{
			deactiveBtnEmptyUsersStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "deactiveBtnEmptyUsers", deactiveBtnEmptyUsersStatus, 1, 3);
			deactiveBtnEmptyComments = "Error Message is Wrong or not displaying error message";
			CommonFunctions.googleScreenshot();
		}
		
		pw.printf(rowFormat, deactiveBtnEmptyUsers[0][1], deactiveBtnEmptyUsers[1][1], deactiveBtnEmptyUsersStatus, deactiveBtnEmptyComments);
		
		Thread.sleep(5000);
		
		//*******   DeActivation of multiple Users   *******//
		String deactiveBtnValidUsers[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","deactiveBtnValidUsers");
		String deactiveBtnValidUsersStatus = "";
		String deactiveBtnValidUsersComments = "";
		
		driver.findElement(By.xpath(props.getProperty("reasonTxtArea"))).sendKeys(deactiveBtnValidUsers[2][1]);
		System.out.println("De-activate Reason entered.");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath(props.getProperty("activeChkbox1"))).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath(props.getProperty("deactiveBtn"))).click();
		System.out.println("Deactive Button Clicked.");
		Thread.sleep(10000);
		
		if((driver.findElement(By.xpath(props.getProperty("activeVal"))).getText()) == "No")
		{
			deactiveBtnValidUsersStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "deactiveBtnValidUsers", deactiveBtnValidUsersStatus, 1, 3);
		}
		else
		{
			deactiveBtnValidUsersStatus = "FAIL";
			deactiveBtnValidUsersComments = "Error Message is Wrong or not displaying error message";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "deactiveBtnValidUsers", deactiveBtnValidUsersStatus, 1, 3);
			CommonFunctions.googleScreenshot();
		}
		
		pw.printf(rowFormat, deactiveBtnValidUsers[0][1], deactiveBtnValidUsers[1][1], deactiveBtnValidUsersStatus, deactiveBtnValidUsersComments);
		
		Thread.sleep(5000);
		
		//*******   Select PlayerPositions in Manage select box   *******//
		Select select = new Select(driver.findElement(By.xpath(props.getProperty("selctUsers"))));
		select.selectByVisibleText("Player Positions");
		System.out.println("Select Player Positions.");
		Thread.sleep(2000);
		
		//*******   Select PlayerPositions in Manage select box (Empty Sport)   *******//
		String playerpositionsSportData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","ppSportsData");
		String playerpositionsSportStatus = "";
		
		AppFunction.playerpositions(props.getProperty("selctSport_pp"),props.getProperty("txtboxPostions_pp"),props.getProperty("txtboxAbbreviation_pp"),props.getProperty("txtboxActualSportAbbreviation_pp"),props.getProperty("txtboxFilterLabel_pp"),props.getProperty("txtboxOrderby_pp"),props.getProperty("sbmtBtn_pp"),playerpositionsSportData[2][1],playerpositionsSportData[3][1],playerpositionsSportData[4][1],playerpositionsSportData[5][1],playerpositionsSportData[6][1],playerpositionsSportData[7][1]);
				
		boolean errmsgplayerpositionsSport = CommonFunctions.isTextPresent("Please select Sport");
		System.out.println(errmsgplayerpositionsSport);
			
		if(errmsgplayerpositionsSport == true)
		{
			playerpositionsSportStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppSportsData", playerpositionsSportStatus, 1, 9);
		}
		else
		{
			playerpositionsSportStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppSportsData", playerpositionsSportStatus, 1, 9);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, playerpositionsSportData[0][1], playerpositionsSportData[1][1], playerpositionsSportStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select PlayerPositions in Manage select box (Empty Position)   *******//
		String playerpositionsemptypostnData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","ppemptypostionData");
		String playerpositionsemptypostnStatus = "";
		
		AppFunction.playerpositions(props.getProperty("selctSport_pp"),props.getProperty("txtboxPostions_pp"),props.getProperty("txtboxAbbreviation_pp"),props.getProperty("txtboxActualSportAbbreviation_pp"),props.getProperty("txtboxFilterLabel_pp"),props.getProperty("txtboxOrderby_pp"),props.getProperty("sbmtBtn_pp"),playerpositionsemptypostnData[2][1],playerpositionsemptypostnData[3][1],playerpositionsemptypostnData[4][1],playerpositionsemptypostnData[5][1],playerpositionsemptypostnData[6][1],playerpositionsemptypostnData[7][1]);
				
		boolean errmsgplayerpositionsemptypostn = CommonFunctions.isTextPresent("Please enter Position");
		System.out.println(errmsgplayerpositionsemptypostn);
			
		if(errmsgplayerpositionsemptypostn == true)
		{
			playerpositionsemptypostnStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppemptypostionData", playerpositionsemptypostnStatus, 1, 9);
		}
		else
		{
			playerpositionsemptypostnStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppemptypostionData", playerpositionsemptypostnStatus, 1, 9);
			CommonFunctions.googleScreenshot();
		}
		
		pw.printf(rowFormat, playerpositionsemptypostnData[0][1], playerpositionsemptypostnData[1][1], playerpositionsemptypostnStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select PlayerPositions in Manage select box (Invalid Position)   *******//
		String playerpositionsinvalidpostnData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","ppinvalidpostionData");
		String playerpositionsinvalidpostnStatus = "";
		
		AppFunction.playerpositions(props.getProperty("selctSport_pp"),props.getProperty("txtboxPostions_pp"),props.getProperty("txtboxAbbreviation_pp"),props.getProperty("txtboxActualSportAbbreviation_pp"),props.getProperty("txtboxFilterLabel_pp"),props.getProperty("txtboxOrderby_pp"),props.getProperty("sbmtBtn_pp"),playerpositionsinvalidpostnData[2][1],playerpositionsinvalidpostnData[3][1],playerpositionsinvalidpostnData[4][1],playerpositionsinvalidpostnData[5][1],playerpositionsinvalidpostnData[6][1],playerpositionsinvalidpostnData[7][1]);
				
		boolean errmsgplayerpositionsinvalidpostn = CommonFunctions.isTextPresent("Invalid Position");
		System.out.println(errmsgplayerpositionsinvalidpostn);
			
		if(errmsgplayerpositionsinvalidpostn == true)
		{
			playerpositionsinvalidpostnStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppinvalidpostionData", playerpositionsinvalidpostnStatus, 1, 9);
		}
		else
		{
			playerpositionsinvalidpostnStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppinvalidpostionData", playerpositionsinvalidpostnStatus, 1, 9);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, playerpositionsinvalidpostnData[0][1], playerpositionsinvalidpostnData[1][1], playerpositionsinvalidpostnStatus, "");
		
		Thread.sleep(5000);

		//*******   Select PlayerPositions in Manage select box (Position already Exists)
		String playerpositionsexistpostnData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","ppexistpositionData");
		String playerpositionsexistpostnStatus = "";
		
		AppFunction.playerpositions(props.getProperty("selctSport_pp"),props.getProperty("txtboxPostions_pp"),props.getProperty("txtboxAbbreviation_pp"),props.getProperty("txtboxActualSportAbbreviation_pp"),props.getProperty("txtboxFilterLabel_pp"),props.getProperty("txtboxOrderby_pp"),props.getProperty("sbmtBtn_pp"),playerpositionsexistpostnData[2][1],playerpositionsexistpostnData[3][1],playerpositionsexistpostnData[4][1],playerpositionsexistpostnData[5][1],playerpositionsexistpostnData[6][1],playerpositionsexistpostnData[7][1]);
				
		boolean errmsgplayerpositionsexistpostn = CommonFunctions.isTextPresent("Position already exists");
		System.out.println(errmsgplayerpositionsexistpostn);
			
		if(errmsgplayerpositionsexistpostn == true)
		{
			playerpositionsexistpostnStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppexistpositionData", playerpositionsexistpostnStatus, 1, 9);
		}
		else
		{
			playerpositionsexistpostnStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppexistpositionData", playerpositionsexistpostnStatus, 1, 9);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, playerpositionsexistpostnData[0][1], playerpositionsexistpostnData[1][1], playerpositionsexistpostnStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select PlayerPositions in Manage select box (Empty Abbreviation)
		String playerpositionsemptyabbrvatnData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","ppemptyabbrvatnData");
		String playerpositionsemptyabbrvatnStatus = "";
		
		AppFunction.playerpositions(props.getProperty("selctSport_pp"),props.getProperty("txtboxPostions_pp"),props.getProperty("txtboxAbbreviation_pp"),props.getProperty("txtboxActualSportAbbreviation_pp"),props.getProperty("txtboxFilterLabel_pp"),props.getProperty("txtboxOrderby_pp"),props.getProperty("sbmtBtn_pp"),playerpositionsemptyabbrvatnData[2][1],playerpositionsemptyabbrvatnData[3][1],playerpositionsemptyabbrvatnData[4][1],playerpositionsemptyabbrvatnData[5][1],playerpositionsemptyabbrvatnData[6][1],playerpositionsemptyabbrvatnData[7][1]);
				
		boolean errmsgplayerpositionsemptyabbrvatn = CommonFunctions.isTextPresent("Please enter Abbreviation");
		System.out.println(errmsgplayerpositionsemptyabbrvatn);
			
		if(errmsgplayerpositionsemptyabbrvatn == true)
		{
			playerpositionsemptyabbrvatnStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppemptyabbrvatnData", playerpositionsemptyabbrvatnStatus, 1, 9);
		}
		else
		{
			playerpositionsemptyabbrvatnStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppemptyabbrvatnData", playerpositionsemptyabbrvatnStatus, 1, 9);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, playerpositionsemptyabbrvatnData[0][1], playerpositionsemptyabbrvatnData[1][1], playerpositionsemptyabbrvatnStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select PlayerPositions in Manage select box (Invalid Abbreviation)
		String playerpositionsinvalidabbrevatnData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","ppinvalidabbrvatnData");
		String playerpositionsinvalidabbrevatnStatus = "";
		
		AppFunction.playerpositions(props.getProperty("selctSport_pp"),props.getProperty("txtboxPostions_pp"),props.getProperty("txtboxAbbreviation_pp"),props.getProperty("txtboxActualSportAbbreviation_pp"),props.getProperty("txtboxFilterLabel_pp"),props.getProperty("txtboxOrderby_pp"),props.getProperty("sbmtBtn_pp"),playerpositionsinvalidabbrevatnData[2][1],playerpositionsinvalidabbrevatnData[3][1],playerpositionsinvalidabbrevatnData[4][1],playerpositionsinvalidabbrevatnData[5][1],playerpositionsinvalidabbrevatnData[6][1],playerpositionsinvalidabbrevatnData[7][1]);
				
		boolean errmsgplayerpositionsinvalidabbrevatn = CommonFunctions.isTextPresent("Invalid Abbreviation");
		System.out.println(errmsgplayerpositionsinvalidabbrevatn);
			
		if(errmsgplayerpositionsinvalidabbrevatn == true)
		{
			playerpositionsinvalidabbrevatnStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppinvalidabbrvatnData", playerpositionsinvalidabbrevatnStatus, 1, 9);
		}
		else
		{
			playerpositionsinvalidabbrevatnStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppinvalidabbrvatnData", playerpositionsinvalidabbrevatnStatus, 1, 9);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, playerpositionsinvalidabbrevatnData[0][1], playerpositionsinvalidabbrevatnData[1][1], playerpositionsinvalidabbrevatnStatus, "");
		
		Thread.sleep(5000);

		
		//*******   Select PlayerPositions in Manage select box (Empty OrderBy)
		String playerpositionsemptyOrderByData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","ppemptyOrderByData");
		String playerpositionsemptyOrderByStatus = "";
		
		AppFunction.playerpositions(props.getProperty("selctSport_pp"),props.getProperty("txtboxPostions_pp"),props.getProperty("txtboxAbbreviation_pp"),props.getProperty("txtboxActualSportAbbreviation_pp"),props.getProperty("txtboxFilterLabel_pp"),props.getProperty("txtboxOrderby_pp"),props.getProperty("sbmtBtn_pp"),playerpositionsemptyOrderByData[2][1],playerpositionsemptyOrderByData[3][1],playerpositionsemptyOrderByData[4][1],playerpositionsemptyOrderByData[5][1],playerpositionsemptyOrderByData[6][1],playerpositionsemptyOrderByData[7][1]);
				
		boolean errmsgplayerpositionsemptyOrderBy = CommonFunctions.isTextPresent("Please enter Order By");
		System.out.println(errmsgplayerpositionsemptyOrderBy);
			
		if(errmsgplayerpositionsemptyOrderBy == true)
		{
			playerpositionsemptyOrderByStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppemptyOrderByData", playerpositionsemptyOrderByStatus, 1, 9);
		}
		else
		{
			playerpositionsemptyOrderByStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppemptyOrderByData", playerpositionsemptyOrderByStatus, 1, 9);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, playerpositionsemptyOrderByData[0][1], playerpositionsemptyOrderByData[1][1], playerpositionsemptyOrderByStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select PlayerPositions in Manage select box (Invalid OrderBy)
		String playerpositionsinvalidOrderByData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","ppinvalidOrderByData");
		String playerpositionsinvalidOrderByStatus = "";
		
		AppFunction.playerpositions(props.getProperty("selctSport_pp"),props.getProperty("txtboxPostions_pp"),props.getProperty("txtboxAbbreviation_pp"),props.getProperty("txtboxActualSportAbbreviation_pp"),props.getProperty("txtboxFilterLabel_pp"),props.getProperty("txtboxOrderby_pp"),props.getProperty("sbmtBtn_pp"),playerpositionsinvalidOrderByData[2][1],playerpositionsinvalidOrderByData[3][1],playerpositionsinvalidOrderByData[4][1],playerpositionsinvalidOrderByData[5][1],playerpositionsinvalidOrderByData[6][1],playerpositionsinvalidOrderByData[7][1]);
				
		boolean errmsgplayerpositionsinvalidOrderBy = CommonFunctions.isTextPresent("Please enter Number for Order By");
		System.out.println(errmsgplayerpositionsinvalidOrderBy);
			
		if(errmsgplayerpositionsinvalidOrderBy == true)
		{
			playerpositionsinvalidOrderByStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppinvalidOrderByData", playerpositionsinvalidOrderByStatus, 1, 9);
		}
		else
		{
			playerpositionsinvalidOrderByStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ppinvalidOrderByData", playerpositionsinvalidOrderByStatus, 1, 9);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, playerpositionsinvalidOrderByData[0][1], playerpositionsinvalidOrderByData[1][1], playerpositionsinvalidOrderByStatus, "");
		
		Thread.sleep(5000);
		
		
		//Select Sport in Manage select box
		Select select1 = new Select(driver.findElement(By.xpath(props.getProperty("selctPlayerPositions"))));
		select1.selectByVisibleText("Sports");
		Thread.sleep(2000);
		
		//*******   Select Sports in Manage select box (Empty Sport)
		String sportsemptySportData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","semptySportData");
		String sportsemptySportStatus = "";
		
		AppFunction.sports(props.getProperty("txtboxSport_s"),props.getProperty("txtboxAbbreviation_s"),props.getProperty("txtboxOrderby_s"),props.getProperty("sbmtBtn_s"),sportsemptySportData[2][1],sportsemptySportData[3][1],sportsemptySportData[4][1]);
				
		boolean errmsgsportsemptySport = CommonFunctions.isTextPresent("Please enter Sport");
		System.out.println(errmsgsportsemptySport);
			
		if(errmsgsportsemptySport == true)
		{
			sportsemptySportStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "semptySportData", sportsemptySportStatus, 1, 6);
		}
		else
		{
			sportsemptySportStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "semptySportData", sportsemptySportStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, sportsemptySportData[0][1], sportsemptySportData[1][1], sportsemptySportStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select Sports in Manage select box (Invalid Sport)
		String sportsinvalidSportData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","sinvalidSportData");
		String sportsinvalidSportStatus = "";
		
		AppFunction.sports(props.getProperty("txtboxSport_s"),props.getProperty("txtboxAbbreviation_s"),props.getProperty("txtboxOrderby_s"),props.getProperty("sbmtBtn_s"),sportsinvalidSportData[2][1],sportsinvalidSportData[3][1],sportsinvalidSportData[4][1]);
				
		boolean errmsgsportsinvalidSport = CommonFunctions.isTextPresent("Invalid Sport");
		System.out.println(errmsgsportsinvalidSport);
			
		if(errmsgsportsinvalidSport == true)
		{
			sportsinvalidSportStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "sinvalidSportData", sportsinvalidSportStatus, 1, 6);
		}
		else
		{
			sportsinvalidSportStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "sinvalidSportData", sportsinvalidSportStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, sportsinvalidSportData[0][1], sportsinvalidSportData[1][1], sportsinvalidSportStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select Sports in Manage select box (Exist Sport)
		String sportsexistSportData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","sexistSportData");
		String sportsexistSportStatus = "";
		
		AppFunction.sports(props.getProperty("txtboxSport_s"),props.getProperty("txtboxAbbreviation_s"),props.getProperty("txtboxOrderby_s"),props.getProperty("sbmtBtn_s"),sportsexistSportData[2][1],sportsexistSportData[3][1],sportsexistSportData[4][1]);
				
		boolean errmsgsportsexistSport = CommonFunctions.isTextPresent("Sport already exists");
		System.out.println(errmsgsportsexistSport);
			
		if(errmsgsportsexistSport == true)
		{
			sportsexistSportStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "sexistSportData", sportsexistSportStatus, 1, 6);
		}
		else
		{
			sportsexistSportStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "sexistSportData", sportsexistSportStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, sportsexistSportData[0][1], sportsexistSportData[1][1], sportsexistSportStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select Sports in Manage select box (Empty Abbreviation)
		String sportsemptyabbrevatnData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","semptyAbbrevatnData");
		String sportsemptyabbrevatnStatus = "";
		
		AppFunction.sports(props.getProperty("txtboxSport_s"),props.getProperty("txtboxAbbreviation_s"),props.getProperty("txtboxOrderby_s"),props.getProperty("sbmtBtn_s"),sportsemptyabbrevatnData[2][1],sportsemptyabbrevatnData[3][1],sportsemptyabbrevatnData[4][1]);
				
		boolean errmsgsportsemptyabbrevatn = CommonFunctions.isTextPresent("Please enter Abbreviation");
		System.out.println(errmsgsportsemptyabbrevatn);
			
		if(errmsgsportsemptyabbrevatn == true)
		{
			sportsemptyabbrevatnStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "semptyAbbrevatnData", sportsemptyabbrevatnStatus, 1, 6);
		}
		else
		{
			sportsemptyabbrevatnStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "semptyAbbrevatnData", sportsemptyabbrevatnStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, sportsemptyabbrevatnData[0][1], sportsemptyabbrevatnData[1][1], sportsemptyabbrevatnStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select Sports in Manage select box (Invalid Abbreviation)
		String sportsinvalidabbrevatnData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","sinvalidAbbrevatnData");
		String sportsinvalidabbrevatnStatus = "";
		
		AppFunction.sports(props.getProperty("txtboxSport_s"),props.getProperty("txtboxAbbreviation_s"),props.getProperty("txtboxOrderby_s"),props.getProperty("sbmtBtn_s"),sportsinvalidabbrevatnData[2][1],sportsinvalidabbrevatnData[3][1],sportsinvalidabbrevatnData[4][1]);
				
		boolean errmsgsportsinvalidabbrevatn = CommonFunctions.isTextPresent("Invalid Abbreviation");
		System.out.println(errmsgsportsinvalidabbrevatn);
			
		if(errmsgsportsinvalidabbrevatn == true)
		{
			sportsinvalidabbrevatnStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "sinvalidAbbrevatnData", sportsinvalidabbrevatnStatus, 1, 6);
		}
		else
		{
			sportsinvalidabbrevatnStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "sinvalidAbbrevatnData", sportsinvalidabbrevatnStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, sportsinvalidabbrevatnData[0][1], sportsinvalidabbrevatnData[1][1], sportsinvalidabbrevatnStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select Sports in Manage select box (Exist Abbreviation)
		String sportsexistabbrevatnData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","sexistAbbrevatnData");
		String sportsexistabbrevatnStatus = "";
		
		AppFunction.sports(props.getProperty("txtboxSport_s"),props.getProperty("txtboxAbbreviation_s"),props.getProperty("txtboxOrderby_s"),props.getProperty("sbmtBtn_s"),sportsexistabbrevatnData[2][1],sportsexistabbrevatnData[3][1],sportsexistabbrevatnData[4][1]);
				
		boolean errmsgsportsexistabbrevatn = CommonFunctions.isTextPresent("Abbreviation already exists");
		System.out.println(errmsgsportsexistabbrevatn);
			
		if(errmsgsportsexistabbrevatn == true)
		{
			sportsexistabbrevatnStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "sexistAbbrevatnData", sportsexistabbrevatnStatus, 1, 6);
		}
		else
		{
			sportsexistabbrevatnStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "sexistAbbrevatnData", sportsexistabbrevatnStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, sportsexistabbrevatnData[0][1], sportsexistabbrevatnData[1][1], sportsexistabbrevatnStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select Sports in Manage select box (Empty OrderBy)
		String sportsemptyorderbyData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","semptyOrderBYData");
		String sportsemptyorderbyStatus = "";
		
		AppFunction.sports(props.getProperty("txtboxSport_s"),props.getProperty("txtboxAbbreviation_s"),props.getProperty("txtboxOrderby_s"),props.getProperty("sbmtBtn_s"),sportsemptyorderbyData[2][1],sportsemptyorderbyData[3][1],sportsemptyorderbyData[4][1]);
				
		boolean errmsgsportsemptyorderby = CommonFunctions.isTextPresent("Please enter Order By");
		System.out.println(errmsgsportsemptyorderby);
			
		if(errmsgsportsemptyorderby == true)
		{
			sportsemptyorderbyStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "semptyOrderBYData", sportsemptyorderbyStatus, 1, 6);
		}
		else
		{
			sportsemptyorderbyStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "semptyOrderBYData", sportsemptyorderbyStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, sportsemptyorderbyData[0][1], sportsemptyorderbyData[1][1], sportsemptyorderbyStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select Sports in Manage select box (Invalid OrderBy)
		String sportsinvalidorderbyData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","sinvalidOrderBYData");
		String sportsinvalidorderbyStatus = "";
		
		AppFunction.sports(props.getProperty("txtboxSport_s"),props.getProperty("txtboxAbbreviation_s"),props.getProperty("txtboxOrderby_s"),props.getProperty("sbmtBtn_s"),sportsinvalidorderbyData[2][1],sportsinvalidorderbyData[3][1],sportsinvalidorderbyData[4][1]);
				
		boolean errmsgsportsinvalidorderby = CommonFunctions.isTextPresent("Please enter Number for Order By");
		System.out.println(errmsgsportsinvalidorderby);
			
		if(errmsgsportsinvalidorderby == true)
		{
			sportsinvalidorderbyStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "sinvalidOrderBYData", sportsinvalidorderbyStatus, 1, 6);
		}
		else
		{
			sportsinvalidorderbyStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "sinvalidOrderBYData", sportsinvalidorderbyStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		pw.printf(rowFormat, sportsinvalidorderbyData[0][1], sportsinvalidorderbyData[1][1], sportsinvalidorderbyStatus, "");
		
		Thread.sleep(5000);
		
		//Select Country States in Manage select box
		Select select2 = new Select(driver.findElement(By.xpath(props.getProperty("selctSport"))));
		select2.selectByVisibleText("Country States");
		Thread.sleep(3000);
		
		//*******   Select CountryStates in Manage select box (Empty Country)
		String csemptycountryData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","selctCountry");
		String csemptycountryStatus = "";
		
		AppFunction.states(props.getProperty("selctCountry_cs"), props.getProperty("txtboxStateName_cs"), props.getProperty("txtboxStateCode_cs"), props.getProperty("sbmtBtn_cs"), csemptycountryData[2][1],csemptycountryData[3][1],csemptycountryData[4][1]);
				
		boolean errmsgcsemptycountry = CommonFunctions.isTextPresent("Please select Country");
		System.out.println(errmsgcsemptycountry);
			
		if(errmsgcsemptycountry == true)
		{
			csemptycountryStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "selctCountry", csemptycountryStatus, 1, 5);
		}
		else
		{
			csemptycountryStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "selctCountry", csemptycountryStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csemptycountryData[0][1], csemptycountryData[1][1], csemptycountryStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select CountryStates in Manage select box (Empty StateName)
		String csemptyStateNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtemptyStateName");
		String csemptyStateNameStatus = "";
		
		AppFunction.states(props.getProperty("selctCountry_cs"), props.getProperty("txtboxStateName_cs"), props.getProperty("txtboxStateCode_cs"), props.getProperty("sbmtBtn_cs"), csemptyStateNameData[2][1],csemptyStateNameData[3][1],csemptyStateNameData[4][1]);
				
		boolean errmsgcsemptyStateName = CommonFunctions.isTextPresent("Please enter State Name");
		System.out.println(errmsgcsemptyStateName);
			
		if(errmsgcsemptyStateName == true)
		{
			csemptyStateNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtemptyStateName", csemptyStateNameStatus, 1, 5);
		}
		else
		{
			csemptyStateNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtemptyStateName", csemptyStateNameStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csemptyStateNameData[0][1], csemptyStateNameData[1][1], csemptyStateNameStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select CountryStates in Manage select box (Invalid StateName)
		String csinvalidStateNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtinvalidStateName");
		String csinvalidStateNameStatus = "";
		
		AppFunction.states(props.getProperty("selctCountry_cs"), props.getProperty("txtboxStateName_cs"), props.getProperty("txtboxStateCode_cs"), props.getProperty("sbmtBtn_cs"), csinvalidStateNameData[2][1],csinvalidStateNameData[3][1],csinvalidStateNameData[4][1]);
				
		boolean errmsgcsinvalidStateName = CommonFunctions.isTextPresent("Invalid State Name");
		System.out.println(errmsgcsinvalidStateName);
			
		if(errmsgcsinvalidStateName == true)
		{
			csinvalidStateNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtinvalidStateName", csinvalidStateNameStatus, 1, 5);
		}
		else
		{
			csinvalidStateNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtinvalidStateName", csinvalidStateNameStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csinvalidStateNameData[0][1], csinvalidStateNameData[1][1], csinvalidStateNameStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select CountryStates in Manage select box (Exist StateName)
		String csexistStateNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtexistStateName");
		String csexistStateNameStatus = "";
		
		AppFunction.states(props.getProperty("selctCountry_cs"), props.getProperty("txtboxStateName_cs"), props.getProperty("txtboxStateCode_cs"), props.getProperty("sbmtBtn_cs"), csexistStateNameData[2][1],csexistStateNameData[3][1],csexistStateNameData[4][1]);
				
		boolean errmsgcsexistStateName = CommonFunctions.isTextPresent("State Name already exists");
		System.out.println(errmsgcsexistStateName);
			
		if(errmsgcsexistStateName == true)
		{
			csexistStateNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtexistStateName", csexistStateNameStatus, 1, 5);
		}
		else
		{
			csexistStateNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtexistStateName", csexistStateNameStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csexistStateNameData[0][1], csexistStateNameData[1][1], csexistStateNameStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select CountryStates in Manage select box (Empty StateCode)
		String csemptyStateCodeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtemptyStateCode");
		String csemptyStateCodeStatus = "";
		
		AppFunction.states(props.getProperty("selctCountry_cs"), props.getProperty("txtboxStateName_cs"), props.getProperty("txtboxStateCode_cs"), props.getProperty("sbmtBtn_cs"), csemptyStateCodeData[2][1],csemptyStateCodeData[3][1],csemptyStateCodeData[4][1]);
				
		boolean errmsgcsemptyStateCode = CommonFunctions.isTextPresent("Please enter State Code");
		System.out.println(errmsgcsemptyStateCode);
			
		if(errmsgcsemptyStateCode == true)
		{
			csemptyStateCodeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtemptyStateCode", csemptyStateCodeStatus, 1, 5);
		}
		else
		{
			csemptyStateCodeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtemptyStateCode", csemptyStateCodeStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csemptyStateCodeData[0][1], csemptyStateCodeData[1][1], csemptyStateCodeStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select CountryStates in Manage select box (Invalid StateCode)
		String csinvalidStateCodeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtinvalidStateCode");
		String csinvalidStateCodeStatus = "";
		
		AppFunction.states(props.getProperty("selctCountry_cs"), props.getProperty("txtboxStateName_cs"), props.getProperty("txtboxStateCode_cs"), props.getProperty("sbmtBtn_cs"), csinvalidStateCodeData[2][1],csinvalidStateCodeData[3][1],csinvalidStateCodeData[4][1]);
				
		boolean errmsgcsinvalidStateCode = CommonFunctions.isTextPresent("Invalid State Code");
		System.out.println(errmsgcsinvalidStateCode);
			
		if(errmsgcsinvalidStateCode == true)
		{
			csinvalidStateCodeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtinvalidStateCode", csinvalidStateCodeStatus, 1, 5);
		}
		else
		{
			csinvalidStateCodeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtinvalidStateCode", csinvalidStateCodeStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csinvalidStateCodeData[0][1], csinvalidStateCodeData[1][1], csinvalidStateCodeStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select CountryStates in Manage select box (Exist StateCode)
		String csexistStateCodeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtexistStateCode");
		String csexistStateCodeStatus = "";
		
		AppFunction.states(props.getProperty("selctCountry_cs"), props.getProperty("txtboxStateName_cs"), props.getProperty("txtboxStateCode_cs"), props.getProperty("sbmtBtn_cs"), csexistStateCodeData[2][1],csexistStateCodeData[3][1],csexistStateCodeData[4][1]);
				
		boolean errmsgcsexistStateCode = CommonFunctions.isTextPresent("State Code already exists");
		System.out.println(errmsgcsexistStateCode);
			
		if(errmsgcsexistStateCode == true)
		{
			csexistStateCodeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtexistStateCode", csexistStateCodeStatus, 1, 5);
		}
		else
		{
			csexistStateCodeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtexistStateCode", csexistStateCodeStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csexistStateCodeData[0][1], csexistStateCodeData[1][1], csexistStateCodeStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Add Button Clicked   ********
		driver.findElement(By.xpath(props.getProperty("addBtn"))).click(); 
		Thread.sleep(2000);
		System.out.println("Country States Add Button Clicked.");
		
		//*******   Select CountryStates in Manage select box (Empty CountryName)
		String csemptyCountryNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtemptyCountryName");
		String csemptyCountryNameStatus = "";
		
		AppFunction.country(props.getProperty("txtCountryName"), props.getProperty("txtCountryCode"), props.getProperty("sbmtBtn"), csemptyCountryNameData[2][1],csemptyCountryNameData[3][1]);
				
		boolean errmsgcsemptyCountryName = CommonFunctions.isTextPresent("Please enter Country Name");
		System.out.println(errmsgcsemptyCountryName);
			
		if(errmsgcsemptyCountryName == true)
		{
			csemptyCountryNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtemptyCountryName", csemptyCountryNameStatus, 1, 5);
		}
		else
		{
			csemptyCountryNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtemptyCountryName", csemptyCountryNameStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csemptyCountryNameData[0][1], csemptyCountryNameData[1][1], csemptyCountryNameStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select CountryStates in Manage select box (Invalid CountryName)
		String csinvalidCountryNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtinvalidCountryName");
		String csinvalidCountryNameStatus = "";
		
		AppFunction.country(props.getProperty("txtCountryName"), props.getProperty("txtCountryCode"), props.getProperty("sbmtBtn"), csinvalidCountryNameData[2][1],csinvalidCountryNameData[3][1]);
				
		boolean errmsgcsinvalidCountryName = CommonFunctions.isTextPresent("Invalid Country Name");
		System.out.println(errmsgcsinvalidCountryName);
			
		if(errmsgcsinvalidCountryName == true)
		{
			csinvalidCountryNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtinvalidCountryName", csinvalidCountryNameStatus, 1, 5);
		}
		else
		{
			csinvalidCountryNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtinvalidCountryName", csinvalidCountryNameStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csinvalidCountryNameData[0][1], csinvalidCountryNameData[1][1], csinvalidCountryNameStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select CountryStates in Manage select box (exist CountryName)
		String csexistCountryNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtexistCountryName");
		String csexistCountryNameStatus = "";
		
		AppFunction.country(props.getProperty("txtCountryName"), props.getProperty("txtCountryCode"), props.getProperty("sbmtBtn"), csexistCountryNameData[2][1],csexistCountryNameData[3][1]);
				
		boolean errmsgcsexistCountryName = CommonFunctions.isTextPresent("Country Name already exists");
		System.out.println(errmsgcsexistCountryName);
			
		if(errmsgcsexistCountryName == true)
		{
			csexistCountryNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtexistCountryName", csexistCountryNameStatus, 1, 5);
		}
		else
		{
			csexistCountryNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtexistCountryName", csexistCountryNameStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csexistCountryNameData[0][1], csexistCountryNameData[1][1], csexistCountryNameStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select CountryStates in Manage select box (Empty CountryCode)
		String csemptyCountryCodeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtemptyCountryCode");
		String csemptyCountryCodeStatus = "";
		
		AppFunction.country(props.getProperty("txtCountryName"), props.getProperty("txtCountryCode"), props.getProperty("sbmtBtn"), csemptyCountryCodeData[2][1],csemptyCountryCodeData[3][1]);
				
		boolean errmsgcsemptyCountryCode = CommonFunctions.isTextPresent("Please enter Country Code");
		System.out.println(errmsgcsemptyCountryCode);
			
		if(errmsgcsemptyCountryCode == true)
		{
			csemptyCountryCodeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtemptyCountryCode", csemptyCountryCodeStatus, 1, 5);
		}
		else
		{
			csemptyCountryCodeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtemptyCountryCode", csemptyCountryCodeStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csemptyCountryCodeData[0][1], csemptyCountryCodeData[1][1], csemptyCountryCodeStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select CountryStates in Manage select box (Invalid CountryCode)
		String csinvalidCountryCodeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtinvalidCountryCode");
		String csinvalidCountryCodeStatus = "";
		
		AppFunction.country(props.getProperty("txtCountryName"), props.getProperty("txtCountryCode"), props.getProperty("sbmtBtn"), csinvalidCountryCodeData[2][1],csinvalidCountryCodeData[3][1]);
				
		boolean errmsgcsinvalidCountryCode = CommonFunctions.isTextPresent("Invalid Country Code");
		System.out.println(errmsgcsinvalidCountryCode);
			
		if(errmsgcsinvalidCountryCode == true)
		{
			csinvalidCountryCodeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtinvalidCountryCode", csinvalidCountryCodeStatus, 1, 5);
		}
		else
		{
			csinvalidCountryCodeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtinvalidCountryCode", csinvalidCountryCodeStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csinvalidCountryCodeData[0][1], csinvalidCountryCodeData[1][1], csinvalidCountryCodeStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select CountryStates in Manage select box (exist CountryCode)
		String csexistCountryCodeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","txtexistCountryCode");
		String csexistCountryCodeStatus = "";
		
		AppFunction.country(props.getProperty("txtCountryName"), props.getProperty("txtCountryCode"), props.getProperty("sbmtBtn"), csexistCountryCodeData[2][1],csexistCountryCodeData[3][1]);
				
		boolean errmsgcsexistCountryCode = CommonFunctions.isTextPresent("Country Code already exists");
		System.out.println(errmsgcsexistCountryCode);
			
		if(errmsgcsexistCountryCode == true)
		{
			csexistCountryCodeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtexistCountryCode", csexistCountryCodeStatus, 1, 5);
		}
		else
		{
			csexistCountryCodeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "txtexistCountryCode", csexistCountryCodeStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, csexistCountryCodeData[0][1], csexistCountryCodeData[1][1], csexistCountryCodeStatus, "");
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath(props.getProperty("cancelBtn"))).click();
		Thread.sleep(2000);
		
		//Select MultiPlayer Challenges in Manage select box
		Select selectMPC = new Select(driver.findElement(By.xpath(props.getProperty("selctCountryStates"))));
		selectMPC.selectByVisibleText("Multi Player Challenges");
		Thread.sleep(3000);
				
		//*******   Select Multi player challenges in Manage select box (Empty EntryType)
		String mpcemptyEntryTypeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","selctEntryType");
		String mpcemptyEntryTypeStatus = "";
		
		AppFunction.Multiplayerchallenges(props.getProperty("selctbox_MPC_entryType"), props.getProperty("txtbox_MPC_Description"), props.getProperty("txtbox_MPC_OrderBy"), props.getProperty("btn_MPC_Submit"), mpcemptyEntryTypeData[2][1],mpcemptyEntryTypeData[3][1],mpcemptyEntryTypeData[4][1]);
				
		boolean errmsgmpcemptyEntryType = CommonFunctions.isTextPresent("Please select Entry Type");
		System.out.println(errmsgmpcemptyEntryType);
			
		if(errmsgmpcemptyEntryType == true)
		{
			mpcemptyEntryTypeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "selctEntryType", mpcemptyEntryTypeStatus, 1, 6);
		}
		else
		{
			mpcemptyEntryTypeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "selctEntryType", mpcemptyEntryTypeStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, mpcemptyEntryTypeData[0][1], mpcemptyEntryTypeData[1][1], mpcemptyEntryTypeStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select Multi player challenges in Manage select box (Exist EntryType)
		String mpcexistEntryTypeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","selctexistEntryType");
		String mpcexistEntryTypeStatus = "";
		
		AppFunction.Multiplayerchallenges(props.getProperty("selctbox_MPC_entryType"), props.getProperty("txtbox_MPC_Description"), props.getProperty("txtbox_MPC_OrderBy"), props.getProperty("btn_MPC_Submit"), mpcexistEntryTypeData[2][1],mpcexistEntryTypeData[3][1],mpcexistEntryTypeData[4][1]);
				
		boolean errmsgmpcexistEntryType = CommonFunctions.isTextPresent("Entry Type already exists");
		System.out.println(errmsgmpcexistEntryType);
			
		if(errmsgmpcexistEntryType == true)
		{
			mpcexistEntryTypeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "selctexistEntryType", mpcexistEntryTypeStatus, 1, 6);
		}
		else
		{
			mpcexistEntryTypeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "selctexistEntryType", mpcexistEntryTypeStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, mpcexistEntryTypeData[0][1], mpcexistEntryTypeData[1][1], mpcexistEntryTypeStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select Multiplayerchallenges in Manage select box (Empty Description)
		String mpcemptyDescriptionData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","MPCtxtemptyDescription");
		String mpcemptyDescriptionStatus = "";
		
		AppFunction.Multiplayerchallenges(props.getProperty("selctbox_MPC_entryType"), props.getProperty("txtbox_MPC_Description"), props.getProperty("txtbox_MPC_OrderBy"), props.getProperty("btn_MPC_Submit"), mpcemptyDescriptionData[2][1],mpcemptyDescriptionData[3][1],mpcemptyDescriptionData[4][1]);
				
		boolean errmsgmpcemptyDescription = CommonFunctions.isTextPresent("Please Enter Description");
		System.out.println(errmsgmpcemptyDescription);
			
		if(errmsgmpcemptyDescription == true)
		{
			mpcemptyDescriptionStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "MPCtxtemptyDescription", mpcemptyDescriptionStatus, 1, 6);
		}
		else
		{
			mpcemptyDescriptionStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "MPCtxtemptyDescription", mpcemptyDescriptionStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, mpcemptyDescriptionData[0][1], mpcemptyDescriptionData[1][1], mpcemptyDescriptionStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select Multiplayerchallenges in Manage select box (Invalid Description)
		String mpcinvalidDescriptionData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","MPCtxtinvalidDescription");
		String mpcinvalidDescriptionStatus = "";
		
		AppFunction.Multiplayerchallenges(props.getProperty("selctbox_MPC_entryType"), props.getProperty("txtbox_MPC_Description"), props.getProperty("txtbox_MPC_OrderBy"), props.getProperty("btn_MPC_Submit"), mpcinvalidDescriptionData[2][1],mpcinvalidDescriptionData[3][1],mpcinvalidDescriptionData[4][1]);
				
		boolean errmsgmpcinvalidDescription = CommonFunctions.isTextPresent("Invalid Description");
		System.out.println(errmsgmpcinvalidDescription);
			
		if(errmsgmpcinvalidDescription == true)
		{
			mpcinvalidDescriptionStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "MPCtxtinvalidDescription", mpcinvalidDescriptionStatus, 1, 6);
		}
		else
		{
			mpcinvalidDescriptionStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "MPCtxtinvalidDescription", mpcinvalidDescriptionStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, mpcinvalidDescriptionData[0][1], mpcinvalidDescriptionData[1][1], mpcinvalidDescriptionStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select Multiplayerchallenges in Manage select box (Empty OrderBy)
		String mpcemptyOrderbyData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","MPCtxtemptyOrderBy");
		String mpcemptyOrderbyStatus = "";
		
		AppFunction.Multiplayerchallenges(props.getProperty("selctbox_MPC_entryType"), props.getProperty("txtbox_MPC_Description"), props.getProperty("txtbox_MPC_OrderBy"), props.getProperty("btn_MPC_Submit"), mpcemptyOrderbyData[2][1],mpcemptyOrderbyData[3][1],mpcemptyOrderbyData[4][1]);
				
		boolean errmsgmpcemptyOrderby = CommonFunctions.isTextPresent("Please Enter OrderBy");
		System.out.println(errmsgmpcemptyOrderby);
			
		if(errmsgmpcemptyOrderby == true)
		{
			mpcemptyOrderbyStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "MPCtxtemptyOrderBy", mpcemptyOrderbyStatus, 1, 6);
		}
		else
		{
			mpcemptyOrderbyStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "MPCtxtemptyOrderBy", mpcemptyOrderbyStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, mpcemptyOrderbyData[0][1], mpcemptyOrderbyData[1][1], mpcemptyOrderbyStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select Multiplayerchallenges in Manage select box (Invalid OrderBy)
		String mpcinvalidOrderbyData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","MPCtxtinvalidOrderBy");
		String mpcinvalidOrderbyStatus = "";
		
		AppFunction.Multiplayerchallenges(props.getProperty("selctbox_MPC_entryType"), props.getProperty("txtbox_MPC_Description"), props.getProperty("txtbox_MPC_OrderBy"), props.getProperty("btn_MPC_Submit"), mpcinvalidOrderbyData[2][1],mpcinvalidOrderbyData[3][1],mpcinvalidOrderbyData[4][1]);
				
		boolean errmsgmpcinvalidOrderby = CommonFunctions.isTextPresent("Invalid OrderBy");
		System.out.println(errmsgmpcinvalidOrderby);
			
		if(errmsgmpcinvalidOrderby == true)
		{
			mpcinvalidOrderbyStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "MPCtxtinvalidOrderBy", mpcinvalidOrderbyStatus, 1, 6);
		}
		else
		{
			mpcinvalidOrderbyStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "MPCtxtinvalidOrderBy", mpcinvalidOrderbyStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, mpcinvalidOrderbyData[0][1], mpcinvalidOrderbyData[1][1], mpcinvalidOrderbyStatus, "");
		
		Thread.sleep(5000);
		
		//Select HeadtoHead Challenges in Manage select box
		Select selectHtoH = new Select(driver.findElement(By.xpath(props.getProperty("selctbox_MPC_Manage"))));
		selectHtoH.selectByVisibleText("Head To Head Challenges");
		Thread.sleep(3000);
			
		//*******   Select HeadtoHeadchallenges in Manage select box (Empty EntryType)
		String htohemptyEntryTypeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","selctHtoHEntryType");
		String htohemptyEntryTypeStatus = "";
		
		AppFunction.HeadtoHeadChallenges(props.getProperty("selctbox_HtoH_entryType"), props.getProperty("txtbox_HtoH_Description"), props.getProperty("txtbox_HtoH_OrderBy"), props.getProperty("btn_HtoH_Submit"), htohemptyEntryTypeData[2][1],htohemptyEntryTypeData[3][1],htohemptyEntryTypeData[4][1]);
				
		boolean errmsghtohemptyEntryType = CommonFunctions.isTextPresent("Please select Entry Type");
		System.out.println(errmsghtohemptyEntryType);
			
		if(errmsghtohemptyEntryType == true)
		{
			htohemptyEntryTypeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "selctHtoHEntryType", htohemptyEntryTypeStatus, 1, 6);
		}
		else
		{
			htohemptyEntryTypeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "selctHtoHEntryType", htohemptyEntryTypeStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, htohemptyEntryTypeData[0][1], htohemptyEntryTypeData[1][1], htohemptyEntryTypeStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select HeadtoHeadchallenges in Manage select box (Exist EntryType)
		String htohexistEntryTypeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","selctexistHtoHEntryType");
		String htohexistEntryTypeStatus = "";
		
		AppFunction.HeadtoHeadChallenges(props.getProperty("selctbox_HtoH_entryType"), props.getProperty("txtbox_HtoH_Description"), props.getProperty("txtbox_HtoH_OrderBy"), props.getProperty("btn_HtoH_Submit"), htohemptyEntryTypeData[2][1],htohemptyEntryTypeData[3][1],htohemptyEntryTypeData[4][1]);
				
		boolean errmsghtohexistEntryType = CommonFunctions.isTextPresent("Entry Type already exists");
		System.out.println(errmsghtohexistEntryType);
			
		if(errmsghtohexistEntryType == true)
		{
			htohexistEntryTypeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "selctexistHtoHEntryType", htohexistEntryTypeStatus, 1, 6);
		}
		else
		{
			htohexistEntryTypeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "selctexistHtoHEntryType", htohexistEntryTypeStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
	
		pw.printf(rowFormat, htohexistEntryTypeData[0][1], htohexistEntryTypeData[1][1], htohexistEntryTypeStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select HeadtoHeadchallenges in Manage select box (Empty Description)
		String htohemptyDescriptionData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","HtoHtxtemptyDescription");
		String htohemptyDescriptionStatus = "";
		
		AppFunction.HeadtoHeadChallenges(props.getProperty("selctbox_HtoH_entryType"), props.getProperty("txtbox_HtoH_Description"), props.getProperty("txtbox_HtoH_OrderBy"), props.getProperty("btn_HtoH_Submit"), htohemptyEntryTypeData[2][1],htohemptyEntryTypeData[3][1],htohemptyEntryTypeData[4][1]);
				
		boolean errmsghtohemptyDescription = CommonFunctions.isTextPresent("Please Enter Description");
		System.out.println(errmsghtohemptyDescription);
			
		if(errmsghtohemptyDescription == true)
		{
			htohemptyDescriptionStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "HtoHtxtemptyDescription", htohemptyDescriptionStatus, 1, 6);
		}
		else
		{
			htohemptyDescriptionStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "HtoHtxtemptyDescription", htohemptyDescriptionStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
			
		pw.printf(rowFormat, htohemptyDescriptionData[0][1], htohemptyDescriptionData[1][1], htohemptyDescriptionStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select HeadtoHeadchallenges in Manage select box (Invalid Description)
		String htohinvalidDescriptionData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","HtoHtxtinvalidDescription");
		String htohinvalidDescriptionStatus = "";
		
		AppFunction.HeadtoHeadChallenges(props.getProperty("selctbox_HtoH_entryType"), props.getProperty("txtbox_HtoH_Description"), props.getProperty("txtbox_HtoH_OrderBy"), props.getProperty("btn_HtoH_Submit"), htohemptyEntryTypeData[2][1],htohemptyEntryTypeData[3][1],htohemptyEntryTypeData[4][1]);
				
		boolean errmsghtohinvalidDescription = CommonFunctions.isTextPresent("Invalid Description");
		System.out.println(errmsghtohinvalidDescription);
			
		if(errmsghtohinvalidDescription == true)
		{
			htohinvalidDescriptionStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "HtoHtxtinvalidDescription", htohinvalidDescriptionStatus, 1, 6);
		}
		else
		{
			htohinvalidDescriptionStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "HtoHtxtinvalidDescription", htohinvalidDescriptionStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		pw.printf(rowFormat, htohinvalidDescriptionData[0][1], htohinvalidDescriptionData[1][1], htohinvalidDescriptionStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select HeadtoHeadchallenges in Manage select box (Empty OrderBy)
		String htohemptyOrderbyData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","HtoHtxtemptyOrderBy");
		String htohemptyOrderbyStatus = "";
		
		AppFunction.HeadtoHeadChallenges(props.getProperty("selctbox_HtoH_entryType"), props.getProperty("txtbox_HtoH_Description"), props.getProperty("txtbox_HtoH_OrderBy"), props.getProperty("btn_HtoH_Submit"), htohemptyEntryTypeData[2][1],htohemptyEntryTypeData[3][1],htohemptyEntryTypeData[4][1]);
				
		boolean errmsghtohemptyOrderby = CommonFunctions.isTextPresent("Please Enter OrderBy");
		System.out.println(errmsghtohemptyOrderby);
			
		if(errmsghtohemptyOrderby == true)
		{
			htohemptyOrderbyStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "HtoHtxtemptyOrderBy", htohemptyOrderbyStatus, 1, 6);
		}
		else
		{
			htohemptyOrderbyStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "HtoHtxtemptyOrderBy", htohemptyOrderbyStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, htohemptyOrderbyData[0][1], htohemptyOrderbyData[1][1], htohemptyOrderbyStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select HeadtoHeadchallenges in Manage select box (Invalid OrderBy)
		String htohinvalidOrderbyData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","HtoHtxtinvalidOrderBy");
		String htohinvalidOrderbyStatus = "";
		
		AppFunction.HeadtoHeadChallenges(props.getProperty("selctbox_HtoH_entryType"), props.getProperty("txtbox_HtoH_Description"), props.getProperty("txtbox_HtoH_OrderBy"), props.getProperty("btn_HtoH_Submit"), htohemptyEntryTypeData[2][1],htohemptyEntryTypeData[3][1],htohemptyEntryTypeData[4][1]);
				
		boolean errmsghtohinvalidOrderby = CommonFunctions.isTextPresent("Invalid OrderBy");
		System.out.println(errmsghtohinvalidOrderby);
			
		if(errmsghtohinvalidOrderby == true)
		{
			htohinvalidOrderbyStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "HtoHtxtinvalidOrderBy", htohinvalidOrderbyStatus, 1, 6);
		}
		else
		{
			htohinvalidOrderbyStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "HtoHtxtinvalidOrderBy", htohinvalidOrderbyStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}

		pw.printf(rowFormat, htohinvalidOrderbyData[0][1], htohinvalidOrderbyData[1][1], htohinvalidOrderbyStatus, "");
		
		Thread.sleep(5000);
		
		//Select EmailConfig in Manage select box
		Select selectEC = new Select(driver.findElement(By.xpath(props.getProperty("selctbox_HtoH_Manage"))));
		selectEC.selectByVisibleText("Email Config");
		Thread.sleep(3000);
					
		//*******   Select EmailConfig in Manage select box (Empty EmailType)
		String echemptyEmailTypeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","emptyEmailType");
		String echemptyEmailTypeStatus = "";
		
		AppFunction.emailConfig(props.getProperty("selctbox_EC_emailType"), props.getProperty("txtbox_EC_fromaddr"), props.getProperty("txtbox_EC_subject"), props.getProperty("txtbox_EC_emailcontent"), props.getProperty("btn_EC_Submit"), echemptyEmailTypeData[2][1],echemptyEmailTypeData[3][1],echemptyEmailTypeData[4][1],echemptyEmailTypeData[5][1]);
				
		boolean errmsgechemptyEmailType = CommonFunctions.isTextPresent("Please select Email Type");
		System.out.println(errmsgechemptyEmailType);
			
		if(errmsgechemptyEmailType == true)
		{
			echemptyEmailTypeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyEmailType", echemptyEmailTypeStatus, 1, 6);
		}
		else
		{
			echemptyEmailTypeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyEmailType", echemptyEmailTypeStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, echemptyEmailTypeData[0][1], echemptyEmailTypeData[1][1], echemptyEmailTypeStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select EmailConfig in Manage select box (Exist EmailType)
		String echexistEmailTypeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","existEmailType");
		String echexistEmailTypeStatus = "";
		
		AppFunction.emailConfig(props.getProperty("selctbox_EC_emailType"), props.getProperty("txtbox_EC_fromaddr"), props.getProperty("txtbox_EC_subject"), props.getProperty("txtbox_EC_emailcontent"), props.getProperty("btn_EC_Submit"), echexistEmailTypeData[2][1],echexistEmailTypeData[3][1],echexistEmailTypeData[4][1],echexistEmailTypeData[5][1]);
				
		boolean errmsgechexistEmailType = CommonFunctions.isTextPresent("Email Type already exists");
		System.out.println(errmsgechexistEmailType);
			
		if(errmsgechexistEmailType == true)
		{
			echexistEmailTypeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "existEmailType", echexistEmailTypeStatus, 1, 6);
		}
		else
		{
			echexistEmailTypeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "existEmailType", echexistEmailTypeStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, echexistEmailTypeData[0][1], echexistEmailTypeData[1][1], echexistEmailTypeStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select EmailConfig in Manage select box (Empty FromAddress)
		String ecemptyfromaddrData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","emptyFromaddr");
		String ecemptyfromaddrStatus = "";
		
		AppFunction.emailConfig(props.getProperty("selctbox_EC_emailType"), props.getProperty("txtbox_EC_fromaddr"), props.getProperty("txtbox_EC_subject"), props.getProperty("txtbox_EC_emailcontent"), props.getProperty("btn_EC_Submit"), ecemptyfromaddrData[2][1],ecemptyfromaddrData[3][1],ecemptyfromaddrData[4][1],ecemptyfromaddrData[5][1]);
				
		boolean errmsgecemptyfromaddr = CommonFunctions.isTextPresent("Please enter FromAddress");
		System.out.println(errmsgecemptyfromaddr);
			
		if(errmsgecemptyfromaddr == true)
		{
			ecemptyfromaddrStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyFromaddr", ecemptyfromaddrStatus, 1, 6);
		}
		else
		{
			ecemptyfromaddrStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyFromaddr", ecemptyfromaddrStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, ecemptyfromaddrData[0][1], ecemptyfromaddrData[1][1], ecemptyfromaddrStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select EmailConfig in Manage select box (Invalid FromAddress)
		String ecinvalidfromaddrData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidFromaddr");
		String ecinvalidfromaddrStatus = "";
		
		AppFunction.emailConfig(props.getProperty("selctbox_EC_emailType"), props.getProperty("txtbox_EC_fromaddr"), props.getProperty("txtbox_EC_subject"), props.getProperty("txtbox_EC_emailcontent"), props.getProperty("btn_EC_Submit"), ecinvalidfromaddrData[2][1],ecinvalidfromaddrData[3][1],ecinvalidfromaddrData[4][1],ecinvalidfromaddrData[5][1]);
				
		boolean errmsgecinvalidfromaddr = CommonFunctions.isTextPresent("Invalid FromAddress");
		System.out.println(errmsgecinvalidfromaddr);
			
		if(errmsgecinvalidfromaddr == true)
		{
			ecinvalidfromaddrStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidFromaddr", ecinvalidfromaddrStatus, 1, 6);
		}
		else
		{
			ecinvalidfromaddrStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidFromaddr", ecinvalidfromaddrStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, ecinvalidfromaddrData[0][1], ecinvalidfromaddrData[1][1], ecinvalidfromaddrStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select EmailConfig in Manage select box (Empty Subject)
		String ecemptysubjectData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","emptySubject");
		String ecemptysubjectStatus = "";
		
		AppFunction.emailConfig(props.getProperty("selctbox_EC_emailType"), props.getProperty("txtbox_EC_fromaddr"), props.getProperty("txtbox_EC_subject"), props.getProperty("txtbox_EC_emailcontent"), props.getProperty("btn_EC_Submit"), ecemptysubjectData[2][1],ecemptysubjectData[3][1],ecemptysubjectData[4][1],ecemptysubjectData[5][1]);
				
		boolean errmsgecemptysubject = CommonFunctions.isTextPresent("Please enter Subject");
		System.out.println(errmsgecemptysubject);
			
		if(errmsgecemptysubject == true)
		{
			ecemptysubjectStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptySubject", ecemptysubjectStatus, 1, 6);
		}
		else
		{
			ecemptysubjectStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptySubject", ecemptysubjectStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, ecemptysubjectData[0][1], ecemptysubjectData[1][1], ecemptysubjectStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select EmailConfig in Manage select box (Empty EmailContent)
		String ecemptyemailContentData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","emptyEmailContent");
		String ecemptyemailContentStatus = "";
		
		AppFunction.emailConfig(props.getProperty("selctbox_EC_emailType"), props.getProperty("txtbox_EC_fromaddr"), props.getProperty("txtbox_EC_subject"), props.getProperty("txtbox_EC_emailcontent"), props.getProperty("btn_EC_Submit"), ecemptyemailContentData[2][1],ecemptyemailContentData[3][1],ecemptyemailContentData[4][1],ecemptyemailContentData[5][1]);
				
		boolean errmsgecemptyemailContent = CommonFunctions.isTextPresent("Please enter EmailContent");
		System.out.println(errmsgecemptyemailContent);
			
		if(errmsgecemptyemailContent == true)
		{
			ecemptyemailContentStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyEmailContent", ecemptyemailContentStatus, 1, 6);
		}
		else
		{
			ecemptyemailContentStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyEmailContent", ecemptyemailContentStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, ecemptyemailContentData[0][1], ecemptyemailContentData[1][1], ecemptyemailContentStatus, "");
		
		Thread.sleep(5000);
		
		//Select NFL Schedules in Manage select box
		Select selectNFLS = new Select(driver.findElement(By.xpath(props.getProperty("slectbox_EC_Manage"))));
		selectNFLS.selectByVisibleText("NFL Schedules");
		Thread.sleep(3000);
				
		//*******   Select NFL Schedules in Manage select box (Invalid Year)
		String nflschdleinvalidYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLSYear");
		String nflschdleinvalidYearStatus = "";
		
		AppFunction.NFLschdleYear(props.getProperty("txtbox_NFLS_Year"), props.getProperty("txtbox_NFLS_Season"), props.getProperty("txtbox_NFLS_Week"), props.getProperty("txtbox_NFLS_Gamestatus"), props.getProperty("txtbox_NFLS_Playday"), props.getProperty("btn_NFLS_Search"), nflschdleinvalidYearData[2][1],nflschdleinvalidYearData[3][1],nflschdleinvalidYearData[4][1],nflschdleinvalidYearData[5][1],nflschdleinvalidYearData[6][1]);
				
		boolean errmsgnflschdleinvalidYear = CommonFunctions.isTextPresent("Please enter Number for Year");
		System.out.println(errmsgnflschdleinvalidYear);
			
		if(errmsgnflschdleinvalidYear == true)
		{
			nflschdleinvalidYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSYear", nflschdleinvalidYearStatus, 1, 7);
		}
		else
		{
			nflschdleinvalidYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSYear", nflschdleinvalidYearStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflschdleinvalidYearData[0][1], nflschdleinvalidYearData[1][1], nflschdleinvalidYearStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select NFL Schedules in Manage select box (Invalid minYear)
		String nflschdleinvalidminYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidminNFLSYear");
		String nflschdleinvalidminYearStatus = "";
		
		AppFunction.NFLschdleYear(props.getProperty("txtbox_NFLS_Year"), props.getProperty("txtbox_NFLS_Season"), props.getProperty("txtbox_NFLS_Week"), props.getProperty("txtbox_NFLS_Gamestatus"), props.getProperty("txtbox_NFLS_Playday"), props.getProperty("btn_NFLS_Search"), nflschdleinvalidminYearData[2][1],nflschdleinvalidminYearData[3][1],nflschdleinvalidminYearData[4][1],nflschdleinvalidminYearData[5][1],nflschdleinvalidminYearData[6][1]);
				
		boolean errmsgnflschdleinvalidminYear = CommonFunctions.isTextPresent("Please enter 4 digits for Year");
		System.out.println(errmsgnflschdleinvalidminYear);
			
		if(errmsgnflschdleinvalidminYear == true)
		{
			nflschdleinvalidminYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidminNFLSYear", nflschdleinvalidminYearStatus, 1, 7);
		}
		else
		{
			nflschdleinvalidminYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidminNFLSYear", nflschdleinvalidminYearStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflschdleinvalidminYearData[0][1], nflschdleinvalidminYearData[1][1], nflschdleinvalidminYearStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Schedules in Manage select box (Valid Year)
		String nflschdlevalidYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","validNFLSYear");
		String nflschdlevalidYearStatus = "";
		
		AppFunction.NFLschdleYear(props.getProperty("txtbox_NFLS_Year"), props.getProperty("txtbox_NFLS_Season"), props.getProperty("txtbox_NFLS_Week"), props.getProperty("txtbox_NFLS_Gamestatus"), props.getProperty("txtbox_NFLS_Playday"), props.getProperty("btn_NFLS_Search"), nflschdlevalidYearData[2][1],nflschdlevalidYearData[3][1],nflschdlevalidYearData[4][1],nflschdlevalidYearData[5][1],nflschdlevalidYearData[6][1]);
				
		boolean errmsgnflschdlevalidYear = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflschdlevalidYear);
			
		if(errmsgnflschdlevalidYear == true)
		{
			nflschdlevalidYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validNFLSYear", nflschdlevalidYearStatus, 1, 7);
		}
		else
		{
			nflschdlevalidYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validNFLSYear", nflschdlevalidYearStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflschdlevalidYearData[0][1], nflschdlevalidYearData[1][1], nflschdlevalidYearStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select NFL Schedules in Manage select box (Invalid Season)
		String nflschdleinvalidSeasonData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLSSeason");
		String nflschdleinvalidSeasonStatus = "";
		
		AppFunction.NFLschdleYear(props.getProperty("txtbox_NFLS_Year"), props.getProperty("txtbox_NFLS_Season"), props.getProperty("txtbox_NFLS_Week"), props.getProperty("txtbox_NFLS_Gamestatus"), props.getProperty("txtbox_NFLS_Playday"), props.getProperty("btn_NFLS_Search"), nflschdleinvalidSeasonData[2][1],nflschdleinvalidSeasonData[3][1],nflschdleinvalidSeasonData[4][1],nflschdleinvalidSeasonData[5][1],nflschdleinvalidSeasonData[6][1]);
				
		boolean errmsgnflschdleinvalidSeason = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflschdleinvalidSeason);
			
		if(errmsgnflschdleinvalidSeason == true)
		{
			nflschdleinvalidSeasonStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSSeason", nflschdleinvalidSeasonStatus, 1, 7);
		}
		else
		{
			nflschdleinvalidSeasonStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSSeason", nflschdleinvalidSeasonStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflschdleinvalidSeasonData[0][1], nflschdleinvalidSeasonData[1][1], nflschdleinvalidSeasonStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Schedules in Manage select box (Invalid Week)
		String nflschdleinvalidWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLSWeek");
		String nflschdleinvalidWeekStatus = "";
		
		AppFunction.NFLschdleYear(props.getProperty("txtbox_NFLS_Year"), props.getProperty("txtbox_NFLS_Season"), props.getProperty("txtbox_NFLS_Week"), props.getProperty("txtbox_NFLS_Gamestatus"), props.getProperty("txtbox_NFLS_Playday"), props.getProperty("btn_NFLS_Search"), nflschdleinvalidWeekData[2][1],nflschdleinvalidWeekData[3][1],nflschdleinvalidWeekData[4][1],nflschdleinvalidWeekData[5][1],nflschdleinvalidWeekData[6][1]);
				
		boolean errmsgnflschdleinvalidWeek = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflschdleinvalidWeek);
			
		if(errmsgnflschdleinvalidWeek == true)
		{
			nflschdleinvalidWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSWeek", nflschdleinvalidWeekStatus, 1, 7);
		}
		else
		{
			nflschdleinvalidWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSWeek", nflschdleinvalidWeekStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflschdleinvalidWeekData[0][1], nflschdleinvalidWeekData[1][1], nflschdleinvalidWeekStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Schedules in Manage select box (Exceeding WeekNumber)
		String nflschdleexceedWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","ExceedingWeekNumber");
		String nflschdleexceedWeekStatus = "";
		
		AppFunction.NFLschdleYear(props.getProperty("txtbox_NFLS_Year"), props.getProperty("txtbox_NFLS_Season"), props.getProperty("txtbox_NFLS_Week"), props.getProperty("txtbox_NFLS_Gamestatus"), props.getProperty("txtbox_NFLS_Playday"), props.getProperty("btn_NFLS_Search"), nflschdleexceedWeekData[2][1],nflschdleexceedWeekData[3][1],nflschdleexceedWeekData[4][1],nflschdleexceedWeekData[5][1],nflschdleexceedWeekData[6][1]);
				
		boolean errmsgnflschdleexceedWeek = CommonFunctions.isTextPresent("Please enter value from 0 to 52 for Week");
		System.out.println(errmsgnflschdleexceedWeek);
			
		if(errmsgnflschdleexceedWeek == true)
		{
			nflschdleexceedWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ExceedingWeekNumber", nflschdleexceedWeekStatus, 1, 7);
		}
		else
		{
			nflschdleexceedWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "ExceedingWeekNumber", nflschdleexceedWeekStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflschdleexceedWeekData[0][1], nflschdleexceedWeekData[1][1], nflschdleexceedWeekStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select NFL Schedules in Manage select box (InvalidDataarthemetic Week)
		String nflschdleinvalidarthemeticWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLSchartersWeek");
		String nflschdleinvalidarthemeticWeekStatus = "";
		
		AppFunction.NFLschdleYear(props.getProperty("txtbox_NFLS_Year"), props.getProperty("txtbox_NFLS_Season"), props.getProperty("txtbox_NFLS_Week"), props.getProperty("txtbox_NFLS_Gamestatus"), props.getProperty("txtbox_NFLS_Playday"), props.getProperty("btn_NFLS_Search"), nflschdleinvalidarthemeticWeekData[2][1],nflschdleinvalidarthemeticWeekData[3][1],nflschdleinvalidarthemeticWeekData[4][1],nflschdleinvalidarthemeticWeekData[5][1],nflschdleinvalidarthemeticWeekData[6][1]);
				
		boolean errmsgnflschdleinvalidarthemeticWeek = CommonFunctions.isTextPresent("Please enter Number for Week");
		System.out.println(errmsgnflschdleinvalidarthemeticWeek);
			
		if(errmsgnflschdleinvalidarthemeticWeek == true)
		{
			nflschdleinvalidarthemeticWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSchartersWeek", nflschdleinvalidarthemeticWeekStatus, 1, 7);
		}
		else
		{
			nflschdleinvalidarthemeticWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSchartersWeek", nflschdleinvalidarthemeticWeekStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflschdleinvalidarthemeticWeekData[0][1], nflschdleinvalidarthemeticWeekData[1][1], nflschdleinvalidarthemeticWeekStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select NFL Schedules in Manage select box (Invalid GameStatus)
		String nflschdleinvalidgamestatusData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLSgameStatus");
		String nflschdleinvalidgamestatusStatus = "";
		
		AppFunction.NFLschdleYear(props.getProperty("txtbox_NFLS_Year"), props.getProperty("txtbox_NFLS_Season"), props.getProperty("txtbox_NFLS_Week"), props.getProperty("txtbox_NFLS_Gamestatus"), props.getProperty("txtbox_NFLS_Playday"), props.getProperty("btn_NFLS_Search"), nflschdleinvalidgamestatusData[2][1],nflschdleinvalidgamestatusData[3][1],nflschdleinvalidgamestatusData[4][1],nflschdleinvalidgamestatusData[5][1],nflschdleinvalidgamestatusData[6][1]);
				
		boolean errmsgnflschdleinvalidgamestatus = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflschdleinvalidgamestatus);
			
		if(errmsgnflschdleinvalidgamestatus == true)
		{
			nflschdleinvalidgamestatusStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSgameStatus", nflschdleinvalidgamestatusStatus, 1, 7);
		}
		else
		{
			nflschdleinvalidgamestatusStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSgameStatus", nflschdleinvalidgamestatusStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflschdleinvalidgamestatusData[0][1], nflschdleinvalidgamestatusData[1][1], nflschdleinvalidgamestatusStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Schedules in Manage select box (Invalid PlayDay)
		String nflschdleinvalidplaydayData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLSplayday");
		String nflschdleinvalidplaydayStatus = "";
		
		AppFunction.NFLschdleYear(props.getProperty("txtbox_NFLS_Year"), props.getProperty("txtbox_NFLS_Season"), props.getProperty("txtbox_NFLS_Week"), props.getProperty("txtbox_NFLS_Gamestatus"), props.getProperty("txtbox_NFLS_Playday"), props.getProperty("btn_NFLS_Search"), nflschdleinvalidplaydayData[2][1],nflschdleinvalidplaydayData[3][1],nflschdleinvalidplaydayData[4][1],nflschdleinvalidplaydayData[5][1],nflschdleinvalidplaydayData[6][1]);
				
		boolean errmsgnflschdleinvalidplayday = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflschdleinvalidplayday);
			
		if(errmsgnflschdleinvalidplayday == true)
		{
			nflschdleinvalidplaydayStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSplayday", nflschdleinvalidplaydayStatus, 1, 7);
		}
		else
		{
			nflschdleinvalidplaydayStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLSplayday", nflschdleinvalidplaydayStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflschdleinvalidplaydayData[0][1], nflschdleinvalidplaydayData[1][1], nflschdleinvalidplaydayStatus, "");
		
		Thread.sleep(5000);
		
		//Select NFL BoxScore in Manage select box
		Select selectNFLBS = new Select(driver.findElement(By.xpath(props.getProperty("slectbox_NFLS_Manage"))));
		selectNFLBS.selectByVisibleText("NFL Box Score");
		Thread.sleep(3000);
			
		//*******   Select NFL BoxScore in Manage select box (Empty Year)
		String nflboxscoreemtpyYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","emptyNFLBSYear");
		String nflboxscoreemtpyYearStatus = "";
				
		AppFunction.NFLBoxScore(props.getProperty("txtbox_NFLBS_Year"), props.getProperty("txtbox_NFLBS_Season"), props.getProperty("txtbox_NFLBS_Week"), props.getProperty("btn_NFLBS_Search"), nflboxscoreemtpyYearData[2][1],nflboxscoreemtpyYearData[3][1],nflboxscoreemtpyYearData[4][1]);
						
		boolean errmsgnflboxscoreemtpyYear = CommonFunctions.isTextPresent("Please enter Search Criteria.");
		System.out.println(errmsgnflboxscoreemtpyYear);
					
		if(errmsgnflboxscoreemtpyYear == true)
		{
			nflboxscoreemtpyYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyNFLBSYear", nflboxscoreemtpyYearStatus, 1, 5);
		}
		else
		{
			nflboxscoreemtpyYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyNFLBSYear", nflboxscoreemtpyYearStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflboxscoreemtpyYearData[0][1], nflboxscoreemtpyYearData[1][1], nflboxscoreemtpyYearStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NFL BoxScore in Manage select box (Invalid Year)
		String nflboxscoreinvalidYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLBSYear");
		String nflboxscoreinvalidYearStatus = "";
				
		AppFunction.NFLBoxScore(props.getProperty("txtbox_NFLBS_Year"), props.getProperty("txtbox_NFLBS_Season"), props.getProperty("txtbox_NFLBS_Week"), props.getProperty("btn_NFLBS_Search"), nflboxscoreinvalidYearData[2][1],nflboxscoreinvalidYearData[3][1],nflboxscoreinvalidYearData[4][1]);
						
		boolean errmsgnflboxscoreinvalidYear = CommonFunctions.isTextPresent("Please enter Number for Year");
		System.out.println(errmsgnflboxscoreinvalidYear);
					
		if(errmsgnflboxscoreinvalidYear == true)
		{
			nflboxscoreinvalidYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLBSYear", nflboxscoreinvalidYearStatus, 1, 5);
		}
		else
		{
			nflboxscoreinvalidYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLBSYear", nflboxscoreinvalidYearStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflboxscoreinvalidYearData[0][1], nflboxscoreinvalidYearData[1][1], nflboxscoreinvalidYearStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NFL BoxScore in Manage select box (Min Year)
		String nflboxscoreminYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidminNFLBSYear");
		String nflboxscoreminYearStatus = "";
				
		AppFunction.NFLBoxScore(props.getProperty("txtbox_NFLBS_Year"), props.getProperty("txtbox_NFLBS_Season"), props.getProperty("txtbox_NFLBS_Week"), props.getProperty("btn_NFLBS_Search"), nflboxscoreminYearData[2][1],nflboxscoreminYearData[3][1],nflboxscoreminYearData[4][1]);
						
		boolean errmsgnflboxscoreminYear = CommonFunctions.isTextPresent("Please enter 4 digits for Year");
		System.out.println(errmsgnflboxscoreminYear);
					
		if(errmsgnflboxscoreminYear == true)
		{
			nflboxscoreminYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidminNFLBSYear", nflboxscoreminYearStatus, 1, 5);
		}
		else
		{
			nflboxscoreminYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidminNFLBSYear", nflboxscoreminYearStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflboxscoreminYearData[0][1], nflboxscoreminYearData[1][1], nflboxscoreminYearStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NFL BoxScore in Manage select box (Valid Year)
		String nflboxscorevalidYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","validNFLBSYear");
		String nflboxscorevalidYearStatus = "";
				
		AppFunction.NFLBoxScore(props.getProperty("txtbox_NFLBS_Year"), props.getProperty("txtbox_NFLBS_Season"), props.getProperty("txtbox_NFLBS_Week"), props.getProperty("btn_NFLBS_Search"), nflboxscorevalidYearData[2][1],nflboxscorevalidYearData[3][1],nflboxscorevalidYearData[4][1]);
						
		boolean errmsgnflboxscorevalidYear = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflboxscorevalidYear);
					
		if(errmsgnflboxscorevalidYear == true)
		{
			nflboxscorevalidYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validNFLBSYear", nflboxscorevalidYearStatus, 1, 5);
		}
		else
		{
			nflboxscorevalidYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validNFLBSYear", nflboxscorevalidYearStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflboxscorevalidYearData[0][1], nflboxscorevalidYearData[1][1], nflboxscorevalidYearStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NFL BoxScore in Manage select box (Invalid Season)
		String nflboxscoreinvalidSeasonData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLBSSeason");
		String nflboxscoreinvalidSeasonStatus = "";
				
		AppFunction.NFLBoxScore(props.getProperty("txtbox_NFLBS_Year"), props.getProperty("txtbox_NFLBS_Season"), props.getProperty("txtbox_NFLBS_Week"), props.getProperty("btn_NFLBS_Search"), nflboxscoreinvalidSeasonData[2][1],nflboxscoreinvalidSeasonData[3][1],nflboxscoreinvalidSeasonData[4][1]);
						
		boolean errmsgnflboxscoreinvalidSeason = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflboxscoreinvalidSeason);
					
		if(errmsgnflboxscoreinvalidSeason == true)
		{
			nflboxscoreinvalidSeasonStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLBSSeason", nflboxscoreinvalidSeasonStatus, 1, 5);
		}
		else
		{
			nflboxscoreinvalidSeasonStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLBSSeason", nflboxscoreinvalidSeasonStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflboxscoreinvalidSeasonData[0][1], nflboxscoreinvalidSeasonData[1][1], nflboxscoreinvalidSeasonStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NFL BoxScore in Manage select box (Invalid Week)
		String nflboxscoreinvalidWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLBSWeek");
		String nflboxscoreinvalidWeekStatus = "";
				
		AppFunction.NFLBoxScore(props.getProperty("txtbox_NFLBS_Year"), props.getProperty("txtbox_NFLBS_Season"), props.getProperty("txtbox_NFLBS_Week"), props.getProperty("btn_NFLBS_Search"), nflboxscoreinvalidWeekData[2][1],nflboxscoreinvalidWeekData[3][1],nflboxscoreinvalidWeekData[4][1]);
						
		boolean errmsgnflboxscoreinvalidWeek = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflboxscoreinvalidWeek);
					
		if(errmsgnflboxscoreinvalidWeek == true)
		{
			nflboxscoreinvalidWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLBSWeek", nflboxscoreinvalidWeekStatus, 1, 5);
		}
		else
		{
			nflboxscoreinvalidWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLBSWeek", nflboxscoreinvalidWeekStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflboxscoreinvalidWeekData[0][1], nflboxscoreinvalidWeekData[1][1], nflboxscoreinvalidWeekStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NFL BoxScore in Manage select box (Exceeding WeekNumber)
		String nflboxscoreexceedWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","exceedNFLBSWeek");
		String nflboxscoreexceedWeekStatus = "";
				
		AppFunction.NFLBoxScore(props.getProperty("txtbox_NFLBS_Year"), props.getProperty("txtbox_NFLBS_Season"), props.getProperty("txtbox_NFLBS_Week"), props.getProperty("btn_NFLBS_Search"), nflboxscoreexceedWeekData[2][1],nflboxscoreexceedWeekData[3][1],nflboxscoreexceedWeekData[4][1]);
						
		boolean errmsgnflboxscoreexceedWeek = CommonFunctions.isTextPresent("Please enter value from 0 to 52 for Week");
		System.out.println(errmsgnflboxscoreexceedWeek);
					
		if(errmsgnflboxscoreexceedWeek == true)
		{
			nflboxscoreexceedWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "exceedNFLBSWeek", nflboxscoreexceedWeekStatus, 1, 5);
		}
		else
		{
			nflboxscoreexceedWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "exceedNFLBSWeek", nflboxscoreexceedWeekStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflboxscoreexceedWeekData[0][1], nflboxscoreexceedWeekData[1][1], nflboxscoreexceedWeekStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NFL BoxScore in Manage select box (alphabeticCharacters of Week)
		String nflboxscorealphaCharWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","arthemeticsNFLBSWeek");
		String nflboxscorealphaCharWeekStatus = "";
				
		AppFunction.NFLBoxScore(props.getProperty("txtbox_NFLBS_Year"), props.getProperty("txtbox_NFLBS_Season"), props.getProperty("txtbox_NFLBS_Week"), props.getProperty("btn_NFLBS_Search"), nflboxscorealphaCharWeekData[2][1],nflboxscorealphaCharWeekData[3][1],nflboxscorealphaCharWeekData[4][1]);
						
		boolean errmsgnflboxscorealphaCharWeek = CommonFunctions.isTextPresent("Please enter Number for Week");
		System.out.println(errmsgnflboxscorealphaCharWeek);
					
		if(errmsgnflboxscorealphaCharWeek == true)
		{
			nflboxscorealphaCharWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "arthemeticsNFLBSWeek", nflboxscorealphaCharWeekStatus, 1, 5);
		}
		else
		{
			nflboxscorealphaCharWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "arthemeticsNFLBSWeek", nflboxscorealphaCharWeekStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflboxscorealphaCharWeekData[0][1], nflboxscorealphaCharWeekData[1][1], nflboxscorealphaCharWeekStatus, "");
				
		Thread.sleep(5000);
	
		
		//Select NFL Statistics in Manage select box
		Select selectNFLStatics = new Select(driver.findElement(By.xpath(props.getProperty("slectbox_NFLBS_Manage"))));
		selectNFLStatics.selectByVisibleText("NFL Statistics");
		Thread.sleep(3000);
		
		//*******   Select NFL BoxScore in Manage select box (Empty Year)
		String nflstaticsemptyYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","emptyNFLStaticsYear");
		String nflstaticsemptyYearStatus = "";
		
		AppFunction.NFLStatistics(props.getProperty("txtbox_NFLStatics_Year"), props.getProperty("txtbox_NFLStatics_Season"), props.getProperty("txtbox_NFLStatics_Week"), props.getProperty("txtbox_NFLStatics_Team"), props.getProperty("txtbox_NFLStatics_PlayerName"), props.getProperty("txtbox_NFLStatics_PlayerId"), props.getProperty("btn_NFLStatics_Search"), nflstaticsemptyYearData[2][1],nflstaticsemptyYearData[3][1],nflstaticsemptyYearData[4][1],nflstaticsemptyYearData[5][1],nflstaticsemptyYearData[6][1],nflstaticsemptyYearData[7][1]);
				
		boolean errmsgnflstaticsemptyYear = CommonFunctions.isTextPresent("Please enter Search Criteria.");
		System.out.println(errmsgnflstaticsemptyYear);
			
		if(errmsgnflstaticsemptyYear == true)
		{
			nflstaticsemptyYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyNFLStaticsYear", nflstaticsemptyYearStatus, 1, 8);
		}
		else
		{
			nflstaticsemptyYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyNFLStaticsYear", nflstaticsemptyYearStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflstaticsemptyYearData[0][1], nflstaticsemptyYearData[1][1], nflstaticsemptyYearStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select NFL Statistics in Manage select box (Invalid Year)
		String nflstaticsinvalidYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLStaticsYear");
		String nflstaticsinvalidYearStatus = "";
		
		AppFunction.NFLStatistics(props.getProperty("txtbox_NFLStatics_Year"), props.getProperty("txtbox_NFLStatics_Season"), props.getProperty("txtbox_NFLStatics_Week"), props.getProperty("txtbox_NFLStatics_Team"), props.getProperty("txtbox_NFLStatics_PlayerName"), props.getProperty("txtbox_NFLStatics_PlayerId"), props.getProperty("btn_NFLStatics_Search"), nflstaticsinvalidYearData[2][1],nflstaticsinvalidYearData[3][1],nflstaticsinvalidYearData[4][1],nflstaticsinvalidYearData[5][1],nflstaticsinvalidYearData[6][1],nflstaticsinvalidYearData[7][1]);
				
		boolean errmsgnflstaticsinvalidYear = CommonFunctions.isTextPresent("Please enter Number for Year");
		System.out.println(errmsgnflstaticsinvalidYear);
			
		if(errmsgnflstaticsinvalidYear == true)
		{
			nflstaticsinvalidYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaticsYear", nflstaticsinvalidYearStatus, 1, 8);
		}
		else
		{
			nflstaticsinvalidYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaticsYear", nflstaticsinvalidYearStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflstaticsinvalidYearData[0][1], nflstaticsinvalidYearData[1][1], nflstaticsinvalidYearStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select NFL Statistics in Manage select box (Invalidmin Year)
		String nflstaticsinvalidminYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLStaminYear");
		String nflstaticsinvalidminYearStatus = "";
		
		AppFunction.NFLStatistics(props.getProperty("txtbox_NFLStatics_Year"), props.getProperty("txtbox_NFLStatics_Season"), props.getProperty("txtbox_NFLStatics_Week"), props.getProperty("txtbox_NFLStatics_Team"), props.getProperty("txtbox_NFLStatics_PlayerName"), props.getProperty("txtbox_NFLStatics_PlayerId"), props.getProperty("btn_NFLStatics_Search"), nflstaticsinvalidminYearData[2][1],nflstaticsinvalidminYearData[3][1],nflstaticsinvalidminYearData[4][1],nflstaticsinvalidminYearData[5][1],nflstaticsinvalidminYearData[6][1],nflstaticsinvalidminYearData[7][1]);
				
		boolean errmsgnflstaticsinvalidminYear = CommonFunctions.isTextPresent("Please enter 4 digits for Year");
		System.out.println(errmsgnflstaticsinvalidminYear);
			
		if(errmsgnflstaticsinvalidminYear == true)
		{
			nflstaticsinvalidminYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaminYear", nflstaticsinvalidminYearStatus, 1, 8);
		}
		else
		{
			nflstaticsinvalidminYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaminYear", nflstaticsinvalidminYearStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
		
		pw.printf(rowFormat, nflstaticsinvalidminYearData[0][1], nflstaticsinvalidminYearData[1][1], nflstaticsinvalidminYearStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select NFL Statistics in Manage select box (Valid Year)
		String nflstaticsvalidYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","validNFLStaticsYear");
		String nflstaticsvalidYearStatus = "";
		
		AppFunction.NFLStatistics(props.getProperty("txtbox_NFLStatics_Year"), props.getProperty("txtbox_NFLStatics_Season"), props.getProperty("txtbox_NFLStatics_Week"), props.getProperty("txtbox_NFLStatics_Team"), props.getProperty("txtbox_NFLStatics_PlayerName"), props.getProperty("txtbox_NFLStatics_PlayerId"), props.getProperty("btn_NFLStatics_Search"), nflstaticsvalidYearData[2][1],nflstaticsvalidYearData[3][1],nflstaticsvalidYearData[4][1],nflstaticsvalidYearData[5][1],nflstaticsvalidYearData[6][1],nflstaticsvalidYearData[7][1]);
				
		boolean errmsgnflstaticsvalidYear = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflstaticsvalidYear);
			
		if(errmsgnflstaticsvalidYear == true)
		{
			nflstaticsvalidYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validNFLStaticsYear", nflstaticsvalidYearStatus, 1, 8);
		}
		else
		{
			nflstaticsvalidYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validNFLStaticsYear", nflstaticsvalidYearStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflstaticsvalidYearData[0][1], nflstaticsvalidYearData[1][1], nflstaticsvalidYearStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Statistics in Manage select box (Invalid Season)
		String nflstaticsinvalidSeasonData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLStaticsSeason");
		String nflstaticsinvalidSeasonStatus = "";
		
		AppFunction.NFLStatistics(props.getProperty("txtbox_NFLStatics_Year"), props.getProperty("txtbox_NFLStatics_Season"), props.getProperty("txtbox_NFLStatics_Week"), props.getProperty("txtbox_NFLStatics_Team"), props.getProperty("txtbox_NFLStatics_PlayerName"), props.getProperty("txtbox_NFLStatics_PlayerId"), props.getProperty("btn_NFLStatics_Search"), nflstaticsinvalidSeasonData[2][1],nflstaticsinvalidSeasonData[3][1],nflstaticsinvalidSeasonData[4][1],nflstaticsinvalidSeasonData[5][1],nflstaticsinvalidSeasonData[6][1],nflstaticsinvalidSeasonData[7][1]);
				
		boolean errmsgnflstaticsinvalidSeason = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflstaticsinvalidSeason);
			
		if(errmsgnflstaticsinvalidSeason == true)
		{
			nflstaticsinvalidSeasonStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaticsSeason", nflstaticsinvalidSeasonStatus, 1, 8);
		}
		else
		{
			nflstaticsinvalidSeasonStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaticsSeason", nflstaticsinvalidSeasonStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflstaticsinvalidSeasonData[0][1], nflstaticsinvalidSeasonData[1][1], nflstaticsinvalidSeasonStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select NFL Statistics in Manage select box (Invalid Week)
		String nflstaticsinvalidWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLStaticsWeek");
		String nflstaticsinvalidWeekStatus = "";
		
		AppFunction.NFLStatistics(props.getProperty("txtbox_NFLStatics_Year"), props.getProperty("txtbox_NFLStatics_Season"), props.getProperty("txtbox_NFLStatics_Week"), props.getProperty("txtbox_NFLStatics_Team"), props.getProperty("txtbox_NFLStatics_PlayerName"), props.getProperty("txtbox_NFLStatics_PlayerId"), props.getProperty("btn_NFLStatics_Search"), nflstaticsinvalidWeekData[2][1],nflstaticsinvalidWeekData[3][1],nflstaticsinvalidWeekData[4][1],nflstaticsinvalidWeekData[5][1],nflstaticsinvalidWeekData[6][1],nflstaticsinvalidWeekData[7][1]);
				
		boolean errmsgnflstaticsinvalidWeek = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflstaticsinvalidWeek);
			
		if(errmsgnflstaticsinvalidWeek == true)
		{
			nflstaticsinvalidWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaticsWeek", nflstaticsinvalidWeekStatus, 1, 8);
		}
		else
		{
			nflstaticsinvalidWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaticsWeek", nflstaticsinvalidWeekStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflstaticsinvalidWeekData[0][1], nflstaticsinvalidWeekData[1][1], nflstaticsinvalidWeekStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Statistics in Manage select box (Exceeding WeekNumber)
		String nflstaticsexceedWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","exceedNFLStaticsWeek");
		String nflstaticsexceedWeekStatus = "";
		
		AppFunction.NFLStatistics(props.getProperty("txtbox_NFLStatics_Year"), props.getProperty("txtbox_NFLStatics_Season"), props.getProperty("txtbox_NFLStatics_Week"), props.getProperty("txtbox_NFLStatics_Team"), props.getProperty("txtbox_NFLStatics_PlayerName"), props.getProperty("txtbox_NFLStatics_PlayerId"), props.getProperty("btn_NFLStatics_Search"), nflstaticsexceedWeekData[2][1],nflstaticsexceedWeekData[3][1],nflstaticsexceedWeekData[4][1],nflstaticsexceedWeekData[5][1],nflstaticsexceedWeekData[6][1],nflstaticsexceedWeekData[7][1]);
				
		boolean errmsgnflstaticsexceedWeek = CommonFunctions.isTextPresent("Please enter value from 0 to 52 for Week");
		System.out.println(errmsgnflstaticsexceedWeek);
			
		if(errmsgnflstaticsexceedWeek == true)
		{
			nflstaticsexceedWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "exceedNFLStaticsWeek", nflstaticsexceedWeekStatus, 1, 8);
		}
		else
		{
			nflstaticsexceedWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "exceedNFLStaticsWeek", nflstaticsexceedWeekStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflstaticsexceedWeekData[0][1], nflstaticsexceedWeekData[1][1], nflstaticsexceedWeekStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Statistics in Manage select box (Invalid Chars Week)
		String nflstaticsinvalidcharsWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLStacharsWeek");
		String nflstaticsinvalidcharsWeekStatus = "";
		
		AppFunction.NFLStatistics(props.getProperty("txtbox_NFLStatics_Year"), props.getProperty("txtbox_NFLStatics_Season"), props.getProperty("txtbox_NFLStatics_Week"), props.getProperty("txtbox_NFLStatics_Team"), props.getProperty("txtbox_NFLStatics_PlayerName"), props.getProperty("txtbox_NFLStatics_PlayerId"), props.getProperty("btn_NFLStatics_Search"), nflstaticsinvalidcharsWeekData[2][1],nflstaticsinvalidcharsWeekData[3][1],nflstaticsinvalidcharsWeekData[4][1],nflstaticsinvalidcharsWeekData[5][1],nflstaticsinvalidcharsWeekData[6][1],nflstaticsinvalidcharsWeekData[7][1]);
				
		boolean errmsgnflstaticsinvalidcharsWeek = CommonFunctions.isTextPresent("Please enter Number for Week");
		System.out.println(errmsgnflstaticsinvalidcharsWeek);
			
		if(errmsgnflstaticsinvalidcharsWeek == true)
		{
			nflstaticsinvalidcharsWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStacharsWeek", nflstaticsinvalidcharsWeekStatus, 1, 8);
		}
		else
		{
			nflstaticsinvalidcharsWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStacharsWeek", nflstaticsinvalidcharsWeekStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflstaticsinvalidcharsWeekData[0][1], nflstaticsinvalidcharsWeekData[1][1], nflstaticsinvalidcharsWeekStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Statistics in Manage select box (Invalid Team)
		String nflstaticsinvalidTeamData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLStaticsTeam");
		String nflstaticsinvalidTeamStatus = "";
		
		AppFunction.NFLStatistics(props.getProperty("txtbox_NFLStatics_Year"), props.getProperty("txtbox_NFLStatics_Season"), props.getProperty("txtbox_NFLStatics_Week"), props.getProperty("txtbox_NFLStatics_Team"), props.getProperty("txtbox_NFLStatics_PlayerName"), props.getProperty("txtbox_NFLStatics_PlayerId"), props.getProperty("btn_NFLStatics_Search"), nflstaticsinvalidTeamData[2][1],nflstaticsinvalidTeamData[3][1],nflstaticsinvalidTeamData[4][1],nflstaticsinvalidTeamData[5][1],nflstaticsinvalidTeamData[6][1],nflstaticsinvalidTeamData[7][1]);
				
		boolean errmsgnflstaticsinvalidTeam = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflstaticsinvalidTeam);
			
		if(errmsgnflstaticsinvalidTeam == true)
		{
			nflstaticsinvalidTeamStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaticsTeam", nflstaticsinvalidTeamStatus, 1, 8);
		}
		else
		{
			nflstaticsinvalidTeamStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaticsTeam", nflstaticsinvalidTeamStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflstaticsinvalidTeamData[0][1], nflstaticsinvalidTeamData[1][1], nflstaticsinvalidTeamStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Statistics in Manage select box (Invalid PlayerName)
		String nflstaticsinvalidPlayerNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLStaPlayerName");
		String nflstaticsinvalidPlayerNameStatus = "";
		
		AppFunction.NFLStatistics(props.getProperty("txtbox_NFLStatics_Year"), props.getProperty("txtbox_NFLStatics_Season"), props.getProperty("txtbox_NFLStatics_Week"), props.getProperty("txtbox_NFLStatics_Team"), props.getProperty("txtbox_NFLStatics_PlayerName"), props.getProperty("txtbox_NFLStatics_PlayerId"), props.getProperty("btn_NFLStatics_Search"), nflstaticsinvalidPlayerNameData[2][1],nflstaticsinvalidPlayerNameData[3][1],nflstaticsinvalidPlayerNameData[4][1],nflstaticsinvalidPlayerNameData[5][1],nflstaticsinvalidPlayerNameData[6][1],nflstaticsinvalidPlayerNameData[7][1]);
				
		boolean errmsgnflstaticsinvalidPlayerName = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflstaticsinvalidPlayerName);
			
		if(errmsgnflstaticsinvalidPlayerName == true)
		{
			nflstaticsinvalidPlayerNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaPlayerName", nflstaticsinvalidPlayerNameStatus, 1, 8);
		}
		else
		{
			nflstaticsinvalidPlayerNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaPlayerName", nflstaticsinvalidPlayerNameStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflstaticsinvalidPlayerNameData[0][1], nflstaticsinvalidPlayerNameData[1][1], nflstaticsinvalidPlayerNameStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Statistics in Manage select box (Invalid PlayerId)
		String nflstaticsinvalidPlayerIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLStaPlayerId");
		String nflstaticsinvalidPlayerIdStatus = "";
		
		AppFunction.NFLStatistics(props.getProperty("txtbox_NFLStatics_Year"), props.getProperty("txtbox_NFLStatics_Season"), props.getProperty("txtbox_NFLStatics_Week"), props.getProperty("txtbox_NFLStatics_Team"), props.getProperty("txtbox_NFLStatics_PlayerName"), props.getProperty("txtbox_NFLStatics_PlayerId"), props.getProperty("btn_NFLStatics_Search"), nflstaticsinvalidPlayerIdData[2][1],nflstaticsinvalidPlayerIdData[3][1],nflstaticsinvalidPlayerIdData[4][1],nflstaticsinvalidPlayerIdData[5][1],nflstaticsinvalidPlayerIdData[6][1],nflstaticsinvalidPlayerIdData[7][1]);
				
		boolean errmsgnflstaticsinvalidPlayerId = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflstaticsinvalidPlayerId);
			
		if(errmsgnflstaticsinvalidPlayerId == true)
		{
			nflstaticsinvalidPlayerIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaPlayerId", nflstaticsinvalidPlayerIdStatus, 1, 8);
		}
		else
		{
			nflstaticsinvalidPlayerIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLStaPlayerId", nflstaticsinvalidPlayerIdStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflstaticsinvalidPlayerIdData[0][1], nflstaticsinvalidPlayerIdData[1][1], nflstaticsinvalidPlayerIdStatus, "");
		
		Thread.sleep(5000);
		
		
		//Select NFL Roster in Manage select box
		Select selectNFLRoster = new Select(driver.findElement(By.xpath(props.getProperty("slectbox_NFLStatics_Manage"))));
		selectNFLRoster.selectByVisibleText("NFL Roster");
		Thread.sleep(3000);
		
		//*******   Select NFL Roster in Manage select box (Empty Year)
		String nflRosteremptyYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","emptyNFLRosterYear");
		String nflRosteremptyYearStatus = "";
		
		AppFunction.NFLRoster(props.getProperty("txtbox_NFLRoster_Year"), props.getProperty("txtbox_NFLRoster_Season"), props.getProperty("txtbox_NFLRoster_Week"), props.getProperty("txtbox_NFLRoster_Team"), props.getProperty("txtbox_NFLRoster_PlayerFName"), props.getProperty("btn_NFLRoster_Search"), nflRosteremptyYearData[2][1],nflRosteremptyYearData[3][1],nflRosteremptyYearData[4][1],nflRosteremptyYearData[5][1],nflRosteremptyYearData[6][1]);
				
		boolean errmsgnflRosteremptyYear = CommonFunctions.isTextPresent("Please enter Search Criteria.");
		System.out.println(errmsgnflRosteremptyYear);
			
		if(errmsgnflRosteremptyYear == true)
		{
			nflRosteremptyYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyNFLRosterYear", nflRosteremptyYearStatus, 1, 7);
		}
		else
		{
			nflRosteremptyYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyNFLRosterYear", nflRosteremptyYearStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflRosteremptyYearData[0][1], nflRosteremptyYearData[1][1], nflRosteremptyYearStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Roster in Manage select box (Invalid Year)
		String nflRosterinvalidYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLRosterYear");
		String nflRosterinvalidYearStatus = "";
		
		AppFunction.NFLRoster(props.getProperty("txtbox_NFLRoster_Year"), props.getProperty("txtbox_NFLRoster_Season"), props.getProperty("txtbox_NFLRoster_Week"), props.getProperty("txtbox_NFLRoster_Team"), props.getProperty("txtbox_NFLRoster_PlayerFName"), props.getProperty("btn_NFLRoster_Search"), nflRosterinvalidYearData[2][1],nflRosterinvalidYearData[3][1],nflRosterinvalidYearData[4][1],nflRosterinvalidYearData[5][1],nflRosterinvalidYearData[6][1]);
				
		boolean errmsgnflRosterinvalidYear = CommonFunctions.isTextPresent("Please enter Number for Year");
		System.out.println(errmsgnflRosterinvalidYear);
			
		if(errmsgnflRosterinvalidYear == true)
		{
			nflRosterinvalidYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterYear", nflRosterinvalidYearStatus, 1, 7);
		}
		else
		{
			nflRosterinvalidYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterYear", nflRosterinvalidYearStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflRosterinvalidYearData[0][1], nflRosterinvalidYearData[1][1], nflRosterinvalidYearStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Roster in Manage select box (Invalidmin Year)
		String nflRosterinvalidminYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLRosterminYear");
		String nflRosterinvalidminYearStatus = "";
		
		AppFunction.NFLRoster(props.getProperty("txtbox_NFLRoster_Year"), props.getProperty("txtbox_NFLRoster_Season"), props.getProperty("txtbox_NFLRoster_Week"), props.getProperty("txtbox_NFLRoster_Team"), props.getProperty("txtbox_NFLRoster_PlayerFName"), props.getProperty("btn_NFLRoster_Search"), nflRosterinvalidminYearData[2][1],nflRosterinvalidminYearData[3][1],nflRosterinvalidminYearData[4][1],nflRosterinvalidminYearData[5][1],nflRosterinvalidminYearData[6][1]);
				
		boolean errmsgnflRosterinvalidminYear = CommonFunctions.isTextPresent("Please enter 4 digits for Year");
		System.out.println(errmsgnflRosterinvalidminYear);
			
		if(errmsgnflRosterinvalidminYear == true)
		{
			nflRosterinvalidminYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterminYear", nflRosterinvalidminYearStatus, 1, 7);
		}
		else
		{
			nflRosterinvalidminYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterminYear", nflRosterinvalidminYearStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflRosterinvalidminYearData[0][1], nflRosterinvalidminYearData[1][1], nflRosterinvalidminYearStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Roster in Manage select box (Valid Year)
		String nflRostervalidYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","validNFLRosterYear");
		String nflRostervalidYearStatus = "";
		
		AppFunction.NFLRoster(props.getProperty("txtbox_NFLRoster_Year"), props.getProperty("txtbox_NFLRoster_Season"), props.getProperty("txtbox_NFLRoster_Week"), props.getProperty("txtbox_NFLRoster_Team"), props.getProperty("txtbox_NFLRoster_PlayerFName"), props.getProperty("btn_NFLRoster_Search"), nflRostervalidYearData[2][1],nflRostervalidYearData[3][1],nflRostervalidYearData[4][1],nflRostervalidYearData[5][1],nflRostervalidYearData[6][1]);
				
		boolean errmsgnflRostervalidYear = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflRostervalidYear);
			
		if(errmsgnflRostervalidYear == true)
		{
			nflRostervalidYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validNFLRosterYear", nflRostervalidYearStatus, 1, 7);
		}
		else
		{
			nflRostervalidYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validNFLRosterYear", nflRostervalidYearStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflRostervalidYearData[0][1], nflRostervalidYearData[1][1], nflRostervalidYearStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Roster in Manage select box (Invalid Season)
		String nflRosterinvalidSeasonData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLRosterSeason");
		String nflRosterinvalidSeasonStatus = "";
		
		AppFunction.NFLRoster(props.getProperty("txtbox_NFLRoster_Year"), props.getProperty("txtbox_NFLRoster_Season"), props.getProperty("txtbox_NFLRoster_Week"), props.getProperty("txtbox_NFLRoster_Team"), props.getProperty("txtbox_NFLRoster_PlayerFName"), props.getProperty("btn_NFLRoster_Search"), nflRosterinvalidSeasonData[2][1],nflRosterinvalidSeasonData[3][1],nflRosterinvalidSeasonData[4][1],nflRosterinvalidSeasonData[5][1],nflRosterinvalidSeasonData[6][1]);
				
		boolean errmsgnflRosterinvalidSeason = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflRosterinvalidSeason);
			
		if(errmsgnflRosterinvalidSeason == true)
		{
			nflRosterinvalidSeasonStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterSeason", nflRosterinvalidSeasonStatus, 1, 7);
		}
		else
		{
			nflRosterinvalidSeasonStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterSeason", nflRosterinvalidSeasonStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflRosterinvalidSeasonData[0][1], nflRosterinvalidSeasonData[1][1], nflRosterinvalidSeasonStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL Roster in Manage select box (Invalid Week)
		String nflRosterinvalidWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLRosterWeek");
		String nflRosterinvalidWeekStatus = "";
				
		AppFunction.NFLRoster(props.getProperty("txtbox_NFLRoster_Year"), props.getProperty("txtbox_NFLRoster_Season"), props.getProperty("txtbox_NFLRoster_Week"), props.getProperty("txtbox_NFLRoster_Team"), props.getProperty("txtbox_NFLRoster_PlayerFName"), props.getProperty("btn_NFLRoster_Search"), nflRosterinvalidWeekData[2][1],nflRosterinvalidWeekData[3][1],nflRosterinvalidWeekData[4][1],nflRosterinvalidWeekData[5][1],nflRosterinvalidWeekData[6][1]);
						
		boolean errmsgnflRosterinvalidWeek = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflRosterinvalidWeek);
					
		if(errmsgnflRosterinvalidWeek == true)
		{
			nflRosterinvalidWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterWeek", nflRosterinvalidWeekStatus, 1, 7);
		}
		else
		{
			nflRosterinvalidWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterWeek", nflRosterinvalidWeekStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflRosterinvalidWeekData[0][1], nflRosterinvalidWeekData[1][1], nflRosterinvalidWeekStatus, "");
				
		Thread.sleep(5000);
				
				
		//*******   Select NFL Roster in Manage select box (Exceeding WeekNumber)
		String nflRosterexceedWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","exceedNFLRosterWeek");
		String nflRosterexceedWeekStatus = "";
				
		AppFunction.NFLRoster(props.getProperty("txtbox_NFLRoster_Year"), props.getProperty("txtbox_NFLRoster_Season"), props.getProperty("txtbox_NFLRoster_Week"), props.getProperty("txtbox_NFLRoster_Team"), props.getProperty("txtbox_NFLRoster_PlayerFName"), props.getProperty("btn_NFLRoster_Search"), nflRosterexceedWeekData[2][1],nflRosterexceedWeekData[3][1],nflRosterexceedWeekData[4][1],nflRosterexceedWeekData[5][1],nflRosterexceedWeekData[6][1]);
						
		boolean errmsgnflRosterexceedWeek = CommonFunctions.isTextPresent("Please enter value from 0 to 52 for Week");
		System.out.println(errmsgnflRosterexceedWeek);
					
		if(errmsgnflRosterexceedWeek == true)
		{
			nflRosterexceedWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "exceedNFLRosterWeek", nflRosterexceedWeekStatus, 1, 7);
		}
		else
		{
			nflRosterexceedWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "exceedNFLRosterWeek", nflRosterexceedWeekStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflRosterexceedWeekData[0][1], nflRosterexceedWeekData[1][1], nflRosterexceedWeekStatus, "");
				
		Thread.sleep(5000);
				
		//*******   Select NFL Roster in Manage select box (Invalid Chars Week)
		String nflRosterinvalidcharsWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLRostercharsWeek");
		String nflRosterinvalidcharsWeekStatus = "";
				
		AppFunction.NFLRoster(props.getProperty("txtbox_NFLRoster_Year"), props.getProperty("txtbox_NFLRoster_Season"), props.getProperty("txtbox_NFLRoster_Week"), props.getProperty("txtbox_NFLRoster_Team"), props.getProperty("txtbox_NFLRoster_PlayerFName"), props.getProperty("btn_NFLRoster_Search"), nflRosterinvalidcharsWeekData[2][1],nflRosterinvalidcharsWeekData[3][1],nflRosterinvalidcharsWeekData[4][1],nflRosterinvalidcharsWeekData[5][1],nflRosterinvalidcharsWeekData[6][1]);
						
		boolean errmsgnflRosterinvalidcharsWeek = CommonFunctions.isTextPresent("Please enter Number for Week");
		System.out.println(errmsgnflRosterinvalidcharsWeek);
					
		if(errmsgnflRosterinvalidcharsWeek == true)
		{
			nflRosterinvalidcharsWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRostercharsWeek", nflRosterinvalidcharsWeekStatus, 1, 7);
		}
		else
		{
			nflRosterinvalidcharsWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRostercharsWeek", nflRosterinvalidcharsWeekStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nflRosterinvalidcharsWeekData[0][1], nflRosterinvalidcharsWeekData[1][1], nflRosterinvalidcharsWeekStatus, "");
				
		Thread.sleep(5000);
				
		//*******   Select NFL Roster in Manage select box (Invalid Team)
		String nflRosterinvalidTeamData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLRosterTeam");
		String nflRosterinvalidTeamStatus = "";
				
		AppFunction.NFLRoster(props.getProperty("txtbox_NFLRoster_Year"), props.getProperty("txtbox_NFLRoster_Season"), props.getProperty("txtbox_NFLRoster_Week"), props.getProperty("txtbox_NFLRoster_Team"), props.getProperty("txtbox_NFLRoster_PlayerFName"), props.getProperty("btn_NFLRoster_Search"), nflRosterinvalidTeamData[2][1],nflRosterinvalidTeamData[3][1],nflRosterinvalidTeamData[4][1],nflRosterinvalidTeamData[5][1],nflRosterinvalidTeamData[6][1]);
						
		boolean errmsgnflRosterinvalidTeam = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflRosterinvalidTeam);
					
		if(errmsgnflRosterinvalidTeam == true)
		{
			nflRosterinvalidTeamStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterTeam", nflRosterinvalidTeamStatus, 1, 7);
		}
		else
		{
			nflRosterinvalidTeamStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterTeam", nflRosterinvalidTeamStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
						
		pw.printf(rowFormat, nflRosterinvalidTeamData[0][1], nflRosterinvalidTeamData[1][1], nflRosterinvalidTeamStatus, "");
				
		Thread.sleep(5000);
				
		//*******   Select NFL Statistics in Manage select box (Invalid PlayerFName)
		String nflRosterinvalidPlayerFNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLRosterPlayerFName");
		String nflRosterinvalidPlayerFNameStatus = "";
				
		AppFunction.NFLRoster(props.getProperty("txtbox_NFLRoster_Year"), props.getProperty("txtbox_NFLRoster_Season"), props.getProperty("txtbox_NFLRoster_Week"), props.getProperty("txtbox_NFLRoster_Team"), props.getProperty("txtbox_NFLRoster_PlayerFName"), props.getProperty("btn_NFLRoster_Search"), nflRosterinvalidPlayerFNameData[2][1],nflRosterinvalidPlayerFNameData[3][1],nflRosterinvalidPlayerFNameData[4][1],nflRosterinvalidPlayerFNameData[5][1],nflRosterinvalidPlayerFNameData[6][1]);
						
		boolean errmsgnflRosterinvalidPlayerFName = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflRosterinvalidPlayerFName);
					
		if(errmsgnflRosterinvalidPlayerFName == true)
		{
			nflRosterinvalidPlayerFNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterPlayerFName", nflRosterinvalidPlayerFNameStatus, 1, 7);
		}
		else
		{
			nflRosterinvalidPlayerFNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLRosterPlayerFName", nflRosterinvalidPlayerFNameStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
					
		pw.printf(rowFormat, nflRosterinvalidPlayerFNameData[0][1], nflRosterinvalidPlayerFNameData[1][1], nflRosterinvalidPlayerFNameStatus, "");
				
		Thread.sleep(5000);
				
		
		//Select NFL PBP in Manage select box
		Select selectNFLPbp = new Select(driver.findElement(By.xpath(props.getProperty("slectbox_NFLRoster_Manage"))));
		selectNFLPbp.selectByVisibleText("NFL Pbp");
		Thread.sleep(3000);
				
		//*******   Select NFL PBP in Manage select box (Empty Year)
		String nflPbpemptyYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","emptyNFLPbpYear");
		String nflPbpemptyYearStatus = "";
		
		AppFunction.NFLPbp(props.getProperty("txtbox_NFLPbp_Year"), props.getProperty("txtbox_NFLPbp_Season"), props.getProperty("txtbox_NFLPbp_Week"), props.getProperty("txtbox_NFLPbp_QuarterNum"), props.getProperty("txtbox_NFLPbp_PlayType"), props.getProperty("btn_NFLPbp_Search"), nflPbpemptyYearData[2][1],nflPbpemptyYearData[3][1],nflPbpemptyYearData[4][1],nflPbpemptyYearData[5][1],nflPbpemptyYearData[6][1]);
				
		boolean errmsgnflPbpemptyYear = CommonFunctions.isTextPresent("Please enter Search Criteria.");
		System.out.println(errmsgnflPbpemptyYear);
			
		if(errmsgnflPbpemptyYear == true)
		{
			nflPbpemptyYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyNFLPbpYear", nflPbpemptyYearStatus, 1, 7);
		}
		else
		{
			nflPbpemptyYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "emptyNFLPbpYear", nflPbpemptyYearStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflPbpemptyYearData[0][1], nflPbpemptyYearData[1][1], nflPbpemptyYearStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select NFL PBP in Manage select box (Invalid Year)
		String nflPbpinvalidYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLPbpYear");
		String nflPbpinvalidYearStatus = "";
		
		AppFunction.NFLPbp(props.getProperty("txtbox_NFLPbp_Year"), props.getProperty("txtbox_NFLPbp_Season"), props.getProperty("txtbox_NFLPbp_Week"), props.getProperty("txtbox_NFLPbp_QuarterNum"), props.getProperty("txtbox_NFLPbp_PlayType"), props.getProperty("btn_NFLPbp_Search"), nflPbpinvalidYearData[2][1],nflPbpinvalidYearData[3][1],nflPbpinvalidYearData[4][1],nflPbpinvalidYearData[5][1],nflPbpinvalidYearData[6][1]);
				
		boolean errmsgnflPbpinvalidYear = CommonFunctions.isTextPresent("Please enter Number for Year");
		System.out.println(errmsgnflPbpinvalidYear);
			
		if(errmsgnflPbpinvalidYear == true)
		{
			nflPbpinvalidYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLPbpYear", nflPbpinvalidYearStatus, 1, 7);
		}
		else
		{
			nflPbpinvalidYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLPbpYear", nflPbpinvalidYearStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflPbpinvalidYearData[0][1], nflPbpinvalidYearData[1][1], nflPbpinvalidYearStatus, "");
		
		Thread.sleep(5000);
		
		
		//*******   Select NFL PBP in Manage select box (Invalidmin Year)
		String nflPbpinvalidminYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLPbpminYear");
		String nflPbpinvalidminYearStatus = "";
		
		AppFunction.NFLPbp(props.getProperty("txtbox_NFLPbp_Year"), props.getProperty("txtbox_NFLPbp_Season"), props.getProperty("txtbox_NFLPbp_Week"), props.getProperty("txtbox_NFLPbp_QuarterNum"), props.getProperty("txtbox_NFLPbp_PlayType"), props.getProperty("btn_NFLPbp_Search"), nflPbpinvalidminYearData[2][1],nflPbpinvalidminYearData[3][1],nflPbpinvalidminYearData[4][1],nflPbpinvalidminYearData[5][1],nflPbpinvalidminYearData[6][1]);
				
		boolean errmsgnflPbpinvalidminYear = CommonFunctions.isTextPresent("Please enter 4 digits for Year");
		System.out.println(errmsgnflPbpinvalidminYear);
			
		if(errmsgnflPbpinvalidminYear == true)
		{
			nflPbpinvalidminYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLPbpminYear", nflPbpinvalidminYearStatus, 1, 7);
		}
		else
		{
			nflPbpinvalidminYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLPbpminYear", nflPbpinvalidminYearStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflPbpinvalidminYearData[0][1], nflPbpinvalidminYearData[1][1], nflPbpinvalidminYearStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL PBP in Manage select box (Valid Year)
		String nflPbpvalidYearData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","validNFLPbpYear");
		String nflPbpvalidYearStatus = "";
		
		AppFunction.NFLPbp(props.getProperty("txtbox_NFLPbp_Year"), props.getProperty("txtbox_NFLPbp_Season"), props.getProperty("txtbox_NFLPbp_Week"), props.getProperty("txtbox_NFLPbp_QuarterNum"), props.getProperty("txtbox_NFLPbp_PlayType"), props.getProperty("btn_NFLPbp_Search"), nflPbpvalidYearData[2][1],nflPbpvalidYearData[3][1],nflPbpvalidYearData[4][1],nflPbpvalidYearData[5][1],nflPbpvalidYearData[6][1]);
				
		boolean errmsgnflPbpvalidYear = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflPbpvalidYear);
			
		if(errmsgnflPbpvalidYear == true)
		{
			nflPbpvalidYearStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validNFLPbpYear", nflPbpvalidYearStatus, 1, 7);
		}
		else
		{
			nflPbpvalidYearStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validNFLPbpYear", nflPbpvalidYearStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflPbpvalidYearData[0][1], nflPbpvalidYearData[1][1], nflPbpvalidYearStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL PBP in Manage select box (Invalid Season)
		String nflPbpinvalidSeasonData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLPbpSeason");
		String nflPbpinvalidSeasonStatus = "";
		
		AppFunction.NFLPbp(props.getProperty("txtbox_NFLPbp_Year"), props.getProperty("txtbox_NFLPbp_Season"), props.getProperty("txtbox_NFLPbp_Week"), props.getProperty("txtbox_NFLPbp_QuarterNum"), props.getProperty("txtbox_NFLPbp_PlayType"), props.getProperty("btn_NFLPbp_Search"), nflPbpinvalidSeasonData[2][1],nflPbpinvalidSeasonData[3][1],nflPbpinvalidSeasonData[4][1],nflPbpinvalidSeasonData[5][1],nflPbpinvalidSeasonData[6][1]);
				
		boolean errmsgnflPbpinvalidSeason = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflPbpinvalidSeason);
			
		if(errmsgnflPbpinvalidSeason == true)
		{
			nflPbpinvalidSeasonStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLPbpSeason", nflPbpinvalidSeasonStatus, 1, 7);
		}
		else
		{
			nflPbpinvalidSeasonStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLPbpSeason", nflPbpinvalidSeasonStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
		
		
		pw.printf(rowFormat, nflPbpinvalidSeasonData[0][1], nflPbpinvalidSeasonData[1][1], nflPbpinvalidSeasonStatus, "");
		
		Thread.sleep(5000);
		
		//*******   Select NFL PBP in Manage select box (Invalid Week)
		String nflPbpinvalidWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLPbpWeek");
		String nflPbpinvalidWeekStatus = "";
				
		AppFunction.NFLPbp(props.getProperty("txtbox_NFLPbp_Year"), props.getProperty("txtbox_NFLPbp_Season"), props.getProperty("txtbox_NFLPbp_Week"), props.getProperty("txtbox_NFLPbp_QuarterNum"), props.getProperty("txtbox_NFLPbp_PlayType"), props.getProperty("btn_NFLPbp_Search"), nflPbpinvalidWeekData[2][1],nflPbpinvalidWeekData[3][1],nflPbpinvalidWeekData[4][1],nflPbpinvalidWeekData[5][1],nflPbpinvalidWeekData[6][1]);
						
		boolean errmsgnflPbpinvalidWeek = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflPbpinvalidWeek);
					
		if(errmsgnflPbpinvalidWeek == true)
		{
			nflPbpinvalidWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLPbpWeek", nflPbpinvalidWeekStatus, 1, 7);
		}
		else
		{
			nflPbpinvalidWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLPbpWeek", nflPbpinvalidWeekStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflPbpinvalidWeekData[0][1], nflPbpinvalidWeekData[1][1], nflPbpinvalidWeekStatus, "");
				
		Thread.sleep(5000);
		
		
		//*******   Select NFL PBP in Manage select box (Exceeding WeekNumber)
		String nflPbpexceedWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","exceedNFLPbpWeek");
		String nflPbpexceedWeekStatus = "";
				
		AppFunction.NFLPbp(props.getProperty("txtbox_NFLPbp_Year"), props.getProperty("txtbox_NFLPbp_Season"), props.getProperty("txtbox_NFLPbp_Week"), props.getProperty("txtbox_NFLPbp_QuarterNum"), props.getProperty("txtbox_NFLPbp_PlayType"), props.getProperty("btn_NFLPbp_Search"), nflPbpexceedWeekData[2][1],nflPbpexceedWeekData[3][1],nflPbpexceedWeekData[4][1],nflPbpexceedWeekData[5][1],nflPbpexceedWeekData[6][1]);
						
		boolean errmsgnflPbpexceedWeek = CommonFunctions.isTextPresent("Please enter value from 0 to 52 for Week");
		System.out.println(errmsgnflPbpexceedWeek);
					
		if(errmsgnflPbpexceedWeek == true)
		{
			nflPbpexceedWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "exceedNFLPbpWeek", nflPbpexceedWeekStatus, 1, 7);
		}
		else
		{
			nflPbpexceedWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "exceedNFLPbpWeek", nflPbpexceedWeekStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
				
				
		pw.printf(rowFormat, nflPbpexceedWeekData[0][1], nflPbpexceedWeekData[1][1], nflPbpexceedWeekStatus, "");
				
		Thread.sleep(5000);
			
		
		//*******   Select NFL PBP in Manage select box (Invalid Chars Week)
		String nflPbpinvalidcharsWeekData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidNFLPbpcharsWeek");
		String nflPbpinvalidcharsWeekStatus = "";
				
		AppFunction.NFLPbp(props.getProperty("txtbox_NFLPbp_Year"), props.getProperty("txtbox_NFLPbp_Season"), props.getProperty("txtbox_NFLPbp_Week"), props.getProperty("txtbox_NFLPbp_QuarterNum"), props.getProperty("txtbox_NFLPbp_PlayType"), props.getProperty("btn_NFLPbp_Search"), nflPbpinvalidcharsWeekData[2][1],nflPbpinvalidcharsWeekData[3][1],nflPbpinvalidcharsWeekData[4][1],nflPbpinvalidcharsWeekData[5][1],nflPbpinvalidcharsWeekData[6][1]);
						
		boolean errmsgnflPbpinvalidcharsWeek = CommonFunctions.isTextPresent("Please enter Number for Week");
		System.out.println(errmsgnflPbpinvalidcharsWeek);
					
		if(errmsgnflPbpinvalidcharsWeek == true)
		{
			nflPbpinvalidcharsWeekStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLPbpcharsWeek", nflPbpinvalidcharsWeekStatus, 1, 7);
		}
		else
		{
			nflPbpinvalidcharsWeekStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidNFLPbpcharsWeek", nflPbpinvalidcharsWeekStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nflPbpinvalidcharsWeekData[0][1], nflPbpinvalidcharsWeekData[1][1], nflPbpinvalidcharsWeekStatus, "");
				
		Thread.sleep(5000);
			
		
		//*******   Select NFL PBP in Manage select box (Invalid QuarterNumber)
		String nflPbpinvalidQuarterNumData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidQuarterNumber");
		String nflPbpinvalidQuarterNumStatus = "";
				
		AppFunction.NFLPbp(props.getProperty("txtbox_NFLPbp_Year"), props.getProperty("txtbox_NFLPbp_Season"), props.getProperty("txtbox_NFLPbp_Week"), props.getProperty("txtbox_NFLPbp_QuarterNum"), props.getProperty("txtbox_NFLPbp_PlayType"), props.getProperty("btn_NFLPbp_Search"), nflPbpinvalidQuarterNumData[2][1],nflPbpinvalidQuarterNumData[3][1],nflPbpinvalidQuarterNumData[4][1],nflPbpinvalidQuarterNumData[5][1],nflPbpinvalidQuarterNumData[6][1]);
						
		boolean errmsgnflPbpinvalidQuarterNum = CommonFunctions.isTextPresent("Please enter Number for Quarter Number");
		System.out.println(errmsgnflPbpinvalidQuarterNum);
					
		if(errmsgnflPbpinvalidQuarterNum == true)
		{
			nflPbpinvalidQuarterNumStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidQuarterNumber", nflPbpinvalidQuarterNumStatus, 1, 7);
		}
		else
		{
			nflPbpinvalidQuarterNumStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidQuarterNumber", nflPbpinvalidQuarterNumStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nflPbpinvalidQuarterNumData[0][1], nflPbpinvalidQuarterNumData[1][1], nflPbpinvalidQuarterNumStatus, "");
				
		Thread.sleep(5000);
		
		
		//*******   Select NFL PBP in Manage select box (Valid QuarterNumber)
		String nflPbpvalidQuarterNumData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","validQuarterNumber");
		String nflPbpvalidQuarterNumStatus = "";
				
		AppFunction.NFLPbp(props.getProperty("txtbox_NFLPbp_Year"), props.getProperty("txtbox_NFLPbp_Season"), props.getProperty("txtbox_NFLPbp_Week"), props.getProperty("txtbox_NFLPbp_QuarterNum"), props.getProperty("txtbox_NFLPbp_PlayType"), props.getProperty("btn_NFLPbp_Search"), nflPbpvalidQuarterNumData[2][1],nflPbpvalidQuarterNumData[3][1],nflPbpvalidQuarterNumData[4][1],nflPbpvalidQuarterNumData[5][1],nflPbpvalidQuarterNumData[6][1]);
						
		boolean errmsgnflPbpvalidQuarterNum = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflPbpvalidQuarterNum);
					
		if(errmsgnflPbpvalidQuarterNum == true)
		{
			nflPbpvalidQuarterNumStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validQuarterNumber", nflPbpvalidQuarterNumStatus, 1, 7);
		}
		else
		{
			nflPbpvalidQuarterNumStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "validQuarterNumber", nflPbpvalidQuarterNumStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nflPbpvalidQuarterNumData[0][1], nflPbpvalidQuarterNumData[1][1], nflPbpvalidQuarterNumStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NFL PBP in Manage select box (Invalid PlayType)
		String nflPbpinvalidPlayTypeData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","invalidPlayType");
		String nflPbpinvalidPlayTypeStatus = "";
				
		AppFunction.NFLPbp(props.getProperty("txtbox_NFLPbp_Year"), props.getProperty("txtbox_NFLPbp_Season"), props.getProperty("txtbox_NFLPbp_Week"), props.getProperty("txtbox_NFLPbp_QuarterNum"), props.getProperty("txtbox_NFLPbp_PlayType"), props.getProperty("btn_NFLPbp_Search"), nflPbpinvalidPlayTypeData[2][1],nflPbpinvalidPlayTypeData[3][1],nflPbpinvalidPlayTypeData[4][1],nflPbpinvalidPlayTypeData[5][1],nflPbpinvalidPlayTypeData[6][1]);
						
		boolean errmsgnflPbpinvalidPlayType = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnflPbpinvalidPlayType);
					
		if(errmsgnflPbpinvalidPlayType == true)
		{
			nflPbpinvalidPlayTypeStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidPlayType", nflPbpinvalidPlayTypeStatus, 1, 7);
		}
		else
		{
			nflPbpinvalidPlayTypeStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "invalidPlayType", nflPbpinvalidPlayTypeStatus, 1, 7);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nflPbpinvalidPlayTypeData[0][1], nflPbpinvalidPlayTypeData[1][1], nflPbpinvalidPlayTypeStatus, "");
				
		Thread.sleep(5000);
		
		//Select NBA Schedules in Manage select box
		Select selectNBASchedls = new Select(driver.findElement(By.xpath(props.getProperty("slectbox_NFLPbp_Manage"))));
		selectNBASchedls.selectByVisibleText("NBA Schedules");
		Thread.sleep(3000);
		
		//*******   Select NBA Schedules in Manage select box (Empty SeasonId)
		String nbaSchedlSeasonIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBASchedlsEmptySeasonId");
		String nbaSchedlSeasonIdStatus = "";
				
		AppFunction.NBASchedules(props.getProperty("txtbox_NBASchdules_SeasonId"), props.getProperty("txtbox_NBASchdules_Season"), props.getProperty("txtbox_NBASchdules_PlayDay"), props.getProperty("btn_NBASchdules_Search"), nbaSchedlSeasonIdData[2][1],nbaSchedlSeasonIdData[3][1],nbaSchedlSeasonIdData[4][1]);
						
		boolean errmsgnbaSchedlSeasonId = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbaSchedlSeasonId);
					
		if(errmsgnbaSchedlSeasonId == true)
		{
			nbaSchedlSeasonIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBASchedlsEmptySeasonId", nbaSchedlSeasonIdStatus, 1, 5);
		}
		else
		{
			nbaSchedlSeasonIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBASchedlsEmptySeasonId", nbaSchedlSeasonIdStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbaSchedlSeasonIdData[0][1], nbaSchedlSeasonIdData[1][1], nbaSchedlSeasonIdStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Schedules in Manage select box (InvalidChars SeasonId)
		String nbainvalidcharsSchedlSeasonIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBASchedlsInvalCharsSeasonId");
		String nbainvalidcharsSchedlSeasonIdStatus = "";
				
		AppFunction.NBASchedules(props.getProperty("txtbox_NBASchdules_SeasonId"), props.getProperty("txtbox_NBASchdules_Season"), props.getProperty("txtbox_NBASchdules_PlayDay"), props.getProperty("btn_NBASchdules_Search"), nbainvalidcharsSchedlSeasonIdData[2][1],nbainvalidcharsSchedlSeasonIdData[3][1],nbainvalidcharsSchedlSeasonIdData[4][1]);
						
		boolean errmsgnbainvalidcharsSchedlSeasonId = CommonFunctions.isTextPresent("Please enter Number for Season Id");
		System.out.println(errmsgnbainvalidcharsSchedlSeasonId);
					
		if(errmsgnbainvalidcharsSchedlSeasonId == true)
		{
			nbainvalidcharsSchedlSeasonIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBASchedlsInvalCharsSeasonId", nbainvalidcharsSchedlSeasonIdStatus, 1, 5);
		}
		else
		{
			nbainvalidcharsSchedlSeasonIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBASchedlsInvalCharsSeasonId", nbainvalidcharsSchedlSeasonIdStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidcharsSchedlSeasonIdData[0][1], nbainvalidcharsSchedlSeasonIdData[1][1], nbainvalidcharsSchedlSeasonIdStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Schedules in Manage select box (Invalid SeasonId)
		String nbainvalidSchedlSeasonIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBASchedlsInvalSeasonId");
		String nbainvalidSchedlSeasonIdStatus = "";
				
		AppFunction.NBASchedules(props.getProperty("txtbox_NBASchdules_SeasonId"), props.getProperty("txtbox_NBASchdules_Season"), props.getProperty("txtbox_NBASchdules_PlayDay"), props.getProperty("btn_NBASchdules_Search"), nbainvalidSchedlSeasonIdData[2][1],nbainvalidSchedlSeasonIdData[3][1],nbainvalidSchedlSeasonIdData[4][1]);
						
		boolean errmsgnbainvalidSchedlSeasonId = CommonFunctions.isTextPresent("Season Id Shouldbe 4 digits");
		System.out.println(errmsgnbainvalidSchedlSeasonId);
					
		if(errmsgnbainvalidSchedlSeasonId == true)
		{
			nbainvalidSchedlSeasonIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBASchedlsInvalSeasonId", nbainvalidSchedlSeasonIdStatus, 1, 5);
		}
		else
		{
			nbainvalidSchedlSeasonIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBASchedlsInvalSeasonId", nbainvalidSchedlSeasonIdStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidSchedlSeasonIdData[0][1], nbainvalidSchedlSeasonIdData[1][1], nbainvalidSchedlSeasonIdStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Schedules in Manage select box (Invalid Season)
		String nbainvalidSchedlSeasonData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBASchedlsInvalSeason");
		String nbainvalidSchedlSeasonStatus = "";
				
		AppFunction.NBASchedules(props.getProperty("txtbox_NBASchdules_SeasonId"), props.getProperty("txtbox_NBASchdules_Season"), props.getProperty("txtbox_NBASchdules_PlayDay"), props.getProperty("btn_NBASchdules_Search"), nbainvalidSchedlSeasonData[2][1],nbainvalidSchedlSeasonData[3][1],nbainvalidSchedlSeasonData[4][1]);
						
		boolean errmsgnbainvalidSchedlSeason = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbainvalidSchedlSeason);
					
		if(errmsgnbainvalidSchedlSeason == true)
		{
			nbainvalidSchedlSeasonStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBASchedlsInvalSeason", nbainvalidSchedlSeasonStatus, 1, 5);
		}
		else
		{
			nbainvalidSchedlSeasonStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBASchedlsInvalSeason", nbainvalidSchedlSeasonStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidSchedlSeasonData[0][1], nbainvalidSchedlSeasonData[1][1], nbainvalidSchedlSeasonStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Schedules in Manage select box (Invalid PlayDay)
		String nbainvalidSchedlPlayDayData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBASchedlsInvalPlayDay");
		String nbainvalidSchedlPlayDayStatus = "";
				
		AppFunction.NBASchedules(props.getProperty("txtbox_NBASchdules_SeasonId"), props.getProperty("txtbox_NBASchdules_Season"), props.getProperty("txtbox_NBASchdules_PlayDay"), props.getProperty("btn_NBASchdules_Search"), nbainvalidSchedlPlayDayData[2][1],nbainvalidSchedlPlayDayData[3][1],nbainvalidSchedlPlayDayData[4][1]);
						
		boolean errmsgnbainvalidSchedlPlayDay = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbainvalidSchedlPlayDay);
					
		if(errmsgnbainvalidSchedlPlayDay == true)
		{
			nbainvalidSchedlPlayDayStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBASchedlsInvalPlayDay", nbainvalidSchedlPlayDayStatus, 1, 5);
		}
		else
		{
			nbainvalidSchedlPlayDayStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBASchedlsInvalPlayDay", nbainvalidSchedlPlayDayStatus, 1, 5);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidSchedlPlayDayData[0][1], nbainvalidSchedlPlayDayData[1][1], nbainvalidSchedlPlayDayStatus, "");
				
		Thread.sleep(5000);
		
		//Select NBA Roster in Manage select box
		Select selectNBARoster = new Select(driver.findElement(By.xpath(props.getProperty("slectbox_NBASchdules_Manage"))));
		selectNBARoster.selectByVisibleText("NBA Roster");
		Thread.sleep(3000);
				
		//*******   Select NBA Roster in Manage select box (Empty SeasonId)
		String nbaRosterSeasonIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBARosterEmptySeasonId");
		String nbaRosterSeasonIdStatus = "";
				
		AppFunction.NBARoster(props.getProperty("txtbox_NBARoster_SeasonId"), props.getProperty("txtbox_NBARoster_Season"), props.getProperty("txtbox_NBARoster_Team"), props.getProperty("txtbox_NBARoster_PlayerName"), props.getProperty("btn_NBARoster_Search"), nbaRosterSeasonIdData[2][1],nbaRosterSeasonIdData[3][1],nbaRosterSeasonIdData[4][1],nbaRosterSeasonIdData[5][1]);
						
		boolean errmsgnbaRosterSeasonId = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbaRosterSeasonId);
					
		if(errmsgnbaRosterSeasonId == true)
		{
			nbaRosterSeasonIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterEmptySeasonId", nbaRosterSeasonIdStatus, 1, 6);
		}
		else
		{
			nbaRosterSeasonIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterEmptySeasonId", nbaRosterSeasonIdStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbaRosterSeasonIdData[0][1], nbaRosterSeasonIdData[1][1], nbaRosterSeasonIdStatus, "");
				
		Thread.sleep(5000);
		
		
		//*******   Select NBA Roster in Manage select box (InvalidChars SeasonId)
		String nbainvalidcharsRosterSeasonIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBARosterInvalCharsSeasonId");
		String nbainvalidcharsRosterSeasonIdStatus = "";
				
		AppFunction.NBARoster(props.getProperty("txtbox_NBARoster_SeasonId"), props.getProperty("txtbox_NBARoster_Season"), props.getProperty("txtbox_NBARoster_Team"), props.getProperty("txtbox_NBARoster_PlayerName"), props.getProperty("btn_NBARoster_Search"), nbainvalidcharsRosterSeasonIdData[2][1],nbainvalidcharsRosterSeasonIdData[3][1],nbainvalidcharsRosterSeasonIdData[4][1],nbainvalidcharsRosterSeasonIdData[5][1]);
						
		boolean errmsgnbainvalidcharsRosterSeasonId = CommonFunctions.isTextPresent("Please enter Number for Season Id");
		System.out.println(errmsgnbainvalidcharsRosterSeasonId);
					
		if(errmsgnbainvalidcharsRosterSeasonId == true)
		{
			nbainvalidcharsRosterSeasonIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterInvalCharsSeasonId", nbainvalidcharsRosterSeasonIdStatus, 1, 6);
		}
		else
		{
			nbainvalidcharsRosterSeasonIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterInvalCharsSeasonId", nbainvalidcharsRosterSeasonIdStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidcharsRosterSeasonIdData[0][1], nbainvalidcharsRosterSeasonIdData[1][1], nbainvalidcharsRosterSeasonIdStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Roster in Manage select box (Invalid SeasonId)
		String nbainvalidRosterSeasonIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBARosterInvalSeasonId");
		String nbainvalidRosterSeasonIdStatus = "";
				
		AppFunction.NBARoster(props.getProperty("txtbox_NBARoster_SeasonId"), props.getProperty("txtbox_NBARoster_Season"), props.getProperty("txtbox_NBARoster_Team"), props.getProperty("txtbox_NBARoster_PlayerName"), props.getProperty("btn_NBARoster_Search"), nbainvalidRosterSeasonIdData[2][1],nbainvalidRosterSeasonIdData[3][1],nbainvalidRosterSeasonIdData[4][1],nbainvalidRosterSeasonIdData[5][1]);
						
		boolean errmsgnbainvalidRosterSeasonId = CommonFunctions.isTextPresent("Season Id Shouldbe 4 digits");
		System.out.println(errmsgnbainvalidRosterSeasonId);
					
		if(errmsgnbainvalidRosterSeasonId == true)
		{
			nbainvalidRosterSeasonIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterInvalSeasonId", nbainvalidRosterSeasonIdStatus, 1, 6);
		}
		else
		{
			nbainvalidRosterSeasonIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterInvalSeasonId", nbainvalidRosterSeasonIdStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidRosterSeasonIdData[0][1], nbainvalidRosterSeasonIdData[1][1], nbainvalidRosterSeasonIdStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Roster in Manage select box (Invalid Season)
		String nbainvalidRosterSeasonData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBARosterInvalSeason");
		String nbainvalidRosterSeasonStatus = "";
				
		AppFunction.NBARoster(props.getProperty("txtbox_NBARoster_SeasonId"), props.getProperty("txtbox_NBARoster_Season"), props.getProperty("txtbox_NBARoster_Team"), props.getProperty("txtbox_NBARoster_PlayerName"), props.getProperty("btn_NBARoster_Search"), nbainvalidRosterSeasonData[2][1],nbainvalidRosterSeasonData[3][1],nbainvalidRosterSeasonData[4][1],nbainvalidRosterSeasonData[5][1]);
						
		boolean errmsgnbainvalidRosterSeason = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbainvalidRosterSeason);
					
		if(errmsgnbainvalidRosterSeason == true)
		{
			nbainvalidRosterSeasonStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterInvalSeason", nbainvalidRosterSeasonStatus, 1, 6);
		}
		else
		{
			nbainvalidRosterSeasonStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterInvalSeason", nbainvalidRosterSeasonStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidRosterSeasonData[0][1], nbainvalidRosterSeasonData[1][1], nbainvalidRosterSeasonStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Roster in Manage select box (Invalid Team)
		String nbainvalidRosterTeamData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBARosterInvalTeam");
		String nbainvalidRosterTeamStatus = "";
				
		AppFunction.NBARoster(props.getProperty("txtbox_NBARoster_SeasonId"), props.getProperty("txtbox_NBARoster_Season"), props.getProperty("txtbox_NBARoster_Team"), props.getProperty("txtbox_NBARoster_PlayerName"), props.getProperty("btn_NBARoster_Search"), nbainvalidRosterTeamData[2][1],nbainvalidRosterTeamData[3][1],nbainvalidRosterTeamData[4][1],nbainvalidRosterTeamData[5][1]);
						
		boolean errmsgnbainvalidRosterTeam = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbainvalidRosterTeam);
					
		if(errmsgnbainvalidRosterTeam == true)
		{
			nbainvalidRosterTeamStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterInvalTeam", nbainvalidRosterTeamStatus, 1, 6);
		}
		else
		{
			nbainvalidRosterTeamStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterInvalTeam", nbainvalidRosterTeamStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidRosterTeamData[0][1], nbainvalidRosterTeamData[1][1], nbainvalidRosterTeamStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Roster in Manage select box (Invalid Player Name)
		String nbainvalidRosterPlayerNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBARosterInvalPlayrName");
		String nbainvalidRosterPlayerNameStatus = "";
				
		AppFunction.NBARoster(props.getProperty("txtbox_NBARoster_SeasonId"), props.getProperty("txtbox_NBARoster_Season"), props.getProperty("txtbox_NBARoster_Team"), props.getProperty("txtbox_NBARoster_PlayerName"), props.getProperty("btn_NBARoster_Search"), nbainvalidRosterPlayerNameData[2][1],nbainvalidRosterPlayerNameData[3][1],nbainvalidRosterPlayerNameData[4][1],nbainvalidRosterPlayerNameData[5][1]);
						
		boolean errmsgnbainvalidRosterPlayerName = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbainvalidRosterPlayerName);
					
		if(errmsgnbainvalidRosterPlayerName == true)
		{
			nbainvalidRosterPlayerNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterInvalPlayrName", nbainvalidRosterPlayerNameStatus, 1, 6);
		}
		else
		{
			nbainvalidRosterPlayerNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBARosterInvalPlayrName", nbainvalidRosterPlayerNameStatus, 1, 6);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidRosterPlayerNameData[0][1], nbainvalidRosterPlayerNameData[1][1], nbainvalidRosterPlayerNameStatus, "");
				
		Thread.sleep(5000);
		
		//Select NBA Statistics in Manage select box
		Select selectNBAStatistics = new Select(driver.findElement(By.xpath(props.getProperty("slectbox_NBARoster_Manage"))));
		selectNBAStatistics.selectByVisibleText("NBA Statistics");
		Thread.sleep(3000);
			
		//*******   Select NBA Statistics in Manage select box (NbaStatics validation)
		String nbaStatisticsData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBAStatics");
		String nbaStatisticsStatus = "";
				
		AppFunction.NBStatistics(props.getProperty("txtbox_NBAStatics_SeasonId"), props.getProperty("txtbox_NBAStatics_Season"), props.getProperty("txtbox_NBAStatics_TeamName"), props.getProperty("txtbox_NBAStatics_PlayerFName"), props.getProperty("txtbox_NBAStatics_PlayerLName"), props.getProperty("txtbox_NBAStatics_PlayerId"), props.getProperty("btn_NBAStatics_Search"), nbaStatisticsData[2][1],nbaStatisticsData[3][1],nbaStatisticsData[4][1],nbaStatisticsData[5][1],nbaStatisticsData[6][1],nbaStatisticsData[7][1]);
						
		boolean errmsgnbaStatistics = CommonFunctions.isTextPresent("Please provide data for atleast 3 fields");
		System.out.println(errmsgnbaStatistics);
					
		if(errmsgnbaStatistics == true)
		{
			nbaStatisticsStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStatics", nbaStatisticsStatus, 1, 8);
		}
		else
		{
			nbaStatisticsStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStatics", nbaStatisticsStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbaStatisticsData[0][1], nbaStatisticsData[1][1], nbaStatisticsStatus, "");
				
		Thread.sleep(5000);
		
		
		//*******   Select NBA Statistics in Manage select box (Empty SeasonId)
		String nbaStatisticsSeasonIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBAStaticsEmptySeasonId");
		String nbaStatisticsSeasonIdStatus = "";
				
		AppFunction.NBStatistics(props.getProperty("txtbox_NBAStatics_SeasonId"), props.getProperty("txtbox_NBAStatics_Season"), props.getProperty("txtbox_NBAStatics_TeamName"), props.getProperty("txtbox_NBAStatics_PlayerFName"), props.getProperty("txtbox_NBAStatics_PlayerLName"), props.getProperty("txtbox_NBAStatics_PlayerId"), props.getProperty("btn_NBAStatics_Search"), nbaStatisticsSeasonIdData[2][1],nbaStatisticsSeasonIdData[3][1],nbaStatisticsSeasonIdData[4][1],nbaStatisticsSeasonIdData[5][1],nbaStatisticsSeasonIdData[6][1],nbaStatisticsSeasonIdData[7][1]);
						
		boolean errmsgnbaStatisticsSeasonId = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbaStatisticsSeasonId);
					
		if(errmsgnbaStatisticsSeasonId == true)
		{
			nbaStatisticsSeasonIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsEmptySeasonId", nbaStatisticsSeasonIdStatus, 1, 8);
		}
		else
		{
			nbaStatisticsSeasonIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsEmptySeasonId", nbaStatisticsSeasonIdStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbaStatisticsSeasonIdData[0][1], nbaStatisticsSeasonIdData[1][1], nbaStatisticsSeasonIdStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Statistics in Manage select box (InvalidChars SeasonId)
		String nbainvalidcharsStaticsSeasonIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBAStaticsInvalCharsSeasonId");
		String nbainvalidcharsStaticsSeasonIdStatus = "";
				
		AppFunction.NBStatistics(props.getProperty("txtbox_NBAStatics_SeasonId"), props.getProperty("txtbox_NBAStatics_Season"), props.getProperty("txtbox_NBAStatics_TeamName"), props.getProperty("txtbox_NBAStatics_PlayerFName"), props.getProperty("txtbox_NBAStatics_PlayerLName"), props.getProperty("txtbox_NBAStatics_PlayerId"), props.getProperty("btn_NBAStatics_Search"), nbainvalidcharsStaticsSeasonIdData[2][1],nbainvalidcharsStaticsSeasonIdData[3][1],nbainvalidcharsStaticsSeasonIdData[4][1],nbainvalidcharsStaticsSeasonIdData[5][1],nbainvalidcharsStaticsSeasonIdData[6][1],nbainvalidcharsStaticsSeasonIdData[7][1]);
						
		boolean errmsgnbainvalidcharsStaticsSeasonId = CommonFunctions.isTextPresent("Please enter Number for Season Id");
		System.out.println(errmsgnbainvalidcharsStaticsSeasonId);
					
		if(errmsgnbainvalidcharsStaticsSeasonId == true)
		{
			nbainvalidcharsStaticsSeasonIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalCharsSeasonId", nbainvalidcharsStaticsSeasonIdStatus, 1, 8);
		}
		else
		{
			nbainvalidcharsStaticsSeasonIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalCharsSeasonId", nbainvalidcharsStaticsSeasonIdStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidcharsStaticsSeasonIdData[0][1], nbainvalidcharsStaticsSeasonIdData[1][1], nbainvalidcharsStaticsSeasonIdStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Statistics in Manage select box (Invalid SeasonId)
		String nbainvalidStaticsSeasonIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBAStaticsInvalSeasonId");
		String nbainvalidStaticsSeasonIdStatus = "";
				
		AppFunction.NBStatistics(props.getProperty("txtbox_NBAStatics_SeasonId"), props.getProperty("txtbox_NBAStatics_Season"), props.getProperty("txtbox_NBAStatics_TeamName"), props.getProperty("txtbox_NBAStatics_PlayerFName"), props.getProperty("txtbox_NBAStatics_PlayerLName"), props.getProperty("txtbox_NBAStatics_PlayerId"), props.getProperty("btn_NBAStatics_Search"), nbainvalidStaticsSeasonIdData[2][1],nbainvalidStaticsSeasonIdData[3][1],nbainvalidStaticsSeasonIdData[4][1],nbainvalidStaticsSeasonIdData[5][1],nbainvalidStaticsSeasonIdData[6][1],nbainvalidStaticsSeasonIdData[7][1]);
						
		boolean errmsgnbainvalidStaticsSeasonId = CommonFunctions.isTextPresent("Season Id Shouldbe 4 digits");
		System.out.println(errmsgnbainvalidStaticsSeasonId);
					
		if(errmsgnbainvalidStaticsSeasonId == true)
		{
			nbainvalidStaticsSeasonIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalSeasonId", nbainvalidStaticsSeasonIdStatus, 1, 8);
		}
		else
		{
			nbainvalidStaticsSeasonIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalSeasonId", nbainvalidStaticsSeasonIdStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidStaticsSeasonIdData[0][1], nbainvalidStaticsSeasonIdData[1][1], nbainvalidStaticsSeasonIdStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Statistics in Manage select box (Invalid Season)
		String nbainvalidStaticsSeasonData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBAStaticsInvalSeason");
		String nbainvalidStaticsSeasonStatus = "";
				
		AppFunction.NBStatistics(props.getProperty("txtbox_NBAStatics_SeasonId"), props.getProperty("txtbox_NBAStatics_Season"), props.getProperty("txtbox_NBAStatics_TeamName"), props.getProperty("txtbox_NBAStatics_PlayerFName"), props.getProperty("txtbox_NBAStatics_PlayerLName"), props.getProperty("txtbox_NBAStatics_PlayerId"), props.getProperty("btn_NBAStatics_Search"), nbainvalidStaticsSeasonData[2][1],nbainvalidStaticsSeasonData[3][1],nbainvalidStaticsSeasonData[4][1],nbainvalidStaticsSeasonData[5][1],nbainvalidStaticsSeasonData[6][1],nbainvalidStaticsSeasonData[7][1]);
						
		boolean errmsgnbainvalidStaticsSeason = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbainvalidStaticsSeason);
					
		if(errmsgnbainvalidStaticsSeason == true)
		{
			nbainvalidStaticsSeasonStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalSeason", nbainvalidStaticsSeasonStatus, 1, 8);
		}
		else
		{
			nbainvalidStaticsSeasonStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalSeason", nbainvalidStaticsSeasonStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidStaticsSeasonData[0][1], nbainvalidStaticsSeasonData[1][1], nbainvalidStaticsSeasonIdStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Statistics in Manage select box (Invalid TeamName)
		String nbainvalidStaticsTeamNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBAStaticsInvalTeamName");
		String nbainvalidStaticsTeamNameStatus = "";
				
		AppFunction.NBStatistics(props.getProperty("txtbox_NBAStatics_SeasonId"), props.getProperty("txtbox_NBAStatics_Season"), props.getProperty("txtbox_NBAStatics_TeamName"), props.getProperty("txtbox_NBAStatics_PlayerFName"), props.getProperty("txtbox_NBAStatics_PlayerLName"), props.getProperty("txtbox_NBAStatics_PlayerId"), props.getProperty("btn_NBAStatics_Search"), nbainvalidStaticsTeamNameData[2][1],nbainvalidStaticsTeamNameData[3][1],nbainvalidStaticsTeamNameData[4][1],nbainvalidStaticsTeamNameData[5][1],nbainvalidStaticsTeamNameData[6][1],nbainvalidStaticsTeamNameData[7][1]);
						
		boolean errmsgnbainvalidStaticsTeamName = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbainvalidStaticsTeamName);
					
		if(errmsgnbainvalidStaticsTeamName == true)
		{
			nbainvalidStaticsTeamNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalTeamName", nbainvalidStaticsTeamNameStatus, 1, 8);
		}
		else
		{
			nbainvalidStaticsTeamNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalTeamName", nbainvalidStaticsTeamNameStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidStaticsTeamNameData[0][1], nbainvalidStaticsTeamNameData[1][1], nbainvalidStaticsTeamNameStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Statistics in Manage select box (Invalid PlyrFName)
		String nbainvalidStaticsPlyrFNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBAStaticsInvalPlyrFName");
		String nbainvalidStaticsPlyrFNameStatus = "";
				
		AppFunction.NBStatistics(props.getProperty("txtbox_NBAStatics_SeasonId"), props.getProperty("txtbox_NBAStatics_Season"), props.getProperty("txtbox_NBAStatics_TeamName"), props.getProperty("txtbox_NBAStatics_PlayerFName"), props.getProperty("txtbox_NBAStatics_PlayerLName"), props.getProperty("txtbox_NBAStatics_PlayerId"), props.getProperty("btn_NBAStatics_Search"), nbainvalidStaticsPlyrFNameData[2][1],nbainvalidStaticsPlyrFNameData[3][1],nbainvalidStaticsPlyrFNameData[4][1],nbainvalidStaticsPlyrFNameData[5][1],nbainvalidStaticsPlyrFNameData[6][1],nbainvalidStaticsPlyrFNameData[7][1]);
						
		boolean errmsgnbainvalidStaticsPlyrFName = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbainvalidStaticsPlyrFName);
					
		if(errmsgnbainvalidStaticsPlyrFName == true)
		{
			nbainvalidStaticsPlyrFNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalPlyrFName", nbainvalidStaticsPlyrFNameStatus, 1, 8);
		}
		else
		{
			nbainvalidStaticsPlyrFNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalPlyrFName", nbainvalidStaticsPlyrFNameStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidStaticsPlyrFNameData[0][1], nbainvalidStaticsPlyrFNameData[1][1], nbainvalidStaticsPlyrFNameStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Statistics in Manage select box (Invalid PlyrLName)
		String nbainvalidStaticsPlyrLNameData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBAStaticsInvalPlyrLName");
		String nbainvalidStaticsPlyrLNameStatus = "";
				
		AppFunction.NBStatistics(props.getProperty("txtbox_NBAStatics_SeasonId"), props.getProperty("txtbox_NBAStatics_Season"), props.getProperty("txtbox_NBAStatics_TeamName"), props.getProperty("txtbox_NBAStatics_PlayerFName"), props.getProperty("txtbox_NBAStatics_PlayerLName"), props.getProperty("txtbox_NBAStatics_PlayerId"), props.getProperty("btn_NBAStatics_Search"), nbainvalidStaticsPlyrLNameData[2][1],nbainvalidStaticsPlyrLNameData[3][1],nbainvalidStaticsPlyrLNameData[4][1],nbainvalidStaticsPlyrLNameData[5][1],nbainvalidStaticsPlyrLNameData[6][1],nbainvalidStaticsPlyrLNameData[7][1]);
						
		boolean errmsgnbainvalidStaticsPlyrLName = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbainvalidStaticsPlyrLName);
					
		if(errmsgnbainvalidStaticsPlyrLName == true)
		{
			nbainvalidStaticsPlyrLNameStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalPlyrLName", nbainvalidStaticsPlyrLNameStatus, 1, 8);
		}
		else
		{
			nbainvalidStaticsPlyrLNameStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalPlyrLName", nbainvalidStaticsPlyrLNameStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidStaticsPlyrLNameData[0][1], nbainvalidStaticsPlyrLNameData[1][1], nbainvalidStaticsPlyrLNameStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Statistics in Manage select box (Invalid PlyerId)
		String nbainvalidStaticsPlyerIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBAStaticsInvalPlyrId");
		String nbainvalidStaticsPlyerIdStatus = "";
				
		AppFunction.NBStatistics(props.getProperty("txtbox_NBAStatics_SeasonId"), props.getProperty("txtbox_NBAStatics_Season"), props.getProperty("txtbox_NBAStatics_TeamName"), props.getProperty("txtbox_NBAStatics_PlayerFName"), props.getProperty("txtbox_NBAStatics_PlayerLName"), props.getProperty("txtbox_NBAStatics_PlayerId"), props.getProperty("btn_NBAStatics_Search"), nbainvalidStaticsPlyerIdData[2][1],nbainvalidStaticsPlyerIdData[3][1],nbainvalidStaticsPlyerIdData[4][1],nbainvalidStaticsPlyerIdData[5][1],nbainvalidStaticsPlyerIdData[6][1],nbainvalidStaticsPlyerIdData[7][1]);
						
		boolean errmsgnbainvalidStaticsPlyerId = CommonFunctions.isTextPresent("No records found");
		System.out.println(errmsgnbainvalidStaticsPlyerId);
					
		if(errmsgnbainvalidStaticsPlyerId == true)
		{
			nbainvalidStaticsPlyerIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalPlyrId", nbainvalidStaticsPlyerIdStatus, 1, 8);
		}
		else
		{
			nbainvalidStaticsPlyerIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalPlyrId", nbainvalidStaticsPlyerIdStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidStaticsPlyerIdData[0][1], nbainvalidStaticsPlyerIdData[1][1], nbainvalidStaticsPlyerIdStatus, "");
				
		Thread.sleep(5000);
		
		//*******   Select NBA Statistics in Manage select box (Invalidchars PlyerId)
		String nbainvalidcharsStaticsPlyerIdData[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","NBAStaticsInvalcharsPlyrId");
		String nbainvalidcharsStaticsPlyerIdStatus = "";
				
		AppFunction.NBStatistics(props.getProperty("txtbox_NBAStatics_SeasonId"), props.getProperty("txtbox_NBAStatics_Season"), props.getProperty("txtbox_NBAStatics_TeamName"), props.getProperty("txtbox_NBAStatics_PlayerFName"), props.getProperty("txtbox_NBAStatics_PlayerLName"), props.getProperty("txtbox_NBAStatics_PlayerId"), props.getProperty("btn_NBAStatics_Search"), nbainvalidcharsStaticsPlyerIdData[2][1],nbainvalidcharsStaticsPlyerIdData[3][1],nbainvalidcharsStaticsPlyerIdData[4][1],nbainvalidcharsStaticsPlyerIdData[5][1],nbainvalidcharsStaticsPlyerIdData[6][1],nbainvalidcharsStaticsPlyerIdData[7][1]);
						
		boolean errmsgnbainvalidcharsStaticsPlyerId = CommonFunctions.isTextPresent("Please enter Number for Player Id");
		System.out.println(errmsgnbainvalidcharsStaticsPlyerId);
					
		if(errmsgnbainvalidcharsStaticsPlyerId == true)
		{
			nbainvalidcharsStaticsPlyerIdStatus = "PASS";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalcharsPlyrId", nbainvalidcharsStaticsPlyerIdStatus, 1, 8);
		}
		else
		{
			nbainvalidcharsStaticsPlyerIdStatus = "FAIL";
			CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "NBAStaticsInvalcharsPlyrId", nbainvalidcharsStaticsPlyerIdStatus, 1, 8);
			CommonFunctions.googleScreenshot();
		}
				
		pw.printf(rowFormat, nbainvalidcharsStaticsPlyerIdData[0][1], nbainvalidcharsStaticsPlyerIdData[1][1], nbainvalidcharsStaticsPlyerIdStatus, "");
				
		Thread.sleep(5000);
		
		
		//****---------------------Logout link Clicked-------------------------****
		String Logout[][]= CommonFunctions.readData("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls","AdminLogout");
		String logoutStatus = "";
		
//		Select selectLogout = new Select(driver.findElement(By.xpath(props.getProperty("slectbox_NBASchdules_Manage"))));
//		selectLogout.selectByVisibleText("Users");
//		Thread.sleep(2000);
		
		CommonFunctions.genericLogout(props.getProperty("logoutLink")); 
		
		logoutStatus = "PASS";
		CommonFunctions.writeReport("D:\\WorkSpace\\FirstPickAdmin\\Data\\FpAdminData.xls", "AdminLogout", logoutStatus, 1, 2);
		Thread.sleep(2000);
		pw.printf(rowFormat, Logout[0][1], Logout[1][1], logoutStatus, "");
		
		String endtime = CommonFunctions.dateandTime();
		System.out.println(endtime);
		
		//****---------------------Mail Parameters declared-------------------------****
		String from = "ramakrishna.yalla@pbsystems.com";
		String to = "ramu.pasupuleti@pbsystems.com";
		String subject = "Testing First Pick Admin Site Using Selenium Automation Tool";
		String cc = "kalyan.urimi@pbsystems.com";
		
		String message1 = "Hello," + "<br/><br/>" + "The following is the summary of test results for FirstPick Admin: -" + "<br/><br/>" + "Testing start time:: " + starttime + "<br/><br/>" + "Testing end time:: " + endtime + "<br/><br/>" + "<table border='1'>" + sw.toString() + "</table>" + "<br/>" + "Do not reply." + "<br/><br/>" + "Thanks and Regards," +"<br/>"+ "FirstPick QA" ;
		
		Mail mail = new Mail(from, to, cc, subject, message1);
		try 
		{
			mail.send();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		//close firefox browser
		driver.quit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	
		}
	
}
