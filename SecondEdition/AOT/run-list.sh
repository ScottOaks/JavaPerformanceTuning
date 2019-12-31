#!/bin/bash

export TIMEFORMAT=%R
JAVA_HOME=$HOME/VMs/jdk-11.0.5
GRAAL_HOME=$HOME/VMs/graalvm-ee-19.2.1

run() {
    i=0
    t=0
    while [ $i -lt 10 ];
    do
        t=$(( $t + $( (time $* >& /dev/null) |& awk '{ print $1 * 1000 }') ))
        i=$(( $i + 1 ))
    done
    echo $*: $(( $t / 10 ))
}

compile() {
    echo Compiling ListDir.class
    $GRAAL_HOME/bin/javac ListDir.java

    echo Producing native image
    $GRAAL_HOME/bin/native-image ListDir
}

if [ ! -f listdir ]; then
    compile
fi
run $JAVA_HOME/bin/java ListDir $1
run ./listdir $1
