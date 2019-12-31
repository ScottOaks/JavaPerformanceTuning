/*
 * Copyright (c) 2009, 2013,2014 Scott Oaks. All rights reserved.
 */
package net.sdo;


import java.util.*;
import java.io.*;

/**
 * 
 * Parse output from jstack to provide a snapshot of where threads
 * are running or blocked.
 *
 * usage: java ParseJStack [ -name threadname ] file1 [file2 ...]
 * 
 * The list of files should be from one or more JDK 6 jstack commands. 
 * 
 * The threadname can be used to restrict output to threads that match a
 * particular pattern. For example, for glassfish you can say
 * -name thread-pool-1 to get all threads in the default ORB threadpool.
 * TODO: this should use regular expression pattern matching; right
 * now it simply uses substring matching (indexOf).
 * 
 */

public class ParseJStack {

    static class HashMapValueComparator implements Comparator {
	HashMap<String, Integer> hm;

	public HashMapValueComparator(HashMap<String, Integer> hm) {
	    this.hm = hm;
	}

	public int compare(Object o1, Object o2) {
	    String s1 = (String) o1;
	    String s2 = (String) o2;
	    int i1 = hm.get(s1).intValue();
	    int i2 = hm.get(s2).intValue();
	    if (i1 == i2)
	        return s1.compareTo(s2);
	    return i2 - i1;
	}

	public boolean equals(Object o) {
	    // Not for the general case, but for now...
	    return hm == o;
	}
    }

    private static Map<String, Integer>
                   getSortedMap(HashMap<String, Integer> hm) {
	TreeMap<String, Integer> tm =
	    new TreeMap<String, Integer>(new HashMapValueComparator(hm));
	Iterator<String> keys = hm.keySet().iterator();
	while (keys.hasNext()) {
	    String s = keys.next();
	    tm.put(s, hm.get(s));
	}
	return tm;
    }

    private static void printTable(HashMap<String, Integer> hm, String title) {
	String s;
	Map<String, Integer> table = getSortedMap(hm);
	Iterator<String> keys = table.keySet().iterator();
	System.out.println("Threads in state " + title);
	int total = 0;
	while (keys.hasNext()) {
	    s = keys.next();
	    Integer i = table.get(s);
	    total += i;
	    System.out.println("\t" + i + " threads running in " + s);
	}
	System.out.println("Total " + title + " Threads: " + total);
	System.out.println("");
    }

    private static class JStackParser {
	private BufferedReader br;
	private int lineNo = 0;
	private int vmThreads = 0;
	private String curMethodLine;
	private StringTokenizer threadTokenizer;
	private String fname;
	private boolean peeked = false;

	public JStackParser(String fname) throws IOException {
	    this.fname = fname;
	    br = new BufferedReader(new FileReader(fname));
	}

	public void close() throws IOException {
	    br.close();
	    lineNo = 0;
	    vmThreads = 0;
	    threadTokenizer = null;
	    fname = null;
	    peeked = false;
	}

	public String getNextThread() throws IOException {
	    peeked = false;
	    String s = br.readLine();
	    lineNo++;
	    while (s != null) {
	        if (!(s.startsWith("\""))) {
		    s = br.readLine();
		    lineNo++;
		    continue;
	        }
		if (s.startsWith("\"Concurrent Mark-Sweep GC Thread")) {
		    s = br.readLine();
		    lineNo++;
		    vmThreads++;
		    continue;
		}
		if (s.startsWith("\"G1")) {
		    s = br.readLine();
		    lineNo++;
		    vmThreads++;
		    continue;
		}
		if (restrictThreads && s.indexOf(threadName) < 0) {
		    s = br.readLine();
		    lineNo++;
		    continue;
		}
	        s = br.readLine();
	        lineNo++;
	        if (s.equals("")) {
		    vmThreads++;
		    continue;
	        }
                break;
	    }
	    if (s == null)
		return null;
	    threadTokenizer = new StringTokenizer(s);
	    threadTokenizer.nextToken();  // java.lang.Thread.State:
	    return threadTokenizer.nextToken();  // BLOCKED, etc.
	}

	public String peekCurrentMethod() throws IOException {
	    curMethodLine = br.readLine();
	    lineNo++;
	    peeked = true;
	    if (curMethodLine.equals(""))
		vmThreads++;
	    return curMethodLine;
	}

	public String getThreadSubState() {
	    return threadTokenizer.nextToken();
	}

	public void insertJavaMethod(HashMap<String, Integer>curTable,
					int skipLines) throws IOException {
	    for (int i = (peeked) ? 1 : 0; i < skipLines; i++) {
		curMethodLine = br.readLine();
	    }
	    lineNo += skipLines;
	    if (peeked) {
		lineNo--;
		peeked = false;
	    }
	    updateTable(curTable, curMethodLine);
	}

	public void insertJavaMethod(HashMap<String, Integer>curTable,
				     boolean skip) throws IOException {
	    if (!peeked) {
	        curMethodLine = br.readLine();
	        lineNo++;
	    }
	    else peeked = false;
	    if (curMethodLine.equals("")) {
		vmThreads++;
		return;
	    }
	    if (skip)
		skipJavaClasses();
	    updateTable(curTable, curMethodLine);
	}

	private void skipJavaClasses() throws IOException {
	    do {
	        curMethodLine = br.readLine();
	        lineNo++;
	        if (curMethodLine.equals(""))
		    break;
	    } while ((curMethodLine.indexOf("at java.") > 0) ||
		     (curMethodLine.indexOf("at ") < 0) ||
		     (curMethodLine.indexOf("at sun.") > 0));
        }

        private void updateTable(HashMap<String, Integer> table,
				 String methodString) {
	    StringTokenizer tok = new StringTokenizer(methodString);
	    String method = null;
	    try {
		tok.nextToken(); // at
	        method = tok.nextToken();
	    } catch (NoSuchElementException nsee) {
		method = "System Thread";
	    }
	    Integer i = (Integer) table.get(method);
	    if (i == null)
	        i = new Integer(1);
	    else i = new Integer(i.intValue() + 1);
	    table.put(method, i);
        }

	public int getVMThreads() {
	    return vmThreads;
	}

	public String toString() {
	    return "parsing file " + fname + " at line " + lineNo;
	}
    }

    private static String threadName = null;
    private static boolean restrictThreads = false;

    public static void main(String[] args) throws Exception {
        int arg;
	int vmThreads = 0;
	for (arg = 0; arg < args.length; arg++) {
	    if (!args[arg].startsWith("-"))
		break;
	    if (args[arg].equals("-name")) {
		restrictThreads = true;
		threadName = args[++arg];
		continue;
	    }
	    System.out.println(
		"usage: java ParseJStack [-name threadname] file1 ...");
	    System.exit(-1);
	}

	HashMap<String, Integer> waitingThreads = new HashMap<String, Integer>();
	HashMap<String, Integer> sleepingThreads = new HashMap<String, Integer>();
	HashMap<String, Integer> runningThreads = new HashMap<String, Integer>();
	HashMap<String, Integer> ioReadThreads = new HashMap<String, Integer>();
	HashMap<String, Integer> ioPollThreads = new HashMap<String, Integer>();
	HashMap<String, Integer> ioAcceptThreads = new HashMap<String, Integer>();
	HashMap<String, Integer> blockedThreads = new HashMap<String, Integer>();
	HashMap<String, Integer> unknownThreads = new HashMap<String, Integer>();

	while (arg < args.length) {
	    JStackParser jsp = new JStackParser(args[arg]);
	    try {
		String state = jsp.getNextThread();
		while (state != null) {
	            if (state.equals("BLOCKED")) {
			jsp.insertJavaMethod(blockedThreads, true);
	            }
	            else if (state.equals("TIMED_WAITING")) {
		        String subState = jsp.getThreadSubState();
		        if (subState.equals("(on")) {	// )
			    jsp.insertJavaMethod(waitingThreads, 2);
		        }
		        else if (subState.equals("(parking)")) {
		            jsp.insertJavaMethod(waitingThreads, true);
		        }
		        else if (subState.equals("(sleeping)")) {
			    jsp.insertJavaMethod(sleepingThreads, 2);
		        }
		        else {
	    	            throw new IllegalStateException(
			    "Unknown thread substate while " + jsp);
		        }
	            }
		    else if (state.equals("WAITING")) {
		        String subState = jsp.getThreadSubState();
		        if (subState.equals("(parking)")) {
		            jsp.insertJavaMethod(blockedThreads, true);
		        }
		        else if (subState.equals("(on")) {    // )
			    jsp.insertJavaMethod(waitingThreads, 2);
		        }
		        else {
			    throw new IllegalStateException(
			    "Unknown thread substate while " + jsp);
		        }
	            }
	            else if (state.equals("RUNNABLE")) {
		        String s = jsp.peekCurrentMethod();
		        if (s.equals("")) {
			    // VM thread; just get next one
		        } else if (s.indexOf("socketRead0") > 0) {
			    jsp.insertJavaMethod(ioReadThreads, true);
		        } else if (s.indexOf("poll0") > 0) {
			    jsp.insertJavaMethod(ioPollThreads, true);
		        } else if (s.indexOf("epollWait") > 0) {
			    jsp.insertJavaMethod(ioPollThreads, true);
		        } else if (s.indexOf("socketAccept") > 0) {
			    jsp.insertJavaMethod(ioAcceptThreads, true);
		        } else {
			    jsp.insertJavaMethod(runningThreads, false);
			}
	            }
	            else throw new IllegalStateException(
			    "Unknown thread state " + state + " while " + jsp);
		    state = jsp.getNextThread();
	        }
		vmThreads += jsp.getVMThreads();
		jsp.close();
		arg++;
    	    } catch (Exception e) {
	        System.out.println("Exception while " + jsp);
	        e.printStackTrace();
	        System.exit(-1);
    	    }
        }
        printTable(runningThreads, "Running");
        printTable(blockedThreads, "Blocked by Locks");
        printTable(waitingThreads, "Waiting for notify");
        printTable(ioReadThreads, "Waiting for I/O read");
        printTable(ioAcceptThreads, "Waiting for I/O accept");
        printTable(ioPollThreads, "Polling");
        printTable(sleepingThreads, "Sleeping");
        printTable(unknownThreads, "Unknown (system)");

        System.out.println("Total # of VM threads: " + vmThreads);
    }
}
