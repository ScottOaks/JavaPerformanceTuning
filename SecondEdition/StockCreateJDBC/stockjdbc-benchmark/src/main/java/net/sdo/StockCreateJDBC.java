/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import net.sdo.stock.StockPrice;
import net.sdo.stock.StockPriceUtils;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockPriceEagerLazyImpl;
import net.sdo.stockimpl.StockPricePK;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import oracle.jdbc.OracleConnection;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class StockCreateJDBC {
    private static String dropTableStockPrice = "DROP TABLE STOCKPRICE CASCADE CONSTRAINTS";
    private static String dropTableStockOptionPrice = "DROP TABLE STOCKOPTIONPRICE";
    private static String createTableStockPrice =
        "CREATE TABLE STOCKPRICE ("
        + "SYMBOL VARCHAR(16) not null,"
	+ "PRICEDATE DATE not null,"
	+ "LOW DECIMAL(30,2) not null,"
	+ "HIGH DECIMAL(30,2) not null,"
	+ "OPENINGPRICE NUMERIC(30,2) not null,"
	+ "CLOSINGPRICE NUMERIC(30,2) not null,"
	+ "ISYEARHIGH INTEGER,"
	+ "ISYEARLOW INTEGER,"
	+ "primary key (symbol, pricedate)"
	+ ")";
    private static String createTableStockOptionPrice =
        "CREATE TABLE STOCKOPTIONPRICE ("
        + "PRICE NUMERIC(30,2) not null,"
	+ "EXPIRATIONPERIOD INTEGER,"
	+ "PRICEDATE DATE not null,"
	+ "SYMBOL VARCHAR(16) not null,"
	+ "primary key (symbol, pricedate, expirationperiod), "
	+ "FOREIGN KEY (SYMBOL, PRICEDATE) "
	+ "REFERENCES STOCKPRICE (SYMBOL, PRICEDATE) "
	+ "ON DELETE CASCADE"
	+ ")";
    private static String insertStockSQL =
        "INSERT INTO STOCKPRICE("
        + "CLOSINGPRICE, HIGH, ISYEARHIGH, ISYEARLOW, LOW, OPENINGPRICE, PRICEDATE, SYMBOL) "
	+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static String insertOptionSQL = "INSERT INTO STOCKOPTIONPRICE("
	+ "PRICE, EXPIRATIONPERIOD, PRICEDATE, SYMBOL) "
	+ "VALUES (?, ?, ?, ?)";

    private static long msPerDay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
    private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
    private static EntityManager mockEM =
	        new MockStockPriceEntityManagerFactory("MockEntityManager").
		createEntityManager();

    @Param({"1"})
    private int numStocks;

    @Param({"01/01/19"})
    private String start ;

    @Param({"12/31/19"})
    private String end ;

    @Param({"1"})
    private int mode;

    @Param({"jdbc:mysql://localhost:3306/perf"})
    private String URL;

    @Param({"soaks"})
    private String user;

    @Param({"mypassword"})
    private String pw;

    private boolean autocommit;
    private boolean commitWhenStockChanges;
    private boolean commitAtTestEnd;
    private boolean batch;
    private boolean batchWhenStockChanges;
    private boolean batchAtTestEnd;
    private StockPrice[] stockPrices;
    @Setup
    public void setup() throws ParseException {
	Date startDate = df.parse(start);
	Date endDate = df.parse(end);
	switch(mode) {
	    case 1:
		batch = false;
	    	autocommit = true;
		commitWhenStockChanges = commitAtTestEnd = false;
		break;
	    case 2:
		batch = false;
	    	autocommit = false;
		commitWhenStockChanges = true;
		commitAtTestEnd = false;
		break;
	    case 3:
		batch = false;
	    	autocommit = false;
		commitWhenStockChanges = false;
		commitAtTestEnd = true;
		break;
	    case 4:
		batch = true;
		batchWhenStockChanges = true;
		batchAtTestEnd = false;
	    	autocommit = false;
		commitWhenStockChanges = true;
		commitAtTestEnd = false;
		break;
	    case 5:
		batch = true;
		batchWhenStockChanges = true;
		batchAtTestEnd = false;
	    	autocommit = false;
		commitWhenStockChanges = false;
		commitAtTestEnd = true;
		break;
	    case 6:
		batch = true;
		batchWhenStockChanges = false;
		batchAtTestEnd = true;
	    	autocommit = false;
		commitWhenStockChanges = false;
		commitAtTestEnd = true;
		break;
	}
	ArrayList<StockPrice> al = new ArrayList<>();
        for (int i = 0; i < numStocks; i++) {
            String symbol = StockPriceUtils.makeSymbol(i);
            Date curDate = new Date(startDate.getTime());
            while (!curDate.after(endDate)) {
                StockPrice sp = mockEM.find(StockPriceEagerLazyImpl.class,
                            new StockPricePK(symbol, (Date) curDate.clone()));
                curDate.setTime(curDate.getTime() + msPerDay);
		if (sp == null) continue;
		al.add(sp);
	    }
	}
	stockPrices = al.toArray(new StockPrice[al.size()]);
    }

    @Setup(Level.Invocation)
    public void createTable() throws SQLException {
        try (Connection c = DriverManager.getConnection(URL, user, pw)) {
            c.setAutoCommit(true);
            try (PreparedStatement ps = c.prepareStatement(dropTableStockOptionPrice)) {
                ps.executeUpdate();
	    } catch (SQLException sqe) {
	    	if (sqe.toString().indexOf("ORA-00942") == -1) {
		    throw sqe;
		}
	    }
            try (PreparedStatement ps = c.prepareStatement(dropTableStockPrice)) {
                ps.executeUpdate();
	    } catch (SQLException sqe) {
	    	if (sqe.toString().indexOf("ORA-00942") == -1) {
		    throw sqe;
		}
	    }
            try (PreparedStatement ps = c.prepareStatement(createTableStockPrice);
                 PreparedStatement ps2 = c.prepareStatement(createTableStockOptionPrice)) {
                    ps.executeUpdate();
		    ps2.executeUpdate();
	    }
	}
    }

    @Benchmark
    public void test(Blackhole bh) throws SQLException {
	Properties p = new Properties();
	p.put("user", user);
	p.put("password", pw);
        try (Connection c = DriverManager.getConnection(URL, p)) {
	    ((OracleConnection) c).setImplicitCachingEnabled(true);
	    ((OracleConnection) c).setStatementCacheSize(100);
            c.setAutoCommit(autocommit);
	    String lastSymbol = null;
            try (PreparedStatement ps = c.prepareStatement(insertStockSQL);
                 PreparedStatement ps2 = c.prepareStatement(insertOptionSQL)) {
                for (StockPrice sp : stockPrices) {
		    String symbol = sp.getSymbol();
		    if (lastSymbol != null && !symbol.equals(lastSymbol)) {
                        if (batch && batchWhenStockChanges) {
                            ps.executeBatch();
                            ps2.executeBatch();
                        }
                        if (commitWhenStockChanges) {
                            c.commit();
                        }
		    }
		    lastSymbol = symbol;
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
                if (batch) {
                    ps.executeBatch();
                    ps2.executeBatch();
                }
                if (!autocommit) {
                    c.commit();
                }
            }
        }
    }
}
