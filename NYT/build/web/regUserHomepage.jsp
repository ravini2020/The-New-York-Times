<%-- 
    Document   : regUserHomepage
    Created on : Dec 20, 2020, 2:20:03 PM
    Author     : MyCom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <header>
            <div class="header">
                <h1 class="head">The New York Times</h1>
                <p class="personalDetails">Welcome <%=request.getAttribute("firstName")%></p>
            </div>
        </header>
            
            <section class="topnav">
            <a class="topnavLeftActive" href="regUserHomePageServlet">Home</a>
            <a class="topnavLeft" href="viewList.jsp">Best Selling Books</a>
            <a class="topnavRight" href="logout">Logout</a>
        </section>
    </body>
</html>
