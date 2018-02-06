package DAO;

public class check
{
	public static void main(String args[])
	{
		mySql obj = mySql.getInstance();
		boolean check = obj.checkLogin("12345", "12345");
		
		System.out.println(check);
		
	}

}
