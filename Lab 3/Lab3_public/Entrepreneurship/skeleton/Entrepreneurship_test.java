import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Name:
 * Matric. No:
 */

public class Entrepreneurship_test {
	public static void main(String args[]) throws FileNotFoundException {
		final String PREFIX = "D:\\Users\\OneDrive\\Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\Lab 3\\Lab3_public\\Entrepreneurship\\";
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
			
			String[] currentCommand = scan.nextLine().split(" ");
			int commandLen = Integer.parseInt(currentCommand[0]);
			int numAvailable = Integer.parseInt(currentCommand[1]);
			int number;
			
			Batch[] batchList = new Batch[commandLen];
			
			for (int i=0; i<commandLen; i++) {
				Batch nextBatch = new Batch(scan);
				number = nextBatch.getNum();
				if (number != -1) {
					i -= number+1;
					commandLen -= number+1;
				}
				else {
					batchList[i] = nextBatch;
				}
			}
			
			// test print
			System.out.println("Batches left: " + commandLen);
			///
			
			scan.close();
			
			long totalPrice = 0;
			for (int i=0; i<commandLen; i++) {
				numAvailable = batchList[i].calculateBatch(numAvailable);
				totalPrice += batchList[i].getPrice();
				if (numAvailable == 0) {
					break;
				}
			}
			
			/*/ test print
			
			for (int i=0; i<commandLen; i++) {
				System.out.println("number: " + batchList[i].getNum());
				System.out.println("price: " + batchList[i].getPrice() + "\n");
			} 
			
			System.out.println("total price: " + totalPrice/10 + "." + totalPrice%10);
			*///
			System.out.println(totalPrice/10 + "." + totalPrice%10);
			
			// ******** OUTPUT ********
			
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