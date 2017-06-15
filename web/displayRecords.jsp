<%-- 
    Document   : displayRecords
    Created on : Nov 3, 2015, 4:52:40 PM
    Author     : John Phillips
--%>

<%@page import="java.util.List"%>
<%@page import="model.Task"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Soding</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
    </head>
    <body>
        <h1><a href="index.html">Individual Assignment</a></h1>
        <h2>Your Task All</h2>
        <%
            List<Task> mydata = (List<Task>) request.getAttribute("mydata");
            out.println("<table>");
            for (Task user : mydata) {
                out.println(user.inHTMLRowFormat());
            }
            out.println("</table>");
        %>
    </body>
</html>
