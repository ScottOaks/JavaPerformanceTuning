/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@State(Scope.Benchmark)
public class DOMSerializingTest extends AbstractParsingTest {
    protected DocumentBuilderFactory dbf;
    protected DocumentBuilder db;
    protected Document doc;
    private ByteArrayOutputStream output;
    private Transformer transformer;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
        try {
            super.setup("xml", true, p);
            dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            db = dbf.newDocumentBuilder();
            db.setErrorHandler(new SAXErrorHandler());
            doc = db.parse(inputStream);
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (Exception ex) {
            throw new IOException("Can't get document builder", ex);
        }
        output = new ByteArrayOutputStream();
    }

    @Override
    protected void testEngine(Blackhole bh) throws IOException {
        try {
	    output.reset();
            DOMSource dsource = new DOMSource(doc);
            StreamResult result = new StreamResult(output);
            transformer.transform(dsource, result);
	    bh.consume(result);
        } catch (TransformerException ex) {
            throw new IOException("Can't Serialize", ex);
        }
    }
}
