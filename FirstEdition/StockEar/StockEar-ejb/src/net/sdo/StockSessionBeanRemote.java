/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.util.Date;
import javax.ejb.Remote;
import net.sdo.stock.StockPriceHistory;

@Remote
public interface StockSessionBeanRemote {
    StockPriceHistory getHistory(String symbol, Date startDate,
                                 Date endDate, boolean doMock, int impl); 
}
