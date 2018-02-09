<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
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
	<img hspace="60" src="Resources/Images/goBackButton.jpg" width="100"/>

<h1>Account Information:</h1>

<table>
<tr>
<td>
Saving 
</td>
<td>
<%=saving%>
</td>
</tr>

<tr>
<td>
Current
</td>
<td>
<%=current%>
</td>
</tr>


<tr>
<td>
Credit
</td>
<td>
-<%=credit%>
</td>
</tr>



<tr>
<td>
Balance
</td>
<td>
<b><%=balance%></b>
</td>
</tr>


</table>

</body>
</html>