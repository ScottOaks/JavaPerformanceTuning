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

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class StockBatchingPool {
    @Param({"1"})
    private int corePoolSize;

    @Param({"1"})
    private int maxPoolSize;

    @Param({"default"})
    private String queueType;

    @Param({"10000"})
    private int numTasks;

    @Param({"01/01/19"})
    private String start;

    @Param({"12/31/19"})
    private String end;

    @Param({"mock"})
    private String emType;

    private ThreadPoolExecutor tpe;
    private BlockingQueue<Runnable> queue;
    private CountDownLatch countDownLatch;
    private Date startDate;
    private Date endDate;
    private EntityManager em;
    @Setup
    public void setup() throws ParseException {
        switch(queueType) {
	    case "default": queue = new LinkedBlockingQueue<>(); break;
	    case "synchronous": queue = new SynchronousQueue<>(); break;
	    case "array": queue = new ArrayBlockingQueue<>(numTasks); break;
	    default: throw new IllegalArgumentException("Bad queuetype " + queueType);
	}
        EntityManagerFactory emf;
	switch(emType) {
	    case "mock": 
                emf = new MockStockPriceEntityManagerFactory("MockEntityManager");
		break;
	    case "db":
		emf = Persistence.createEntityManagerFactory("StockPU");
		break;
	    default: throw new IllegalArgumentException("Bad emtype " + emType);
	}
	em = emf.createEntityManager();
	DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	startDate = df.parse(start);
	endDate = df.parse(end);
        tpe = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
		Long.MAX_VALUE, TimeUnit.SECONDS, queue);
	tpe.prestartAllCoreThreads();
    }

    @TearDown
    public void shutdown() {
        tpe.shutdown();
    }

    @Benchmark
    public void test(Blackhole bh) throws InterruptedException {
	countDownLatch = new CountDownLatch(numTasks);
        for (int i = 0; i < numTasks; i++) {
	    tpe.submit(new StockCalcTask(i, startDate, endDate,
	    		numTasks, StockPriceUtils.STANDARD,
			em, countDownLatch, bh));
	}
	countDownLatch.await();
    }

    private static class StockCalcTask implements Callable<Object> {

        private int stockId;
        private int max;
        private int mode;
        private Date startDate, endDate;
        private EntityManager em;
	private CountDownLatch latch;
	private Blackhole blackhole;

        public StockCalcTask(int i, Date startDate, Date endDate, int max,
		 int mode, EntityManager em, CountDownLatch latch,
		 Blackhole bh) {
            stockId = i;
            this.startDate = startDate;
            this.endDate = endDate;
            this.max = max;
            this.mode = mode;
            this.em = em;
	    this.latch = latch;
	    this.blackhole = bh;
        }

        @Override
        public Object call() {
            try {
                String symbol = StockPriceUtils.makeSymbol(stockId);
                StockPriceHistory sph;
                if (mode == 0) {
                    sph = new StockPriceHistoryImpl(symbol, startDate, endDate, em);
                }
                else {
                    sph = new StockPriceHistoryLogger(symbol, startDate, endDate, em);
                }
                sph.getHighPrice(); // Force recalc
		blackhole.consume(sph);
            } catch (Exception re) {
                re.printStackTrace();
                throw new RuntimeException(re);
            } finally {
	        latch.countDown();
	    }
            return null;
        }
    }
}
