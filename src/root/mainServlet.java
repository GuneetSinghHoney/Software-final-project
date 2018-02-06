package root;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AllDatabaseMethodsToBeImplemented;
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
		
		//checking 


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
		    		  response.getWriter().println("Login Successfull");
		    	  }
		    	  else {
		    		  response.getWriter().println("Login Failed");
		    	  }
		      }
			 
			
			
		}
		else if(Action.equals("forget"))
		{
			// Password Recovery
			 response.getWriter().println("Password Recovery");
		}
		
	}

}
