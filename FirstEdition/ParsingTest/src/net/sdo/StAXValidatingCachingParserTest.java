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
import org.xml.sax.SAXException;

public class StAXValidatingCachingParserTest extends AbstractParsingTest {
    private Schema schema;
    private boolean exceptionFindAllItems = false;
    private XMLInputFactory staxFactory;

    @Override
    protected void engineInit(RunParams rp) throws IOException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        StreamSource ss = new StreamSource(rp.getSchemaFileName());
        try {
            schema = sf.newSchema(new Source[]{ss});
        } catch (SAXException ex) {
            throw new IOException("Can't read schema file", ex);
        }
        staxFactory = XMLInputFactory.newInstance();
        staxFactory.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
    }

    @Override
    protected void engineRun(InputStream ins) throws IOException {
        exceptionFindAllItems = false;
        try {
            XMLStreamReader xsr = staxFactory.createXMLStreamReader(ins);
            final XMLStreamReader reader = new StreamReaderDelegate(xsr) {
                @Override
                public int next() throws XMLStreamException {
                    int state = super.next();
                    switch (state) {
                        case XMLStreamConstants.START_ELEMENT:
                            String s = super.getLocalName();
                            if (ITEM_ID.equals(s)) {
                                isItemID = true;
                            }
                            break;
                        case XMLStreamConstants.CHARACTERS:
                            if (isItemID) {
                                String id = super.getText();
                                isItemID = false;
                                if (addItemId(id)) {
                                    exceptionFindAllItems = true;
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
            if (exceptionFindAllItems) {
                return;
            }
            throw new IOException("STAX Parsing Exception ", ex);
        }
    }
}
