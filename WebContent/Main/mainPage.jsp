<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Centennial Bank</title>

<style type="text/css">

#menu ul
{
    margin: 0px;
    padding: 0px;
    list-style-type: none;
}

#menu li
{
    list-style: none;
}

#menu a
{
    display: block;
    width: 8em;
    color: white;
    background-color: #000099;
    text-decoration: none;
    text-align: center;
}

#menu a:hover
{
    background-color: #6666AA;
}

#menu li
{
    list-style: none;
    float: left;
    margin-right: 0.5em;
}



</style>

</head>
<body>
<div id="menu">
    <ul>
        <li><a href="/CentennialBank/root?action=account">-My Account-</a></li>
        <li><a href="/CentennialBank/root?action=user">-User-</a></li>
        <li><a href="/CentennialBank/root?action=transfer">-Transfers-</a></li>
        <li><a href="/CentennialBank/root?action=settings">-Settings-</a></li>
    </ul>
</div>
</body>
</html>