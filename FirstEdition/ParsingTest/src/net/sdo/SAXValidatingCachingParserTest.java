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
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class SAXValidatingCachingParserTest extends SAXParserTest {
    private SAXParser parser;

    @Override
    protected void engineInit(RunParams rp) throws IOException {
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            StreamSource ss = new StreamSource(rp.getSchemaFileName());
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
