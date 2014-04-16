/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import extra166y.CustomConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class StringInternTest {

    private static final int SIZE = 10000000;
    private static CustomConcurrentHashMap<String, String> map =
        new CustomConcurrentHashMap<>(CustomConcurrentHashMap.WEAK,
            CustomConcurrentHashMap.EQUALS,
            CustomConcurrentHashMap.WEAK,
            CustomConcurrentHashMap.EQUALS,
            1009);
    private static ArrayList<String> list = new ArrayList<>(SIZE);

    public static String selfIntern(String s) {
        String result = map.get(s);
        if (result == null) {
            result = map.putIfAbsent(s, s);
            if (result == null) {
                result = s;
            }
        }
        return result;
    }

    public static void timeSelf() {
        for (int i = 0; i < SIZE; i++) {
            String s = selfIntern(makeRandomString());
            list.add(s);
        }
    }

    public static void timeJDK() {
        for (int i = 0; i < SIZE; i++) {
            String s = makeRandomString().intern();
            list.add(s);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long then, now;
        switch (args[0]) {
            case "self":
                then = System.currentTimeMillis();
                timeSelf();
                now = System.currentTimeMillis();
                System.out.println("Self took " + (now - then));
                break;
            case "jdk":
                then = System.currentTimeMillis();
                timeJDK();
                now = System.currentTimeMillis();
                System.out.println("JDK took " + (now - then));
                break;
        }
    }

    private static String makeRandomString() {
        Random r = ThreadLocalRandom.current();
        char[] c = new char[r.nextInt(256) + 1];
        for (int i = 0; i < c.length; i++) {
            c[i] = (char) ('a' + r.nextInt(26));
        }
        return new String(c);
    }
}
