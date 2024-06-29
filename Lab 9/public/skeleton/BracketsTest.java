import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * Name:
 * Matric. No:
 */

public class BracketsTest {
	public static void main(String args[]) throws FileNotFoundException {
		boolean PC = true;
		final String DEVICE = PC? "Administrator": "HD";
		final String ONEDRIVE = "C:\\Users\\" + DEVICE + "\\OneDrive\\";
		final String PREFIXLAB = "Lab 9\\public\\"; // Change every lab
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
				new File(PREFIX + "input\\7." + EXTENSION_IN),
				new File(PREFIX + "input\\8." + EXTENSION_IN),
				new File(PREFIX + "input\\9." + EXTENSION_IN),
				new File(PREFIX + "input\\10." + EXTENSION_IN)
				};
		File[] file_out = {
				new File(PREFIX + "output\\1." + EXTENSION_OUT),
				new File(PREFIX + "output\\2." + EXTENSION_OUT),
				new File(PREFIX + "output\\3." + EXTENSION_OUT),
				new File(PREFIX + "output\\4." + EXTENSION_OUT),
				new File(PREFIX + "output\\5." + EXTENSION_OUT),
				new File(PREFIX + "output\\6." + EXTENSION_OUT),
				new File(PREFIX + "output\\7." + EXTENSION_OUT),
				new File(PREFIX + "output\\8." + EXTENSION_OUT),
				new File(PREFIX + "output\\9." + EXTENSION_OUT),
				new File(PREFIX + "output\\10." + EXTENSION_OUT)
				};
		Scanner scan;
		int testFrom = 1;
		int testTo = 10;
		for(int i_test=testFrom-1; i_test<testTo; i_test++) {
			scan = new Scanner(file_in[i_test]);
			long startTime = System.currentTimeMillis();
			System.out.println("INPUT: ");
			// ********************************** COPY PASTE BELOW ***************************************** //

			int length = Integer.parseInt(scan.nextLine());
			String[] equation = scan.nextLine().split(" ");
			scan.close();
			
			Stack<Long> lastNum = new Stack<>();
			
			long totalNumber = 0L;
			int index = 0;
			while(index < length) {
				while (index < length) {
					String str = equation[index++];
					if (str.equals("(")) {
						lastNum.push(totalNumber);
						totalNumber = 1L;
						break;
					} else if (str.equals(")")) {
						totalNumber = mod(lastNum.pop() * totalNumber);
						break;
					} else {
						totalNumber = mod(totalNumber + Long.parseLong(str));
					}
				}
				while (index < length) {
					String str = equation[index++];
					if (str.equals("(")) {
						lastNum.push(totalNumber);
						totalNumber = 0L;
						break;
					} else if (str.equals(")")) {
						totalNumber = mod(lastNum.pop() + totalNumber);
						break;
					} else {
						totalNumber = mod(totalNumber * Long.parseLong(str));
					}
				}
			}
			
			System.out.println(totalNumber);
			
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
	
	private static long mod(long number) {
		return number % 1_000_000_007L;
	}
}
