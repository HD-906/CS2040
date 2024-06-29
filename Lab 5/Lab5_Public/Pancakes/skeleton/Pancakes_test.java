import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

public class Pancakes_test {
	public static void main(String args[]) throws FileNotFoundException {
		final String PREFIX = "D:\\Users\\OneDrive\\Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\Lab 5\\Lab5_public\\Pancakes\\";
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
			System.out.println("INPUT: ");
			// ********************************** COPY PASTE BELOW ***************************************** //
			int length = Integer.valueOf(scan.nextLine());
			String[] listA = scan.nextLine().split(" ");
			String[] listB = scan.nextLine().split(" ");
			Map<String, Integer> map = new HashMap<>();
			boolean possible = true;
			
			for (int i=0; i<length; i++) {
				map.put(listA[i], i);
			}

			Set<Integer> indexesVisited = new HashSet<>();
			for (int i=0; i<length; i++) {
				int j = i;
				int temp = 0;
				//
				// Use a set to indicate visited index
				while (!listB[j].equals(listA[i]) && !indexesVisited.contains(i)) {
					possible = !possible;
					j = map.get(listB[j]);
					indexesVisited.add(j);
					
					temp++;
					if (temp > length) {
						System.out.println("Something went wrong");
						break;
					}
				}
				
				/*/
				// Changes ListB to indicate visited index
				while (!listB[j].equals(listA[i])) {
					String ListBJTemp = listB[j];
					listB[j] = listA[j];
					possible = !possible;
					j = map.get(ListBJTemp);
					
					temp++;
					if (temp > length) {
						System.out.println("Something went wrong");
						break;
					}
				}
				listB[j] = listA[j];
				/*/
			}

			System.out.println(possible? Pancakes.POSSIBLE: Pancakes.IMPOSSIBLE);
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
