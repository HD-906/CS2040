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

public class PalindromesTest {
	public static void main(String args[]) throws FileNotFoundException {
		boolean PC = true;
		final String DEVICE = PC? "Administrator": "HD";
		final String ONEDRIVE = "C:\\Users\\" + DEVICE + "\\OneDrive\\";
		final String PREFIXLAB = "Lab 6\\Lab6_public\\Palindromes\\"; // Change every lab
		final String PREFIX = ONEDRIVE + "Documents\\Sch\\Yr3 (22-23)\\Sem1\\CS2040 - Data Structures and Algorithms\\Lab Materials\\" + PREFIXLAB;
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

	private static int getAttribute(String bagContent) {
		char[] attributeArray = new char[26];
		Arrays.fill(attributeArray, '0');
		
		for (char c: bagContent.toCharArray())
			attributeArray[c-'A'] = attributeArray[c-'A']=='0'? '1': '0';
		
		return Integer.parseInt(new String(attributeArray), 2);
	}
}
