/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CleanupClass {
    private static class CleanupFinalizer extends WeakReference {
        private static ReferenceQueue<CleanupFinalizer> finRefQueue;
        private static HashSet<CleanupFinalizer> pendingRefs = new HashSet<>();

        static {
            finRefQueue = new ReferenceQueue<>();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    CleanupFinalizer fr;
                    while (true) {
                        try {
                            fr = (CleanupFinalizer) finRefQueue.remove();
                            fr.cleanup();
                            pendingRefs.remove(fr);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            Logger.getLogger(CleanupFinalizer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.start();
        }
        private boolean closed = false;

        public CleanupFinalizer(Object o) {
            super(o, finRefQueue);
            pendingRefs.add(this);
        }

        public void setClosed() {
            System.out.println("Called setClose for " + this);
            closed = true;
            doNativeCleanup();
        }

        public void cleanup() {
            if (!closed) {
            System.out.println("Called cleanup for " + this);
                doNativeCleanup();
            }
        }

        private void doNativeCleanup() {}
    }

    CleanupFinalizer cf;

    public CleanupClass() {
        cf = new CleanupFinalizer(this);
    }

    public void close() {
        cf.setClosed();
    }
}
