/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PausingThreadPoolExecutor extends ThreadPoolExecutor {

    private boolean isPaused;
    private ReentrantLock pauseLock = new ReentrantLock();
    private Condition unpaused = pauseLock.newCondition();
    private CountDownLatch latch;
    private BlockingQueue<Runnable> queue;
    private int latchCount;

    public PausingThreadPoolExecutor(int nThreads, BlockingQueue<Runnable> queue) {
        super(nThreads, nThreads, Long.MAX_VALUE, TimeUnit.DAYS, queue);
        this.queue = queue;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        pauseLock.lock();
        try {
            while (isPaused) {
                unpaused.await();
            }
        } catch (InterruptedException ie) {
            t.interrupt();
        } finally {
            pauseLock.unlock();
        }
    }

    public void addTask(Runnable r) {
        latchCount++;
        queue.add(r);
    }
    
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        latch.countDown();
    }

    public void pause() {
        pauseLock.lock();
        try {
            isPaused = true;
        } finally {
            pauseLock.unlock();
        }
    }

    public void resume() {
        pauseLock.lock();
        latch = new CountDownLatch(latchCount);
        latchCount = 0;
        try {
            isPaused = false;
            unpaused.signalAll();
        } finally {
            pauseLock.unlock();
        }
        try {
            latch.await();
        } catch (InterruptedException ex) {
        }
    }
}
