package DAO;

import java.math.BigDecimal;

public class check
{
	public static void main(String args[])
	{
		mySql obj = mySql.getInstance();
	//	boolean check = obj.payAccountToAccount(new BigDecimal("300"), new BigDecimal("500"),"saving", "11111", "12345", new BigDecimal("100"));
		
		boolean check = obj.betweenAccountsTransfer(new BigDecimal("200"), "saving", "12345");
		System.out.println(check);
	 
		
	}

}
