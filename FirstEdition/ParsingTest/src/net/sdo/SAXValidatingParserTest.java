/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import com.sun.org.apache.xerces.internal.jaxp.JAXPConstants;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXValidatingParserTest extends SAXParserTest {
    private SAXParser parser;

    @Override
    protected void engineInit(RunParams rp) throws IOException {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setValidating(true);
            spf.setNamespaceAware(true);
            parser = spf.newSAXParser();
        } catch (Exception ex) {
            throw new IOException("Can't initialize parser", ex);
        }
    }

    @Override
    protected XMLReader getReader() throws ParserConfigurationException, SAXException {
        parser.reset();
        parser.setProperty(JAXPConstants.JAXP_SCHEMA_LANGUAGE, XMLConstants.W3C_XML_SCHEMA_NS_URI);
        XMLReader xr = parser.getXMLReader();
        xr.setErrorHandler(new SAXErrorHandler());
        return xr;
    }
}
