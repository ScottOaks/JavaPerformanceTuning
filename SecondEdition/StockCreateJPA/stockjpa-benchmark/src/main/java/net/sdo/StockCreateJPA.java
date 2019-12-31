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
import java.sql.SQLSyntaxErrorException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import net.sdo.stock.StockPrice;
import net.sdo.stock.StockPriceUtils;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockOptionPK;
import net.sdo.stockimpl.StockOptionPriceLazyLazyImpl;
import net.sdo.stockimpl.StockPriceLazyLazyImpl;
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

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class StockCreateJPA {
    private static String dropTableStockPrice = "DROP TABLE StockPrice CASCADE CONSTRAINTS";
    private static String dropTableStockOptionPrice = "DROP TABLE StockOptionPrice";
    private static String createTableStockPrice =
        "CREATE TABLE StockPrice ("
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
        "CREATE TABLE StockOptionPrice ("
        + "PRICE NUMERIC(30,2) not null,"
        + "EXPIRATIONPERIOD INTEGER,"
        + "PRICEDATE DATE not null,"
        + "SYMBOL VARCHAR(16) not null,"
        + "primary key (symbol, pricedate, expirationperiod), "
        + "FOREIGN KEY (SYMBOL, PRICEDATE) "
        + "REFERENCES StockPrice (SYMBOL, PRICEDATE) "
        + "ON DELETE CASCADE"
        + ")";

    private static long msPerDay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
    private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
    private static EntityManager mockEM =
	        new MockStockPriceEntityManagerFactory("MockEntityManager").
		createEntityManager();

    @Param({"1"})
    private int numStocks;

    @Param({"01/01/19"})
    private String start;

    @Param({"12/31/22"})
    private String end;

    @Param({"1"})
    private int mode;

    @Setup(Level.Invocation)
    public void createTable() throws SQLException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction txn = em.getTransaction();
	try {
	    txn.begin();
	    em.createNativeQuery(dropTableStockOptionPrice).executeUpdate();
	    txn.commit();
	} catch (Exception sqe) {
	    txn.rollback();
	    if (sqe.toString().indexOf("Unknown table") == -1) {
	        throw sqe;
	    }
	}
	try {
	    txn.begin();
	    em.createNativeQuery(dropTableStockPrice).executeUpdate();
	    txn.commit();
	} catch (Exception sqe) {
	    txn.rollback();
	    if (sqe.toString().indexOf("Unknown table") == -1) {
	        throw sqe;
	    }
	}
	txn.begin();
	em.createNativeQuery(createTableStockPrice).executeUpdate();
	txn.commit();
	txn.begin();
	em.createNativeQuery(createTableStockOptionPrice).executeUpdate();
	txn.commit();
	em.close();
    }

    private EntityManagerFactory emf;
    private StockPrice[] stockPrices;
    @Setup
    public void setup() throws ParseException {
        emf = Persistence.createEntityManagerFactory("StockPU-Mode" + mode);
	Date startDate = df.parse(start);
	Date endDate = df.parse(end);
        ArrayList<StockPrice> al = new ArrayList<>();
        for (int i = 0; i < numStocks; i++) {
            String symbol = StockPriceUtils.makeSymbol(i);
            Date curDate = new Date(startDate.getTime());
            while (!curDate.after(endDate)) {
                StockPrice sp = mockEM.find(StockPriceLazyLazyImpl.class,
                            new StockPricePK(symbol, (Date) curDate.clone()));
                curDate.setTime(curDate.getTime() + msPerDay);
                if (sp == null) continue;
                al.add(sp);
            }
        }
        stockPrices = al.toArray(new StockPrice[al.size()]);
    }

    @Benchmark
    public void test(Blackhole bh) throws SQLException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction txn = em.getTransaction();
        txn.begin();
        for (StockPrice sp : stockPrices) {
            em.persist(sp);
            for (int j = 0; j < 5; j++) {
                StockOptionPriceLazyLazyImpl sop = new StockOptionPriceLazyLazyImpl();
                sop.setId(new StockOptionPK(sp.getSymbol(), sp.getDate(), j));
                sop.setPrice(sp.getClosingPrice().multiply(new BigDecimal(1 + j / 100.)));
                em.persist(sop);
            }
        }
	txn.commit();
    }
}
