/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.sdo.stock.StockPriceHistory;
import net.sdo.stock.StockPriceUtils;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockPriceHistoryImpl;
import net.sdo.stockimpl.StockPriceHistoryLogger;

public class StockPriceHistoryBatcher {
    private static final NumberFormat nf =
        NumberFormat.getCurrencyInstance(Locale.US);
    private static int numStocks;
    private static int mode;


    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static void initEM() {
        String s = System.getProperty("MockEntityManager");
        if (s != null) {
            emf = new MockStockPriceEntityManagerFactory(s);
        } else {
            emf = Persistence.createEntityManagerFactory("StockPU");
        }
        em = emf.createEntityManager();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        Date startDate;
        Date endDate;
        int save = 0;

        numStocks = (args.length < 1) ? 10000 : Integer.parseInt(args[0]);
        if (args.length < 2) {
            startDate = df.parse("01/01/12");
            endDate = df.parse("12/31/12");
        } else {
            startDate = df.parse(args[1]);
            endDate = df.parse(args[2]);
        }
        if (args.length < 3) {
            mode = 0;
        } 
        else {
            mode = Integer.parseInt(args[3]);
        }
        if (args.length > 4) {
            save = Integer.parseInt(args[4]);
        }
        System.out.println("Num stocks " + numStocks + " " +
                           startDate + " " + endDate);

        initEM();
        StockPriceHistory[] saved = new StockPriceHistory[save];
        for (int i = 0; i < numStocks; i++) {
            String symbol = StockPriceUtils.makeSymbol(i);
            StockPriceHistory sph;
            if (mode == 0) {
                sph = new StockPriceHistoryImpl(symbol, startDate, endDate, em);
            }
            else {
                sph = new StockPriceHistoryLogger(symbol, startDate,
                              endDate, em);
            }
            System.out.println("For " + sph.getSymbol()
                + ": High " + nf.format(sph.getHighPrice())
                + ", Low " + nf.format(sph.getLowPrice())
                + ", Standard Deviation: " + sph.getStdDev().doubleValue());
            if (save > 0) {
                saved[i % save] = sph;
            }
        }
    }
}
