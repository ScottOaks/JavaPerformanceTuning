/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import net.sourceforge.sizeof.SizeOf;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSParserFilter;
import org.w3c.dom.traversal.NodeFilter;

public class FilteringDOMBuilderTest extends AbstractParsingTest {
    private DOMImplementationLS domLS;

    private class InputFilter implements LSParserFilter {
        private boolean done = false;
        private boolean itemCountReached;

        @Override
        public short acceptNode(Node node) {
            if (itemCountReached) {
                String s = node.getNodeName();
                if ("ItemArray".equals(s)) {
                    return NodeFilter.FILTER_ACCEPT;
                }
                if (done) {
                    return NodeFilter.FILTER_SKIP;
                }
                // This is the </Item> element
                // the last thing we need
                if ("Item".equals(s)) {
                    done = true;
                }
            }
            return NodeFilter.FILTER_ACCEPT;
        }

        @Override
        public int getWhatToShow() {
            return NodeFilter.SHOW_ALL;
        }

        @Override
        public short startElement(Element element) {
            if (itemCountReached) {
                return NodeFilter.FILTER_ACCEPT;
            }
            String s = element.getTagName();
            if (ITEM_ID.equals(element.getTagName())) {
                if  (addItemId(element.getNodeValue())) {
                    itemCountReached = true;
                }
            }
            return NodeFilter.FILTER_ACCEPT;
        }
    }

    @Override
    protected void engineInit(RunParams rp) throws IOException {
        try {
            System.setProperty(DOMImplementationRegistry.PROPERTY,
                "com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl");
            DOMImplementationRegistry registry =
                DOMImplementationRegistry.newInstance();
            DOMImplementation domImpl = registry.getDOMImplementation("LS 3.0");
            domLS =
                (DOMImplementationLS) domImpl;
        //} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException ex) {
        } catch (Exception ex) {
            throw new IOException("Can't create DOM LS implementation", ex);
        }
    }

    @Override
    protected void engineRun(InputStream is) throws IOException {
        LSParser lsp = domLS.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS,
            "http://www.w3.org/2001/XMLSchema");
        lsp.setFilter(new InputFilter());
        LSInput lsi = domLS.createLSInput();
        lsi.setByteStream(is);
        Document doc = lsp.parse(lsi);
        if (System.getProperty("debugParse") != null) {
            try {
                System.out.println("Waiting for input");
                System.in.read();
                DOMSource dsource = new DOMSource(doc);
                StreamResult result = new StreamResult(System.err);
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(dsource, result);
            } catch (TransformerException ex) {
                ex.printStackTrace();
            }
        }
    }
}
