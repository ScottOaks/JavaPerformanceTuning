/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sdo.stock.StockPriceHistory;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockPriceHistoryCompress;
import net.sdo.stockimpl.StockPriceHistoryEager;
import net.sdo.stockimpl.StockPriceHistoryEagerTransient;
import net.sdo.stockimpl.StockPriceHistoryCompressLazy;
import net.sdo.stockimpl.StockPriceHistoryImpl;
import net.sdo.stockimpl.StockPriceHistoryLogger;

@Stateless
public class StockSessionBeanImpl implements StockSessionBeanLocal, StockSessionBeanRemote {
    private static int sleepTime;
    private static EntityManager mockEM;
    @PersistenceContext(unitName = "StockPU")
    protected EntityManager dbEM;

    static {
        String s = System.getProperty("EJBSleepTime");
        if (s != null) {
            sleepTime = Integer.parseInt(s);
        } else {
            sleepTime = -1;
        }
    }

    @Override
    public StockPriceHistory getHistory(String symbol, Date startDate,
                                  Date endDate, boolean doMock, int impl) {
        StockPriceHistory sph;
        EntityManager em;
        if (doMock) {
            if (mockEM == null) {
                mockEM = new MockStockPriceEntityManagerFactory(
                                System.getProperty("MockEntityManager")).
                             createEntityManager();
            }
            em = mockEM;
        }
        else {
            em = dbEM;
        }
        switch(impl) {
            case StockPriceHistory.STANDARD:
                sph = new StockPriceHistoryImpl(symbol, startDate, endDate, em);
                break;
            case StockPriceHistory.LOGGING:
                sph = new StockPriceHistoryLogger(symbol, startDate,
                                                  endDate, em);
                break;
            case StockPriceHistory.COMPRESS:
                sph = new StockPriceHistoryCompress(symbol, startDate,
                                                    endDate, em);
                break;
            case StockPriceHistory.COMPRESS_LAZY:
                sph = new StockPriceHistoryCompressLazy(symbol, startDate,
                                                        endDate, em);
                break;
            case StockPriceHistory.COMPRESS_EAGER:
                sph = new StockPriceHistoryEager(symbol, startDate,
                                                 endDate, em);
                break;
            case StockPriceHistory.COMPRESS_EAGER_TRANSIENT:
                sph = new StockPriceHistoryEagerTransient(symbol, startDate,
                                                          endDate, em);
                break;
            default:
                throw new
                IllegalArgumentException("Unknown Implementation type " + impl);
        }
        return sph;
    }

    @PostConstruct
    public void doPostConstruct() {
        if (sleepTime != -1) {
            try {
                Thread.currentThread().sleep(sleepTime);
            } catch (Exception e) {}
        }
    }
}
