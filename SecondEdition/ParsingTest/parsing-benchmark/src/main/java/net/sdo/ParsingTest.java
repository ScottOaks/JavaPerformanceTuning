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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

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
public class ParsingTest {

    @State(Scope.Benchmark)
    public static class Parameters {
        @Param({"10"})
        public int targetIdCount;
    }

    @Benchmark
    public void domBuilder(Parameters p, DOMBuilderTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void domSerializing(Parameters p, DOMSerializingTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void filteringDom(Parameters p, FilteringDOMBuilderTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void jaxbParsing(Parameters p, JAXBParsingTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void jaxbSerializing(Parameters p, JAXBSerializingTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void jsonb(Parameters p, JSONB test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void jsonbJackson(Parameters p, JSONBJackson test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void jsonBuilder(Parameters p, JSONBuilderTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void jsonParsing(Parameters p, JSONParserTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void jacksonParsing(Parameters p, JsonJacksonParserTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void saxCaching(Parameters p, SAXValidatingCachingParserTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void saxValidating(Parameters p, SAXValidatingParserTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void saxParsing(Parameters p, SAXParserTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void saxParsingNoReuse(Parameters p, SAXNoReuseParserTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }


    @Benchmark
    public void staxParsing(Parameters p, StAXParserTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }

    @Benchmark
    public void staxValidating(Parameters p, StAXValidatingCachingParserTest test, Blackhole bh) throws IOException {
        test.test(bh);
    }
}
