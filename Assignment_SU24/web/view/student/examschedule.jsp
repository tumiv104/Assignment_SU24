<%-- 
    Document   : examschedule
    Created on : Jul 11, 2024, 3:29:34 PM
    Author     : Nitro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="sexamschedule" method="POST">
            <table border="1px">
                <tr>
                    <th>SUBJECT NAME</th>
                    <th>WEIGHT</th>
                    <th>DATE</th>
                    <th>DURATION</th>
                </tr>
                <c:forEach items="${requestScope.exams}" var="e">
                    <tr>
                    <td>${e.assessment.name}</td>
                    <td>${e.assessment.weight}</td>
                    <td>${e.date}</td>
                    <td>${e.duration} minutes</td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
