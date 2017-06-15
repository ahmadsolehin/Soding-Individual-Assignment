<%-- 
    Document   : createRecord
    Created on : Nov 3, 2015, 5:19:26 PM
    Author     : John Phillips
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Soding</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="mystyle.css">
        
        <link rel="stylesheet" href="code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"> </script>
    </head>
    <body>
        <h1><a href="index.html">Individual Assignment</a></h1>
        <h2>Create New Task</h2>
        <form action="Controller" >

            Task's Name: <input type="text" name="name" size="20" placeholder="Enter name" required>
            <br><br>
            Description*: 
            
            <textarea name="desc" placeholder="Enter desc" rows="5"></textarea>
            
            <br><br>
          
            

            <input type="hidden" name="action" value="createRecord">

            <input type="submit" name="submit" value="Submit">
            <br><br>
        </form>
    </body>
</html>

