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
            Course <br/> 
            <c:forEach items="${requestScope.courses}" var="c">
                ${c.name}
                <input type="button" value="View Grade" onclick="window.location.href='grade?cid=${c.id}'"/>
                <input type="button" value="Exam Schedule" onclick="window.location.href='examschedule?cid=${c.id}'"/>
                <br/>
            </c:forEach>
                
            
        </form>
    </body>
</html>
