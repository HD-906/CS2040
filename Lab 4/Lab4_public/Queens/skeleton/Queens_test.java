import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.*;

/**
 * Name: Li Huanda
 * Matric. No: A0216000HS
 */

public class Queens_test {
	static boolean check(int n, int[] col, Set<Integer> rowSet) {
		Set<Integer>[] rowSetArr = new HashSet[n];
		boolean res = true;
		
		for (int i=0; i<n; i++) { // ASSIGN INDIVIDUAL SET
			if (col[i] == -1) {
				res = false;
				Set<Integer> rowSetTemp = new HashSet<>();
				rowSetTemp.addAll(rowSet);
				for (int j=0; j<n; j++) {
					if (col[j] != -1) {
						rowSetTemp.remove(col[j] + i - j);
						rowSetTemp.remove(col[j] - i + j);
					}
				}
				if (rowSetTemp.size() <= 1) {
					Iterator<Integer> iterator = rowSetTemp.iterator();
					if (iterator.hasNext()) {
						int tempNum = iterator.next();
						col[i] = tempNum;
						rowSet.remove(tempNum);
						i = -1;
						res = true;
						continue;
					}
					else {
						return false;
					}
				}
				rowSetArr[i] = rowSetTemp;
			}
			else {
				rowSetArr[i] = null;
			}
		}
		
		if (res) {
			return true;
		}
		
		for (int i=0; i<n; i++) { // SIMULATE PLACEMENT
			if (col[i] == -1) {
				Iterator<Integer> iterator = rowSetArr[i].iterator();
				do {
					int tempNum = iterator.next();
					
					int[] colTemp = Arrays.copyOf(col, n);
					colTemp[i] = tempNum;
					
					Set<Integer> rowSetTemp = new HashSet<>();
					rowSetTemp.addAll(rowSet);
					rowSetTemp.remove(tempNum);
					
					if (check(n, colTemp, rowSetTemp)) {
						return true;
					}
				} while (iterator.hasNext());
				return false;
			}
		} 
		return true;
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		final String PREFIX = "D:\\Users\\OneDrive\\Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\Lab 4\\Lab4_public\\Queens\\";
		final String EXTENSION_IN = "txt";
		final String EXTENSION_OUT = "txt";
		File[] file_in = {
				new File(PREFIX + "input\\1." + EXTENSION_IN),
				new File(PREFIX + "input\\2." + EXTENSION_IN),
				new File(PREFIX + "input\\3." + EXTENSION_IN),
				new File(PREFIX + "input\\4." + EXTENSION_IN),
				new File(PREFIX + "input\\5." + EXTENSION_IN)
				};
		File[] file_out = {
				new File(PREFIX + "output\\1." + EXTENSION_OUT),
				new File(PREFIX + "output\\2." + EXTENSION_OUT),
				new File(PREFIX + "output\\3." + EXTENSION_OUT),
				new File(PREFIX + "output\\4." + EXTENSION_OUT),
				new File(PREFIX + "output\\5." + EXTENSION_OUT)
				};
		Scanner scan;
		int testFrom = 1;
		int testTo = 5;
		for(int i_test=testFrom-1; i_test<testTo; i_test++) {
			scan = new Scanner(file_in[i_test]);
			long startTime = System.currentTimeMillis();
			System.out.println("INPUT: ");
			// ********************************** COPY PASTE BELOW ***************************************** //

			String[] config = scan.nextLine().split(" ");
			int n = Integer.valueOf(config[0]);
			int m = Integer.valueOf(config[1]);
			
			int[] columnOriginal = new int[n];
			Arrays.fill(columnOriginal, -1);
			
			int[] arrInt = IntStream.range(0, n).toArray();
			Set<Integer> rowSet = Arrays.stream(arrInt).boxed().collect(Collectors.toSet());
			
			for (int i=0; i<n; i++) {
				int queenCol = scan.nextLine().indexOf('Q');
				if (queenCol != -1) {
					columnOriginal[queenCol] = i;
					rowSet.remove(i);
					if(--m == 0) {
						break;
					}
				}
			}
			
			System.out.println(check(n, columnOriginal, rowSet));
			
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