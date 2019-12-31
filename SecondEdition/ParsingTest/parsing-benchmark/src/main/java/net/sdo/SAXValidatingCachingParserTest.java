/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

@State(Scope.Benchmark)
public class SAXValidatingCachingParserTest extends AbstractSAXParserTest {
    private SAXParser parser;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
        super.setup(p);
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            StreamSource ss = new StreamSource(schemaInputStream);
            Schema schema = sf.newSchema(new Source[]{ss});
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setValidating(false);
            spf.setNamespaceAware(true);
            spf.setSchema(schema);
            parser = spf.newSAXParser();
        } catch (Exception ex) {
            throw new IOException("Can't initialize parser", ex);
        }
    }

    @Override
    protected XMLReader getReader() throws ParserConfigurationException, SAXException {
        parser.reset();
        XMLReader xr = parser.getXMLReader();
        return xr;
    }
}
