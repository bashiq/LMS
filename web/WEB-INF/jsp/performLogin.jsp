<%-- 
    Document   : performLogin
    Created on : Nov 7, 2017, 10:17:37 PM
    Author     : Bilal
--%>

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

        <form class="LoginControllerBean">
            <div class="logimgcontainer">
                <img src="images/caplogo.jpg" alt="Capitol Technology University" class="logo">
            </div>

            <div class="loginContainer">
                <label><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="username" required class="loginform">

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required class="loginform">

                <button type="submit" class="loginform">Login</button>
            </div>
        </form>
    </body>
</html>
