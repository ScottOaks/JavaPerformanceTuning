package net.sdo;

import java.util.ArrayList;

public class EpsilonTest {

	private static final ArrayList al = new ArrayList();

	public static void main(String[] args) {
		System.out.println("Start");
		for (int i = 0; i < 4096; i++) {
			al.add(new byte[1024*512]);
		}
		System.out.println("End");
	}

}
