<%-- 
    Document   : lecturer
    Created on : Jul 4, 2024, 11:39:48 PM
    Author     : Nitro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: linear-gradient(135deg, #FF6F61, #DE4313);
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                color: #333;
            }
            .form-container {
                background-color: #fff;
                padding: 30px 40px;
                border-radius: 10px;
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
                width: 400px;
                text-align: center;
            }
            .form-container h2 {
                margin-bottom: 20px;
                color: #444;
                font-size: 24px;
            }
            .form-container label {
                display: block;
                margin-bottom: 10px;
                font-weight: bold;
                text-align: left;
            }
            .form-container select,
            .form-container input[type="text"],
            .form-container input[type="password"] {
                width: 100%;
                padding: 12px;
                margin: 10px 0;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-sizing: border-box;
                background: #f9f9f9;
            }
            .form-container input[type="submit"] {
                width: 100%;
                padding: 12px;
                background-color: #3498db;
                border: none;
                border-radius: 5px;
                color: white;
                font-size: 16px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .form-container input[type="submit"]:hover {
                background-color: #2980b9;
            }
            .form-container .checkbox-label {
                display: flex;
                align-items: center;
                margin: 10px 0;
                font-size: 14px;
            }
            .form-container .checkbox-label input {
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <c:if test="${requestScope.exams eq null}">
                <h2>Select Course</h2>
                <form action="lecturer" method="POST"> 
                    <input type="hidden" name="lid" value="${param.lid}"/>
                    <label for="course">Course</label>
                    <select name="cid">
                        <c:forEach items="${requestScope.courses}" var="c">
                            <option value="${c.id}">${c.name}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="View"/>
                </form>
            </c:if>
            <c:if test="${requestScope.exams ne null}">
                <h2>Exams</h2>
                <form action="take" method="GET">
                    <input type="hidden" name="lid" value="${param.lid}"/>
                    <input type="hidden" name="cid" value="${param.cid}"/>
                    <c:forEach items="${requestScope.exams}" var="e">
                        <input type="checkbox" name="eid" value="${e.id}"/>                             
                        <label>${e.assessment.name} - ${e.assessment.subject.name} - ${e.date} - ${e.duration} minutes</label>
                        <br/>
                    </c:forEach>
                    <input type="submit" value="take"/>
                </form>
            </c:if>
        </div>
    </body>
</html>
