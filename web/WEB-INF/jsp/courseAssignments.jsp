<%-- 
    Document   : courseAssignments
    Created on : Nov 8, 2017, 1:04:22 AM
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

                <a href="#"><h3 class="w3-wide"><b>LMS</b></h3></a>
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
                <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">Assignments</h3>
            </div>
            <%
                TUser tu = (TUser)session.getAttribute("tUser");
            %>
            <!--Conditional statement for professor vs student view-->
            <%if (tu.getRoleId() == 2) {%><!-- if teacher create assignment button-->
            <form> 
                <input type="submit" value="Create new Assignment">
            </form>
            <% }%>
            <br /><br />
            <table>

                <c:forEach items="${retAssignments}" var="value">

                    <tr>
                        <td>
                            <form action ="#">
                                ${value.getAssignmentName()}<br />
                                Graded yet: ${value.getStringIsGraded()} &nbsp; | ${value.getPotentialScore()} potential points
                                <input type="hidden" name = "assignmentObj" value="goto blah">
                                <input type="hidden" name = "action" value="viewAnAssignment">
                                <input type="hidden" name = "action" value="${value.getAssignmentId()}">
                            </form>
                        </td>

                    </tr>
                </c:forEach>
            </table>
            <br /><br />

        </div>
    </body>
</html>