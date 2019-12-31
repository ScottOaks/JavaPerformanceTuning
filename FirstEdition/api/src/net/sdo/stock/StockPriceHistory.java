/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo.stock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface StockPriceHistory {
    public static final int STANDARD = 0;
    public static final int LOGGING = 1;
    public static final int COMPRESS = 2;
    public static final int COMPRESS_LAZY = 3;
    public static final int COMPRESS_SKIP = 4; // NOT USED
    public static final int COMPRESS_EAGER = 5;
    public static final int COMPRESS_EAGER_TRANSIENT = 6;

    StockPrice getPrice(Date d);
    Collection<StockPrice> getPrices(Date startDate, Date endDate);
    Map<Date, StockPrice> getAllEntries();
    Map<BigDecimal,ArrayList<Date>> getHistogram();
    BigDecimal getAveragePrice();
    Date getFirstDate();
    BigDecimal getHighPrice();
    Date getLastDate();
    BigDecimal getLowPrice();
    BigDecimal getStdDev();
    String getSymbol();
}
