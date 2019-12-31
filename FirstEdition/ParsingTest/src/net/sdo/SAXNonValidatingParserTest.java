/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXNonValidatingParserTest extends SAXParserTest {
    private SAXParser parser;

    @Override
    protected void engineInit(RunParams rp) throws IOException {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setValidating(false);
            spf.setNamespaceAware(false);
            parser = spf.newSAXParser();
        } catch (Exception ex) {
            throw new IOException("Can't initialize parser" , ex);
        }
    }

    @Override
    protected XMLReader getReader() throws ParserConfigurationException, SAXException {
        parser.reset();
        return parser.getXMLReader();
    }
}
