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

public class JSONParserTest extends AbstractParsingTest {
    private JsonParserFactory factory;

    @Override
    protected void engineInit(RunParams rp) throws IOException {
        factory = Json.createParserFactory(null);
    }

    @Override
    protected void engineRun(InputStream in) throws IOException {
        JsonParser parser = factory.createParser(in);
        int itemCount = 0;
        while (parser.hasNext()) {
            Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    String s = parser.getString();
                    if (ITEM_ID.equals(s)) {
                        isItemID = true;
                    }
                    break;
                case VALUE_STRING:
                    if (isItemID) {
                        if (addItemId(parser.getString())) {
                            return;
                        }
                        isItemID = false;
                    }
                    continue;
                default:
                    continue;
            }
        }
    }
}
