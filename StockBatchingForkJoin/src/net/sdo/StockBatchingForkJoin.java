/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.sdo.stock.StockPriceHistory;
import net.sdo.stock.StockPriceUtils;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockPriceHistoryImpl;

public class StockBatchingForkJoin {

    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

    private static class StockForkTask extends RecursiveAction {
        private static int mode;
        private static Date startDate, endDate;
        private static EntityManager em;
        private static boolean verbose;

        private int first;
        private int last;

        public StockForkTask(int first, int last) {
            this.first = first;
            this.last = last;
        }

        @Override
        protected void compute() {
            if (first == last) {
                try {
                    String symbol = StockPriceUtils.makeSymbol(first);
                    StockPriceHistory sph;
                    sph = new StockPriceHistoryImpl(symbol, startDate, endDate, em);
                    if (verbose) {
                        System.out.println("For " + sph.getSymbol()
                            + ": High " + nf.format(sph.getHighPrice())
                            + ", Low " + nf.format(sph.getLowPrice())
                            + ", Standard Deviation: " + sph.getStdDev().doubleValue());
                    } else {
                        sph.getHighPrice(); // Force recalc
                    }
                } catch (RuntimeException re) {
                    re.printStackTrace();
                    throw re;
                }
            } else {
                int mid = (first + last) >>> 1;
                invokeAll(new StockForkTask(first, mid),
                      new StockForkTask(mid + 1, last));
            }
        }
    }

    public static void main(String[] args) throws ParseException, InterruptedException {
        EntityManager em;
        EntityManagerFactory emf;
        boolean verbose = Boolean.getBoolean("verbose");
        String s = System.getProperty("MockEntityManager");
        if (s != null) {
            emf = new MockStockPriceEntityManagerFactory(s);
        } else {
            emf = Persistence.createEntityManagerFactory("StockPU");
        }
        em = emf.createEntityManager();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        Date startDate = df.parse("01/01/01");
        Date endDate = df.parse("12/31/25");

        StockForkTask.startDate = startDate;
        StockForkTask.endDate = endDate;
        StockForkTask.em = em;
        StockForkTask.verbose = verbose;
        ForkJoinPool pool;
        if (args.length > 0) {
            pool = new ForkJoinPool(Integer.parseInt(args[0]));
        }
        else {
            pool = new ForkJoinPool();
        }
        long then = System.currentTimeMillis();
        pool.invoke(new StockForkTask(1, 1000));
        long now = System.currentTimeMillis();
        System.err.println("Elapsed:  " + (now - then));
    }
}
