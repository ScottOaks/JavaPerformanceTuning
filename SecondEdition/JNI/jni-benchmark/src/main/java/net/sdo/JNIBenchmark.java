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

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class JNIBenchmark {
    static {
        System.load(System.getProperty("LIBPATH") + "/libRandomTestCLibrary.so");
    }

    @Param({"100"})
    int nValues;

    @Param({"100"})
    int nTrials;

    @Benchmark
    public void testJavaJavaJava(Blackhole bh) {
	long l = 0;
        for (int i = 0; i < nTrials; i++) {
	    long a = calcJavaJava(nValues);
	    l += a / nTrials;
	}
	bh.consume(l);
    }

    private long calcJavaJava(int nValues) {
        long l = 0;
	for (int i = 0; i < nValues; i++) {
	    l += calcJava(i);
	}
	long a = l / nValues;
	return a;
    }

    private long calcJava(int i) {
        return i * 3 + 15;
    }

    @Benchmark
    public void testJavaJavaC(Blackhole bh) {
	long l = 0;
        for (int i = 0; i < nTrials; i++) {
	    long a = calcJavaC(nValues);
	    l += 50 - a;
	}
	bh.consume(l);
    }

    private long calcJavaC(int nValues) {
        long l = 0;
	for (int i = 0; i < nValues; i++) {
	    long n = calcC(i);
	    l += n;
	}
	long a = l / nValues;
	return a;
    }

    private native long calcC(int i);

    @Benchmark
    public void testJavaCC(Blackhole bh) {
	long l = 0;
        for (int i = 0; i < nTrials; i++) {
	    long a = calcCC(nValues);
	    l += 50 - a;
	}
	bh.consume(l);
    }

    private native long calcCC(int nValues);

    @Benchmark
    public void testCCC(Blackhole bh) {
	bh.consume(calcCCC(nTrials, nValues));
    }

    private native long calcCCC(int nTrials, int nValues);
}
