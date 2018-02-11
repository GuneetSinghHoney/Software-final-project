package root;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.AllDatabaseMethodsToBeImplemented;
import DAO.balanceBean;
import DAO.mySql;

/**
 * Servlet implementation class mainServlet
 */
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
			RequestDispatcher disp = request.getRequestDispatcher("Main/Transfers/A2A.jsp");
			disp.forward(request, response);
			
		}
		else if(Action.equals("A2E"))
		{
			//Account to Email
			System.out.println("Account to Email");
			RequestDispatcher disp = request.getRequestDispatcher("Main/Transfers/A2E.jsp");
			disp.forward(request, response);
			
		}
		else if(Action.equals("C2S"))
		{
			//Between Account 
			System.out.println("Account to other Account");
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
			
			RequestDispatcher disp = request.getRequestDispatcher("Main/Account.jsp");
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
		    		RequestDispatcher disp = request.getRequestDispatcher("Main/mainPage.jsp");
		  			disp.forward(request, response);
		    	  }
		    	  else {
		    		  response.getWriter().println("Login Failed");
		    	  }
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
