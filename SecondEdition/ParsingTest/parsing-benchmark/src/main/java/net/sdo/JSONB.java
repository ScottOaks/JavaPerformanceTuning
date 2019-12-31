/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import net.sdo.jsonb.FindItemsByKeywordsResponse;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class JSONB extends AbstractParsingTest {
    private InputStream in;
    private Jsonb jsonb;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
        super.setup("json", true, p);
        jsonb = JsonbBuilder.create();
    }

    @Override
    protected void testEngine(Blackhole bh) throws IOException {
        FindItemsByKeywordsResponse f =
		jsonb.fromJson(inputStream, FindItemsByKeywordsResponse.class);
        bh.consume(f);
    }
}
