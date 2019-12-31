/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class StAXParserTest extends AbstractParsingTest {
    protected XMLInputFactory staxFactory;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
        super.setup("xml", false, p);
        staxFactory = XMLInputFactory.newInstance();
        staxFactory.setProperty (XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
    }

    @Override
    protected void testEngine(Blackhole bh) throws IOException {
        boolean isID = false;
        try {
            XMLStreamReader reader = staxFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                reader.next();
                int state = reader.getEventType();
                switch (state) {
                    case XMLStreamConstants.START_ELEMENT:
                        String s = reader.getLocalName();
                        if (ID.equals(s)) {
                            isID = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (isID) {
                            String id = reader.getText();
                            isID = false;
                            if (addId(id)) {
                                return;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (XMLStreamException ex) {
            throw new IOException("STAX Parsing Exception ", ex);
        }
    }
}
