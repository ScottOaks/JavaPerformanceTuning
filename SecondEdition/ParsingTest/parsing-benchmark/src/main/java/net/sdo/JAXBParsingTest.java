/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class JAXBParsingTest extends AbstractParsingTest {
    private JAXBContext jc;
    private Unmarshaller u;

    @Setup
    public void setup(ParsingTest.Parameters p) throws IOException {
        super.setup("xml", true, p);
        try {
            jc = JAXBContext.newInstance("net.sdo.jaxb");
            u = jc.createUnmarshaller();
        } catch (JAXBException ex) {
            throw new IOException("Can't create JAXB context", ex);
        }
    }

    @Override
    protected void testEngine(Blackhole bh) throws IOException {
        try {
            bh.consume(u.unmarshal(inputStream));
        } catch (JAXBException ex) {
            throw new IOException("Can't create JAXB object", ex);
        }
    }
}
