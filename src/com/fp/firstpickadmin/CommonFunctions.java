package com.fp.firstpickadmin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.fp.firstpickadmin.AdminLogin;


public class CommonFunctions {

	//*****************   Generic Login   *********************//
	public static void genericLogin(String userIdObj,String pwdObj,String loginBtnObj,String userIdTxt,String pwdTxt) throws Exception  
	{
		AdminLogin.getDriver().findElement(By.xpath(userIdObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(userIdObj)).sendKeys(userIdTxt);
		Thread.sleep(1000);
		System.out.println("FpUser UserName/Email Entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(pwdObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(pwdObj)).sendKeys(pwdTxt);
		Thread.sleep(1000);
		System.out.println("FpUser Password Entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(loginBtnObj)).click();
		Thread.sleep(5000);
		System.out.println("FpUser Login Button Clicked.");
	}

	//*****************   Generic Logout   *********************//	
	public static void genericLogout(String logoutObj)
	{
		AdminLogin.getDriver().findElement(By.xpath(logoutObj)).click();	
		System.out.println("LogOut link clicked.");
	}
	
	//**************   Reading an Excel Data   *********************//
	public static String[][] readData(String readfileName, String readsheetName) throws  IOException, BiffException

	{

		File infoFile = new File(readfileName);

		Workbook infoWorkBook = Workbook.getWorkbook(infoFile);

		Sheet infoSheet = infoWorkBook.getSheet(readsheetName);
		int infoRows = infoSheet.getRows();

		int infoColumns = infoSheet.getColumns();

		String[][] column = new String[infoColumns][infoRows];

		for(int i=1; i< infoRows; i++)

		{

		for(int j1=0; j1<infoColumns; j1++)

		{
		column[j1][i] = infoSheet.getCell(j1, i).getContents();

//			System.out.println(column[j1][i]);
		
		}

		}
		return column;

	}

	
	//**************   Writing a Data into Excel   *********************//
	public static void writeReport(String readFileName, String readSheetName, String result, int row,int col) throws BiffException, IOException, RowsExceededException, WriteException

	{

		File fileWrite = new File(readFileName);
	
		Workbook in = Workbook.getWorkbook(fileWrite);
	
		WritableWorkbook writable_workbook = Workbook.createWorkbook(fileWrite, in);
	
		WritableSheet writable_sheet = writable_workbook.getSheet(readSheetName);
	
		writable_sheet.addCell(new Label(col, row , result));
	
		writable_workbook.write();
	
		writable_workbook.close();

	}	
	
	//*************** Verify a Web Element Existence **************//
	public static boolean verifyElementAbsent(String locator) throws Exception
	{		
		int verificationerrmsg = 0;
		
		verificationerrmsg = AdminLogin.getDriver().findElements(By.xpath(locator)).size();
		boolean result;
//		System.out.println(verificationerrmsg);
		if(verificationerrmsg > 0)
		{
			result = true;	
		}
		else
		{
			result = false;
		}
//	    System.out.println(result);
	    
	    return result;
	}
		
	public static boolean isTextPresent(String text)
	{
        try{
            boolean b = AdminLogin.getDriver().getPageSource().contains(text);
            return b;
        }
        catch(Exception e){
            return false;
        }
    }
	
	public static String dateandTime() 
	{
		  Calendar currentDate = Calendar.getInstance();
		  SimpleDateFormat formatter= 
		  new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss");
		  String dateNow = formatter.format(currentDate.getTime());
		  return dateNow;
	}
	
	
	public static void gettabledata()
	{
		
		WebElement table = AdminLogin.getDriver().findElement(By.tagName("table"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		Iterator<WebElement> i = rows.iterator();
		System.out.println("Table has following content");
		while(i.hasNext()){
		    WebElement row=i.next();
		    List<WebElement> columns= row.findElements(By.tagName("td"));
		    Iterator<WebElement> j = columns.iterator();
		        while(j.hasNext()){
		            WebElement column = j.next();
		            System.out.print(column.getText());
		            System.out.print("    |  ");
		        }System.out.println("");
		}System.out.println("Table content is printed");
		    
	}
	
	public static void googleScreenshot()
	{
		try 
		{
			File scrnsht = ((TakesScreenshot)AdminLogin.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrnsht, new File("D:\\WorkSpace\\FirstPickAdmin\\Screenshots\\google_page.png"));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
