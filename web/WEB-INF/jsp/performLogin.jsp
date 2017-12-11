<%-- 
    Document   : performLogin
    Created on : Nov 7, 2017, 10:17:37 PM
    Author     : Bilal
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="./css/main.css" rel="stylesheet" type="text/css"/>
        <title>Login</title>
    </head>
    <body>
        <h2>Login Form</h2>


        <div class="logimgcontainer">
            <img src="images/caplogo.jpg" alt="Capitol Technology University" class="logo">
        </div>

        <br />
        <h4><font color="red">${invalidLogin}</font></h4>
            <c:remove var="message" scope="session" /> 

        <div class="loginContainer">
            <form class="login.htm" method = "POST">
                <label><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="username" required class="loginform">

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required class="loginform">
                <input type ="hidden" name ="action" value ="login">

                <button type="submit" class="loginform">Login</button>
            </form>
        </div>

    </body>
</html>
