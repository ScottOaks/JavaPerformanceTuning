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

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

@State(Scope.Benchmark)
public class SAXValidatingParserTest extends AbstractSAXParserTest {
    private SAXParser parser;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
        super.setup(p);
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
