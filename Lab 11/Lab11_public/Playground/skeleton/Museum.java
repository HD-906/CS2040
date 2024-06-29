/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

import java.util.*;

public class Museum {
	public static void main(String args[]) {
    	Scanner scan = new Scanner(System.in);
		Map<String, Integer> locationMap = new HashMap<>();
		int n = Integer.valueOf(scan.nextLine());

		String[] exhibits = new String[n];
		
		exhibits = scan.nextLine().split(" ");
		
		int maxChain = 1, currentChain = 1, iHead = -1;
		
		for (int i=0; i<n; i++) {
			String iD = exhibits[i];
			if (locationMap.containsKey(iD)) {
				int iDuplicate = locationMap.get(iD);
				maxChain = (maxChain < currentChain)? currentChain: maxChain;
				currentChain = i - iDuplicate;
				for (int j=iDuplicate-1; j>iHead; j--) {
					locationMap.remove(exhibits[j]);
				}
				iHead = iDuplicate;
			} else {
				currentChain++;
			}
			locationMap.put(iD, i);
		}

		System.out.println(maxChain);
  	}
}