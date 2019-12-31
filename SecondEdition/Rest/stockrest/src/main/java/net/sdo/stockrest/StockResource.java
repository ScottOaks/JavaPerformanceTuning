package net.sdo.stockrest;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NameBinding;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.sdo.stock.StockPrice;
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
@ApplicationScoped
public class StockResource {
    private static ThreadPoolExecutor tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(64);
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

    protected StockPriceHistory getStockPriceHistory(int impl,
		boolean doMock, String symbol,
		String start, String end, int saveCount) 
		throws ParseException {
    /**
     * Return stock info
     *
     * @return {@link JsonObject}
     */
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
	return sph;
    }

    private static String cached = null;
    @GET
    @Compress
    @Produces(MediaType.APPLICATION_JSON)
    public String getStockInfo(
	@DefaultValue("" + StockPriceHistory.STANDARD) @QueryParam("impl") int impl,
	@DefaultValue("true") @QueryParam("doMock") boolean doMock,
	@DefaultValue("") @QueryParam("symbol") String symbol,
	@DefaultValue("01/01/2019") @QueryParam("start") String start,
	@DefaultValue("01/01/2034") @QueryParam("end") String end,
	@DefaultValue("0") @QueryParam("save") int saveCount,
	@DefaultValue("false") @QueryParam("useCached") boolean useCached
	) throws ParseException {

	if (useCached && cached != null) {
	    return cached;
	}
	StockPriceHistory sph = getStockPriceHistory(impl, doMock,
				symbol, start, end, saveCount);
        cached = JSON.createObjectBuilder()
                .add("symbol", sph.getSymbol())
                .add("high", sph.getHighPrice().setScale(2, RoundingMode.HALF_UP))
                .add("low", sph.getLowPrice().setScale(2, RoundingMode.HALF_UP))
                .add("average", sph.getAveragePrice().setScale(2, RoundingMode.HALF_UP))
                .add("stddev", sph.getStdDev().setScale(2, RoundingMode.HALF_UP))
                .build().toString();
	return cached;
    }

    private static String extendedCached;
    @GET
    @Compress
    @Path("/extended")
    @Produces(MediaType.APPLICATION_JSON)
    public String getExtendedStockInfo(
	@DefaultValue("" + StockPriceHistory.STANDARD) @QueryParam("impl") int impl,
	@DefaultValue("true") @QueryParam("doMock") boolean doMock,
	@DefaultValue("") @QueryParam("symbol") String symbol,
	@DefaultValue("01/01/2019") @QueryParam("start") String start,
	@DefaultValue("01/01/2034") @QueryParam("end") String end,
	@DefaultValue("0") @QueryParam("save") int saveCount,
	@DefaultValue("false") @QueryParam("useCached") boolean useCached
	) throws ParseException {
	
	if (useCached && extendedCached != null) {
	    return extendedCached;
	}
	StockPriceHistory sph = getStockPriceHistory(impl, doMock,
				symbol, start, end, saveCount);
        JsonArrayBuilder arr = JSON.createArrayBuilder();
	for (Map.Entry<Date, StockPrice> entry : sph.getAllEntries().entrySet()) {
	    arr.add(
	        JSON.createObjectBuilder()
		    .add("date", entry.getKey().getTime())
	    	    .add("price", entry.getValue()
		    			.getClosingPrice().setScale(2, RoundingMode.HALF_UP))
		    .build()
	    );
	}
        extendedCached = JSON.createObjectBuilder()
                .add("symbol", sph.getSymbol())
		.add("prices", arr).build().toString();
	return extendedCached;
    }

    @GET
    @Path("/sleep")
    @Produces(MediaType.APPLICATION_JSON)
    public String sleepEndpoint(
	@DefaultValue("100") @QueryParam("delay") long delay
	) throws ParseException {
	try { Thread.sleep(delay); } catch (InterruptedException ie) {}
	return "{\"sleepTime\": \"" + delay + "\"}";
    }

    @GET
    @Path("/asyncsleep")
    @Produces(MediaType.APPLICATION_JSON)
    public void sleepAsyncEndpoint(
	@DefaultValue("100") @QueryParam("delay") long delay,
	@Suspended final AsyncResponse ar
	) throws ParseException {
	tpe.execute(() -> {
	    try { Thread.sleep(delay); } catch (InterruptedException ie) {}
	    ar.resume("{\"sleepTime\": \"" + delay + "\"}");
	});
    }

    @GET
    @Path("/asyncreject")
    @Produces(MediaType.APPLICATION_JSON)
    public void sleepAsyncRejectEndpoint(
	@DefaultValue("100") @QueryParam("delay") long delay,
	@Suspended final AsyncResponse ar
	) throws ParseException {
	if (tpe.getActiveCount() == 64) {
	    ar.cancel();
	    return;
	}
	tpe.execute(() -> {
	    try { Thread.sleep(delay); } catch (InterruptedException ie) {}
	    ar.resume("{\"sleepTime\": \"" + delay + "\"}");
	});
    }
}
