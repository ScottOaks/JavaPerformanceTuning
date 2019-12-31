/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class FindFilter {
    private static boolean verbose;
    private static volatile String answer = null;

    private static long calcMulti(ArrayList<String> al) {
        long then = System.currentTimeMillis();
        Stream<String> stream = al.stream();
        Optional<String> t = stream.filter(symbol -> symbol.charAt(0) != 'A').
            filter(symbol -> symbol.charAt(1) != 'A').
            filter(symbol -> symbol.charAt(2) != 'A').
            filter(symbol -> symbol.charAt(3) != 'A').findFirst();
        answer = t.get();
        long now = System.currentTimeMillis();
        return now - then;
    }

    private static ArrayList<String> calcArray(ArrayList<String> src,
                                               int c, char target) {
        ArrayList<String> dst = new ArrayList<>();
        for (String s : src) {
            if (s.charAt(c) != target)
                dst.add(s);
        }
        return dst;
    }

    private static long calcEager(ArrayList<String> al) {
        long then = System.currentTimeMillis();
        ArrayList<String> al1 = calcArray(al, 0, 'A');
        ArrayList<String> al2 = calcArray(al1, 1, 'A');
        ArrayList<String> al3 = calcArray(al2, 2, 'A');
        ArrayList<String> al4 = calcArray(al3, 3, 'A');
        answer = al4.get(0);
        long now = System.currentTimeMillis();
        return now - then;
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
                case "Multi":
                    time += calcMulti(al);
                    break;
                case "Eager":
                    time += calcEager(al);
                    break;
            }
        }
        System.out.println("Operation took " + time + " ms for " + answer);
    }
}
