<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Account</title>
</head>
<body>
Create Account
<form method="post" action="/CentennialBank/root?action=signup" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>First Name </td>
                    <td><input type="text" name="firstName" size="20" required/></td>
                </tr>
                <tr>
                    <td>Last Name </td>
                    <td><input type="text" name="lastName" size="20" required/></td>
                </tr>
                <tr>
                    <td>Date of Birth </td>
                    <td><input type="date" name="dateOfBirth" min="1900-01-01" max='2018-01-01' required/></td>
                </tr>
                <tr>
                	<td>Gender </td>
                	<td> 
                	<input type="radio" name="gender" value="Male" checked> Male<br>                	
  					<input type="radio" name="gender" value="Female"> Female<br></td>
                </tr>
                <tr>
                    <td>Address </td>
                    <td><input type="text" name="address" size="30" required/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="text" name="address2" size="30" required/></td>
                </tr>
                <tr>
                    <td>Email </td>
                    <td><input type="email" name="email" size="50" placeholder="Enter your email" required/></td>
                </tr>
                <tr>
                    <td>Phone Number</td>
                    <td><input type="tel" name="phone" required placeholder="647-111-1111" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}"/></td>
                </tr>
                <tr>
                    <td>Photo ID </td>
                    <td><input type="file" name="photo" size="50" required/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" size="25" id="password" required/></td>
                </tr>
                 <tr>
                    <td>Confirm Password</td>
                    <td><input type="password" name="confirmPassword" size="25" id="confirm_password" required/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Save">
                    </td>
                </tr>
            </table> </form>
</body>
</html>

<script type="text/javascript">
		var password = document.getElementById("password")
		, confirm_password = document.getElementById("confirm_password");
		
		function validatePassword(){
		if(password.value != confirm_password.value) {
		  confirm_password.setCustomValidity("Passwords Don't Match");
		} else {
		  confirm_password.setCustomValidity('');
		}
		}
		
		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;

</script>