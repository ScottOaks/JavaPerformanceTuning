/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import net.sdo.stock.StockPrice;
import net.sdo.stock.StockPriceUtils;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockPriceEagerLazyImpl;
import net.sdo.stockimpl.StockPricePK;
import oracle.jdbc.OracleConnection;

public class StockPriceCreateJDBC extends Thread {

    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    private static long msPerDay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
    private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
    private static Date startDate;
    private static Date endDate;
    private static EntityManager mockEM =
        new MockStockPriceEntityManagerFactory("MockEntityManager").
	createEntityManager();
    private static int nTransactions = 1;
    private static String URL;
    private static String user;
    private static String pw;
    private static boolean autocommit;
    private static boolean batch;
    private static String insertStockSQL = "INSERT INTO STOCKPRICE("
        + "CLOSINGPRICE, HIGH, ISYEARHIGH, ISYEARLOW, LOW, OPENINGPRICE, PRICEDATE, SYMBOL) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static String insertOptionSQL = "INSERT INTO STOCKOPTIONPRICE("
        + "PRICE, EXPIRATIONPERIOD, PRICEDATE, SYMBOL) "
        + "VALUES (?, ?, ?, ?)";
    private int startStock;
    private int numStocks;

    public StockPriceCreateJDBC(int start, int num) {
        startStock = start;
        numStocks = num;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + ": Starting");
        try (Connection c = DriverManager.getConnection(URL, user, pw)) {
            c.setAutoCommit(autocommit);
            ((OracleConnection) c).setImplicitCachingEnabled(true);
            ((OracleConnection) c).setStatementCacheSize(10);
            try (PreparedStatement ps = c.prepareStatement(insertStockSQL);
                 PreparedStatement ps2 = c.prepareStatement(insertOptionSQL)) {
                for (int i = startStock; i < numStocks; i++) {
                    String symbol = StockPriceUtils.makeSymbol(i);
                    Date curDate = new Date(startDate.getTime());
                    System.out.println(Thread.currentThread() +
		        ": Processing " + i + ": " + symbol);
                    while (!curDate.after(endDate)) {
			// MockEM creates the data to store
                        StockPrice sp =
			    mockEM.find(StockPriceEagerLazyImpl.class,
			    new StockPricePK(symbol, (Date) curDate.clone()));
                        if (sp != null) {
                            ps.clearParameters();
                            ps.setBigDecimal(1, sp.getClosingPrice());
                            ps.setBigDecimal(2, sp.getHigh());
                            ps.setInt(3, sp.isYearHigh() ? 1 : 0);
                            ps.setInt(4, sp.isYearLow() ? 1 : 0);
                            ps.setBigDecimal(5, sp.getLow());
                            ps.setBigDecimal(6, sp.getOpeningPrice());
                            ps.setDate(7, new java.sql.Date(sp.getDate().getTime()));
                            ps.setString(8, sp.getSymbol());
                            if (batch) {
                                ps.addBatch();
                            } else {
                                ps.executeUpdate();
                            }
                            for (int j = 0; j < 5; j++) {
                                ps2.clearParameters();
                                ps2.setBigDecimal(1,
				    sp.getClosingPrice().multiply(
				        new BigDecimal(1 + j / 100.)));
                                ps2.setInt(2, j);
                                ps2.setDate(3,
				    new java.sql.Date(sp.getDate().getTime()));
                                ps2.setString(4, sp.getSymbol());
                                if (batch) {
                                    ps2.addBatch();
                                } else {
                                    ps2.executeUpdate();
                                }
                            }
                        }
                        curDate.setTime(curDate.getTime() + msPerDay);
                    }
                    if (batch && (i % nTransactions) == 0) {
                        ps.executeBatch();
                        ps2.executeBatch();
                    }
                    if (!autocommit && (i % nTransactions) == 0) {
                        c.commit();
                    }
                }
                if (batch) {
                    ps.executeBatch();
                    ps2.executeBatch();
                }
                if (!autocommit) {
                    c.commit();
                }
            }
        } catch (SQLException sqe) {
            sqe.printStackTrace();
        }
        System.out.println(Thread.currentThread() + ": Complete");
    }

    public static void main(String[] args) throws ParseException {
        int curArg = 0;
        URL = args[curArg++];
        user = args[curArg++];
        pw = args[curArg++];
        int nThreads = Integer.parseInt(args[curArg++]);
        int num = Integer.parseInt(args[curArg++]);
        int numPerThread = num / nThreads;
        startDate = df.parse(args[curArg++]);
        endDate = df.parse(args[curArg++]);
        autocommit = Boolean.parseBoolean(args[curArg++]);
        nTransactions = Integer.parseInt(args[curArg++]);
        batch = Boolean.parseBoolean(args[curArg++]);
        StockPriceCreateJDBC[] threads = new StockPriceCreateJDBC[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new StockPriceCreateJDBC(numPerThread * i,
	        numPerThread * i + numPerThread);
        }
        for (int i = 0; i < nThreads; i++) {
            threads[i].start();
        }
        for (int i = 0; i < nThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                System.err.println("Thread join interrupted");
            }
        }
    }
}
