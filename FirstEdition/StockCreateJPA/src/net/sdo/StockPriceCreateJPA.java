/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
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
import net.sdo.stockimpl.StockOptionPriceEagerLazyImpl;
import net.sdo.stockimpl.StockPriceEagerLazyImpl;
import net.sdo.stockimpl.StockPricePK;

public class StockPriceCreateJPA extends Thread {

    private static final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
    private static long msPerDay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
    private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
    private static Date startDate;
    private static Date endDate;
    private static EntityManager mockEM = new MockStockPriceEntityManagerFactory("MockEntityManager").createEntityManager();
    private static int nTransactions = 1;
    private static boolean reuseEM;
    private static boolean reuseTX;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("StockPU");

    private int startStock;
    private int numStocks;

    public StockPriceCreateJPA(int start, int num) {
        startStock = start;
        numStocks = num;
    }

    @Override
    public void run() {
        try {
            EntityManager em = emf.createEntityManager();
            EntityTransaction txn = em.getTransaction();
            txn.begin();
            for (int i = startStock; i < numStocks; i++) {
                String symbol = StockPriceUtils.makeSymbol(i);
                Date curDate = new Date(startDate.getTime());
                System.out.println(Thread.currentThread() + ": Processing " + i + ": " + symbol);
                while (!curDate.after(endDate)) {
                    StockPrice sp = mockEM.find(StockPriceEagerLazyImpl.class, new StockPricePK(symbol, (Date) curDate.clone()));
                    if (sp != null) {
                        em.persist(sp);
                        for (int j = 0; j < 5; j++) {
                            StockOptionPriceEagerLazyImpl sop = new StockOptionPriceEagerLazyImpl();
                            sop.setId(new StockOptionPK(sp.getSymbol(), sp.getDate(), j));
                            sop.setPrice(sp.getClosingPrice().multiply(new BigDecimal(1 + j / 100.)));
                            em.persist(sop);
                        }
                    }
                    curDate.setTime(curDate.getTime() + msPerDay);
                }
                if ((i % nTransactions) == nTransactions - 1) {
                    txn.commit();
                    if (!reuseEM) {
                        em.close();
                        em = emf.createEntityManager();
                        txn = em.getTransaction();
                    }
                    if (reuseEM && !reuseTX) {
                        txn = em.getTransaction();
                    }
                    txn.begin();
                }
            }
            txn.commit();
            em.close();
            System.out.println(Thread.currentThread() + ": Complete");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {
        int curArg = 0;
        int nThreads = Integer.parseInt(args[curArg++]);
        int num = Integer.parseInt(args[curArg++]);
        int numPerThread = num / nThreads;
        startDate = df.parse(args[curArg++]);
        endDate = df.parse(args[curArg++]);
        nTransactions = Integer.parseInt(args[curArg++]);
        reuseEM = Boolean.parseBoolean(args[curArg++]);
        reuseTX = Boolean.parseBoolean(args[curArg++]);
        StockPriceCreateJPA[] threads = new StockPriceCreateJPA[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new StockPriceCreateJPA(numPerThread * i, numPerThread * i + numPerThread);
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
