package DAO;

import java.sql.*;


public class mySql implements AllDatabaseMethodsToBeImplemented {

	//Creating the class Singleton
	private static mySql myInstance = null;
	 	private Connection conn = null;
	    private Statement stmt = null;
	
	public static mySql getInstance()
	{
		//checking if the object is null
		if(myInstance == null)
		{
			myInstance = new mySql();
			
		}
	
		return myInstance;
		
	}

	//mySql Credentials
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/bank";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "1255";
	//constructor;
	  


		mySql()
	   {
	    	System.out.println("Running");
		   try{
			      //STEP 2: Register JDBC driver
			     Class.forName("com.mysql.jdbc.Driver");

			     
			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);

			      
		   }catch(Exception e)
		   {
			   e.printStackTrace();
		   }
	   }
	   
	@Override
	public boolean checkLogin(String AccountNumber, String Password)
	{
		try {
			 conn=  DriverManager.getConnection(DB_URL,USER,PASS);
			
			
			stmt = conn.createStatement();
		 
		ResultSet rs = 	stmt.executeQuery("SELECT * FROM bank.login where username='"+AccountNumber+"'AND pass='"+Password+"'");
		System.out.println("11");
			
		if(rs.next())
		{
			return true;
		}
		else
		{
			return false;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public balanceBean getBalanceInfo(String AccountNumber) {
		
		try {
			 conn=  DriverManager.getConnection(DB_URL,USER,PASS);
			
			
			stmt = conn.createStatement();
		 
		ResultSet rs = 	stmt.executeQuery("SELECT * FROM bank.balance where account= '"+AccountNumber+"'");
		System.out.println("getting balance from database");
		

		rs.next();
		
		balanceBean bal = new balanceBean();
		
		bal.setAccount(AccountNumber);
		bal.setCredit(rs.getString("credit"));
		bal.setCurrent(rs.getString("current"));
		bal.setLimit(rs.getString("limit"));
		bal.setSaving(rs.getString("saving"));

		
		return bal;
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean paybill(String savingOrCurrent, String AccountAmount,String CreditAmount, String accountNumber) {
		 
		try {
			String query = "";
			if(savingOrCurrent.equals("saving"))
			{
				query = "update bank.balance set saving='"+AccountAmount+"', credit='"+CreditAmount+"' where account='"+accountNumber+"'";
			}
			else 
			{
				query = "update bank.balance set current='"+AccountAmount+"', credit='"+CreditAmount+"' where account='"+accountNumber+"'";

			}
			
		
			 conn=  DriverManager.getConnection(DB_URL,USER,PASS);
			 stmt = conn.createStatement();
			 return stmt.execute(query);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean makeTransection(String accountNumberFrom, String accountNumberToo, String amount, Date date) {
		// TODO Auto-generated method stub
		return false;
	}

}
