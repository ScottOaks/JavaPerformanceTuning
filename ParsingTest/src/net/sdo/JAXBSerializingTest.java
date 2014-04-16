/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import net.sdo.jaxb.FindPopularItemsResponse;

public class JAXBSerializingTest extends AbstractParsingTest {
    private JAXBContext jc;
    private FindPopularItemsResponse responseItems;
    private ByteArrayOutputStream os;

    @Override
    protected void engineInit(RunParams rp) throws IOException {
        try {
            jc = JAXBContext.newInstance("net.sdo.jaxb");
            Unmarshaller u = jc.createUnmarshaller();
            responseItems = (FindPopularItemsResponse) u.unmarshal(new ByteArrayInputStream(rp.getInput()));
            os = new ByteArrayOutputStream();
        } catch (JAXBException ex) {
            throw new IOException("Can't create JAXB context", ex);
        }
    }

    @Override
    protected void engineRun(InputStream is) throws IOException {
        try {
            os.reset();
            Marshaller m = jc.createMarshaller();
            m.marshal(responseItems,os);
        } catch (JAXBException ex) {
            throw new IOException("Can't create JAXB object", ex);
        }
    }
}
