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
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
public class FilterTest {

    ArrayList<String> al;

    @Setup
    public void setup() {
        al = new ArrayList<>(500000);
        for (int first = (int) 'A'; first <= (int) 'Z'; first++) {
            for (int second = 'A'; second <= 'Z'; second++) {
                for (int third = 'A'; third <= 'Z'; third++) {
                    for (int fourth = 'A'; fourth <= 'Z'; fourth++) {
                        char[] c = new char[4];
                        c[0] = (char) first;
                        c[1] = (char) second;
                        c[2] = (char) third;
                        c[3] = (char) fourth;
                        al.add(new String(c));
                    }
                }
            }
        }
    }

    @Benchmark
    public void calcMulti(Blackhole bh) {
        Stream<String> stream = al.stream();
        Optional<String> t = stream.filter(symbol -> symbol.charAt(0) != 'A').
            filter(symbol -> symbol.charAt(1) != 'A').
            filter(symbol -> symbol.charAt(2) != 'A').
            filter(symbol -> symbol.charAt(3) != 'A').findFirst();
        String answer = t.get();
	bh.consume(answer);
    }

    private int count;
    @Benchmark
    public void countFilter(Blackhole bh) {
        count = 0;
        Stream<String> stream = al.stream();
        stream.filter(
            symbol -> symbol.charAt(0) != 'A' &&
            symbol.charAt(1) != 'A' &&
            symbol.charAt(2) != 'A' &&
            symbol.charAt(3) != 'A').
              forEach(symbol -> { count++; });
	bh.consume(count);
    }

    @Benchmark
    public void countIterator(Blackhole bh) {
	int count = 0;
        for (String symbol : al) {
          if (symbol.charAt(0) != 'A' &&
              symbol.charAt(1) != 'A' &&
              symbol.charAt(2) != 'A' &&
              symbol.charAt(3) != 'A')
              count++;
          }
	bh.consume(count);
    }

    private ArrayList<String> calcArray(List<String> src, Predicate<String> p) {
        ArrayList<String> dst = new ArrayList<>();
        for (String s : src) {
            if (p.test(s))
                dst.add(s);
        }
        return dst;
    }

    @Benchmark
    public void calcEager(Blackhole bh) {
        ArrayList<String> al1 = calcArray(al, (s) -> s.charAt(0) != 'A');
        ArrayList<String> al2 = calcArray(al1, (s) -> s.charAt(1) != 'A');
        ArrayList<String> al3 = calcArray(al2, (s) -> s.charAt(2) != 'A');
        ArrayList<String> al4 = calcArray(al3, (s) -> s.charAt(3) != 'A');
        String answer = al4.get(0);
	bh.consume(answer);
    }
} 
