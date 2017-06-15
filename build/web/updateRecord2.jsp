<%-- 
    Document   : updateRecord2
    Created on : Nov 3, 2015, 8:54:49 PM
    Author     : John Phillips
--%>

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
        <h2>Update The Task</h2>
        <form action="Controller" >
            <% Task task = (Task) request.getAttribute("task");%>
            Task Id: <input type="text" name="taskId" value="<%= task.getTaskId()%>" readonly>
            <br><br>
            Name : <input type="text" name="name" size="20" value="<%= task.getName()%>" required>
            <br><br>

            Description : 
            <textarea name="desc"  rows="5"><%= task.getDescription()%></textarea>
            <br><br>            

            <input type="hidden" name="action" value="updateRecord2">

            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>
