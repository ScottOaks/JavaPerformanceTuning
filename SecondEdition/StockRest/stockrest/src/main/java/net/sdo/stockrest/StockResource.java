package net.sdo.stockrest;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sdo.stock.StockPriceHistory;
import net.sdo.stock.StockPriceUtils;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockPriceHistoryCompress;
import net.sdo.stockimpl.StockPriceHistoryEager;
import net.sdo.stockimpl.StockPriceHistoryEagerTransient;
import net.sdo.stockimpl.StockPriceHistoryCompressLazy;
import net.sdo.stockimpl.StockPriceHistoryImpl;
import net.sdo.stockimpl.StockPriceHistoryLogger;

@Path("/stock")
@RequestScoped
public class StockResource {
    private ThreadLocal<DateFormat> localDf = new ThreadLocal<DateFormat>() {
    	@Override
        public DateFormat initialValue() {
	        return DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
	}
    };

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    private static EntityManager mockEM;
    @PersistenceContext(unitName = "StockPU")
    protected EntityManager dbEM;
    private static ArrayList<StockPriceHistory> al = new ArrayList<>();

    public StockResource() {
    }

    /**
     * Return stock info
     *
     * @return {@link JsonObject}
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getStockInfo(
	@DefaultValue("" + StockPriceHistory.STANDARD) @QueryParam("impl") int impl,
	@DefaultValue("true") @QueryParam("doMock") boolean doMock,
	@DefaultValue("") @QueryParam("symbol") String symbol,
	@DefaultValue("01/01/2019") @QueryParam("start") String start,
	@DefaultValue("01/01/2034") @QueryParam("end") String end,
	@DefaultValue("0") @QueryParam("save") int saveCount
	) throws ParseException {
        StockPriceHistory sph;
	EntityManager em;
	DateFormat df = localDf.get();
	Date startDate = df.parse(start);
	Date endDate = df.parse(end);

	if (symbol.length() == 0) {
	    symbol = StockPriceUtils.getRandomSymbol();
	}
        if (doMock) {
	    if (mockEM == null) {
	        mockEM = new MockStockPriceEntityManagerFactory("MockEntityManager").createEntityManager();
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
	if (saveCount > 0) {
	    synchronized(al) {
	        if (al.size() > saveCount) {
		    al.remove(0);
		}
		al.add(sph);
	    }
	}
        return JSON.createObjectBuilder()
                .add("symbol", sph.getSymbol())
                .add("high", sph.getHighPrice())
                .add("low", sph.getLowPrice())
                .add("average", sph.getAveragePrice())
                .add("stddev", sph.getStdDev())
                .build();
    }
}
