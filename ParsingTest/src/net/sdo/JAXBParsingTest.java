/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBParsingTest extends AbstractParsingTest {
    private JAXBContext jc;
    private Unmarshaller u;
    @Override
    protected void engineInit(RunParams rp) throws IOException {
        try {
            jc = JAXBContext.newInstance("net.sdo.jaxb");
            u = jc.createUnmarshaller();
        } catch (JAXBException ex) {
            throw new IOException("Can't create JAXB context", ex);
        }
    }

    @Override
    protected void engineRun(InputStream is) throws IOException {
        try {
            u.unmarshal(is);
        } catch (JAXBException ex) {
            throw new IOException("Can't create JAXB object", ex);
        }
    }
}
