/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

public class ParsingTest {
    public static void main(String[] args) throws Exception {
        int nIterations = 1000000;
        int nWarmupIterations = 1000000;
        int curArg = 0;
        while (args[curArg].startsWith("-")) {
            if (args[curArg].equals("-n")) {
                nIterations = Integer.parseInt(args[++curArg]);
            }
            else if (args[curArg].equals("-w")) {
                nWarmupIterations = Integer.parseInt(args[++curArg]);
            }
            else {
                usage();
            }
            curArg++;
        }
        RunParams rp = new RunParams(args[curArg]);
        AbstractParsingTest pti = (AbstractParsingTest) Class.forName(rp.getClassName()).newInstance();
        pti.init(rp);
        for (int i = 0; i < nWarmupIterations; i++) {
            pti.run();
        }
        System.gc();
        System.runFinalization();
        System.gc();
        System.out.println("Starting Measurement");
        long then = System.currentTimeMillis();
        for (int i = 0; i < nIterations; i++) {
            pti.run();
        }
        long now = System.currentTimeMillis();
        long elapsed = now - then;
        System.out.println("Time to execute " + nIterations + " iterations is " + elapsed);
        System.out.println("Time per operation: " + ((double) elapsed) / nIterations);
    }

    private static void usage() {
        System.out.println("Usage:");
        System.out.println("java ParsingTest TestClassName");
        System.exit(-1);
    }
}
