/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo.rest;

import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.persistence.EntityManager;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import net.sdo.stock.StockPrice;
import net.sdo.stock.StockPriceHistory;
import net.sdo.stock.StockPriceUtils;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockPriceHistoryImpl;

@Path("StockPrice")
public class StockPriceResource {
    private static Map<String,SoftReference<String>> savedData = new ConcurrentHashMap<>();
    @Context
    private UriInfo context;

    private static EntityManager em;

    static {
        em = new MockStockPriceEntityManagerFactory("MockEntityManager").createEntityManager();
    }

    @GET
    @Produces("application/json")
    public String getDetailData(
        @QueryParam("symbol") String symbol,
        @QueryParam("startDate") String startDate,
        @QueryParam("endDate") String endDate,
        @DefaultValue("0") @QueryParam("mode") int mode,
        @DefaultValue("false") @QueryParam("cache") boolean doCache
    ) {
        
        if (symbol == null || symbol.length() == 0) {
            symbol = StockPriceUtils.getRandomSymbol();
        }
        if (startDate == null || startDate.length() == 0) {
            startDate = "1/1/2001";
        }
        if (endDate == null || endDate.length() == 0) {
            endDate = "12/31/2025";
        }
        String s = symbol + ":" + startDate + ":" + endDate + ":" + mode;
        String json = null;
        if (doCache) {
            SoftReference<String> sr = savedData.get(s);
            if (sr != null) {
            json = sr.get();
            }
        }
        if (json == null) {
            StockPriceHistory sph;
            sph = new StockPriceHistoryImpl(symbol, new Date(startDate), new Date(endDate), em);
            json = toJsonString(sph, mode);
            if (doCache) {
                savedData.put(s, new SoftReference(json));
            }
        }
        return json;
    }

    public String toJsonString(StockPriceHistory sph, int mode) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job = job.add("symbol", sph.getSymbol()).
            add("avgPrice", sph.getAveragePrice()).
            add("highPrice", sph.getHighPrice()).
            add("lowPrice", sph.getLowPrice()).
            add("stdDev", sph.getStdDev()).
            add("firstDate", sph.getFirstDate().getTime()).
            add("lastDate", sph.getLastDate().getTime());
        if (mode != 0) {
            JsonArrayBuilder pBuilder = Json.createArrayBuilder();
            for (Map.Entry<Date, StockPrice> entry : sph.getAllEntries().entrySet()) {
                JsonObjectBuilder oBuilder = Json.createObjectBuilder();
                StockPrice sp = entry.getValue();
                pBuilder = pBuilder.add(
                    oBuilder.add("date", entry.getKey().getTime()).
                    add("high", sp.getHigh()).
                    add("low", sp.getLow()).
                    add("open", sp.getOpeningPrice()).
                    add("close", sp.getClosingPrice()).
                    add("isYearHigh", sp.isYearHigh()).
                    add("isYearLow", sp.isYearLow()));
            }
            job.add("prices", pBuilder);
        }
        StringWriter sw = new StringWriter();
        try (JsonWriter jw = Json.createWriter(sw)) {
            jw.writeObject(job.build());
        }
        return sw.toString();
    }
}
