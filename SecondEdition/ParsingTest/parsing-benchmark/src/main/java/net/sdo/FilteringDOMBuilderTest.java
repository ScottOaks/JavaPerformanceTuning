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

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

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

@State(Scope.Benchmark)
public class FilteringDOMBuilderTest extends AbstractParsingTest {
    private DOMImplementationLS domLS;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
        super.setup("xml", true, p);
        try {
            System.setProperty(DOMImplementationRegistry.PROPERTY,
                "com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl");
            DOMImplementationRegistry registry =
                DOMImplementationRegistry.newInstance();
            DOMImplementation domImpl = registry.getDOMImplementation("LS 3.0");
            domLS =
                (DOMImplementationLS) domImpl;
        } catch (Exception ex) {
            throw new IOException("Can't create DOM LS implementation", ex);
        }
    }

    private class InputFilter implements LSParserFilter {
        private boolean done = false;
        private boolean idCountReached;

        private boolean inID = false;
        @Override
        public short acceptNode(Node node) {
            if (inID && idCountReached) {
                return NodeFilter.FILTER_SKIP;
	    }
	    if (!inID) {
                return NodeFilter.FILTER_ACCEPT;
	    }
            String s = node.getNodeValue();
            if  (addId(s)) {
                idCountReached = true;
            }
	    inID = false;
            return NodeFilter.FILTER_ACCEPT;
        }

        @Override
        public int getWhatToShow() {
            return NodeFilter.SHOW_ALL;
        }

        @Override
        public short startElement(Element element) {
            if (ID.equals(element.getTagName())) {
		inID = true;
            }
	    else {
	        inID = false;
	    }
            return NodeFilter.FILTER_ACCEPT;
        }
    }

    @Override
    protected void testEngine(Blackhole bh) throws IOException {
        LSParser lsp = domLS.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS,
            "http://www.w3.org/2001/XMLSchema");
        lsp.setFilter(new InputFilter());
        LSInput lsi = domLS.createLSInput();
        lsi.setByteStream(inputStream);
        Document doc = lsp.parse(lsi);
        if (System.getProperty("debugParse") != null) {
            try {
                DOMSource dsource = new DOMSource(doc);
                StreamResult result = new StreamResult(System.err);
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(dsource, result);
            } catch (TransformerException ex) {
                ex.printStackTrace();
            }
        }
	bh.consume(doc);
    }
}
