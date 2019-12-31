package net.sdo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.openjdk.jmh.infra.Blackhole;

public abstract class AbstractParsingTest {
    protected static String ID = "itemId";
    protected ByteArrayInputStream inputStream;
    protected ByteArrayInputStream schemaInputStream;

    private int targetIdCount;
    private ArrayList<String> ids;
    private boolean isDOM;

    protected boolean addId(String s) {
        ids.add(s);
        if (targetIdCount > 0 && ids.size() == targetIdCount) {
            return true;
        }
        return false;
    }

    protected abstract void testEngine(Blackhole bh) throws IOException;
    public void test(Blackhole bh) throws IOException {
        inputStream.reset();
	ids = new ArrayList<>();
        testEngine(bh);
	if (!isDOM) {
            if (System.getProperty("debugParse") != null) {
                System.out.println(ids);
            }
            if (targetIdCount > 0 && targetIdCount != ids.size()) {
                throw new IOException("Unexpected count in document, found " + ids.size());
            }
	}
    }

    private ByteArrayInputStream readInputStream(String name) throws IOException {
	InputStream is = getClass().getClassLoader().getResourceAsStream(name);
	ByteArrayOutputStream baos = new ByteArrayOutputStream(is.available());
	byte[] buf = new byte[is.available()];
	int n = is.read(buf);
	while (n > 0) {
	    baos.write(buf, 0, n);
	    n = is.read(buf);
	}
        byte[] data = baos.toByteArray();
        is.close();
	baos.close();
	return new ByteArrayInputStream(data);
    }

    public void setup(String type, boolean isDOM, ParsingTest.Parameters p) throws IOException {
	this.isDOM = isDOM;
	targetIdCount = p.targetIdCount;

	inputStream = readInputStream(type + "/inputFile." + type);
	schemaInputStream = readInputStream("xsd/schema.xsd");
    }
}
