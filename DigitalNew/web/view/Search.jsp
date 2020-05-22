<%-- 
    Document   : Search
    Created on : Feb 6, 2020, 11:29:50 PM
    Author     : thang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Paging.HtmlHelper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Digital"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/home.css" rel="stylesheet" type="text/css"/> 
        <%
            ArrayList<Digital> digitalListPaging = (ArrayList<Digital>) request.getAttribute("digitalListPaging");
            Digital digital = (Digital) request.getAttribute("digital");
            Integer pageindex = (Integer) request.getAttribute("pageindex");
            Integer pagecount = (Integer) request.getAttribute("pagecount");
            String title = (String) request.getAttribute("title");
            String error = (String) request.getAttribute("error");
        %>
    </head>

    <body>
        <div class="background">
            
            <jsp:include page="../body/Header.jsp"/>
            <div class="separate">
                <div class="content">
                    <%
                        if (error != null) {
                    %>
                    <div class="error">
                        <h4><%=error%></h4>
                    </div>
                    <%
                    } else {
                    %>
                    <%
                        for (Digital d : digitalListPaging) {
                    %>

                    <div class="title">
                        <h4><a href="detail?id=<%=d.getId()%>"><%=d.getTitle()%></a></h4>
                    </div>
                    <div class="title">
                        <table>
                            <tr>
                                <td><img class="imageContentSearch"  src="<%=d.getImage()%>" /></td>
                                <td>
                                    <div class="descript">
                                        <p><%=d.getShortDes()%></p>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                    </br>                
                    <%
                        }
                    %>
                    <form action="search" method="Post">
                        <input type="hidden" name="title" value="<%=title%>">
                        <%=HtmlHelper.pager(pageindex, pagecount)%>
                    </form>
                    <%
                        }
                    %>
                </div>
                <jsp:include page="../body/Right.jsp"/>
            </div>
            <jsp:include page="../body/Footer.jsp"/>
        </div>
    </body>
</html>
