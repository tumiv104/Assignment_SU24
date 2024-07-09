<%-- 
    Document   : login
    Created on : Jul 8, 2024, 11:17:00 AM
    Author     : Nitro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login" method="POST">
            username <input type="text" name="username"/> 
            password <input type="password" name="password"/>
            <input type="submit" value="login"/>
        </form>
    </body>
</html>
