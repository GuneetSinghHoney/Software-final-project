package DAO;

public interface AllDatabaseMethodsToBeImplemented
{
	//Login - Checking 
	public boolean checkLogin(String AccountNumber, String Password);
	
	//Getting Information
	public balanceBean getBalanceInfo(String AccountNumber);

}
