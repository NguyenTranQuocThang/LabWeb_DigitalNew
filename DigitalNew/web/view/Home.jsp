<%-- 
    Document   : Home
    Created on : Feb 6, 2020, 8:49:11 PM
    Author     : thang
--%>

<%@page import="Model.Digital"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <%
            Digital digital = (Digital) request.getAttribute("digital");

        %>
    </head>
    <body>
        <div class="background">
            <jsp:include page="../body/Header.jsp"/>
            <div class="separate">
                <div class="content">
                    <div class="block">
                        <div class="title">
                            <h4><%=digital.getTitle()%></h4>
                        </div>
                        
                        <img class="imageContent" src="<%=digital.getImage()%>" />
                        
                        <div class="descript">
                            <p>
                                <%=digital.getDescription()%>
                            </p>
                        </div> 
                    </div>
                    <div class="time">
                        <div class="image1"></div>
                        <div class="image2"></div>
                        By <%=digital.getAuthor()%> | <%=digital.getDateFormat() %>
                    </div>
                </div>
                <jsp:include page="../body/Right.jsp"/>
            </div>
            <jsp:include page="../body/Footer.jsp"/>
        </div>
    </body>
</html>
