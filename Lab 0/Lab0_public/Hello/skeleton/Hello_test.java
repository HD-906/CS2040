/**
 * Name :
 * Matric. No :
 */

import java.io.*;
import java.util.*;
public class Hello_test {
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
	
	public static void main(String args[]) throws FileNotFoundException {
		final String PREFIX = "D:\\Users\\OneDrive\\Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\Lab 0\\Lab0_public\\Hello\\";
		File[] file_in = {
				new File(PREFIX + "input\\1.in"),
				new File(PREFIX + "input\\2.in"),
				new File(PREFIX + "input\\3.in"),
				new File(PREFIX + "input\\4.in"),
				new File(PREFIX + "input\\5.in"),
				new File(PREFIX + "input\\6.in"),
				new File(PREFIX + "input\\7.in"),
				new File(PREFIX + "input\\8.in")
				};
		File[] file_out = {
				new File(PREFIX + "output\\1.out"),
				new File(PREFIX + "output\\2.out"),
				new File(PREFIX + "output\\3.out"),
				new File(PREFIX + "output\\4.out"),
				new File(PREFIX + "output\\5.out"),
				new File(PREFIX + "output\\6.out"),
				new File(PREFIX + "output\\7.out"),
				new File(PREFIX + "output\\8.out")
				};
		int num;
		Scanner scan;
		String testCase_in, testCase_out;
		for (int i=0; i<file_in.length; i++) {
			scan = new Scanner(file_in[i]);
			testCase_in = scan.nextLine();
			num = count(testCase_in);
			scan = new Scanner(file_out[i]);
			testCase_out = scan.nextLine();
			System.out.println(num + " and " + testCase_out);
		}
  }
}