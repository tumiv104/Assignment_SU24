<%-- 
    Document   : grade
    Created on : Jul 10, 2024, 2:54:19 PM
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
        <form action="grade" method="POST">
            <table border="1px">
                <tr>
                    <c:forEach items="${requestScope.exams}" var="e">
                        <th>
                            ${e.assessment.name}_${e.assessment.weight}
                        </th>
                    </c:forEach>
                        <th>Average</th>
                        <th>Status</th>
                <tr/>
                <tr>
                    <c:forEach items="${requestScope.exams}" var="e">
                        <td>
                                   <c:forEach items="${requestScope.grades}" var="g">
                                       <c:if test="${g.exam.id eq e.id}">
                                       ${g.score}
                                       </c:if>
                                   </c:forEach>
                        </td>
                    </c:forEach>
                        <td>${requestScope.avg}</td>
                        <td>${requestScope.status}</td>
                </tr>
            </table>
        </form>
        <input type="button" value="Back" onclick="javascript:history.go(-1)">
    </body>
</html>
