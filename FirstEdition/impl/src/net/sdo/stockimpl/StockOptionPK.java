/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo.stockimpl;

import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class StockOptionPK {
    private String symbol;
    @Temporal(TemporalType.DATE)
    private Date priceDate;
    private int expirationPeriod;

    public StockOptionPK() {
    }

    public StockOptionPK(String s, Date d, int i) {
        symbol = s;
        priceDate = d;
        expirationPeriod = i;
    }

    public String getSymbol() {
        return symbol;
    }

    public Date getDate() {
        return priceDate;
    }

    public int getExpirationPeriod() {
        return expirationPeriod;
    }
}
