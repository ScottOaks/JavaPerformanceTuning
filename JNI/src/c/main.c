
/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

double calcInC(int);

int main(int argc, char** argv) {
    struct timeval then;
    gettimeofday(&then, NULL);
    int nTrials, nValues;
    sscanf(argv[1], "%d", &nTrials);
    sscanf(argv[2], "%d", &nValues);
    double error = 0;
    int i;
    srand(time(NULL));
    for (i = 0; i < nTrials; i++) {
        double average = calcInC(nValues);
        error += 50 - average;
    }
    struct timeval now;
    gettimeofday(&now, NULL);
    printf("Error: %lf calculated in %ld\n", error, (now.tv_sec - then.tv_sec) * 1000 + now.tv_usec / 1000 - then.tv_usec / 1000);
    exit(EXIT_SUCCESS);
}
