/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.openjdk.jmh.infra.Blackhole;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public abstract class AbstractSAXParserTest extends AbstractParsingTest {

    protected abstract XMLReader getReader() throws ParserConfigurationException, SAXException;

    private static class SAXDoneException extends SAXParseException {
        public SAXDoneException(String s) {
            super(s, null);
        }
    }

    protected class CustomizedInnerHandler extends DefaultHandler {
        private boolean isID = false;
        @Override
        public void startElement(String space, String name, String raw, Attributes atts) {
            if (name.length() == 0) {
                name = raw;
            }
            if (name.equalsIgnoreCase(ID)) {
                isID = true;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXDoneException {
            if (isID) {
                String s = new String(ch, start, length);
                isID = false;
                if (addId(s)) {
                    throw new SAXDoneException("Done");
                }
            }
        }
    }

    public void setup(ParsingTest.Parameters p) throws IOException {
        super.setup("xml", false, p);
    }

    @Override
    protected void testEngine(Blackhole bh) throws IOException {
        try {
            InputSource source = new InputSource();
            XMLReader reader = getReader();
            CustomizedInnerHandler handler = new CustomizedInnerHandler();
            reader.setContentHandler(handler);
            source.setByteStream(inputStream);
            reader.parse(source);
        } catch (SAXDoneException sde) {
            //
        } catch (Exception ex) {
            throw new IOException("Can't complete parsing", ex);
        }
    }
}
