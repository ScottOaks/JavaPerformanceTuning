/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import net.sdo.stock.StockOptionPrice;
import net.sdo.stock.StockPrice;
import net.sdo.stock.StockPriceUtils;
import net.sdo.stockimpl.StockPriceEagerLazyImpl;
import net.sdo.stockimpl.StockPricePK;

public class StockPriceReadJPA {

    private static long msPerDay = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
    private static EntityManagerFactory emf;
    private static volatile BigDecimal bd;
    private static boolean batch;
    private static boolean processOptions = true;
    private static boolean eagerMtO;
    private static boolean lazyOtM;
    private static boolean join;
    private static boolean debug;
    private static boolean doSingle;
    private static boolean queryCache;

    private static long run(String query) {
        long then = System.currentTimeMillis();
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery(query);
        if (batch) {
            q.setHint("eclipselink.JDBC_FETCH_SIZE", "1");
        }
        if (debug) {
            System.out.println("Start Execute Query");
        }
        List<StockPrice> l = q.getResultList();
        if (debug) {
            System.out.println("End Execute Query");
        }
        String lastSymbol = "";
        for (StockPrice sp : l) {
            if (!sp.getSymbol().equals(lastSymbol)) {
                lastSymbol = sp.getSymbol();
                if (debug) System.out.println("Processing " + lastSymbol);
            }
            if (debug) {
                System.out.println("Start Visit Stock price");
            }
            bd = sp.getClosingPrice();
            if (processOptions) {
                Collection<? extends StockOptionPrice> options = sp.getOptions();
                for (StockOptionPrice sop : options) {
                    if (debug) {
                        System.out.println("Start Visit Option price");
                    }
                    bd = sop.getPrice().add(bd);
                }
            }
        }
        em.close();
        return System.currentTimeMillis() - then;
    }

    private static long run() {
        long then = System.currentTimeMillis();
        EntityManager em = emf.createEntityManager();
        String lastSymbol = "";
        for (int i = 0; i < 128; i++) {
            String symbol = StockPriceUtils.makeSymbol(i);
            for (Date d : validDates) {
                    if (debug) {
                    System.out.println("Do single lookup ");
                }
                StockPrice sp = em.find(StockPriceEagerLazyImpl.class, new StockPricePK(symbol, d));
                if (sp != null) {
                    bd = sp.getClosingPrice();
                    if (processOptions) {
                        Collection<? extends StockOptionPrice> options = sp.getOptions();
                        for (StockOptionPrice sop : options) {
                            if (debug) {
                                System.out.println("Do single option lookup ");
                            }
                            bd = sop.getPrice().add(bd);
                        }
                    }
                }
            }
        }
        em.close();
        return System.currentTimeMillis() - then;
    }
    private static ArrayList<Date> validDates = new ArrayList<>();

    private static void makeDates() {
        Date d = new Date("1/1/13");
        Date endDate = new Date("12/31/13");
        Calendar calendar = Calendar.getInstance();
        while (d.before(endDate)) {
            calendar.setTime(d);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                validDates.add((Date) d.clone());
            }
            d.setTime(d.getTime() + msPerDay);
        }
    }

    public static void main(String[] args) throws IOException {
        int curArg = 0;
        while (curArg < args.length) {
            switch(args[curArg++]) {
                case "-b": batch = Boolean.parseBoolean(args[curArg]); break;
                case "-p": processOptions = Boolean.parseBoolean(args[curArg]); break;
                case "-e": eagerMtO = Boolean.parseBoolean(args[curArg]); break;
                case "-l": lazyOtM = Boolean.parseBoolean(args[curArg]); break;
                case "-j": join = Boolean.parseBoolean(args[curArg]); break;
                case "-d": debug = Boolean.parseBoolean(args[curArg]); break;
                case "-s": doSingle = Boolean.parseBoolean(args[curArg]); break;
                case "-q": queryCache = Boolean.parseBoolean(args[curArg]); break;
            }
            curArg++;
        }
        if (doSingle) {
            makeDates();
        }

        String stockPU;
        String query;
        if (lazyOtM) {
            if (eagerMtO) {
                stockPU = "StockPULazyEager";
                query = "findAll";
                System.out.println("Using LazyEager");
            } else {
                stockPU = "StockPULazyLazy";
                query = "findAll";
                System.out.println("Using LazyLazy");
            }
        }
        else {
            if (eagerMtO) {
                stockPU = "StockPUEagerEager";
                query = "findAll";
                System.out.println("Using EagerEager");
            } else {
                if (queryCache) {
                    stockPU = "StockPUEagerLazyQueryCache";
                    query = "findAll";
                    System.out.println("Using EagerLazyQueryCache");
                }
                else {
                    stockPU = "StockPUEagerLazy";
                    query = "findAll";
                    System.out.println("Using EagerLazy");
                }
            }
        }

        if (join) {
            query = "findJoin";
        }
        if (doSingle) {
            query = null;
        }
        emf = Persistence.createEntityManagerFactory(stockPU);
        System.out.println("Starting First pass");
        long elapsed = (query != null) ? run(query) : run();
        System.out.println("First pass completed in " + elapsed);

        System.out.println("Starting second pass");
        elapsed = (query != null) ? run(query) : run();
        System.out.println("Second pass completed in " + elapsed);
    }
}
