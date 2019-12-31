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
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import net.sdo.stock.StockOptionPrice;
import net.sdo.stock.StockPrice;
import net.sdo.stock.StockPriceUtils;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockOptionPK;
import net.sdo.stockimpl.StockOptionPriceEagerLazyImpl;
import net.sdo.stockimpl.StockPriceEagerEagerImpl;
import net.sdo.stockimpl.StockPriceEagerLazyImpl;
import net.sdo.stockimpl.StockPriceEagerLazyQueryCacheImpl;
import net.sdo.stockimpl.StockPriceLazyEagerImpl;
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
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.AverageTime)
public class StockReadJPA {
    private static long msPerDay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
    private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);

    @State(Scope.Thread)
    public static abstract class TestState {

        @Param({"true"})
        protected boolean batch;

        @Param({"true"})
        protected boolean processOptions;

        @Param({"true"})
        protected boolean doSingle;

        @Param({"true"})
        protected boolean join;

	@Param({"3"})
	protected int nStocks;

	@Param({"1/1/19"})
	protected String start;

	@Param({"12/31/19"})
	protected String end;

	@Param({"true"})
	protected boolean cached;

	public String pu;
	public String query;
	public Class targetClass;

        private EntityManagerFactory emf;
	private ArrayList<Date> validDates;
	private EntityManager em;
	protected abstract void setupChild();
        @Setup(Level.Trial)
        public void setup() throws ParseException {
	    setupChild();
            emf = Persistence.createEntityManagerFactory(pu);
	    validDates = new ArrayList<>();
            Date d = df.parse(start);
            Date endDate = df.parse(end);
            Calendar calendar = Calendar.getInstance();
            while (d.before(endDate)) {
                calendar.setTime(d);
                if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                    && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                    validDates.add((Date) d.clone());
                }
                d.setTime(d.getTime() + msPerDay);
            }
	    if (cached) {
                em = emf.createEntityManager();
		run(null);
	    }
        }

	@Setup(Level.Invocation)
	public void setupInvocation() {
	    if (!cached) {
                emf = Persistence.createEntityManagerFactory(pu);
                em = emf.createEntityManager();
	    }
	}

	@TearDown(Level.Invocation)
	public void teardownInvocation() {
	    if (!cached) {
                em.close();
		emf.close();
	    }
	}

	public void runQuery(Blackhole bh) {
            Query q = em.createNamedQuery(query);
            if (batch) {
                q.setHint("eclipselink.JDBC_FETCH_SIZE", "1");
            }
            List<StockPrice> l = q.getResultList();
            String lastSymbol = "";
            for (StockPrice sp : l) {
                if (!sp.getSymbol().equals(lastSymbol)) {
                    lastSymbol = sp.getSymbol();
                }
                BigDecimal bd = sp.getClosingPrice();
		if (bh != null) bh.consume(bd);
                if (processOptions) {
                    Collection<? extends StockOptionPrice> options = sp.getOptions();
                    for (StockOptionPrice sop : options) {
                        bd = sop.getPrice().add(bd);
			if (bh != null) bh.consume(bd);
                    }
                }
            }
        }

	public void runSingle(Blackhole bh) {
            String lastSymbol = "";
	    BigDecimal bd;
            for (int i = 0; i < nStocks; i++) {
                String symbol = StockPriceUtils.makeSymbol(i);
                for (Date d : validDates) {
                    StockPrice sp = (StockPrice) em.find(targetClass, new StockPricePK(symbol, d));
                    if (sp != null) {
                        bd = sp.getClosingPrice();
			if (bh != null) bh.consume(bd);
                        if (processOptions) {
                            Collection<? extends StockOptionPrice> options = sp.getOptions();
                            for (StockOptionPrice sop : options) {
                                bd = sop.getPrice().add(bd);
				if (bh != null) bh.consume(bd);
                            }
                       }
                    }
                }
            }
	}
	
	public void run(Blackhole bh) {
	    if (doSingle) {
	        runSingle(bh);
	    }
	    else {
	        runQuery(bh);
	    }
	}
    }

    public static class TestLazyEager extends TestState {
        protected void setupChild() {
	    pu = "StockPULazyEager";
	    targetClass = StockPriceLazyEagerImpl.class;
	    query = (join) ? "findJoinLE" : "findAllLE";
	}
    }

    public static class TestLazyLazy extends TestState {
        protected void setupChild() {
	    pu = "StockPULazyLazy";
	    targetClass = StockPriceLazyLazyImpl.class;
	    query = (join) ? "findJoinLL" : "findAllLL";
	}
    }

    public static class TestEagerEager extends TestState {
        protected void setupChild() {
	    pu = "StockPUEagerEager";
	    targetClass = StockPriceEagerEagerImpl.class;
	    query = (join) ? "findJoinEE" : "findAllEE";
	}
    }

    public static class TestEagerLazy extends TestState {
        protected void setupChild() {
	    pu = "StockPUEagerLazy";
	    targetClass = StockPriceEagerLazyImpl.class;
	    query = (join) ? "findJoinEL" : "findAllEL";
	}
    }

    public static class TestLazyQuery extends TestState {
        protected void setupChild() {
	    pu = "StockPULazyQuery";
	    targetClass = StockPriceEagerLazyQueryCacheImpl.class;
	    query = (join) ? "findJoinELQ" : "findAllELQ";
	}
    }

    @Benchmark
    public void testLazyEager(TestLazyEager ts, Blackhole bh) throws InterruptedException {
	test(ts, bh);
    }

    @Benchmark
    public void testLazyLazy(TestLazyLazy ts, Blackhole bh) throws InterruptedException {
	test(ts, bh);
    }

    @Benchmark
    public void testEagerEager(TestEagerEager ts, Blackhole bh) throws InterruptedException {
	test(ts, bh);
    }

    @Benchmark
    public void testLazyQuery(TestLazyQuery ts, Blackhole bh) throws InterruptedException {
	test(ts, bh);
    }

    @Benchmark
    public void testEagerLazy(TestEagerLazy ts, Blackhole bh) throws InterruptedException {
	test(ts, bh);
    }

    public void test(TestState ts, Blackhole bh) {
        ts.run(bh);
    }
}
