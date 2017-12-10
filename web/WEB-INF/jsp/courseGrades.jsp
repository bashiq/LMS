<%-- 
    Document   : courseGrades
    Created on : Nov 8, 2017, 1:04:59 AM
    Author     : Bilal
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dao.TAssign"%>
<%@page import="orm.TUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
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
                <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">Grades</h3>
            </div>

            <!--Conditional statement for professor vs student view-->
            <%
                TUser tu = (TUser) session.getAttribute("tUser");
            %>
            <% if (tu.getRoleId() == 2) {%>


            <c:forEach items="${ta.getAssignment()}" var="assign">
                <p>${assign.getAssignmentName()}</p>
                <table>

                    <tr>
                        <th>Student Name</th>
                        <th>Submission</th>
                        <th>Grade<br />${assign.getPotentialScore()}</th>
                    </tr>

                    <c:forEach items="${ta.getTassign()}" var="tuse" varStatus = "loopCount" >
                        <tr>
                            <td>${tuse.gettUser().getUserName()}</td>
                            <td>${tuse.getUserAssign().getUserAssignmentSubmission()}</td>
                            <td><form> <input type="text" size="3" name ="" value ="${tuse.getUserAssign().getScore()}" > <input type="submit" value="update"></form></td--%>

                        </tr>
                    </c:forEach>

                </table>
                <br /><br />
            </c:forEach>



            <!--Table user grades goes here-->
            <%} else {%>
            <%
                ArrayList<TAssign> tassign = (ArrayList<TAssign>) session.getAttribute("tassign");
                session.removeAttribute("tassign");
                int i = 0;
                System.out.println("his "+ tassign.get(i).getUserAssign().getScore());
            %>

            <table>

                <tr>
                    <th>Name</th>
                    <th>Score</th>
                    <th>Out of</th>
                </tr>
                <c:forEach items="${ta.getAssignment()}" var="value">
                    <tr>
                        <td>${value.getAssignmentName()}</td>
                        <td><%= tassign.get(i).getUserAssign().getScore() %></td>
                        <td>${value.getPotentialScore()}</td>
                    </tr>
                    <% i++;%>
                </c:forEach>
                 <tr>
                    <td colspan="2"> Total: <c:out value="${cart.getCartOrderTotal()}"/></td>
                    <td>${blah}900/1000</td>
                </tr>
            </table>
            <%}%>
        </div>
    </body>
</html>
