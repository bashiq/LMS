<%-- 
    Document   : viewAssignment
    Created on : Nov 8, 2017, 10:28:43 PM
    Author     : Bilal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link href="./css/main.css" rel="stylesheet" type="text/css"/>
        <title>People</title>
    </head>
    <body class="w3-content" style="max-width:1200px">

        <!-- Sidebar/menu -->
        <nav class="w3-sidebar w3-bar-block w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidebar">
            <div class="w3-container w3-display-container w3-padding-16">

                <a href=""><h3 class="w3-wide"><b>LMS</b></h3></a>
            </div>
            <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
                <a href="#" class="w3-bar-item w3-button">Assignments</a>
                <a href="#" class="w3-bar-item w3-button">Grades</a>               
                <a href="#" class="w3-bar-item w3-button">People</a>
            </div>
            <a href="" class="w3-bar-item w3-button w3-padding">Log Off</a> 
        </nav>


        <div class="w3-main" style="margin-left:250px">
            <!-- Project Section -->
            <div class="w3-container w3-padding-32" id="projects">
                <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">Assignment 1</h3>
                <!-- if student-->
                <% if(false){%>
                <h4>due blah</h4>
                <h4>points</h4>
                <h4>Assignment description</h4>
                
                <form> 
                <input type="submit" value="Submit Assignment"></form>
                <%} else{%>
                    <form action="/action_page.php">
  Assignment name:<br>
  <input type="text" name="aname" value="">
  <br>
  Description:<br>
  <input type="text" name="desc" value="">
   <br>
  Points:<br>
  <input type="text" name="outof" value="">
   <br>
  Due:<br>
  <input type="text" name="due" value="">
  <br><br>
  <input type="submit" value="Submit">
</form>
                <%}%>
            </div>

        </div>
    </div>
</body>
