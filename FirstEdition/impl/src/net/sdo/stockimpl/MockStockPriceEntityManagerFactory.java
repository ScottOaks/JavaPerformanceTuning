/*
 * Copyright (c) 2013 Scott Oaks. All rights reserved.
 */

package net.sdo.stockimpl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

public class MockStockPriceEntityManagerFactory implements EntityManagerFactory {
    static private EntityTransaction dummyTransaction = new EntityTransaction() {
        @Override
        public void begin() {}

        @Override
        public void commit() {}

        @Override
        public void rollback() {}

        @Override
        public void setRollbackOnly() {}

        @Override
        public boolean getRollbackOnly() { return false; }

        @Override
        public boolean isActive() { return false; }
    };

    @Override
    public EntityManager createEntityManager(SynchronizationType st) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public EntityManager createEntityManager(SynchronizationType st, Map map) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void addNamedQuery(String string, Query query) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public <T> void addNamedEntityGraph(String string, EntityGraph<T> eg) {
        throw new UnsupportedOperationException("Not supported.");
    }

    private static class DataHolder {
        public Random random = ThreadLocalRandom.current();
        public String symbol;
        public BigDecimal lastClose;
        public Calendar calendar = Calendar.getInstance();
    }
    
    private static ThreadLocal<DataHolder> tlDataHolder =
           new ThreadLocal<DataHolder>() {
        @Override
        public DataHolder initialValue() {
            return new DataHolder();
        }
    };
    
    class MockEntityManager implements EntityManager {
        @Override
        public void persist(Object o) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public <T> T merge(T t) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void remove(Object o) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public <T> T find(Class<T> type, Object o) {
            return findWithHistory(tlDataHolder.get(), o);
        }

        protected <T> T findWithHistory(DataHolder dh, Object o) {
            StockPricePK pk = (StockPricePK) o;
            StockPriceEagerLazyImpl sp = new StockPriceEagerLazyImpl(pk);
            dh.calendar.setTime(pk.getDate());
            if (dh.calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                dh.calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                return null;
            }
    
            if (dh.symbol == null || !dh.symbol.equals(pk.getSymbol())) {
                dh.symbol = pk.getSymbol();
                sp.setOpeningPrice(
                        new BigDecimal(
                             dh.random.nextDouble() * 100).
                                 setScale(2, BigDecimal.ROUND_HALF_UP));
            }
            else {
                sp.setOpeningPrice(dh.lastClose);
            }
            double pctChange = dh.random.nextDouble() * dh.random.nextDouble();
            pctChange *= (dh.random.nextDouble() < 0.6) ? 0.1 : -0.1;
            pctChange += 1.0;
            sp.setClosingPrice(sp.getOpeningPrice().
                multiply(new BigDecimal(pctChange).
                setScale(2, BigDecimal.ROUND_HALF_UP)).
                setScale(2, BigDecimal.ROUND_HALF_UP));
            if (sp.getClosingPrice().compareTo(BigDecimal.ZERO) < 0) {
                sp.setClosingPrice(BigDecimal.ZERO);
            }
            
            pctChange = dh.random.nextDouble() * dh.random.nextDouble() * 0.1;
            sp.setHigh(sp.getOpeningPrice().
                multiply(new BigDecimal(pctChange * dh.random.nextDouble())).
                setScale(2, BigDecimal.ROUND_HALF_UP));
            if (sp.getHigh().compareTo(sp.getClosingPrice()) < 0) {
                sp.setHigh(sp.getClosingPrice());
            }
            if (sp.getHigh().compareTo(sp.getOpeningPrice()) < 0) {
                sp.setHigh(sp.getOpeningPrice());
            }
            
            pctChange = dh.random.nextDouble() * dh.random.nextDouble() * 0.1;
            BigDecimal dailyLoss = sp.getClosingPrice().
                multiply(new BigDecimal(pctChange).
                setScale(2, BigDecimal.ROUND_HALF_UP)).
                setScale(2, BigDecimal.ROUND_HALF_UP);
            sp.setLow(sp.getOpeningPrice().subtract(dailyLoss));
            if (sp.getLow().compareTo(sp.getClosingPrice()) > 0) {
                sp.setLow(sp.getClosingPrice());
            }
            dh.lastClose = sp.getClosingPrice();
        
            return (T) sp;
        }

        @Override
        public <T> T find(Class<T> type, Object o, Map<String, Object> map) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public <T> T find(Class<T> type, Object o, LockModeType lmt) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public <T> T find(Class<T> type, Object o,
                  LockModeType lmt, Map<String, Object> map) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public <T> T getReference(Class<T> type, Object o) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void flush() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void setFlushMode(FlushModeType fmt) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public FlushModeType getFlushMode() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void lock(Object o, LockModeType lmt) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void lock(Object o, LockModeType lmt, Map<String, Object> map) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void refresh(Object o) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void refresh(Object o, Map<String, Object> map) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void refresh(Object o, LockModeType lmt) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void refresh(Object o, LockModeType lmt, Map<String, Object> map) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void detach(Object o) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public boolean contains(Object o) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public LockModeType getLockMode(Object o) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void setProperty(String string, Object o) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Map<String, Object> getProperties() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Query createQuery(String string) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public <T> TypedQuery<T> createQuery(CriteriaQuery<T> cq) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public <T> TypedQuery<T> createQuery(String string, Class<T> type) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Query createNamedQuery(String string) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public <T> TypedQuery<T> createNamedQuery(String string, Class<T> type) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Query createNativeQuery(String string) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Query createNativeQuery(String string, Class type) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Query createNativeQuery(String string, String string1) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void joinTransaction() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public <T> T unwrap(Class<T> type) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Object getDelegate() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public void close() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public boolean isOpen() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public EntityTransaction getTransaction() {
            return dummyTransaction;
        }

        @Override
        public EntityManagerFactory getEntityManagerFactory() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public CriteriaBuilder getCriteriaBuilder() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Metamodel getMetamodel() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Query createQuery(CriteriaUpdate cu) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public Query createQuery(CriteriaDelete cd) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public StoredProcedureQuery createNamedStoredProcedureQuery(String string) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String string) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String string, Class... types) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public StoredProcedureQuery createStoredProcedureQuery(String string, String... strings) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public boolean isJoinedToTransaction() {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public <T> EntityGraph<T> createEntityGraph(Class<T> type) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public EntityGraph<?> createEntityGraph(String string) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public EntityGraph<?> getEntityGraph(String string) {
            throw new UnsupportedOperationException("Not supported.");
        }

        @Override
        public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> type) {
            throw new UnsupportedOperationException("Not supported.");
        }
    }

    class StdRandomMockEntityManager extends MockEntityManager {
        @Override
        public <T> T find(Class<T> type, Object o) {
            DataHolder dh = tlDataHolder.get();
            dh.random = new Random();
            dh.calendar = Calendar.getInstance();
            return findWithHistory(dh, o);
        }
    }
    
    private String className;
    public MockStockPriceEntityManagerFactory(String s) {
        className = s;
    }

    @Override
    public EntityManager createEntityManager() {
        switch(className) {
            case "MockEntityManager": return new MockEntityManager();
            case "StdRandomMockEntityManager": return new StdRandomMockEntityManager();
            default: throw new IllegalArgumentException("No class defined for " + className);
        }
    }

    @Override
    public EntityManager createEntityManager(Map map) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Metamodel getMetamodel() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean isOpen() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Map<String, Object> getProperties() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Cache getCache() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public PersistenceUnitUtil getPersistenceUnitUtil() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
