<%-- 
  Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
--%>

<%@page import="net.sdo.stock.StockOptionPrice"%>
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
        <h1>Stock Option Price History for <%=history.getSymbol()%></h1>
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
            %>
                <tr>
                <%
                    Collection<? extends StockOptionPrice> optionPrices = sp.getOptions();
                    for (StockOptionPrice sop : optionPrices) {
                        BigDecimal price = sop.getPrice();
                %>
                        <td><%=price%></td>
                <%
                    }
                %>
                </tr>
            <%
                }
            %>
        </body>
</html>
