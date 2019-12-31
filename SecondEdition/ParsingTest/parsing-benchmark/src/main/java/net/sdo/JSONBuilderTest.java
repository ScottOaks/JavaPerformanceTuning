/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonReader;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class JSONBuilderTest extends AbstractParsingTest {
    JsonReader jr;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
        super.setup("json", true, p);
    }

    @Override
    protected void testEngine(Blackhole bh) throws IOException {
        jr = Json.createReader(inputStream);
        bh.consume(jr.read());
    }
}
