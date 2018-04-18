package DAO;

import java.io.InputStream;
import java.math.BigDecimal;
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
	
	//Check if the Account number exists
	public boolean accountNumberCheck(String AccountNumber);
	
	//Pay Account to Account
	public boolean payAccountToAccount(BigDecimal saving, BigDecimal current, String AccountType, String accountNumberFrom, String accountNumberToo, BigDecimal amount);

	//Pay Between Accounts
	public boolean betweenAccountsTransfer(BigDecimal amount, String AccountToPay,String AccountNumber);
	
	//Sign Up new Account
	String signup(String firstName, String lastName, java.util.Date dateOfBirth, String gender, String address,
			String address2, String email, String phone, String password, InputStream photo);
	
	//get user information
	userBean getUserInformation(String accountNumber);
	
	//add feedback
	void feedback(String account, String feedback);
	
	//send intrac
	void sendMoney(String account, String phone, String password, int amount);
	
	//receive money 
	boolean receiveMoney(String account, String password, String phone);
	
}
