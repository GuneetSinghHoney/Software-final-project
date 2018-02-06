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

}
