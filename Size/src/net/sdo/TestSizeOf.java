/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import net.sourceforge.sizeof.SizeOf;

public class TestSizeOf {

    private static class A {
        private int i;
    }

    private static class B extends A {
        private Locale l = Locale.US;
    }

    private static class C extends A {
        private ConcurrentHashMap chm = new ConcurrentHashMap();
    }

    private static class ByteClass1 {
        byte b;
    }

    private static class ByteClass2 {
        byte b;
        byte b2;
    }

    private static class ByteClass3 {
        byte b;
        byte b2;
        byte b3;
    }

    private static class ByteClass4 {
        byte b;
        byte b2;
        byte b3;
        byte b4;
    }

    private static class EmptyClass {
    }

    private static class EmptyLocaleClass1 {
        private Locale l;
    }

    private static class EmptyLocaleClass2 {
        private Locale l;
        private Locale l2;
    }

    private static class EmptyLocaleClass3 {
        private Locale l;
        private Locale l2;
        private Locale l3;
    }

    private static class InitializedLocaleClass {

        private Locale l = Locale.US;
    }

    public static void main(String[] args) {
        SizeOf.skipStaticField(true);
        System.out.println("Size of Calendar is " + SizeOf.deepSizeOf(Calendar.getInstance()));
        HashMap hm = new HashMap();
        System.out.println("Size of default HashMap is " + SizeOf.sizeOf(hm) + " deep: " + SizeOf.deepSizeOf(hm));
        ConcurrentHashMap chm = new ConcurrentHashMap();
        System.out.println("Size of default ConcurrentHashMap is " + SizeOf.sizeOf(chm) + " deep: " + SizeOf.deepSizeOf(chm));
        for (int i = 1; i < 1024; i *= 2) {
            System.out.println("Size of size " + i + " HashMap is " + SizeOf.deepSizeOf(new HashMap(i)));
            System.out.println("Size of size " + i + " ConcurrentHashMap is " + SizeOf.deepSizeOf(new ConcurrentHashMap(i)));
        }
        for (int i = 1; i < 1024; i *= 2) {
            hm = new HashMap(i);
            for (int j = 0; j < i; j++) {
                hm.put(new Object(), new Object());
            }
            System.out.println("Size of full size " + i + " HashMap is " + SizeOf.deepSizeOf(hm));
            hm = null;
            chm = new ConcurrentHashMap(i);
            for (int j = 0; j < i; j++) {
                chm.put(new Object(), new Object());
            }
            System.out.println("Size of full size " + i + " ConcurrentHashMap is " + SizeOf.deepSizeOf(chm));
            chm = null;
        }
        SoftReference sr = new SoftReference(null);
        System.out.println("Shallow size of sr is " + SizeOf.sizeOf(sr));
        System.out.println("Deep size of sr is " + SizeOf.deepSizeOf(sr));
        WeakReference wr = new WeakReference(null);
        System.out.println("Shallow size of wr is " + SizeOf.sizeOf(wr));
        System.out.println("Deep size of wr is " + SizeOf.deepSizeOf(wr));
        EmptyClass ec = new EmptyClass();
        System.out.println("Shallow size of ec is " + SizeOf.sizeOf(ec));
        System.out.println("Deep size of ec is " + SizeOf.deepSizeOf(ec));

        EmptyLocaleClass1 elc1 = new EmptyLocaleClass1();
        System.out.println("Shallow size of elc is " + SizeOf.sizeOf(elc1));
        System.out.println("Deep size of elc is " + SizeOf.deepSizeOf(elc1));
        EmptyLocaleClass2 elc2 = new EmptyLocaleClass2();
        System.out.println("Shallow size of elc2 is " + SizeOf.sizeOf(elc2));
        System.out.println("Deep size of elc2 is " + SizeOf.deepSizeOf(elc2));
        EmptyLocaleClass3 elc3 = new EmptyLocaleClass3();
        System.out.println("Shallow size of elc3 is " + SizeOf.sizeOf(elc3));
        System.out.println("Deep size of elc3 is " + SizeOf.deepSizeOf(elc3));
        InitializedLocaleClass ilc = new InitializedLocaleClass();
        System.out.println("Shallow size of ilc is " + SizeOf.sizeOf(ilc));
        System.out.println("Deep size of ilc is " + SizeOf.deepSizeOf(ilc));

        A a = new A();
        System.out.println("Shallow size of a is " + SizeOf.sizeOf(a));
        System.out.println("Deep size of a is " + SizeOf.deepSizeOf(a));
        B b = new B();
        System.out.println("Shallow size of b is " + SizeOf.sizeOf(b));
        System.out.println("Deep size of b is " + SizeOf.deepSizeOf(b));
        C c = new C();
        System.out.println("Shallow size of c is " + SizeOf.sizeOf(c));
        System.out.println("Deep size of c is " + SizeOf.deepSizeOf(c));

        ByteClass1 bc1 = new ByteClass1();
        System.out.println("Shallow size of bc1 is " + SizeOf.sizeOf(bc1));
        System.out.println("Deep size of bc1 is " + SizeOf.deepSizeOf(bc1));
        ByteClass2 bc2 = new ByteClass2();
        System.out.println("Shallow size of bc2 is " + SizeOf.sizeOf(bc2));
        System.out.println("Deep size of bc2 is " + SizeOf.deepSizeOf(bc2));
        ByteClass3 bc3 = new ByteClass3();
        System.out.println("Shallow size of bc3 is " + SizeOf.sizeOf(bc3));
        System.out.println("Deep size of bc3 is " + SizeOf.deepSizeOf(bc3));
        ByteClass4 bc4 = new ByteClass4();
        System.out.println("Shallow size of bc4 is " + SizeOf.sizeOf(bc4));
        System.out.println("Deep size of bc4 is " + SizeOf.deepSizeOf(bc4));

        byte[] barr0 = new byte[0];
        System.out.println("Shallow size of barr0 is " + SizeOf.sizeOf(barr0));
        System.out.println("Deep size of barr0 is " + SizeOf.deepSizeOf(barr0));
        int[] iarr0 = new int[0];
        System.out.println("Shallow size of iarr0 is " + SizeOf.sizeOf(iarr0));
        System.out.println("Deep size of iarr0 is " + SizeOf.deepSizeOf(iarr0));
        int[] iarr1 = new int[1];
        System.out.println("Shallow size of iarr1 is " + SizeOf.sizeOf(iarr1));
        System.out.println("Deep size of iarr1 is " + SizeOf.deepSizeOf(iarr1));
        int[] iarr128 = new int[128];
        System.out.println("Shallow size of iarr128 is " + SizeOf.sizeOf(iarr128));
        System.out.println("Deep size of iarr128 is " + SizeOf.deepSizeOf(iarr128));
    }
}
