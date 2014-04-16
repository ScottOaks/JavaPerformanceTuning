/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RunParams {
    private Properties props;

    public RunParams(String filename) throws IOException {
            props = new Properties();
            props.load(new FileReader(filename));
    }

    public byte[] getInput() throws IOException {
            byte[] b;
            InputStream is = new FileInputStream(props.getProperty("inputFile"));
            int size = is.available();
            b = new byte[size];
            is.read(b);
            is.close();
            return b;
    }

    public String getClassName() {
            return props.getProperty("className");
    }

    public int getTargetItemCount() {
            String s = props.getProperty("targetItemCount");
            if (s == null) {
                    return Integer.MAX_VALUE;
            }
            return Integer.parseInt(s);
    }

    public String getSchemaFileName() {
            return props.getProperty("schemaFileName");
    }
}
