import java.util.*;

/**
 * Name:
 * Matric. No:
 */

public class Groceries {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
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
	}
}
