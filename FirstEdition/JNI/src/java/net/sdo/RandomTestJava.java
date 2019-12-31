/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.util.Random;

public class RandomTestJava {
    private Random r = new Random();
    private static int nTrials;
    private static int nValues;

    public static void main(String[] args) {
        nTrials = Integer.parseInt(args[0]);
        nValues = Integer.parseInt(args[1]);
        doit(false); // warmup
        doit(true);
    }

    public static void doit(boolean report) {
        RandomTestJava rtj = new RandomTestJava();

        // Java Only
        double error = 0;
        long then = System.currentTimeMillis();
        for (int i = 0; i < nTrials; i++) {
            double average = rtj.calc(nValues);
            error += 50 - average;
        }
        long now = System.currentTimeMillis();
        if (report) {
            System.out.println("Error: " + error + " calcuated in Java in " + (now - then));
        }

        // Java Java C
        error = 0;
        then = System.currentTimeMillis();
        for (int i = 0; i < nTrials; i++) {
            double average = rtj.calcCRandom(nValues);
            error += 50 - average;
        }
        now = System.currentTimeMillis();
        if (report) {
            System.out.println("Error: " + error + " calcuated in C random only in " + (now - then));
        }
        
        // Java C C
        error = 0;
        then = System.currentTimeMillis();
        for (int i = 0; i < nTrials; i++) {
            double average = rtj.calc0(nValues);
            error += 50 - average;
        }
        now = System.currentTimeMillis();
        if (report) {
            System.out.println("Error: " + error + " calcuated in C in " + (now - then));
        }

        // C Java Java
        then = System.currentTimeMillis();
        error = rtj.calcFromC(nTrials, nValues);
        now = System.currentTimeMillis();
        if (report) {
            System.out.println("Error: " + error + " calcuated from C in " + (now - then));
        }
    }

    private native double calc0(int nValues);
    private native double calcFromC(int nTrials, int nValues);
    private native int getCRandom();

    public double calc(int nValues) {
        long d = 0;
        for (int j = 0; j < nValues; j++) {
            int n = r.nextInt(100) + 1;
            d += n;
        }
        double average = d / nValues;
        return average;
    }

    public double calcCRandom(int nValues) {
        long d = 0;
        for (int j = 0; j < nValues; j++) {
            int n = getCRandom();
            d += n;
        }
        double average = d / nValues;
        return average;
    }

    static {
        System.load(System.getProperty("LIBPATH") + "/libRandomTestCLibrary.so");
    }
}
