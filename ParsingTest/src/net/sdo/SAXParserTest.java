/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public abstract class SAXParserTest extends AbstractParsingTest {

    protected abstract XMLReader getReader() throws ParserConfigurationException, SAXException;

    private static class SAXDoneException extends SAXParseException {

        public SAXDoneException(String s) {
            super(s, null);
        }
    }

    protected class CustomizedInnerHandler extends DefaultHandler {
        @Override
        public void startElement(String space, String name, String raw, Attributes atts) {
            if (name.length() == 0) {
                name = raw;
            }
            if (name.equalsIgnoreCase(ITEM_ID)) {
                isItemID = true;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXDoneException {
            if (isItemID) {
                String s = new String(ch, start, length);
                isItemID = false;
                if (addItemId(s)) {
                    throw new SAXDoneException("Done");
                }
            }
        }
    }

    @Override
    protected void engineRun(InputStream ins) throws IOException {
        try {
            InputSource source = new InputSource();
            XMLReader reader = getReader();
            CustomizedInnerHandler handler = new CustomizedInnerHandler();
            reader.setContentHandler(handler);
            source.setByteStream(ins);
            reader.parse(source);
        } catch (SAXDoneException sde) {
            int i = 0;
        } catch (Exception ex) {
            throw new IOException("Can't complete parsing", ex);
        }
    }
}
