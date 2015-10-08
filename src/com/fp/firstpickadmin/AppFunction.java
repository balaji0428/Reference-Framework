package com.fp.firstpickadmin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.fp.firstpickadmin.AdminLogin;

public class AppFunction {

//	public static void activateanddeactivatUsers()
//	{
//		AdminLogin.getDriver().findElement(By.xpath(props.getProperty("activeChkbox1"))).click();
//		Thread.sleep(1000);
//		AdminLogin.getDriver().findElement(By.xpath(props.getProperty("activeChkbox2"))).click();
//		Thread.sleep(1000);
//		AdminLogin.getDriver().findElement(By.xpath(props.getProperty("activeBtn"))).click();
//		Thread.sleep(20000);
//	}
	
	public static void playerpositions(String selctSprotPPObj,String txtPositionPPObj,String txtAbbreviationPPObj,String txtactualsportabbrvatinPPObj,String txtfilterlblPPObj,String txtorderbyPPObj,String btnSubmitPPObj,String selSportVal,String txtPositionVal,String txtabbrvatnVal,String txtactualabbrvatnVal,String txtfilterlblVal,String txtOrderByVal) throws InterruptedException
	{	
		Select sportpp = new Select(AdminLogin.getDriver().findElement(By.xpath(selctSprotPPObj)));
		sportpp.selectByVisibleText(selSportVal);
		Thread.sleep(2000);	
		System.out.println("Player Postion Sprot Selected.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtPositionPPObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtPositionPPObj)).sendKeys(txtPositionVal); 
		Thread.sleep(1000);
		System.out.println("Player Postion entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtAbbreviationPPObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtAbbreviationPPObj)).sendKeys(txtabbrvatnVal);
		Thread.sleep(1000);
		System.out.println("Player postion abbreviation entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtactualsportabbrvatinPPObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtactualsportabbrvatinPPObj)).sendKeys(txtactualabbrvatnVal);		
		Thread.sleep(1000);
		System.out.println("Player Postion actualabbreviation entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtfilterlblPPObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtfilterlblPPObj)).sendKeys(txtfilterlblVal);		
		Thread.sleep(1000);
		System.out.println("Player Postion filter label entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtorderbyPPObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtorderbyPPObj)).sendKeys(txtOrderByVal);	
		Thread.sleep(1000);
		System.out.println("Player Postion orderby entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSubmitPPObj)).click();  
		Thread.sleep(2000);	
		System.out.println("Player Postion Submit button clicked.");
	}
	
	public static void sports(String txtSprotSObj,String txtAbbreviationSObj,String txtorderbySObj,String btnSubmitSObj,String txtSportSVal,String txtabbrvatnSVal,String txtOrderBySVal) throws InterruptedException
	{
	
		AdminLogin.getDriver().findElement(By.xpath(txtSprotSObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtSprotSObj)).sendKeys(txtSportSVal); 
		Thread.sleep(1000);
		System.out.println("Sports text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtAbbreviationSObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtAbbreviationSObj)).sendKeys(txtabbrvatnSVal);	
		Thread.sleep(1000);
		System.out.println("Sports Abbreviation entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtorderbySObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtorderbySObj)).sendKeys(txtOrderBySVal);  
		Thread.sleep(1000);
		System.out.println("Sports orderby entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSubmitSObj)).click();   
		Thread.sleep(2000);
		System.out.println("Sports Submit button clicked.");

	}
	
	public static void states(String selctCountryObj,String txtStateNameObj,String txtStateCodeObj,String btnSubmitCSObj,String selctCountryVal,String txtstatenameVal,String txtstatecodeVal) throws InterruptedException
	{
		
		Select selctcounty = new Select(AdminLogin.getDriver().findElement(By.xpath(selctCountryObj)));
		selctcounty.selectByVisibleText(selctCountryVal);
		Thread.sleep(1000);
		System.out.println("Country Selected.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtStateNameObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtStateNameObj)).sendKeys(txtstatenameVal);	
		Thread.sleep(1000);
		System.out.println("StateName text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtStateCodeObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtStateCodeObj)).sendKeys(txtstatecodeVal); 
		Thread.sleep(1000);
		System.out.println("StateCode text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSubmitCSObj)).click();
		Thread.sleep(2000);
		System.out.println("Country States Submit button clicked.");
		
	}
	
	public static void country(String txtCountryNameObj,String txtCountryCodeObj,String btnSubmitCObj,String txtCountryNameVal,String txtCountryCodeVal) throws InterruptedException
	{
	
		AdminLogin.getDriver().findElement(By.xpath(txtCountryNameObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtCountryNameObj)).sendKeys(txtCountryNameVal);	  
		Thread.sleep(1000);
		System.out.println("CountryName text entered.");
		AdminLogin.getDriver().findElement(By.xpath(txtCountryCodeObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtCountryCodeObj)).sendKeys(txtCountryCodeVal);   
		Thread.sleep(1000);
		System.out.println("CountryCode text entered.");
		AdminLogin.getDriver().findElement(By.xpath(btnSubmitCObj)).click();  
		Thread.sleep(2000);
		System.out.println("Country Submit button clicked.");
		
	}
	
	public static void Multiplayerchallenges(String selctEntryTypeMPCObj,String txtDescriptionMPCObj,String txtOrderBYMPCObj,String btnSubmitMPCObj,String selctEntryTypeMPCVal,String txtDescriptionMPCVal,String txtOrderBYMPCVal) throws InterruptedException
	{
		Select selctEntryType = new Select(AdminLogin.getDriver().findElement(By.xpath(selctEntryTypeMPCObj)));
		selctEntryType.selectByVisibleText(selctEntryTypeMPCVal);
		Thread.sleep(1000);
		System.out.println("MutlitplayerChallenges EntryType Selected.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtDescriptionMPCObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtDescriptionMPCObj)).sendKeys(txtDescriptionMPCVal);	
		Thread.sleep(1000);
		System.out.println("MutlitplayerChallenges Description text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtOrderBYMPCObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtOrderBYMPCObj)).sendKeys(txtOrderBYMPCVal); 
		Thread.sleep(1000);
		System.out.println("MutlitplayerChallenges OrderBy text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSubmitMPCObj)).click();
		Thread.sleep(2000);
		System.out.println("MultiPlyerChallenges Submit button clicked.");
	}
	
	public static void HeadtoHeadChallenges(String selctEntryTypeHtoHObj,String txtDescriptionHtoHObj,String txtOrderBYHtoHObj,String btnSubmitHtoHObj,String selctEntryTypeHtoHVal,String txtDescriptionHtoHVal,String txtOrderBYHtoHVal) throws InterruptedException
	{
		Select selctEntryType = new Select(AdminLogin.getDriver().findElement(By.xpath(selctEntryTypeHtoHObj)));
		selctEntryType.selectByVisibleText(selctEntryTypeHtoHVal);
		Thread.sleep(1000);
		System.out.println("HeadtoHeadChallenges EntryType Selected.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtDescriptionHtoHObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtDescriptionHtoHObj)).sendKeys(txtDescriptionHtoHVal);	
		Thread.sleep(1000);
		System.out.println("HeadtoHeadChallenges Description text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtOrderBYHtoHObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtOrderBYHtoHObj)).sendKeys(txtOrderBYHtoHVal); 
		Thread.sleep(1000);
		System.out.println("HeadtoHeadChallenges OrderBy text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSubmitHtoHObj)).click();
		Thread.sleep(2000);
		System.out.println("HeadtoHeadChallenges Submit button clicked.");
	}
	
	public static void emailConfig(String selctEmailTypeObj,String txtfromaddrObj,String txtsubjectObj,String txtemailcotentObj,String btnSubmitECObj,String selctEmailTypeVal,String txtfromaddrVal,String txtsubjectVal,String txtemailcontentVal) throws InterruptedException
	{
		Select selctEntryType = new Select(AdminLogin.getDriver().findElement(By.xpath(selctEmailTypeObj)));
		selctEntryType.selectByVisibleText(selctEmailTypeVal);
		Thread.sleep(1000);
		System.out.println("Email Config EmailType Selected.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtfromaddrObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtfromaddrObj)).sendKeys(txtfromaddrVal);	
		Thread.sleep(1000);
		System.out.println("Email Config fromaddress text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtsubjectObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtsubjectObj)).sendKeys(txtsubjectVal); 
		Thread.sleep(1000);
		System.out.println("Email Config Subject text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtemailcotentObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtemailcotentObj)).sendKeys(txtemailcontentVal); 
		Thread.sleep(1000);
		System.out.println("Email Config emailContent text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSubmitECObj)).click();
		Thread.sleep(2000);
		System.out.println("Email Config Submit button clicked.");
	}
	
	public static void NFLschdleYear(String txtNFLschdlYearObj,String txtNFLschdlSeasonObj,String txtNFLschdlWeekObj,String txtNFLschdlGameStatusObj,String txtNFLschdlPlayDayObj,String btnSearchNFLObj,String txtNFLschdlYearVal,String txtNFLschdlSeasonVal,String txtNFLschdlWeekVal,String txtNFLschdlGameStatusVal,String txtNFLschdlPlayDayVal) throws InterruptedException
	{
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLschdlYearObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtNFLschdlYearObj)).sendKeys(txtNFLschdlYearVal);	
		Thread.sleep(1000);
		System.out.println("NFL Schedules Year text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLschdlSeasonObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLschdlSeasonObj)).sendKeys(txtNFLschdlSeasonVal); 
		Thread.sleep(1000);
		System.out.println("NFL Schedules Season text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLschdlWeekObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLschdlWeekObj)).sendKeys(txtNFLschdlWeekVal); 
		Thread.sleep(1000);
		System.out.println("NFL Schedules Week text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLschdlGameStatusObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLschdlGameStatusObj)).sendKeys(txtNFLschdlGameStatusVal); 
		Thread.sleep(1000);
		System.out.println("NFL Schedules GameStatus text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLschdlPlayDayObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLschdlPlayDayObj)).sendKeys(txtNFLschdlPlayDayVal); 
		Thread.sleep(1000);
		System.out.println("NFL Schedules PlayDay text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSearchNFLObj)).click();
		Thread.sleep(2000);
		System.out.println("NFL Schedules Search button clicked.");
		
	}
	
	public static void NFLBoxScore(String txtNFLboxscoreYearObj,String txtNFLboxscoreSeasonObj,String txtNFLboxscoreWeekObj,String btnSearchNFLBSObj,String txtNFLboxscoreYearVal,String txtNFLboxscoreSeasonVal,String txtNFLboxscoreWeekVal) throws InterruptedException
	{
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLboxscoreYearObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtNFLboxscoreYearObj)).sendKeys(txtNFLboxscoreYearVal);	
		Thread.sleep(1000);
		System.out.println("NFL Box Score Year text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLboxscoreSeasonObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLboxscoreSeasonObj)).sendKeys(txtNFLboxscoreSeasonVal); 
		Thread.sleep(1000);
		System.out.println("NFL Box Score Season text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLboxscoreWeekObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLboxscoreWeekObj)).sendKeys(txtNFLboxscoreWeekVal); 
		Thread.sleep(1000);
		System.out.println("NFL Box Score Week text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSearchNFLBSObj)).click();
		Thread.sleep(2000);
		System.out.println("Box Score Search button clicked.");
		
	}
	
	public static void NFLStatistics(String txtNFLstaticsYearObj,String txtNFLstaticsSeasonObj,String txtNFLstaticsWeekObj,String txtNFLstaticsTeamObj,String txtNFLstaticsPlayerNameObj,String txtNFLstaticsPlayerIdObj,String btnSearchNFLstaticsObj,String txtNFLstaticsYearVal,String txtNFLstaticsSeasonVal,String txtNFLstaticsWeekVal,String txtNFLstaticsTeamVal,String txtNFLstaticsPlayerNameVal,String txtNFLstaticsPlayerIdVal) throws InterruptedException
	{
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsYearObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsYearObj)).sendKeys(txtNFLstaticsYearVal);	
		Thread.sleep(1000);
		System.out.println("NFL Statistics Year text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsSeasonObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsSeasonObj)).sendKeys(txtNFLstaticsSeasonVal); 
		Thread.sleep(1000);
		System.out.println("NFL Statistics Season text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsWeekObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsWeekObj)).sendKeys(txtNFLstaticsWeekVal); 
		Thread.sleep(1000);
		System.out.println("NFL Statistics Week text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsTeamObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsTeamObj)).sendKeys(txtNFLstaticsTeamVal); 
		Thread.sleep(1000);
		System.out.println("NFL Statistics Team text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsPlayerNameObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsPlayerNameObj)).sendKeys(txtNFLstaticsPlayerNameVal); 
		Thread.sleep(1000);
		System.out.println("NFL Statistics PlayName text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsPlayerIdObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLstaticsPlayerIdObj)).sendKeys(txtNFLstaticsPlayerIdVal); 
		Thread.sleep(1000);
		System.out.println("NFL Statistics PlayId text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSearchNFLstaticsObj)).click();
		Thread.sleep(2000);
		System.out.println("NFL Statistics Search button clicked.");
		
	}
	
	public static void NFLRoster(String txtNFLRosterYearObj,String txtNFLRosterSeasonObj,String txtNFLRosterWeekObj,String txtNFLRosterTeamObj,String txtNFLRosterPlayerFNameObj,String btnSearchNFLRosterObj,String txtNFLRosterYearVal,String txtNFLRosterSeasonVal,String txtNFLRosterWeekVal,String txtNFLRosterTeamVal,String txtNFLRosterPlayerFNameVal) throws InterruptedException
	{
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLRosterYearObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtNFLRosterYearObj)).sendKeys(txtNFLRosterYearVal);	
		Thread.sleep(1000);
		System.out.println("NFL Roster Year text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLRosterSeasonObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLRosterSeasonObj)).sendKeys(txtNFLRosterSeasonVal); 
		Thread.sleep(1000);
		System.out.println("NFL Roster Season text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLRosterWeekObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLRosterWeekObj)).sendKeys(txtNFLRosterWeekVal); 
		Thread.sleep(1000);
		System.out.println("NFL Roster Week text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLRosterTeamObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLRosterTeamObj)).sendKeys(txtNFLRosterTeamVal); 
		Thread.sleep(1000);
		System.out.println("NFL Roster Team text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLRosterPlayerFNameObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLRosterPlayerFNameObj)).sendKeys(txtNFLRosterPlayerFNameVal); 
		Thread.sleep(1000);
		System.out.println("NFL Roster PlayFullName text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSearchNFLRosterObj)).click();
		Thread.sleep(2000);
		System.out.println("NFL Roster Search button clicked.");
		
	}
	
	public static void NFLPbp(String txtNFLpbpYearObj,String txtNFLpbpSeasonObj,String txtNFLpbpWeekObj,String txtNFLpbpQuarterNumObj,String txtNFLpbpPlayTypeObj,String btnSearchNFLpbpObj,String txtNFLpbpYearVal,String txtNFLpbpSeasonVal,String txtNFLpbpWeekVal,String txtNFLpbpQuarterNumVal,String txtNFLpbpPlayTypeVal) throws InterruptedException
	{
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLpbpYearObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtNFLpbpYearObj)).sendKeys(txtNFLpbpYearVal);	
		Thread.sleep(1000);
		System.out.println("NFL Pbp Year text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLpbpSeasonObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLpbpSeasonObj)).sendKeys(txtNFLpbpSeasonVal); 
		Thread.sleep(1000);
		System.out.println("NFL Pbp Season text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLpbpWeekObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLpbpWeekObj)).sendKeys(txtNFLpbpWeekVal); 
		Thread.sleep(1000);
		System.out.println("NFL Pbp Week text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLpbpQuarterNumObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLpbpQuarterNumObj)).sendKeys(txtNFLpbpQuarterNumVal); 
		Thread.sleep(1000);
		System.out.println("NFL Pbp Quarter Number text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNFLpbpPlayTypeObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNFLpbpPlayTypeObj)).sendKeys(txtNFLpbpPlayTypeVal); 
		Thread.sleep(1000);
		System.out.println("NFL Pbp PlayType text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSearchNFLpbpObj)).click();
		Thread.sleep(2000);
		System.out.println("NFL Pbp Search button clicked.");
		
	}
	
	public static void NBASchedules(String txtNBASchdlsSeasonIdObj,String txtNBASchdlsSeasonObj,String txtNBASchdlsPlayDayObj,String btnSearchNBASchdlsObj,String txtNBASchdlsSeasonIdVal,String txtNBASchdlsSeasonVal,String txtNBASchdlsPlaydayVal) throws InterruptedException
	{
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBASchdlsSeasonIdObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtNBASchdlsSeasonIdObj)).sendKeys(txtNBASchdlsSeasonIdVal);	
		Thread.sleep(1000);
		System.out.println("NBA Schedules SeasonId text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBASchdlsSeasonObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNBASchdlsSeasonObj)).sendKeys(txtNBASchdlsSeasonVal); 
		Thread.sleep(1000);
		System.out.println("NBA Schedules Season text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBASchdlsPlayDayObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNBASchdlsPlayDayObj)).sendKeys(txtNBASchdlsPlaydayVal); 
		Thread.sleep(1000);
		System.out.println("NBA Schedules PlayDay text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSearchNBASchdlsObj)).click();
		Thread.sleep(2000);
		System.out.println("NBA Schedules Search button clicked.");
		
	}
	
	public static void NBARoster(String txtNBARosterSeasonIdObj,String txtNBARosterSeasonObj,String txtNBARosterTeamObj,String txtNBARosterPlayerNameObj,String btnSearchNBARosterObj,String txtNBARosterSeasonIdVal,String txtNBARosterSeasonVal,String txtNBARosterTeamVal,String txtNBARosterPlayernameVal) throws InterruptedException
	{
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBARosterSeasonIdObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtNBARosterSeasonIdObj)).sendKeys(txtNBARosterSeasonIdVal);	
		Thread.sleep(1000);
		System.out.println("NBA Roster SeasonId text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBARosterSeasonObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNBARosterSeasonObj)).sendKeys(txtNBARosterSeasonVal); 
		Thread.sleep(1000);
		System.out.println("NBA Roster Season text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBARosterTeamObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNBARosterTeamObj)).sendKeys(txtNBARosterTeamVal); 
		Thread.sleep(1000);
		System.out.println("NBA Roster Team text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBARosterPlayerNameObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNBARosterPlayerNameObj)).sendKeys(txtNBARosterPlayernameVal); 
		Thread.sleep(1000);
		System.out.println("NBA Roster PlayerName text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSearchNBARosterObj)).click();
		Thread.sleep(2000);
		System.out.println("NBA Roster Search button clicked.");
		
	}
	
	public static void NBStatistics(String txtNBAstaticsSeasonIdObj,String txtNBAstaticsSeasonObj,String txtNBAstaticsTeamObj,String txtNBAstaticsPlayerFNameObj,String txtNBAstaticsPlayerLNameObj,String txtNBAstaticsPlayerIdObj,String btnSearchNBAstaticsObj,String txtNBAstaticsSeasonIdVal,String txtNBAstaticsSeasonVal,String txtNBAstaticsTeamVal,String txtNBAstaticsPlayerFNameVal,String txtNBAstaticsPlayerLNameVal,String txtNBAstaticsPlayerIdVal) throws InterruptedException
	{
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsSeasonIdObj)).clear();
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsSeasonIdObj)).sendKeys(txtNBAstaticsSeasonIdVal);	
		Thread.sleep(1000);
		System.out.println("NBA Statistics SeasonId text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsSeasonObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsSeasonObj)).sendKeys(txtNBAstaticsSeasonVal); 
		Thread.sleep(1000);
		System.out.println("NBA Statistics Season text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsTeamObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsTeamObj)).sendKeys(txtNBAstaticsTeamVal); 
		Thread.sleep(1000);
		System.out.println("NBA Statistics Team text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsPlayerFNameObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsPlayerFNameObj)).sendKeys(txtNBAstaticsPlayerFNameVal); 
		Thread.sleep(1000);
		System.out.println("NBA Statistics PlayerFullName text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsPlayerLNameObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsPlayerLNameObj)).sendKeys(txtNBAstaticsPlayerLNameVal); 
		Thread.sleep(1000);
		System.out.println("NBA Statistics PlayerLastName text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsPlayerIdObj)).clear(); 
		AdminLogin.getDriver().findElement(By.xpath(txtNBAstaticsPlayerIdObj)).sendKeys(txtNBAstaticsPlayerIdVal); 
		Thread.sleep(1000);
		System.out.println("NBA Statistics PlayId text entered.");
		
		AdminLogin.getDriver().findElement(By.xpath(btnSearchNBAstaticsObj)).click();
		Thread.sleep(2000);
		System.out.println("NBA Statistics Search button clicked.");
		
	}
	
}
