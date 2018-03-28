package DAO;

import java.io.InputStream;
import java.math.BigDecimal;
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
	
	/* static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static String dbName = "bank";
		static String USER = "bank";
		static String PASS = "123456";
		static String DB_URL = "jdbc:mysql://mysql8.db4free.net:3307";
		static String driverName = "com.mysql.jdbc.Driver";*/


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

	@Override
	public boolean accountNumberCheck(String AccountNumber) {

		try {
			 conn=  DriverManager.getConnection(DB_URL,USER,PASS);
			 stmt = conn.createStatement();
			 ResultSet rs = 	stmt.executeQuery("SELECT * FROM bank.login where username='"+AccountNumber+"'");
				System.out.println("account number checking");
					
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
	public boolean payAccountToAccount(BigDecimal saving,BigDecimal current, String type, String accountNumberFrom, String accountNumberToo, BigDecimal amount)
	{
		try {
			 conn=  DriverManager.getConnection(DB_URL,USER,PASS);
			 stmt = conn.createStatement();
			 System.out.println("rs");
			 ResultSet rs = stmt.executeQuery("SELECT current FROM bank.balance where account ='"+accountNumberToo+"'");
			 rs.next();
			 BigDecimal num = new BigDecimal(rs.getString(1).toString());
			  System.out.println(num);
			 num = num.add(amount);
			 
			 stmt.executeUpdate("update bank.balance set current='"+num.toString()+"' where account='"+accountNumberToo+"'");
			 
			 Statement st2 = stmt;
			 if(type.equals("saving")) {
				 num = saving.subtract(amount);
				 System.out.println(num);
			 st2.executeUpdate("update bank.balance set saving='"+num.toString()+"' where account= '"+accountNumberFrom+"'");
			 }
			 else {

			 num = current.subtract(amount);
			 st2.executeUpdate("update bank.balance set current='"+num.toString()+"' where account= '"+accountNumberFrom+"'");
				 
			 }
			 
			 return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		 
		return false;
	}

	@Override
	public boolean betweenAccountsTransfer(BigDecimal amount, String AccountToPay, String AccountNumber) {
	
		try {
			 conn=  DriverManager.getConnection(DB_URL,USER,PASS);
			 stmt = conn.createStatement();
			 System.out.println("rs");
			 
			 ResultSet rs;
			 BigDecimal saving,current;
			 
			 rs = stmt.executeQuery("SELECT saving,current FROM bank.balance where account ='"+AccountNumber+"'");
			 rs.next();
			 saving = new BigDecimal(rs.getString(1).toString());
			 current = new BigDecimal(rs.getString(2).toString());
			 System.out.println(saving +"   "+current);
			 if(AccountToPay.equals("current"))
			 {
				 current = current.add(amount);
				 saving = saving.subtract(amount);
			 }
			 else{
				 saving = saving.add(amount);
				 current = current.subtract(amount);
			 }

			 System.out.println(saving +"   "+current);
			 stmt.executeUpdate("update bank.balance set current='"+current.toString()+"',saving='"+saving.toString()+"' where account='"+AccountNumber.toString()+"'");
			 stmt.close();
			 return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String signup(String firstName, String lastName, java.util.Date dateOfBirth, String gender, String address,
			String address2, String email, String phone, String password, InputStream photo) {

		
		System.out.println("New User");	
		
		//Insert to database
		try {
			 conn=  DriverManager.getConnection(DB_URL,USER,PASS);
			 
	         stmt = conn.createStatement();
	         
	         //adding data to Usertable table
	         
			 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `User`"
			 		+ "(`FirstName`, `LastName`, `DateOfBirth`,`Gender`,"
			 		+ "`Address1`,`Address2`,`Email`,`Phone`,`photo`,`Password`) "
			 		+ " VALUES (?,?,?,?,?,?,?,?,?,?)");
			 //Generate Random account number
			 /*Random rand = new Random();
			 Integer n = rand.nextInt(9999998) + 1;
			 String account = n.toString();
			 pstmt.setString(1,account ); .*/
			 
			 //Set variables in prepared statement
			 pstmt.setString(1, firstName);
			 pstmt.setString(2, lastName); 
			 
			 //convert util date to sql date
			 java.sql.Date sqlDate = new java.sql.Date(dateOfBirth.getTime());
			 pstmt.setDate(3, sqlDate);
			 pstmt.setString(4, gender);
			 pstmt.setString(5, address);
			 pstmt.setString(6, address2);
			 pstmt.setString(7, email);
			 pstmt.setString(8, phone);
			 pstmt.setBlob(9, photo);
			 pstmt.setString(10, password);
			 
			 //Execute Insert
			 System.out.println(pstmt.toString());
			 pstmt.executeUpdate();
			 System.out.println("execute");
			 
			 pstmt.close();
			 //Getting account number
			 pstmt = conn.prepareStatement("select account from `User` where"
			 		+ "`FirstName`=? && `phone`=? && `Email`=? && `LastName`=?");
				 	
				  pstmt.setString(1, firstName);
				  pstmt.setString(2, phone);
				  pstmt.setString(3, email);
				  pstmt.setString(4, lastName);
				  
				 ResultSet rs = pstmt.executeQuery();
				 rs.next();
				 String accountNumber = String.valueOf(rs.getInt(1));
				 
				 System.out.println("Account Number :" + accountNumber);
			 //Adding vales to login table
			 
			 addLogin(accountNumber,password);
			 addBalance(accountNumber);  
			 return accountNumber;
			 
			 
		}   catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "ohNos";
	}

	private void addBalance(String accountNumber) 
	{
		
	 
		String zero = "0";
		 

		try {
			conn=  DriverManager.getConnection(DB_URL,USER,PASS);
			 
	         stmt = conn.createStatement();
	         
	         //adding data to Usertable table
	         
			 PreparedStatement ps = conn.prepareStatement("INSERT INTO `balance`"
			 		+ "(`account`, `saving`, `current`,`credit`,"
			 		+ "`limit`) "
			 		+ " VALUES (?,?,?,?,?)");
			
			 ps.setString(1, accountNumber);
			 ps.setString(2, zero);
			 ps.setString(3, zero);
			 ps.setString(4, zero);
			 ps.setString(5, zero);
			 
			 ps.executeUpdate();
			System.out.println("balance updated !"+ps.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
	}

	private void addLogin(String accountNumber, String password)
	{
		String query = "insert into bank.login values('"+accountNumber+"','"+password+"')";

		try {
			conn=  DriverManager.getConnection(DB_URL,USER,PASS);
			  stmt = conn.createStatement();
			  
			  stmt.execute(query);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
      
		
	}

	@Override
	public userBean getUserInformation(String accountNumber) {
		try {
			conn=  DriverManager.getConnection(DB_URL,USER,PASS);
			 
	        stmt = conn.createStatement();
		
	        String query = "select * from User where account ='"+accountNumber+"'";
	        
	        ResultSet rs =  stmt.executeQuery(query);
	        
	        rs.next();
	        
	        userBean user = new userBean();
	        user.setAccount(accountNumber);
	        user.setAddress1(rs.getString(rs.findColumn("Address1")));
	        user.setAddress2(rs.getString(rs.findColumn("Address2")));
	        user.setDob(rs.getDate(rs.findColumn("DateOfBirth")));
	        user.setEmail(rs.getString(rs.findColumn("Email")));
	        user.setFirstName(rs.getString(rs.findColumn("FirstName")));
	        user.setGender(rs.getString(rs.findColumn("Gender")));
	        user.setLastName(rs.getString(rs.findColumn("LastName")));
	        user.setPhone(rs.getString(rs.findColumn("Phone")));
	        
	        
	        return user;
	        
	        
	        
	        
		
		}catch(Exception e)
		{
			
		}
		return null;
	}

	@Override
	public void feedback(String account, String feedback) {
		 
			String query = "insert into bank.feedback values('"+account+"','"+feedback+"')";
		
			try {
				conn=  DriverManager.getConnection(DB_URL,USER,PASS);
				  stmt = conn.createStatement();
				  
				  stmt.execute(query);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	 

}
