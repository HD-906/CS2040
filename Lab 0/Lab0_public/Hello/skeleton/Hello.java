/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

import java.util.*;
public class Hello {
	public static int count(String str) {
		int counter = 0;
		String[] words = str.split(" ");
		for (int i=0; i<words.length; i++) {
			if (words[i].equals("Hello")) {
				counter++;
			}
			else if (words[i].equals("World")) {
				counter--;
			}
		}
		return counter;
	}
	
	public static void main(String args[]) {
	    Scanner scan = new Scanner(System.in);
		String testCase = scan.nextLine();
		int num = count(testCase);
		System.out.println(num);
		scan.close();
  }
}