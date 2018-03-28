package DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

public class NewAccount {
	static String dbName = "mikecompproj";
	static String dbUserName = "mikecompproj";
	static String dbPassword = "123456";
	static String connectionString = "jdbc:mysql://mysql8.db4free.net:3307/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword;
	static String driverName = "com.mysql.jdbc.Driver";
	
	
	public static void newAccount(String firstName, String lastName, Date dateOfBirth,String gender,
			String address, String address2, String email, String phone, String password,
			InputStream photo ){
		
		System.out.println("New User");	
		
		//Insert to database
		try {
			Class.forName(driverName);
			 // Step 1: Allocate a database 'Connection' object
	         Connection conn = DriverManager.getConnection(connectionString);			
	               // MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"					
	         // Step 2: Allocate a 'Statement' object in the Connection
	         Statement stmt = conn.createStatement();
			 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `User`"
			 		+ "(`FirstName`, `LastName`, `DateOfBirth`,`Gender`,"
			 		+ "`Address1`,`Address2`,`Email`,`Phone`,`ID`,`Password`) "
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
			 conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
