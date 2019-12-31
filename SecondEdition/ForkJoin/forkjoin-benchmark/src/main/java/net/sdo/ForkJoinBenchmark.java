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

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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
public class ForkJoinBenchmark {

    private static class CalcTask extends RecursiveTask<Integer> implements Callable<Integer> {
        private double[] d;
        private int first;
        private int last;
        private int nLeaves;
	private boolean balanced;
	private int nCalc;

        public CalcTask(double[] d, int first, int last, int nLeaves, boolean balanced, int nCalc) {
            this.first = first;
            this.last = last;
            this.d = d;
            this.nLeaves = nLeaves;
	    this.balanced = balanced;
	    this.nCalc = nCalc;
        }

        @Override
        protected Integer compute() {
            int subCount;
            if (last - first < nLeaves) {
                subCount = 0;
                for (int i = first; i <= last; i++) {
                    if (d[i] < 0.5) {
                        subCount++;
                    }
                    if (!balanced) {
			for (int j = 0; j < i / 100; j++) {
			    d[i] += j;
		        }
                    }
		    else {
		        for (int j = 0; j < nCalc; j++) {
			    d[i] *= d[i];
			}
		    }
                }
            } else {
                int mid = (first + last) >>> 1;
                CalcTask left =
                    new CalcTask(d, first, mid, nLeaves, balanced, nCalc);
                left.fork();
                CalcTask right =
                    new CalcTask(d, mid + 1, last, nLeaves, balanced, nCalc);
                right.fork();
                subCount = right.join();
                subCount += left.join();
            }
            return subCount;
        }

        public Integer call() {
            return compute();
        }
    }

    private static class ThreadPoolExecutorTask implements Callable<Integer> {
        private double[] d;
        private int first;
        private int last;
        private ThreadPoolExecutor tpe;
        private static AtomicInteger count = new AtomicInteger();
	private boolean balanced;
	private int nCalc;

        public ThreadPoolExecutorTask(double[] d, int first, int last, ThreadPoolExecutor tpe, boolean balanced, int nCalc) {
            this.first = first;
            this.last = last;
            this.tpe = tpe;
	    this.balanced = balanced;
	    this.d = d;
	    this.nCalc = nCalc;
        }

        public Integer call() {
            int subCount = 0;
            for (int i = first; i < last; i++) {
                if (d[i] < 0.5) {
                    subCount++;
                }
                if (!balanced) {
		    for (int j = 0; j < i / 100; j++) {
			d[i] += j;
		    }
                }
		else {
		    for (int j = 0; j < nCalc; j++) {
			d[i] *= d[i];
		    }
		}

            }
	    return subCount;
        }
    }

    @State(Scope.Benchmark)
    public static class GlobalParams {
        @Param({"10"})
	public int nDoubles;

	@Param({"1"})
	public int nLeaves;

	@Param({"2"})
	public int nThreads;

	@Param({"true"})
	public boolean balanced;

	@Param({"0"})
	public int nCalc;

	public double[] d;

	@Setup
	public void setup() {
	    Random r = new Random();
	    d = new double[nDoubles];
	    for (int i = 0; i < d.length; i++) {
	        d[i] = r.nextDouble();
	    }
	}
    }

    @State(Scope.Benchmark)
    public static class ThreadPoolTest {
        private ThreadPoolExecutor tpe;
	private double[] d;
	private int nThreads;
	private boolean balanced;
	private int nCalc;

	@Setup
	public void setup(GlobalParams gp) {
	    this.d = gp.d;
	    tpe = new ThreadPoolExecutor(gp.nThreads, gp.nThreads,
	    		Long.MAX_VALUE, TimeUnit.SECONDS,
			new LinkedBlockingQueue());
	    nThreads = gp.nThreads;
	    balanced = gp.balanced;
	    nCalc = gp.nCalc;
	}

	@TearDown
	public void teardown() throws InterruptedException {
	    tpe.shutdown();
	    tpe.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
	}

	public int test() throws InterruptedException, ExecutionException {
            int curFirst = 0;
            int range = d.length / nThreads;
            int curLast = Math.min(d.length, range);
	    ArrayList<Future<Integer>> futures = new ArrayList<>();
            for (int i = 0; i < nThreads; i++) {
                 Future<Integer> f = tpe.submit(new ThreadPoolExecutorTask(d, curFirst, curLast, tpe, balanced, nCalc));
		 futures.add(f);
                 curFirst = curLast;
		 if (i == nThreads - 2) {
		     curLast = d.length;
		 }
		 else {
		     curLast += range;
		 }
            }
	    int count = 0;
	    for (Future<Integer> fi : futures) {
	        count += fi.get();
	    }
	    return count;
	}
    }

    @State(Scope.Benchmark)
    public static class ForkJoinTest {
        private ForkJoinPool fjp;
	private double[] d;
	private int nThreads;
	private boolean balanced;
	private int nCalc;
	private int nLeaves;

	@Setup
	public void setup(GlobalParams gp) {
	    this.d = gp.d;
	    fjp = new ForkJoinPool(gp.nThreads);
	    this.nThreads = gp.nThreads;
	    this.balanced = gp.balanced;
	    this.nCalc = gp.nCalc;
	    this.nLeaves = gp.nLeaves;
	}

	@TearDown
	public void teardown() throws InterruptedException {
	    fjp.shutdown();
	    fjp.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
	}

	public int test() throws InterruptedException, ExecutionException {
	    CalcTask task = new CalcTask(d, 0, d.length - 1, nLeaves, balanced, nCalc);
	    int i = fjp.invoke(task);
	    return i;
	}
    }

    @Benchmark
    public void testThreadPool(ThreadPoolTest tpt, Blackhole bh) throws InterruptedException, ExecutionException {
        int count = tpt.test();
	bh.consume(count);
	bh.consume(tpt.d);
    }

    @Benchmark
    public void testForkJoin(ForkJoinTest fjt, Blackhole bh) throws InterruptedException, ExecutionException {
        int count = fjt.test();
	bh.consume(count);
	bh.consume(fjt.d);
    }
}
