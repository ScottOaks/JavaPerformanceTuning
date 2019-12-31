/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@State(Scope.Benchmark)
public class DOMBuilderTest extends AbstractParsingTest {
    protected DocumentBuilderFactory dbf;
    protected DocumentBuilder db;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
	try {
	    super.setup("xml", true, p);
            dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            db = dbf.newDocumentBuilder();
            db.setErrorHandler(new SAXErrorHandler());
	} catch (ParserConfigurationException pce) {
	    throw new IOException("Can't setup DOMBuilder", pce);
	}
    }

    @Override
    public void testEngine(Blackhole bh) throws IOException {
        try {
            db.reset();
            Document doc = db.parse(inputStream);
	    bh.consume(doc);
        } catch (SAXException ex) {
            throw new IOException("Can't Parse" , ex);
        }
    }
}
