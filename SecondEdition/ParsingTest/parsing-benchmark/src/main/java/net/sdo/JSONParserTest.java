/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class JSONParserTest extends AbstractParsingTest {
    private JsonParserFactory factory;
    private JsonParser parser;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
        super.setup("json", false, p);
        factory = Json.createParserFactory(null);
    }

    @Override
    protected void testEngine(Blackhole bh) throws IOException {
        boolean isID = false;

        parser = factory.createParser(inputStream);
        int idCount = 0;
        while (parser.hasNext()) {
            Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    String s = parser.getString();
                    if (ID.equals(s)) {
                        isID = true;
                    }
                    break;
                case VALUE_STRING:
                    if (isID) {
                        if (addId(parser.getString())) {
                            return;
                        }
                        isID = false;
                    }
                    continue;
                default:
                    continue;
            }
        }
    }
}
