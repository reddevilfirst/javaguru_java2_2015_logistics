<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  if (((String) session.getAttribute("userType")) != null)
    response.sendRedirect("../jsp/userProfile.jsp");
%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../style.css">
    <title>Login page</title>
</head>
<body>
<div align="center"><img src="../images/indexlogo.jpg"/></div>
<hr />
<div align="right"><a href="../index.html">BACK</a></div>
<hr />
<div align="center">
    <form method="post" action="userLogin">
        Login:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="login" /><br/>
        <br/>
        Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="password" /><br/>
        <br/>
        <input type="submit" value="login" />
    </form>
</div>
</body>
</html>