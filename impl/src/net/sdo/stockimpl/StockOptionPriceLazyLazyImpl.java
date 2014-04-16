/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo.stockimpl;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import net.sdo.stock.StockOptionPrice;

@Entity
@Table(name="StockOptionPrice")
public class StockOptionPriceLazyLazyImpl implements StockOptionPrice {
    @EmbeddedId
    private StockOptionPK id;

    @Column(precision = 30, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns(
        {@JoinColumn(name="symbol", referencedColumnName="SYMBOL",
                     insertable=false, updatable=false),
        @JoinColumn(name="priceDate", referencedColumnName="priceDate",
                    insertable=false, updatable=false)
        })
    private StockPriceLazyLazyImpl stock;

    @Override
    public String getSymbol() {
        return id.getSymbol();
    }

    @Override
    public Date getDate() {
        return id.getDate();
    }

    @Override
    public int getExpirationPeriod() {
        return id.getExpirationPeriod();
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    public void setId(StockOptionPK id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
