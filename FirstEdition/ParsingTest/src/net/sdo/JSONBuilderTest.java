/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonReader;

public class JSONBuilderTest extends AbstractParsingTest {
    @Override
    protected void engineRun(InputStream in) throws IOException {
        JsonReader jr = Json.createReader(in);
        jr.read();
    }
}
