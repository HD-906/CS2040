import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Name:
 * Matric. No:
 */

public class MacaronsTest {
	public static void main(String args[]) throws FileNotFoundException {
		final String ONEDRIVE = "C:\\Users\\Administrator\\OneDrive\\";
		final String PREFIXLAB = "Lab 6\\Lab6_public\\Macarons\\"; // Change every lab
		final String PREFIX = ONEDRIVE + "Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\" + PREFIXLAB;
		final String EXTENSION_IN = "txt";
		final String EXTENSION_OUT = "txt";
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
			System.out.println("INPUT: ");
			// ********************************** COPY PASTE BELOW ***************************************** //

			int num = scan.nextInt();
		    int base = scan.nextInt();
		    
		    Map<Integer, Long> numOfOccur = new HashMap<>();

		    Integer lastRemainder = scan.nextInt() % base;
		    if (lastRemainder != 0) {
		    	numOfOccur.put(lastRemainder, 0L);
		    	numOfOccur.put(0, 0L);
		    }
		    else
			    numOfOccur.put(0, 1L);
		    
		    for (int i = 1; i < num; i++) {
		    	lastRemainder = (lastRemainder + scan.nextInt() % base) % base;
		    	numOfOccur.merge(lastRemainder, 0L, (a,b) -> ++a);
		    }
		    long sum = numOfOccur.values().stream().map(a -> a*(a+1)/2).reduce(0L, Long::sum);
		    System.out.println(sum);
			
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
}
