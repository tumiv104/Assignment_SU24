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
            .form-container .course-item {
                margin-bottom: 20px;
            }
            .form-container .course-item span {
                display: block;
                margin-bottom: 10px;
                font-size: 18px;
            }
            .form-container input[type="button"] {
                padding: 10px 20px;
                margin: 5px;
                background-color: #3498db;
                border: none;
                border-radius: 5px;
                color: white;
                font-size: 14px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .form-container input[type="button"]:hover {
                background-color: #2980b9;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h2>Select Course</h2>
            <form action="student" method="POST"> 
                <c:forEach items="${requestScope.courses}" var="c">
                    <div class="course-item">
                        <span>${c.name}</span>
                        <input type="button" value="View Grade" onclick="window.location.href = 'grade?cid=${c.id}'"/>
                        <input type="button" value="Exam Schedule" onclick="window.location.href = 'examschedule?cid=${c.id}'"/>
                    </div>
                </c:forEach>


            </form>
        </div>
    </body>
</html>
