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

import java.util.ArrayList;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class ExceptionBenchmark {
    @Param({"1"})
    private int nLoops;

    @Param({"100"})
    private int exceptionFactor;

    private static interface ExceptionTest {
        public void test(int num, int pctError, Blackhole bh);
    }

    private static class CheckedException extends Exception {
        public CheckedException(String s) {
	    super(s);
	}
    }

    private static class CheckedExceptionTester {
        public void test(int num, int pctError, Blackhole bh) {
            ArrayList<String> al = new ArrayList<>();
	    for (int i = 0; i < nLoops; i++) {
		try {
	            if ((i % pctError) == 0) {
		        throw new CheckedException("Failed");
	            }
	            Object o = new Object();
		    al.add(o.toString());
	        } catch (CheckedException ce) {
		    // continue
		}
	    }
	    bh.consume(al);
	}
    }

    @Benchmark
    public void testCheckedException(Blackhole bh) {
        test(nLoops, exceptionFactor, bh);
    }

    private static class UncheckedExceptionTester {
        public void test(int num, int pctError, Blackhole bh) {
            ArrayList<String> al = new ArrayList<>();
	    for (int i = 0; i < nLoops; i++) {
		try {
	            if ((i % pctError) == 0) {
		        throw new CheckedException("Failed");
	            }
	            Object o = new Object();
		    al.add(o.toString());
	        } catch (CheckedException ce) {
		    // continue
		}
	    }
	    bh.consume(al);
	}
    }

    @Benchmark
    public void testCheckedException(Blackhole bh) {
        test(nLoops, exceptionFactor, bh);
    }

    @Benchmark
    public void testCodeException(Blackhole bh) {
        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < nLoops; i++) {
            try {
                if ((i % exceptionFactor) == 0) {
                    throw new NullPointerException("Force Exception");
                }
                Object o = new Object();
                al.add(o.toString());
            } catch (NullPointerException npe) {
                // Continue to get next string
            }
        }
	bh.consume(al);
    }

    @Benchmark
    public void testDefensiveProgramming(Blackhole bh) {
        ArrayList<String> al = new ArrayList<>();
        for (int i = 0; i < nLoops; i++) {
	    Object o = null;
            if ((i % exceptionFactor) != 0) {
		o = new Object();
            }
	    if (o != null)
		al.add(o.toString());
        }
	bh.consume(al);
    }
}
