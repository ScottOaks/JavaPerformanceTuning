/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.util.Date;
import javax.ejb.Local;
import net.sdo.stock.StockPriceHistory;

@Local
public interface StockSessionBeanLocal {
   StockPriceHistory getHistory(String symbol, Date startDate,
                                Date endDate, boolean doMock, int impl); 
}
