/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DOMSerializingTest extends AbstractParsingTest {
    protected DocumentBuilderFactory dbf;
    protected DocumentBuilder db;
    protected Document doc;
    private ByteArrayOutputStream output;
    private Transformer transformer;

    @Override
    protected void engineInit(RunParams rp) throws IOException {
        dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        try {
            db = dbf.newDocumentBuilder();
            db.setErrorHandler(new SAXErrorHandler());
            doc = db.parse(new ByteArrayInputStream(rp.getInput()));
            transformer = TransformerFactory.newInstance().newTransformer();
        //} catch (TransformerConfigurationException | ParserConfigurationException | SAXException ex) {
        } catch (Exception ex) {
            throw new IOException("Can't get document builder", ex);
        }
        output = new ByteArrayOutputStream();
    }

    @Override
    protected void engineRun(InputStream in) throws IOException {
        try {
            output.reset();
            DOMSource dsource = new DOMSource(doc);
            StreamResult result = new StreamResult(output);
            transformer.transform(dsource, result);
        } catch (TransformerException ex) {
            throw new IOException("Can't Serialize", ex);
        }
    }
}
