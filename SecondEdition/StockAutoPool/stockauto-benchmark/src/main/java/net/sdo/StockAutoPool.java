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

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.sdo.stock.StockPriceHistory;
import net.sdo.stock.StockPriceUtils;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockPriceHistoryImpl;
import net.sdo.stockimpl.StockPriceHistoryLogger;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.AverageTime)
public class StockAutoPool {

    @State(Scope.Benchmark)
    public static class GlobalParams {
        @Param({"1"})
        public int corePoolSize;

        @Param({"10000"})
        public int numSymbols;

        @Param({"01/01/19"})
        public String start;

        @Param({"12/31/22"})
        public String end;

        @Param({"mock"})
        public String emType;

	public ArrayList<String> symbols;
	public EntityManager em;
	public Date startDate;
	public Date endDate;

	@Setup
	public void setup() throws ParseException {
            EntityManagerFactory emf;
	    switch(emType) {
	        case "mock": 
                    emf = new MockStockPriceEntityManagerFactory("MockEntityManager");
		    break;
	        case "db":
		    emf = Persistence.createEntityManagerFactory("StockPU");
		    break;
	        default: throw new IllegalArgumentException("Bad emType " + emType);
	    }
	    em = emf.createEntityManager();
	    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	    startDate = df.parse(start);
	    endDate = df.parse(end);
	    symbols = new ArrayList<>();
	    for (int i = 0; i < numSymbols; i++) {
	        symbols.add(StockPriceUtils.makeSymbol(i));
	    }
	}
    }

    @State(Scope.Benchmark)
    public static class ThreadPoolTester {
	private GlobalParams gp;
	ThreadPoolExecutor tpe;

        @Setup
        public void setup(GlobalParams gp) throws ParseException {
	    this.gp = gp;
	    tpe = new ThreadPoolExecutor(gp.corePoolSize, gp.corePoolSize,
	                     Long.MAX_VALUE, TimeUnit.SECONDS,
			     new LinkedBlockingQueue());
        }

	@TearDown
	public void teardown() throws InterruptedException {
	    tpe.shutdown();
	    tpe.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
	}

	private static class Task implements Runnable {
	    private GlobalParams gp;
	    private String symbol;
	    private CountDownLatch latch;
	    private Blackhole bh;

	    public Task(String symbol, GlobalParams gp, CountDownLatch latch, Blackhole bh) {
	        this.symbol = symbol;
		this.gp = gp;
		this.latch = latch;
		this.bh = bh;
	    }

	    public void run() {
	        bh.consume(new StockPriceHistoryImpl(symbol,
					gp.startDate, gp.endDate, gp.em));
		latch.countDown();
	    }
	}

	public void test(Blackhole bh) throws InterruptedException {
	    CountDownLatch latch = new CountDownLatch(gp.symbols.size());
	    for (String s: gp.symbols) {
	        tpe.submit(new Task(s, gp, latch, bh));
	    }
	    latch.await();
	}
    }

    @State(Scope.Benchmark)
    public static class StreamTester {
	private GlobalParams gp;
	Stream<String> stream;

        @Setup
        public void setup(GlobalParams gp) throws ParseException {
	    this.gp = gp;
        }

	public void test(Blackhole bh) {
	    stream = gp.symbols.parallelStream();
	    stream.forEach(s -> {
	        bh.consume(new StockPriceHistoryImpl(s,
					gp.startDate, gp.endDate, gp.em));
	    });
	}
    }

    @Benchmark
    public void testThreadPool(ThreadPoolTester tpt, Blackhole bh) throws InterruptedException {
        tpt.test(bh);
    }

    @Benchmark
    public void testStream(StreamTester st, Blackhole bh) throws InterruptedException {
        st.test(bh);
    }
}
