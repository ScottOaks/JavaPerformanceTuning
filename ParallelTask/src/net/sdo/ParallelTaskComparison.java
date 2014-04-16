/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */
package net.sdo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ParallelTaskComparison {
    private static int nThreads;
    private static boolean unbalance;

    private static class ForkJoinTask extends RecursiveTask<Integer> implements Callable<Integer> {
        private double[] d;
        public volatile double dummy;
        private int first;
        private int last;
        private int nLeaves;

        public ForkJoinTask(double[] d, int first, int last, int nLeaves) {
            this.first = first;
            this.last = last;
            this.d = d;
            this.nLeaves = nLeaves;
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
                    if (unbalance) {
                        for (int j = 0; j < d.length - i; j++) {
                            for (int k = 0; k < 100; k++) {
                                dummy = j * k + i;
                                d[i] = dummy;
                            }
                        }
                    }
                }
            } else {
                int mid = (first + last) >>> 1;
                ForkJoinTask left = 
                    new ForkJoinTask(d, first, mid, nLeaves);
                left.fork();
                ForkJoinTask right =
                    new ForkJoinTask(d, mid + 1, last, nLeaves);
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

    private static class ThreadPoolExecutorTask implements Callable<Integer>, Runnable {
        private double[] d;
        private int first;
        private int last;
        private volatile double dummy;
        private ThreadPoolExecutor tpe;
        private static AtomicInteger count = new AtomicInteger();

        public ThreadPoolExecutorTask(double[] d, int first, int last, ThreadPoolExecutor tpe) {
            this.first = first;
            this.last = last;
            this.d = d;
            this.tpe = tpe;
        }

        public void run() {
            int subCount = 0;
            for (int i = first; i <= last; i++) {
                if (d[i] < 0.5) {
                    subCount++;
                }
                if (unbalance) {
                    for (int j = 0; j < d.length - i; j++) {
                        for (int k = 0; k < 100; k++) {
                            dummy = j * k + i;
                            d[i] = dummy;
                        }
                    }
                }
            }
            count.addAndGet(subCount);
        }

        public Integer call() throws Exception {
            int curFirst = first;
            int range = (last - first) / nThreads;
            int curLast = Math.min(last, first + range);
            for (int i = 0; i < nThreads; i++) {
                 tpe.execute(new ThreadPoolExecutorTask(d, curFirst, curLast, tpe));
                 curFirst = curLast + 1;
                 if (i == nThreads - 2) {
                     curLast = Math.max(last, curLast + range);
                 }
                 else curLast = Math.min(last, curLast + range);
            }
            tpe.shutdown();
            return count.get();
        }
    }

    public static void main(String[] args) throws Exception {
        int nLeaves = 10;
        nThreads = Integer.parseInt(args[0]);
        int nDoubles = Integer.parseInt(args[1]);
        double[] d = new double[nDoubles];
        Random r = new Random(12345);
        for (int i = 0; i < d.length; i++) {
            d[i] = r.nextDouble();
        }
        ExecutorService executor;
        Callable c;
        int mode = Integer.parseInt(args[2]);
        switch (mode) {
            case 0:
                ThreadPoolExecutor tpe = new ThreadPoolExecutor(nThreads,
                    nThreads, Long.MAX_VALUE,
                    TimeUnit.SECONDS, new LinkedBlockingQueue());
                c = new ThreadPoolExecutorTask(d, 0, nDoubles - 1, tpe);
                executor = tpe;
                break;
            case 1:
                executor = new ForkJoinPool(nThreads);
                c = new ForkJoinTask(d, 0, nDoubles - 1, nLeaves);
                break;
            default:
                throw new IllegalArgumentException("Bad arg2 switch");

        }
        unbalance = Boolean.parseBoolean(args[3]);
        if (args.length > 4) {
            nLeaves = Integer.parseInt(args[4]);
        }
        long then = System.currentTimeMillis();
        Future<Integer> f = executor.submit(c);
        int count;
        if (mode == 0) {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            count = ThreadPoolExecutorTask.count.get();
        }
        else {
            count = f.get();
        }
        long now = System.currentTimeMillis();
        System.err.println("Calculated " + count + " in:  " + (now - then));
        System.exit(0);
    }
}
