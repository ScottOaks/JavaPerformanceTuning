/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.sourceforge.sizeof.SizeOf;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DOMBuilderTest extends AbstractParsingTest {
    protected DocumentBuilderFactory dbf;
    protected DocumentBuilder db;

    @Override
    protected void engineInit(RunParams rp) throws IOException {
        dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new IOException("Can't get document builder", ex);
        }
        db.setErrorHandler(new SAXErrorHandler());
    }

    @Override
    protected void engineRun(InputStream in) throws IOException {
        try {
            db.reset();
            Document doc = db.parse(in);
            if (System.getProperty("parseDebug") != null) {
                System.out.println("Waiting for input");
                System.in.read();
            }
        } catch (SAXException ex) {
            throw new IOException("Can't Parse" , ex);
        }
    }
}
