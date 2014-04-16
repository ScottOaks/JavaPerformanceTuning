/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class UncontendedTest {
    private static ArrayList<Long> answers = new ArrayList<>();
    private static long NLOOPS = 500000000;
    private static interface Getter {
        public long getAndIncrement();
    }

    private static class AtomicGetter implements Getter {
        private AtomicLong al = new AtomicLong();
        public long getAndIncrement() {
            return al.getAndIncrement();
        }
    }

    private static class SyncGetter implements Getter {
        private long l;
        @Override
        public synchronized long getAndIncrement() {
            return l++;
        }
    }

    private static class UnsyncGetter implements Getter {
        private long l;
        @Override
        public long getAndIncrement() {
            return l++;
        }
    }

    private Getter getter;

    public UncontendedTest(Getter g) {
        getter = g;
    }

    public void run() {
        long l = 0;
        for (int i = 0; i < NLOOPS; i++) {
            l += getter.getAndIncrement();
        }
        answers.add(l);
    }

    public static void main(String[] args) {
        // Warmup
        AtomicGetter atomic = new AtomicGetter();
        SyncGetter sync = new SyncGetter();
        UnsyncGetter unsync = new UnsyncGetter();

        doLoop(atomic);
        doLoop(sync);
        doLoop(unsync);

        //Time
        long syncTime = doLoop(sync);
        System.out.println("Sync calc: " + answers);
        long atomicTime = doLoop(atomic);
        System.out.println("Atomic calc: " + answers);
        long unsyncTime = doLoop(unsync);
        System.out.println("Unsync calc: " + answers);
        System.out.println("Time for atomic: " + atomicTime);
        System.out.println("Time for synchronous: " + syncTime);
        System.out.println("Time for unsync: " + unsyncTime);
    }

    private static long doLoop(Getter g) {
        UncontendedTest uct = new UncontendedTest(g);
        long then = System.currentTimeMillis();
        uct.run();
        long now = System.currentTimeMillis();
        return (now - then);
    }
}
