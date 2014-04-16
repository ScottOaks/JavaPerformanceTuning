To build:
    Even if you are not using an entity manager, building this requires a
    JPA implementation (since the mock entity manager still uses JPA
    interfaces). Download an implementation (e.g., the reference implementation
    from GlassFish) and in your environment set

    JAVAX_PERSISTENCE=path_to/javax.persistence.jar

    Then run
    ant

To run:
    In general, to use a MockEntityManager, run

    java <jvmargs> -DMockEntityManager=MockEntityManager \
          -jar jars/StockBatching.jar [num_stocks] \
	  [startDate] [endDate] [mode] [save]

    where 
    num_stocks -- optional, default 10000
    startDate -- optional, default 01/01/12
    endDate -- optional, default 12/13/12
    mode -- optional, 0 is standard Impl; 1 is Impl with logging
    save -- optional, number of entries to keep in the heap

Notes on Examples:
Example 4-2:
    To limit the impact of writing to System.out, redirect to /dev/null

    time java -client -DMockEntityManager=MockEntityManager \
    	 -jar jars/StockBatcher.jar
    time java -server -DMockEntityManager=MockEntityManager \
    	 -jar jars/StockBatcher.jar
    time java -XX:+TieredCompilation -DMockEntityManager=MockEntityManager \
    	 -jar jars/StockBatcher.jar

    Each command is run with an argument of 1, 10, 100, and so on to produce
    the results in the table.

Example 5-1:
    To run, e.g. with 100 entries saved in a 2GB heap:
    time java -Xmx2g -Xms2g \
	     -XX:+UseConcMarkSweepGC -XX:+UseParNewGC \
             -XX:+TieredCompilation -DMockEntityManager=MockEntityManager \
             -jar jars/StockBatcher.jar 50000 1/1/01 12/31/06 0 100

    For the throughput collector, remove the CMS arguments. For G1, replace
    the CMS arguments with -XX:+UseG1GC

Example 7-4:
    Run with this argument:
    -DMockEntityManager=StdRandomMockEntityManager
