import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Name : Li Huanda
 * Matric. No : A0216000H
 */

public class AlwaysPalindromesTest {
	public static void main(String args[]) throws FileNotFoundException {
		boolean PC = true;
		final String DEVICE = PC? "Administrator": "HD";
		final String ONEDRIVE = "C:\\Users\\" + DEVICE + "\\OneDrive\\";
		final String PREFIXLAB = "Lab 0\\Lab0_public\\AlwaysPalindromes\\"; // Change every lab
		final String PREFIX = ONEDRIVE + "Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\" + PREFIXLAB;
		final String EXTENSION_IN = "in";
		final String EXTENSION_OUT = "out";
		File[] file_in = {
				new File(PREFIX + "input\\1." + EXTENSION_IN),
				new File(PREFIX + "input\\2." + EXTENSION_IN),
				new File(PREFIX + "input\\3." + EXTENSION_IN),
				new File(PREFIX + "input\\4." + EXTENSION_IN),
				new File(PREFIX + "input\\5." + EXTENSION_IN),
				new File(PREFIX + "input\\6." + EXTENSION_IN),
				new File(PREFIX + "input\\7." + EXTENSION_IN)
				};
		File[] file_out = {
				new File(PREFIX + "output\\1." + EXTENSION_OUT),
				new File(PREFIX + "output\\2." + EXTENSION_OUT),
				new File(PREFIX + "output\\3." + EXTENSION_OUT),
				new File(PREFIX + "output\\4." + EXTENSION_OUT),
				new File(PREFIX + "output\\5." + EXTENSION_OUT),
				new File(PREFIX + "output\\6." + EXTENSION_OUT),
				new File(PREFIX + "output\\7." + EXTENSION_OUT)
				};
		Scanner scan;
		int testFrom = 1;
		int testTo = 7;
		for(int i_test=testFrom-1; i_test<testTo; i_test++) {
			scan = new Scanner(file_in[i_test]);
			long startTime = System.currentTimeMillis();
			System.out.println("INPUT: ");
			// ********************************** COPY PASTE BELOW ***************************************** //


			String word = scan.nextLine();
			
			if (isPalindrome(word))
				System.out.println(word);
			else {
				char[] wordChar = word.toCharArray();
				char[] palindromeWordChar = new char[word.length()*2]; 
				
				for (int i=0; i<wordChar.length; i++) {
					palindromeWordChar[i] = wordChar[i];
					palindromeWordChar[palindromeWordChar.length-1-i] = wordChar[i];
				}
				
				System.out.println(new String(palindromeWordChar));
			}
			
			// ******** OUTPUT ********
			scan.close();
			scan = new Scanner(file_out[i_test]);
			System.out.println("\nOUTPUT: ");
			do {
				System.out.println(scan.nextLine());
			} while (scan.hasNext());
			// ********************************** COPY PASTE ABOVE ***************************************** //
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("\nTimetaken for test " + i_test + ": \n" + elapsedTime + " ms\n");
			scan.close();
		}
	}

	private static boolean isPalindrome(String word) {
		for (int i=0; i<word.length()/2; i++) {
			if (word.charAt(i) != word.charAt(word.length()-1-i))
				return false;
		}
		return true;
	}
}
