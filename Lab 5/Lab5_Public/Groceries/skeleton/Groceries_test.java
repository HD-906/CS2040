import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Name:
 * Matric. No:
 */

public class Groceries_test {
	public static void main(String args[]) throws FileNotFoundException {
		final String PREFIX = "D:\\Users\\OneDrive\\Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\Lab 5\\Lab5_public\\Groceries\\";
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
				new File(PREFIX + "input\\8." + EXTENSION_IN)
				};
		File[] file_out = {
				new File(PREFIX + "output\\1." + EXTENSION_OUT),
				new File(PREFIX + "output\\2." + EXTENSION_OUT),
				new File(PREFIX + "output\\3." + EXTENSION_OUT),
				new File(PREFIX + "output\\4." + EXTENSION_OUT),
				new File(PREFIX + "output\\5." + EXTENSION_OUT),
				new File(PREFIX + "output\\6." + EXTENSION_OUT),
				new File(PREFIX + "output\\7." + EXTENSION_OUT),
				new File(PREFIX + "output\\8." + EXTENSION_OUT)
				};
		Scanner scan;
		int testFrom = 1;
		int testTo = 8;
		for(int i_test=testFrom-1; i_test<testTo; i_test++) {
			scan = new Scanner(file_in[i_test]);
			long startTime = System.currentTimeMillis();
			// ********************************** COPY PASTE BELOW ***************************************** //
			String[] firstLine = scan.nextLine().split(" ");
			int numTotal = Integer.valueOf(firstLine[0]);
			int numGroup = Integer.valueOf(firstLine[1]);
			int numFree = Integer.valueOf(firstLine[2]);
			int[] priceList = new int[numTotal];

			// create price list
			for (int i=0; i<numTotal; i++) {
				priceList[i] = Integer.valueOf(scan.nextLine());
			}
			
			Arrays.sort(priceList);
			
			long priceTotal = 0L;
			int outsideGroup = numTotal % numGroup;
			for (int i=0; i<outsideGroup; i++) {
				priceTotal += priceList[i];
			}
			
			for (int i=outsideGroup; i<numTotal; i+=numGroup) {
				for (int j=numFree; j<numGroup; j++) {
					priceTotal += priceList[i+j];
				}
			}
			
			System.out.println(priceTotal);
			// ******** OUTPUT ********
			scan.close();
			scan = new Scanner(file_out[i_test]);
			System.out.println("OUTPUT: ");
			System.out.println(scan.nextLine());
			// ********************************** COPY PASTE ABOVE ***************************************** //
			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("\nTimetaken for test " + i_test + ": \n" + elapsedTime + " ms\n");
			scan.close();
		}
	}
}
