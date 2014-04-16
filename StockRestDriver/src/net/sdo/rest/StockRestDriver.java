/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo.rest;

import com.sun.faban.driver.*;

import java.io.IOException;

@BenchmarkDefinition (
    name    = "StockRestDriver",
    configPrecedence = true,
    version = "0.1"
)
@BenchmarkDriver (
    name           = "StockRestDriver"
    /*threadPerScale    = 1*/
)
@FlatMix(
    operations = { "StockRest1Yr", "StockRest1YrNoData",
    			"StockRest5Calls", "StockRest2Calls",
			"StockRest10Calls", "StockRest2Yr",
			"StockRest6Months", "StockRest5Yr"
		   },
    mix = { 1, 1, 1, 1, 1, 1, 1, 1 },
    deviation = 5
)
@NegativeExponential (
    cycleType = CycleType.THINKTIME,
    cycleMean = 500,
    cycleMax = 500,
    cycleDeviation = 5
)
public class StockRestDriver {
    private static int numTx;
    private static int maxTx = Integer.MAX_VALUE;

    private synchronized static void countTx() {
	if (++numTx > maxTx) {
            System.out.println("Completed transactions: " + numTx);
            System.exit(0);
	}
    }

    /** The driver context for this instance */
    private DriverContext ctx;
    private HttpTransport http;
    private String stockRest1YrURL;
    private String stockRest1YrNoDataURL;
    private String stockRest5CallsURL;
    private String stockRest2YrURL;
    private String stockRest6MonthsURL;
    private String stockRest5YrURL;

    public StockRestDriver() {
        ctx = DriverContext.getContext();
	http = new HttpTransport();

	String host = ctx.getProperty("HOST");
	String port = ctx.getProperty("PORT");
	String cache = ctx.getProperty("CACHE");
	stockRest1YrURL = "http://" + host + ":" + port + "/StockPriceRestService/webresources/StockPrice?mode=1&startDate=01%2F01%2F2013&endDate=12%2F31%2F2013&cache=" + cache + "&symbol=AAAA";
	stockRest1YrNoDataURL = "http://" + host + ":" + port + "/StockPriceRestService/webresources/StockPrice?mode=0&startDate=01%2F01%2F2013&endDate=12%2F31%2F2013&" + cache + "=true&symbol=AAAA";
	stockRest5CallsURL = "http://" + host + ":" + port + "/StockPriceRestService/webresources/StockPrice?mode=0&startDate=01%2F01%2F2013&endDate=01%2F07%2F2013&" + cache + "=true&symbol=AAAA";
	stockRest2YrURL = "http://" + host + ":" + port + "/StockPriceRestService/webresources/StockPrice?mode=1&startDate=01%2F01%2F2013&endDate=12%2F31%2F2014&" + cache + "=true&symbol=AAAA";
	stockRest6MonthsURL = "http://" + host + ":" + port + "/StockPriceRestService/webresources/StockPrice?mode=1&startDate=01%2F01%2F2013&endDate=06%2F30%2F2013&" + cache + "=true&symbol=AAAA";
	stockRest5YrURL = "http://" + host + ":" + port + "/StockPriceRestService/webresources/StockPrice?mode=1&startDate=01%2F01%2F2013&endDate=12%2F31%2F2017&" + cache + "=true&symbol=AAAA";

	String tx = ctx.getProperty("NUM_TX");
	if (tx != null)
	    maxTx = Integer.parseInt(tx);
    }

    @BenchmarkOperation (
       name = "StockRest1Yr",
       max90th = 1000.,
       timing = Timing.MANUAL
    )
    public void doStockRest1Yr() throws IOException {
	ctx.recordTime();
	http.readURL(stockRest1YrURL);
	ctx.recordTime();
	countTx();
    }

    @BenchmarkOperation (
       name = "StockRest1YrNoData",
       max90th = 1000.,
       timing = Timing.MANUAL
    )
    public void doStockRest1YrNoData() throws IOException {
	ctx.recordTime();
	http.readURL(stockRest1YrNoDataURL);
	ctx.recordTime();
	countTx();
    }

    @BenchmarkOperation (
       name = "StockRest5Calls",
       max90th = 1000.,
       timing = Timing.MANUAL
    )
    public void doStockRest5Calls() throws IOException {
	ctx.recordTime();
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	ctx.recordTime();
	countTx();
    }

    @BenchmarkOperation (
       name = "StockRest2Calls",
       max90th = 1000.,
       timing = Timing.MANUAL
    )
    public void doStockRest2Calls() throws IOException {
	ctx.recordTime();
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	ctx.recordTime();
	countTx();
    }

    @BenchmarkOperation (
       name = "StockRest10Calls",
       max90th = 1000.,
       timing = Timing.MANUAL
    )
    public void doStockRest10Calls() throws IOException {
	ctx.recordTime();
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	http.readURL(stockRest5CallsURL);
	ctx.recordTime();
	countTx();
    }

    @BenchmarkOperation (
       name = "StockRest2Yr",
       max90th = 1000.,
       timing = Timing.MANUAL
    )
    public void doStockRest2Yr() throws IOException {
	ctx.recordTime();
	http.readURL(stockRest2YrURL);
	ctx.recordTime();
	countTx();
    }

    @BenchmarkOperation (
       name = "StockRest6Months",
       max90th = 1000.,
       timing = Timing.MANUAL
    )
    public void doStockRest6Months() throws IOException {
	ctx.recordTime();
	http.readURL(stockRest6MonthsURL);
	ctx.recordTime();
	countTx();
    }

    @BenchmarkOperation (
       name = "StockRest5Yr",
       max90th = 1000.,
       timing = Timing.MANUAL
    )
    public void doStockRest5Yr() throws IOException {
	ctx.recordTime();
	http.readURL(stockRest5YrURL);
	ctx.recordTime();
	countTx();
    }
}
