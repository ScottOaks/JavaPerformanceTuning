/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo.stock;

import java.io.StringWriter;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class StockPriceUtils {
    public static final int STANDARD = 0;
    public static final int LOGGER = 1;
    public static final int HALF = 2;
    public static final int ALTERNATE = 3;

    public static final int SYMBOL_LENGTH = 4;
    public static final int NSYMBOLS = 26 * SYMBOL_LENGTH;

    public static String makeSymbol(int symbolNumber) {
        String s = new BigInteger("" + symbolNumber).toString(26);
        StringBuilder sb = new StringBuilder();
        int len = SYMBOL_LENGTH - s.length();
        while (len-- > 0) {
            sb.append('A');
        }
        for (int i = 0; i < s.length(); i++) {
            char c = Character.toUpperCase(s.charAt(i));
            if (c >= '0' & c <= '9') {
                c = (char) ('A' + c - '0');
            } else {
                c = (char) (c + 10);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getRandomSymbol() {
        return makeSymbol(ThreadLocalRandom.current().nextInt(NSYMBOLS));
    }
}
