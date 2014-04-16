/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class ClassLoaderTest implements Runnable {

    private static String[] classNames;
    private static AtomicLong time = new AtomicLong();
    private static URL[] urls;
    private static URLClassLoader ucl;

    private static boolean increaseStackDepth = false;

    private static void loadClasses(String[] classNames, int count, ClassLoader cl) throws ClassNotFoundException {
        for (int i = 0; i < count; i++) {
            String name = classNames[i];
            cl.loadClass(name);
        }
    }

    private static void initializeFromJar(String fileName) throws ZipException, IOException {
        ZipFile zf = new ZipFile(new File(fileName));
        Enumeration e = zf.entries();
        ArrayList<String> al = new ArrayList<String>();
        while (e.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) e.nextElement();
            String s = ze.getName();
            if (s.endsWith(".class")) {
                al.add(s);
            }
        }
        String[] names = al.toArray(new String[al.size()]);
        classNames = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            classNames[i] = names[i].substring(0, names[i].length() - 6).replaceAll("/", ".");
        }
    }

    public void a1() { a2(); }
    public void a2() { a3(); }
    public void a3() { a4(); }
    public void a4() { a5(); }
    public void a5() { a6(); }
    public void a6() { a7(); }
    public void a7() { a8(); }
    public void a8() { a9(); }
    public void a9() { a10(); }
    public void a10() { a11(); }
    public void a11() { a12(); }
    public void a12() { a13(); }
    public void a13() { a14(); }
    public void a14() { a15(); }
    public void a15() { a16(); }
    public void a16() { a17(); }
    public void a17() { a18(); }
    public void a18() { a19(); }
    public void a19() { a20(); }
    public void a20() { a21(); }
    public void a21() { a22(); }
    public void a22() { a23(); }
    public void a23() { a24(); }
    public void a24() { a25(); }
    public void a25() { a26(); }
    public void a26() { a27(); }
    public void a27() { a28(); }
    public void a28() { a29(); }
    public void a29() { a30(); }
    public void a30() { a31(); }
    public void a31() { a32(); }
    public void a32() { a33(); }
    public void a33() { a34(); }
    public void a34() { a35(); }
    public void a35() { a36(); }
    public void a36() { a37(); }
    public void a37() { a38(); }
    public void a38() { a39(); }
    public void a39() { a40(); }
    public void a40() {
        try {
            loadClasses(classNames, classNames.length, new URLClassLoader(urls));
        } catch (ClassNotFoundException ex) {
            System.err.println("Can't define class ");
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    public void run() {
        try {
        if (increaseStackDepth) {
            a1();
        }
        else {
            loadClasses(classNames, classNames.length, new URLClassLoader(urls));
        }
        } catch (Exception e) {
            System.err.println("Can't define class ");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, ZipException, IOException {
        initializeFromJar(args[0]);
        int nThreads = Integer.parseInt(args[1]);
        increaseStackDepth = Boolean.parseBoolean(args[2]);
        urls = new URL[]{new File(args[0]).toURL()};
        for (int i = 0; i < 10; i++) {
            ucl = new URLClassLoader(urls);
            loadClasses(classNames, classNames.length, ucl);
        }
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();    
        PausingThreadPoolExecutor tpe = new PausingThreadPoolExecutor(nThreads, queue);
        tpe.prestartAllCoreThreads();
        for (int j = 0; j < 100; j++) {
            tpe.pause();
            ucl = new URLClassLoader(urls);
            for (int i = 0; i < nThreads; i++) {
                tpe.addTask(new ClassLoaderTest());
            }
            long then = System.currentTimeMillis();
            tpe.resume();
            long now = System.currentTimeMillis();
            time.getAndAdd(now - then);
        }
        tpe.shutdown();
        System.out.println("Threads: " + nThreads + ", Classes: " + classNames.length + ", Time: " + time);
    }
}
