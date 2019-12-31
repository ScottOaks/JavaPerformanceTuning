/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;
import javax.xml.transform.Source;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import org.xml.sax.SAXException;

@State(Scope.Benchmark)
public class StAXValidatingCachingParserTest extends AbstractParsingTest {
    private Schema schema;
    private XMLInputFactory staxFactory;
    private boolean exceptionFindAllIds = false;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
        super.setup("xml", false, p);
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        StreamSource ss = new StreamSource(schemaInputStream);
        try {
            schema = sf.newSchema(new Source[]{ss});
        } catch (SAXException ex) {
            throw new IOException("Can't read schema file", ex);
        }
        staxFactory = XMLInputFactory.newInstance();
        staxFactory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
    }

    boolean isID = false;
    @Override
    public void testEngine(Blackhole bh) throws IOException {
	exceptionFindAllIds = false;
	try {
            XMLStreamReader xsr = staxFactory.createXMLStreamReader(inputStream);
            final XMLStreamReader reader = new StreamReaderDelegate(xsr) {
                @Override
                public int next() throws XMLStreamException {
                    int state = super.next();
                    switch (state) {
                        case XMLStreamConstants.START_ELEMENT:
                            String s = super.getLocalName();
                            if (ID.equals(s)) {
                                isID = true;
                            }
                            break;
                        case XMLStreamConstants.CHARACTERS:
                            if (isID) {
                                String id = super.getText();
                                isID = false;
                                if (addId(id)) {
                                    exceptionFindAllIds = true;
                                    throw new XMLStreamException("Done");
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    return state;
                }
            };
            Validator validator = schema.newValidator();
            validator.validate(new StAXSource(reader));
        } catch (Exception ex) {
            if (exceptionFindAllIds) {
                return;
            }
            throw new IOException("STAX Parsing Exception ", ex);
        }
    }
}
