<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pay Bills</title>
</head>
<body>
<%
//Getting the data from the servlet.
HttpSession s =(HttpSession) request.getSession();
String AccountNumber = s.getAttribute("account").toString();

String balance = request.getAttribute("balance").toString();
String saving = request.getAttribute("saving").toString();
String current = request.getAttribute("current").toString();
String credit = request.getAttribute("credit").toString();

%>

<form action="/CentennialBank/root?action=paybill" method="post">

<table>
<tr>
<td>
Credit Card Outstanding Amount
</td>
<td>
    <%=credit%>
</td>
</tr>

<tr>
<td>
The Account to make payment:
</td>
<td>
  <input type="radio" name="radios" value="current"/> Current - ($<%=current%>) 
 <input type="radio" name="radios" value="saving"/> Saving - ($<%=saving%>)  
</td>
</tr>

<tr>
<td>
The Amount to be paid:
</td>
<td>
<input type="text" id="amount" name="amount">
</td>
</tr>

<tr>
<td>
<input type="submit" id="submit">
</td>
</tr>
</table>

</form>
<script type="text/javascript">
var init = function(){

	console.log("checking !");
	var amount = Document.myform.amount.innerHTML;
	var button = Document.getElementById("submit");
	if(amount><%=saving%> & amount><%=current%>)
		{
		button.disable = true;		
		}
	else
		{
		button.disable = false;
		}
}
 

</script>

<!-- Error List -->
<%
String error = "";
try{
	error = request.getAttribute("error").toString();
	if(error.equals("null"))
	{
		error = "";
	}
}catch(Exception e){}%>
}
<h2 style="background-color: red;color: white;"><%=error%></h2>
</body>
</html>