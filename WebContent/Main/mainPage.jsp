<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Centennial Bank</title>


</head>
<body>
<%@include file="header.jsp" %>
<div id="layer5" style="position: absolute; width: 750px; height: 379px; z-index: 2; left: 138px; top: 128px; background-image:url('background.png')">
<!-- #BeginEditable "content" -->

	<%
//Getting the data from the servlet.
HttpSession s =(HttpSession) request.getSession();
String AccountNumber = s.getAttribute("account").toString();

String balance = request.getAttribute("balance").toString();
String saving = request.getAttribute("saving").toString();
String current = request.getAttribute("current").toString();
String credit = request.getAttribute("credit").toString();

%>
 

<h1>Account Information:</h1>

<table style="margin-left: 200px">

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
</div>
</body>
</html>