import java.util.*;
import java.util.stream.*;

/**
 * Name: Li Huanda
 * Matric. No: A0216000HS
 */

public class Queens {
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
//					System.out.println("EXCEPTION FOUND ON COLUMN " + i); //
					Iterator<Integer> iterator = rowSetTemp.iterator();
					if (iterator.hasNext()) {
						int tempNum = iterator.next();
						col[i] = tempNum;
						rowSet.remove(tempNum);
//						System.out.println("FORCE FILL " + i + " WITH " + tempNum); //
						i = -1;
						res = true;
						continue;
					}
					else {
//						System.out.println("FALSE_A IMPOSSIBLE ON COLUMN " + i); //
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
			/*/
			System.out.println("TRUE_A BEFORE SIMULATION");
			System.out.println("AFTER ASSIGNMENT");
			System.out.println("Set: ");
			for (int i=0; i<n; i++) {
				System.out.println(rowSetArr[i]);
			} 
			System.out.println("");
			System.out.println(Arrays.toString(col));
			System.out.println("");
			/*/
			return true;
		}
		
		/*/
		System.out.println("AFTER ASSIGNMENT");
		System.out.println("Set: ");
		for (int i=0; i<n; i++) {
			System.out.println(rowSetArr[i]);
		} 
		System.out.println("");
		System.out.println(Arrays.toString(col));
		System.out.println("");
		/*/
		
		for (int i=0; i<n; i++) { // SIMULATE PLACEMENT
			if (col[i] == -1) {
				Iterator<Integer> iterator = rowSetArr[i].iterator();
				do {
					int tempNum = iterator.next();
//					System.out.println("SIMULATE TO FILL " + i + " WITH " + tempNum); //
					
					int[] colTemp = Arrays.copyOf(col, n);
					colTemp[i] = tempNum;
					
					Set<Integer> rowSetTemp = new HashSet<>();
					rowSetTemp.addAll(rowSet);
					rowSetTemp.remove(tempNum);
					
					if (check(n, colTemp, rowSetTemp)) {
//						System.out.println("TRUE_B after TRUE_A"); //
						return true;
					}
				} while (iterator.hasNext());
//				System.out.println("FALSE_B ALL SIMULATIONS FAILED"); //
				return false;
			}
		} 
//		System.out.println("TRUE_E STH WENT WRONG"); //
		return true;
	}
	
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		String[] config = scan.nextLine().split(" ");
		int n = Integer.valueOf(config[0]);
		int m = Integer.valueOf(config[1]);
		
		int[] columnOriginal = new int[n];
		Arrays.fill(columnOriginal, -1);
		
		int[] arrInt = IntStream.range(0, n).toArray();
		Set<Integer> rowSet = Arrays.stream(arrInt).boxed().collect(Collectors.toSet());
		
		for (int i=0; i<n&&m>0; i++) {
			int queenCol = scan.nextLine().indexOf('Q');
			if (queenCol != -1) {
				columnOriginal[queenCol] = i;
				rowSet.remove(i);
				m--;
			}
		}
		
		System.out.println(check(n, columnOriginal, rowSet));
		
	}
}