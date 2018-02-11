package DAO;

import java.sql.Date;

public interface AllDatabaseMethodsToBeImplemented
{
	//Login - Checking 
	public boolean checkLogin(String AccountNumber, String Password);
	
	//Getting Information
	public balanceBean getBalanceInfo(String AccountNumber);

	//Pay credit card bill
	public boolean paybill(String savingOrChecking,String Amount,String CreditAmountToBePaid, String accountNumber);
	
	//Create a transaction for security and audit
	public boolean makeTransection(String accountNumberFrom, String accountNumberToo, String amount , Date date);
}
