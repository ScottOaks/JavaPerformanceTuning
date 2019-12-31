/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoadingDelegate {
    public static volatile long sum;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(args[0]);
	ClassLoader cl;
	CalcInterface ci = null;
	URL[] urls = null;
	if (args.length > 2) {
            urls = new URL[]{new File(args[1]).toURL()};
	}
	else {
	    cl = ClassLoadingDelegate.class.getClassLoader();
	    ci = (CalcInterface) cl.loadClass(args[1]).newInstance();
	}
        for (int j = 0; j < 3; j++) {
	    sum = 0;
            long then = System.nanoTime();
            for (int i = 0; i < N; i++) {
		if (args.length > 2) {
	            cl = new URLClassLoader(urls);
                    ci = (CalcInterface) cl.loadClass(args[2]).newInstance();
		}
                sum += ci.calc();
            }
	    System.out.println(sum);
	    long now = System.nanoTime();
            long time = (now - then) / 1000;
            System.out.println("Average time " + j + ":  " + ((double) (time) / N));
        }            
    }
}
