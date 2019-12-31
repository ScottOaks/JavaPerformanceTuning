/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class CountFilter {
    private static boolean verbose;
    private static volatile int count = 0;

    private static long countFilter(ArrayList<String> al) {
        long then = System.currentTimeMillis();
        Stream<String> stream = al.stream();
        stream.filter(
		    symbol -> symbol.charAt(0) != 'A' &&
                    symbol.charAt(1) != 'A' &&
                    symbol.charAt(2) != 'A' &&
                    symbol.charAt(3) != 'A').
		            forEach(symbol -> { count++; });
        long now = System.currentTimeMillis();
        return now - then;
    }

    private static long countIterator(ArrayList<String> al) {
	    long then = System.currentTimeMillis();
	    for (String symbol : al) {
	        if (symbol.charAt(0) != 'A' &&
	            symbol.charAt(1) != 'A' &&
	            symbol.charAt(2) != 'A' &&
	            symbol.charAt(3) != 'A')
	            count++;
		}
	    return System.currentTimeMillis() - then;
    }

    public static void main(String[] args) {
        verbose = Boolean.getBoolean("verbose");
        ArrayList<String> al = new ArrayList<>(500000);
        for (int first = (int) 'A'; first <= (int) 'Z'; first++) {
            for (int second = 'A'; second <= 'Z'; second++) {
            for (int third = 'A'; third <= 'Z'; third++) {
                for (int fourth = 'A'; fourth <= 'Z'; fourth++) {
                    char[] c = new char[4];
                    c[0] = (char) first;
                    c[1] = (char) second;
                    c[2] = (char) third;
                    c[3] = (char) fourth;
                    al.add(new String(c));
                }
            }
            }
        }
        System.out.println("Start");
        long time = 0;
        for (int i = 0; i < 1000; i++) {
            switch (args[0]) {
                case "Filter":
                    time += countFilter(al);
                    break;
                case "Iterator":
                    time += countIterator(al);
                    break;
            }
        }
        System.out.println("Operation took " + time + " ms for " + count);
    }
}
