/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

#include <stdlib.h>
#include <net_sdo_JNIBenchmark.h>

long calcInC(int nValues) {
    long d = 0;
    int j;
    for (j = 0; j < nValues; j++) {
        d += j;
    }
    long average = d / nValues;
    return average;
}

JNIEXPORT jlong JNICALL Java_net_sdo_JNIBenchmark_calcCC
(JNIEnv *env, jclass obj, jint nValues) {
    return calcInC(nValues);
}

JNIEXPORT jlong JNICALL Java_net_sdo_JNIBenchmark_calcC
(JNIEnv *env, jobject this, jint i) {
    return i * 3 + 15;
}

JNIEXPORT jlong JNICALL Java_net_sdo_JNIBenchmark_calcCCC
(JNIEnv *env, jclass obj, jint nTrials, jint nValues) {
    long l = 0;
    for (int i = 0; i < nTrials; i++) {
        long a = calcInC(nValues);
	l += 50 - a;
    }
    return l;
}
