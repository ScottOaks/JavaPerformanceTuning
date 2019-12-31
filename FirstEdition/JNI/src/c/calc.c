/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

#include <stdlib.h>
#include <net_sdo_RandomTestJava.h>

double calcInC(int nValues) {
    long d = 0;
    int j;
    for (j = 0; j < nValues; j++) {
        int n = rand() % 100 + 1;
        d += n;
    }
    double average = (double) d / nValues;
    return average;
}

JNIEXPORT jdouble JNICALL Java_net_sdo_RandomTestJava_calc0
(JNIEnv *env, jclass obj, jint nValues) {
    return calcInC(nValues);
}

jclass class = NULL;
jmethodID methodID;

JNIEXPORT jdouble JNICALL Java_net_sdo_RandomTestJava_calcFromC
(JNIEnv *env, jobject this, jint nTrials, jint nValues) {

    if (class == NULL) {
        class = (*env)->FindClass(env, "net/sdo/RandomTestJava");
        if (class == NULL) {
            fprintf(stderr, "Can't find class");
            exit(-1);
        }
        methodID = (*env)->GetMethodID(env, class, "calc", "(I)D");
        if (methodID == NULL) {
            fprintf(stderr, "Can't find method");
            exit(-1);
        }
    }
    double error = 0;
    int i;
    for (i = 0; i < nTrials; i++) {
        double average = (*env)->CallDoubleMethod(env, this, methodID, nValues);
        error += 50 - average;
    }
    return error;
}

JNIEXPORT jint JNICALL Java_net_sdo_RandomTestJava_getCRandom
(JNIEnv *env, jobject this) {
    return rand() % 100 + 1;
}
