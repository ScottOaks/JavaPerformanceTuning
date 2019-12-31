default: bin/randtest

bin/calc.o : src/c/calc.c
	-@mkdir bin
	gcc -c -O2 -Isrc/h -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -fPIC -o bin/calc.o src/c/calc.c

lib/libRandomTestCLibrary.so : bin/calc.o
	-@mkdir lib
	ld -shared -o lib/libRandomTestCLibrary.so -fPIC bin/calc.o 

bin/randtest : src/c/main.c lib/libRandomTestCLibrary.so
	-@mkdir bin
	gcc -O2 -Isrc/h -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -fPIC -o bin/randtest src/c/main.c -L lib -l RandomTestCLibrary

library: lib/libRandomTestCLibrary.so
