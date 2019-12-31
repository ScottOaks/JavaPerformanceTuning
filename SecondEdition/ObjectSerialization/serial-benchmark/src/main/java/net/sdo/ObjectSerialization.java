/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.sdo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.AverageTime)
public class ObjectSerialization {

    @State(Scope.Benchmark)
    public static class CompressUnbufferedSerialization {
        public StockPriceHistory sph;
        @Setup
        public void setup() {
	    EntityManager em = new MockStockPriceEntityManagerFactory("MockEntityManager").createEntityManager();
	    sph = new StockPriceHistoryCompressUnbuffered("AAAA", new Date("1/01/09"),
		new Date("12/31/19"), em);
	}
    }

    @State(Scope.Benchmark)
    public static class CompressLazySerialization {
        public StockPriceHistory sph;
        @Setup
        public void setup() {
	    EntityManager em = new MockStockPriceEntityManagerFactory("MockEntityManager").createEntityManager();
	    sph = new StockPriceHistoryCompressLazy("AAAA", new Date("1/01/09"),
		new Date("12/31/19"), em);
	}
    }

    @State(Scope.Benchmark)
    public static class Eager {
        public StockPriceHistory sph;
        @Setup
        public void setup() {
	    EntityManager em = new MockStockPriceEntityManagerFactory("MockEntityManager").createEntityManager();
	    sph = new StockPriceHistoryEager("AAAA", new Date("1/01/09"),
		new Date("12/31/19"), em);
	}
    }

    @State(Scope.Benchmark)
    public static class EagerTransient {
        public StockPriceHistory sph;
        @Setup
        public void setup() {
	    EntityManager em = new MockStockPriceEntityManagerFactory("MockEntityManager").createEntityManager();
	    sph = new StockPriceHistoryEagerTransient("AAAA", new Date("1/01/09"),
		new Date("12/31/19"), em);
	}
    }

    @State(Scope.Benchmark)
    public static class CompressSerialization {
        public StockPriceHistory sph;
        @Setup
        public void setup() {
	    EntityManager em = new MockStockPriceEntityManagerFactory("MockEntityManager").createEntityManager();
	    sph = new StockPriceHistoryCompress("AAAA", new Date("1/01/09"),
		new Date("12/31/19"), em);
	}
    }

    @State(Scope.Benchmark)
    public static class Externalizable {
        public StockPriceHistory sph;
        @Setup
        public void setup() {
	    EntityManager em = new MockStockPriceEntityManagerFactory("MockEntityManager").createEntityManager();
	    sph = new StockPriceHistoryExternalizable("AAAA", new Date("1/01/09"),
		new Date("12/31/19"), em);
	}
    }

    @Benchmark
    public void testCompressLazySerialization(CompressLazySerialization dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, false, bh);
    }

    @Benchmark
    public void testLazyCompressDeserialization(CompressLazySerialization dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, true, bh);
    }

    @Benchmark
    public void testCompressUnbufferedSerialization(CompressUnbufferedSerialization dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, false, bh);
    }

    @Benchmark
    public void testCompressUnbufferedDeserialization(CompressUnbufferedSerialization dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, true, bh);
    }

    @Benchmark
    public void testCompressSerialization(CompressSerialization dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, false, bh);
    }

    @Benchmark
    public void testCompressDeserialization(CompressSerialization dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, true, bh);
    }

    @Benchmark
    public void testExternalizableSerialization(Externalizable dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, false, bh);
    }

    @Benchmark
    public void testExternalizableDeserialization(Externalizable dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, true, bh);
    }

    @Benchmark
    public void testEagerSerialization(Eager dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, false, bh);
    }

    @Benchmark
    public void testEagerDeserialization(Eager dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, true, bh);
    }

    @Benchmark
    public void testEagerTransientSerialization(EagerTransient dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, false, bh);
    }

    @Benchmark
    public void testEagerTransientDeserialization(EagerTransient dh, Blackhole bh) throws IOException, ClassNotFoundException {
	test(dh.sph, true, bh);
    }

    public void test(StockPriceHistory sph, boolean deserialize, Blackhole bh) throws IOException, ClassNotFoundException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(sph);
        oos.close();
	byte[] b = baos.toByteArray();
	bh.consume(b);
        if (deserialize) {
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            ObjectInputStream ois = new ObjectInputStream(bais);
            bh.consume(ois.readObject());
            ois.close();
        }
    }

    public static class SimplePoint implements Serializable {
        public int x;
        public int y;
    }

    @State(Scope.Benchmark)
    public static class SimplePointTester {
        SimplePoint[] sp = new SimplePoint[100000];
        @Setup
        public void setup() {
	    for (int i = 0; i < 100000; i++) {
	        sp[i] = new SimplePoint();
		sp[i].x = i;
		sp[i].y = i + 2;
	    }
	}
    }

    @Benchmark
    public void testPointSimple(SimplePointTester spt, Blackhole bh) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        for (int i = 0; i < 100000; i++) {
            oos.writeObject(spt.sp[i]);
        }
	oos.close();
    }

    @Benchmark
    public void testPointSimpleDeserialize(SimplePointTester spt, Blackhole bh) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        for (int i = 0; i < 100000; i++) {
            oos.writeObject(spt.sp[i]);
        }
	oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        for (int i = 0; i < 100000; i++) {
            bh.consume(ois.readObject());
        }
        ois.close();
    }

    public static class Point implements Serializable {
        public transient int x;
        public transient int y;

        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.defaultWriteObject();
            oos.writeInt(x);
            oos.writeInt(y);
        }
        private void readObject(ObjectInputStream ois)
                                throws IOException, ClassNotFoundException {
            ois.defaultReadObject();
            x = ois.readInt();
            y = ois.readInt();
        }
    }

    @State(Scope.Benchmark)
    public static class PointTester {
        Point[] p = new Point[100000];
        @Setup
        public void setup() {
	    for (int i = 0; i < 100000; i++) {
	        p[i] = new Point();
		p[i].x = i;
		p[i].y = i + 2;
	    }
	}
    }

    @Benchmark
    public void testPoint(PointTester pt, Blackhole bh) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        for (int i = 0; i < 100000; i++) {
            oos.writeObject(pt.p[i]);
        }
	oos.close();
    }

    @Benchmark
    public void testPointDeserialize(PointTester pt, Blackhole bh) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        for (int i = 0; i < 100000; i++) {
            oos.writeObject(pt.p[i]);
        }
	oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        for (int i = 0; i < 100000; i++) {
            bh.consume(ois.readObject());
        }
        ois.close();
    }

    public static class TripHistory implements Serializable {
        public transient SimplePoint[] airportsVisited;
	// THIS CODE IS NOT FUNCTIONALLY CORRECT
	private void writeObject(ObjectOutputStream oos) throws IOException {
	    oos.defaultWriteObject();
	    oos.writeInt(airportsVisited.length);
	    for (int i = 0; i < airportsVisited.length; i++) {
	        oos.writeInt(airportsVisited[i].x);
	        oos.writeInt(airportsVisited[i].y);
	    }
	}

	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
	    ois.defaultReadObject();
	    int length = ois.readInt();
	    airportsVisited = new SimplePoint[length];
	    for (int i = 0; i < length; i++) {
	        airportsVisited[i] = new SimplePoint();
		airportsVisited[i].x = ois.readInt();
		airportsVisited[i].y = ois.readInt();
	    }
	}
    }

    @State(Scope.Benchmark)
    public static class PointArrayTester {
	public TripHistory th = new TripHistory();
        public SimplePoint[] sp = new SimplePoint[100000];
        @Setup
        public void setup() {
	    for (int i = 0; i < 100000; i++) {
	        sp[i] = new SimplePoint();
		sp[i].x = i;
		sp[i].y = i + 2;
	    }
	    th.airportsVisited = sp;
	}
    }

    @Benchmark
    public void testPointArray(PointArrayTester pt, Blackhole bh) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
	oos.writeObject(pt.th);
	oos.close();
    }

    @Benchmark
    public void testPointArrayDeserialize(PointArrayTester pt, Blackhole bh) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(pt.th);
	oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        bh.consume(ois.readObject());
        ois.close();
    }

    public static class TripHistoryDefault implements Serializable {
        public SimplePoint[] airportsVisited;
    }

    @State(Scope.Benchmark)
    public static class PointArrayDefaultTester {
	public TripHistoryDefault th = new TripHistoryDefault();
        public SimplePoint[] sp = new SimplePoint[100000];
        @Setup
        public void setup() {
	    for (int i = 0; i < 100000; i++) {
	        sp[i] = new SimplePoint();
		sp[i].x = i;
		sp[i].y = i + 2;
	    }
	    th.airportsVisited = sp;
	}
    }

    @Benchmark
    public void testPointArrayDefault(PointArrayDefaultTester pt, Blackhole bh) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
	oos.writeObject(pt.th);
	oos.close();
    }

    @Benchmark
    public void testPointArrayDefaultDeserialize(PointArrayDefaultTester pt, Blackhole bh) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(pt.th);
	oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        bh.consume(ois.readObject());
        ois.close();
    }
}
