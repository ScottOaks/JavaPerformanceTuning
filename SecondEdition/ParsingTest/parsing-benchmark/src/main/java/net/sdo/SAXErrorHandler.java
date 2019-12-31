/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class SAXErrorHandler implements ErrorHandler {

    public void warning(SAXParseException spe) throws SAXParseException {
        System.out.println("SAX Warning" + spe);
        throw spe;
    }

    public void error(SAXParseException spe) throws SAXParseException {
        System.out.println("SAX error" + spe);
        throw spe;
    }

    public void fatalError(SAXParseException spe) throws SAXParseException {
        System.out.println("SAX fatalError" + spe);
        throw spe;
    }
}
