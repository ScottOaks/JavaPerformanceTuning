/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo.stockimpl;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class StockPricePK implements Serializable {
    private String symbol;

    @Temporal(TemporalType.DATE)
    private Date priceDate;

    public StockPricePK() {
    }

    public StockPricePK(String symbol, Date date) {
        this.symbol = symbol;
        this.priceDate = date;
    }

    public Date getDate() {
        return priceDate;
    }

    public String getSymbol() {
        return symbol;
    }
}
