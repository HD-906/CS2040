import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

public class Constellations_test {

	// Takes in n and k and returns nCk, which is n choose k
	public static long choose(int n, int k) {
		// System.out.println("choosing " + "(" + n + ", " + k + ")");

		if (k < 0 || k > n)
			return 0;
		if (k == 0 || k == n)
			return 1;
		
		if (k > n-k)
			k = n-k;
		return choose(n, k-1) * (n-k+1) / k;
		
		/* derivation:
		 *   n choose k
		 * = n! / (k!*(n-k)!)
		 * = n! / (k*(k-1)! * (n-k+1)!/(n-k+1))
		 * = n! / ((k-1)!*(n-(k-1))!) * (n-k+1)/k
		 * */
	}
	
	public static BigInteger calculate(int n, int a, int b) {
		if (n<a) {
			// System.out.println("calculating " + "(" + n + ", " + a + ", " + b + ")");
			return BigInteger.ONE;
		}
		else {
			// System.out.println("calculating " + "(" + n + ", " + a + ", " + b + ")");
			BigInteger partialCombi;
			BigInteger totalCombi = BigInteger.ZERO;
			for (int i=a; i<=b; i++) {
				partialCombi = calculate(n-i, a, b).multiply(BigInteger.valueOf(choose(n, i)));
				totalCombi = totalCombi.add(partialCombi);
			}
			return totalCombi;
		}
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		final String PREFIX = "D:\\Users\\OneDrive\\Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\Lab 4\\Lab4_public\\Constellations\\";
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
			// ********************************** COPY PASTE BELOW ***************************************** //
			String[] currentCommand = scan.nextLine().split(" ");
			int n = Integer.parseInt(currentCommand[0]);
			int a = Integer.parseInt(currentCommand[1]);
			int b = Integer.parseInt(currentCommand[2]);
			
			System.out.println(calculate(n, a, b));
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
