/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

public class TestCleanup {
        
        public static void main(String[] args) {
                CleanupClass c1 = new CleanupClass();
                CleanupClass c2 = new CleanupClass();
                System.out.println("C1 is " + c1);
                System.out.println("C2 is " + c2);
                c1.close();
                c1 = null;
                c2 = null;
                for (int i = 0; i < 1000000; i++) {
                    byte[] b = new byte[1024];
                }
        }
}
