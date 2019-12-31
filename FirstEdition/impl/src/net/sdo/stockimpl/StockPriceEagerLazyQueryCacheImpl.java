/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo.stockimpl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import net.sdo.stock.StockOptionPrice;
import net.sdo.stock.StockPrice;
import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;

@Entity
@NamedQueries({
    @NamedQuery(name="findAll",
        //NB: Eclipselink-specific
        query="SELECT s FROM StockPriceEagerLazyQueryCacheImpl s ORDER BY s.id.symbol",
        hints=@QueryHint(name=QueryHints.CACHE_USAGE,
              value=CacheUsage.CheckCacheThenDatabase)
        ),
    @NamedQuery(name="findJoin",
        query="SELECT s FROM StockPriceEagerLazyQueryCacheImpl s JOIN FETCH s.optionsPrices ORDER BY s.id.symbol",
        hints=@QueryHint(name=QueryHints.CACHE_USAGE,
                value=CacheUsage.CheckCacheThenDatabase)
    )
})

@Table(name="StockPrice")
public class StockPriceEagerLazyQueryCacheImpl implements Serializable, StockPrice {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private StockPricePK id;
    
    @Column(precision = 30, scale = 2)
    private BigDecimal high;
    
    @Column(precision = 30, scale = 2)
    private BigDecimal low;
    
    @Column(precision = 30, scale = 2)
    private BigDecimal closingPrice;
    
    @Column(precision = 30, scale = 2)
    private BigDecimal openingPrice;
    
    @Column
    private boolean isYearHigh;
    
    @Column
    private boolean isYearLow;

    @OneToMany(mappedBy="stock", fetch=FetchType.LAZY)
    private Collection<StockOptionPriceEagerLazyQueryCacheImpl> optionsPrices;

    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public void setIsYearHigh(boolean isYearHigh) {
        this.isYearHigh = isYearHigh;
    }

    public void setIsYearLow(boolean isYearLow) {
        this.isYearLow = isYearLow;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public void setOpeningPrice(BigDecimal openingPrice) {
        this.openingPrice = openingPrice;
    }

    public StockPriceEagerLazyQueryCacheImpl() {
    }
    
    StockPriceEagerLazyQueryCacheImpl(StockPricePK pk) {
        id = pk;
    }

    @Override
    public Date getDate() {
        return id.getDate();
    }

    @Override
    public String getSymbol() {
        return id.getSymbol();
    }

    @Override
    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    @Override
    public BigDecimal getHigh() {
        return high;
    }

    @Override
    public boolean isYearHigh() {
        return isYearHigh;
    }

    @Override
    public boolean isYearLow() {
        return isYearLow;
    }

    @Override
    public BigDecimal getLow() {
        return low;
    }

    @Override
    public BigDecimal getOpeningPrice() {
        return openingPrice;
    }

    @Override
    public Collection<? extends StockOptionPrice> getOptions() {
        return optionsPrices;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public StockPricePK getId() {
        return id;
    }

    public void setId(StockPricePK id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StockPriceEagerLazyQueryCacheImpl)) {
            return false;
        }
        StockPriceEagerLazyQueryCacheImpl other = (StockPriceEagerLazyQueryCacheImpl) object;
        if ((this.id == null && other.id != null) ||
            (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.sdo.StockPrice[ id=" + id + " ]";
    }
}
