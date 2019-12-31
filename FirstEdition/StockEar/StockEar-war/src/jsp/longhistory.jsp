<%-- 
 Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Date"%>
<%@page import="net.sdo.stock.StockPrice"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="history" class="net.sdo.stock.StockPriceHistory" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stock History Page</title>
    </head>
    <body>
        <h1>Stock Price History for <%=history.getSymbol()%></h1>
        For period <%=history.getFirstDate()%> to <%=history.getLastDate()%>
        <table>
            <tr>
                <th>Date</th>
                <th>Open</th>
                <th>High</th>
                <th>Low</th>
                <th>Close</th>
            </tr>
        <%
        Collection<StockPrice> prices = history.getPrices(
                      history.getFirstDate(), history.getLastDate());
        for (StockPrice sp : prices) {
            Date date = sp.getDate();
            BigDecimal highPrice = sp.getHigh();
            BigDecimal lowPrice = sp.getLow();
            BigDecimal closePrice = sp.getClosingPrice();
            BigDecimal openPrice = sp.getOpeningPrice();
        %>
            <tr>
                <td><%=date%></td>
                <td><%=openPrice%></td>
                <td><%=highPrice%></td>
                <td><%=lowPrice%></td>
                <td><%=closePrice%></td>
            </tr>
        <%
        }
        %>
    </body>
</html>
