<%-- 
 Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:useBean id="exception" class="java.lang.Exception" scope="request" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Error from StockPrice Servlet!</h1>
        The following error was returned from the StockPrice servlet
        <pre>
            <% 
                PrintWriter pw = new PrintWriter(out);
                exception.printStackTrace(pw);
            %>
        </pre>
    </body>
</html>
