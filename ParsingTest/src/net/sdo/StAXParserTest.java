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

public class StAXParserTest extends AbstractParsingTest {
    protected XMLInputFactory staxFactory;

    @Override
    protected void engineInit(RunParams rp) throws IOException {
        staxFactory = XMLInputFactory.newInstance();
        staxFactory.setProperty (XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
    }

    @Override
    protected void engineRun(InputStream ins) throws IOException {
        try {
            XMLStreamReader reader = staxFactory.createXMLStreamReader(ins);
            while (reader.hasNext()) {
                reader.next();
                int state = reader.getEventType();
                switch (state) {
                    case XMLStreamConstants.START_ELEMENT:
                        String s = reader.getLocalName();
                        if (ITEM_ID.equals(s)) {
                            isItemID = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (isItemID) {
                            String id = reader.getText();
                            isItemID = false;
                            if (addItemId(id)) {
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
