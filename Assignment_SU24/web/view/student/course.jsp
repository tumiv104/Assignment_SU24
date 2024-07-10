<%-- 
    Document   : course.jsp
    Created on : Jul 9, 2024, 11:47:13 AM
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
        <form action="student" method="POST"> 
            Course <select name="cid">
                <c:forEach items="${requestScope.courses}" var="c">
                    <option value="${c.id}">${c.name}</option>
                </c:forEach>
            </select>
            <input type="button" value="View Grade" onclick="window.location.href='grade?cid=${c.id}'"/>
            <input type="button" value="Exam Schedule" onclick="window.location.href='examschedule?cid=${c.id}'"/>
        </form>
    </body>
</html>
