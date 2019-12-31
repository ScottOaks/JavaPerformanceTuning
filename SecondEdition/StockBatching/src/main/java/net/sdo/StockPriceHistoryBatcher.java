/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.sdo.stock.StockPriceHistory;
import net.sdo.stock.StockPriceUtils;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockPriceHistoryImpl;
import net.sdo.stockimpl.StockPriceHistoryLogger;

public class StockPriceHistoryBatcher extends Thread {
    private static final NumberFormat nf =
	NumberFormat.getCurrencyInstance(Locale.US);
    private static int numStocks = 10000;
    private static int save = 0;
    private static Date startDate;
    private static Date endDate;
    private static boolean useLogger = false;

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static void initEM() {
	String s = System.getProperty("MockEntityManager");
	if (s != null) {
	    emf = new MockStockPriceEntityManagerFactory(s);
	} else {
	    emf = Persistence.createEntityManagerFactory("StockPU");
	}
	em = emf.createEntityManager();
    }
    private static ArrayList<byte[]> fillData;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, InterruptedException {
	DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	int nThreads = 1;
	int fill = 0;

        int argc = 0;
	while (argc < args.length) {
	    switch(args[argc++]) {
	        case "-s": startDate = df.parse(args[argc++]); break;
	        case "-e": endDate = df.parse(args[argc++]); break;
	        case "-n": numStocks = Integer.parseInt(args[argc++]); break;
	        case "-l": useLogger = true; break;
	        case "-m": save = Integer.parseInt(args[argc++]); break;
	        case "-t": nThreads = Integer.parseInt(args[argc++]); break;
	        case "-f": fill = Integer.parseInt(args[argc++]); break;
	    }
	}
	if (startDate == null) {
	    startDate = df.parse("01/01/20");
	}
	if (endDate == null) {
	    endDate = df.parse("01/31/20");
	}
	System.out.println("Num stocks " + numStocks + " " +
			   startDate + " " + endDate);
	if (fill > 0) {
		fillData = new ArrayList<>();
		for (int i = 0; i < fill; i++) {
		    fillData.add(new byte[1024*1024]);
		}
	}
	initEM();
	StockPriceHistoryBatcher[] sphb = new StockPriceHistoryBatcher[nThreads];
	for (int i = 0; i < nThreads; i++) {
		sphb[i] = new StockPriceHistoryBatcher();
		sphb[i].start();
	}
	for (int i = 0; i < nThreads; i++) {
		sphb[i].join();
	}
    }

    public void run() {
	StockPriceHistory[] saved = new StockPriceHistory[save];
	for (int i = 0; i < numStocks; i++) {
	    String symbol = StockPriceUtils.makeSymbol(i);
	    StockPriceHistory sph;
	    if (useLogger) {
		sph = new StockPriceHistoryLogger(symbol, startDate,
			      endDate, em);
	    }
	    else {
		sph = new StockPriceHistoryImpl(symbol, startDate, endDate, em);
	    }
	    System.out.println("For " + sph.getSymbol()
		+ ": High " + nf.format(sph.getHighPrice())
		+ ", Low " + nf.format(sph.getLowPrice())
		+ ", Standard Deviation: " + sph.getStdDev().doubleValue());
	    if (save > 0) {
		saved[i % save] = sph;
	    }
	}
    }
}
