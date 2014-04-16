<%-- 
 Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="history" class="net.sdo.stock.StockPriceHistory" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stock Price History</title>
    </head>
    <body>
        <h1>Stock Price History for <%=history.getSymbol()%></h1>
        For period <%=history.getFirstDate()%> to <%=history.getLastDate()%>
        <br>
        <br><strong>High: </strong><%=history.getHighPrice()%>
        <br><strong>Low: </strong><%=history.getLowPrice()%>
        <br><strong>Average: </strong><%=history.getAveragePrice()%>
        <br><strong>Standard Deviation: </strong><%=history.getStdDev()%>
    </body>
</html>
