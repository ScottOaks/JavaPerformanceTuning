/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ExceptionTest {
    protected abstract ArrayList<String> test(int num, int pctError);

    private static class CheckedException extends Exception {
        public CheckedException(String s) {
            super(s);
        }
    }

    HashMap<Exception, Integer> hm;

    protected void countException(Exception e) {
        if (hm == null) {
            hm = new HashMap<Exception, Integer>();
        }
        Integer count = hm.get(e);
        if (count == null) {
            hm.put(e, new Integer(1));
            if (e.getStackTrace().length == 0) {
                System.out.println("Warning: Unexpected 0-length StackTrace");
            }
        } else {
            hm.put(e, new Integer(count.intValue() + 1));
            if (e.getStackTrace().length != 0) {
                System.out.println("Warning: Unexpected non 0-length StackTrace");
            }
        }
    }

    public static class ExceptionTestUnchecked extends ExceptionTest {
        @Override
        public ArrayList<String> test(int num, int pctError) {
            ArrayList<String> al = new ArrayList<String>();
            for (int i = 0; i < num; i++) {
                Object o = null;
                if ((i % pctError) != 0) {
                o = new Object();
                }
                try {
                al.add(o.toString());
                } catch (NullPointerException npe) {
                    countException(npe);
                }
            }
            System.out.println("Total distinct exceptions: " + hm.size());
            hm = null;
            return al;
        }
    }

    public static class ExceptionTestChecked extends ExceptionTest {
        public ArrayList<String> test(int num, int pctError) {
            ArrayList<String> al = new ArrayList<String>();
            for (int i = 0; i < num; i++) {
                try {
                    if ((i % pctError) == 0) {
                        throw new CheckedException("Failed");
                    }
                    Object o = new Object();
                    al.add(o.toString());
                } catch (CheckedException e) {
                    countException(e);
                }
            }
            System.out.println("Total distinct exceptions: " + hm.size());
            hm = null;
            return al;
        }
    }

    public static class ExceptionTestDefensive extends ExceptionTest {
        @Override
        public ArrayList<String> test(int num, int pctError) {
            ArrayList<String> al = new ArrayList<String>();
            for (int i = 0; i < num; i++) {
                Object o = null;
                if ((i % pctError) != 0) {
                    o = new Object();
                }
                if (o != null) {
                    al.add(o.toString());
                }
            }
            return al;
        }
    }

    public static class ExceptionTestBigStack {
        public ArrayList<String> test(int num, int pctError, ExceptionTest et) {
            return a1(num, pctError, et);
        }

        private ArrayList<String> doTest(int num, int pctError, ExceptionTest et) {
            return et.test(num, pctError);
        }

        private ArrayList<String> a1(int num, int pctError, ExceptionTest et) { return a2(num, pctError, et); }
        private ArrayList<String> a2(int num, int pctError, ExceptionTest et) { return a3(num, pctError, et); }
        private ArrayList<String> a3(int num, int pctError, ExceptionTest et) { return a4(num, pctError, et); }
        private ArrayList<String> a4(int num, int pctError, ExceptionTest et) { return a5(num, pctError, et); }
        private ArrayList<String> a5(int num, int pctError, ExceptionTest et) { return a6(num, pctError, et); }
        private ArrayList<String> a6(int num, int pctError, ExceptionTest et) { return a7(num, pctError, et); }
        private ArrayList<String> a7(int num, int pctError, ExceptionTest et) { return a8(num, pctError, et); }
        private ArrayList<String> a8(int num, int pctError, ExceptionTest et) { return a9(num, pctError, et); }
        private ArrayList<String> a9(int num, int pctError, ExceptionTest et) { return a10(num, pctError, et); }
        private ArrayList<String> a10(int num, int pctError, ExceptionTest et) { return a11(num, pctError, et); }
        private ArrayList<String> a11(int num, int pctError, ExceptionTest et) { return a12(num, pctError, et); }
        private ArrayList<String> a12(int num, int pctError, ExceptionTest et) { return a13(num, pctError, et); }
        private ArrayList<String> a13(int num, int pctError, ExceptionTest et) { return a14(num, pctError, et); }
        private ArrayList<String> a14(int num, int pctError, ExceptionTest et) { return a15(num, pctError, et); }
        private ArrayList<String> a15(int num, int pctError, ExceptionTest et) { return a16(num, pctError, et); }
        private ArrayList<String> a16(int num, int pctError, ExceptionTest et) { return a17(num, pctError, et); }
        private ArrayList<String> a17(int num, int pctError, ExceptionTest et) { return a18(num, pctError, et); }
        private ArrayList<String> a18(int num, int pctError, ExceptionTest et) { return a19(num, pctError, et); }
        private ArrayList<String> a19(int num, int pctError, ExceptionTest et) { return a20(num, pctError, et); }
        private ArrayList<String> a20(int num, int pctError, ExceptionTest et) { return a21(num, pctError, et); }
        private ArrayList<String> a21(int num, int pctError, ExceptionTest et) { return a22(num, pctError, et); }
        private ArrayList<String> a22(int num, int pctError, ExceptionTest et) { return a23(num, pctError, et); }
        private ArrayList<String> a23(int num, int pctError, ExceptionTest et) { return a24(num, pctError, et); }
        private ArrayList<String> a24(int num, int pctError, ExceptionTest et) { return a25(num, pctError, et); }
        private ArrayList<String> a25(int num, int pctError, ExceptionTest et) { return a26(num, pctError, et); }
        private ArrayList<String> a26(int num, int pctError, ExceptionTest et) { return a27(num, pctError, et); }
        private ArrayList<String> a27(int num, int pctError, ExceptionTest et) { return a28(num, pctError, et); }
        private ArrayList<String> a28(int num, int pctError, ExceptionTest et) { return a29(num, pctError, et); }
        private ArrayList<String> a29(int num, int pctError, ExceptionTest et) { return a30(num, pctError, et); }
        private ArrayList<String> a30(int num, int pctError, ExceptionTest et) { return a31(num, pctError, et); }
        private ArrayList<String> a31(int num, int pctError, ExceptionTest et) { return a32(num, pctError, et); }
        private ArrayList<String> a32(int num, int pctError, ExceptionTest et) { return a33(num, pctError, et); }
        private ArrayList<String> a33(int num, int pctError, ExceptionTest et) { return a34(num, pctError, et); }
        private ArrayList<String> a34(int num, int pctError, ExceptionTest et) { return a35(num, pctError, et); }
        private ArrayList<String> a35(int num, int pctError, ExceptionTest et) { return a36(num, pctError, et); }
        private ArrayList<String> a36(int num, int pctError, ExceptionTest et) { return a37(num, pctError, et); }
        private ArrayList<String> a37(int num, int pctError, ExceptionTest et) { return a38(num, pctError, et); }
        private ArrayList<String> a38(int num, int pctError, ExceptionTest et) { return a39(num, pctError, et); }
        private ArrayList<String> a39(int num, int pctError, ExceptionTest et) { return a40(num, pctError, et); }
        private ArrayList<String> a40(int num, int pctError, ExceptionTest et) { return a41(num, pctError, et); }
        private ArrayList<String> a41(int num, int pctError, ExceptionTest et) { return a42(num, pctError, et); }
        private ArrayList<String> a42(int num, int pctError, ExceptionTest et) { return a43(num, pctError, et); }
        private ArrayList<String> a43(int num, int pctError, ExceptionTest et) { return a44(num, pctError, et); }
        private ArrayList<String> a44(int num, int pctError, ExceptionTest et) { return a45(num, pctError, et); }
        private ArrayList<String> a45(int num, int pctError, ExceptionTest et) { return a46(num, pctError, et); }
        private ArrayList<String> a46(int num, int pctError, ExceptionTest et) { return a47(num, pctError, et); }
        private ArrayList<String> a47(int num, int pctError, ExceptionTest et) { return a48(num, pctError, et); }
        private ArrayList<String> a48(int num, int pctError, ExceptionTest et) { return a49(num, pctError, et); }
        private ArrayList<String> a49(int num, int pctError, ExceptionTest et) { return a50(num, pctError, et); }
        private ArrayList<String> a50(int num, int pctError, ExceptionTest et) { return a51(num, pctError, et); }
        private ArrayList<String> a51(int num, int pctError, ExceptionTest et) { return a52(num, pctError, et); }
        private ArrayList<String> a52(int num, int pctError, ExceptionTest et) { return a53(num, pctError, et); }
        private ArrayList<String> a53(int num, int pctError, ExceptionTest et) { return a54(num, pctError, et); }
        private ArrayList<String> a54(int num, int pctError, ExceptionTest et) { return a55(num, pctError, et); }
        private ArrayList<String> a55(int num, int pctError, ExceptionTest et) { return a56(num, pctError, et); }
        private ArrayList<String> a56(int num, int pctError, ExceptionTest et) { return a57(num, pctError, et); }
        private ArrayList<String> a57(int num, int pctError, ExceptionTest et) { return a58(num, pctError, et); }
        private ArrayList<String> a58(int num, int pctError, ExceptionTest et) { return a59(num, pctError, et); }
        private ArrayList<String> a59(int num, int pctError, ExceptionTest et) { return a60(num, pctError, et); }
        private ArrayList<String> a60(int num, int pctError, ExceptionTest et) { return a61(num, pctError, et); }
        private ArrayList<String> a61(int num, int pctError, ExceptionTest et) { return a62(num, pctError, et); }
        private ArrayList<String> a62(int num, int pctError, ExceptionTest et) { return a63(num, pctError, et); }
        private ArrayList<String> a63(int num, int pctError, ExceptionTest et) { return a64(num, pctError, et); }
        private ArrayList<String> a64(int num, int pctError, ExceptionTest et) { return a65(num, pctError, et); }
        private ArrayList<String> a65(int num, int pctError, ExceptionTest et) { return a66(num, pctError, et); }
        private ArrayList<String> a66(int num, int pctError, ExceptionTest et) { return a67(num, pctError, et); }
        private ArrayList<String> a67(int num, int pctError, ExceptionTest et) { return a68(num, pctError, et); }
        private ArrayList<String> a68(int num, int pctError, ExceptionTest et) { return a69(num, pctError, et); }
        private ArrayList<String> a69(int num, int pctError, ExceptionTest et) { return a70(num, pctError, et); }
        private ArrayList<String> a70(int num, int pctError, ExceptionTest et) { return a71(num, pctError, et); }
        private ArrayList<String> a71(int num, int pctError, ExceptionTest et) { return a72(num, pctError, et); }
        private ArrayList<String> a72(int num, int pctError, ExceptionTest et) { return a73(num, pctError, et); }
        private ArrayList<String> a73(int num, int pctError, ExceptionTest et) { return a74(num, pctError, et); }
        private ArrayList<String> a74(int num, int pctError, ExceptionTest et) { return a75(num, pctError, et); }
        private ArrayList<String> a75(int num, int pctError, ExceptionTest et) { return a76(num, pctError, et); }
        private ArrayList<String> a76(int num, int pctError, ExceptionTest et) { return a77(num, pctError, et); }
        private ArrayList<String> a77(int num, int pctError, ExceptionTest et) { return a78(num, pctError, et); }
        private ArrayList<String> a78(int num, int pctError, ExceptionTest et) { return a79(num, pctError, et); }
        private ArrayList<String> a79(int num, int pctError, ExceptionTest et) { return a80(num, pctError, et); }
        private ArrayList<String> a80(int num, int pctError, ExceptionTest et) { return a81(num, pctError, et); }
        private ArrayList<String> a81(int num, int pctError, ExceptionTest et) { return a82(num, pctError, et); }
        private ArrayList<String> a82(int num, int pctError, ExceptionTest et) { return a83(num, pctError, et); }
        private ArrayList<String> a83(int num, int pctError, ExceptionTest et) { return a84(num, pctError, et); }
        private ArrayList<String> a84(int num, int pctError, ExceptionTest et) { return a85(num, pctError, et); }
        private ArrayList<String> a85(int num, int pctError, ExceptionTest et) { return a86(num, pctError, et); }
        private ArrayList<String> a86(int num, int pctError, ExceptionTest et) { return a87(num, pctError, et); }
        private ArrayList<String> a87(int num, int pctError, ExceptionTest et) { return a88(num, pctError, et); }
        private ArrayList<String> a88(int num, int pctError, ExceptionTest et) { return a89(num, pctError, et); }
        private ArrayList<String> a89(int num, int pctError, ExceptionTest et) { return a90(num, pctError, et); }
        private ArrayList<String> a90(int num, int pctError, ExceptionTest et) { return a91(num, pctError, et); }
        private ArrayList<String> a91(int num, int pctError, ExceptionTest et) { return a92(num, pctError, et); }
        private ArrayList<String> a92(int num, int pctError, ExceptionTest et) { return a93(num, pctError, et); }
        private ArrayList<String> a93(int num, int pctError, ExceptionTest et) { return a94(num, pctError, et); }
        private ArrayList<String> a94(int num, int pctError, ExceptionTest et) { return a95(num, pctError, et); }
        private ArrayList<String> a95(int num, int pctError, ExceptionTest et) { return a96(num, pctError, et); }
        private ArrayList<String> a96(int num, int pctError, ExceptionTest et) { return a97(num, pctError, et); }
        private ArrayList<String> a97(int num, int pctError, ExceptionTest et) { return a98(num, pctError, et); }
        private ArrayList<String> a98(int num, int pctError, ExceptionTest et) { return a99(num, pctError, et); }
        private ArrayList<String> a99(int num, int pctError, ExceptionTest et) { return a100(num, pctError, et); }
        private ArrayList<String> a100(int num, int pctError, ExceptionTest et) {
            return et.test(num, pctError);
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        int numLoops = Integer.parseInt(args[0]);
        int pctExceptions = Integer.parseInt(args[1]);
        Class[] tests = { ExceptionTestUnchecked.class, ExceptionTestChecked.class, ExceptionTestDefensive.class };
        ExceptionTestBigStack etbt = new ExceptionTestBigStack();

        // Warmup Loop
        System.out.println("Start warmpup loop");
        for (Class c : tests) {
            ExceptionTest et = (ExceptionTest) c.newInstance();
            ArrayList<String> strings = et.test(numLoops, pctExceptions);
            etbt.test(numLoops, pctExceptions, et);
        }
        System.out.println("Done warmpup loop");


        for (Class c : tests) {
            System.out.println("Start test for " + c);
            long then = System.currentTimeMillis();
            ExceptionTest et = (ExceptionTest) c.newInstance();
            ArrayList<String> strings = et.test(numLoops, pctExceptions);
            long now = System.currentTimeMillis();
            System.out.println(c + ": " + numLoops + " iterations with " + pctExceptions + " exceptions took " + (now - then) + " ms " + " for " + strings.size() + " objects");

            then = System.currentTimeMillis();
            strings = etbt.test(numLoops, pctExceptions, et);
            now = System.currentTimeMillis();
            System.out.println(c + ": (BigStack) " + numLoops + " iterations with " + pctExceptions + " exceptions took " + (now - then) + " ms " + " for " + strings.size() + " objects");

        }
    }
}
