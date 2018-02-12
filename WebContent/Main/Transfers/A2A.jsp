<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A2A</title>
</head>
<body>
<%
//Getting the data from the servlet.
 
HttpSession s =(HttpSession) request.getSession();
String AccountNumber = s.getAttribute("account").toString();

String saving = request.getAttribute("saving").toString();
String current = request.getAttribute("current").toString();
 
 
%>

<form action="/CentennialBank/root?action=A2A" method="post">

<table>
<tr>
<td>
Enter Receiver's Account Number: 
</td>
<td>
   <input type="text" name="rec">
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

</body>
</html>