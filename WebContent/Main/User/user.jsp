<%@page import="DAO.userBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">

td{
margin-left: 20px;
}

</style>
</head>
<body>
<%@include file="header.jsp" %>
<div id="layer5" style="position: absolute; width: 750px; height: 379px; z-index: 2; left: 138px; top: 128px; background-image:url('background.png')">
<!-- #BeginEditable "content" -->

<%  userBean user = (userBean) request.getAttribute("user");
%>

<table>
<tr><td>ACCOUNT NUMBER </td><td><%=user.getAccount() %></td></tr>

<tr><td>Address</td> <td><%=user.getAddress1()%></td></tr> 

<tr><td>ZIP CODE</td> <td><%=user.getAddress2()%></td></tr>

<tr><td>Email</td> <td><%=user.getEmail()%></td></tr>

<tr><td>First Name</td> <td><%=user.getFirstName()%></td>

<tr> <td>Gender</td> <td><%=user.getGender() %></td></tr>

<tr><td>Last Name</td><td><%=user.getLastName() %></td></tr>

<tr><td>Phone Number</td> <td><%=user.getPhone() %></td></tr>

<tr><td>Date Of Birth</td><td> <%=user.getDob() %></td></tr>
</table>
</div>
</body>
</html>