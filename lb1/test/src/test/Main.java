package test;

import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		
		Vector<Integer> arr = new Vector<Integer>();
		
		arr.add(2);
		arr.add(2);
		arr.add(2);
		System.out.println(arr);
		
		arr.set(0, 3);
		System.out.println(arr);
		//Example example = new Example();
		//example.writeFile();
	}

}
