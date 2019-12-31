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

import org.apache.commons.lang3.RandomStringUtils;

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
public class StringConcatBenchmark {
    @Param({"1"})
    private int nStrings;

    private String[] strings;
    private String prefix;

    @Setup
    public void setup() {
        strings = new String[nStrings];
	for (int i = 0; i < nStrings; i++) {
	    strings[i] = RandomStringUtils.randomAscii(24);
	}
	prefix = RandomStringUtils.randomAscii(24);
    }

    @Benchmark
    public void testJDK11StyleDouble(Blackhole bh) {
        String s = "";
	double d = 1.0;
	for (int i = 0; i < nStrings; i++) {
	    s = s + strings[i] + d;
	}
	bh.consume(s);
    }

    @Benchmark
    public void testJDK8StyleDouble(Blackhole bh) {
        String s = "";
	double d = 1.0;
	for (int i = 0; i < nStrings; i++) {
	    s = new StringBuilder().append(s).append(strings[i]).append(d).toString();
	}
	bh.consume(s);
    }

    @Benchmark
    public void testJDK11Style(Blackhole bh) {
        String s = "";
	for (int i = 0; i < nStrings; i++) {
	    s = s + strings[i];
	}
	bh.consume(s);
    }

    @Benchmark
    public void testJDK8Style(Blackhole bh) {
        String s = "";
	for (int i = 0; i < nStrings; i++) {
	    s = new StringBuilder().append(s).append(strings[i]).toString();
	}
	bh.consume(s);
    }

    @Benchmark
    public void testStringBuilder(Blackhole bh) {
        StringBuilder sb = new StringBuilder();
	for (int i = 0; i < nStrings; i++) {
	    sb.append(strings[i]);
	}
	bh.consume(sb.toString());
    }

    @Benchmark
    public void testSingleStringBuilder(Blackhole bh) {
        String s = new StringBuilder(prefix).append(strings[0]).toString();
	bh.consume(s);
    }

    @Benchmark
    public void testSingleJDK11Style(Blackhole bh) {
	String s = prefix + strings[0];
	bh.consume(s);
    }

    @Benchmark
    public void testSingleJDK8Style(Blackhole bh) {
	String s = new StringBuilder().append(prefix).append(strings[0]).toString();
	bh.consume(s);
    }
}
