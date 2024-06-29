import java.util.*;

/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

public class Pancakes {
	public static final String POSSIBLE = "Possible";
	public static final String IMPOSSIBLE = "Impossible";
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

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

		System.out.println(possible? POSSIBLE: IMPOSSIBLE);
	}
}