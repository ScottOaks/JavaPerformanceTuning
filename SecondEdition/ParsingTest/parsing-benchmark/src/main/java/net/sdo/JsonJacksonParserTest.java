/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class JsonJacksonParserTest extends AbstractParsingTest {
    private JsonFactory jsonFactory;
    private JsonParser parser;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
        super.setup("json", false, p);
        jsonFactory = new JsonFactory();
    }

    @Override
    protected void testEngine(Blackhole bh) throws IOException {
        parser = jsonFactory.createParser(inputStream);
        int idCount = 0;
        boolean isID = false;
        for (JsonToken token = parser.nextToken(); token != null; token = parser.nextToken()) {
            switch (token) {
                case FIELD_NAME:
                    String s = parser.getCurrentName();
                    if (ID.equals(s)) {
                        isID = true;
                    }
                    break;
                case VALUE_STRING:
                    if (isID) {
                        String s2 = parser.getText();
                        if (addId(s2)) {
                            return;
                        }
                        isID = false;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
