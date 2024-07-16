<%-- 
    Document   : take
    Created on : Jul 5, 2024, 4:58:25 PM
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
                width: 80%;
                max-width: 800px;
                text-align: center;
                overflow-x: auto;
            }
            .form-container h2 {
                margin-bottom: 20px;
                color: #444;
                font-size: 24px;
            }
            .form-container table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            .form-container th,
            .form-container td {
                border: 1px solid #ddd;
                padding: 12px;
                text-align: center;
            }
            .form-container th {
                background-color: #f2f2f2;
                font-weight: bold;
            }
            .form-container input[type="text"] {
                width: 80%;
                padding: 8px;
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
        </style>
    </head>
    <body> 
        <div class="form-container">
            <h2>Enter Grades</h2>
            <form action="take" method="POST">
                <table border="1px">
                    <tr>
                        <th></th>
                            <c:forEach items="${requestScope.exams}" var="e">
                            <th>
                                ${e.assessment.name}
                                ${e.date} - ${e.assessment.weight} %
                            </th>
                        </c:forEach>
                    </tr>
                    <c:forEach items="${requestScope.students}" var="s">
                        <tr>
                            <td>${s.name}</td>
                            <c:forEach items="${requestScope.exams}" var="e">
                                <td>
                                    <input type="hidden" name="gradeid" value="${s.id}_${e.id}" />
                                    <input type="text" name="grade${s.id}_${e.id}" 

                                           <c:forEach items="${requestScope.grades}" var="g">
                                               <c:if test="${g.exam.id eq e.id and g.student.id eq s.id}">
                                                   value="${g.score}"
                                               </c:if>
                                           </c:forEach>
                                           />
                                </td>
                            </c:forEach>
                        </tr>   
                    </c:forEach>
                </table>
                <input type="hidden" name="cid" value="${param.cid}"/>
                <input type="submit" value="save" />
            </form>
        </div>
    </body>
</html>
