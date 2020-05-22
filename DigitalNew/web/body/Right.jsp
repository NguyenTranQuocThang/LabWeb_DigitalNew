<%-- 
    Document   : Right
    Created on : Feb 6, 2020, 3:39:14 PM
    Author     : thang
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.Digital"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/right.css" rel="stylesheet" type="text/css"/>
        
        <%
            Digital digital = (Digital)request.getAttribute("digital");
            Digital digitalNew = (Digital)request.getAttribute("digitalNew");
            ArrayList<Digital> digitalList = (ArrayList<Digital>)request.getAttribute("digitalList");
        %>
    </head>
    <body>
        <div class="right">

            <div class="title">
                <h4>Digital News</h4>
            </div>
            <div class="descript">
                <p><%=digitalNew.getShortDes() %>
                </p>
            </div>
            
            <div class="title">
                <h4>Search</h4>
            </div>
            <form action="search" method="post">
                <input class="boxsearch" type="text" name="title" size="17" required="" >
                <input class="buttonsearch" type="submit" value="Go">
            </form>
            </br>
            
            <div class="title">
                <h4>Last Articles</h4>
                </br>
            </div>
            <%
                for (Digital d : digitalList) {
            %>
                <a class="recentNew" href="detail?id=<%=d.getId() %>"><%=d.getTitle() %></a>
                </br>
                </br>
            <%
                    }
            %>
            
        </div>
    </body>
</html>
