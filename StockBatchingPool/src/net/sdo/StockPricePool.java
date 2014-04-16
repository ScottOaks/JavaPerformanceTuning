/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
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

public class StockPricePool {
    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

    private static class StockCalcTask implements Callable<Object> {

        private int stockId;
        private int max;
        private int mode;
        private Date startDate, endDate;
        private EntityManager em;
        private boolean verbose;

        public StockCalcTask(int i, Date startDate, Date endDate, int max, int mode, EntityManager em, boolean verbose) {
            stockId = i;
            this.startDate = startDate;
            this.endDate = endDate;
            this.max = max;
            this.mode = mode;
            this.em = em;
            this.verbose = verbose;
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
                if (verbose) {
                    System.out.println("For " + sph.getSymbol()
                        + ": High " + nf.format(sph.getHighPrice())
                        + ", Low " + nf.format(sph.getLowPrice())
                        + ", Standard Deviation: " + sph.getStdDev().doubleValue());
                } else {
                    sph.getHighPrice(); // Force recalc
                }
            } catch (Exception re) {
                re.printStackTrace();
                throw new RuntimeException(re);
            }
            return null;
        }
    }

    /**
     * @param args the command line arguments
     */
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
        int coreSize = 1;
        int maxSize = Runtime.getRuntime().availableProcessors();
        BlockingQueue<Runnable> queue =
            new LinkedBlockingQueue<>();
        int numTasks = 10000;
        Date startDate = df.parse("01/01/01");
        Date endDate = df.parse("12/31/25");

        if (args.length > 0) {
            coreSize = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            maxSize = Integer.parseInt(args[1]);
        }
        if (args.length > 2) {
            switch (Integer.parseInt(args[2])) {
                case 0:
                    // Use LinkedBlockingQueue
                    break;
                case 1:
                    queue = new SynchronousQueue<>();
                    break;
                case 2:
                    queue = new ArrayBlockingQueue<>(numTasks);
                    break;
                default:
                    throw new IllegalArgumentException("unknown queue argument");

            }
        }

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(coreSize,
            maxSize, Long.MAX_VALUE, TimeUnit.SECONDS, queue);
        tpe.prestartAllCoreThreads();

        System.out.println("Calculating " + numTasks +
            " histories from " + startDate + " to " + endDate +
            " verbose=" + verbose);
        long then = System.currentTimeMillis();
        for (int i = 0; i < numTasks; i++) {
            tpe.submit(
                new StockCalcTask(i, startDate, endDate,
                numTasks, StockPriceUtils.STANDARD, em, verbose));
        }
        tpe.shutdown();
        tpe.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        long now = System.currentTimeMillis();
        System.err.println("Elapsed:  " + (now - then));
        tpe.shutdownNow();
    }
}
