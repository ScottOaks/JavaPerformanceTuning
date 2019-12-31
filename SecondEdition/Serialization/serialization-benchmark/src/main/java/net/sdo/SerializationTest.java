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
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class SerializationTest {
    @Param({"1"})
    private int nLoops;

    private StockPriceHistory sphStandard;
    private StockPriceHistory sphCompressUnbuffered;
    private StockPriceHistory sphCompress;
    private StockPriceHistory sphCompressLazy;
    private StockPriceHistory sphEager;
    private StockPriceHistory sphEagerTransient;
    private StockPriceHistory sphCompressExternalizable;
    private StockPriceHistory sphExternalizable;

    @Setup
    public void setup() {
        EntityManager em = new MockStockPriceEntityManagerFactory("MockEntityManager").createEntityManager();
        sphStandard = new StockPriceHistoryImpl("AAAA", new Date("1/01/19"), new Date("12/31/19"), em);
        sphCompressUnbuffered = new StockPriceHistoryCompressUnbuffered("AAAA", new Date("1/01/19"), new Date("12/31/19"), em);
        sphCompress = new StockPriceHistoryCompress("AAAA", new Date("1/01/19"), new Date("12/31/19"), em);
        sphCompressLazy = new StockPriceHistoryCompressLazy("AAAA", new Date("1/01/19"), new Date("12/31/19"), em);
        sphEager = new StockPriceHistoryEager("AAAA", new Date("1/01/19"), new Date("12/31/19"), em);
        sphEagerTransient = new StockPriceHistoryEagerTransient("AAAA", new Date("1/01/19"), new Date("12/31/19"), em);
        sphExternalizable = new StockPriceHistoryExternalizable("AAAA", new Date("1/01/19"), new Date("12/31/19"), em);
    }

    @Benchmark
    public void testImpl(Blackhole bh) throws IOException, ClassNotFoundException {
        test(sphStandard, bh);
    }

    @Benchmark
    public void testCompressUnbuffered(Blackhole bh) throws IOException, ClassNotFoundException {
        test(sphCompressUnbuffered, bh);
    }

    @Benchmark
    public void testCompress(Blackhole bh) throws IOException, ClassNotFoundException {
        test(sphCompress, bh);
    }

    @Benchmark
    public void testCompressLazy(Blackhole bh) throws IOException, ClassNotFoundException {
        test(sphCompressLazy, bh);
    }

    @Benchmark
    public void testExternalizable(Blackhole bh) throws IOException, ClassNotFoundException {
        test(sphExternalizable, bh);
    }

    @Benchmark
    public void testEager(Blackhole bh) throws IOException, ClassNotFoundException {
        test(sphEager, bh);
    }

    @Benchmark
    public void testEagerTransient(Blackhole bh) throws IOException, ClassNotFoundException {
        test(sphEagerTransient, bh);
    }

    public void test(StockPriceHistory sph, Blackhole bh) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(baos);
	oos.writeObject(sph);
	oos.close();
	byte[] b = baos.toByteArray();
	ByteArrayInputStream bais = new ByteArrayInputStream(b);
	ObjectInputStream ois = new ObjectInputStream(bais);
	bh.consume(ois.readObject());
	ois.close();
    }
}
