<%-- 
    Document   : viewAssignment
    Created on : Nov 8, 2017, 10:28:43 PM
    Author     : Bilal
--%>

<%@page import="orm.TUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">${assignment.getAssignmentName()} </h3>

                <%
                    TUser tu = (TUser) session.getAttribute("tUser");
                %>
                <!-- if student-->
                <% if (tu.getRoleId() == 1) {%>
                <p><b>Points</b> ${assignment.getPotentialScore()}</p>
                <p><b>Submitting</b> in text box below</p>
                <p>${assignment.getAssignmentDescription()} </p>


                <form action ="#"> 
                    <textarea rows="8" cols="70" name="desc"></textarea>
                    <br /><br />
                    <input type="submit" value="Submit Assignment">
                </form>
                <%} else {%>
                <form action="#">
                    Assignment name:<br />
                    <input type="text" name="assignmentName" value="${assignment.getAssignmentName()}" />
                    <br />
                    Description:<br />
                    <textarea rows="5" cols="70" name="description">${assignment.getAssignmentDescription()}
                    </textarea>
                    <br />
                    Points:<br/>
                    <input type="text" name="potentialScore" value="${assignment.getPotentialScore()}">
                    <br />
                    <!--Due:<br/>
                    <input type="text" name="due" value=""-->
                    <br /><br />
                    <input type="submit" value="Submit">
                </form>
                <%}%>
            </div>

        </div>
    </div>
</body>
