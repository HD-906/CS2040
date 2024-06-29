import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Name: Li Huanda
 * Matric. No: A0216000H
 */

public class Palindromes {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int bagNum = scan.nextInt();
		scan.nextLine();
		Map<Integer, Bag> bagMap = new HashMap<>();
		
		for (int i=0; i<bagNum; i++) {
			String bagContent = scan.nextLine();
			int attribute = getAttribute(bagContent);
			
			if (bagMap.containsKey(attribute))
				bagMap.get(attribute).incrementQuantity();
			else
				bagMap.put(attribute, new Bag());
		}
		
		Object[] attributeArray = bagMap.keySet().toArray();
		
		long ways = 0;

		for (Object attributeObj: attributeArray) {
			Integer attribute = (Integer) attributeObj;
			Bag bag = bagMap.get(attribute);
			
			ways += bag.getLocalWays() + bag.getWaysWithAllOtherPairables(attribute, bagMap);
			bagMap.remove(attribute);
		}
		
		System.out.println(ways);
	}

	private static int getAttribute(String bagContent) {
		char[] attributeArray = new char[26];
		Arrays.fill(attributeArray, '0');
		
		for (char c: bagContent.toCharArray())
			attributeArray[c-'A'] = attributeArray[c-'A']=='0'? '1': '0';
		
		return Integer.parseInt(new String(attributeArray), 2);
	}
}

class Bag {
	private int quantity;
	
	// constructor
	Bag() {
		quantity = 1;
	}
	
	public void incrementQuantity() {
		quantity++;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public long getLocalWays() {
		return (quantity * (quantity-1L))/2L;
	}
	
	public long getWaysWithAllOtherPairables(int attribute, Map<Integer, Bag> bagMap) {
		long ways = 0;
		for (int i=0; i<26; i++) {
			int attributeOther = (1<<i)^attribute;
			if (bagMap.containsKey(attributeOther))
				ways += (long) quantity * bagMap.get(attributeOther).getQuantity();
		}
		
		return ways;
	}
}