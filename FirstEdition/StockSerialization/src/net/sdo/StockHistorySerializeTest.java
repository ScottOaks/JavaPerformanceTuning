/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import javax.persistence.EntityManager;
import net.sdo.stock.StockPriceHistory;
import net.sdo.stockimpl.MockStockPriceEntityManagerFactory;
import net.sdo.stockimpl.StockPriceHistoryCompress;
import net.sdo.stockimpl.StockPriceHistoryCompressLazy;
import net.sdo.stockimpl.StockPriceHistoryCompressUnbuffered;
import net.sdo.stockimpl.StockPriceHistoryEager;
import net.sdo.stockimpl.StockPriceHistoryEagerTransient;
import net.sdo.stockimpl.StockPriceHistoryExternalizable;
import net.sdo.stockimpl.StockPriceHistoryImpl;

public class StockHistorySerializeTest {
    private static int nLoops = 10000;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        EntityManager em = new MockStockPriceEntityManagerFactory("MockEntityManager").createEntityManager();
        StockPriceHistory sph = new StockPriceHistoryImpl("AAAA", new Date("1/01/13"), new Date("12/31/13"), em);

        System.out.println("\nStart regular SPH serialize");
        test(sph, false);
        System.gc();
        System.out.println("\nStart regular SPH serialize/deserialize");
        test(sph, true);
        System.gc();

        sph = new StockPriceHistoryCompressUnbuffered("AAAA", new Date("1/01/13"), new Date("12/31/13"), em);
        System.out.println("\nStart unbuffered compress SPH serialize");
        test(sph, false);
        System.gc();
        System.out.println("\nStart unbuffered compress SPH serialize/deserialize");
        test(sph, true);
        System.gc();

        sph = new StockPriceHistoryCompress("AAAA", new Date("1/01/13"), new Date("12/31/13"), em);
        System.out.println("\nStart compress SPH serialize");
        test(sph, false);
        System.gc();
        System.out.println("\nStart compress SPH serialize/deserialize");
        test(sph, true);
        System.gc();

        sph = new StockPriceHistoryCompressLazy("AAAA", new Date("1/01/13"), new Date("12/31/13"), em);
        System.out.println("\nStart lazy compress SPH serialize");
        test(sph, false);
        System.gc();
        System.out.println("\nStart lazy compress SPH serialize/deserialize");
        test(sph, true);
        System.gc();

        sph = new StockPriceHistoryExternalizable("AAAA", new Date("1/01/13"), new Date("12/31/13"), em);
        System.out.println("\nStart externalizable SPH serialize");
        test(sph, false);
        System.gc();
        System.out.println("\nStart externalizable SPH serialize/deserialize");
        test(sph, true);
        System.gc();

        sph = new StockPriceHistoryEager("AAAA", new Date("1/01/13"), new Date("12/31/13"), em);
        System.out.println("\nStart regular eager SPH serialize");
        test(sph, false);
        System.gc();
        System.out.println("\nStart regular eager SPH serialize/deserialize");
        test(sph, true);
        System.gc();

        sph = new StockPriceHistoryEagerTransient("AAAA", new Date("1/01/13"), new Date("12/31/13"), em);
        System.out.println("\nStart regular eager transient SPH serialize");
        test(sph, false);
        System.gc();
        System.out.println("\nStart regular SPH eager transient serialize/deserialize");
        test(sph, true);
    }

    public static void test(StockPriceHistory sph, boolean deserialize) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(sph);
        oos.close();
        byte[] b = baos.toByteArray();
        System.out.println("Serialized size is " + b.length);
        for (int i = 0; i < nLoops; i++) {
            baos.reset();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(sph);
            oos.close();
            if (deserialize) {
                ByteArrayInputStream bais = new ByteArrayInputStream(b);
                ObjectInputStream ois = new ObjectInputStream(bais);
                ois.readObject();
                ois.close();
            }
        }

        long then = System.currentTimeMillis();
        for (int i = 0; i < nLoops; i++) {
            baos.reset();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(sph);
            oos.close();
            if (deserialize) {
                ByteArrayInputStream bais = new ByteArrayInputStream(b);
                ObjectInputStream ois = new ObjectInputStream(bais);
                ois.readObject();
                ois.close();
            }
        }
        long now = System.currentTimeMillis();
        System.out.println("Time: " + (now - then));
    }
}
