default: lib/libRandomTestCLibrary.so

src/main/h/net_sdo_JNIBenchmark.h: src/main/java/net/sdo/JNIBenchmark.java
	javah -classpath target/benchmarks.jar -d src/main/h net.sdo.JNIBenchmark

bin/calc.o : src/main/c/calc.c src/main/h/net_sdo_JNIBenchmark.h
	-@mkdir bin
	gcc -c -O2 -Isrc/main/h -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -fPIC -o bin/calc.o src/main/c/calc.c

lib/libRandomTestCLibrary.so : bin/calc.o
	-@mkdir lib
	ld -shared -o lib/libRandomTestCLibrary.so -fPIC bin/calc.o 

clean:
	-rm lib/*
	-rm bin/*
