/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.io.InputStream;

public class JsonJacksonParserTest extends AbstractParsingTest {
    private JsonFactory jsonFactory;

    @Override
    protected void engineInit(RunParams rp) {
        jsonFactory = new JsonFactory();
    }

    @Override
    protected void engineRun(InputStream is) throws IOException {
        JsonParser parser = jsonFactory.createParser(is);
        int itemCount = 0;
        for (JsonToken token = parser.nextToken(); token != null; token = parser.nextToken()) {
            switch (token) {
                case FIELD_NAME:
                    String s = parser.getCurrentName();
                    if (ITEM_ID.equals(s)) {
                        isItemID = true;
                    }
                    break;
                case VALUE_STRING:
                    if (isItemID) {
                        String s2 = parser.getText();
                        if (addItemId(s2)) {
                            return;
                        }
                        isItemID = false;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
