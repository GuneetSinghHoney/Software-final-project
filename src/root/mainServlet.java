package root;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.AllDatabaseMethodsToBeImplemented;
import DAO.NewAccount;
import DAO.balanceBean;
import DAO.mySql;
import DAO.userBean;

/**
 * Servlet implementation class mainServlet
 */
@MultipartConfig
@WebServlet("/root")
public class mainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("get");
		String Action = request.getParameter("action");

		//checking 
		if(Action.equals("forget"))
		{
			// Password Recovery
			 response.getWriter().println("Password Recovery");
		}
		else if(Action.equals("contact"))
		{
			RequestDispatcher disp = request.getRequestDispatcher("Main/User/contact.jsp");
			disp.forward(request, response);
		}
		else if(Action.equals("rec"))
		{
			RequestDispatcher disp = request.getRequestDispatcher("Main/Transfers/rec.jsp");
			disp.forward(request, response);
			
		}
		else if(Action.equals("signup"))
		{
			//servlet 
			
			
			System.out.println("SignUp !");
			RequestDispatcher disp = request.getRequestDispatcher("Main/signup.jsp");
			disp.forward(request, response);
			
		}
		else if(Action.equals("user"))
		{
			
		//About User
		mySql sql = mySql.getInstance();
			
		userBean currentUser = 
		sql.getUserInformation(request.getSession().getAttribute("account").toString());
			
		request.setAttribute("user", currentUser);
			
		System.out.println("user Information !");
		System.out.println(currentUser.toString());
		RequestDispatcher disp = request.getRequestDispatcher("Main/User/user.jsp");
		disp.forward(request, response);
			
		}
		else if(Action.equals("success"))
		{
			System.out.println("Successful !");
			RequestDispatcher disp = request.getRequestDispatcher("Main/Success.jsp");
			disp.forward(request, response);
		}
		else if(Action.equals("pay"))
		{
			//Pay Credit Bills.
			
			//My Account
			System.out.println("My Account check");
			
			//connecting to database and collecting the information
			//Saving, Current, Credit, Balance 
			String account = request.getSession().getAttribute("account").toString();
			try {
				mySql sql = mySql.getInstance();
				balanceBean bean = sql.getBalanceInfo(account);
				
					
			//Adding the collected data to the sessions and Request scope
			
				request.setAttribute("current", bean.getCurrent());
				request.setAttribute("saving", bean.getSaving());
				request.setAttribute("limit", bean.getLimit());
				request.setAttribute("credit", bean.getCredit());

				//Calculating the balance;
				
				double finalBalance = ((Double.parseDouble(bean.getCurrent()))+(Double.parseDouble(bean.getSaving()))) -
						Double.parseDouble(bean.getCredit());	
						
						
						System.out.println(bean.toString());
						System.out.println("final bal = "+finalBalance+"");
						
				request.setAttribute("balance", finalBalance);
			}catch (Exception e) {
			e.printStackTrace();
			}
			//Dispatching Request.
			System.out.println("Pay Bills");
			RequestDispatcher disp = request.getRequestDispatcher("Main/pay.jsp");
			disp.forward(request, response);
			
		}
		else if(Action.equals("A2A"))
		{
			//Account to Account transfer
			System.out.println("Account to Account");
			//My Account
		 
			
			//connecting to database and collecting the information
			//Saving, Current, Credit, Balance 
			String account = request.getSession().getAttribute("account").toString();
			try {
				mySql sql = mySql.getInstance();
				balanceBean bean = sql.getBalanceInfo(account);
				
					
			//Adding the collected data to the sessions and Request scope
			System.out.println(bean.toString());
				request.setAttribute("current", bean.getCurrent());
				request.setAttribute("saving", bean.getSaving());
				request.setAttribute("limit", bean.getLimit());
				request.setAttribute("credit", bean.getCredit());

				//Calculating the balance;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			RequestDispatcher disp = request.getRequestDispatcher("Main/Transfers/A2A.jsp");
			disp.forward(request, response);
			
		}
		else if(Action.equals("A2E"))
		{
			//Account to Email
			System.out.println("Account to Email");
			String account = request.getSession().getAttribute("account").toString();
			try {
				mySql sql = mySql.getInstance();
				balanceBean bean = sql.getBalanceInfo(account);
				
					
			//Adding the collected data to the sessions and Request scope
			System.out.println(bean.toString());
				request.setAttribute("current", bean.getCurrent());
				request.setAttribute("saving", bean.getSaving());
				request.setAttribute("limit", bean.getLimit());
				request.setAttribute("credit", bean.getCredit());

				//Calculating the balance;
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			RequestDispatcher disp = request.getRequestDispatcher("Main/Transfers/A2E.jsp");
			disp.forward(request, response);
			
		}
		else if(Action.equals("C2S"))
		{
			//Between Account 
			//Account to Account transfer
			System.out.println("Between Accounts");
			//My Account
		 
			
			//connecting to database and collecting the information
			//Saving, Current, Credit, Balance 
			String account = request.getSession().getAttribute("account").toString();
			try {
				mySql sql = mySql.getInstance();
				balanceBean bean = sql.getBalanceInfo(account);
				
					
			//Adding the collected data to the sessions and Request scope
			System.out.println(bean.toString());
				request.setAttribute("current", bean.getCurrent());
				request.setAttribute("saving", bean.getSaving());
				request.setAttribute("limit", bean.getLimit());
				request.setAttribute("credit", bean.getCredit());

				//Calculating the balance;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			RequestDispatcher disp = request.getRequestDispatcher("Main/Transfers/C2S.jsp");
			disp.forward(request, response);
			
		}
		else if(Action.equals("account"))
		{
			//My Account
			System.out.println("My Account check");
			
			//connecting to database and collecting the information
			//Saving, Current, Credit, Balance 
			String account = request.getSession().getAttribute("account").toString();
			try {
				mySql sql = mySql.getInstance();
				balanceBean bean = sql.getBalanceInfo(account);
				
					
			//Adding the collected data to the sessions and Request scope
			
				request.setAttribute("current", bean.getCurrent());
				request.setAttribute("saving", bean.getSaving());
				request.setAttribute("limit", bean.getLimit());
				request.setAttribute("credit", bean.getCredit());

				//Calculating the balance;
				
				double finalBalance = ((Double.parseDouble(bean.getCurrent()))+(Double.parseDouble(bean.getSaving()))) -
						Double.parseDouble(bean.getCredit());	
						
						
						System.out.println(bean.toString());
						System.out.println("final bal = "+finalBalance+"");
						
				request.setAttribute("balance", finalBalance);
			
			//Dispatching Request.
			
			RequestDispatcher disp = request.getRequestDispatcher("Main/mainPage.jsp");
			disp.forward(request, response);
			}
			//try end
			catch(Exception e)
			{
				//catch for the account info getting database connection try
				System.out.println("Error !");
			}
		}
		else if(Action.equals("transfer"))
		{
			//Transfer

			System.out.println("Transfer check");
			RequestDispatcher disp = request.getRequestDispatcher("Main/Transfer.jsp");
			disp.forward(request, response);
			
		}
		else if(Action.equals("settings"))
		{
			//Settings 

			System.out.println("Settings check");
			RequestDispatcher disp = request.getRequestDispatcher("Main/Settings.jsp");
			disp.forward(request, response);
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String Action = request.getParameter("action");

		if(Action.equals("login"))
		{
			//login checking and request forward
			
			String account = request.getParameter("account");
			String pass = request.getParameter("password");
			
			//security component
			
			account = account.trim();
			pass = pass.trim();
		
			Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
			 
		     
		      Matcher matcher = pattern.matcher(account);
		       
		      if (!matcher.matches() && account.length()<10 && pass.length() <20) {
		         // invalid string !
		    	  
		    	  
		      } else {
		         // Account number and password OK
		    	  System.out.println("checking the credentials :"+account+"   "+pass);
		    	  mySql obj = mySql.getInstance();
		  		boolean status = obj.checkLogin(account, pass);
		    	  if(status)
		    	  {
		    		  System.out.println("Logged Inn"+account);
		    		  HttpSession sees = request.getSession();
		    		  sees.setAttribute("account", account);
		    		//connecting to database and collecting the information
		  			//Saving, Current, Credit, Balance 
		  			
		    		 
		  			try {
		  				mySql sql = mySql.getInstance();
		  				balanceBean bean = sql.getBalanceInfo(account);
		  				
		  					
		  			//Adding the collected data to the sessions and Request scope
		  			
		  				request.setAttribute("current", bean.getCurrent());
		  				request.setAttribute("saving", bean.getSaving());
		  				request.setAttribute("limit", bean.getLimit());
		  				request.setAttribute("credit", bean.getCredit());

		  				//Calculating the balance;
		  				
		  				double finalBalance = ((Double.parseDouble(bean.getCurrent()))+(Double.parseDouble(bean.getSaving()))) -
		  						Double.parseDouble(bean.getCredit());	
		  						
		  						
		  						System.out.println(bean.toString());
		  						System.out.println("final bal = "+finalBalance+"");
		  						
		  				request.setAttribute("balance", finalBalance);
		  			
		  			//Dispatching Request.
		    		RequestDispatcher disp = request.getRequestDispatcher("Main/mainPage.jsp");
		  			disp.forward(request, response);
		    	  }catch(Exception e)
		  			{
		    		 e.printStackTrace(); 
		  			}
		    	  }
		    	  else {
		    		  response.getWriter().println("Login Failed");
		    	  }
		      }
			 
			
			
		}
		else if(Action.equals("signup"))
		{

			System.out.println("do Post");
			String firstName = request.getParameter("firstName").toString();
			String lastName = request.getParameter("lastName").toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = request.getParameter("dateOfBirth").toString();
			System.out.println(date);
			Date dateOfBirth = null;
					try {
						  dateOfBirth = sdf.parse(date);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			String gender = request.getParameter("gender").toString();		
			String address = request.getParameter("address").toString();
			String address2 = request.getParameter("address2").toString();
			String email = request.getParameter("email").toString();
			String phone = request.getParameter("phone").toString();
			String password = request.getParameter("password").toString();
			
			request.setAttribute("checkValues", firstName + " " + lastName + " "  
					+ dateOfBirth +" "+gender + " " + address + " "
					+ address2 + " " + email + " " + phone + " " + password	);
			System.out.println("Checkpoint");
			
			// obtains the upload file part in this multipart request
			
			InputStream inputStream = null; // input stream of the upload file
			 
			 
	        Part filePart = request.getPart("photo");
	        if (filePart != null) {
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());
	             
	            // obtains input stream of the upload file
	            inputStream = filePart.getInputStream();
	        }
	        mySql sql = mySql.getInstance();
	        String account = sql.signup(firstName, lastName, dateOfBirth, gender, address, address2, email, phone, password, inputStream);

	        //add these values to database and dispacth request ....
	        
	        if(account.equals("ohNo")) {
	        	//error
	        	System.out.println("Error !");
	        }
	        else {
	        
			RequestDispatcher disp = request.getRequestDispatcher("login/login.html");
			disp.forward(request, response);
	        }
	        
		}
		else if(Action.equals("C2S"))
		{
			// Between the Account transfer
						
			String accountToPay = "";
			String accountFromPay= "";
			BigDecimal amount;
			boolean check = false;
			//Get the From Account and the To Account
			
			accountToPay = request.getParameter("radios");
			
			if(accountFromPay.equals("saving"))
			{
				accountFromPay="current";
			}
			else
			{
				accountFromPay="saving";
			}
			
			
			//Get the amount 
		
			amount = new BigDecimal(request.getParameter("amount").toString());
		System.out.println(amount);
			
			//validate the amount
			try {
				mySql sql = mySql.getInstance();
				balanceBean bean = sql.getBalanceInfo(request.getSession().getAttribute("account").toString());
				BigDecimal amountfrom;
				if(accountFromPay.equals("saving"))
				{
					amountfrom = new BigDecimal(bean.getSaving());
				}
				else
				{
					amountfrom = new BigDecimal(bean.getCurrent());
				}
				
				if(amount.compareTo(amountfrom) == 1)
				{
					check = false;
				}
				else
				{
					check= true;
				}
			
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
						
			
			//if valid then transfer funds by calling the sql method
			
			System.out.println("Check "+check);
			if(check)
			{
				mySql sql = mySql.getInstance();
				sql.betweenAccountsTransfer(amount, accountToPay, request.getSession().getAttribute("account").toString());
				RequestDispatcher disp = request.getRequestDispatcher("/CentennialBank/root?action=success");
				disp.forward(request, response);
			}
			
			
			// else return to the same page saying error !
			else
			{
				 PrintWriter pw = response.getWriter();
				 pw.println("Error!");
				 pw.close();
				 
			}
			
		}
		else if(Action.equals("A2A"))
		{
			//Account to Account Transfer
		
			boolean hasMoney = false;
			boolean recvalidAccount = false;
			BigDecimal current=null,saving=null,credit=null;

			BigDecimal amount = new BigDecimal(request.getParameter("amount").toString());
			String selection = request.getParameter("radios").toString();
			String account = request.getSession().getAttribute("account").toString();
			String rec = request.getParameter("rec").toString();
			try {
				mySql sql = mySql.getInstance();
				balanceBean bean = sql.getBalanceInfo(account);
				
				current = new BigDecimal(bean.getCurrent());
				 saving = new BigDecimal(bean.getSaving());
				credit  = new BigDecimal(bean.getCredit());
				String error = "";
				
				
				
				//------------------checking Balance-------------------:
						
				if(selection.equals("current"))
				{
					if(amount.compareTo(current) ==1)
					{
						//Error condition
						error="Not Enough Balance in Current Account !";
					}
					else
					{
						hasMoney = true;
					}
				}
				else
				{if(amount.compareTo(saving) ==1)
				{
					//Error condition
					error="Not Enough Balance in Saving Account !";
				}
				else
				{
					hasMoney = true;
				}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			//-----------------Balance Checked ---------------------------------------
			
			//-----------------Checking If the Receiver Account number Exists-----------------
			mySql sql = mySql.getInstance();
			boolean check = sql.accountNumberCheck(rec);
			
			if(check)
			{
				recvalidAccount = true;
			}
		
			//---------------Account number checked-----------------------
			
			if(hasMoney&&recvalidAccount)
			{
				mySql sql1 = mySql.getInstance();
				System.out.println(sql1.payAccountToAccount(saving, current, selection, account, rec, amount));
			}
			
			
		}		
		else if(Action.equals("feedback"))
		{
			String feedback = request.getParameter("feedback");
			
			System.out.println(feedback);
			//add to database
			mySql sql = mySql.getInstance();
			sql.feedback(request.getSession().getAttribute("account").toString(), feedback);
			
			//response
			PrintWriter pw = response.getWriter();
			pw.println("Thankyou for the feedback, We will not change anything ~");
			
			pw.close();
			
		}
		else if(Action.equals("A2E"))
		{
			
			boolean hasMoney = false;
			 
			BigDecimal current=null,saving=null,credit=null;

			int amount = Integer.parseInt(request.getParameter("amount").toString());
		 
			String account = request.getSession().getAttribute("account").toString();
			String phone = request.getParameter("phone").toString();
			String password = request.getParameter("password").toString();
			
			
			try {
				mySql sql = mySql.getInstance();
				balanceBean bean = sql.getBalanceInfo(account);
				
				current = new BigDecimal(bean.getCurrent());
				 saving = new BigDecimal(bean.getSaving());
				credit  = new BigDecimal(bean.getCredit());
				String error = "";
				
				
				
				//------------------checking Balance-------------------:
						
			
					if(new BigDecimal(amount+"").compareTo(current) ==1)
					{
						//Error condition
						error="Not Enough Balance in Current Account !";
					}
					else
					{
						hasMoney = true;
						
					}
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			//-----------------Balance Checked ---------------------------------------
			
			if(hasMoney)
			{
				try {
					mySql sql = mySql.getInstance();
					sql.sendMoney(account, phone, password, amount);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			RequestDispatcher disp = request.getRequestDispatcher("/Centennialbank/suc.jsp");
			disp.forward(request, response);
		}
		
		else if(Action.equals("rec"))
		{
		 String phone = request.getParameter("phone");
			String password = request.getParameter("password");
			String account = request.getSession().getAttribute("account").toString();
			boolean check= false;
			try {
				mySql sql = mySql.getInstance();
			check = 	sql.receiveMoney(account, password, phone);
				
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			PrintWriter pw = response.getWriter();
			if(check) {
			pw.print("<h1>MONEY RECEIVED</h1>");
			pw.close();
			}else {
				pw.print("<h1>ERROR ! CONTACT NEAREST BRANCH OR CALL OUR CUSTOMER HELPLINE</h1>");
				pw.close();
			}
		}
		
		else if(Action.equals("paybill"))
		{
			//Pay Credit card bill

			BigDecimal amount = new BigDecimal(request.getParameter("amount").toString());
			String selection = request.getParameter("radios").toString();
			String account = request.getSession().getAttribute("account").toString();
			try {
				mySql sql = mySql.getInstance();
				balanceBean bean = sql.getBalanceInfo(account);
				
				BigDecimal current = new BigDecimal(bean.getCurrent());
				BigDecimal saving = new BigDecimal(bean.getSaving());
				BigDecimal credit  = new BigDecimal(bean.getCredit());
				String error = null;
				
						
				if(selection.equals("current"))
				{
					if(amount.compareTo(current) ==1)
					{
						//Error condition
						error="Not Enough Balance in Current Account !";
					}
					else
					{
						//paybill
						boolean check = sql.paybill("current", current.subtract(amount)+"",credit.subtract(amount)+"" , account);
						System.out.println("Amount paid"+amount);
					}
				}
				else
				{if(amount.compareTo(saving) ==1)
				{
					//Error condition
					error="Not Enough Balance in Saving Account !";
				}
				else
				{
					//paybill
					boolean check = sql.paybill("saving", saving.subtract(amount)+"",credit.subtract(amount)+"" , account);
					System.out.println("Amount paid "+amount);
		
				}
					if(error==null)
					{
						RequestDispatcher disp = request.getRequestDispatcher("/Centennialbank/root?action=Account");
						disp.forward(request, response);
					}
					else
					{
						request.setAttribute("error", error);
						RequestDispatcher disp = request.getRequestDispatcher("/CentennialBank/root?action=pay");
						disp.forward(request, response);
					}
					
				}
				
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
			
		}
	
	}

}
